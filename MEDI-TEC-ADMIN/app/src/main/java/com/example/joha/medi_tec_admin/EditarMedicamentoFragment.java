package com.example.joha.medi_tec_admin;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarMedicamentoFragment extends Fragment {

    private View rootView;
    private Button aceptarButton, cancelarButton;
    private EditText input_nombre, input_descripcion;
    private String stringNombre,stringDescripcion;
    private String valorString;
    private int valorInt;

    int idMedicamento;

    Medicamento medicamento;
    public EditarMedicamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_editar_medicamento, container, false);
        valorInt = Global.posicionItemListViewPresionado;

        aceptarButton = (Button) rootView.findViewById(R.id.medicamento_editar_button_Aceptar);
        cancelarButton = (Button) rootView.findViewById(R.id.medicamento_editar_button_cancelar);

        input_nombre = (EditText) rootView.findViewById(R.id.medicamento_editar_input_nombre);
        input_descripcion = (EditText) rootView.findViewById(R.id.medicamento_editar_input_descripcion);


        medicamento = Global.listaMedicamentos.get(valorInt);
        idMedicamento = medicamento.getIdMedicamento();
        stringNombre = medicamento.getNombre();
        stringDescripcion = medicamento.getDescripcion();

        input_nombre.setText(stringNombre);
        input_descripcion.setText(stringDescripcion);

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPreviousFragment();
            }
        });
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_nombre.getText().toString().equals("")){
                    Snackbar.make(view, "Nombre vacio", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if(input_descripcion.getText().toString().equals("")){
                    Snackbar.make(view, "Descripcion vacio", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    stringNombre = input_nombre.getText().toString();
                    stringDescripcion = input_descripcion.getText().toString();

                    final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Global.getBaseUrl())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Servidor servidor = retrofit.create(Servidor.class);
                    Call<Boolean> call = servidor.modificarMedicamento(idMedicamento,stringNombre,stringDescripcion);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Snackbar.make(getView(), "Modificacion exitosa", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            goPreviousFragment();
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Snackbar.make(getView(), "Error Modificacion", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    });

                }
            }
        });
        return rootView;
    }

    private void goPreviousFragment() {
        MedicamentosListaFragment medicamentosListaFragment = new MedicamentosListaFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.ContentForFragments, medicamentosListaFragment).commit();
    }

}
