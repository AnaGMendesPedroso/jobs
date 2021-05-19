package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t1progmobile.helpers.DBHelper;

public class CandidatarVagaActivity extends AppCompatActivity {

    private TextView emailCandidato;
    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;
    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidatar_vaga);
        emailCandidato = findViewById(R.id.editEmailCandidato);
        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CandidatarVagaActivity.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CandidatarVagaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void vincularCandidatoVaga(View view){
        String email = emailCandidato.getText().toString();

        int pessoaid = dbHelper.buscarPessoaIDPorEmail(email);
        if(pessoaid == -1){
            Toast toastEmailNaoCadastrado = Toast.makeText(this, "Email não cadastrado. Crie sua conta e tente novamente.", Toast.LENGTH_LONG);
            toastEmailNaoCadastrado.show();
        }else {
            Intent intent = getIntent();
            String resposta = dbHelper.vincularCandidatoVaga(Integer.parseInt(intent.getStringExtra("idVaga")), pessoaid);
            Toast toastSucessoCadastro = Toast.makeText(this, resposta, Toast.LENGTH_SHORT);
            toastSucessoCadastro.show();
            emailCandidato.setText("");
        }
    }

    public void cancelar(View view) {
        finish();
    }
}