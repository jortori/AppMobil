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
import com.example.appmobil.modelos.Tracking;

import java.util.ArrayList;


public class TrackingAdapter extends BaseAdapter {
     Activity activity;
     ArrayList<Tracking> items;

    public TrackingAdapter(Activity activity, ArrayList<Tracking> items) {
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
            vi = inflater.inflate(R.layout.tracking_list_template, null);
        }
        Tracking item = items.get(position);
        ImageView image =  vi.findViewById(R.id.icono);
        TextView latitud =  vi.findViewById(R.id.txtlatitud_tracking_template);
        latitud.setText(item.getLatitud());
        TextView longitud =  vi.findViewById(R.id.txtlongitud_tracking_template);
        longitud.setText(item.getLongitud());
        TextView direccion =  vi.findViewById(R.id.txtdireccion_tracking_template);
        direccion.setText(item.getDireccion());
        TextView usuario =  vi.findViewById(R.id.user_tracking_template);
        usuario.setText(item.getUsuario());
        TextView fecha =  vi.findViewById(R.id.txtfecha_tracking_template);
        fecha.setText(item.getFecha());
    return vi;
    }
}
