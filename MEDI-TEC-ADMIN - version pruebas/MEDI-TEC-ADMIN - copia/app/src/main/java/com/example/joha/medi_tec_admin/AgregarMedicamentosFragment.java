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


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarMedicamentosFragment extends Fragment {

    private View rootView;
    private Button aceptarButton, cancelarButton;
    private EditText input_nombre, input_descripcion;
    private String stringNombre,stringDescripcion;

    public AgregarMedicamentosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_agregar_medicamentos, container, false);
        aceptarButton = (Button) rootView.findViewById(R.id.medicamento_agregarButtonAceptar);
        cancelarButton = (Button) rootView.findViewById(R.id.medicamento_agregarButtonCancelar);

        input_nombre = (EditText) rootView.findViewById(R.id.medicamentos_agregar_input_nombre);
        input_descripcion = (EditText) rootView.findViewById(R.id.medicamentos_agregar_input_descripcion);

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
                    /*stringNombre = input_nombre.getText().toString();
                    stringDescripcion = input_descripcion.getText().toString();
                    int idBorrar = 0;
                    Medicamento medi = new Medicamento(idBorrar,stringNombre,stringDescripcion);
                    Global.listaMedicamentos.add(medi);
                    goPreviousFragment();*/
                    stringNombre = input_nombre.getText().toString();
                    stringDescripcion = input_descripcion.getText().toString();
                    int idNuevo = Global.listaMedicamentos.size() + 1;
                    Medicamento medicamento = new Medicamento(idNuevo,stringNombre,stringDescripcion);
                    medicamento.insertar(getContext());
                    Global.listaMedicamentos.add(medicamento);
                    Toast.makeText(getActivity(), "Agregado ", Toast.LENGTH_SHORT).show();
                    goPreviousFragment();
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
