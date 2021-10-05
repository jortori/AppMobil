package com.example.appmobil.ui.Products;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.ProductAdapter;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Productos extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    EditText cajaproducto;
    Button botonbuscar;
    ListView Listaproducto;
    ArrayList<Product> Productos;
    ArrayList<String> ProductosString;
    JsonRequest jrq;

    private ProductosViewModel mViewModel;

    public static Productos newInstance() {
        return new Productos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.productos_fragment,container,false);
        cajaproducto = vista.findViewById(R.id.txtproducts);
        botonbuscar = vista.findViewById(R.id.SearchProducts);
        Listaproducto = vista.findViewById(R.id.ProductList);
        botonbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarsesion();
            }});
        return vista;
    }

    private void iniciarsesion() {
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/buscar_productos.php?ds18="+cajaproducto.getText().toString();
        URL = URL.replace(" ","%20");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jrq);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductosViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No Se ha encontrado el producto, se detecto el siguiente error: " + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Productos = new ArrayList<Product>();
            ProductosString = new ArrayList<String>();
            JSONArray jsonArray = response.getJSONArray("datos");
            JSONObject productos  = null;
            for (int i = 0; i< jsonArray.length(); i++) {
                productos = jsonArray.getJSONObject(i);
                Product Prods = new Product();
                Prods.setPano20(productos.optString("pano20"));                      //Encabezados de las columnas de la tabla de datos!
                Prods.setDs18(productos.optString("ds18"));
                Prods.setQyhnd(productos.optInt("on_hand"));
                Prods.setSos1(productos.optString("sos1"));
                Prods.setPres(productos.optString("pres"));
                Prods.setLob(productos.optString("lob"));
                Prods.setTipo(productos.optString("tipo"));
                Prods.setQyor(productos.optInt("on_order"));
                Productos.add(Prods);
            }
            ProductAdapter adaptador = new ProductAdapter(getActivity(), Productos);
            Listaproducto.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}