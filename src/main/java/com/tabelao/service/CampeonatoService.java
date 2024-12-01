package com.tabelao.service;

import com.tabelao.dto.DtoDadosCampeonato;
import com.tabelao.dto.DtoEquipe;
import com.tabelao.dto.DtoResponseCampeonatoCriado;
import com.tabelao.model.Campeonato;
import com.tabelao.model.Equipe;
import com.tabelao.model.Grupo;
import com.tabelao.model.Rodada;
import com.tabelao.util.algoritmos.RoundRobin;
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
        else campeonato.setRodadas(scheduler.dumbSchedule(campeonato.getRodadas(), request));

        //no futuro, persistir no banco de dados

        return new DtoResponseCampeonatoCriado(campeonato.getGrupos(), campeonato.getRodadas());
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
        int qtdeRodadasCriadas = 0;
        campeonato.addRodadas(gerarJogosDentroDoGrupo(campeonato, qtdeRodadasCriadas));
        campeonato.addRodadas(gerarJogosEntreGruposDiferentes(campeonato, qtdeRodadasCriadas));
    }

    public List<Rodada> gerarJogosDentroDoGrupo(Campeonato campeonato, int qtdeRodadasCriadas){
        List<Rodada> rodadas = new ArrayList<>();
        if (campeonato.getQtdeTurnosDentro() > 0) {
            for(int i = 0; i < campeonato.getQtdeTurnosDentro(); i++){
                for (Grupo grupo : campeonato.getGrupos()) {
                    boolean inverterMando = (i % 2) == 1;
                    if (i > 0) qtdeRodadasCriadas = campeonato.getRodadas().size();
                    rodadas.addAll(RoundRobin.gerarRodadas(grupo, inverterMando, qtdeRodadasCriadas));
                }
            }
        }
        return rodadas;
    }
    

    public List<Rodada> gerarJogosEntreGruposDiferentes(Campeonato campeonato, int qtdeRodadas){
        List<Rodada> rodadas = new ArrayList<>();
        int qtdeRodadasCriadas = 0;
        if(campeonato.getQtdeTurnosFora() > 0){
            Deque<Grupo> filaGrupos = new LinkedList<>();
            filaGrupos.addAll(campeonato.getGrupos());
            for(int i = 0; i < campeonato.getQtdeTurnosFora(); i++){
                boolean inverterMando = (i % 2) == 1;
                qtdeRodadasCriadas = campeonato.getRodadas().size();
                if(i == 0 && !campeonato.getRodadas().isEmpty())
                    qtdeRodadasCriadas = campeonato.getRodadas().size()/filaGrupos.size();
                rodadas.addAll(RoundRobin.cruzarEntreGrupos(filaGrupos, inverterMando, qtdeRodadasCriadas, false));
            }
        }
        return rodadas;
    }
}
