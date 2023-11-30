package com.cifpceuta.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class ResumenResultados extends AppCompatActivity {
    private ArrayList<Pregunta> preguntas = new ArrayList<>();
    private ArrayList<Respuesta> respuestasDadas = new ArrayList<>();
    private int nPreguntas;
    private ListView lvResultados;
    private Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_resultados);

        lvResultados = findViewById(R.id.lv_resultados);
        btnVolver = findViewById(R.id.btn_volver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver(v);
            }
        });

        if(this.getIntent()!=null){
            Intent intent = this.getIntent();
            nPreguntas = intent.getIntExtra("nPreguntas",-1);
            for(int i = 0; i<nPreguntas; i++){
                preguntas.add((Pregunta) intent.getSerializableExtra("pregunta"+i));
            }
            for(int i = 0; i<nPreguntas; i++){
               respuestasDadas.add((Respuesta) intent.getSerializableExtra("respuesta"+i));
            }
        }
        SharedPreferences prefs = getSharedPreferences("PreferenciasAppQuiz", MODE_PRIVATE);
        boolean isMostrarRespuestas = prefs.getBoolean("isMostrarRespuestas",false);
        ArrayList<String> resultados = new ArrayList<>();
        for(int i = 0; i<preguntas.size(); i++){
            if(!isMostrarRespuestas) {
                if (preguntas.get(i).getRespuestaCorrecta().equals(respuestasDadas.get(i))) {
                    resultados.add(preguntas.get(i).getTextoPregunta() + " Correcta");
                } else {
                    resultados.add(preguntas.get(i).getTextoPregunta() + " Errónea");
                }
            } else {
                if (preguntas.get(i).getRespuestaCorrecta().equals(respuestasDadas.get(i))) {
                    resultados.add(preguntas.get(i).getTextoPregunta() + " Correcta");
                } else {
                    resultados.add(preguntas.get(i).getTextoPregunta() + " Errónea -> "+preguntas.get(i).getRespuestaCorrecta().getTextoRespuesta());
                }
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.resumen_preguntas,R.id.tv_resultado_pregunta, resultados);
        lvResultados.setAdapter(arrayAdapter);
    }
    public void volver(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}