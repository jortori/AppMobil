package com.example.appmobil.Adapter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PositionService extends Service implements Response.ErrorListener, Response.Listener<JSONObject>{

    String direccion, latitud, longitud, fecha, user, latitude, longitude, address, date;
    private Context thisContext=this;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    final Handler handler= new Handler();
    Runnable runnable;
    JsonRequest jrq;
    public static final String usuario = "names";
    public static final String lat = "lat";
    public static final String lon = "lon";
    public static final String dir = "dir";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){

    }

    public void onDestroy(){
        Toast.makeText(thisContext,"Finalizando el seguimiento ....... " ,Toast.LENGTH_SHORT).show();
        handler.removeCallbacks(runnable);
    }

    public int onStartCommand(Intent intent, int Flag, int idProcess ){
        user = intent.getStringExtra("names");
        latitude = intent.getStringExtra("lat");
        longitude = intent.getStringExtra("lon");
        address = intent.getStringExtra("dir");
        Toast.makeText(thisContext,"Iniciando el seguimiento ......" ,Toast.LENGTH_SHORT).show();
            insertarcontacto();
        return START_STICKY;
    }

    private void insertarcontacto(){
        Date date = new Date();
        fecha = dateFormat.format(date);
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/insertar_tracking.php?latitud="+ latitude + "&longitud=" + longitude +
                "&direccion=" + address + "&usuario=" + user + "&fecha=" + fecha;
        URL = URL.replace(" ","%20");
        URL = URL.replace("#","No ");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(thisContext).addToRequestQueue(jrq);
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }
    @Override
    public void onResponse(JSONObject response) {

    }
    }
