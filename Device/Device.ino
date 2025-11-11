#include "secrets.h"
#include <WiFi.h>
#include <WiFiClientSecure.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#include "MAX30105.h"

#define ID_DO_APARELHO "vitals"
#define PINO_SDA 8
#define PINO_SCL 9

WiFiClientSecure clienteSeguro;
MAX30105 sensorParticula;

// Configuração do LED RGB
#define LEDRGB 48
#define LED_COUNT 1
Adafruit_NeoPixel strip(LED_COUNT, LEDRGB, NEO_GRB + NEO_KHZ800);

// Variáveis globais do Firebase
String tokenId = "";
unsigned long tempoAquisicaoToken = 0;
const unsigned long VALIDADE_TOKEN_MS = 3500UL * 1000;
bool firebaseConectado = false;
bool todasConexoesTestadas = false;

// Variáveis do sensor de frequência cardíaca
const byte TAMANHO_MEDIA = 4;
byte taxasBatimento[TAMANHO_MEDIA];
byte posicaoAtualTaxa = 0;
long ultimoBatimento = 0;
float batimentosPorMinuto;
int mediaBatimentos = 0;

// Variáveis para controle de tempo
unsigned long ultimaAtualizacaoFirebase = 0;
const unsigned long INTERVALO_ATUALIZACAO_FIREBASE = 2000;

// Variáveis para autoajuste
byte intensidadeLedAtual = 0x1F;
unsigned long ultimoAjuste = 0;
const unsigned long INTERVALO_AJUSTE = 3000;
bool ajusteConcluido = false;
int qualidadeSinal = 0;

// Variáveis para detecção REAL de batimentos
bool sensorEncontrado = false;
unsigned long ultimaVerificacaoSensor = 0;
const unsigned long INTERVALO_VERIFICACAO = 5000;

long baseline = 0;
bool baselineEstabelecida = false;
unsigned long tempoBaseline = 0;

// Variáveis aprimoradas para detecção de picos
long ultimoValor = 0;
long penultimoValor = 0;
bool subindo = false;
unsigned long ultimoPico = 0;
const unsigned long INTERVALO_MINIMO_PICOS = 300;

// Variáveis para controle de tempo REAL
unsigned long ultimaLeitura = 0;
const unsigned long INTERVALO_LEITURA = 20;

// Variáveis para piscada sem delay
bool piscando = false;
unsigned long inicioPiscada = 0;
const unsigned long DURACAO_PISCADA = 30;

// Variáveis para detecção de falha
unsigned long ultimaLeituraValida = 0;
const unsigned long TEMPO_MAXIMO_SEM_SINAL = 10000;

// Protótipos de função
bool verificarEReconectarConexoes();
bool enviarLeituraFirebase(String tipoLeitura, String leituraAtual);
void conectarWiFi();
bool fazerLoginFirebase();
bool verificarToken();
void falhou();
void verificarSensor();
void processarLeiturasTempoReal(unsigned long agora);
void ajustarSensorAutomaticamente();
int calcularQualidadeSinal(long valorIR);
void mostrarStatus(uint32_t cor, String mensagem);

