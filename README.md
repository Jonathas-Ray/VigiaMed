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
### Módulo 1: IoT (Hardware e seu Software)
    •	**RF01**: O Wearable deve medir sinais vitais como: SpO2, Frequência Cardíaca e Temperatura Corporal.
    •	**RF02**: O Wearable deve lidar com os dados dos sensores, realizando todas as etapas de cálculos e tratamento dos dados coletados possibilitados pelos limites do microcontrolador usado.
    •	**RF03**: O Wearable deve ser capaz de receber e armazenar temporariamente uma referência de paciente (ex: nome ou código) enviada pelo software desktop para que possa enviar seus dados de maneira associada à essa referência.
    •	**RF04**: O Wearable deve enviar um pacote de dados já minimamente tratados (contendo os sinais vitais mensurados e a referência do paciente) para o software desktop em intervalos de tempo pré-definidos.
    •	**RF05**: Em caso de significativa anomalia (pré-estabelecida em contato com o P.O.) o device deve antecipar o envio de dados do paciente para o sistema como um alerta.
### Módulo 2: Software Desktop (Terminal de Visualização e Controle)
    •	**RF06**: O software desktop deve ter uma função para que o operador armazene uma referência (nome/código) bem como envie para um dispositivo específico, associando também nele o dispositivo ao paciente.
    •	**RF07**: O software desktop deve ser capaz de receber os pacotes de dados, enviados pelo device.
    •	**RF08**: A interface do software desktop deve ser capaz de exibir os sinais vitais finais recebidos (SpO2, Frequência Cardíaca, Pressão Arterial, Temperatura) e a referência do paciente associado.
    •	**RF09**: O software desktop deve possuir ou permitir a configuração de faixas de sinais vitais que correspondem a cada cor do Protocolo de Manchester bem como estabelecer proporções de alteração que signifiquem uma anomalia em potencial.
    •	**RF10**: O software desktop deve permitir classificar o paciente de acordo com o protocolo, exibindo a cor correspondente, e gerar os alertas visuais e sonoros necessários em caso de possivel anomalia.
    •	**RF11**: O software desktop presará por não armazenará histórico de dados dos pacientes anteriores à sua entrada no hospital exceto para estabelecer as proporções que indicam anomalia ou outra necessidade indicada pelo Product Owner.

## Requisitos Não Funcionais:
### Hardware (Esfigmomanômetro)
    •	RNF01 - Os sensores do device devem possuir precisão clínica suficiente para medição de sinais vitais em ambiente hospitalar, observando para isso as precisões especificas dos sensores e seus respectivos módulos.
    •	RNF02 - Ergonomia e Conforto: Os dispositivos devem ser leves, hipoalergênicos e confortáveis para uso durante o período de monitoramento.
### Desempenho e Confiabilidade
    •	RNF03 - Periodicidade do Monitoramento: O intervalo entre as transmissões de dados do microcontrolador para o software deve ser configurável ou fixado em um valor clinicamente relevante (ex: a cada 1, 5 ou 10 minutos).
    •	RNF04 - Integridade do Pacote de Dados: O sistema de comunicação deve garantir que o pacote de dados enviado pelo device chegue ao software sem corrupção.
    •	RNF05 - Robustez da Conexão: As conexões com e sem fio (Device ↔ Desktop ↔ Servidor Local) devem ser estáveis e capazes de se restabelecer automaticamente em caso de falha.
### Software e Usabilidade
    •	RNF06 - Responsividade da Interface: A interface do software deve permanecer responsiva aos comandos do operador a todo momento.
    •	RNF07 - Clareza na Exibição: A interface deve ser capaz de apresentar os dados e a classificação de Manchester de forma clara e inequívoca indicando, ao menos, o horário da última medição recebida.
    •	RNF08 - Compatibilidade de SO: O software desktop deve ser compatível com o sistema operacional Windows 10 ou superior.

# Banco de Dados – VigiaMed

O banco de dados do **VigiaMed** foi projetado para gerir as informações relacionadas ao monitoramento de sinais vitais de pacientes em ambientes hospitalares ou clínicos. Ele segue o modelo de negócio híbrido **Cloud-assisted on-premise**, em que o nosso hardware coleta os dados, o software exibe e permite a manipulação e a persistência em servidor, possivelmente também em nuvem, auxiliando na tomada de decisão.

## Estrutura Geral
O modelo é composto por sete entidades principais:

### Unidade
Representa os hospitais, clínicas ou unidades de saúde que utilizam o sistema.
- Campos principais: nome, endereço, telefone, e-mail
- Relação: 1:N com **Dispositivo** (uma unidade pode ter vários dispositivos)

### Dispositivo
Refere-se aos equipamentos do projeto (pulseira, anel e demais dispositivos IoT).
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
