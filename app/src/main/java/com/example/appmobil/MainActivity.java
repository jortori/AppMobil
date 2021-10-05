package com.example.appmobil;

import android.Manifest;
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
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.Adapter.VolleySingleton;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        Response.ErrorListener, Response.Listener<JSONObject>{

    private AppBarConfiguration mAppBarConfiguration;
    public static final String nombres = "names";
    String latitud, longitud, direccion, fecha, usuario;
    TextView Usuario, Latitud, Longitud, Direccion, UsuarioContacto;
    MainActivity mainActivity;
    Visitas visitas;
    Welcome welcome;
    Sitios sitios;

    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public MainActivity getMainActivity() {
        return mainActivity;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public Visitas getVisitas() {
        return visitas;
    }
    public void setVisitas(Visitas visitas) {
        this.visitas = visitas;
    }

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

        welcome = new Welcome();
        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.nav_host_fragment, welcome, "Bienvenido");
        fragmentTransaction2.commit();
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
                data2.putString("names",usuario);
                contactos.setArguments(data2);
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.nav_host_fragment, contactos, "Visitas");
                fragmentTransaction2.commit();
                break;
            case R.id.productos:
                Productos productos = new Productos();
                Bundle data3 = new Bundle();
                data3.putString("names",usuario);
                productos.setArguments(data3);
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.nav_host_fragment, productos, "Visitas");
                fragmentTransaction3.commit();
                break;
            case R.id.clientes:
                Clientes clientes = new Clientes();
                Bundle data4 = new Bundle();
                data4.putString("names",usuario);
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
                VisitaReporte visitaReporte = new VisitaReporte();
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


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No Se ha encontrado establecido conexion debido a:      " + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
    }



}