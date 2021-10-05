package com.example.appmobil.ui.welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Sitio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Welcome extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    JsonRequest jrq;
    ArrayList<Sitio> Sitios;
    private SQLiteHelper db;


    public static Welcome newInstance() {
        return new Welcome();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_welcome, container, false);
        db = new SQLiteHelper(getContext());
        db.borrarsitios();
        iniciarsesion();
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void iniciarsesion(){
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/buscar_todos_sitios.php";
        URL = URL.replace(" ","%20");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No Se ha podido enlazar comunicación" + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Sitios = new ArrayList<Sitio>();
            JSONArray jsonArray = response.getJSONArray("clientes");
            JSONObject sitio  = null;
            for (int i = 0; i< jsonArray.length(); i++) {
                sitio = jsonArray.getJSONObject(i);
                Sitio Sit = new Sitio();
                Sit.setId_sitio(sitio.optString("id_sitio"));
                Sit.setCuno(sitio.optString("cuno"));
                Sit.setNombre_sitio(sitio.optString("nombre_sitio"));
                Sit.setDireccion_sitio(sitio.optString("direccion_sitio"));
                Sit.setLatitud_sitio(sitio.optString("latitud_sitio"));
                Sit.setLongitud_sitio(sitio.optString("longitud_sitio"));
                Sitios.add(Sit);
                db.insertarsitios(Sit.getId_sitio(), Sit.getCuno(), Sit.getNombre_sitio(), Sit.getDireccion_sitio(), Sit.getLatitud_sitio(), Sit.getLongitud_sitio());
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}