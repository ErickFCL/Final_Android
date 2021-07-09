package com.example.finalandroid.Services;

import com.example.finalandroid.Clases.Entrenador;
import com.example.finalandroid.Clases.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PokemonService {
    @GET("pokemons/N00020449")
    Call<List<Pokemon>> getAll();
    @GET("entrenador/N00020449")
    Call<Entrenador> getOne();
    @POST("entrenador/N00020449")
    Call<Entrenador> createE(@Body Entrenador entrenador);
    @POST("pokemons/N00020449/crear")
    Call<Pokemon> create(@Body Pokemon pokemon);


    @POST("entrenador/N00020449/pokemon")
    Call<Pokemon> capture(@Body Pokemon pokemon);
}
