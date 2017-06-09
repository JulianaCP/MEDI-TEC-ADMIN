package com.example.joha.medi_tec_admin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Juliana on 17/05/2017.
 */
public interface Servidor {
    @GET("/enfermedades/obtener")
    Call<ArrayList<Enfermedad>> obtenerListaEnfermedades();

    @GET("/medicamentos/obtener")
    Call<ArrayList<Medicamento>> obtenerListaMedicamentos();

    @GET("/sintomas/obtener")
    Call<ArrayList<Sintoma>> obtenerListaSintomas();


    @GET("/sintomasEnfermedad/obtener/{idEnfermedad}")
    Call<ArrayList<Sintoma>> obtenerListaSintomasEnfermedad(@Path("idEnfermedad") int idEnfermedad);



    //enfermedad
    @POST("/enfermedades/insertar/{nombre}/{descripcion}  ")
    Call<Boolean> insertarEnfermedades(@Path("nombre") String nombre, @Path("descripcion") String descripcion);

    @POST("/enfermedades/modificar/{id}/{nombre}/{descripcion}")
    Call<Boolean> modificarEnfermedades(@Path("id") int id,@Path("nombre") String nombre, @Path("descripcion") String descripcion);

    @POST("/enfermedades/eliminar/{id}")
    Call<Boolean> eliminarEnfermedad(@Path("id") int id);



    //medicamentos
    @POST("/medicamentos/insertar/{nombre}/{descripcion}  ")
    Call<Boolean> insertarMedicamentos(@Path("nombre") String nombre, @Path("descripcion") String descripcion);

    @POST("/medicamentos/modificar/{id}/{nombre}/{descripcion}")
    Call<Boolean> modificarMedicamento(@Path("id") int id,@Path("nombre") String nombre, @Path("descripcion") String descripcion);



    @POST("/sintomasEnfermedad/eliminar/{id}")
    Call<Boolean> eliminarTodosSintomasEnfermedad(@Path("id") int id);


    @POST("/sintomasEnfermedad/insertar/{sintoma}/{enfermedad}")
    Call<Boolean> insertarSintomaEnfemedad(@Path("sintoma") int sintoma, @Path("enfermedad") int enfermedad);

}


