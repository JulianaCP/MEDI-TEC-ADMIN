package com.example.pruebas.diseno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editar_medicamentos extends AppCompatActivity {

    Button aceptarButton, cancelarButton;
    EditText input_nombre, input_descripcion;
    String stringNombre,stringDescripcion;
    Bundle bundle;
    String valorString;
    int valorInt;

    Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_medicamentos);

        bundle = getIntent().getExtras();
        valorString = bundle.getString("valor");
        valorInt = Integer.parseInt(valorString);

        aceptarButton = (Button)findViewById(R.id.medicamento_editar_button_Aceptar);
        cancelarButton = (Button)findViewById(R.id.medicamento_editar_button_cancelar);

        input_nombre = (EditText)findViewById(R.id.medicamento_editar_input_nombre);
        input_descripcion = (EditText)findViewById(R.id.medicamento_editar_input_descripcion);


        medicamento = Global.listaMedicamentos.get(valorInt);
        stringNombre = medicamento.getNombre();
        stringDescripcion = medicamento.getDescripcion();

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



                    Global.listaMedicamentos.get(valorInt).setNombre(stringNombre);
                    Global.listaMedicamentos.get(valorInt).setDescripcion(stringDescripcion);
                    finish();
                }
            }
        });
    }
}
