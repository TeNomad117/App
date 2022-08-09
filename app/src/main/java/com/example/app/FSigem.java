package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FSigem extends AppCompatActivity {

    private static final int LayoutHeight=10;
    private ViewGroup.LayoutParams _layoutParams;
    ImageView imageUser, imageFirma;
    TextView tNombre, tPaterno, tMaterno, tcurp, trfc, texp, tinicio, tfin, tfolio, timpresion, tlicencia, tCatPadre, tCategoria, tLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsigem);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageUser = findViewById(R.id.imageUser);
        imageFirma = findViewById(R.id.imageFirma);

        tNombre = findViewById(R.id.textView2);
        tPaterno = findViewById(R.id.textView3);
        tMaterno = findViewById(R.id.textView4);
        tcurp = findViewById(R.id.textView5);
        trfc = findViewById(R.id.textView6);
        texp = findViewById(R.id.textView7);
        tinicio = findViewById(R.id.textView8);
        tfin = findViewById(R.id.textView9);
        tfolio = findViewById(R.id.textView10);
        timpresion = findViewById(R.id.textView11);
        tlicencia = findViewById(R.id.textView13);
        tCatPadre = findViewById(R.id.textView16);
        tCategoria = findViewById(R.id.textView14);
        tLugar = findViewById(R.id.textView15);

        Bundle reciveData = getIntent().getExtras();
        String name = reciveData.getString("name");
        tNombre.setText("Nombre : " + reciveData.getString("name"));
        String apP = reciveData.getString("apP");
        tPaterno.setText("Paterno : " + reciveData.getString("apP"));
        String apM = reciveData.getString("apM");
        tMaterno.setText("Materno : " + reciveData.getString("apM"));
        String curp = reciveData.getString("curp");
        tcurp.setText("CURP : " + reciveData.getString("curp"));
        String rfc = reciveData.getString("rfc");
        trfc.setText("RFC : " + reciveData.getString("rfc"));
        String exp = reciveData.getString("exp");
        texp.setText("Expediente : " + reciveData.getString("exp"));
        //Foto
        String foto = reciveData.getString("foto");
        byte[] decodedString = Base64.decode(foto, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageUser.setImageBitmap(decodedByte);
        imageUser.setEnabled(false);
        //Firma
        System.out.println(reciveData.getString("firma"));
        String firma = reciveData.getString("firma");
        byte[] codeString = Base64.decode(firma, Base64.DEFAULT);
        Bitmap codeByte = BitmapFactory.decodeByteArray(codeString, 0, codeString.length);
        imageFirma.setImageBitmap(codeByte);
        imageFirma.setEnabled(false);

//        String ini = reciveData.getString("ini");
//        String fin = reciveData.getString("fin");
//        //String fol = reciveData.getString("fol");
//        String imp = reciveData.getString("imp");
//        String lic = reciveData.getString("lic");
        //String cpadre = reciveData.getString("cpadre");
        //String cat = reciveData.getString("cat");
        //String lu = reciveData.getString("lu");

        String fol = reciveData.getString("fol");

        tinicio.setText("Inicio : " + reciveData.getString("ini"));
        tfin.setText("Fin : " + reciveData.getString("fin"));
        _layoutParams=tfolio.getLayoutParams();
        if (fol==null || fol.trim().equals("") || fol.trim().equals("null")) {
            _layoutParams.height=0;
        } else {
            _layoutParams.height=FSigem.LayoutHeight;
        }
        tfolio.setLayoutParams(_layoutParams);
        tfolio.setText("Folio : " + reciveData.getString("fol"));
        timpresion.setText("Impresion : " + reciveData.getString("imp"));
        tlicencia.setText("Licencia : " + reciveData.getString("lic"));
        tCatPadre.setText("Categoria Padre : " + reciveData.getString("cpadre"));
        tCategoria.setText("Categoria : " + reciveData.getString("cat"));
        tLugar.setText("Lugar : " + reciveData.getString("lu"));

    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    //Metodo boton regresar
//    public void onClick(View view){
//        Intent r = new Intent(this, MainActivity.class);
//        startActivity(r);
//    }
}