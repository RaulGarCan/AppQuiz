package com.cifpceuta.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pregunta> preguntas;
    private Button btnSiguiente;
    private TextView tvTextoPregunta;
    private RadioButton rbOpcion1, rbOpcion2, rbOpcion3, rbOpcion4;
    private RadioGroup rgOpciones;
    private int nPreguntas = 0;
    private ArrayList<Respuesta> respuestaSeleccionadas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSiguiente = findViewById(R.id.btn_siguiente);
        tvTextoPregunta = findViewById(R.id.tv_texto_pregunta);
        rbOpcion1 = findViewById(R.id.rb_opcion1);
        rbOpcion2 = findViewById(R.id.rb_opcion2);
        rbOpcion3 = findViewById(R.id.rb_opcion3);
        rbOpcion4 = findViewById(R.id.rb_opcion4);
        rgOpciones = findViewById(R.id.rg_opciones);

        rbOpcion1.setChecked(true);

        // Creamos las preguntas
        preguntas = new ArrayList<>();

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Opcion 1", true));
        respuestas.add(new Respuesta("Opcion 2", false));
        respuestas.add(new Respuesta("Opcion 3", false));
        respuestas.add(new Respuesta("Opcion 4", false));

        preguntas.add(new Pregunta("Pregunta 1",respuestas));

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Opcion 1", true));
        respuestas.add(new Respuesta("Opcion 2", false));
        respuestas.add(new Respuesta("Opcion 3", false));
        respuestas.add(new Respuesta("Opcion 4", false));

        preguntas.add(new Pregunta("Pregunta 2",respuestas));

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Opcion 1", true));
        respuestas.add(new Respuesta("Opcion 2", false));
        respuestas.add(new Respuesta("Opcion 3", false));
        respuestas.add(new Respuesta("Opcion 4", false));

        preguntas.add(new Pregunta("Pregunta 3",respuestas));

        respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("Opcion 1", true));
        respuestas.add(new Respuesta("Opcion 2", false));
        respuestas.add(new Respuesta("Opcion 3", false));
        respuestas.add(new Respuesta("Opcion 4", false));

        preguntas.add(new Pregunta("Pregunta 4",respuestas));

        avanzarPregunta();
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avanzarPregunta();
            }
        });
    }
    public void avanzarPregunta(){
        if(!btnSiguiente.getText().toString().equalsIgnoreCase("Comprobar")){
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

        }
    }
}