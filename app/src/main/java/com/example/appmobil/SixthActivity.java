package com.example.appmobil;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.modelos.Contact;
import com.example.appmobil.modelos.Sitio;
import com.example.appmobil.ui.Actividad.Actividad;
import com.example.appmobil.ui.Customers.Clientes;
import com.example.appmobil.ui.Pedido.Pedido;
import com.example.appmobil.ui.Products.Productos;
import com.example.appmobil.ui.Sites.Sitios;
import com.example.appmobil.ui.contactos.Contactos;
import com.example.appmobil.ui.visitas.Visitas;
import com.example.appmobil.ui.visitasReporte.VisitaReporte;
import com.example.appmobil.ui.welcome.Welcome;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SixthActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>, NavigationView.OnNavigationItemSelectedListener{
    String latitud, longitud, direccion, fecha, usuario;
    JsonRequest jrq;
    ArrayList<Contact> Contactos;
    final Handler handler= new Handler();
    public static final String nombres = "names";
    private SQLiteHelper db;
    private AppBarConfiguration mAppBarConfiguration;
    ProgressDialog progressDialog = null;
    Welcome welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        usuario = getIntent().getStringExtra("names");
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textView);
        navUsername.setText(usuario);

        db = new SQLiteHelper(this);
        //db.borrarsitios();
        iniciarsesion();
        latitud = "";
        longitud ="";
        direccion ="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = new Date();
        fecha = dateFormat.format(date);
       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
           progressDialog = new ProgressDialog(this);
           progressDialog.show();
           progressDialog.setTitle("Encontrando su ubicación...");
           progressDialog.setMessage("Espere un momento... Registrando ubicación.");
           progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
           progressDialog.setProgress(0);
           progressDialog.setMax(10);
           progressDialog.show();
       }

       if (direccion==""){
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   Bundle data = new Bundle();
                   data.putString("latitud",latitud);
                   data.putString("longitud",longitud);
                   data.putString("direccion",direccion);
                   data.putString("names",usuario);
                   data.putString("fecha", fecha);
                   Sitios sitios = new Sitios();
                   sitios.setArguments(data);
                   FragmentManager fm = getSupportFragmentManager();
                   fm.beginTransaction().replace(R.id.nav_host_fragment, sitios).commit();
                   progressDialog.dismiss();
               }
           }, 6000);
       } else {
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   Bundle data = new Bundle();
                   data.putString("latitud",latitud);
                   data.putString("longitud",longitud);
                   data.putString("direccion",direccion);
                   data.putString("names",usuario);
                   data.putString("fecha", fecha);
                   Sitios sitios = new Sitios();
                   sitios.setArguments(data);
                   FragmentManager fm = getSupportFragmentManager();
                   fm.beginTransaction().replace(R.id.nav_host_fragment, sitios).commit();
                   progressDialog.dismiss();
               }
           }, 4000);
       }
    }

    private void iniciarsesion(){
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/buscar_todos_contactos.php";
        URL = URL.replace(" ","%20");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(this).addToRequestQueue(jrq);
    }

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setFifthActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }
    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion = DirCalle.getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No Se ha encontrado establecido conexion debido a:      " + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Contactos = new ArrayList<Contact>();
            JSONArray jsonArray = response.getJSONArray("clientes");
            JSONObject clientes  = null;
            for (int i = 0; i< jsonArray.length(); i++) {
                clientes = jsonArray.getJSONObject(i);
                Contact Cont = new Contact();
                Cont.setCuno(clientes.optString("cuno"));
                Cont.setProfesion(clientes.optString("profesion"));
                Cont.setFirstname(clientes.optString("firstname"));
                Cont.setSecondname(clientes.optString("secondname"));
                Cont.setLastname1(clientes.optString("lastname1"));
                Cont.setLastname2(clientes.optString("lastname2"));
                Cont.setCargo(clientes.optString("cargo"));
                Cont.setPhone(clientes.optString("phone"));
                Cont.setCel(clientes.optString("cel"));
                Cont.setEmail(clientes.optString("email"));
                Contactos.add(Cont);
                db.insertarcontacto(Cont.getCuno(), Cont.getProfesion(), Cont.getFirstname(), Cont.getSecondname(), Cont.getLastname1(), Cont.getLastname2(), Cont.getCargo(), Cont.getPhone(), Cont.getCel(), Cont.getEmail());

            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);
        int id=item.getItemId();
        switch (id) {
            case R.id.visitas:
                Intent h= new Intent(this, FifthActivity.class);
                h.putExtra(FifthActivity.nombres, usuario);
                startActivity(h);
                break;
            case R.id.contactos:
                Contactos contactos = new Contactos();
                Bundle data2 = new Bundle();
                data2.putString("latitud",latitud);
                data2.putString("longitud",longitud);
                data2.putString("direccion",direccion);
                data2.putString("names",usuario);
                data2.putString("fecha", fecha);
                contactos.setArguments(data2);
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.nav_host_fragment, contactos, "Visitas");
                fragmentTransaction2.commit();
                break;
            case R.id.productos:
                Productos productos = new Productos();
                Bundle data3 = new Bundle();
                data3.putString("latitud",latitud);
                data3.putString("longitud",longitud);
                data3.putString("direccion",direccion);
                data3.putString("names",usuario);
                data3.putString("fecha", fecha);
                productos.setArguments(data3);
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.nav_host_fragment, productos, "Visitas");
                fragmentTransaction3.commit();
                break;
            case R.id.clientes:
                Clientes clientes = new Clientes();
                Bundle data4 = new Bundle();
                data4.putString("latitud",latitud);
                data4.putString("longitud",longitud);
                data4.putString("direccion",direccion);
                data4.putString("names",usuario);
                data4.putString("fecha", fecha);
                clientes.setArguments(data4);
                FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction4.replace(R.id.nav_host_fragment, clientes, "Visitas");
                fragmentTransaction4.commit();
                break;
            case R.id.pedido:
                Pedido pedido = new Pedido();
                Bundle data5 = new Bundle();
                data5.putString("names",usuario);
                pedido.setArguments(data5);
                FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction5.replace(R.id.nav_host_fragment, pedido, "Pedido");
                fragmentTransaction5.commit();
                break;
            case R.id.actividad:
                Actividad actividad = new Actividad();
                Bundle data8 = new Bundle();
                data8.putString("names",usuario);
                actividad.setArguments(data8);
                FragmentTransaction fragmentTransaction8 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction8.replace(R.id.nav_host_fragment, actividad, "Actividad");
                fragmentTransaction8.commit();
                break;
            case R.id.nav_home:
                welcome = new Welcome();
                Bundle data6 = new Bundle();
                data6.putString("names",usuario);
                FragmentTransaction fragmentTransaction6 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction6.replace(R.id.nav_host_fragment, welcome, "Bienvenido");
                fragmentTransaction6.commit();
                break;
            case R.id.sitios:
                Intent j= new Intent(this, SixthActivity.class);
                j.putExtra(SixthActivity.nombres, usuario);
                startActivity(j);
                break;
            case R.id.reportevisitas:
                VisitaReporte visitaReporte= new VisitaReporte();
                Bundle data7 = new Bundle();
                data7.putString("names",usuario);
                visitaReporte.setArguments(data7);
                FragmentTransaction fragmentTransaction7 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction7.replace(R.id.nav_host_fragment, visitaReporte, "Reporte de visitas");
                fragmentTransaction7.commit();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class Localizacion implements LocationListener {
        SixthActivity fifthActivity;
        public SixthActivity getFifthActivity() {
            return fifthActivity;
        }
        public void setFifthActivity(SixthActivity fifthActivity) {
            this.fifthActivity = fifthActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String sLatitud = String.valueOf(loc.getLatitude());
            String sLongitud = String.valueOf(loc.getLongitude());
            latitud = sLatitud;
            longitud = sLongitud;
            this.fifthActivity.setLocation(loc);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            latitud = "GPS Activado";
        }
        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            latitud = "GPS Desactivado";
        }
    }



}
