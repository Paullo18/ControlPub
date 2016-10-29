package com.example.menic.nfctest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by menic on 27/10/2016.
 */

public class ClienteDAO {

    private static final String URL = "http://192.168.1.105:8070/ControlPub/services/ClienteDAO?wsdl";
    private static final String NAMESPACE= "http://controlpub.webservice.com.br";

    private static final String INSERIR = "inserirCliente";
    private static final String EXCLUIR = "excluirCliente";
    private static final String ATUALIZAR = "atualizarCliente";
    private static final String BUSCAR_TODOS = "buscarTodoOsClientes";
    private static final String BUSCAR_POR_ID = "buscarClientePorID";
    private static final String BUSCAR_POR_TAG = "buscarClientePorTag";

    public boolean inserirCliente(Cliente cliente){
        SoapObject inserirCliente = new SoapObject(NAMESPACE, INSERIR);

        SoapObject client = new SoapObject(NAMESPACE, "cliente");

        client.addProperty("ID_CLIENTE", cliente.getID_CLIENTE());
        client.addProperty("NOME_CLIENTE", cliente.getNOME_CLIENTE());
        client.addProperty("CPF", cliente.getCPF());
        client.addProperty("EMAIL", cliente.getEMAIL());
        client.addProperty("USUARIO", cliente.getUSUARIO());
        client.addProperty("SENHA", cliente.getSENHA());
        client.addProperty("TAG_NFC", cliente.getTAG_NFC());


        inserirCliente.addSoapObject(client);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(inserirCliente);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + INSERIR, envelope);

            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            return Boolean.parseBoolean(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean excluirCliente(Cliente cliente){

        return true;
    }

    public ArrayList<Cliente> buscarTodoOsClientes(){
        ArrayList<Cliente> lista = new ArrayList<Cliente>();


        SoapObject buscarClientes = new SoapObject(NAMESPACE, BUSCAR_TODOS);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarClientes);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_TODOS, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();


            for (SoapObject soapObject: resposta) {
                Cliente client = new Cliente();
                client.setID_CLIENTE(Integer.parseInt(soapObject.getProperty("ID_CLIENTE").toString()));
                client.setNOME_CLIENTE(soapObject.getProperty("NOME_CLIENTE").toString());
                client.setCPF(soapObject.getProperty("CPF").toString());
                client.setEMAIL(soapObject.getProperty("EMAIL").toString());
                client.setUSUARIO(soapObject.getProperty("USUARIO").toString());
                client.setSENHA(soapObject.getProperty("SENHA").toString());
                client.setTAG_NFC(soapObject.getProperty("TAG_NFC").toString());

                lista.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public Cliente buscarClientePorID(String USUARIO, String SENHA){
        Cliente client = null;


        SoapObject buscarCliente = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
        buscarCliente.addProperty("USUARIO", USUARIO);
        buscarCliente.addProperty("SENHA", SENHA);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarCliente);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_POR_ID, envelope);

            SoapObject resposta = (SoapObject) envelope.getResponse();

            client = new Cliente();
            client.setID_CLIENTE(Integer.parseInt(resposta.getProperty("ID_CLIENTE").toString()));
            client.setNOME_CLIENTE(resposta.getProperty("NOME_CLIENTE").toString());
            client.setCPF(resposta.getProperty("CPF").toString());
            client.setEMAIL(resposta.getProperty("EMAIL").toString());
            client.setUSUARIO(resposta.getProperty("USUARIO").toString());
            client.setSENHA(resposta.getProperty("SENHA").toString());
            client.setTAG_NFC(resposta.getProperty("TAG_NFC").toString());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return client;
    }

    public Cliente buscarClientePorTag(String TAG_NFC){
        Cliente client = null;

        SoapObject buscarClientePorTag = new SoapObject(NAMESPACE, BUSCAR_POR_TAG);
        buscarClientePorTag.addProperty("TAG_NFC", TAG_NFC);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarClientePorTag);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:" + BUSCAR_POR_TAG, envelope);

            SoapObject resposta = (SoapObject) envelope.getResponse();

                client = new Cliente();
                client.setID_CLIENTE(Integer.parseInt(resposta.getProperty("ID_CLIENTE").toString()));
                client.setNOME_CLIENTE(resposta.getProperty("NOME_CLIENTE").toString());
                client.setCPF(resposta.getProperty("CPF").toString());
                client.setEMAIL(resposta.getProperty("EMAIL").toString());
                client.setUSUARIO(resposta.getProperty("USUARIO").toString());
                client.setSENHA(resposta.getProperty("SENHA").toString());
                client.setTAG_NFC(resposta.getProperty("TAG_NFC").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return client;
    }

    public boolean atualizarCliente(Cliente cliente){
        return true;
    }
}
