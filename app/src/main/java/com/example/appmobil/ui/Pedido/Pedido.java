package com.example.appmobil.ui.Pedido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.JavaMailAPI;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.R;
import com.example.appmobil.modelos.Customer;
import com.example.appmobil.modelos.Product;
import com.example.appmobil.modelos.Sitio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Pedido extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    EditText Cant1, Cant2, Cant3, Cant4, Cant5, Cant6, Cant7, Cant8, Cant9, Cant10;
    TextView Usuario;
    Button btnRegistrar, btnRegresar;
    AutoCompleteTextView Cliente, Item1, Item2, Item3, Item4, Item5, Item6, Item7, Item8, Item9, Item10;
    ArrayList<Customer> clientes;
    ArrayList<String> listaproductos2, listaClientes;
    JsonRequest jrq;
    private SQLiteHelper db;
    ArrayList<Product> Productos2;
    String Stringmail1 = " ";
    String Stringmail2 = " ";
    String Stringmail3 = "salvador.hernandez@generaldeequipos.com";
    String Stringmail5 = "julio.guevara@generaldeequipos.com";
    String Stringmail6 = " ";
    String Stringmail7 = "jose.patino@generaldeequipos.com";
    String Stringmail8 = " ";
    String usuario;


    public static Pedido newInstance() {
        return new Pedido();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_order, container, false);
        Usuario = vista.findViewById(R.id.usuariocontacto);
        Item1 = vista.findViewById(R.id.txtitem1);
        Item2 = vista.findViewById(R.id.txtitem2);
        Item3 = vista.findViewById(R.id.txtitem3);
        Item4 = vista.findViewById(R.id.txtitem4);
        Item5 = vista.findViewById(R.id.txtitem5);
        Item6 = vista.findViewById(R.id.txtitem6);
        Item7 = vista.findViewById(R.id.txtitem7);
        Item8 = vista.findViewById(R.id.txtitem8);
        Item9 = vista.findViewById(R.id.txtitem9);
        Item10 = vista.findViewById(R.id.txtitem10);
        Cant1 = vista.findViewById(R.id.txcantidad1);
        Cant2 = vista.findViewById(R.id.txcantidad2);
        Cant3 = vista.findViewById(R.id.txcantidad3);
        Cant4 = vista.findViewById(R.id.txcantidad4);
        Cant5 = vista.findViewById(R.id.txcantidad5);
        Cant6 = vista.findViewById(R.id.txcantidad6);
        Cant7 = vista.findViewById(R.id.txcantidad7);
        Cant8 = vista.findViewById(R.id.txcantidad8);
        Cant9 = vista.findViewById(R.id.txcantidad9);
        Cant10 = vista.findViewById(R.id.txcantidad10);
        Bundle bundle =getArguments();
        usuario = bundle.getString("names");
        btnRegistrar = vista.findViewById(R.id.botonregistrarpedido);
        btnRegresar = vista.findViewById(R.id.botonregresarmenupedido);
        Cliente = vista.findViewById(R.id.txtclientepedido);
        db = new SQLiteHelper(getContext());
        Productos2 = new ArrayList<Product>();
        clientes = new ArrayList<Customer>();
        listaproductos2 = new ArrayList<String>();
        listaClientes = new ArrayList<>();
        Usuario.setText(usuario);
        iniciarsesion();
        Consultarlistaclientes();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendmail();
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            }
        });
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void iniciarsesion(){
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/buscar_productos.php?ds18= ";
        //URL = URL.replace(" ","%20");
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
            JSONArray jsonArray = response.getJSONArray("datos");
            JSONObject productos  = null;
            for (int i = 0; i< jsonArray.length(); i++) {
                productos = jsonArray.getJSONObject(i);
                Product Prods = new Product();
                Prods.setPano20(productos.optString("pano20"));                      //Encabezados de las columnas de la tabla de datos!
                Prods.setDs18(productos.optString("ds18"));
                Prods.setQyhnd(productos.optInt("on_hand"));
                Prods.setSos1(productos.optString("sos1"));
                Prods.setPres(productos.optString("pres"));
                Prods.setLob(productos.optString("lob"));
                Prods.setTipo(productos.optString("tipo"));
                Prods.setQyor(productos.optInt("on_order"));
                Productos2.add(Prods);
            }
            for (int j = 0 ; j<Productos2.size();j++){
                listaproductos2.add(Productos2.get(j).getDs18()+ " - " + Productos2.get(j).getPres() + " - " + Productos2.get(j).getPano20());
            }
            ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,listaproductos2);
            Item1.setAdapter(adaptador);
            Item2.setAdapter(adaptador);
            Item3.setAdapter(adaptador);
            Item4.setAdapter(adaptador);
            Item5.setAdapter(adaptador);
            Item6.setAdapter(adaptador);
            Item7.setAdapter(adaptador);
            Item8.setAdapter(adaptador);
            Item9.setAdapter(adaptador);
            Item10.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendmail() throws AddressException {
        String subject = "\"******* Pedido de lubricante generado por: " + Usuario.getText() + "*******\"";
        String body =  "DATOS DE PEDIDO: " + "\n" + " " + "\n" +
                "Cliente: " + Cliente.getText().toString() + "\n" + " " + "\n" +
                "Asesor: " + Usuario.getText().toString() + "\n" + " " + "\n" +
                " **************** ITEMS A FACTURAR **************** " + "\n" +
                "Item1: " + Item1.getText().toString() + "  Cantidad: " + Cant1.getText().toString() + "\n" +
                "Item2: " + Item2.getText().toString() + "  Cantidad: " + Cant2.getText().toString() + "\n" +
                "Item3: " + Item3.getText().toString() + "  Cantidad: " + Cant3.getText().toString() + "\n" +
                "Item4: " + Item4.getText().toString() + "  Cantidad: " + Cant4.getText().toString() + "\n" +
                "Item5: " + Item5.getText().toString() + "  Cantidad: " + Cant5.getText().toString() + "\n" +
                "Item6: " + Item6.getText().toString() + "  Cantidad: " + Cant6.getText().toString() + "\n" +
                "Item7: " + Item7.getText().toString() + "  Cantidad: " + Cant7.getText().toString() + "\n" +
                "Item8: " + Item8.getText().toString() + "  Cantidad: " + Cant8.getText().toString() + "\n" +
                "Item9: " + Item9.getText().toString() + "  Cantidad: " + Cant9.getText().toString() + "\n" +
                "Item10: " + Item10.getText().toString() + " Cantidad: " + Cant10.getText().toString() + "\n" + " " + "\n" +
                " Correo Generado desde CogesaAPP, favor no responder!!";
        String to = Stringmail1 + " , " + Stringmail2 + " , " + Stringmail3 + " , "  + Stringmail5 +  " , "  + Stringmail6 + " , "+ Stringmail7 + " , "+ Stringmail8;
        InternetAddress[] parse = InternetAddress.parse(to,true);
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), subject, body, parse);
        javaMailAPI.execute();
    }

    private void Consultarlistaclientes() {
        clientes  = db.getCustomers();

        for (int i = 0 ; i<clientes.size();i++){
            listaClientes.add(clientes.get(i).getCuno() + " - " + clientes.get(i).getCunm());
        }
        ArrayAdapter<CharSequence> adaptador  = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,listaClientes);
        Cliente.setAdapter(adaptador);
    }
}