package com.example.menic.nfctest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by menic on 28/10/2016.
 */

public class ContaDAO {

    private static final String URL = "http://192.168.1.105:8070/ControlPub/services/ContaDAO?wsdl";
    private static final String NAMESPACE= "http://controlpub.webservice.com.br";

    private static final String BUSCAR_CONTA_POR_ID = "buscarContaPorID";
    private static final String BUSCAR_VALOR_CONTA = "buscarValorConta";

    public ArrayList<Conta> buscarContaPorID(int ID_CLIENTE){
        ArrayList<Conta> lista = new ArrayList<Conta>();


        SoapObject buscarContaPorID = new SoapObject(NAMESPACE, BUSCAR_CONTA_POR_ID);

        buscarContaPorID.addProperty("ID_CLIENTE", ID_CLIENTE);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarContaPorID);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_CONTA_POR_ID, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();


            for (SoapObject soapObject: resposta) {
                Conta conta = new Conta();
                conta.setDESCRICAO_PRODUTO(soapObject.getProperty("DESCRICAO_PRODUTO").toString());
                conta.setVALOR(("R$"+soapObject.getProperty("VALOR").toString().replace(".", ",")));

                lista.add(conta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public ContaValor buscarValorConta(int ID_CLIENTE){
        ContaValor contaValor = null;


        SoapObject buscarValorConta = new SoapObject(NAMESPACE, BUSCAR_VALOR_CONTA);
        buscarValorConta.addProperty("ID_CLIENTE", ID_CLIENTE);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarValorConta);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_VALOR_CONTA, envelope);

            SoapObject resposta = (SoapObject) envelope.getResponse();

            contaValor = new ContaValor();
            contaValor.setVALOR_CONTA("R$"+resposta.getProperty("VALOR_CONTA").toString().replace(".", ","));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return contaValor;
    }
}
