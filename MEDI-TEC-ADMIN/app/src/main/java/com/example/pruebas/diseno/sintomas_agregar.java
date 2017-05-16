package com.example.pruebas.diseno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sintomas_agregar extends AppCompatActivity {

    Button sintomasAgregarButtonAceptar;
    EditText sintomasAgregarInputNombre;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas_agregar);
        sintomasAgregarButtonAceptar = (Button)findViewById(R.id.sintomasAgregarButtonAceptar);
        sintomasAgregarInputNombre = (EditText)findViewById(R.id.sintomasAgregarInpuNombre);

        sintomasAgregarButtonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sintomasAgregarInputNombre.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"nombre Vacio",Toast.LENGTH_LONG).show();
                }
                else{
                    nombre = sintomasAgregarInputNombre.getText().toString();
                    int idBorrar = 0;
                    Sintoma sintoma = new Sintoma(idBorrar,nombre);
                    Global.lista.add(sintoma);
                    finish();
                }
            }
        });
    }
}
