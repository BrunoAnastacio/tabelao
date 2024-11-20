    # Tabelão: criação inteligente de tabelas para campeonatos e torneios
## O que o Tabelão é hoje?
Na sua versão 1.x, o **Tabelão** é uma api que gera toda a tabela de jogos de um campeonato, dado os times informados.
É possível gerar 5 tipos diferentes de tabelas:
- **Turno único**, como, por exemplo, nos grupos da Copa do Mundo, onde 4 times jogam entre si apenas uma vez;
- **Turno e returno**, como acontece, por exemplo, na Libertadores (grupos onde os times jogam entre si em jogos de ida e volta) ou o Brasileirão, que é o mesmo sistema, mas com todos os times em um único grupo;
- **Turno único entre grupos diferentes**, como costumava ocorrer no Campeonato Carioca, por exemplo, onde os times era divididos em dois grupos e cada um jogava uma vez contra seus adversários de grupo e uma vez contra o outro grupo;
- **Turno e returno entre grupos diferentes**: imagine o exemplo acima, mas com os times jogando entre si em turno e returno
- **Turno e returno dentro do grupo + turno único entre grupos diferentes**: uma formação mista, aproveitando as possibilidades anteriores.

### Algoritmo Round-robin: a alma do tabelão
O **tabelão** não é nada mais que a implementação do [algoritmo Round-robin](https://en.wikipedia.org/wiki/Round-robin_tournament), ou **algoritmo de pontos-corridos**, que permite que, em um torneio, todos os participantes joguem entre si.

## O que o Tabelão quer ser no futuro?
O objetivo é que o **Tabelão** evolua de uma api de tabelamento de campeonatos para um estudo sobre agendamento inteligente, priorizando otimização de viagens.
Para explicar, vai um exemplo. 

Hoje, no Brasileirão, por exemplo, um time como o Fortaleza, precisa fazer quatro viagens por ano ao Rio de Janeiro, para enfrentar os quatro times da capital carioca (Flamengo, Vasco, Fluminense e Botafogo). Num cenário ideal (o que não ocorre por *n* motivos no futebol brasileiro), o Fortaleza viajaria uma vez ao Rio, para jogar contra os quatro cariocas de uma vez; ou no máximo duas vezes, fazendo dois jogos em cada viagem e poupando jogadores, dinheiro e horas de vôo.

Um exemplo é a NBA, onde um time como o New York Knicks, por exemplo, ao viajar para a Califórnia - do outro lado dos EUA - joga em sequência contra Lakers, Clippers, Warriors e Kings, transformando quatro viagens maçantes em uma só.

*Disclaimer: a distância entre os times é apenas um dos fatores de complexidade do calendario da NBA. Disponibilidade das arenas, jogos comprados pelas TVs para exibicao em rede nacional e datas comemorativas são apenas mais algumas "peças" que compõem esse quebra cabeça, como explica [esse post do blog Bola Presa] (https://bolapresa.com.br/o-jogo-do-calendario-da-nba/).

Desta forma, o **Tabelão** quer ser uma API que recebe um grupo de times e outras informações adicionais (como coordenadas, país/estado-sede ou horas de viagem até seus adversários) e devolve todo o agendamento de uma temporada, aproveitando ao máximo as viagens longas, reduzindo horas de traslado.

Creio que esse estudo pode ser útil, por exemplo, para projetos futuros que abordem problemas mais "reais", recorrentes, como roteamento de entregas, por exemplo.

## Como estamos hoje:
Na versão 1.x, recebemos um JSON conforme modelos disponíveis em ``/src/test/Request`` e devolvemos todos os cruzamentos entre adversários possíveis, dependendo do informado no campo ``tipoTabela``.

O resultado recebido é um objeto ``jogo``, que contém os campos:
- ``id``: campo que, por hora está zerado, aguardando uma futura implementação de persistência;
- ``nome``: nome do time;
- ``cidade``, ``coordenadaX``, ``coordenadaY``, ``cluster`` e ``grupo``: : campos que deverão ser utilizados futuramente, para clusterizar os times. Por enquanto, está retornando ``null``;

### Melhorias à fazer na versão 1.x:
- Avaliar a necessidade dos campos de clusterização informados acima, o que deve ser feito nas próximas fases do projeto, quando iniciar a implementação do algoritmo de clusterização;
- Corrigir contagem das rodadas. Hoje, em uma formação de turno e returno, por exemplo, quando se inicia o returno, as rodadas voltam a serem contadas à partir do "1". O objetivo é que, caso o "turno" termine na 7ª rodada, a 1ª rodada do returno seja contada como "8ª rodada", e não como 1ª, como está hoje.
- Refatorações e aplicação de design patterns pertinentes ao código.
