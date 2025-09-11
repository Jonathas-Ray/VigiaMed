# VigiaMed
Projeto IoT voltado para saúde.<br><br>
<font size ="3"> Modelo de negócio:<br>
Hibrido (Cloud-assisted on-premise)</font><br><br>
O Hardware não será pensado para mudar, mas 
o software via Cloud receberá updates tanto com melhorias
quanto com correções de bugs.<br>
A ideia é que o Hardware será o mesmo após o fim do 
projeto mudando apenas como os sistemas lidam com os 
dados.<br>

## Requisitos Funcionais:
### Módulo 1: Pulseira (Unidade Central de Processamento)
    •	**RF01**: A pulseira deve medir diretamente os sinais de SpO2, Frequência Cardíaca e Temperatura Corporal e pressão arterial
    •	**RF02**: A pulseira deve receber os dados dos sensores.
    •	**RF03**: A pulseira deve executar todos os cálculos para transformar os dados brutos em informações finais, incluindo o cálculo da Pressão Arterial (baseado no PTT), Frequência Cardíaca, SpO2 e Temperatura.
    •	**RF04**: A pulseira deve ser capaz de receber e armazenar temporariamente uma referência de paciente (ex: nome ou código) enviada pelo software desktop.
    •	**RF05**: A pulseira deve enviar um pacote de dados consolidados (contendo todos os sinais vitais já calculados e a referência do paciente) para o software desktop em intervalos de tempo pré-definidos 
    ou em caso de significativa alteração.

### Módulo 2: Software Desktop (Terminal de Visualização e Controle)
    •	**RF06**: O software desktop deve ter uma função para que o operador insira uma referência (nome/código) e a envie para um dispositivo (pulseira) específico.
    •	**RF07**: O software desktop deve ser capaz de receber os pacotes de dados periódicos e já processados, enviados pela pulseira.
    •	**RF08**: A interface do software desktop deve ser capaz de exibir os sinais vitais finais recebidos (SpO2, Frequência Cardíaca, Pressão Arterial, Temperatura) e a referência do paciente associado.
    •	**RF09** O software desktop deve possuir ou permitir a configuração de faixas de sinais vitais que correspondem a cada cor do Protocolo de Manchester ou valor que indiquem de alguma forma anormalidades.
    •	**RF10**: O software desktop deve permitir classificar o paciente de acordo com o protocolo, exibindo a cor correspondente, e gerar os alertas visuais e sonoros necessários.
=======
## Requisitos Não Funcionais:
### Hardware (Pulseira e Anel)
    •	RNF01 - Capacidade de Processamento Embarcado: A pulseira deve possuir um microcontrolador com capacidade de processamento e memória suficientes para executar todos os algoritmos de cálculo de forma eficiente.
    •	RNF02 - Autonomia da Bateria: A bateria deve ser suficiente para cobrir turnos de monitoramento então se espera autonomia de algumas horas, possívelmente de 8 a 12 horas.
    •	RNF03 - Ergonomia e Conforto: O dispositivo deve ser leve, hipoalergênicos e confortáveis para uso durante o período de monitoramento.
### Desempenho e Confiabilidade
    •	RNF04 - Periodicidade do Monitoramento: O intervalo entre as transmissões de dados da pulseira para o software deve ser configurável ou fixado em um valor clinicamente relevante (ex: a cada 1, 5 ou 10 minutos).
    •	RNF05 - Integridade do Pacote de Dados: O sistema de comunicação deve garantir que o pacote de dados enviado pela pulseira chegue ao software sem corrupção.
    •	RNF06 - Robustez da Conexão: As conexões com e sem fio (Pulseira ↔ Desktop) devem ser estáveis e capazes de se restabelecer automaticamente em caso de falha.
### Software e Usabilidade
    •	RNF07 - Responsividade da Interface: A interface do software deve permanecer responsiva aos comandos do operador a todo momento.
    •	RNF08 - Clareza na Exibição: A interface deve ser capaz de apresentar os dados e a classificação de Manchester de forma clara e inequívoca, indicando o horário da última medição recebida.
    •	RNF09 - Compatibilidade de SO: O software desktop deve ser compatível com o sistema operacional Windows 10 ou superior.

Delimitando o escopo do projeto percebeu-se a inviabilidade de ECG no projeto como foi pensado, apesar da disponibilidade de sensores para IoT, uma vez que para a realidade hospitalar seriam necessários um mínimo de 5 sensores muito distantes entre si (no chamado "padrão ouro" são utilizados 10).
=======
Delimitando o escopo do projeto percebeu-se a inviabilidade de ECG
no projeto como foi pensado, apesar da disponibilidade de sensores 
para IoT, uma vez que para a realidade hospitalar seriam necessários
um mínimo de 5 sensores muito distantes entre si (no chamado "padrão 
ouro" são utilizados 10).
 
# Banco de Dados – VigiaMed  

O banco de dados do **VigiaMed** foi projetado para armazenar e organizar informações relacionadas ao monitoramento de sinais vitais de pacientes em ambientes hospitalares ou clínicos. Ele segue o modelo de negócio híbrido **Cloud-assisted on-premise**, em que o hardware (pulseira e sensores) coleta os dados e o software, via nuvem, processa e gera informações para tomada de decisão.  

## Estrutura Geral  
O modelo é composto por sete entidades principais:  

### Unidade  
Representa os hospitais, clínicas ou unidades de saúde que utilizam o sistema.  
- Campos principais: nome, endereço, telefone, e-mail  
- Relação: 1:N com **Dispositivo** (uma unidade pode ter vários dispositivos)  

### Dispositivo  
Refere-se aos equipamentos do projeto (pulseira, e demais dispositivos IoT).  
- Campos principais: tipo, modelo, número de série  
- Relações: pertence a uma **unidade** e registra várias **medições**  

### Paciente  
Armazena os dados pessoais dos pacientes monitorados.  
- Campos principais: nome, idade, sexo, CPF, data de nascimento  
- Relação: 1:N com **Medição** (um paciente pode ter várias medições)  

### Sensor  
Define os sensores utilizados (como SpO2, PPG e temperatura).  
- Campos principais: nome, tipo do sensor  
- Relação: 1:N com **Medição** (um sensor pode gerar várias medições)  

### Medição  
É a tabela central do sistema, responsável por registrar os dados coletados dos pacientes.  
- Campos principais: batimento cardíaco, oxigenação (SpO2), pressão sistólica, pressão diastólica, temperatura, data e hora da medição  
- Relações: vinculado a um **paciente**, um **dispositivo** e um **sensor**  

### Log de Medicação  
Armazena eventos relacionados a intervenções médicas.  
- Campos principais: descrição, data/hora do evento, vínculo com a medição  
- Relação: 1:N com **Medição** (uma medição pode ter múltiplos registros de medicação)  

## Relações Principais  
- Uma **Unidade** possui vários **Dispositivos**  
- Um **Dispositivo** registra várias **Medições**  
- Um **Paciente** pode ter diversas **Medições**  
- Um **Sensor** pode gerar múltiplas **Medições**  
- Uma **Medição** pode estar associada a diferentes registros no **Log de Medicação**  

## Objetivo do Modelo  
O modelo de dados do VigiaMed foi construído para:  
- Garantir rastreabilidade das informações coletadas (quem, quando, onde e com qual dispositivo).  
- Oferecer flexibilidade para inclusão de novos sensores sem necessidade de grandes mudanças na estrutura.  
- Viabilizar o armazenamento histórico de sinais vitais e intervenções médicas.  
- Preparar a base para análises futuras em **Business Intelligence (BI)** e monitoramento clínico em tempo real.  
