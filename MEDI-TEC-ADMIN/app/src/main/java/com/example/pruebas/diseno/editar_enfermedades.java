package com.example.pruebas.diseno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editar_enfermedades extends AppCompatActivity {

    Button aceptarButton, cancelarButton;
    EditText input_nombre, input_descripcion;
    String stringNombre,stringDescripcion;
    Bundle bundle;
    String valorString;
    int valorInt;

    Enfermedad enfermedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_enfermedades);
        bundle = getIntent().getExtras();
        valorString = bundle.getString("valor");
        valorInt = Integer.parseInt(valorString);

        aceptarButton = (Button)findViewById(R.id.enfermedades_editar_button_aceptar);
        cancelarButton = (Button)findViewById(R.id.enfermedades_editar_button_cancelar);

        input_nombre = (EditText)findViewById(R.id.enfermedades_editar_input_nombre);
        input_descripcion = (EditText)findViewById(R.id.enfermedades_editar_input_descripcion);



        enfermedad = Global.listaEnfermedades.get(valorInt);
        stringNombre = enfermedad.getNombre();
        stringDescripcion = enfermedad.getDescripcion();

        input_nombre.setText(stringNombre);
        input_descripcion.setText(stringDescripcion);

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



                    Global.listaEnfermedades.get(valorInt).setNombre(stringNombre);
                    Global.listaEnfermedades.get(valorInt).setDescripcion(stringDescripcion);
                    finish();
                }
            }
        });
    }
}
