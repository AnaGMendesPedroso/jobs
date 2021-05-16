package com.example.t1progmobile.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        Random random = new Random();
        int imagemSorteadaJobs = random.nextInt(7);

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLista = layoutInflater.inflate(R.layout.item_list_view, parent, false);

        ImageView images = itemLista.findViewById(R.id.imagemItem);
        TextView tituloVaga = itemLista.findViewById(R.id.tituloVagaDivulgada);
        TextView remuneracaVaga = itemLista.findViewById(R.id.remuneracaoDivulgada);
        TextView cargaHorariaVaga = itemLista.findViewById(R.id.cargaHorariaDivulgada);

        images.setImageResource(imagensJobs[imagemSorteadaJobs]);
        tituloVaga.setText(vagasCadastradas.get(position).getDescricao());
        remuneracaVaga.setText(String.valueOf(vagasCadastradas.get(position).getValor()));
        cargaHorariaVaga.setText(String.valueOf(vagasCadastradas.get(position).getHorasSemana()));

        return itemLista;

    }
}
