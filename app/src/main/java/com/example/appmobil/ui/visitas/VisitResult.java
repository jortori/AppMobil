package com.example.appmobil.ui.visitas;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.JavaMailAPI;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.FifthActivity;
import com.example.appmobil.R;
import org.json.JSONObject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class VisitResult extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    TextView Usuario, Latitud, Longitud, Direccion, Fecha, Nombre, Contacto, Proposito, Comentario, Sitio ;
    Button  btnmail, btnback;
    String latitud, longitud, direccion, usuario, fecha, comentario, nombre, contacto, proposito, sitio;
    JsonRequest jrq;
    EditText Correo1, Correo2, Correo3;
    String Stringlatitud, Stringlongitud, Stringdireccion, Stringcomentario, Stringusuario, Stringcliente, Stringproposito, Stringsitio, Stringcontacto;
    String Stringmail0, Stringmail1, Stringmail2, Stringmail3;


    public VisitResult() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VisitResult newInstance(String param1, String param2) {
        VisitResult fragment = new VisitResult();
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
        View vista =  inflater.inflate(R.layout.fragment_visit_result, container, false);
        Usuario = vista.findViewById(R.id.txtuser_report);
        Latitud = vista.findViewById(R.id.txtlatitudreport);
        Longitud = vista.findViewById(R.id.txtlongitudreport);
        Direccion = vista.findViewById(R.id.txtdireccionreport);
        Fecha =vista.findViewById(R.id.txtfechareport);
        Nombre = vista.findViewById(R.id.txtnombrereport);
        Contacto = vista.findViewById(R.id.txtcontacto_report);;
        Proposito = vista.findViewById(R.id.txtpropositoreport);
        Comentario = vista.findViewById(R.id.txtcomentarioreport);
        Sitio = vista.findViewById(R.id.txtsitiovisitreport);
        btnmail = vista.findViewById(R.id.botoncorreoreport);
        Correo1 = vista.findViewById(R.id.txtmail);
        Correo2 = vista.findViewById(R.id.txtmail2);
        Correo3 = vista.findViewById(R.id.txtmail3);
        btnback = vista.findViewById(R.id.backvisitreport);
        Bundle bundle = new Bundle();
        bundle =getArguments();
        longitud = bundle.getString("longitud");
        latitud = bundle.getString("latitud");
        direccion = bundle.getString("direccion");
        usuario = bundle.getString("names");
        fecha = bundle.getString("fecha");
        nombre = bundle.getString("cliente");
        contacto = bundle.getString("contacto");
        comentario = bundle.getString("comentario");
        proposito = bundle.getString("proposito");
        sitio = bundle.getString("sitio");
        Usuario.setText(usuario);
        Latitud.setText(latitud);
        Longitud.setText(longitud);
        Direccion.setText(direccion);
        Fecha.setText(fecha);
        Nombre.setText(nombre);
        Contacto.setText(contacto);
        Proposito.setText(proposito);
        Comentario.setText(comentario);
        Sitio.setText(sitio);
        insertarvisita();
        buttonclic();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });

        return vista;
    }

    private void regresar() {
        Intent intent = new Intent(getContext(), FifthActivity.class);
        startActivity(intent);
        intent.putExtra(FifthActivity.nombres, Usuario.getText().toString());
        startActivity(intent);
    }

    private void insertarvisita(){
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/insertar_visitas.php?latitud="+ latitud + "&longitud=" + longitud +
                "&direccion=" + direccion + "&usuario=" + usuario + "&fecha=" + fecha +
                "&cliente=" + Nombre.getText().toString() + "&comentario=" + comentario + "&contacto=" + contacto + "&proposito=" + proposito + "&sitio=" + sitio;
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
        Toast.makeText(getContext(),"Visita ingresada correctamente" ,Toast.LENGTH_SHORT).show();
    }

    private void buttonclic() {
        btnmail.setOnClickListener((View v) -> {
            Stringlatitud = Latitud.getText().toString();
            Stringlongitud = Longitud.getText().toString();
            Stringdireccion = Direccion.getText().toString();
            Stringusuario = Usuario.getText().toString();
            Stringcliente = Nombre.getText().toString();
            Stringproposito = Proposito.getText().toString();
            Stringsitio = Sitio.getText().toString();
            Stringcontacto = Contacto.getText().toString();
            Stringcomentario = Comentario.getText().toString();
            Stringmail1 = Correo1.getText().toString().trim();
            Stringmail2 = Correo2.getText().toString().trim();
            Stringmail3 = Correo3.getText().toString().trim();
            try {
                //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
                sendmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void sendmail() throws AddressException {
        String subject = "\"******* Reporte de visita de:" + Stringusuario + "*******\"";
        String body =  "Datos del reporte: " + "\n" + " " + "\n" +
                "Usuario: " + Stringusuario + "\n" +
                "Cliente: " + Stringcliente + "\n" +
                "Contacto: " + Stringcontacto + "\n" +
                "Sitio: " + Stringsitio + "\n" +
                "Proposito: " + Stringproposito + "\n" +
                "Comentario: " + Stringcomentario + "\n" +
                "Latitud: " + Stringlatitud + "\n" +
                "Longitud: " + Stringlongitud + "\n" +
                "Direccion: " + Stringdireccion + "\n" +
                " Correo Generado desde CogesaAPP, favor no responder!!";
        Stringmail0 = "salvador.hernandez@generaldeequipos.com";
        String to = Stringmail0 + " , " + Stringmail1 + " , " + Stringmail2 + " , " + Stringmail3 ;
        InternetAddress[] parse = InternetAddress.parse(to,true);
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), subject, body, parse);
        javaMailAPI.execute();
    }
}