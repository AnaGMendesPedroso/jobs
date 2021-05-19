package com.example.t1progmobile.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t1progmobile.AlterarVaga;
import com.example.t1progmobile.CandidatarVagaActivity;
import com.example.t1progmobile.ListagemVagasActivity;
import com.example.t1progmobile.R;
import com.example.t1progmobile.entities.Vaga;

import java.util.ArrayList;
import java.util.Random;

public class Adaptador extends BaseAdapter {
    private ArrayList<Vaga> vagasCadastradas;
    private Context context;
    private int imagensJobs[];

    public Adaptador(Context context, ArrayList<Vaga> vagas, int imagens[]){
      this.vagasCadastradas = vagas;
      this.context = context;
      this.imagensJobs = imagens;
    }

    @Override
    public int getCount() {
        return vagasCadastradas.size();
    }

    @Override
    public Object getItem(int position) {
        return vagasCadastradas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DBHelper dbHelper = new DBHelper(context);
        Random random = new Random();
        int imagemSorteadaJobs = random.nextInt(7);

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLista = layoutInflater.inflate(R.layout.item_list_view, parent, false);

        ImageView images = itemLista.findViewById(R.id.imagemItem);
        TextView tituloVaga = itemLista.findViewById(R.id.tituloVagaDivulgada);
        TextView idVaga = itemLista.findViewById(R.id.idVaga);
        TextView remuneracaVaga = itemLista.findViewById(R.id.remuneracaoDivulgada);
        TextView cargaHorariaVaga = itemLista.findViewById(R.id.cargaHorariaDivulgada);

        images.setImageResource(imagensJobs[imagemSorteadaJobs]);
        tituloVaga.setText(vagasCadastradas.get(position).getDescricao());
        idVaga.setText(String.valueOf(vagasCadastradas.get(position).getVagaId()));
        remuneracaVaga.setText(String.valueOf(vagasCadastradas.get(position).getValor()));
        cargaHorariaVaga.setText(String.valueOf(vagasCadastradas.get(position).getHorasSemana()));

        Button botaoCandidatar = (Button) itemLista.findViewById(R.id.tenhoInteresse);
        Button botaoApagarVaga = (Button) itemLista.findViewById(R.id.apagarVaga);
        Button botaoAlterarVaga = (Button) itemLista.findViewById(R.id.alterarVaga);

        botaoCandidatar.setOnClickListener(view -> {
            Intent intent = new Intent(context, CandidatarVagaActivity.class);
            intent.putExtra("idVaga", idVaga.getText().toString());
            context.startActivity(intent);
        });

        botaoAlterarVaga.setOnClickListener(view -> {
            Intent intent = new Intent(context, AlterarVaga.class);
            intent.putExtra("vagaID", String.valueOf(vagasCadastradas.get(position).getVagaId()));
            intent.putExtra("vagaDescricao", vagasCadastradas.get(position).getDescricao());
            intent.putExtra("vagaHorasSemana", String.valueOf(vagasCadastradas.get(position).getHorasSemana()));
            intent.putExtra("vagaRemuneracao", String.valueOf(vagasCadastradas.get(position).getValor()));

            context.startActivity(intent);
        });

        botaoApagarVaga.setOnClickListener(view -> {
            boolean apagou = dbHelper.apagarVaga(Integer.parseInt(idVaga.getText().toString()));
            if (apagou){
                Toast toastSucesso = Toast.makeText(context, "Vaga apagada com sucesso", Toast.LENGTH_SHORT);
                toastSucesso.show();
            }else{
                Toast toastErro = Toast.makeText(context, "Vaga possui candidatos. Não foi possível apagar.", Toast.LENGTH_SHORT);
                toastErro.show();
            }
        });

        return itemLista;

    }
}
