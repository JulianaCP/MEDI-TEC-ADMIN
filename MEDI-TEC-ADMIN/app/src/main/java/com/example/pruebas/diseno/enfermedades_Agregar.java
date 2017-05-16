package com.example.pruebas.diseno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class enfermedades_Agregar extends AppCompatActivity {

    Button aceptarButton, cancelarButton;
    EditText input_nombre, input_descripcion;
    String stringNombre,stringDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_agregar);

        aceptarButton = (Button)findViewById(R.id.enfermedades_agregar_button_Aceptar);
        cancelarButton = (Button)findViewById(R.id.enfermedad_agregar_cancelar);

        input_nombre = (EditText)findViewById(R.id.enfermedad_agregar_Input_nombre);
        input_descripcion = (EditText)findViewById(R.id.enfermedad_agregar_Input_descripcion);


        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_nombre.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Nombre vacio", Toast.LENGTH_SHORT).show();
                }
                else if(input_descripcion.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Descripcion vacio", Toast.LENGTH_SHORT).show();
                }
                else{
                    stringNombre = input_nombre.getText().toString();
                    stringDescripcion = input_descripcion.getText().toString();

                    int idBorrar = 0;
                    Enfermedad enfer = new Enfermedad(idBorrar,stringNombre,stringDescripcion);
                    Global.listaEnfermedades.add(enfer);
                    finish();
                }
            }
        });
    }
}
