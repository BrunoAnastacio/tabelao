package com.tabelao.service;

import com.tabelao.dto.DtoDadosCampeonato;
import com.tabelao.dto.DtoEquipe;
import com.tabelao.dto.DtoResponseCampeonatoCriado;
import com.tabelao.model.*;
import com.tabelao.util.algoritmos.GeradorRoundRobin;
import com.tabelao.util.algoritmos.Scheduler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.random;

@Service
public class CampeonatoService {

    Scheduler scheduler = new Scheduler();

    public DtoResponseCampeonatoCriado gerarCampeonato(DtoDadosCampeonato request){
        List<Equipe> equipes = new ArrayList<>();

        for (DtoEquipe dtoEquipe:request.equipes()) {
            Equipe equipe = new Equipe(dtoEquipe);
            equipes.add(equipe);
        }

        Campeonato campeonato = new Campeonato(
                request.nomeCampeonato(),
                equipes,
                request.qtdeGrupos(),
                request.turnosDentroDoGrupo(),
                request.turnosEntreGrupos()
        );

        sortearGrupos(campeonato);
        gerarRodadas(campeonato);

        //agendamento de jogos - Scheduler a implemementar
        if(request.tabelaOtimizada()) campeonato.setRodadas(scheduler.smartSchedule(campeonato.getRodadas(), request));
        else campeonato.getTabela().setJogos(scheduler.dumbSchedule(campeonato.getTabela().getJogos(), request));


        //no futuro, persistir no banco de dados

        return new DtoResponseCampeonatoCriado(campeonato.getGrupos(), campeonato.getTabela().getJogos());
    }

    public void sortearGrupos(Campeonato campeonato){

        List<Grupo> gruposCriados = criarGrupos(campeonato);
        int tamanhoDeCadaGrupo = campeonato.getEquipes().size()/campeonato.getQtdeGrupos();

        if (tamanhoDeCadaGrupo >=3 ){
            for (Grupo grupoCriado:gruposCriados) {
                for(int i=0; i < tamanhoDeCadaGrupo; i++){
                    Equipe equipe = escolherEquipe(campeonato);
                    grupoCriado.addOrOverwrite(equipe);
                    campeonato.getEquipes().remove(equipe);
                }
            }
        } else{
            throw new RuntimeException("Numero de equipes precisa ser: 1) Um multiplo do numero de grupos; " +
                    "2) Suficiente para preencher cada grupo com 3 equipes ou mais");
        }
        campeonato.setGrupos(gruposCriados);
    }

    public List<Grupo> criarGrupos(Campeonato campeonato){
        List<Grupo> gruposCriados = new ArrayList<>();
        for (int i = 0; i < campeonato.getQtdeGrupos(); i++){
            Grupo grupo = new Grupo("Grupo " + (i+1));
            gruposCriados.add(grupo);
        }
        return gruposCriados;
    }

    public Equipe escolherEquipe(Campeonato campeonato){
        return campeonato.getEquipes().get((int) (random() * campeonato.getEquipes().size()));
    }

    public void gerarRodadas(Campeonato campeonato){
        gerarJogosDentroDoGrupo(campeonato);
        gerarJogosEntreGruposDiferentes(campeonato);
    }

    public void gerarJogosDentroDoGrupo(Campeonato campeonato){
        List<Jogo> jogosTurno = new ArrayList<>();
        if (campeonato.getQtdeTurnosDentro() > 0) {
            for(int i = 0; i < campeonato.getQtdeTurnosDentro(); i++) { //percorre cada turno
                int qtdeRodadas = campeonato.getTabela().getQtdeRodadas();
                List<Jogo> jogosGrupo = new ArrayList<>();
                for (Grupo grupo : campeonato.getGrupos()) { //percorre cada grupo
                    boolean inverterMando = (i % 2) == 1;
                    List<Jogo> jogos = GeradorRoundRobin.gerarJogosEntreTimesDoMesmoGrupo(campeonato.getTabela(), grupo, inverterMando, qtdeRodadas);
                    jogosGrupo.addAll(jogos);
                }
                jogosTurno.addAll(jogosGrupo);
            }
        }
        campeonato.getTabela().setJogos(jogosTurno);
    }

    public void gerarJogosEntreGruposDiferentes(Campeonato campeonato){
        List<Jogo> jogos = new ArrayList<>();
        if(campeonato.getQtdeTurnosFora() > 0){
            Deque<Grupo> filaGrupos = new LinkedList<>();
            filaGrupos.addAll(campeonato.getGrupos()); //fila para o round robin
            for(int i = 0; i < campeonato.getQtdeTurnosFora(); i++){ //gera rodadas para cada turno
                boolean inverterMando = (i % 2) == 1;
                campeonato.setTabela(GeradorRoundRobin.cruzarEntreGrupos(filaGrupos, campeonato.getTabela(), inverterMando, false));
            }
        }
    }
}
