package com.cifpceuta.appquiz;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Pregunta implements Serializable {
    private String textoPregunta;
    private ArrayList<Respuesta> respuestas;

    public Pregunta(String textoPregunta, ArrayList<Respuesta> respuestas) {
        this.textoPregunta = textoPregunta;
        this.respuestas = respuestas;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }


    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void addRespuesta(Respuesta r){
        this.respuestas.add(r);
    }
    public void removeRespuesta(Respuesta r){
        this.respuestas.remove(r);
    }
    public Respuesta getRespuestaCorrecta(){
        for(Respuesta r : respuestas){
            if(r.isEsCorrecta()){
                return r;
            }
        }
        return null;
    }
    public void randomizarRespuestas(){
        ArrayList<Respuesta> respuestasRandom = new ArrayList<>();
        ArrayList<Integer> numerosRand = new ArrayList<>();
        for(int i=0; i<respuestas.size(); i++){
            numerosRand.add(i);
        }
        Log.d("arrayRandom","ArrayRandoms: "+numerosRand);
        for(int i=0; i<respuestas.size(); i++){
            Log.d("iteracionRandom","i: "+i);
            int rand = numerosRand.get((int)(Math.random()*numerosRand.size()));
            numerosRand.remove(rand);
            Log.d("arrayRandom","ArrayRandoms: "+numerosRand);
            Log.d("randomNumber", "RandomNumber: "+rand);
            respuestasRandom.add(respuestas.get(rand));
        }
        respuestas = respuestasRandom;
    }
}
