package com.example.appmobil.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appmobil.modelos.Actividad;
import com.example.appmobil.modelos.Contact;
import com.example.appmobil.modelos.Customer;
import com.example.appmobil.modelos.Event;
import com.example.appmobil.modelos.Profesion;
import com.example.appmobil.modelos.Purpose;
import com.example.appmobil.modelos.Schedule;
import com.example.appmobil.modelos.Sites;
import com.example.appmobil.modelos.Sitio;
import com.example.appmobil.modelos.Tracking;
import com.example.appmobil.modelos.Visit;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper  {
    //INSTRUCCIONES PARA CREAR TABLAS EN BASE DE DATOS.
    //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
    private static final String VISITS_TABLE_CREATE = "CREATE TABLE Visits(latitud TEXT, longitud TEXT, direccion TEXT, usuario TEXT, fecha TEXT, cliente TEXT, comentario TEXT )";
    private static final String TRACKING_TABLE_CREATE = "CREATE TABLE Tracking(latitud TEXT, longitud TEXT, direccion TEXT, usuario TEXT, fecha TEXT)";
    private static final String CUSTOMER_TABLE_CREATE = "CREATE TABLE Customers(cuno TEXT, cunm TEXT, phone TEXT, credit INT)";
    private static final String PURPOSE_TABLE_CREATE = "CREATE TABLE Purposes(id_purpose TEXT, purpose TEXT)";
    private static final String CONTACT_TABLE_CREATE = "CREATE TABLE Contacts(cuno TEXT, profesion TEXT, firstname TEXT, secondname TEXT, lastname1 TEXT, lastname2 TEXT, cargo TEXT, phone TEXT, cel TEXT, email TEXT)";
    private static final String PROFESION_TABLE_CREATE = "CREATE TABLE Profesion(id TEXT, profesion TEXT)";
    private static final String SITES_TABLE_CREATE = "CREATE TABLE Sites(id TEXT, site TEXT)";
    private static final String SITIOS_TABLE_CREATE = "CREATE TABLE Sitios(id_sitio TEXT, cuno TEXT, nombre_sitio TEXT, direccion_sitio TEXT, latitud_sitio TEXT, longitud_sitio TEXT)";
    private static final String ACTIVITIES_TABLE_CREATE = "CREATE TABLE Activities(id TEXT, Activity TEXT)";
    private static final String EVENTS_TABLE_CREATE = "CREATE TABLE Events(id TEXT, Event TEXT)";
    private static final String SCHEDULE_TABLE_CREATE = "CREATE TABLE Schedule(usuario TEXT, cliente TEXT, actividad TEXT, fecha TEXT, comentario TEXT)";
    private static final String PURPOSE_TABLE_INSERT1 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Relación - Cortesía')";
    private static final String PURPOSE_TABLE_INSERT2 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Nuevas oportunidades')";
    private static final String PURPOSE_TABLE_INSERT3 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Identificar necesidades')";
    private static final String PURPOSE_TABLE_INSERT4 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Nuevos productos - cambios')";
    private static final String PURPOSE_TABLE_INSERT5 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Propuesta de negocio')";
    private static final String PURPOSE_TABLE_INSERT6 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Precios')";
    private static final String PURPOSE_TABLE_INSERT7 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Entrega de producto')";
    private static final String PURPOSE_TABLE_INSERT8 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Revisar inventario')";
    private static final String PURPOSE_TABLE_INSERT9 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Reemplazo de averias')";
    private static final String PURPOSE_TABLE_INSERT10 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('VENTAS','Toma de pedidos')";
    private static final String PURPOSE_TABLE_INSERT11 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('MERCADEO','Propuesta de apoyo mercadeo')";
    private static final String PURPOSE_TABLE_INSERT12 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('MERCADEO','Entrega promocionales')";
    private static final String PURPOSE_TABLE_INSERT13 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('MERCADEO','Acciones de competencia')";
    private static final String PURPOSE_TABLE_INSERT14 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('CREDITOS','Tramite de quedan')";
    private static final String PURPOSE_TABLE_INSERT15 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('CREDITOS','Tramite de orden de compra')";
    private static final String PURPOSE_TABLE_INSERT16 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('CREDITOS','Firma de documentos')";
    private static final String PURPOSE_TABLE_INSERT17 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('CREDITOS','Cobro factura')";
    private static final String PURPOSE_TABLE_INSERT18 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('INGENIERIA','Propuesta prueba desempeño')";
    private static final String PURPOSE_TABLE_INSERT19 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('INGENIERIA','Propuesta estudio de planta')";
    private static final String PURPOSE_TABLE_INSERT20 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('INGENIERIA','Conocimiento competencia')";
    private static final String PURPOSE_TABLE_INSERT21 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('INGENIERIA','Retirar muestras')";
    private static final String PURPOSE_TABLE_INSERT22 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('INGENIERIA','Atencion quejas')";
    private static final String PURPOSE_TABLE_INSERT23 = "INSERT INTO  Purposes (id_purpose, purpose) VALUES ('INGENIERIA','Entrega de reporte POP')";
    private static final String PROFESION_TABLE_INSERT1 = "INSERT INTO  Profesion (id, profesion) VALUES ('1','Ing.')";
    private static final String PROFESION_TABLE_INSERT2 = "INSERT INTO  Profesion (id, profesion) VALUES ('2','Lic.')";
    private static final String PROFESION_TABLE_INSERT3 = "INSERT INTO  Profesion (id, profesion) VALUES ('3','Sr.')";
    private static final String PROFESION_TABLE_INSERT4 = "INSERT INTO  Profesion (id, profesion) VALUES ('4','Sra.')";
    private static final String SITES_TABLE_INSERT1 = "INSERT INTO  Sites (id, site) VALUES ('1','Ofi')";
    private static final String SITES_TABLE_INSERT2 = "INSERT INTO  Sites (id, site) VALUES ('2','Pla')";
    private static final String SITES_TABLE_INSERT3 = "INSERT INTO  Sites (id, site) VALUES ('3','Pro')";
    private static final String SITES_TABLE_INSERT4 = "INSERT INTO  Sites (id, site) VALUES ('4','Suc')";
    private static final String SITES_TABLE_INSERT5 = "INSERT INTO  Sites (id, site) VALUES ('5','Mat')";
    private static final String ACTIVITIES_TABLE_INSERT1 = "INSERT INTO  Activities (id, Activity) VALUES ('1','Visita de campo')";
    private static final String ACTIVITIES_TABLE_INSERT2 = "INSERT INTO  Activities (id, Activity) VALUES ('2','Llamada de seguimiento')";
    private static final String ACTIVITIES_TABLE_INSERT3 = "INSERT INTO  Activities (id, Activity) VALUES ('3','Inspeccion de equipo')";
    private static final String ACTIVITIES_TABLE_INSERT4 = "INSERT INTO  Activities (id, Activity) VALUES ('4','Visita para entrega de productos')";
    private static final String ACTIVITIES_TABLE_INSERT5 = "INSERT INTO  Activities (id, Activity) VALUES ('5','Visita para identificacion de necesidades')";
    private static final String EVENTS_TABLE_INSERT1 = "INSERT INTO  Events (id, Event) VALUES ('1','REUNIONES - Virtual con cliente')";
    private static final String EVENTS_TABLE_INSERT2 = "INSERT INTO  Events (id, Event) VALUES ('2','REUNIONES - Mejorar precio oferta repuestos')";
    private static final String EVENTS_TABLE_INSERT3 = "INSERT INTO  Events (id, Event) VALUES ('3','REUNIONES - Mejorar precio oferta servicio')";
    private static final String EVENTS_TABLE_INSERT4 = "INSERT INTO  Events (id, Event) VALUES ('4','REUNIONES - Presencial con cliente')";
    private static final String EVENTS_TABLE_INSERT5 = "INSERT INTO  Events (id, Event) VALUES ('5','REUNIONES - Revision de metas')";
    private static final String EVENTS_TABLE_INSERT6 = "INSERT INTO  Events (id, Event) VALUES ('6','REUNIONES - Presencial con cliente en taller')";
    private static final String EVENTS_TABLE_INSERT7 = "INSERT INTO  Events (id, Event) VALUES ('7','SEGUIMIENTO - CRM')";
    private static final String EVENTS_TABLE_INSERT8 = "INSERT INTO  Events (id, Event) VALUES ('8','SEGUIMIENTO - Programacion de servicios taller')";
    private static final String EVENTS_TABLE_INSERT9 = "INSERT INTO  Events (id, Event) VALUES ('9','CAPACITACIONES - Presencial en COGESA')";
    private static final String EVENTS_TABLE_INSERT10 = "INSERT INTO  Events (id, Event) VALUES ('10','CAPACITACIONES - En Linea')";
    private static final String EVENTS_TABLE_INSERT11 = "INSERT INTO  Events (id, Event) VALUES ('11','CAPACITACIONES - Fuera de COGESA')";
    private static final String EVENTS_TABLE_INSERT12 = "INSERT INTO  Events (id, Event) VALUES ('12','INGRESAR VISITA A DBS')";
    private static final String EVENTS_TABLE_INSERT13 = "INSERT INTO  Events (id, Event) VALUES ('13','ELABORAR COTIZACION DE OFERTA')";
    private static final String EVENTS_TABLE_INSERT14 = "INSERT INTO  Events (id, Event) VALUES ('14','GESTION CREDITOS - Condicion crediticia del cliente')";
    private static final String EVENTS_TABLE_INSERT15 = "INSERT INTO  Events (id, Event) VALUES ('15','GESTION CREDITOS - Requerimiento de credito')";
    private static final String EVENTS_TABLE_INSERT16 = "INSERT INTO  Events (id, Event) VALUES ('16','GESTION CREDITOS - Entrega de cheques o págarés.')";
    private static final String EVENTS_TABLE_INSERT17 = "INSERT INTO  Events (id, Event) VALUES ('17','LLAMADAS TELEFONICAS - Discusion de ofertas')";
    private static final String EVENTS_TABLE_INSERT18 = "INSERT INTO  Events (id, Event) VALUES ('18','LLAMADAS TELEFONICAS - Seguimiento de fallas.')";
    private static final String EVENTS_TABLE_INSERT19 = "INSERT INTO  Events (id, Event) VALUES ('19','PREPARAR ENTREGA DE PRODUCTOS A CLIENTES.')";
    private static final String EVENTS_TABLE_INSERT20 = "INSERT INTO  Events (id, Event) VALUES ('20','ENTREGA DE VEHICULOS PARA MANTENIMIENTO')";
    private static final String EVENTS_TABLE_INSERT21 = "INSERT INTO  Events (id, Event) VALUES ('21','PERMISOS - Personal')";
    private static final String EVENTS_TABLE_INSERT22 = "INSERT INTO  Events (id, Event) VALUES ('22','PERMISOS - ISSS')";
    private static final String EVENTS_TABLE_INSERT23 = "INSERT INTO  Events (id, Event) VALUES ('23','DEMOSTRACION DE PRODUCTOS')";
    private static final String CUSTOMERS_TABLE_TRUNCATE = "DELETE FROM  Customers";
    private static final String CONTACTS_TABLE_TRUNCATE = "DELETE FROM  Contacts";
    public static final String DB_NAME = "Visits.sqlite";
    public static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    //Abrimos la base de datos
    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    //Metodo para crear la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VISITS_TABLE_CREATE);
        db.execSQL(TRACKING_TABLE_CREATE);
       // db.execSQL(CUSTOMERS_TABLE_TRUNCATE);
        // db.execSQL(CONTACTS_TABLE_TRUNCATE);
        db.execSQL(PROFESION_TABLE_CREATE);
        db.execSQL(SITIOS_TABLE_CREATE);
        db.execSQL(SITES_TABLE_CREATE);
        db.execSQL(ACTIVITIES_TABLE_CREATE);
        db.execSQL(EVENTS_TABLE_CREATE);
        db.execSQL(SCHEDULE_TABLE_CREATE);
        db.execSQL(SITES_TABLE_INSERT1);
        db.execSQL(SITES_TABLE_INSERT2);
        db.execSQL(SITES_TABLE_INSERT3);
        db.execSQL(SITES_TABLE_INSERT4);
        db.execSQL(SITES_TABLE_INSERT5);
        db.execSQL(CUSTOMER_TABLE_CREATE);
        db.execSQL(PURPOSE_TABLE_CREATE);
        db.execSQL(CONTACT_TABLE_CREATE);
        db.execSQL(PURPOSE_TABLE_INSERT1);
        db.execSQL(PURPOSE_TABLE_INSERT2);
        db.execSQL(PURPOSE_TABLE_INSERT3);
        db.execSQL(PURPOSE_TABLE_INSERT4);
        db.execSQL(PROFESION_TABLE_INSERT1);
        db.execSQL(PROFESION_TABLE_INSERT2);
        db.execSQL(PROFESION_TABLE_INSERT3);
        db.execSQL(PROFESION_TABLE_INSERT4);
        db.execSQL(PURPOSE_TABLE_INSERT5);
        db.execSQL(PURPOSE_TABLE_INSERT6);
        db.execSQL(PURPOSE_TABLE_INSERT7);
        db.execSQL(PURPOSE_TABLE_INSERT8);
        db.execSQL(PURPOSE_TABLE_INSERT9);
        db.execSQL(PURPOSE_TABLE_INSERT10);
        db.execSQL(PURPOSE_TABLE_INSERT11);
        db.execSQL(PURPOSE_TABLE_INSERT12);
        db.execSQL(PURPOSE_TABLE_INSERT13);
        db.execSQL(PURPOSE_TABLE_INSERT14);
        db.execSQL(PURPOSE_TABLE_INSERT15);
        db.execSQL(PURPOSE_TABLE_INSERT16);
        db.execSQL(PURPOSE_TABLE_INSERT17);
        db.execSQL(PURPOSE_TABLE_INSERT18);
        db.execSQL(PURPOSE_TABLE_INSERT19);
        db.execSQL(PURPOSE_TABLE_INSERT20);
        db.execSQL(PURPOSE_TABLE_INSERT21);
        db.execSQL(PURPOSE_TABLE_INSERT22);
        db.execSQL(PURPOSE_TABLE_INSERT23);
        db.execSQL(ACTIVITIES_TABLE_INSERT1);
        db.execSQL(ACTIVITIES_TABLE_INSERT2);
        db.execSQL(ACTIVITIES_TABLE_INSERT3);
        db.execSQL(ACTIVITIES_TABLE_INSERT4);
        db.execSQL(ACTIVITIES_TABLE_INSERT5);
        db.execSQL(EVENTS_TABLE_INSERT1);
        db.execSQL(EVENTS_TABLE_INSERT2);
        db.execSQL(EVENTS_TABLE_INSERT3);
        db.execSQL(EVENTS_TABLE_INSERT4);
        db.execSQL(EVENTS_TABLE_INSERT5);
        db.execSQL(EVENTS_TABLE_INSERT6);
        db.execSQL(EVENTS_TABLE_INSERT7);
        db.execSQL(EVENTS_TABLE_INSERT8);
        db.execSQL(EVENTS_TABLE_INSERT9);
        db.execSQL(EVENTS_TABLE_INSERT10);
        db.execSQL(EVENTS_TABLE_INSERT11);
        db.execSQL(EVENTS_TABLE_INSERT12);
        db.execSQL(EVENTS_TABLE_INSERT13);
        db.execSQL(EVENTS_TABLE_INSERT14);
        db.execSQL(EVENTS_TABLE_INSERT15);
        db.execSQL(EVENTS_TABLE_INSERT16);
        db.execSQL(EVENTS_TABLE_INSERT17);
        db.execSQL(EVENTS_TABLE_INSERT18);
        db.execSQL(EVENTS_TABLE_INSERT19);
        db.execSQL(EVENTS_TABLE_INSERT20);
        db.execSQL(EVENTS_TABLE_INSERT21);
        db.execSQL(EVENTS_TABLE_INSERT22);
        db.execSQL(EVENTS_TABLE_INSERT23);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Insertar una nueva visita
    public void insertar(String latitud, String longitud, String direccion, String usuario, String fecha, String cliente, String comentario) {
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        ContentValues cv = new ContentValues();
        cv.put("latitud", latitud);
        cv.put("longitud", longitud);
        cv.put("direccion", direccion);
        cv.put("usuario", usuario);
        cv.put("fecha", fecha);
        cv.put("cliente", cliente);
        cv.put("comentario", comentario);
        db.insert("Visits", null, cv);
    }

    //Borrar un comentario a partir de su id
    public void borrarcustomers(int id) {
        String[] args = new String[]{String.valueOf(id)};
        db.delete("Visits", "_id=?", args);
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Visit> getComments() {
        ArrayList<Visit> lista = new ArrayList<Visit>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  latitud, longitud, direccion, usuario, fecha, cliente, comentario from Visits", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String latitud = c.getString(c.getColumnIndex("latitud"));
                String longitud = c.getString(c.getColumnIndex("longitud"));
                String direccion = c.getString(c.getColumnIndex("direccion"));
                String usuario = c.getString(c.getColumnIndex("usuario"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String cliente = c.getString(c.getColumnIndex("cliente"));
                String comentario = c.getString(c.getColumnIndex("comentario"));
                Visit com = new Visit(latitud, longitud, direccion, usuario, fecha, cliente, comentario);
                lista.add(com);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public void insertartracking( String latitud, String longitud, String direccion, String usuario, String fecha) {
        ContentValues cv = new ContentValues();
        cv.put("latitud", latitud);
        cv.put("longitud", longitud);
        cv.put("direccion", direccion);
        cv.put("usuario", usuario);
        cv.put("fecha", fecha);
        db.insert("Tracking", null, cv);
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Tracking> getTracking() {
        ArrayList<Tracking> lista = new ArrayList<Tracking>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  latitud, longitud, direccion, usuario, fecha from Tracking", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String latitud = c.getString(c.getColumnIndex("latitud"));
                String longitud = c.getString(c.getColumnIndex("longitud"));
                String direccion = c.getString(c.getColumnIndex("direccion"));
                String usuario = c.getString(c.getColumnIndex("usuario"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                Tracking com = new Tracking(latitud, longitud, direccion, usuario, fecha);
                lista.add(com);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> lista = new ArrayList<Customer>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  cuno, cunm, phone, credit  from Customers", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String cuno = c.getString(c.getColumnIndex("cuno"));
                String cunm = c.getString(c.getColumnIndex("cunm"));
                String phone = c.getString(c.getColumnIndex("phone"));
                int credit = c.getInt(c.getColumnIndex("credit"));
                Customer com = new Customer(cuno, cunm, phone, credit);
                lista.add(com);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public void insetarcliente( String cuno, String cunm, String phone, int credit) {
        ContentValues cv = new ContentValues();
        cv.put("cuno", cuno);
        cv.put("cunm", cunm);
        cv.put("phone", phone);
        cv.put("credit", credit);
        db.insert("Customers", null, cv);
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Purpose> getPurpose() {
        ArrayList<Purpose> lista = new ArrayList<Purpose>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  id_purpose, purpose  from Purposes", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id_purpose = c.getString(c.getColumnIndex("id_purpose"));
                String purpose = c.getString(c.getColumnIndex("purpose"));
                Purpose com = new Purpose(id_purpose, purpose);
                lista.add(com);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public void insertarcontacto( String cuno, String profesion, String firstname, String secondname, String lastname1, String lastname2, String cargo, String phone, String cel, String email) {
        ContentValues cv = new ContentValues();
        cv.put("cuno", cuno);
        cv.put("profesion", profesion);
        cv.put("firstname", firstname);
        cv.put("secondname", secondname);
        cv.put("lastname1", lastname1);
        cv.put("lastname2", lastname2);
        cv.put("cargo", cargo);
        cv.put("phone", phone);
        cv.put("cel", cel);
        cv.put("email", email);
        db.insert("Contacts", null, cv);
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> lista = new ArrayList<Contact>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  cuno, profesion, firstname, secondname, lastname1, lastname2, cargo, phone, cel, email  from Contacts", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String cuno = c.getString(c.getColumnIndex("cuno"));
                String profesion = c.getString(c.getColumnIndex("profesion"));
                String firstname = c.getString(c.getColumnIndex("firstname"));
                String secondname = c.getString(c.getColumnIndex("secondname"));
                String lastname1 = c.getString(c.getColumnIndex("lastname1"));
                String lastname2 = c.getString(c.getColumnIndex("lastname2"));
                String cargo = c.getString(c.getColumnIndex("cargo"));
                String phone = c.getString(c.getColumnIndex("phone"));
                String cel = c.getString(c.getColumnIndex("cel"));
                String email = c.getString(c.getColumnIndex("email"));
                Contact  com = new Contact(cuno, profesion, firstname, secondname, lastname1, lastname2, cargo, phone, cel, email);
                lista.add(com);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Profesion> getProfesion() {
        ArrayList<Profesion> lista = new ArrayList<Profesion>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  id, profesion  from Profesion", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex("id"));
                String profesion = c.getString(c.getColumnIndex("profesion"));
                Profesion prof = new Profesion(id, profesion);
                lista.add(prof);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Sites> getSites() {
        ArrayList<Sites> lista = new ArrayList<Sites>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  id, site  from Sites", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex("id"));
                String site = c.getString(c.getColumnIndex("site"));
                Sites sit = new Sites(id, site);
                lista.add(sit);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public  void borrarclientes(){
        String  DELETE_CUSTOMERS = "DELETE FROM Customers where cuno <> ' '";
        db.execSQL(DELETE_CUSTOMERS);
    }

    public  void borracontactos(){
        String  DELETE_CONTACTS = "DELETE FROM Contacts where cuno <> ' '";
        db.execSQL(DELETE_CONTACTS);
    }

    public  void borrarsitios(){
        String  DELETE_SITES = "DELETE FROM Sitios where cuno <> ' '";
        db.execSQL(DELETE_SITES);
    }

    public void insertarsitios( String id_sitio, String cuno, String nombre_sitio, String direccion_sitio, String latitud_sitio, String longitud_sitio) {
        ContentValues cv = new ContentValues();
        cv.put("id_sitio", id_sitio);
        cv.put("cuno", cuno);
        cv.put("nombre_sitio", nombre_sitio);
        cv.put("direccion_sitio", direccion_sitio);
        cv.put("latitud_sitio", latitud_sitio);
        cv.put("longitud_sitio", longitud_sitio);
        db.insert("Sitios", null, cv);
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Sitio> getSitios() {
        ArrayList<Sitio> lista = new ArrayList<Sitio>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  id_sitio, cuno, nombre_sitio, direccion_sitio, latitud_sitio, longitud_sitio  from Sitios", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id_sitio = c.getString(c.getColumnIndex("id_sitio"));
                String cuno = c.getString(c.getColumnIndex("cuno"));
                String nombre_sitio = c.getString(c.getColumnIndex("nombre_sitio"));
                String direccion_sitio = c.getString(c.getColumnIndex("direccion_sitio"));
                String latitud_sitio = c.getString(c.getColumnIndex("latitud_sitio"));
                String longitud_sitio = c.getString(c.getColumnIndex("longitud_sitio"));
                Sitio  com = new Sitio(id_sitio, cuno, nombre_sitio, direccion_sitio, latitud_sitio, longitud_sitio);
                lista.add(com);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Actividad> getActividades() {
        ArrayList<Actividad> lista = new ArrayList<Actividad>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  id, Activity  from Activities", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex("id"));
                String activity = c.getString(c.getColumnIndex("Activity"));
                Actividad actividad = new Actividad(id, activity);
                lista.add(actividad);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Event> getEventos() {
        ArrayList<Event> lista = new ArrayList<Event>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  id, Event  from Events", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex("id"));
                String event = c.getString(c.getColumnIndex("Event"));
                Event evento = new Event(id, event);
                lista.add(evento);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    //Obtener la lista de visitas ingresadas
    public ArrayList<Schedule> getSchedule() {
        ArrayList<Schedule> lista = new ArrayList<Schedule>();
        //String latitud, longitud, direccion, usuario, fecha, cliente, comentario;
        Cursor c = db.rawQuery("select  usuario, cliente, actividad, fecha, comentario  from Schedule", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String usuario = c.getString(c.getColumnIndex("usuario"));
                String cliente = c.getString(c.getColumnIndex("cliente"));
                String actividad = c.getString(c.getColumnIndex("actividad"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String comentario = c.getString(c.getColumnIndex("comentario"));
                Schedule schedule = new Schedule(usuario, cliente, actividad, fecha, comentario);
                lista.add(schedule);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public void insertarschedule( String usuario, String cliente, String actividad, String fecha, String comentario) {
        ContentValues cv = new ContentValues();
        cv.put("usuario", usuario);
        cv.put("cliente", cliente);
        cv.put("actividad", actividad);
        cv.put("fecha", fecha);
        cv.put("comentario", comentario);
        db.insert("Schedule", null, cv);
    }
}
