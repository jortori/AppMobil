package com.example.appmobil.ui.visitas;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Contact;
import com.example.appmobil.modelos.Customer;
import com.example.appmobil.modelos.Purpose;
import com.example.appmobil.modelos.Sitio;
import com.example.appmobil.modelos.User;
import com.example.appmobil.modelos.Visit;
import com.example.appmobil.ui.Sites.Sitios;
import com.example.appmobil.ui.contactos.Contactos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Visitas extends Fragment {

    private VisitasViewModel mViewModel;
    EditText comentariovisita;
    TextView Latitud, Longitud, Direccion, Usuario, Fecha;
    String  Nombre, Contacto, Proposito;
    Button botonregistrar, botonregresar;
    ImageButton botoncontacto, botonsitio;
    Spinner SpinnerProposito;
    AutoCompleteTextView SpinnerClientes, SpinnerContactos ,SpinnerSitios;
    ArrayList<Visit> lista;
    ArrayList<User> usuarios;
    ArrayList<Customer> clientes;
    ArrayList<Purpose> propositos;
    ArrayList<Contact> contactos;
    ArrayList<Sitio> ubicaciones;
    ArrayList<String>  listaClientes, listaContactos, listaPropositos, listaSitios;
    JsonRequest jrq;
    private SQLiteHelper db;
    String usuario, latitud, longitud, direccion, fecha, direccionadj;
    private EditText editText;

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.visitas_fragment, container, false);
        Latitud = vista.findViewById(R.id.txtlatitud);
        Longitud = vista.findViewById(R.id.txtlongitud);
        Direccion = vista.findViewById(R.id.txtdireccion);
        Usuario = vista.findViewById(R.id.txtuser_visit);
        Fecha = vista.findViewById(R.id.txtfecha);
        comentariovisita = vista.findViewById(R.id.txtcomentario);
        botonregistrar = vista.findViewById(R.id.ReportVisits);
        botoncontacto = vista.findViewById(R.id.crearcontacto);
        botonsitio = vista.findViewById(R.id.crearsitio);
        SpinnerClientes = vista.findViewById(R.id.txtcustomer_spinner);
        SpinnerContactos = vista.findViewById(R.id.txtcontacto_spinner);
        SpinnerProposito = vista.findViewById(R.id.txtproposito_spinner);
        SpinnerSitios = vista.findViewById(R.id.txtsitio);
        db = new SQLiteHelper(getContext());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = new Date();
        fecha = dateFormat.format(date);
        Fecha.setText(fecha);
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
        Consultarproposito();
        Consultarcontactos();
        Consultarsitios();
        botonregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertar(Latitud.getText().toString(), Longitud.getText().toString(), Direccion.getText().toString(),
                        Usuario.getText().toString(), Fecha.getText().toString(), Nombre,
                        comentariovisita.getText().toString());
                Toast.makeText(getContext(), "......Registrando visita", Toast.LENGTH_SHORT).show();
                VisitResult visitasResultado = new VisitResult();
                Bundle data = new Bundle();
                data.putString("latitud",latitud);
                data.putString("longitud",longitud);
                data.putString("direccion",direccion);
                data.putString("names",usuario);
                data.putString("fecha", fecha);
                data.putString("cliente",SpinnerClientes.getText().toString());
                data.putString("proposito",Proposito);
                data.putString("sitio",SpinnerSitios.getText().toString());
                data.putString("comentario", comentariovisita.getText().toString());
                data.putString("contacto", SpinnerContactos.getText().toString());
                visitasResultado.setArguments(data);
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, visitasResultado, "Visitas");
                fragmentTransaction.commit();
            }
        });

        botoncontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contactos contactos = new Contactos();
                Bundle data2 = new Bundle();
                data2.putString("names",usuario);
                contactos.setArguments(data2);
                FragmentTransaction fragmentTransaction2 = getParentFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.nav_host_fragment, contactos, "Visitas");
                fragmentTransaction2.commit();
            }
        });

        botonsitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sitios sitios = new Sitios();
                Bundle data7 = new Bundle();
                data7.putString("names",usuario);
                FragmentTransaction fragmentTransaction7 = getParentFragmentManager().beginTransaction();
                fragmentTransaction7.replace(R.id.nav_host_fragment, sitios, "Sitios");
                fragmentTransaction7.commit();
            }
        });

        SpinnerProposito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    Proposito = propositos.get(position-1).getId_purpose() + " - " + propositos.get(position-1).getPurpose();
                } else {
                    Proposito = " ";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return vista;
    }

    private void Consultarlistaclientes() {
        clientes  = db.getCustomers();
        listaClientes = new ArrayList<>();
        for (int i = 0 ; i<clientes.size();i++){
            listaClientes.add(clientes.get(i).getCuno() + " - " + clientes.get(i).getCunm());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getActivity(),R.layout.spinner_item,listaClientes);
        SpinnerClientes.setAdapter(adaptador);
    }

    private void Consultarproposito() {
        propositos  = db.getPurpose();
        listaPropositos = new ArrayList<>();
        listaPropositos.add("Seleccione");
        for (int i = 0 ; i<propositos.size();i++){
            listaPropositos.add(propositos.get(i).getId_purpose() + " - " + propositos.get(i).getPurpose());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getActivity(),R.layout.spinner_item,listaPropositos);
        SpinnerProposito.setAdapter(adaptador);
    }

    private void Consultarcontactos() {
        contactos= db.getContacts();
        listaContactos = new ArrayList<>();
        for (int i = 0 ; i<contactos.size();i++){
            listaContactos.add(contactos.get(i).getCuno()+ " - " + contactos.get(i).getProfesion()  + " " +  contactos.get(i).getFirstname()   + " " +  contactos.get(i).getLastname1());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getActivity(),R.layout.spinner_item,listaContactos);
        SpinnerContactos.setAdapter(adaptador);
    }

    private void Consultarsitios() {
        ubicaciones = db.getSitios();
        listaSitios = new ArrayList<>();
        for (int i = 0 ; i<ubicaciones.size();i++){
            listaSitios.add(ubicaciones.get(i).getCuno() + " - " + ubicaciones.get(i).getNombre_sitio());
            }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getActivity(),R.layout.spinner_item,listaSitios);
        SpinnerSitios.setAdapter(adaptador);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VisitasViewModel.class);

    }
}
