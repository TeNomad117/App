package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class FSicapam extends AppCompatActivity {

    private static final int LayoutHeight=10;
    private ViewGroup.LayoutParams _layoutParams;

    TextView tNumsolicitud, tEjercicio, tCobes, tCadenafirmado, tNomembarcacion, tVehiculo,
            tModulo, tOficina, tRegistro, tHolograma, tPermiso, tInicio, tFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsicapam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        int _fieldHeight=0;

        tNumsolicitud = findViewById(R.id.textView2);
        tEjercicio = findViewById(R.id.textView3);
        tCobes = findViewById(R.id.textView4);
        tCadenafirmado = findViewById(R.id.textView5);
        tNomembarcacion = findViewById(R.id.textView6);
        tVehiculo = findViewById(R.id.textView7);
        tModulo = findViewById(R.id.textView8);
        tOficina = findViewById(R.id.textView9);
        tRegistro = findViewById(R.id.textView10);
        tHolograma = findViewById(R.id.textView11);
        tPermiso = findViewById(R.id.textView14);
        tInicio = findViewById(R.id.textView15);
        tFin = findViewById(R.id.textView16);

        Bundle reciveData = getIntent().getExtras();
        String Solicitud = reciveData.getString("numsol");
        String Ejercicio = reciveData.getString("iejer");
        String Documento = reciveData.getString("cobs");
        String Electronica = reciveData.getString("cadfirm");
        String Embarcacion = reciveData.getString("nomemb");
        String ID = reciveData.getString("ivehi");
        String Modulo = reciveData.getString("cmod");
        String Oficina = reciveData.getString("cofi");
        String Emision = reciveData.getString("emisi");
        String Holograma = reciveData.getString("holo");
        String Servicio = reciveData.getString("permi");
        String Inicio = reciveData.getString("ini");
        String Fin = reciveData.getString("fin");

        //String ID = reciveData.getString("ivehi");
        /**
         * Bloque ID
         */
        _layoutParams=tVehiculo.getLayoutParams();
        _fieldHeight=_layoutParams.height;
        if (ID==null || ID.trim().equals("") || ID.trim().equals("null")) {
            _layoutParams.height=0;
        } else {
            _layoutParams.height=_fieldHeight;
        }
        tVehiculo.setLayoutParams(_layoutParams);
        tVehiculo.setText(ID);
        /**
         * Fin Bloque ID
         */

        /**
         * Bloque Holo
         */
        _layoutParams=tHolograma.getLayoutParams();
        _fieldHeight=_layoutParams.height;
        if (Holograma==null || Holograma.trim().equals("") || Holograma.trim().equals("null")) {
            _layoutParams.height=0;
        } else {
            _layoutParams.height=_fieldHeight;
        }
        tHolograma.setLayoutParams(_layoutParams);
        tHolograma.setText(Holograma);
        /**
         * Fin Bloque Holo
         */

        /**
         * Bloque Holo
         */
        _layoutParams=tPermiso.getLayoutParams();
        _fieldHeight=_layoutParams.height;
        if (Servicio==null || Servicio.trim().equals("") || Servicio.trim().equals("null")) {
            _layoutParams.height=0;
        } else {
            _layoutParams.height=_fieldHeight;
        }
        tPermiso.setLayoutParams(_layoutParams);
        tPermiso    .setText(Servicio);
        /**
         * Fin Bloque Holo
         */





        tNumsolicitud.setText(reciveData.getString("numsol"));
        tEjercicio.setText(reciveData.getString("iejer"));
        tCobes.setText(reciveData.getString("cobs"));
        tCadenafirmado.setText(reciveData.getString("cadfirm"));
        tNomembarcacion.setText(reciveData.getString("nomemb"));

        tModulo.setText("Modulo : " + reciveData.getString("cmod"));
        tRegistro.setText(reciveData.getString("emisi"));
        tOficina.setText(reciveData.getString("cofi"));
        tHolograma.setText(reciveData.getString("holo"));
        tPermiso.setText("Servicio : " + reciveData.getString("permi"));
        tInicio.setText("Inicio : " + reciveData.getString("ini"));
        tFin.setText("Fin : " + reciveData.getString("fin"));
    }

}