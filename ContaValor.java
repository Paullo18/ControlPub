package com.example.menic.nfctest;

/**
 * Created by menic on 28/10/2016.
 */

public class ContaValor {
    private String VALOR_CONTA;

    public ContaValor(){

    }

    public ContaValor(String VALOR_CONTA) {
        this.VALOR_CONTA = VALOR_CONTA;
    }

    public String getVALOR_CONTA() {
        return VALOR_CONTA;
    }

    public void setVALOR_CONTA(String VALOR_CONTA) {
        this.VALOR_CONTA = VALOR_CONTA;
    }

    @Override
    public String toString() {
        return VALOR_CONTA;
    }
}
