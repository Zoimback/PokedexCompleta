package com.example.pokedexcompleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Customadapter extends BaseAdapter{
    ArrayList<Pokemon> pokemons;
   Context txt;

    public Customadapter(ArrayList<Pokemon> pokemons, Context txt) {
        this.pokemons = pokemons;
        this.txt = txt;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int i) {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View viewInflado = LayoutInflater.from(txt).inflate(R.layout.item_pkm,null);//genera un view nuevo que a partir del layout itempkm generado
        TextView txtNombre =viewInflado.findViewById(R.id.nombrepkm);
        ImageView imgPKM =viewInflado.findViewById(R.id.imgpkm);

        txtNombre.setText(pokemons.get(i).getNombre());
        Picasso.Builder builder;
        builder = new Picasso.Builder(txt);
        builder.build().load(MainActivity.urlIMG.get(i)).into(imgPKM);


        return viewInflado;
    }
}
