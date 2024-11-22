
fetch('dados.json')
    .then(response => response.json())
    .then(data => {
        dadosTorneio = data;
        // Chame as funções para criar a interface
        criarListaEquipes(dadosTorneio);
        criarTabelaJogos(dadosTorneio);
    })
    .catch(error => {
        console.error('Erro ao carregar o arquivo JSON:', error);
    });

// Função para criar a lista de equipes
function criarListaEquipes(dados) {
    const listaEquipes = document.getElementById('lista-equipes');
    dados.grupos.forEach(grupo => {
        const li = document.createElement('li');
        li.textContent = grupo.nomeGrupo;
        const ulInterno = document.createElement('ul');
        grupo.equipes.forEach(equipe => {
            const liInterno = document.createElement('li');
            liInterno.textContent = equipe.nome;
            ulInterno.appendChild(liInterno);
        });
        li.appendChild(ulInterno);
        listaEquipes.appendChild(li);
    });
}

// Função para criar a tabela de jogos
function criarTabelaJogos(dados) {
    const corpoTabela = document.getElementById('corpo-tabela');
    dados.rodadas.forEach(rodada => {
        rodada.jogos.forEach(jogo => {
            const tr = document.createElement('tr');
            const tdRodada = document.createElement('td');
            tdRodada.textContent = rodada.numeroRodada;
            const tdMandante = document.createElement('td');
            tdMandante.textContent = jogo.mandante ? jogo.mandante.nome : '';
            const tdVisitante = document.createElement('td');
            tdVisitante.textContent = jogo.visitante ? jogo.visitante.nome : '';
            tr.appendChild(tdRodada);
            tr.appendChild(tdMandante);
            tr.appendChild(tdVisitante);
            corpoTabela.appendChild(tr);
        });
    });
}

// Chamar as funções para criar a interface
criarListaEquipes(dadosTorneio);
criarTabelaJogos(dadosTorneio);