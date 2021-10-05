package com.example.appmobil.ui.Sites;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Customer;
import com.example.appmobil.modelos.Sites;
import com.example.appmobil.ui.visitas.VisitResult;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Sitios extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    EditText NombreSitio;
    TextView Usuario, Latitud, Longitud, Direccion;
    Button btnRegistrar, btnRegresar;
    Spinner Spinnercodigo;
    AutoCompleteTextView SpinnerCliente;
    ArrayList<Sites> sitios;
    ArrayList<Customer> clientes;
    ArrayList<String> listasitios, listaClientes;
    String usuario, Sitio, direccion, latitud, longitud, codigo, fecha;
    private SQLiteHelper db;
    JsonRequest jrq;

    private SitiosViewModel mViewModel;

    public static Sitios newInstance() {
        return new Sitios();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.sitios_fragment, container, false);
        SpinnerCliente = vista.findViewById(R.id.txtclientesitio);
        Spinnercodigo = vista.findViewById(R.id.txtcodigositio);
        btnRegistrar = vista.findViewById(R.id.botonregistrarsitio);
        NombreSitio  = vista.findViewById(R.id.txtnombresitio);
        Latitud  = vista.findViewById(R.id.txtlatitudsitio);
        Longitud  = vista.findViewById(R.id.txtlongitudsitio);
        Direccion  = vista.findViewById(R.id.txtdireccionsitio);
        Usuario = vista.findViewById(R.id.usuariositio);
        db = new SQLiteHelper(getContext());
        Bundle bundle = new Bundle();
        bundle =getArguments();
        longitud = bundle.getString("longitud");
        latitud = bundle.getString("latitud");
        direccion = bundle.getString("direccion");
        usuario = bundle.getString("names");
        Usuario.setText(usuario);
        Longitud.setText(longitud);
        Direccion.setText(direccion);
        Latitud.setText(latitud);
        Consultarlistaclientes();
        Consultarsitio();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "......Registrando sitio", Toast.LENGTH_SHORT).show();
                insertarsitio();
            }
        });
        Spinnercodigo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    Sitio = listasitios.get(position);
                } else {
                    Sitio = " ";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return vista;
    }

    private void Consultarsitio() {
        sitios  = db.getSites();
        listasitios = new ArrayList<>();
        listasitios.add("Seleccione");
        for (int i = 0 ; i<sitios.size();i++){
            listasitios.add(sitios.get(i).getId_site() + " - " + sitios.get(i).getSite());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listasitios);
        Spinnercodigo.setAdapter(adaptador);
    }
    private void Consultarlistaclientes() {
        clientes  = db.getCustomers();
        listaClientes = new ArrayList<>();
        for (int i = 0 ; i<clientes.size();i++){
            listaClientes.add(clientes.get(i).getCuno() + " - " + clientes.get(i).getCunm());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listaClientes);
        SpinnerCliente.setAdapter(adaptador);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SitiosViewModel.class);
        // TODO: Use the ViewModel
    }

    private void insertarsitio(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = new Date();
        fecha = dateFormat.format(date);
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/insertar_sitio.php?id_sitio="+ Sitio + "&cuno="+ SpinnerCliente.getText().toString().substring(0,6) + "&nombre_sitio=" + NombreSitio.getText().toString() +
                "&direccion_sitio=" + direccion + "&latitud_sitio=" + latitud + "&longitud_sitio=" + longitud +
                "&user=" + usuario + "&fecha=" + fecha;
        URL = URL.replace(" ","%20");
        URL = URL.replace("#","No ");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No Se ha podido enlazar comunicación" + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Sitio ingresado correctamente" ,Toast.LENGTH_SHORT).show();
    }
}