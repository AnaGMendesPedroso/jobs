package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.t1progmobile.entities.Vaga;
import com.example.t1progmobile.helpers.DBHelper;

public class AlterarVaga extends AppCompatActivity {

    private DBHelper dbHelper = new DBHelper(this);
    private EditText editDescricao;
    private EditText editValorSalario;
    private EditText editCargaHoraria;
    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;
    private Vaga vaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_vaga);

        editDescricao = findViewById(R.id.editDescricaoAtualizarVaga);
        editValorSalario = findViewById(R.id.editSalarioAtualizarVaga);
        editCargaHoraria = findViewById(R.id.editCargaHorariaAtualizarVaga);
        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlterarVaga.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlterarVaga.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        String vagaID  = intent.getStringExtra("vagaID");
        String vagaDescricao  = intent.getStringExtra("vagaDescricao");
        String vagaHorasSemana  = intent.getStringExtra("vagaHorasSemana");
        String vagaRemuneracao  = intent.getStringExtra("vagaRemuneracao");

        editDescricao.setText(vagaDescricao);
        editCargaHoraria.setText(vagaHorasSemana);
        editValorSalario.setText(vagaRemuneracao);

        vaga = new Vaga(Integer.parseInt(vagaID), vagaDescricao, Integer.parseInt(vagaHorasSemana), Double.parseDouble(vagaRemuneracao));

    }

    public void atualizarVaga(View view){

        String vagaDescricao = editDescricao.getText().toString();
        String vagaHorasSemana = editCargaHoraria.getText().toString();
        String vagaRemuneracao = editValorSalario.getText().toString();

        vaga = new Vaga(vaga.getVagaId(), vagaDescricao, Integer.parseInt(vagaHorasSemana), Double.parseDouble(vagaRemuneracao));


        dbHelper.atualizarVaga(vaga);
        Toast toastSucesso = Toast.makeText(this, "Vaga atualizada com sucesso", Toast.LENGTH_SHORT);
        toastSucesso.show();
    }

    public void cancelar(View v){
        finish();
    }
}