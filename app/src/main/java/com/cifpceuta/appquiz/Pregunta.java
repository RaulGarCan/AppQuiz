package com.cifpceuta.appquiz;

import java.io.Serializable;
import java.util.ArrayList;

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
}