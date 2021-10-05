package com.example.appmobil.ui.Customers;

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
import com.example.appmobil.Adapter.CustomerAdapter;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Clientes extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{
    EditText cajacliente;
    Button botonbuscar;
    ListView Listacliente;
    ArrayList<Customer> Clientes;
    //RequestQueue rq;
    JsonRequest jrq;

    private ClientesViewModel mViewModel;

    public static Clientes newInstance() {
        return new Clientes();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.clientes_fragment,container,false);
        cajacliente = vista.findViewById(R.id.txtcustomer);
        botonbuscar = vista.findViewById(R.id.SearchCustomers);
        Listacliente = vista.findViewById(R.id.CustomerList);
        //rq = Volley.newRequestQueue(getContext());
        botonbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarsesion();
            }});
        return vista;
    }

    private void iniciarsesion() {
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/buscar_clientes.php?cunm="+cajacliente.getText().toString();
        URL = URL.replace(" ","%20");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jrq);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClientesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No Se ha encontrado el producto, se detecto el siguiente error: " + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Clientes = new ArrayList<Customer>();
            JSONArray jsonArray = response.getJSONArray("clientes");
            JSONObject clientes  = null;
            for (int i = 0; i< jsonArray.length(); i++) {
                clientes = jsonArray.getJSONObject(i);
                Customer Cust = new Customer();
                Cust.setCuno(clientes.optString("cuno"));                      //Encabezados de las columnas de la tabla de datos!
                Cust.setCunm(clientes.optString("cunm"));
                Cust.setPhone(clientes.optString("phno"));
                Cust.setCredit(clientes.optInt("crlmt"));
                Clientes.add(Cust);
            }
            CustomerAdapter adaptador = new CustomerAdapter(getActivity(), Clientes);
            Listacliente.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}