void setup() {
  Serial.begin(115200);
  delay(200);
  strip.begin();
  strip.show();

  // Inicializar I2C
  Wire.begin(PINO_SDA, PINO_SCL);

  Serial.println("=== INICIANDO SISTEMA ===");
  
  // TESTE 1: Sensor de batimentos
  mostrarStatus(strip.Color(255, 255, 0), "Testando sensor...");
  verificarSensor();
  if (sensorEncontrado) {
    mostrarStatus(strip.Color(0, 255, 0), "Sensor OK!");
  } else {
    mostrarStatus(strip.Color(255, 0, 0), "Sensor FALHOU!");
    return;
  }

  // TESTE 2: Conexão WiFi
  mostrarStatus(strip.Color(255, 165, 0), "Conectando WiFi...");
  bool wifiConectado = false;
  for (int i = 0; i < NUM_REDES; i++) {
    Serial.print("Tentando rede ");
    Serial.println(WIFI_SSIDS[i]);
    WiFi.begin(WIFI_SSIDS[i], WIFI_PASSWORDS[i]);
    
    unsigned long inicio = millis();
    while (WiFi.status() != WL_CONNECTED && millis() - inicio < 10000) {
      delay(500);
      Serial.print(".");
    }
    
    if (WiFi.status() == WL_CONNECTED) {
      wifiConectado = true;
      Serial.println("\nWiFi conectado!");
      mostrarStatus(strip.Color(0, 255, 0), "WiFi OK!");
      break;
    }
  }
  
  if (!wifiConectado) {
    mostrarStatus(strip.Color(255, 0, 0), "WiFi FALHOU!");
    return;
  }

  // TESTE 3: Login Firebase
  mostrarStatus(strip.Color(0, 255, 255), "Login Firebase...");
  if (fazerLoginFirebase()) {
    mostrarStatus(strip.Color(0, 255, 0), "Firebase OK!");
    firebaseConectado = true;
  } else {
    mostrarStatus(strip.Color(255, 0, 0), "Firebase FALHOU!");
    return;
  }

  // SISTEMA PRONTO
  mostrarStatus(strip.Color(0, 255, 0), "Sistema Pronto!");
  todasConexoesTestadas = true;
}

void loop() {
  unsigned long tempoAtual = millis();
  
  // Verificar sensor periodicamente
  if (tempoAtual - ultimaVerificacaoSensor >= INTERVALO_VERIFICACAO) {
    ultimaVerificacaoSensor = tempoAtual;
    verificarSensor();
  }
  
  if (!sensorEncontrado) {
    // Sensor não encontrado - PISCA AZUL
    if ((tempoAtual / 1000) % 2 == 0) {
      strip.setPixelColor(0, strip.Color(0, 0, 255));
    } else {
      strip.setPixelColor(0, strip.Color(0, 0, 0));
    }
    strip.show();
  } else {
    // Sensor encontrado - processar leituras
    processarLeiturasTempoReal(tempoAtual);
  }
  
  // Gerenciar piscada do batimento
  if (piscando && (tempoAtual - inicioPiscada >= DURACAO_PISCADA)) {
    strip.setPixelColor(0, strip.Color(0, 0, 100));
    strip.show();
    piscando = false;
  }
  
  // Autoajuste do sensor
  if (!ajusteConcluido && tempoAtual - ultimoAjuste >= INTERVALO_AJUSTE) {
    ultimoAjuste = tempoAtual;
    ajustarSensorAutomaticamente();
  }
  
  // Atualizar Firebase apenas se todas as conexões estiverem OK
  if (todasConexoesTestadas && tempoAtual - ultimaAtualizacaoFirebase >= INTERVALO_ATUALIZACAO_FIREBASE) {
    ultimaAtualizacaoFirebase = tempoAtual;
    
    bool envioSucesso = false;
    if (mediaBatimentos > 0) {
      String media = String(mediaBatimentos);
      envioSucesso = enviarLeituraFirebase("heartRate", media);
    } else {
      envioSucesso = enviarLeituraFirebase("heartRate", "0");
    }
    
    if (!envioSucesso) {
      falhou();
      firebaseConectado = verificarEReconectarConexoes();
    }
  }
  
  delay(10);
}

void mostrarStatus(uint32_t cor, String mensagem) {
  Serial.println(mensagem);
  strip.setPixelColor(0, cor);
  strip.show();
  delay(2000);
  strip.setPixelColor(0, strip.Color(0, 0, 0));
  strip.show();
  delay(500);
}

void verificarSensor() {
  if (sensorParticula.begin(Wire, I2C_SPEED_FAST)) {
    if (!sensorEncontrado) {
      // Pisca verde 3x (bloqueante apenas na inicialização)
      for (int i = 0; i < 3; i++) {
        strip.setPixelColor(0, strip.Color(0, 255, 0));
        strip.show();
        delay(200);
        strip.setPixelColor(0, strip.Color(0, 0, 0));
        strip.show();
        delay(200);
      }
      
      // Configurar sensor com parâmetros para pele pigmentada
      sensorParticula.setup();
      sensorParticula.setPulseAmplitudeRed(intensidadeLedAtual);
      sensorParticula.setPulseAmplitudeGreen(0);
      
      // Resetar variáveis
      baselineEstabelecida = false;
      tempoBaseline = millis();
      ultimoValor = 0;
      penultimoValor = 0;
      subindo = false;
    }
    sensorEncontrado = true;
  } else {
    sensorEncontrado = false;
  }
}

