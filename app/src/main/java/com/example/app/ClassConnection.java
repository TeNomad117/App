package com.example.app;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ClassConnection extends AsyncTask <String,String,String>{//parametros

    @Override //trabajo en segundo plano
    protected String doInBackground(String... strings){
        HttpURLConnection httpURLConnection=null; //objeto que hara la conexion
        URL url = null; //objeto url donde se alojan los datos a descargar
        try {
            url = new URL(strings[0]); //se agrega el parametro //verifiar el parametro
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection(); //abre la conexion
            httpURLConnection.connect(); //Se hace la Conexion
            int code = httpURLConnection.getResponseCode(); //Respuesta codigo que manda del resultado
            if(code == HttpURLConnection.HTTP_OK){ //
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream()); //se guarda la respuesta en in
                BufferedReader reader = new BufferedReader(new InputStreamReader(in)); //trasnformar los datos
                String line = ""; //Leera cada linea de las cadena la alamacenara en line
                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine())!=null){
                    buffer.append(line);
                }
                return buffer.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
