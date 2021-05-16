package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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