package com.example.pruebas.diseno;

/**
 * Created by Pruebas on 16/05/2017.
 */


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Servidor {

    @GET("enfermedades/obtener")
    Call<Enfermedad> obtenerEnfermedades();
}
