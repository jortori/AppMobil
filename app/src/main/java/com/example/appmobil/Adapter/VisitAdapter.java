package com.example.appmobil.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmobil.R;
import com.example.appmobil.modelos.Visit;

import java.util.ArrayList;


public class VisitAdapter extends BaseAdapter {
     Activity activity;
    ArrayList<Visit> items;

    public VisitAdapter(Activity activity, ArrayList<Visit> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1000;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.visit_list_template, null);
        }

        Visit item = items.get(position);
        ImageView image =  vi.findViewById(R.id.icono);
        TextView codigo =  vi.findViewById(R.id.codigo_template);
        codigo.setText(item.getCliente());
        TextView comentario =  vi.findViewById(R.id.nombre_template);
        comentario.setText(item.getComentario());
        TextView usuario =  vi.findViewById(R.id.user_template);
        usuario.setText(item.getUsuario());
        TextView latitud =  vi.findViewById(R.id.txtlatitudtemplate);
        latitud.setText(item.getLatitud());
        TextView longitud =  vi.findViewById(R.id.txtlongitudtemplate);
        longitud.setText(item.getLongitud());
        TextView direccion =  vi.findViewById(R.id.txtdirecciontemplate);
        direccion.setText(item.getDireccion());
        TextView fecha =  vi.findViewById(R.id.txtfechatemplate);
        fecha.setText(item.getFecha());

        return vi;
    }
}
