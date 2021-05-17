package com.example.t1progmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.example.t1progmobile.entities.Vaga;
import com.example.t1progmobile.helpers.Adaptador;
import com.example.t1progmobile.helpers.DBHelper;

import java.util.ArrayList;

public class ListagemVagasActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private ListView listView;
    private DBHelper DBHelper = new DBHelper(this);
    private ArrayList<Vaga> vagasCadastradas;
    private TextView textViewVagasVazia;
    private int imagens[] = {R.drawable.jobs01, R.drawable.jobs02, R.drawable.jobs03, R.drawable.jobs04, R.drawable.jobs05, R.drawable.jobs06, R.drawable.jobs07};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_vagas);
        textViewVagasVazia = findViewById(R.id.textViewVagasVazia);
        vagasCadastradas = DBHelper.consultarTodasVagas();

        if( vagasCadastradas.size() == 0){
            textViewVagasVazia.setVisibility(View.VISIBLE);
        }else{
            textViewVagasVazia.setVisibility(View.INVISIBLE);
            listView = findViewById(R.id.listViewVagas);
            listView.setAdapter(new Adaptador(this, vagasCadastradas, imagens));
        }
    }

    public void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_vaga);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.candidatar_me:

        }
    }
}