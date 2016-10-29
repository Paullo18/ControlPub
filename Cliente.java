package com.example.menic.nfctest;

/**
 * Created by menic on 27/10/2016.
 */

public class Cliente {

    private int ID_CLIENTE;
    private String NOME_CLIENTE;
    private String CPF;
    private String EMAIL;
    private String USUARIO;
    private String SENHA;
    private String TAG_NFC;

    public Cliente(){

    }

    public Cliente(int iD_CLIENTE, String nOME_CLIENTE, String cPF, String eMAIL, String uSUARIO, String sENHA,
                   String tAG_NFC) {
        super();
        ID_CLIENTE = iD_CLIENTE;
        NOME_CLIENTE = nOME_CLIENTE;
        CPF = cPF;
        EMAIL = eMAIL;
        USUARIO = uSUARIO;
        SENHA = sENHA;
        TAG_NFC = tAG_NFC;
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int iD_CLIENTE) {
        ID_CLIENTE = iD_CLIENTE;
    }

    public String getNOME_CLIENTE() {
        return NOME_CLIENTE;
    }

    public void setNOME_CLIENTE(String nOME_CLIENTE) {
        NOME_CLIENTE = nOME_CLIENTE;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String eMAIL) {
        EMAIL = eMAIL;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String uSUARIO) {
        USUARIO = uSUARIO;
    }

    public String getSENHA() {
        return SENHA;
    }

    public void setSENHA(String sENHA) {
        SENHA = sENHA;
    }

    public String getTAG_NFC() {
        return TAG_NFC;
    }

    public void setTAG_NFC(String tAG_NFC) {
        TAG_NFC = tAG_NFC;
    }
}

