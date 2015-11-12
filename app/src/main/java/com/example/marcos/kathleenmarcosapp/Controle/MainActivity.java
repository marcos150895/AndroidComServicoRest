package com.example.marcos.kathleenmarcosapp.Controle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcos.kathleenmarcosapp.Model.Veiculo;
import com.example.marcos.kathleenmarcosapp.Network.VeiculoRequester;
import com.example.marcos.kathleenmarcosapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class MainActivity extends ActionBarActivity{

    Spinner spinnerFabricante;
    Spinner spinnerModelo;
    Spinner spinnerCidade;
    Button btnConsultar;
    String modelo,fabricante,cidade;
    ArrayList<Veiculo>  veiculos;
    final String servidor = "localhost:8080/ProjetoKathleenMarcos5/json";
    VeiculoRequester requester;
    ProgressBar mProgress;
    Intent intent;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.contexto = this;
        setupViews();

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        spinnerFabricante.setSelection(0);
        spinnerModelo.setSelection(0);
        spinnerCidade.setSelection(0);
    }

    private void setupViews() {
        modelo = "";
        cidade = "";
        fabricante = "";
        btnConsultar = (Button) findViewById(R.id.button);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        spinnerFabricante = (Spinner) findViewById(R.id.spinnerFabricante);
        spinnerFabricante.setOnItemSelectedListener(new FabricanteSelecionado());

        spinnerModelo = (Spinner) findViewById(R.id.spinnerModelo);
        spinnerModelo.setOnItemSelectedListener(new ModeloSelecionado());

        spinnerCidade = (Spinner) findViewById(R.id.spinnerCidade);
        spinnerCidade.setOnItemSelectedListener(new CidadeSelecionada());
        mProgress.setVisibility(View.INVISIBLE);

    }



    private class FabricanteSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            fabricante = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class CidadeSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            cidade = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class ModeloSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            modelo = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // constante static para identificar o parametro
    public final static String CERVEJAS = "veiculos";
    //será chamado quando o usuário clicar em enviar
    public void consultarVeiculos(View view) {
        final String pmodelo = this.modelo.equals("Modelo")?"":modelo;
        final String pfabricante = this.fabricante.equals("Fabricante")?"":fabricante;
        final String pcidade = this.cidade.equals("Cidade")?"":cidade;

        requester = new VeiculoRequester();
        if(requester.isConnected(this)) {
            intent = new Intent(this, listarVeiculosActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        veiculos = requester.get("http://10.0.2.2:8080/ProjetoKathleenMarcos/VeiculoJSON",pmodelo,pfabricante,pcidade);
                        System.out.println(veiculos);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(CERVEJAS, veiculos);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }





}



