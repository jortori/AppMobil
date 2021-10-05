package com.example.appmobil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.modelos.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SessionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{
    JsonRequest jrq;
    EditText cajauser, cajapwd;
    ImageButton loginbutton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_session,container,false);

        cajauser = vista.findViewById(R.id.txtuser);
        cajapwd = vista.findViewById(R.id.txtpassword);
        loginbutton = vista.findViewById(R.id.botonlogin);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarsesion();
            }
        });
        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No Se ha encontrado el usuario, se detecto el siguiente error:     " + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        User usuario = new User();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));                      //Encabezados de las columnas de la tabla de datos!
            usuario.setNombre(jsonObject.optString("nombres"));
            usuario.setPassword(jsonObject.optString("pwd"));
            Toast.makeText(getContext(),"Se ha encontrado el usuario: " + usuario.getUser(),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(MainActivity.nombres, usuario.getNombre());
        startActivity(intent);
    }

    private void iniciarsesion(){
        String ip ="http://23.253.109.115/prueba/";
        String URL = ip+"/iniciar.php?user="+cajauser.getText().toString()+"&pwd="+cajapwd.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jrq);

    }
}
