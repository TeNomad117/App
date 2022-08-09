package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FormPrueba extends AppCompatActivity {

    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_prueba);

        button2 = findViewById(R.id.button2);
    }
    //Metodo boton regresar
    public void onClick(View view){
        Intent r = new Intent(this, MainActivity.class);
        startActivity(r);
    }
}