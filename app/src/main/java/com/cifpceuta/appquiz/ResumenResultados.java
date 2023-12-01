package com.cifpceuta.appquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<String> resultados;
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
            Log.d("nPreguntasResumen",nPreguntas+"");
            for(int i = 0; i<nPreguntas; i++){
                preguntas.add((Pregunta) intent.getSerializableExtra("pregunta"+i));
                respuestasDadas.add((Respuesta) intent.getSerializableExtra("respuesta"+i));
            }
        }
        SharedPreferences prefs = getSharedPreferences("PreferenciasAppQuiz", MODE_PRIVATE);
        boolean isMostrarRespuestas = prefs.getBoolean("isMostrarRespuestas",false);
        resultados = new ArrayList<>();

        Log.d("preguntasSizeResumen",preguntas.size()+"");
        for(int i = 0; i<nPreguntas; i++){
            if(!isMostrarRespuestas) {
                if (respuestasDadas.get(i).isEsCorrecta()) {
                    resultados.add(preguntas.get(i).getTextoPregunta() + " Correcta");
                } else {
                    resultados.add(preguntas.get(i).getTextoPregunta() + " Errónea");
                }
            } else {
                Log.d("i del for ",i+"");
                if (respuestasDadas.get(i).isEsCorrecta()) {
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