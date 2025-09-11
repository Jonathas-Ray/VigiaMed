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
    •	**RF01**: A pulseira deve medir diretamente os sinais de SpO2, Frequência Cardíaca e Temperatura Corporal.
    •	**RF02**: A pulseira deve receber os dados dos sensores e do Anel.
    •	**RF03**: A pulseira deve executar todos os cálculos para transformar os dados brutos em informações finais, incluindo o cálculo da Pressão Arterial (baseado no PTT), Frequência Cardíaca, SpO2 e Temperatura.
    •	**RF04**: A pulseira deve ser capaz de receber e armazenar temporariamente uma referência de paciente (ex: nome ou código) enviada pelo software desktop bem como um primeiro valor para Pressão Arterial.
    •	**RF05**: A pulseira deve enviar um pacote de dados consolidados (contendo todos os sinais vitais já calculados e a referência do paciente) para o software desktop em intervalos de tempo pré-definidos 
    ou em caso de significativa alteração.
### Módulo 2: Anel (Sensor Auxiliar)
    •	**RF06**: O anel deve medir os sinais de Fotopletismografia (PPG).
    •	**RF07**: O anel deve detectar o tempo exato da chegada da onda de pulso no dedo (marcador temporal t2).
    •	**RF08**: O anel deve transmitir os dados de PPG e seu marcador temporal (t2) diretamente para a Pulseira.
### Módulo 3: Software Desktop (Terminal de Visualização e Controle)
    •	**RF09**: O software desktop deve ter uma função para que o operador armazene uma referência (nome/código) bem como envie para um dispositivo (pulseira) específico a primeira referência de Pressão Arterial.
    •	**RF10**: O software desktop deve ser capaz de receber os pacotes de dados, enviados pela pulseira.
    •	**RF11**: A interface do software desktop deve ser capaz de exibir os sinais vitais finais recebidos (SpO2, Frequência Cardíaca, Pressão Arterial, Temperatura) e a referência do paciente associado.
    •	**RF12** O software desktop deve possuir ou permitir a configuração de faixas de sinais vitais que correspondem a cada cor do Protocolo de Manchester ou valor que indiquem de alguma forma anormalidades.
    •	**RF13**: O software desktop deve permitir classificar o paciente de acordo com o protocolo, exibindo a cor correspondente, e gerar os alertas visuais e sonoros necessários.
    •	**RF14**: O software desktop não armazenará um histórico de dados dos pacientes.


## Requisitos Não Funcionais:
### Hardware (Esfigmomanômetro)
    •	RNF01 - Capacidade de Processamento Embarcado: O Device deve usar a conectividade do microcontrolador para enviar os resultados dos cálculos e medições que fizer ao sistema desktop.
    •	RNF02 - Ergonomia e Conforto: Os dispositivos devem ser leves, hipoalergênicos e confortáveis para uso durante o período de monitoramento.
### Desempenho e Confiabilidade
    •	RNF03 - Periodicidade do Monitoramento: O intervalo entre as transmissões de dados do microcontrolador para o software deve ser configurável ou fixado em um valor clinicamente relevante (ex: a cada 1, 5 ou 10 minutos).
    •	RNF04 - Integridade do Pacote de Dados: O sistema de comunicação deve garantir que o pacote de dados enviado pelo device chegue ao software sem corrupção.
    •	RNF05 - Robustez da Conexão: As conexões com e sem fio (Device ↔ Desktop ↔ Servidor Local) devem ser estáveis e capazes de se restabelecer automaticamente em caso de falha.
### Software e Usabilidade
    •	RNF06 - Responsividade da Interface: A interface do software deve permanecer responsiva aos comandos do operador a todo momento.
    •	RNF07 - Clareza na Exibição: A interface deve ser capaz de apresentar os dados e a classificação de Manchester de forma clara e inequívoca indicando, ao menos, o horário da última medição recebida.
    •	RNF08 - Compatibilidade de SO: O software desktop deve ser compatível com o sistema operacional Windows 10 ou superior.

