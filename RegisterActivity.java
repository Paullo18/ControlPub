package com.example.menic.nfctest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtCPF;
    EditText edtEmail;
    EditText edtUsuario;
    EditText edtSenha;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnCadastrar = (Button) findViewById(R.id.btnRegister);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String CPF = edtCPF.getText().toString();
                String email = edtEmail.getText().toString();
                String usuario = edtUsuario.getText().toString();
                String senha = edtSenha.getText().toString();

                Toast.makeText(getApplicationContext(), "Dados gravados Nome: " + nome + " CPF: " + CPF + " Email: " + email + " Usuario: " + usuario + " Senha: " + senha, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
