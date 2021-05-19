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

public class DivulgarVagaActivity extends AppCompatActivity {

    private DBHelper dbHelper = new DBHelper(this);
    private EditText editDescricao;
    private EditText editValorSalario;
    private EditText editCargaHoraria;
    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divulgar_vaga);
        editDescricao = findViewById(R.id.editDescricao);
        editValorSalario = findViewById(R.id.editSalario);
        editCargaHoraria = findViewById(R.id.editCargaHoraria);

        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DivulgarVagaActivity.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DivulgarVagaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void limparCampos() {
        editDescricao.setText("");
        editValorSalario.setText("");
        editCargaHoraria.setText("");
    }

    public void salvarVaga(View view) {
        String descricaoVaga = editDescricao.getText().toString();
        int cargaHorariaVaga = Integer.parseInt(editCargaHoraria.getText().toString());
        double salarioVaga = Double.parseDouble(editValorSalario.getText().toString().replaceAll( "," , "." ));
        Vaga vagaEmprego = new Vaga();
        vagaEmprego.setDescricao(descricaoVaga);
        vagaEmprego.setHorasSemana(cargaHorariaVaga);
        vagaEmprego.setValor(salarioVaga);

        dbHelper.criarEmprego(vagaEmprego);

        Toast toastSucesso = Toast.makeText(DivulgarVagaActivity.this,
                "Vaga cadastrada com sucesso!", Toast.LENGTH_SHORT);
        toastSucesso.show();
        limparCampos();
    }

    public void cancelar(View view) {
       finish();
    }
}