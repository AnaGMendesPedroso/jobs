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

public class PerfilActivity extends AppCompatActivity {

    private EditText etdEmail;
    private EditText etdNome;
    private EditText etdCPF;
    private EditText etdTelefone;
    private EditText etdSenha;
    private EditText etdConfirmacaoSenha;
    private String usuarioId;
    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;

    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        etdEmail = findViewById(R.id.editEmailConta);
        etdEmail.setClickable(false);
        etdNome = findViewById(R.id.editNomeConta);
        etdCPF = findViewById(R.id.editCPFConta);
        etdTelefone = findViewById(R.id.editTextPhoneConta);
        etdSenha = findViewById(R.id.editTextNumberPasswordConta);
        etdConfirmacaoSenha = findViewById(R.id.editTextNumberPasswordConfirmConta);

        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        String pessoaNome = intent.getStringExtra("pessoaNome");
        String pessoaCPF = intent.getStringExtra("pessoaCPF");
        String pessoaEmail = intent.getStringExtra("pessoaEmail");
        String pessoaTelefone = intent.getStringExtra("pessoaTelefone");
        usuarioId = intent.getStringExtra("pessoaId");

        etdNome.setText(pessoaNome);
        etdCPF.setText(pessoaCPF);
        etdEmail.setText(pessoaEmail);
        etdTelefone.setText(pessoaTelefone);

    }

    public void atualizarConta(View view){
        if(!etdSenha.getText().toString().equals(etdConfirmacaoSenha.getText().toString())){
            Toast toastErroConfirmacao = Toast.makeText (this, "Senhas diferem entre si. Tente novamente.", Toast.LENGTH_SHORT);
            toastErroConfirmacao.show();
            etdSenha.setText("");
            etdConfirmacaoSenha.setText("");

        }else{
            String nome = etdNome.getText().toString();
            String cpf = etdCPF.getText().toString();
            String email = etdEmail.getText().toString();
            String telefone = etdTelefone.getText().toString();
            String senha = etdSenha.getText().toString();

            Pessoa pessoa = new Pessoa(Integer.parseInt(usuarioId), nome, cpf, email, telefone, Integer.parseInt(senha));
            dbHelper.atualizarPerfil(pessoa);
            Toast toastSucessoCadastro = Toast.makeText(this, "Usuário atualizado com sucesso", Toast.LENGTH_SHORT);
            toastSucessoCadastro.show();
            limparCampos();
        }
    }


    private void limparCampos() {
        etdNome.setText("");
        etdCPF.setText("");
        etdEmail.setText("");
        etdTelefone.setText("");
        etdSenha.setText("");
        etdConfirmacaoSenha.setText("");
    }

    public void cancelar(View view){
        finish();
    }
}