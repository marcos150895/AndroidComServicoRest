package com.example.marcos.kathleenmarcosapp.Controle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.marcos.kathleenmarcosapp.Adapter.VeiculoAdapter;
import com.example.marcos.kathleenmarcosapp.Model.Veiculo;
import com.example.marcos.kathleenmarcosapp.R;

import java.util.ArrayList;

public class listarVeiculosActivity extends ActionBarActivity{
    ListView listView;
    Activity atividade;
    public final static String veiculo = "veiculos";
    Veiculo[] veiculos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_veiculos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        atividade = this;

        //pega a mensagem do intent
        Intent intent = getIntent();

        veiculos = ((ArrayList<Veiculo>)intent.getSerializableExtra(MainActivity.CERVEJAS)).toArray(new Veiculo[0]);
        //Log.d("log cervejas", veiculos.toString());
        //cria o listview de cervejas
        listView = (ListView) findViewById(R.id.view_lista_veiculos);

        VeiculoAdapter adapter = new VeiculoAdapter(this, veiculos);
        listView.setAdapter(adapter);

        // listener de click em um item do listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
               // Intent intent = new Intent(atividade, DetalheCervejaActivity.class);
               // intent.putExtra(CERVEJA, cervejas[position]);

               // startActivity(intent);

            }

        });
    }

}
