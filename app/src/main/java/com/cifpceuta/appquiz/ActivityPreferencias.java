package com.cifpceuta.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.SwitchCompat;

public class ActivityPreferencias extends AppCompatActivity {

    private SwitchCompat randomPreguntas, randomRespuestas, mostrarRespuestas, activarCountdown;
    private Button btnSalirPreferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        randomPreguntas = findViewById(R.id.sw_random_preguntas);
        randomRespuestas = findViewById(R.id.sw_random_respuestas);
        mostrarRespuestas = findViewById(R.id.sw_mostrar_respuestas_correctas);
        activarCountdown = findViewById(R.id.sw_countdown_timer);
        btnSalirPreferencias = findViewById(R.id.btn_salir_preferencias);

        SharedPreferences prefs = getSharedPreferences("PreferenciasAppQuiz", MODE_PRIVATE);
        boolean isRandomPreguntas = prefs.getBoolean("isRandomPreguntas",false);
        if(isRandomPreguntas){
            randomPreguntas.setChecked(true);
        }
        boolean isRandomRespuestas = prefs.getBoolean("isRandomRespuestas",false);
        if(isRandomRespuestas){
            randomRespuestas.setChecked(true);
        }
        boolean isMostrarRespuestas = prefs.getBoolean("isMostrarRespuestas",false);
        if(isMostrarRespuestas){
            mostrarRespuestas.setChecked(true);
        }
        boolean isActivarCountdown = prefs.getBoolean("isActivarCountdown",false);
        if(isActivarCountdown){
            activarCountdown.setChecked(true);
        }
        btnSalirPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirPreferencias(v);
            }
        });

    }
    public void salirPreferencias(View v){
        SharedPreferences.Editor editor = getSharedPreferences("PreferenciasAppQuiz", MODE_PRIVATE).edit();
        editor.putBoolean("isRandomPreguntas",randomPreguntas.isChecked());
        editor.putBoolean("isRandomRespuestas",randomRespuestas.isChecked());
        editor.putBoolean("isMostrarRespuestas",mostrarRespuestas.isChecked());
        editor.putBoolean("isActivarCountdown",activarCountdown.isChecked());
        editor.apply();

        startActivity(new Intent(this, MainActivity.class));
    }
}