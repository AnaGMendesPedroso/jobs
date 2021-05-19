package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.t1progmobile.entities.Vaga;
import com.example.t1progmobile.helpers.Adaptador;
import com.example.t1progmobile.helpers.DBHelper;
import java.util.ArrayList;

public class ListagemVagasActivity extends AppCompatActivity{

    private ListView listView;
    private DBHelper dbHelper = new DBHelper(this);
    private ArrayList<Vaga> vagasCadastradas;
    private TextView textViewVagasVazia;
    private TextView tituloVaga;
    private TextView cargaHorariaVaga;
    private TextView remuneracaoVaga;
    private TextView idVaga;

    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;

    private int imagens[] = {R.drawable.jobs01, R.drawable.jobs02, R.drawable.jobs03, R.drawable.jobs04, R.drawable.jobs05, R.drawable.jobs06, R.drawable.jobs07};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_vagas);
        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListagemVagasActivity.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListagemVagasActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        textViewVagasVazia = findViewById(R.id.textViewVagasVazia);

        vagasCadastradas = dbHelper.consultarTodasVagas();

        if( vagasCadastradas.size() == 0 ){
            textViewVagasVazia.setVisibility(View.VISIBLE);
        }else{
            textViewVagasVazia.setVisibility(View.INVISIBLE);
            listView = findViewById(R.id.listViewVagas);
            listView.setAdapter(new Adaptador(this, vagasCadastradas, imagens));
        }
    }

    public void atualizarVagas(View view) {
        recreate();
    }
}