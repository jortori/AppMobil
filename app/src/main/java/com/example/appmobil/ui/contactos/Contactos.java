package com.example.appmobil.ui.contactos;

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
import com.example.appmobil.modelos.Profesion;
import org.json.JSONObject;
import java.util.ArrayList;

public class Contactos extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    EditText PrimerNombre, SegundoNonbre, PrimerApellido, SegundoApellido, Cargo, Telefono, Celular, Correo;
    TextView Usuario;
    Button btnRegistrar, btnRegresar;
    Spinner SpinnerProfesion;
    AutoCompleteTextView SpinnerCliente;
    ArrayList<Profesion> profesiones;
    ArrayList<Customer> clientes;
    ArrayList<String> listaprofesiones, listaClientes;
    String Cliente, Profesion, usuario;
    private SQLiteHelper db;
    public static final String nombres = "names";
    JsonRequest jrq;

    private ContactosViewModel mViewModel;

    public static Contactos newInstance() {
        return new Contactos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.contactos_fragment, container, false);
        SpinnerCliente = vista.findViewById(R.id.txtclientepedido);
        SpinnerProfesion = vista.findViewById(R.id.txtitem1);
        btnRegistrar = vista.findViewById(R.id.botonregistrarcontacto);
        PrimerNombre  = vista.findViewById(R.id.txtprimernombrecontacto);
        SegundoNonbre  = vista.findViewById(R.id.txtsegundonombrecontacto);
        PrimerApellido  = vista.findViewById(R.id.txtprimerapellidocontacto);
        SegundoApellido  = vista.findViewById(R.id.txtsegundoapellidocontacto);
        Cargo  = vista.findViewById(R.id.txtcargocontacto);
        Telefono  = vista.findViewById(R.id.txttelefonocontacto);
        Celular  = vista.findViewById(R.id.txtcelularcontacto);
        Correo  = vista.findViewById(R.id.txtmailcontacto);
        Usuario = vista.findViewById(R.id.usuariocontacto);
        Bundle bundle = new Bundle();
        bundle =getArguments();
        usuario = bundle.getString("names");
        Usuario.setText(usuario);
        db = new SQLiteHelper(getContext());
        Consultarlistaclientes();
        Consultarproposito();

        SpinnerProfesion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    Profesion = profesiones.get(position-1).getProfesion();
                } else {
                    Profesion = " ";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzar();
            }
        });

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContactosViewModel.class);
    }

    private void lanzar() {
        Bundle data = new Bundle();
        data.putString("cliente",SpinnerCliente.getText().toString());
        data.putString("profesion",Profesion);
        data.putString("primernombre",PrimerNombre.getText().toString());
        data.putString("names",usuario);
        data.putString("segundonombre", SegundoNonbre.getText().toString());
        data.putString("primerapellido",PrimerApellido.getText().toString());
        data.putString("segundoapellido",SegundoApellido.getText().toString());
        data.putString("cargo",Cargo.getText().toString());
        data.putString("telefono", Telefono.getText().toString());
        data.putString("celular", Celular.getText().toString());
        data.putString("correo", Correo.getText().toString());
        ContactResult contactResult = new ContactResult();
        contactResult.setArguments(data);
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, contactResult, "Contactos");
        fragmentTransaction.commit();
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

    private void Consultarproposito() {
        profesiones  = db.getProfesion();
        listaprofesiones = new ArrayList<>();
        listaprofesiones.add("Seleccione");
        for (int i = 0 ; i<profesiones.size();i++){
            listaprofesiones.add(profesiones.get(i).getId() + " - " + profesiones.get(i).getProfesion());
        }
        ArrayAdapter<CharSequence> adaptador2  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listaprofesiones);
        SpinnerProfesion.setAdapter(adaptador2);
    }

    private void insertarcontacto(){
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/insertar_contacto.php?cliente="+ SpinnerCliente.getText() + "&profesion=" + Profesion +
                "&primernombre=" + PrimerNombre.getText().toString() + "&segundonombre=" + SegundoNonbre.getText().toString() + "&primerapellido=" + PrimerApellido.getText().toString() +
                "&segundoapellido=" + SegundoApellido.getText().toString() + "&cargo=" + Cargo.getText().toString() + "&telefono=" +
                Telefono.getText().toString() + "&celular=" + Celular.getText().toString() + "&correo=" + Correo.getText().toString();
        URL = URL.replace(" ","%20");
        URL = URL.replace("#","No ");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);

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