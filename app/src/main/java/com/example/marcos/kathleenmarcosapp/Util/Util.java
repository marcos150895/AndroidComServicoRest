package com.example.marcos.kathleenmarcosapp.Util;


import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.marcos.kathleenmarcosapp.R;

import java.lang.reflect.Field;

/**
 * Created by Marcos on 21/10/2015.
 */
public class Util {
    public static Drawable getDrawable(Activity context, String drawableName) {
        //troca hifen por underline
        drawableName = drawableName.replace('-', '_');
        drawableName = drawableName.replace('â', 'a');
        //procurar imagem
        Class<?> c = R.drawable.class;
        try {
            Field idField = c.getDeclaredField(drawableName);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //return context.getResources().getDrawable();
        return null;
    }
}
