package com.example.marcos.kathleenmarcosapp.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.marcos.kathleenmarcosapp.Model.Veiculo;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Marcos on 21/10/2015.
 */
public class VeiculoRequester implements Serializable{

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Veiculo> get(String url,String pmodelo,String pfabricante,String pcidade) throws IOException {

        Log.d("RESULTADO",url);
        ArrayList<Veiculo> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
      RequestBody formBody = new FormEncodingBuilder()
               .add("modelo", pmodelo)
               .add("fabricante", pfabricante)
               .add("cidade", pcidade)
               .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();
        Log.d("RESULTADO","JSON:"+jsonStr);

       // NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        try {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++) {
                item = (JSONObject) root.get(i);

                String modelo = item.getString("Modelo");
                String precoCont = item.getString("Valor km controlado");
                String fabricante = item.getString("fabricante");
                String precoLivre = item.getString("Valor km Livre");


                lista.add(new Veiculo(modelo,fabricante,precoLivre,precoCont));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
           // if (lista.size() == 0)

            //vc.setModelo(modelo);
           // vc.setFabricante(fabricante);
           /* vc.setKm_controlado("0.0");
            vc.setKm_livre("0.0");
            lista.add(vc);*/

            //Log.v("CervejaRequester", jsonStr);
        }
        Log.d("RESULTADO","lista:"+lista.toString());
        return lista;
    }
    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
