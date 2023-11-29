package com.cifpceuta.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResumenResultados extends AppCompatActivity {
    private ArrayList<Pregunta> preguntas = new ArrayList<>();
    private ArrayList<Respuesta> respuestasDadas = new ArrayList<>();
    private int nPreguntas;
    private ListView lvResultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_resultados);

        lvResultados = findViewById(R.id.lv_resultados);

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
        ArrayList<String> resultados = new ArrayList<>();
        for(int i = 0; i<preguntas.size(); i++){
           if(preguntas.get(i).getRespuestaCorrecta().equals(respuestasDadas.get(i))){
               resultados.add(preguntas.get(i).getTextoPregunta()+": correcta");
           } else {
               resultados.add(preguntas.get(i).getTextoPregunta()+": errónea");
           }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.resumen_preguntas,R.id.tv_resultado_pregunta, resultados);
        lvResultados.setAdapter(arrayAdapter);
    }
}