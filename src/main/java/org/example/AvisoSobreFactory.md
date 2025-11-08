Que fique avisado por aqui, por conta do final que usei (Jefté não criticou quando avaliou) a maneira de usar a factory
passa a precisar de instanciação unica, seguem abaixo exemplos mas aviso de antemão pois pode causar engano e é possível
mudar:

package org.example;

import org.example.entities.*;
import org.example.factories.*;
import java.util.Date;

public class Main {
public static void main(String[] args) {
// Pessoa (exemplo original)
PessoaFactory pessoaFactory = new PessoaFactory();
Pessoa pessoa1 = pessoaFactory.criarPessoa("Ray", 28);

        // Dispositivo
        DispositivoFactory dispositivoFactory = new DispositivoFactory();
        Dispositivo dispositivo1 = dispositivoFactory.criarDispositivo("Modelo X", "12345", new Date());
        
        // Log
        LogFactory logFactory = new LogFactory();
        Log log1 = logFactory.criarLog("LOGIN", "Usuário logou no sistema", new Date());
        
        // Medicao
        MedicaoFactory medicaoFactory = new MedicaoFactory();
        Medicao medicao1 = medicaoFactory.criarMedicao("2024-01-15 10:30:00");
        
        // MedicaoLista
        MedicaoListaFactory medicaoListaFactory = new MedicaoListaFactory();
        MedicaoLista medicaoLista1 = medicaoListaFactory.criarMedicaoLista(25.5, "Temperatura", "2024-01-15 10:30:00");
        
        // Paciente
        PacienteFactory pacienteFactory = new PacienteFactory();
        Paciente paciente1 = pacienteFactory.criarPaciente("João Silva", "REF123");
        
        // Sensor
        SensorFactory sensorFactory = new SensorFactory();
        Sensor sensor1 = sensorFactory.criarSensor("Sensor Temperatura", "Celsius");
        
        // StatusDispositivo
        StatusDispositivoFactory statusDispositivoFactory = new StatusDispositivoFactory();
        StatusDispositivo status1 = statusDispositivoFactory.criarStatusDispositivo("Ativo");
        
        // TabelaList
        TabelaListFactory tabelaListFactory = new TabelaListFactory();
        TabelaList tabela1 = tabelaListFactory.criarTabelaList("Tabela Principal");
        
        // Unidade
        UnidadeFactory unidadeFactory = new UnidadeFactory();
        Unidade unidade1 = unidadeFactory.criarUnidade("Unidade Centro", "Rua Central, 123", "11-9999-8888", "centro@email.com");
        
        // Usuario
        UsuarioFactory usuarioFactory = new UsuarioFactory();
        Usuario usuario1 = usuarioFactory.criarUsuario("Admin", "Administrador", "admin@email.com", "senha123");
    }
