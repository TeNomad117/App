package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class FormPrueba extends AppCompatActivity {

    ViewFlipper flipperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_prueba);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int images[] = {
          R.drawable.p1,
          R.drawable.p2,
          R.drawable.p3,
          R.drawable.p4,
          R.drawable.p5,
          R.drawable.p6,
          R.drawable.p7,
          R.drawable.p8,
          R.drawable.p9,
          R.drawable.p10,
          R.drawable.p11,
          R.drawable.p12,
          R.drawable.p13,
          R.drawable.p14,
          R.drawable.p15,
          R.drawable.p16,
          R.drawable.p17,
          R.drawable.p18,
          R.drawable.p19,};
        flipperLayout = findViewById(R.id.flipper);
        for(int image: images){
            flipperImages(image);
        }
    }
    //Configuracion carrucel
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        flipperLayout.addView(imageView);
        flipperLayout.setFlipInterval(2000);//duracion de cada imagen
        flipperLayout.setAutoStart(true);

        //activacion de animacion
        flipperLayout.setInAnimation(this, android.R.anim.slide_out_right);
        flipperLayout.setInAnimation(this, android.R.anim.slide_in_left);
        //activacion de animacion

    }//Configuracion carrucel

}