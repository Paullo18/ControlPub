package com.example.menic.nfctest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtLogin;
    EditText edtSenha;
    String loginForm = null;
    String passwordForm = null;

    UserSessionManager userSession;

    // list of NFC technologies detected:
    private final String[][] techList = new String[][] {
            new String[] {
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    NfcAdapter mAdapter ;

    private NfcAdapter getAdapter() {
        if (mAdapter == null) {
            NfcManager manager = (NfcManager) getSystemService(NFC_SERVICE);
            mAdapter = manager.getDefaultAdapter();
        }
        return mAdapter;
    }

    ContaDAO daoC = new ContaDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        //boolean response = dao.inserirCliente(new Cliente(0, "Paulo", 0, "1", "1", "1", "1"));
        //Log.d("ControlPub", response + "");

        //ArrayList<Cliente> lista = dao.buscarTodoOsClientes();
        //Log.d("ControlPub", lista.toString());
        //ArrayList<Conta> lista = daoC.buscarContaPorID(5);


        userSession = new UserSessionManager(getApplicationContext());
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginForm = edtLogin.getText().toString();
                passwordForm = edtSenha.getText().toString();
                ClienteDAO dao = new ClienteDAO();
                Cliente client  = dao.buscarClientePorID(loginForm, passwordForm);

                //Log.d("ControlPub", client.toString());
                if(client == null){
                    Toast.makeText(getApplicationContext(), "Usuário e/ou senha incorretos!", Toast.LENGTH_LONG).show();
                }else {
                    if (loginForm.equals(client.getUSUARIO().toString()) && passwordForm.equals(client.getSENHA().toString())) {
                            userSession.createUserLoginSession("username-controlepub", loginForm);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuário e/ou senha incorretos!", Toast.LENGTH_LONG).show();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //SharedPreferences p = new SharedPreferences(login.getText(), 0);
                }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        // creating pending intent:
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent from NFC event:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null){
            ((TextView)findViewById(R.id.textView)).setText("NFC Indiponivel!");
        }else {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // disabling foreground dispatch:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            String tag = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
            ClienteDAO dao = new ClienteDAO();
            Cliente client  = dao.buscarClientePorTag(tag);
            //Cliente client  = dao.buscarClientePorTag(tag);
            if(tag.equals(client.getTAG_NFC().toString()) && client.toString() != null){
                userSession.createUserLoginSession("username-controlepub", loginForm);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                /*((TextView)findViewById(R.id.textView)).setText(
                        "NFC Tag\n" +
                                ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));*/
            }else{
                ((TextView)findViewById(R.id.textView)).setText("TAG Invalida...");
            }

        }
    }

    private String ByteArrayToHexString(byte [] inarray) {
        int i, j, in;
        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String out= "";

        for(j = 0 ; j < inarray.length ; ++j)
        {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }


}