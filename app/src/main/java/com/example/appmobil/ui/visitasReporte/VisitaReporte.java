package com.example.appmobil.ui.visitasReporte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.ProductAdapter;
import com.example.appmobil.Adapter.VisitAdapter;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Product;
import com.example.appmobil.modelos.Visit;
import com.example.appmobil.ui.Products.ProductosViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VisitaReporte extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    TextView UsuarioReporte;
    Button botonbuscar;
    ListView Listavisita;
    ArrayList<Visit> Visitas;
    ArrayList<String> VisitasString, meses, years;
    JsonRequest jrq;
    String usuario;
    Spinner SpinnerMes, SpinnerYr;
    String mes;
    String yr;

    private ProductosViewModel mViewModel;

    public static VisitaReporte newInstance() {
        return new VisitaReporte();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.visit_report_fragment,container,false);
        UsuarioReporte = vista.findViewById(R.id.usuariovisitreport);
        Bundle bundle =getArguments();
        usuario = bundle.getString("names");
        UsuarioReporte.setText(usuario);
        botonbuscar = vista.findViewById(R.id.SearchVisits);
        Listavisita = vista.findViewById(R.id.VisitList);
        SpinnerMes = vista.findViewById(R.id.txtitem1);
        SpinnerYr = vista.findViewById(R.id.txtitem2);
        Consultarmes();
        ConsultarAño();
        botonbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarsesion();
            }});

        SpinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    mes = meses.get(position);
                } else {
                    mes = " ";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SpinnerYr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    yr = years.get(position);
                } else {
                    mes = " ";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return vista;
    }

    private void iniciarsesion() {
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/buscar_todas_visitas.php?usuario="+usuario+"&fecha="+mes+"&yr="+yr;
        URL = URL.replace(" ","%20");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jrq);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductosViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No Se ha encontrado el producto, se detecto el siguiente error: " + error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Visitas = new ArrayList<Visit>();
            VisitasString = new ArrayList<String>();
            JSONArray jsonArray = response.getJSONArray("clientes");
            JSONObject visitas  = null;
            for (int i = 0; i< jsonArray.length(); i++) {
                visitas = jsonArray.getJSONObject(i);
                Visit visit = new Visit();
                visit.setCliente(visitas.optString("cliente"));                      //Encabezados de las columnas de la tabla de datos!
                visit.setFecha(visitas.optString("fecha"));
                visit.setUsuario(visitas.optString("usuario"));
                visit.setDireccion(visitas.optString("direccion"));
                visit.setLatitud(visitas.optString("latitud"));
                visit.setLongitud(visitas.optString("longitud"));
                Visitas.add(visit);
            }
            VisitAdapter adaptador = new VisitAdapter(getActivity(), Visitas);
            Listavisita.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void Consultarmes() {
        meses = new ArrayList<>();
        meses.add("Seleccione");
        meses.add("01");
        meses.add("02");
        meses.add("03");
        meses.add("04");
        meses.add("05");
        meses.add("06");
        meses.add("07");
        meses.add("08");
        meses.add("09");
        meses.add("10");
        meses.add("11");
        meses.add("12");
        ArrayAdapter<CharSequence> adaptador2  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,meses);
        SpinnerMes.setAdapter(adaptador2);
    }

    private void ConsultarAño() {
        years = new ArrayList<>();
        years.add("Seleccione");
        years.add("2020");
        years.add("2021");
        years.add("2022");
        years.add("2023");
        years.add("2024");
        years.add("2025");
        years.add("2026");
        years.add("2027");
        years.add("2028");
        years.add("2029");
        years.add("2030");
        years.add("2031");
        ArrayAdapter<CharSequence> adaptador2  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,years);
        SpinnerYr.setAdapter(adaptador2);
    }
}