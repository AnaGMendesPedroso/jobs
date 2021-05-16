package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t1progmobile.entities.Pessoa;
import com.example.t1progmobile.helpers.PessoaHelper;

public class CriarContaActivity extends AppCompatActivity {

    private EditText etdEmail;
    private EditText etdNome;
    private EditText etdCPF;
    private EditText etdTelefone;
    private EditText etdSenha;
    private EditText etdConfirmacaoSenha;
    private PessoaHelper pessoaHelper = new PessoaHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        etdEmail = findViewById(R.id.editEmailLogin);
        etdNome = findViewById(R.id.editTextPersonName);
        etdCPF = findViewById(R.id.editCPF);
        etdTelefone = findViewById(R.id.editTextPhone);
        etdSenha = findViewById(R.id.editTextNumberPassword);
        etdConfirmacaoSenha = findViewById(R.id.editTextNumberPasswordConfirm);
    }

    public void criarConta(View view){
        if(etdSenha.getText().equals(etdConfirmacaoSenha.getText())){

            if(pessoaHelper.buscarEmailCadastrado(etdEmail.getText().toString())){
                Toast toastErro = Toast.makeText(this,
                        "Email já cadastrado. Tente logar ao invés de se cadastrar", Toast.LENGTH_SHORT);
                toastErro.show();

            } else {
                Pessoa pessoa = new Pessoa(
                        etdNome.getText().toString(),
                        etdCPF.getText().toString(),
                        etdEmail.getText().toString(),
                        etdTelefone.getText().toString(),
                        Integer.parseInt(etdSenha.getText().toString()));
                pessoaHelper.cadastrarPessoa(pessoa);
                Toast toastSucesso = Toast.makeText(this,
                        "Usuário criado com sucesso", Toast.LENGTH_SHORT);
                toastSucesso.show();

                Intent intent = new Intent(this, ListagemVagasActivity.class);
                startActivity(intent);
            }

        } else {
            Toast toastErro = Toast.makeText(this,
                    "Senhas não conferem, tente novamente", Toast.LENGTH_SHORT);
            toastErro.show();
            limparCamposSenha();
        }
        limparCampos();
    }

    private void limparCampos() {
        etdNome.setText("");
        etdCPF.setText("");
        etdEmail.setText("");
        etdTelefone.setText("");
        etdSenha.setText("");
        etdConfirmacaoSenha.setText("");
    }


    private void limparCamposSenha() {
        etdSenha.setText("");
        etdConfirmacaoSenha.setText("");
    }

    public void cancelar(View view){
        this.finish();
    }
}