void processarLeiturasTempoReal(unsigned long agora) {
  if (agora - ultimaLeitura < INTERVALO_LEITURA) {
    return;
  }
  ultimaLeitura = agora;
  
  long valorAtual = sensorParticula.getRed();
  
  // Estabelecer baseline durante os primeiros 5 segundos
  if (!baselineEstabelecida) {
    if (agora - tempoBaseline < 5000) {
      // Durante a calibração - LED amarelo piscante
      if ((agora / 500) % 2 == 0) {
        strip.setPixelColor(0, strip.Color(255, 255, 0));
      } else {
        strip.setPixelColor(0, strip.Color(0, 0, 0));
      }
      strip.show();
      
      baseline = (baseline * 0.99) + (valorAtual * 0.01);
    } else {
      baselineEstabelecida = true;
      strip.setPixelColor(0, strip.Color(0, 0, 100));
      strip.show();
    }
    return;
  }
  
  // APÓS CALIBRAÇÃO - DETECÇÃO REAL EM TEMPO REAL
  if (!piscando) {
    strip.setPixelColor(0, strip.Color(0, 0, 100));
    strip.show();
  }
  
  // Detecção de PICO real
  long valorNormalizado = valorAtual - baseline;
  
  bool estavaSubindo = subindo;
  subindo = (valorNormalizado > ultimoValor);
  
  // Detectar pico: estava subindo e agora começou a descer
  if (estavaSubindo && !subindo && 
      ultimoValor > 5000 &&
      (agora - ultimoPico) > INTERVALO_MINIMO_PICOS &&
      !piscando) {
    
    // PICO DETECTADO - INICIAR PISCADA VERMELHA
    ultimoPico = agora;
    piscando = true;
    inicioPiscada = agora;
    strip.setPixelColor(0, strip.Color(255, 0, 0));
    strip.show();

    // Cálculo do BPM
    long diferencaTempo = agora - ultimoBatimento;
    ultimoBatimento = agora;

    batimentosPorMinuto = 60 / (diferencaTempo / 1000.0);

    if (batimentosPorMinuto < 255 && batimentosPorMinuto > 20) {
      taxasBatimento[posicaoAtualTaxa++] = (byte)batimentosPorMinuto;
      posicaoAtualTaxa %= TAMANHO_MEDIA;

      mediaBatimentos = 0;
      for (byte x = 0; x < TAMANHO_MEDIA; x++) {
        mediaBatimentos += taxasBatimento[x];
      }
      mediaBatimentos /= TAMANHO_MEDIA;
    }
  }
  
  penultimoValor = ultimoValor;
  ultimoValor = valorNormalizado;
  baseline = (baseline * 0.999) + (valorAtual * 0.001);
}

int calcularQualidadeSinal(long valorIR) {
    if (valorIR > 100000) return 100;
    if (valorIR > 50000) return 80;
    if (valorIR > 20000) return 60;
    if (valorIR > 10000) return 40;
    if (valorIR > 5000) return 20;
    return 0;
}

void ajustarSensorAutomaticamente() {
    // LED amarelo durante ajuste
    strip.setPixelColor(0, strip.Color(255, 255, 0));
    strip.show();
    
    // Lógica de ajuste para pele pigmentada
    byte intensidadesParaTestar[] = {0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F};
    byte numTestes = sizeof(intensidadesParaTestar) / sizeof(intensidadesParaTestar[0]);
    
    for (int i = 0; i < numTestes; i++) {
        byte intensidadeTeste = intensidadesParaTestar[i];
        sensorParticula.setPulseAmplitudeRed(intensidadeTeste);
        sensorParticula.setPulseAmplitudeIR(intensidadeTeste);
        intensidadeLedAtual = intensidadeTeste;
        delay(200);
    }
    
    ajusteConcluido = true;
    strip.setPixelColor(0, strip.Color(0, 0, 100));
    strip.show();
}

