package com.tabelao.util.algoritmos;

import com.tabelao.model.*;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GeradorRoundRobin {

    private static Equipe mandante;
    private static Equipe visitante;
    private static Jogo jogo = new Jogo();

    public static List<Jogo> gerarJogosEntreTimesDoMesmoGrupo(Tabela tabela, Grupo grupo,
                                                              boolean inverterMando, int qtdeRodadas){

        tratarGrupoImpar(grupo);
        tabela.setQtdeRodadas(qtdeRodadas);
        List <Jogo> jogos = new ArrayList<>(); //cria uma lista de jogos
        int numTimes = grupo.getSize();
        for (int r = 0; r < numTimes -1; r++ ) { //para cada rodada
            tabela.addUmaRodada(); //atualiza rodada
            for (int i = 0; i < numTimes / 2; i++) { //para cada jogo na rodada
                if (inverterMando) { //define visitante e mandante
                    visitante = grupo.getEquipe(i);
                    mandante = grupo.getEquipe(numTimes - 1 - i);
                } else {
                    mandante = grupo.getEquipe(i);
                    visitante = grupo.getEquipe(numTimes - 1 - i);
                }
                //ou verifica a rodada de descanso em caso de grupo com numero impar de times
                if (!mandante.getNome().equals("folga") && !visitante.getNome().equals("folga")) {
                    if (r % 2 == 0) {
                        jogo = new Jogo(mandante, visitante, tabela.getQtdeRodadas());
                    } else {
                        jogo = new Jogo(visitante, mandante, tabela.getQtdeRodadas());
                    }
                }
                jogos.add(jogo);
            }
            grupo.rotacionar();
        }
        return jogos;
    }

    public static Tabela cruzarEntreGrupos(Deque<Grupo> grupos, Tabela tabela,
                                                  boolean inverterMando,
                                                  boolean fullViagem){

        int numElementos = grupos.size();
        for (int r = 0; r < numElementos -1; r++ ){ //muda cruzamento - executa round robin entre grupos
            Deque<Grupo> gruposManipulados = new LinkedList<>();
            gruposManipulados.addAll(grupos);
            Grupo g = grupos.removeFirst();

            int numeroDeCruzamentosPorLooping = numElementos/2;
            int qtdeRodadas = tabela.getQtdeRodadas();
            for (int i = 0; i < numeroDeCruzamentosPorLooping; i++ ) { //define jogos da rodada
                Grupo gmandante = gruposManipulados.removeFirst();
                Grupo gvisitante = gruposManipulados.removeLast();

                if (inverterMando) {
                    gerarJogosEntreTimesDeGruposDiferentes(gvisitante, gmandante, tabela, qtdeRodadas, fullViagem);
                } else {
                    gerarJogosEntreTimesDeGruposDiferentes(gmandante, gvisitante, tabela, qtdeRodadas, fullViagem);
                }
            }
            grupos.addLast(grupos.removeFirst());
            grupos.addFirst(g);
        }
        return tabela;
    }

    public static void gerarJogosEntreTimesDeGruposDiferentes(Grupo grupoA,
                                                                  Grupo grupoB,
                                                                  Tabela tabela,
                                                                  int qtdeRodadas,
                                                                  boolean fullViagem){

        int tamGrupo = grupoA.getSize();
        tabela.setQtdeRodadas(qtdeRodadas);
        for (int r = 0; r < tamGrupo; r++) { //itera pelo numero de rodadas
            tabela.addUmaRodada();
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
                    jogo = new Jogo(visitante, mandante, tabela.getQtdeRodadas());
                } else {
                    mandante = grupoA.getEquipe(i);
                    visitante = grupoB.getEquipe((i + r) % tamGrupo);
                    jogo = new Jogo(visitante, mandante, tabela.getQtdeRodadas());
                }
                tabela.addJogo(jogo);
            }
        }
    }

    public static Grupo tratarGrupoImpar(Grupo grupo){
        if(grupo.getSize() % 2 != 0 ) //se qtde de times no grupo for impar...
            grupo.add(new Equipe("folga")); //adicionar um time ficticio
        return grupo;
    }

}
