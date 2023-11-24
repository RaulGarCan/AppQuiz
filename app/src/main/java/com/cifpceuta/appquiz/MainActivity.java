package com.cifpceuta.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> preguntas;
    private ArrayList<String> respuestas;
    private boolean[] aciertos;
    private int[] respuestasCorrectas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntas = new ArrayList<>();
        for(int i=0; i<10; i++){
            preguntas.add("Pregunta "+i);
        }
        respuestas = new ArrayList<>();
        for(int i=0; i<preguntas.size(); i++){
            respuestas.add("Opcion 1:Opcion 2:Opcion 3:Opcion 4");
        }
        aciertos = new boolean[preguntas.size()];
        respuestasCorrectas = new int[preguntas.size()];
    }
}