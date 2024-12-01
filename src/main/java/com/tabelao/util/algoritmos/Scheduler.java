package com.tabelao.util.algoritmos;

import com.tabelao.dto.DtoDadosCampeonato;
import com.tabelao.model.Rodada;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {

    //Algoritmo de agendamento que deve consultar uma Tabela e definir datas para todos os jogos
    //Implementar as seguintes opções:
    // -- Colocar todos os jogos de uma rodada em uma mesma data (ex: rodada 1: 12/06, rodada 2: 18/06, etc...
    // -- Agendar jogos de respeitando um limite diário. (ex: 2 jogos/dia - 12/06: Senegal x Italia, Brasil x Uruguai; 13/06: EUA x Ira, UK x Peru)

    public List<Rodada> dumbSchedule(List<Rodada> rodadas, DtoDadosCampeonato request){

        // cenário 1:

        int numRodadas = rodadas.size();
        //para cada rodada
        //


        return rodadas;
    }

    public List<Rodada> smartSchedule(List<Rodada> rodadas, DtoDadosCampeonato request){

        //
        return rodadas;
    }
}
