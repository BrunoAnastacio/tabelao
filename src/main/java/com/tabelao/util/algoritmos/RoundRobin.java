package com.tabelao.util.algoritmos;

import com.tabelao.model.Equipe;
import com.tabelao.model.Grupo;
import com.tabelao.model.Jogo;
import com.tabelao.model.Rodada;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class RoundRobin {

    private static Equipe mandante;
    private static Equipe visitante;
    private static Jogo jogo = new Jogo();

    public static List<Rodada> gerarRodadas(Grupo grupo, boolean inverterMando, int qtdeRodadasCriadas){
        tratarGrupoImpar(grupo);

        int numTimes = grupo.getSize();
        List<Rodada> rodadas = new ArrayList<>(); //cria lista de rodadas

        for (int r = 0; r < numTimes -1; r++ ){ //itera pelo numero de rodadas
            Rodada rodada = new Rodada( r+1+qtdeRodadasCriadas ); //cria uma rodada com sua lista de jogos

            for (int i = 0; i < numTimes/2; i++ ){ //itera pelo numero de jogos
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

    public static List <Rodada> cruzarEntreGrupos(Deque<Grupo> grupos,
                                                  boolean inverterMando,
                                                  int qtdeRodadasCriadas,
                                                  boolean fullViagem){
        List<Rodada> rodadas = new ArrayList<>();
        int numElementos = grupos.size();
        for (int r = 0; r < numElementos -1; r++ ){ //muda cruzamento
            int numeroDeCruzamentosPorLooping = numElementos/2;
            Deque<Grupo> gruposManipulados = new LinkedList<>();
            gruposManipulados.addAll(grupos);
            Grupo g = grupos.removeFirst();

            for (int i = 0; i < numeroDeCruzamentosPorLooping; i++ ) { //define jogos da rodada
                Grupo gmandante = gruposManipulados.removeFirst();
                Grupo gvisitante = gruposManipulados.removeLast();

                if (inverterMando) {
                    rodadas.addAll(RoundRobin.gerarRodadasEntreGruposDiferentes(gvisitante, gmandante, qtdeRodadasCriadas, fullViagem));
               } else {
                    rodadas.addAll(RoundRobin.gerarRodadasEntreGruposDiferentes(gmandante, gvisitante, qtdeRodadasCriadas, fullViagem));
                }
            }
            qtdeRodadasCriadas = qtdeRodadasCriadas + grupos.size() + 1;
            grupos.addLast(grupos.removeFirst());
            grupos.addFirst(g);
        }
        return rodadas;
    }

    public static List <Rodada> gerarRodadasEntreGruposDiferentes(Grupo grupoA,
                                                                  Grupo grupoB,
                                                                  int qtdeRodadasCriadas,
                                                                  boolean fullViagem){

        List<Rodada> rodadas = new ArrayList<>();
        int tamGrupo = grupoA.getSize();
        for (int r = 0; r < tamGrupo; r++) { //itera pelo numero de rodadas
            Rodada rodada = new Rodada(r + 1 + qtdeRodadasCriadas); //cria uma rodada com sua lista de jogos
            boolean inverterRodada;
            if(fullViagem){
                inverterRodada = false;
            } else{
                inverterRodada = (r % 2 == 1);
            }
            for (int i = 0; i < tamGrupo; i++) { //itera pelo numero de jogos
                if (inverterRodada) {
                    visitante = grupoA.getEquipe(i);
                    mandante = grupoB.getEquipe((i + r) % tamGrupo);
                    jogo = new Jogo(visitante, mandante, rodada.getNumeroRodada());
                } else {
                    mandante = grupoA.getEquipe(i);
                    visitante = grupoB.getEquipe((i + r) % tamGrupo);
                    jogo = new Jogo(visitante, mandante, rodada.getNumeroRodada());
                }
                rodada.add(jogo);
            }
            rodadas.add(rodada);
        }
        return rodadas;
    }

    public static Grupo tratarGrupoImpar(Grupo grupo){
        if(grupo.getSize() % 2 != 0 ) //se qtde de times no grupo for impar...
            grupo.add(new Equipe("folga")); //adicionar um time ficticio
        return grupo;
    }
}