bool enviarLeituraFirebase(String tipoLeitura, String leituraAtual) {
    if (!verificarEReconectarConexoes()) {
        return false;
    }

    String url = String("https://") + FIREBASE_HOST + "/vitals/04CDF8/" + tipoLeitura + "/Valor.json?auth=" + tokenId;
    clienteSeguro.setInsecure();
    HTTPClient http;
    http.begin(clienteSeguro, url);
    http.addHeader("Content-Type", "application/json");

    String cargaUtil = "\"" + leituraAtual + "\"";
    int codigoHttp = http.PUT(cargaUtil);

    bool sucesso = (codigoHttp == HTTP_CODE_OK);

    if (sucesso) {
        strip.setPixelColor(0, strip.Color(0, 255, 0));
        strip.show();
        delay(750);
    } else {
        strip.setPixelColor(0, strip.Color(255, 255, 0));
        strip.show();
        delay(750);
    }

    http.end();
    return sucesso;
}

void conectarWiFi() {
    if (WiFi.status() == WL_CONNECTED) return;
    
    for (int i = 0; i < NUM_REDES; i++) {
        WiFi.begin(WIFI_SSIDS[i], WIFI_PASSWORDS[i]);
        
        unsigned long inicio = millis();
        bool conectado = false;
        
        while (millis() - inicio < 10000) {
            if (WiFi.status() == WL_CONNECTED) {
                conectado = true;
                break;
            }
            delay(500);
        }
        
        if (conectado) {
            strip.setPixelColor(0, strip.Color(0, 255, 0));
            strip.show();
            delay(1000);
            strip.setPixelColor(0, strip.Color(0, 0, 100));
            strip.show();
            return;
        } else {
            strip.setPixelColor(0, strip.Color(255, 255, 0));
            strip.show();
            delay(300);
            strip.setPixelColor(0, strip.Color(0, 0, 0));
            strip.show();
            delay(300);
        }
    }
    
    for (int i = 0; i < 3; i++) {
        strip.setPixelColor(0, strip.Color(255, 0, 0));
        strip.show();
        delay(500);
        strip.setPixelColor(0, strip.Color(0, 0, 0));
        strip.show();
        delay(500);
    }
}

bool verificarEReconectarConexoes() {
    if (WiFi.status() != WL_CONNECTED) {
        conectarWiFi();
    }
    
    bool firebaseOk = false;
    if (WiFi.status() == WL_CONNECTED) {
        if (!verificarToken()) {
            firebaseOk = fazerLoginFirebase();
        } else {
            firebaseOk = true;
        }
    }
    
    return firebaseOk;
}

bool fazerLoginFirebase() {
  if (WiFi.status() != WL_CONNECTED) return false;
  WiFiClientSecure cliente;
  cliente.setInsecure();
  HTTPClient https;
  String url = String("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=") + FIREBASE_API_KEY;
  https.begin(cliente, url);
  https.addHeader("Content-Type", "application/json");
  StaticJsonDocument<256> documento;
  documento["email"] = FIREBASE_EMAIL;
  documento["password"] = FIREBASE_PASSWORD;
  documento["returnSecureToken"] = true;
  String cargaUtil;
  serializeJson(documento, cargaUtil);
  int codigoHttp = https.POST(cargaUtil);
  String resposta = https.getString();
  if (codigoHttp == HTTP_CODE_OK) {
    StaticJsonDocument<512> documentoResposta;
    if (!deserializeJson(documentoResposta, resposta)) {
      tokenId = documentoResposta["idToken"].as<String>();
      tempoAquisicaoToken = millis();
      https.end();
      return true;
    }
  }
  https.end();
  return false;
}

bool verificarToken() {
  if (tokenId.isEmpty()) return false;
  if (millis() - tempoAquisicaoToken > VALIDADE_TOKEN_MS) {
    return fazerLoginFirebase();
  }
  return true;
}

void falhou(){
    for (int i = 0; i < 3; i++) {
        strip.setPixelColor(0, strip.Color(255, 0, 0));
        strip.show();
        delay(750);
    }
}