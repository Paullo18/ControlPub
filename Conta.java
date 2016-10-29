package com.example.menic.nfctest;

/**
 * Created by menic on 28/10/2016.
 */

public class Conta {
    private int ID_CLIENTE;
    private int ID_PRODUTO;
    private String DESCRICAO_PRODUTO;
    private String VALOR;


    public Conta(){}


    public Conta(int ID_CLIENTE, int ID_PRODUTO, String DESCRICAO_PRODUTO, String VALOR) {
        this.ID_CLIENTE = ID_CLIENTE;
        this.ID_PRODUTO = ID_PRODUTO;
        this.DESCRICAO_PRODUTO = DESCRICAO_PRODUTO;
        this.VALOR = VALOR;
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public int getID_PRODUTO() {
        return ID_PRODUTO;
    }

    public void setID_PRODUTO(int ID_PRODUTO) {
        this.ID_PRODUTO = ID_PRODUTO;
    }

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

    public String getDESCRICAO_PRODUTO() {
        return DESCRICAO_PRODUTO;
    }

    public void setDESCRICAO_PRODUTO(String DESCRICAO_PRODUTO) {
        this.DESCRICAO_PRODUTO = DESCRICAO_PRODUTO;
    }

    @Override
    public String toString() {
        return DESCRICAO_PRODUTO + "    " + VALOR;
    }
}
