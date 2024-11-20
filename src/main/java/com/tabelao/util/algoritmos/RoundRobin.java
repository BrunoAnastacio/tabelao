package com.tabelao.util.algoritmos;

import com.tabelao.model.Equipe;
import com.tabelao.model.Grupo;
import com.tabelao.model.Jogo;
import com.tabelao.model.Rodada;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin {

    private static Equipe mandante;
    private static Equipe visitante;
    private static Jogo jogo = new Jogo();

    public static List<Rodada> gerarRodadas(Grupo grupo, boolean inverterMando){
        tratarGrupoImpar(grupo);
        int numTimes = grupo.getSize();
        List<Rodada> rodadas = new ArrayList<>(); //cria lista de rodadas
        for (int r = 0; r < numTimes -1; r++ ){
            Rodada rodada = new Rodada( r+1 ); //cria uma rodada com sua lista de jogos
            for (int i = 0; i < numTimes/2; i++ ){
                if (inverterMando) {
                    visitante = grupo.getEquipe(i);
                    mandante = grupo.getEquipe(numTimes - 1 - i);
                }
                else{
                    mandante = grupo.getEquipe(i);
                    visitante = grupo.getEquipe(numTimes - 1 - i);
                }
                //verifica a rodada de descanso em caso de grupo com numero impar de times
                if(!mandante.getNome().equals("folga")&&!visitante.getNome().equals("folga")){
                    if(r % 2 == 0){
                        jogo = new Jogo(mandante, visitante, rodada.getNumeroRodada());
                    } else{
                        jogo = new Jogo(visitante, mandante, rodada.getNumeroRodada());
                    }
                }
                rodada.add(jogo);
            }
            rodadas.add(rodada);
            grupo.rotacionar();
        }
        return rodadas;
    }

    public static Grupo tratarGrupoImpar(Grupo grupo){

        if(grupo.getSize() % 2 != 0 ) //se qtde de times no grupo for impar...
            grupo.add(new Equipe("folga")); //adicionar um time ficticio

        return grupo;
    }
}
