package com.example.pruebas.diseno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class medicamentos_agregar extends AppCompatActivity {

    Button aceptarButton, cancelarButton;
    EditText input_nombre, input_descripcion;
    String stringNombre,stringDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos_agregar);

        aceptarButton = (Button)findViewById(R.id.medicamento_agregarButtonAceptar);
        cancelarButton = (Button)findViewById(R.id.medicamento_agregarButtonCancelar);

        input_nombre = (EditText)findViewById(R.id.medicamentos_agregar_input_nombre);
        input_descripcion = (EditText)findViewById(R.id.medicamentos_agregar_input_descripcion);


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
                    Medicamento medi = new Medicamento(idBorrar,stringNombre,stringDescripcion);
                    Global.listaMedicamentos.add(medi);
                    finish();
                }
            }
        });
    }
}
