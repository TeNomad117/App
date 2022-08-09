package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button btn_Scan;
    Button button;
    ImageView sinConexion;

    private RequestQueue myQueue;
    private static final int _SIGEM = 13;
    private static final int _SICAPAM = 11;
    private static final int _FieldHeight = 49;
    //private static String _regexQR = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myQueue = Volley.newRequestQueue(this);

        btn_Scan = findViewById(R.id.btn_scan); //boton escaneo
        sinConexion = findViewById(R.id.imagenSinConexion); //imagen para identificar la conexion
        sinConexion.setVisibility(View.INVISIBLE); //se oculta la imagen
        button = findViewById(R.id.button);

        //Boton flotante
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormPrueba.class);
                startActivity(intent);
            }
        });
        //Boton flotante

        //Validacion de conexion a internet
        ConnectivityManager con = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            FancyToast.makeText(this,"Conectado !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();//R.drawable.icon(Permite agregar un icono al mensaje)
        }else {
            sinConexion.setVisibility(View.VISIBLE);
            btn_Scan.setEnabled(false);

            FancyToast.makeText(this,"No Conectado !",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        }//Validacion de conexion a internet


        //Accion del boton
        btn_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Accion del escanner
                IntentIntegrator integrador = new IntentIntegrator(MainActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Decodificando Codigo QR");
                integrador.setCameraId(0);
                integrador.setOrientationLocked(false);
                integrador.setBeepEnabled(true);
                integrador.setCaptureActivity(CaptureActivityPortrait.class);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
                //Accion del escanner
            }
        });////Accion del boton
    }

    public void getQrVersion(String QRContent) {
        //QRContent="https://cp.semar.gob.mx/cp/des/Sicapam/ConsultaQr?c=MTgzNywyMDIxLDE4MTQ5OSxEZXNndWFjZQ==";
        System.out.println("Informacion del QR");
        System.out.println(QRContent);
        if (QRContent.contains("cp.semar.gob.mx")) {
            System.out.println("Contiene URL");
            String uri;

            System.out.println(QRContent.contains("|"));
            System.out.println(QRContent.contains("\\|"));
            System.out.println(QRContent.contains("||"));
            System.out.println(QRContent.lastIndexOf("|"));

            if (QRContent.contains("|")) {
                uri = QRContent.substring(QRContent.lastIndexOf("|"), QRContent.length());
            } else {
                uri = QRContent;
            }
            uri=uri.replace("|","").trim();
            Intent httpIntent = new Intent(Intent.ACTION_VIEW);
            httpIntent.setData(Uri.parse(uri));
            startActivity(httpIntent);

            /**
             * Rutina de
             * redireccionamiento
             */
            //6357623|hfsdgfgs|sahdghjdgs|kfkldjfklsd|ww.dfgsdhjfhdgfhj
            //fin de rutina
        } else {
            getsistema(QRContent);
        }
        /**
         * Validar regexp para Formato de QR Sigem
         */

        /**
         * Rutina para QR seguro
         */
        //dghasdgaj\jhgdjhagsdhjgsa\ghgdjhasgd\
    }

    //Resultado del escaneo
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result !=null){
            if(result.getContents() == null){
                FancyToast.makeText(this,"Escaneo Cancelado",FancyToast.LENGTH_LONG,FancyToast.CONFUSING,false).show();
            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                //txt_Resultado.setText(result.getContents());
                getQrVersion(result.getContents());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }//Resultado del escaneo

    //validacion del sistema
    public void getsistema(String result){
        String url;
        //Extracion del id del sistema
        System.out.println(result);
        byte [] _ainfo = Base64.decode(result,Base64.DEFAULT);
        String _sInfo = new String(_ainfo);
        if(_sInfo.startsWith(String.valueOf(_SIGEM))){
            url = "https://cp.semar.gob.mx/cp/des/Sigem/service/reporte/consultaSolQR?tkn="+result;
            System.out.println("Informacion valida!!");
            getRequest();
        } else if (_sInfo.startsWith(String.valueOf(_SICAPAM))){
            url = "https://cp.semar.gob.mx/cp/des/Sicapam/service/ws/resolveSecureQR?tkn="+result;
            System.out.println("Informacion valida!!");
        }else{
            System.out.println("Error de informacion!!");}
        //System.out.println("URL envio " + url);
    }//validacion del sistema

    //visualizacion de la informacion
    public void getJSON(View view) {
        getRequest();
    }//visualizacion de la informacion

    //Informacion del URL SIGEM
    private void getRequest(){
        String url = "https://cp.semar.gob.mx/cp/des/Sigem/service/reporte/consultaSolQR?tkn=MTN-0tPT04extby21q--wL6rr9awttPTztLQ0s7Lh83SzQ==";
        //Extracion del id del sistema
        byte [] _ainfo = Base64.decode("MTN-0tPT04extby21q--wL6rr9awttPTztLQ0s7Lh83SzQ==",Base64.URL_SAFE);
        String _sInfo = new String(_ainfo);
        System.out.println(_sInfo);
        System.out.println(_sInfo.startsWith("13"));

        //Extracion del id del sistema

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ViewGroup.LayoutParams _layoutParams;
                        try {
                            //Agregar mensaje de error si no trae datos
                            if (response.has("msj")) {

                                //System.out.println(response.getString("msj"));
                            }
                            System.out.println(response);
                            //Datos Expediente
                            JSONObject _exp = response.getJSONObject("expediente");
                            String Materno = _exp.getString("apMaterno");
                            String Paterno = _exp.getString("apPaterno");
                            String CURP = _exp.getString("curp");
                            String exp = _exp.getString("expediente");
                            String Nombre = _exp.getString("nombre");
                            String RFC = _exp.getString("rfc");
                            String Firma = _exp.getString("firma");
                            String Foto = _exp.getString("foto");
                            //Datos Expediente

                            //Envio de datos
                            Bundle SendData = new Bundle();
                            SendData.putString("name", Nombre.toString());
                            SendData.putString("apP", Paterno.toString());
                            SendData.putString("apM", Materno.toString());
                            SendData.putString("curp", CURP.toString());
                            SendData.putString("rfc", RFC.toString());
                            SendData.putString("exp", exp.toString());
                            SendData.putString("firma",Firma.toString());
                            SendData.putString("foto",Foto.toString());
                            //Envio de datos

                            // Datos Licencia
                            JSONObject _lic = _exp.getJSONObject("licencia");
                            String Fin = _lic.getString("dtFin");
                            String Inicio = _lic.getString("dtInicio");
                            String Impresion = _lic.getString("tsImpresion");
                            String CatPadre = _lic.getString("cCategPadre");
                            String Categoria = _lic.getString("cCategoria");
                            String Folio = _lic.getString("cFolio");
                            String Licencia = _lic.getString("cLicencia");
                            String Lugar = _lic.getString("cLugar");

                            SendData.putString("fin", Fin.toString());
                            SendData.putString("ini", Inicio.toString());
                            SendData.putString("imp", Impresion.toString());
                            SendData.putString("cpadre", CatPadre.toString());
                            SendData.putString("cat", Categoria);
                            SendData.putString("fol", Folio.toString());
                            SendData.putString("lic", Licencia.toString());
                            SendData.putString("lu", Lugar.toString());
                            Intent intent = new Intent(MainActivity.this, FSigem.class);
                            intent.putExtras(SendData);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        myQueue.add(request);
    }
    //controlar el boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode==event.KEYCODE_BACK){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desea salir del Scanner?")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
        return super.onKeyDown(keyCode, event);
    }
}