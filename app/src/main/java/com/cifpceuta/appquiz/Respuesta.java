package com.cifpceuta.appquiz;

import java.io.Serializable;
import java.util.Objects;

public class Respuesta implements Serializable {
    private String textoRespuesta;
    private boolean esCorrecta;

    public Respuesta(String textoRespuesta, boolean esCorrecta) {
        this.textoRespuesta = textoRespuesta;
        this.esCorrecta = esCorrecta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respuesta respuesta = (Respuesta) o;
        return esCorrecta == respuesta.esCorrecta && Objects.equals(textoRespuesta, respuesta.textoRespuesta);
    }
}
