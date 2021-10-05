package com.example.appmobil.ui.Actividad;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.JavaMailAPI;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Customer;
import com.example.appmobil.modelos.Event;
import com.example.appmobil.modelos.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Actividad extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    AutoCompleteTextView Cliente;
    Spinner Actividad;
    TextView Usuario;
    Button BotonRegistro;
    ArrayList<Customer> clientes;
    ArrayList<Event> eventos;
    private SQLiteHelper db;
    String evento, fecha;
    String usuario;
    ArrayList<String>  listaClientes, listaActividades;
    ArrayList<Event>  actividades;
    JsonRequest jrq;

    public static Actividad newInstance() {
        return new Actividad();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.actividad_fragment, container, false);

        Cliente = vista.findViewById(R.id.txtclienteagendar);
        Actividad = vista.findViewById(R.id.txtactividadagendar);
        BotonRegistro = vista.findViewById(R.id.botonconsultaragenda);
        Usuario = vista.findViewById(R.id.usuarioconsultaragenda);

        db = new SQLiteHelper(getContext());

        Bundle bundle = new Bundle();
        bundle =getArguments();
        usuario = bundle.getString("names");
        Usuario.setText(usuario);

        Consultarlistaclientes();
        Consultaractividad();

        BotonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarevento();
            }
        });

        Actividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    evento = actividades.get(position-1).getEvento();
                } else {
                    evento = " ";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void Consultarlistaclientes() {
        clientes  = db.getCustomers();
        listaClientes = new ArrayList<>();
        for (int i = 0 ; i<clientes.size();i++){
            listaClientes.add(clientes.get(i).getCuno() + " - " + clientes.get(i).getCunm());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listaClientes);
        Cliente.setAdapter(adaptador);
    }

    private void Consultaractividad() {
        actividades  = db.getEventos();
        listaActividades = new ArrayList<>();
        listaActividades.add("Seleccione");
        for (int i = 0 ; i<actividades.size();i++){
            listaActividades.add(actividades.get(i).getId() + " - " + actividades.get(i).getEvento());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listaActividades);
        Actividad.setAdapter(adaptador);
    }

    private void insertarevento(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = new Date();
        fecha = dateFormat.format(date);
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/insertar_evento.php?usuario="+ Usuario.getText().toString() + "&cliente=" + Cliente.getText().toString() +
                "&evento=" + evento + "&fecha=" + fecha;
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
        Toast.makeText(getContext(),"Se ha registrador el evento: " + evento,Toast.LENGTH_SHORT).show();
    }



}