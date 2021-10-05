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
import com.example.appmobil.modelos.Product;
import java.util.ArrayList;


public class ScheduleAdapter extends BaseAdapter {
     Activity activity;
     ArrayList<Product> items;

    public ScheduleAdapter(Activity activity, ArrayList<Product> items) {
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
        return items.get(position).getQyhnd();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_template, null);
        }
        Product item = items.get(position);
        ImageView image =  vi.findViewById(R.id.icono);
        TextView numparte =  vi.findViewById(R.id.numparte);
        numparte.setText(item.getPano20());
        TextView desc =  vi.findViewById(R.id.desc);
        desc.setText(item.getDs18());
        TextView onhand = vi.findViewById(R.id.txtonhand);
        onhand.setText(Integer.toString(item.getQyhnd()));
    return vi;
    }
}
