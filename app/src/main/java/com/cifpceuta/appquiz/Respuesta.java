package com.cifpceuta.appquiz;

public class Respuesta {
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
}
