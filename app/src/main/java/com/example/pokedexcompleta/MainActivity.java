package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Jsoup ---> JSON
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ListView listview;
    ArrayList<String> nombres = new ArrayList<>();
    static ArrayList<String> urlIMG = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listadopokemon);
        //Customadapter adapter = new Customadapter(pokemons,this);

        //--------------------Pre-ejecucion--------------
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("QWRNAERBH>SBGbgBRH");
                    Document resCompleto = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
                   nombres = (ArrayList<String>) resCompleto.select(" a[href^=/es/pokedex/]").eachText();

                    for (int i = 0; i < nombres.size();i++){

                        String numPkm = String.format("%03d",i+1);
                        urlIMG.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+numPkm+".png");//Conformar lista de urls
                        pokemons.add(new Pokemon(nombres.get(i)));//Conformar la lista de nombres.




                        System.out.println(urlIMG.get(i));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Customadapter adapter = new Customadapter(pokemons,MainActivity.this);
                        listview.setAdapter(adapter);
                    }
                });
            }
        }).start() ;

    }
}