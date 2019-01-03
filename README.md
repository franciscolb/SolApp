SolApp

Nesta aplicação é possível obter informações sobre a metereologia dos locais, fornecidos pela API do IPMA. Quando a aplicação é iniciada, por predefinição, vai buscar as informações metereológicas de Aveiro. Depois de esta ser iniciada é possível escolher o sítio que deseja ver as informações meteorológicas e qual o dia, estando disponíveis cinco dias a contar a partir do dia atual. É mostrada informação sobre a direção e intensidade do vento, a probabilidade de precipitação e a descrição geral do tempo (céu limpo, chuva, nublado, etc). Também é mostrada a data da previsão mostrada e a temperatura mínima e máxima.

Em termos de implementação é utilizado o Retrofit para as chamadas à api, o Room para controlo sobre os dados, sobre SQLite. Primeiro foi utilizado totalmente o exemplo do tutorial mas depois foi adaptado para retirar a parte que tinha dependency injection. Para tratamento dos dados JSON foi utilizado o GSON. Em termos de base de dados foram utilizadas quatro entidades. Uma para Weather que contém informação meteorológica de determinado local, até cinco dias. WeatherType que contém informação sobre o estado do tempo relacionado com o id (1-> céu limpo) em português e inglês. WindSpeed que também conforme id tem informação sobre a velocidade do vento (moderado, calmo) também em português e inglês. E por fim as localidades que possui os globalid's e o nome das localidades. O repositório é responsável por efetuar as chamadas à api.

Limitações:
- existem certas zonas que o link de acesso à api resulta num erro (404 NOT FOUND). Neste caso são apresentadas as informações anteriores, ou seja, quando é o botão mostrar é clicado não acontece nada.

Cuidados a correr o projeto:
- nenhum detetado
