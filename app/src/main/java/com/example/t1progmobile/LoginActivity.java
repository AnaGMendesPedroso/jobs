package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.t1progmobile.entities.Pessoa;
import com.example.t1progmobile.helpers.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmailLogin;
    private EditText editTextSenhaLogin;
    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;

    private DBHelper DBHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmailLogin = findViewById(R.id.editEmailLogin);
        editTextSenhaLogin = findViewById(R.id.editSenhaLogin);

        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void logar(View view){
        Pessoa pessoa = DBHelper.buscarPessoaCadastrada(editTextEmailLogin.getText().toString(), Integer.parseInt(editTextSenhaLogin.getText().toString()));

        if(pessoa.getNome() == null){
            Toast toastErro = Toast.makeText(this,
                    "Usuário não encontrado, tente novamente", Toast.LENGTH_SHORT);
            toastErro.show();
        } else {
            Intent intent = new Intent(this, PerfilActivity.class);
            intent.putExtra("pessoaNome", pessoa.getNome());
            intent.putExtra("pessoaCPF", pessoa.getCpf());
            intent.putExtra("pessoaEmail", pessoa.getEmail());
            intent.putExtra("pessoaTelefone", pessoa.getTelefone());
            intent.putExtra("pessoaId", String.valueOf(pessoa.getPessoaId()));
            startActivity(intent);
        }

    }

    public void irParaCriarConta(View view){
        Intent intent = new Intent(this, CriarContaActivity.class);
        startActivity(intent);
    }

    public void cancelar(View view) {
        finish();
    }
}