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
import com.example.appmobil.modelos.Customer;

import java.util.ArrayList;


public class CustomerAdapter extends BaseAdapter {
     Activity activity;
     ArrayList<Customer> items;

    public CustomerAdapter(Activity activity, ArrayList<Customer> items) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.customer_list_template, null);
        }

        Customer item = items.get(position);
        ImageView image =  vi.findViewById(R.id.icono);
        TextView codigo =  vi.findViewById(R.id.codigo);
        codigo.setText(item.getCuno());
        TextView nombre =  vi.findViewById(R.id.nombre);
        nombre.setText(item.getCunm());
        TextView phone = vi.findViewById(R.id.txttelefono);
        phone.setText(item.getPhone());
        TextView credit = vi.findViewById(R.id.txtcredito);
        credit.setText(Integer.toString(item.getCredit()));
    return vi;
    }
}
