package com.example.joha.medi_tec_admin;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class asignarSintomaEnfermedadFragment extends Fragment {


    private View rootView;

    ListView listViewSintomas;
    Enfermedad enfermedad;
    int valorId;
    int idEnfermedad;
    ArrayList<Sintoma> listaSintomasEnfermedad = new ArrayList<Sintoma>();
    TextView nombreEnferedadText;

    Button aceptar, cancelar;

    public asignarSintomaEnfermedadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_asignar_sintoma_enfermedad, container, false);

        listViewSintomas = (ListView)rootView.findViewById(R.id.asignar_sintoma_listView_sintomas);
        nombreEnferedadText = (TextView)rootView.findViewById(R.id.asignar_sintoma_enfermedad_LABEL_nombreEnfermd);
        aceptar = (Button)rootView.findViewById(R.id.asignar_sintoma_enfermedad_button_aceptar);
        cancelar = (Button)rootView.findViewById(R.id.asignar_sintoma_enfermedad_button_cancelar);


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPreviousFragment();
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarTodo();
            }
        });


        valorId = Global.posicionItemListViewPresionado;
        enfermedad = Global.listaEnfermedades.get(valorId);
        idEnfermedad = enfermedad.getIdEnfermedad();
        nombreEnferedadText.setText(enfermedad.getNombre());

        System.out.println("\nValor idEnfemerdad: "+ idEnfermedad+" \n");
        obtenerDatosSintomasEnfermedad();
        return rootView;

    }
    public void eliminarTodo(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servidor servidor = retrofit.create(Servidor.class);

        Call<Boolean> call= servidor.eliminarTodosSintomasEnfermedad(idEnfermedad);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Snackbar.make(getView(), "correcto: ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                System.out.println("\n\n\n*************************************** REVISAR RESULTADO\n");
                System.out.println("response.MESSAGE: " +response.message());
                System.out.println("response.isSuccessful: " +response.isSuccessful());
                System.out.println("response.isSuccessful: " +response.errorBody());
                System.out.println("response.toString: " +response.toString());
                System.out.println("response.raw: " +response.raw());
                System.out.println("response.headers: " +response.headers());
                System.out.println("\n\n\n*************************************** final RESULTADO\n");
                insertarReleacionesSintomasEnfermedad();
                //obtenerDatosSintomasEnfermedad();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Snackbar.make(getView(), "Fallo ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void insertarReleacionesSintomasEnfermedad(){
        for(int i=0;i<listaSintomasEnfermedad.size();i++){
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Global.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Servidor servidor = retrofit.create(Servidor.class);

            Call<Boolean> call= servidor.insertarSintomaEnfemedad(listaSintomasEnfermedad.get(i).getId(),
                    idEnfermedad);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    System.out.println("exito insertar base ");
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    System.out.println("fallo insertar base ");
                }
            });
        }
        goPreviousFragment();
    }
    public void llenarListView(){
        listViewSintomas.setAdapter(new viewAdapter(getActivity().getApplicationContext()));
    }


    public void obtenerDatos(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servidor servidor = retrofit.create(Servidor.class);

        Call<ArrayList<Sintoma>> call= servidor.obtenerListaSintomas();
        call.enqueue(new Callback<ArrayList<Sintoma>>() {
            @Override
            public void onResponse(Call<ArrayList<Sintoma>> call, Response<ArrayList<Sintoma>> response) {

                Global.listaSintomas = response.body();
                System.out.println("\n\n------------- LISTA SINTOMAS --------------------------- ");
                for(int i = 0;i<response.body().size();i++){
                    System.out.println(" \nnombre "+ response.body().get(i).getNombre());
                    System.out.println(" id "+ response.body().get(i).getId() + "\n");
                }
                llenarListView();
            }

            @Override
            public void onFailure(Call<ArrayList<Sintoma>> call, Throwable t) {
                Snackbar.make(getView(), "Fallo conexion: ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void obtenerDatosSintomasEnfermedad(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servidor servidor = retrofit.create(Servidor.class);

        Call<ArrayList<Sintoma>> call= servidor.obtenerListaSintomasEnfermedad(idEnfermedad);
        call.enqueue(new Callback<ArrayList<Sintoma>>() {
            @Override
            public void onResponse(Call<ArrayList<Sintoma>> call, Response<ArrayList<Sintoma>> response) {
                listaSintomasEnfermedad = response.body();
                System.out.println("\n\n------------- listaSintomasEnfermedad --------------------------- ");
                for(int i = 0;i<response.body().size();i++){

                    System.out.println(" \nobjeto "+ response.body().get(i));
                    System.out.println(" nombre "+ response.body().get(i).getNombre());
                    System.out.println(" id "+ response.body().get(i).getId() + "\n");
                }
               obtenerDatos();
            }

            @Override
            public void onFailure(Call<ArrayList<Sintoma>> call, Throwable t) {
                Snackbar.make(getView(), "Fallo conexion: ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public class viewAdapter extends BaseAdapter{

        LayoutInflater layoutInflater;
        public viewAdapter(Context context){
            layoutInflater = layoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return Global.listaSintomas.size();
        }

        @Override
        public Object getItem(int i) {
            return Global.listaSintomas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view = layoutInflater.inflate(R.layout.activity_asignar_sintoma_enfermedad_componente,null);
            }
            final int pos = i;
            final TextView nombreSintoma = (TextView)view.findViewById(R.id.componente_asignar_sintoma_enfermedad_nombre);
            final CheckBox checkBoxSintoma = (CheckBox)view.findViewById(R.id.componente_asignar_enfermedad_checkBox);

            nombreSintoma.setText(Global.listaSintomas.get(pos).getNombre());

            Boolean valor = listaSintomasEnfermedad.contains(Global.listaSintomas.get(pos));
            Sintoma ff = Global.listaSintomas.get(pos);
            System.out.println(" \n--------------------------- ");
            System.out.println(" objeto "+ ff);
            System.out.println(" nombre "+ ff.getNombre());
            System.out.println(" id "+ ff.getId());
            System.out.println(" esta en lista "+ valor);
            System.out.println(" --------------------------- \n");
            if(encontrarDentro(pos)){

                checkBoxSintoma.setChecked(true);
            }

            checkBoxSintoma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBoxSintoma.isChecked()){
                        listaSintomasEnfermedad.add(Global.listaSintomas.get(pos));
                        Snackbar.make(view, "ADD - Lista:  len:" + listaSintomasEnfermedad.size()+ "continene valor: "
                                +listaSintomasEnfermedad.contains(Global.listaSintomas.get(pos)), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else{
                        eliminarSintomaLista_SintomasEnfermedad(Global.listaSintomas.get(pos));

                        Snackbar.make(view, "REMOVE - Lista:  len:" + listaSintomasEnfermedad.size()+ "continene valor: "
                                +listaSintomasEnfermedad.contains(Global.listaSintomas.get(pos)), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });
            return view;
        }
    }
    public void eliminarSintomaLista_SintomasEnfermedad(Sintoma sintoma){
        for(int i=0;i<listaSintomasEnfermedad.size();i++){
            if(listaSintomasEnfermedad.get(i).getId() == sintoma.getId() ||
                    listaSintomasEnfermedad.get(i).getNombre().equals(sintoma.getNombre())){
               listaSintomasEnfermedad.remove(i);
                break;
            }
        }
    }
    public Boolean encontrarDentro(int pos){
        Boolean esta = false;

        for(int i=0;i<listaSintomasEnfermedad.size();i++){
            if(listaSintomasEnfermedad.get(i).getId() == Global.listaSintomas.get(pos).getId() ||
                    listaSintomasEnfermedad.get(i).getNombre().equals(Global.listaSintomas.get(pos).getNombre())){
                esta = true;
            }
        }

        return esta;

    }

    private void goPreviousFragment() {
        EnfermedadesListaFragment enfermedadesListaFragment = new EnfermedadesListaFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.ContentForFragments, enfermedadesListaFragment).commit();
    }

}
