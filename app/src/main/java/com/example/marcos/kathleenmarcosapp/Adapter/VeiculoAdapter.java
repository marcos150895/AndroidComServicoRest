package com.example.marcos.kathleenmarcosapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.example.marcos.kathleenmarcosapp.Util.*;
import com.example.marcos.kathleenmarcosapp.Model.Veiculo;
import com.example.marcos.kathleenmarcosapp.R;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by Marcos on 21/10/2015.
 */
public class VeiculoAdapter extends BaseAdapter implements SectionIndexer {


    Activity context;
    public Veiculo[] veiculos;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public VeiculoAdapter(Activity context, Veiculo[] veiculos){
        this.context = context;
        this.veiculos = veiculos;
        System.out.println(veiculos.length);
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(veiculos);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(veiculos);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(veiculos);

    }


    @Override
    public int getCount() {
        return veiculos.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < veiculos.length)
            return veiculos[position];
        else
            return null;
    }

    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_carro, parent, false);

            TextView modelo = (TextView)view.findViewById(R.id.modeloVeiculo);
            TextView fabricante = (TextView)view.findViewById(R.id.fabricanteVec);
            TextView kmLivre = (TextView)view.findViewById(R.id.kmLivre);
            TextView kmControlado = (TextView)view.findViewById(R.id.kmControlado);
            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(modelo,fabricante,kmLivre,kmControlado));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
//        Drawable drawable = Util.getDrawable(context, veiculos[position].getEstado());
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        holder.getModelo().setText(veiculos[position].getModelo());
        holder.getFabricante().setText("Fabricante : " + ( veiculos[position].getFabricante()));
        holder.getKmControlado().setText("KM Controlado: R$"+( veiculos[position].getKm_controlado()));
        holder.getKmLivre().setText("KM Livre : R$" +( veiculos[position].getKm_livre()));
       // holder.getFabricante().setText(veiculos[position].getKm_controlado());



        return view;
    }

//metodos da interface SectionIndexer



    public Object[] getSections() {
        return sectionHeaders;
    }


    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }


    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }



}
