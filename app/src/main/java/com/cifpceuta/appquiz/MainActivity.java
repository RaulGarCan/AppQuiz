package com.cifpceuta.appquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pregunta> preguntas;
    private Button btnSiguiente;
    private TextView tvTextoPregunta, tvNumeroPregunta;
    private RadioButton rbOpcion1, rbOpcion2, rbOpcion3, rbOpcion4;
    private RadioGroup rgOpciones;
    private int nPreguntas = 0;
    private Toolbar toolbar;
    private ArrayList<Respuesta> respuestaSeleccionadas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSiguiente = findViewById(R.id.btn_siguiente);
        tvTextoPregunta = findViewById(R.id.tv_texto_pregunta);
        tvNumeroPregunta = findViewById(R.id.tv_contador_pregunta);
        rbOpcion1 = findViewById(R.id.rb_opcion1);
        rbOpcion2 = findViewById(R.id.rb_opcion2);
        rbOpcion3 = findViewById(R.id.rb_opcion3);
        rbOpcion4 = findViewById(R.id.rb_opcion4);
        rgOpciones = findViewById(R.id.rg_opciones);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prepararPreguntas();
        avanzarPregunta();
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hayOpcionSeleccionada()){
                    avanzarPregunta();
                    rgOpciones.clearCheck();
                }
            }
        });
    }
    public void avanzarPregunta(){
        if(!btnSiguiente.getText().toString().equalsIgnoreCase("Comprobar")){
            tvNumeroPregunta.setText(nPreguntas+1+"/"+preguntas.size());
            // Guarda la respuesta seleccionada
            if(rbOpcion1.isChecked()){
                respuestaSeleccionadas.add(preguntas.get(nPreguntas).getRespuestas().get(0));
            } else if (rbOpcion2.isChecked()) {
                respuestaSeleccionadas.add(preguntas.get(nPreguntas).getRespuestas().get(1));
            } else if (rbOpcion3.isChecked()) {
                respuestaSeleccionadas.add(preguntas.get(nPreguntas).getRespuestas().get(2));
            } else {
                respuestaSeleccionadas.add(preguntas.get(nPreguntas).getRespuestas().get(3));
            }
            // Cambia el texto del botón en la última pregunta
            if(nPreguntas==preguntas.size()-1){
                btnSiguiente.setText("Comprobar");
            }
            // Actualiza los datos
            tvTextoPregunta.setText(preguntas.get(nPreguntas).getTextoPregunta());
            rbOpcion1.setText(preguntas.get(nPreguntas).getRespuestas().get(0).getTextoRespuesta());
            rbOpcion2.setText(preguntas.get(nPreguntas).getRespuestas().get(1).getTextoRespuesta());
            rbOpcion3.setText(preguntas.get(nPreguntas).getRespuestas().get(2).getTextoRespuesta());
            rbOpcion4.setText(preguntas.get(nPreguntas).getRespuestas().get(3).getTextoRespuesta());

            nPreguntas++;
        } else {
            Intent intent = new Intent(this, ResumenResultados.class);
            intent.putExtra("nPreguntas",nPreguntas);
            for(int i = 0; i<preguntas.size(); i++){
                intent.putExtra("pregunta"+i,preguntas.get(i));
            }
            for(int i = 0; i<respuestaSeleccionadas.size(); i++){
                intent.putExtra("respuesta"+i,respuestaSeleccionadas.get(i));
            }
            startActivity(intent);
        }
    }
    public void prepararPreguntas(){
        // Creamos las preguntas
        preguntas = new ArrayList<>();

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("11", true));
        respuestas.add(new Respuesta("7", false));
        respuestas.add(new Respuesta("5", false));
        respuestas.add(new Respuesta("9", false));

        Pregunta p = new Pregunta("¿Cuántas zonas horarias tiene Rusia?",respuestas);

        SharedPreferences prefs = getSharedPreferences("PreferenciasAppQuiz", MODE_PRIVATE);
        boolean isRandomRespuestas = prefs.getBoolean("isRandomRespuestas",false);

        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Flor de Cerezo", true));
        respuestas.add(new Respuesta("Flor del Dragón", false));
        respuestas.add(new Respuesta("Flor de Oriente", false));
        respuestas.add(new Respuesta("Flor del Desierto", false));

        p = new Pregunta("¿Cuál es la flor nacional de Japón?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("13", true));
        respuestas.add(new Respuesta("12", false));
        respuestas.add(new Respuesta("15", false));
        respuestas.add(new Respuesta("14", false));

        p = new Pregunta("¿Cuántas franjas tiene la bandera de Estados Unidos?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Canguro", true));
        respuestas.add(new Respuesta("Koala", false));
        respuestas.add(new Respuesta("Ornitorrinco", false));
        respuestas.add(new Respuesta("Pez Payaso", false));

        p = new Pregunta("¿Cuál es el animal nacional de Australia?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("365", true));
        respuestas.add(new Respuesta("366", false));
        respuestas.add(new Respuesta("360", false));
        respuestas.add(new Respuesta("356", false));

        p = new Pregunta("¿Cuántos días le toma a la tierra dar una vuelta a la órbita del sol?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Inca", true));
        respuestas.add(new Respuesta("Aztecas", false));
        respuestas.add(new Respuesta("Egipcios", false));
        respuestas.add(new Respuesta("Romanos", false));

        p = new Pregunta("¿Cuál de los siguientes imperios no tenía un idioma escrito?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Constantinopla", true));
        respuestas.add(new Respuesta("Turkistán", false));
        respuestas.add(new Respuesta("Siria", false));
        respuestas.add(new Respuesta("Istambulia", false));

        p = new Pregunta("¿Cómo se llamaba Estambul antes de 1923?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Suecia", true));
        respuestas.add(new Respuesta("Groenlandia", false));
        respuestas.add(new Respuesta("Islandia", false));
        respuestas.add(new Respuesta("Reino Unido", false));

        p = new Pregunta("¿Qué país tiene la mayor cantidad de islas en el mundo?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("El Vaticano", true));
        respuestas.add(new Respuesta("Suiza", false));
        respuestas.add(new Respuesta("Mónaco", false));
        respuestas.add(new Respuesta("San Marino", false));

        p = new Pregunta("¿Cuál es el país más pequeño del mundo?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("88", true));
        respuestas.add(new Respuesta("69", false));
        respuestas.add(new Respuesta("93", false));
        respuestas.add(new Respuesta("76", false));

        p = new Pregunta("¿Cuántas teclas tiene un piano?",respuestas);
        if (isRandomRespuestas){
            p.randomizarRespuestas();
        }
        preguntas.add(p);

        boolean isRandomPreguntas = prefs.getBoolean("isRandomPreguntas",false);
        if(isRandomPreguntas){
            randomizarPreguntas();
        }
    }
    public boolean hayOpcionSeleccionada(){
        if(rbOpcion1.isChecked() || rbOpcion2.isChecked() || rbOpcion3.isChecked() || rbOpcion4.isChecked()){
            return true;
        }
        return false;
    }
    public void randomizarPreguntas(){
        ArrayList<Pregunta> preguntasRandom = new ArrayList<>();
        ArrayList<Integer> numerosRand = new ArrayList<>();
        for(int i=0; i<preguntas.size(); i++){
            numerosRand.add(i);
        }
        for(int i=0; i<preguntas.size(); i++){
            Integer rand = numerosRand.get((int)(Math.random()*numerosRand.size()));
            numerosRand.remove(rand);
            preguntasRandom.add(preguntas.get(rand));
        }
        preguntas = preguntasRandom;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_preferencias,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.preferencias){
            startActivity(new Intent(this, ActivityPreferencias.class));
        }

        return true;
    }
}