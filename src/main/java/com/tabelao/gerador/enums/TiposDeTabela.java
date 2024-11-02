package com.tabelao.gerador.enums;

public enum TiposDeTabela {
    TURNO_UNICO_DENTRO_DO_GRUPO(1),
    TURNO_RETURNO_DENTRO_DO_GRUPO(2),
    TURNO_UNICO_ENTRE_GRUPOS(3),
    TURNO_RETURNO_ENTRE_GRUPOS(4),
    TURNO_RETURNO_DENTRO_DO_GRUPO_E_TURNO_UNICO_ENTRE_GRUPOS(5);

    private final int numero;

    TiposDeTabela(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
