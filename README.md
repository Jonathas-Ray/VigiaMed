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
=======
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
