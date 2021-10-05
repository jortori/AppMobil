package com.example.appmobil;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.appmobil.Adapter.SQLiteHelper;
import com.example.appmobil.Adapter.VolleySingleton;
import com.example.appmobil.modelos.Customer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>{
    ArrayList<Customer> Clientes;
    JsonRequest jrq;
    private SQLiteHelper db;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setTitle("Accediendo a bases de datos...");
        progressDialog.setMessage("Accediendo a bases de datos...");
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setMax(10);
        progressDialog.show();
        db = new SQLiteHelper(this);
        db.borrarclientes();
        db.borracontactos();
        db.borrarsitios();
        iniciarsesion();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.Escenario, new SessionFragment()).commit();
    }

    private void iniciarsesion() {
        String ip = "http://23.253.109.115/prueba";
        String URL = ip+"/buscar_todos_clientes.php";
        URL = URL.replace(" ","%20");
        jrq = new JsonObjectRequest(Request.Method.GET, URL, null,this,this);
        VolleySingleton.getInstanciaVolley(this).addToRequestQueue(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Clientes = new ArrayList<Customer>();
            JSONArray jsonArray = response.getJSONArray("clientes");
            JSONObject clientes  = null;
            for (int i = 0; i< jsonArray.length(); i++) {
                clientes = jsonArray.getJSONObject(i);
                Customer Cust = new Customer();
                Cust.setCuno(clientes.optString("cuno"));
                Cust.setCunm(clientes.optString("cunm"));
                Cust.setPhone(clientes.optString("phno"));
                Cust.setCredit(clientes.optInt("crlmt"));
                Clientes.add(Cust);
                db.insetarcliente(Cust.getCuno().toString(),Cust.getCunm().toString(),Cust.getPhone().toString(),Cust.getCredit());
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();
    }
}
