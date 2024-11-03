package com.tabelao.gerador.algoritmos;

public class SmartScheduler {

//Implementar agendamento de jogos "inteligente":
    //Se um time como o Toronto Raptors vai ao Texas enfrentar o San Antonio Spurs,
    //este algoritmo de agendamento deve identificar espaço na agenda de outros
    //times da região (ex. Dallas, Houston, Oklahoma, New Orleans) e tentar enfileirar
    //jogos contra esses times, de forma que o Raptors faça o máximo de jogos permitido
    //contra times de uma mesma região

    //Possíveis caminhos:
        // - Clusterizar times de uma tabela usando um ou mais desses critérios:
            // - Coordenadas geográficas
            // - Distância entre os times, considerando horas de viagem (avião + ônibus)
            // - Clusterização manual (ex: a definição de divisões regionais da NBA)

        //Exemplo de clusteres:
            // - Cluster 1: Celtics, Knicks, Nets, Sixers, Wizards
            // - Cluster 2: Raptors, Pistons, Cavs, Pacers, Bulls, Bucks, Wolves
            // - Cluster 3: Hornets, Hawks, Magic, Heat
            // - Cluster 4: Grizzles, Pelicans, Rockets, Spurs, Mavs, Thunder
            // - Cluster 5: Jazz, Nuggets
            // - Cluster 6: Suns, Clippers, Lakers, Warriors, Kings, Blazers

        // - Com os clusteres definidos, deve-se marcar os confrontos entre os clusteres. Ex:
            // - Cluster 2 visita Cluster 6:
                // Isso significa que todos os times do cluster 6 vão receber times do cluster 2 em jogos.
                // Exexmplo de agendamentos:
                    // 13/11: Raptors @ Suns, Pistons @ Clippers, Cavs @ Lakers
                    // 14/11: Pacers @ Warriors, Bulls @ Kings, Bucks @ Blazers
                    // 15/11: Wolves @ Suns, Raptors @ Clippers, Pistons @ lakers, etc...

        // Possíveis problemas:
            // Diferença de tamanho entre clusteres: se for muito grande (ex cluster 5 x cluster 2), pode atrapanhar
            // Nem sempre é viável submeter um time a 7 jogos fora de casa de uma vez
    
}
