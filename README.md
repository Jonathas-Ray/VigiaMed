# VigiaMed
Projeto IoT voltado para saúde.<br>
<font size ="3"> Modelo de negócio:<br>
Hibrido (Cloud-assisted on-premise)</font><br><br>
O Hardware não será pensado para mudar, mas
o software via Cloud receberá updates tanto com melhorias
quanto com correções de bugs.<br>
A ideia é que o Hardware será o mesmo após o fim do
projeto mudando apenas como os sistemas lidam com os
dados.
## Requisitos Funcionais:
### Módulo 1: IoT (Hardware e seu Software)
    •	**RF01**: O Wearable deve medir sinais vitais como: SpO2, Frequência Cardíaca e Temperatura Corporal.
    •	**RF02**: O Wearable deve lidar com os dados dos sensores, realizando todas as etapas de cálculos e tratamento dos dados coletados possibilitados pelos limites do microcontrolador usado.
    •	**RF03**: O Wearable deve ser capaz de enviar sua identidade junto com os sinais aferidos para ser associado à referência do paciente no sistema (ex: nome ou código).
    •	**RF04**: O Wearable deve enviar um pacote de dados já minimamente tratados (contendo os sinais vitais mensurados e a referência do paciente) para o software desktop em intervalos de tempo pré-definidos.
### Módulo 2: A Interface (Terminal de Visualização e Controle)
    •	**RF05**: A Interface deve permitir ao operador armazenar uma referência (nome/código) do paciente e associá-la à identificação de um dispositivo.
    •	**RF06**: A Interface deve ser capaz de receber os pacotes de dados, enviados pelo device.
    •	**RF07**: A interface deve ser capaz de exibir os sinais vitais finais recebidos junto à referência do paciente associado.

## Requisitos Não Funcionais:
### Hardware (Esfigmomanômetro)
    •	RNF01 - Os sensores do device devem possuir precisão clínica suficiente para medição de sinais vitais em ambiente hospitalar, observando para isso as precisões especificas dos sensores e seus respectivos módulos.
    •	RNF02 - Ergonomia e Conforto: Os dispositivos devem ser leves, hipoalergênicos e confortáveis para uso durante o período de monitoramento.
### Desempenho e Confiabilidade
    •	RNF03 - Periodicidade do Monitoramento: O intervalo entre as transmissões de dados do microcontrolador para o software deve ser configurável ou fixado em um valor clinicamente relevante (ex: a cada 1, 5 ou 10 minutos).
    •	RNF04 - Integridade do Pacote de Dados: O sistema de comunicação deve garantir que o pacote de dados enviado pelo device chegue ao software sem corrupção.
    •	RNF05 - Robustez da Conexão: As conexões sem fio (Device ↔ Desktop ↔ Servidor Local) devem ser estáveis e capazes de se restabelecer automaticamente em caso de falha.
### Software e Usabilidade
    •	RNF06 - Responsividade da Interface: A interface do software deve permanecer responsiva aos comandos do operador a todo momento.
    •	RNF07 - Clareza na Exibição: A interface deve ser capaz de apresentar os dados e a classificação de Manchester de forma clara e inequívoca indicando, ao menos, o horário da última medição recebida.

# Banco de Dados – VigiaMed

## Estrutura Geral  

O banco de dados do **VigiaMed** foi projetado para gerir informações relacionadas ao monitoramento de sinais vitais de pacientes em ambientes hospitalares e clínicos realizado por nosso sistema embarcado de mesmo nome. Dentro do modelo de negócio híbrido **Cloud-assisted on-premise**, em que o nosso hardware coleta os dados e o software exibe e permite a manipulação, o banco entrará com a persistência em servidor local auxiliando na tomada de decisão. Ele é composto por **11 entidades principais**, organizadas para garantir rastreabilidade, segurança e flexibilidade.  

### Unidade  
Representa hospitais, clínicas ou unidades de saúde da rede que utilizar o sistema.  
- **Campos:** nome, endereço, telefone, e-mail, tipo  
- **Relações:**  
  - 1:N com **Dispositivo**  
  - 1:N com **Usuário**  

---

### StatusDispositivo  
Define os possíveis estados em que um dispositivo pode se encontrar.  
- **Campos:** estado (descrição do status)  
- **Relações:**  
  - 1:N com **Dispositivo**  

---

### Dispositivo  

Refere-se aos equipamentos do projeto (pulseira, e demais dispositivos IoT).  
- Campos principais: tipo, modelo, número de série  
- Relações: pertence a uma **unidade** e registra várias **medições**  
=======
Refere-se ao nosso Hardware e ao Sistema nele embarcado.
- **Campos:** modelo, número de série, data_aquisicao
- **Relações:**  
  - N:1 com **Unidade**  
  - N:1 com **StatusDispositivo**  
  - 1:N com **Medição**  

---

### Usuário  
Controla o acesso ao sistema (administradores, médicos, técnicos etc.).  
- **Campos:** nome, tipo (papel do usuário), e-mail, senha (hash)  
- **Relações:**  
  - Pertence a uma **Unidade**  
  - 1:N com **Log**  

---


### Paciente  
Armazena dados revelantes para identificação das anomalias (Mencionados nos RF 05, 09 e 11) e uma referência que permita identificar o paciente monitorado.
- **Campos:** nome, referência (identificação interna)
- **Relações:**  
  - 1:1 com **Medição**  

---

### Sensor  
Define os sensores utilizados no sistema (ex: SpO₂, PPG, temperatura).  
- **Campos:** nome, unidade de medida  
- **Relações:**  
  - 1:N com **Medição Lista**  

---

### Medição  
Tabela central que representa o evento de associação do paciente e dispositivo.  
- **Campos:** data/hora da entrada
- **Relações:**  
  - N:1 com **Paciente**  
  - N:1 com **Dispositivo**  
  - 1:N com **Medição Lista** 

---

### Medição Lista  
Registra os resultados individuais das medições feitas pelos sensores.  
- **Campos:** resultado, tipo de medição, data/hora  
- **Relações:**  
  - N:1 com **Medição**  
  - N:1 com **Sensor**  

---

### Log  
Armazena eventos e ações realizadas no sistema.  
- **Campos:** ação, descrição, data  
- **Relações:**  
  - N:1 com **Usuário**  
  - Opcionalmente vinculado à **Tabela List**  

---

### Tabela List  
Tabela auxiliar para armazenar categorias e listas de referência.  
- **Campos:** nome  
- **Relações:**  
  - Pode ser associada a registros de **Log**  

---

## Relações Principais  

- Uma **Unidade** possui vários **Dispositivos** e **Usuários**  
- Um **Dispositivo** possui um **Status** e registra várias **Medições**  
- Um **Paciente** dá entrada uma vez sendo associado à **Medição**  
- Uma **Medição** pode gerar múltiplos registros em **Medição Lista**  
- Um **Sensor** pode gerar múltiplos registros em **Medição Lista**  
- Um **Usuário** pode gerar várias entradas no **Log**  
- A **Tabela List** funciona como base de categorias auxiliares  

---

## Objetivos do Modelo  

Garantir **rastreabilidade completa** (quem, quando, onde e com qual dispositivo).  
Oferecer **flexibilidade** para inclusão de novos sensores/tipos de medição.  
Armazenar **histórico** de sinais vitais, dispositivos e logs do sistema.  
Controlar **usuários e ações** de forma auditável.  
Preparar os dados para análises futuras em **Business Intelligence (BI)** e **monitoramento em tempo real**.  

---

Este modelo garante que o **VigiaMed** seja escalável, seguro e pronto para evoluções futuras.
