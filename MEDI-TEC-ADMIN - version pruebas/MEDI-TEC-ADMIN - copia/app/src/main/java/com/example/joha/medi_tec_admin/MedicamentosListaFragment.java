package com.example.joha.medi_tec_admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentosListaFragment extends Fragment {

    private MenuInflater inflayer;
    private ArrayList<Medicamento> arrayListaMedicamentosClass;
    private ArrayList<String> arrayListaMedicamentosString;
    private ArrayAdapter<String> adapter;
    private View rootView;
    private ListView medicamentos_lista_ListViewMedicamentos;
    private int posicionItemPopuMenuPresionado;
    private Bundle bundle;

    public MedicamentosListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_medicamentos_lista, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        medicamentos_lista_ListViewMedicamentos = (ListView) rootView.findViewById(R.id.medicamentos_lista_listView_medicamentos);

        llenarListViewMedicamentos();

        medicamentos_lista_ListViewMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Global.posicionItemListViewPresionado = i;
                showPopupMenu(view);
            }
        });

        FloatingActionButton btnAgregarMedicamento = (FloatingActionButton) rootView.findViewById(R.id.btnAgregarMedicamento);
        btnAgregarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarMedicamentosFragment agregarMedicamentosFragment = new AgregarMedicamentosFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.ContentForFragments, agregarMedicamentosFragment).commit();
            }
        });

        return rootView;
    }



    @Override
    public void onResume() {
        llenarListViewMedicamentos();
        super.onResume();
    }
    public void obtenerDatos(){
        Global.listaMedicamentos = ListaMedicamentos.getInstancia().leer(getContext());
        /*final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servidor servidor = retrofit.create(Servidor.class);

        Call<ArrayList<Medicamento>> call= servidor.obtenerListaMedicamentos();

        call.enqueue(new Callback<ArrayList<Medicamento>>() {
            @Override
            public void onResponse(Call<ArrayList<Medicamento>> call, Response<ArrayList<Medicamento>> response) {
                Global.listaMedicamentos = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Medicamento>> call, Throwable t) {
                Snackbar.make(getView(), "Fallo: "+ t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    public void llenarListViewMedicamentos(){
        obtenerDatos();
        arrayListaMedicamentosClass = Global.listaMedicamentos;
        arrayListaMedicamentosString = convertirClass_String();
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1 , arrayListaMedicamentosString);
        medicamentos_lista_ListViewMedicamentos.setAdapter(adapter);
    }
    public ArrayList<String> convertirClass_String(){
        ArrayList<String> listaTemp = new ArrayList<String>();
        String valor;

        for (int i=0;i<arrayListaMedicamentosClass.size();i++){
            valor = arrayListaMedicamentosClass.get(i).getNombre();
            listaTemp.add(valor);
        }
        return listaTemp;
    }

    public void showPopupMenu(final View view){

        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                posicionItemPopuMenuPresionado = menuItem.getItemId();
                if(posicionItemPopuMenuPresionado == R.id.menuOpcionEditar){
/*
                    bundle = new Bundle();
                    bundle.putString("valor",Integer.toString(Global.posicionItemListViewPresionado));
                    Intent intent = new Intent(getApplicationContext(),editar_medicamentos.class);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                    EditarMedicamentoFragment editarMedicamentoFragment = new EditarMedicamentoFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, editarMedicamentoFragment).commit();

                }
                else{
                    new AlertDialog.Builder(getActivity())

                            .setMessage("¿Desea Eliminar?")
                            .setTitle("Confirmacion")
                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener()  {
                                public void onClick(DialogInterface dialog, int id) {
                                    Log.i("Dialogos", "Elemento Eliminado.");
                                    dialog.cancel();
                                    eliminar(Global.posicionItemListViewPresionado);

                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Log.i("Dialogos", "Confirmacion Cancelada.");
                                    dialog.cancel();
                                }
                            }).create().show();
                }
                return true;
            }
        });
        inflayer = getActivity().getMenuInflater();
        inflayer.inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.show();
    }
    public void eliminar(int posicion){
        Global.listaMedicamentos.remove(posicion);
        onResume();
    }

}
