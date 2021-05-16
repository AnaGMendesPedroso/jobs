package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t1progmobile.entities.Vaga;
import com.example.t1progmobile.helpers.VagaHelper;

public class DivulgarVagaActivity extends AppCompatActivity {

    private VagaHelper vagaHelper = new VagaHelper(this);
    private EditText editDescricao;
    private EditText editValorSalario;
    private EditText editCargaHoraria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divulgar_vaga);
        editDescricao = findViewById(R.id.editDescricao);
        editValorSalario = findViewById(R.id.editSalario);
        editCargaHoraria = findViewById(R.id.editCargaHoraria);
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

        vagaHelper.criarEmprego(vagaEmprego);

        Toast toastSucesso = Toast.makeText(DivulgarVagaActivity.this,
                "Vaga cadastrada com sucesso!", Toast.LENGTH_SHORT);
        toastSucesso.show();
        limparCampos();
    }
}