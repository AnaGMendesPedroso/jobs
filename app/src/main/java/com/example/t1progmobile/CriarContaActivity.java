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

public class CriarContaActivity extends AppCompatActivity {

    private EditText etdEmail;
    private EditText etdNome;
    private EditText etdCPF;
    private EditText etdTelefone;
    private EditText etdSenha;
    private EditText etdConfirmacaoSenha;
    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;
    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        etdEmail = findViewById(R.id.editTextEmailAddress);
        etdNome = findViewById(R.id.editTextPersonName);
        etdCPF = findViewById(R.id.editCPF);
        etdTelefone = findViewById(R.id.editTextPhone);
        etdSenha = findViewById(R.id.editTextNumberPassword);
        etdConfirmacaoSenha = findViewById(R.id.editTextNumberPasswordConfirm);

        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarContaActivity.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarContaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void criarConta(View view){

        String nome = etdNome.getText().toString();
        String cpf = etdCPF.getText().toString();
        String email = etdEmail.getText().toString();
        String telefone = etdTelefone.getText().toString();
        String senha = etdSenha.getText().toString();
        String confSenha = etdConfirmacaoSenha.getText().toString();

        if(!senha.equals(confSenha)){
            Toast toastErroConfirmacao = Toast.makeText (CriarContaActivity.this, "Senhas diferem entre si. Tente novamente.", Toast.LENGTH_SHORT);
            toastErroConfirmacao.show();
            etdSenha.setText("");
            etdConfirmacaoSenha.setText("");
        }else{
            boolean verficaSeEmailJaEstaCadastrado = dbHelper.buscarEmailCadastrado(email);

            if(verficaSeEmailJaEstaCadastrado){
                Toast toastErroEmail = Toast.makeText (CriarContaActivity.this, "Email já cadastrado. Tente novamente.", Toast.LENGTH_SHORT);
                toastErroEmail.show();
            }else {
                Pessoa pessoa = new Pessoa(nome, cpf, email, telefone, Integer.parseInt(senha));
                dbHelper.cadastrarPessoa(pessoa);
                Toast toastSucessoCadastro = Toast.makeText(CriarContaActivity.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT);
                toastSucessoCadastro.show();
                limparCampos();
            }
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