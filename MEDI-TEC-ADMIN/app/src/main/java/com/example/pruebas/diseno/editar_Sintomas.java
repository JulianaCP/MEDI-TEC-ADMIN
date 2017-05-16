package com.example.pruebas.diseno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editar_Sintomas extends AppCompatActivity {

    EditText editarSintmasInputNombre;
    Button editsrSintomasButtonAceptar;
    Bundle bundle;
    String valorString, nuevoNombre;
    int valorInt;

    Sintoma sintomaEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__sintomas);
        bundle = getIntent().getExtras();
        valorString = bundle.getString("valor");
        valorInt = Integer.parseInt(valorString);

        sintomaEditar = Global.lista.get(valorInt); //obtengo sintoma


        editarSintmasInputNombre = (EditText)findViewById(R.id.editarSintomasInputNombre);
        editsrSintomasButtonAceptar = (Button)findViewById(R.id.editarSintomasButtonAceptar);

        editarSintmasInputNombre.setText(sintomaEditar.getNombre());


        editsrSintomasButtonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoNombre = editarSintmasInputNombre.getText().toString();
                if(editarSintmasInputNombre.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"nombre Vacio",Toast.LENGTH_LONG).show();
                }
                else{
                    Global.lista.get(valorInt).setNombre(nuevoNombre);
                    finish();
                }

            }
        });
    }
}
