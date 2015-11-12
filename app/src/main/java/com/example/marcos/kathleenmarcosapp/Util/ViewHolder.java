package com.example.marcos.kathleenmarcosapp.Util;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marcos on 21/10/2015.
 */
public class ViewHolder {

    private TextView modelo;
    private TextView fabricante;
    private TextView kmLivre;
    private TextView kmControlado;

    public ViewHolder(TextView modelo, TextView fabricante, TextView kmLivre, TextView kmControlado) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.kmLivre = kmLivre;
        this.kmControlado = kmControlado;
    }

    public TextView getModelo() {
        return modelo;
    }

    public void setModelo(TextView modelo) {
        this.modelo = modelo;
    }

    public TextView getFabricante() {
        return fabricante;
    }

    public void setFabricante(TextView fabricante) {
        this.fabricante = fabricante;
    }

    public TextView getKmLivre() {
        return kmLivre;
    }

    public void setKmLivre(TextView kmLivre) {
        this.kmLivre = kmLivre;
    }

    public TextView getKmControlado() {
        return kmControlado;
    }

    public void setKmControlado(TextView kmControlado) {
        this.kmControlado = kmControlado;
    }
}