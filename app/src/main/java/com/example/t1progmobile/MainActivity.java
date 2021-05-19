package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView accountToolBar;
    private ImageView criarVagaToolbar;
    private TextView appNameToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountToolBar = findViewById(R.id.iconeContaToolbar);
        criarVagaToolbar = findViewById(R.id.criarVagaToobar);

        criarVagaToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DivulgarVagaActivity.class);
                startActivity(intent);
            }
        });

        accountToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    public void irParaListagem(View view) {
        Intent intent = new Intent(this, ListagemVagasActivity.class);
        startActivity(intent);
    }

    public void irParaCadastroVaga(View view) {
        Intent intent = new Intent(this, DivulgarVagaActivity.class);
        startActivity(intent);
    }

    public void irParaLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void irParaCriarConta(View view){
        Intent intent = new Intent(this, CriarContaActivity.class);
        startActivity(intent);
    }
}