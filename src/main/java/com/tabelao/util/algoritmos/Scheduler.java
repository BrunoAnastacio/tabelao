package com.tabelao.util.algoritmos;

import com.tabelao.dto.DtoDadosCampeonato;
import com.tabelao.model.Jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Component
public class Scheduler {

    public List<Jogo> dumbSchedule(List<Jogo> jogos, DtoDadosCampeonato request) {

        Collections.sort(jogos);
        List<Jogo> jogosAgendados = new ArrayList<>();
        LocalDate dia = request.diaDeInicio(); // Dia de início do campeonato
        int[] semanaOriginal = request.semana(); // Configuração semanal original
        int[] semana = semanaOriginal.clone(); // Clonar para controlar a capacidade atual
        DayOfWeek dayOfWeek = dia.getDayOfWeek();
        int diaDaSemana = dayOfWeek.getValue() % 7; // Índice do vetor (domingo = 0, segunda = 1, ..., sábado = 6)
        Stack<LocalDate> datasBloqueadas = Arrays.stream(request.datasBloqueadas())
                .sorted(Collections.reverseOrder()) // Inverte a ordem
                .collect(Stack::new, Stack::push, Stack::addAll);

        LocalDate proximaDataBloqueada = datasBloqueadas.isEmpty() ? null : datasBloqueadas.pop(); // Memória de datas bloqueadas

        for (Jogo jogo : jogos) {
            // Procurar o próximo dia disponível
            while (semana[diaDaSemana] == 0 || (dia.equals(proximaDataBloqueada))) {
                // Caso o dia esteja bloqueado, avançar para o próximo dia
                if (dia.equals(proximaDataBloqueada)) {
                    if (!datasBloqueadas.isEmpty()) {
                        proximaDataBloqueada = datasBloqueadas.pop();
                    } else {
                        proximaDataBloqueada = null; // Não há mais datas bloqueadas
                    }
                }

                diaDaSemana = (diaDaSemana + 1) % 7; // Avançar para o próximo dia da semana
                dia = dia.plusDays(1); // Incrementar a data

                // Se voltamos ao domingo, reinicia a capacidade semanal
                if (diaDaSemana == 0) {
                    semana = semanaOriginal.clone(); // Reinicia os limites semanais
                }
            }

            // Agendar o jogo no dia encontrado
            jogo.setData(dia);
            jogosAgendados.add(jogo);
            semana[diaDaSemana]--; // Reduzir o limite de jogos para o dia atual
        }

        return jogosAgendados;
    }


    public void somarData(LocalDate dia, LocalDate proximaDataBloqueada, int diaDaSemana, int [] semana, int [] semanaOriginal) {
//       do{
//
//       } while(dia == proximaDataBloqueada);

        diaDaSemana = (diaDaSemana + 1) % 7; // Avançar para o próximo dia da semana
        dia = dia.plusDays(1); // Incrementar a data

        // Se voltamos ao domingo, reinicia a capacidade semanal
        if (diaDaSemana == 0) {
            semana = semanaOriginal.clone(); // Reinicia os limites semanais
        }
    }


    public List<Jogo> smartSchedule(List<Jogo> jogos, DtoDadosCampeonato request){

        //
        return jogos;
    }
}
