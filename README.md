# VigiaMed
Projeto IoT voltado para saúde.<br>
<font size ="3"> Modelo de negócio: Hibrido</font><br>
O Hardware não será pensado para mudar, mas 
o software via Cloud receberá updates tanto com melhorias
quanto com reparos de bugs.<br>
A ideia é que o Hardware será o mesmo após o fim do 
projeto mudando apenas como os sistemas lidam com os 
dados.<br>

## Requisitos Funcionais:
### Módulo 1: Pulseira (Unidade Central de Processamento)
    •	**RF01**: A pulseira deve medir diretamente os sinais de SpO2, Frequência Cardíaca e Temperatura Corporal.
    •	**RF02**: A pulseira deve receber os dados de PPG e o marcador temporal (t2) do Anel.
    •	**RF03**: A pulseira deve executar todos os cálculos para transformar os dados brutos em informações finais, incluindo o cálculo da Pressão Arterial (baseado no PTT), Frequência Cardíaca, SpO2 e Temperatura.
    •	**RF04**: A pulseira deve ser capaz de receber e armazenar temporariamente uma referência de paciente (ex: nome ou código) enviada pelo software desktop.
    •	**RF05**: A pulseira deve enviar um pacote de dados consolidados (contendo todos os sinais vitais já calculados e a referência do paciente) para o software desktop em intervalos de tempo pré-definidos.
### Módulo 2: Anel (Sensor Auxiliar)
    •	**RF06**: O anel deve medir os sinais de Fotopletismografia (PPG).
    •	**RF07**: O anel deve detectar o tempo exato da chegada da onda de pulso no dedo (marcador temporal t2).
    •	**RF08**: O anel deve transmitir os dados de PPG e seu marcador temporal (t2) diretamente para a Pulseira.
### Módulo 3: Software Desktop (Terminal de Visualização e Controle)
    •	**RF09**: O software desktop deve ter uma função para que o operador insira uma referência (nome/código) e a envie para um dispositivo (pulseira) específico.
    •	**RF10**: O software desktop deve ser capaz de receber os pacotes de dados periódicos e já processados, enviados pela pulseira.
    •	**RF11**: A interface do software desktop deve exibir os sinais vitais finais recebidos (SpO2, Frequência Cardíaca, Pressão Arterial, Temperatura) e a referência do paciente associado.
    •	**RF12** O software desktop deve permitir a configuração das faixas de sinais vitais que correspondem a cada cor do Protocolo de Manchester.
    •	**RF13**: O software desktop deve classificar o paciente de acordo com o último pacote de dados recebido, exibir a cor correspondente do Protocolo de Manchester e gerar os alertas visuais e sonoros necessários.
    •	**RF14**: O software desktop não armazenará um histórico de dados dos pacientes.
