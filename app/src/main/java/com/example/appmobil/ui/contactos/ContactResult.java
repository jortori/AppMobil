package com.example.appmobil.ui.contactos;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.ui.visitas.Visitas;

public class ContactResult extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    TextView Cliente, Profesion, Primernombre, Segundonombre, Primerapellido, Segundoapellido, Cargo, Telefono, Celular, Correo, Usuario;
    String usuario, cliente, profesion, primernombre, primerapellido, segundonombre, segundoapellido, cargo, telefono, celular, correo;
    JsonRequest jrq;

    public ContactResult() {
    }

    // TODO: Rename and change types and number of parameters
    public static ContactResult newInstance(String param1, String param2) {
        ContactResult fragment = new ContactResult();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.contactos_fragment_result, container, false);
        Cliente = vista.findViewById(R.id.txtclientecontactoreporte);
        Profesion = vista.findViewById(R.id.txtprofesioncontactoreporte);
        Primernombre = vista.findViewById(R.id.txtprimernombrecontactoreporte);
        Segundonombre = vista.findViewById(R.id.txtsegundonombrecontactoreporte);
        Primerapellido = vista.findViewById(R.id.txtprimerapellidocontactoreporte);
        Segundoapellido = vista.findViewById(R.id.txtsegundoapellidocontactoreporte);
        Cargo = vista.findViewById(R.id.txtcargocontactoreporte);
        Telefono = vista.findViewById(R.id.txttelefonocontactoreporte);
        Celular = vista.findViewById(R.id.txtcelularcontactoreporte);
        Correo = vista.findViewById(R.id.txtmailcontactoreporte);
        Usuario = vista.findViewById(R.id.usuariocontactoreport);
        Bundle bundle =getArguments();
        usuario = bundle.getString("names");
        cliente = bundle.getString("cliente");
        profesion = bundle.getString("profesion");
        primernombre = bundle.getString("primernombre");
        segundonombre = bundle.getString("segundonombre");
        primerapellido = bundle.getString("primerapellido");
        segundoapellido = bundle.getString("segundoapellido");
        cargo = bundle.getString("cargo");
        telefono = bundle.getString("telefono");
        celular = bundle.getString("celular");
        correo = bundle.getString("correo");
        Cliente.setText(cliente);
        Profesion.setText(profesion);
        Primernombre.setText(primernombre);
        Segundonombre.setText(segundonombre);
        Primerapellido.setText(primerapellido);
        Segundoapellido.setText(segundoapellido);
        Cargo.setText(cargo);
        Telefono.setText(telefono);
        Celular.setText(celular);
        Correo.setText(correo);
        Usuario.setText(usuario);
        insertarcontacto();
        return vista;
    }


    private void insertarcontacto(){
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/insertar_contacto.php?cliente="+ cliente + "&profesion=" + profesion +
                "&primernombre=" + primernombre + "&segundonombre=" + segundonombre + "&primerapellido=" + primerapellido +
                "&segundoapellido=" + segundoapellido + "&cargo=" + cargo + "&telefono=" +
                telefono + "&celular=" + celular + "&correo=" + correo;
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
        Toast.makeText(getContext(),"Contacto ingresado correctamente" ,Toast.LENGTH_SHORT).show();
    }
}