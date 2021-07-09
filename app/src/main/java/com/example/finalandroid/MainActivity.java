package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalandroid.Clases.Entrenador;
import com.example.finalandroid.Clases.Pokemon;
import com.example.finalandroid.Services.PokemonService;
import com.example.finalandroid.adapters.PokemonAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button listar = findViewById(R.id.myList);
        Button registrar = findViewById(R.id.register);
        Button entrenador = findViewById(R.id.CrearEntrenador);
        ImageView image = findViewById(R.id.Entrenador);
        TextView nombres = findViewById(R.id.Nombres);
        TextView pueblos = findViewById(R.id.Pueblo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService service = retrofit.create(PokemonService.class);
        Call<Entrenador> entrenador2 = service.getOne();

        entrenador2.enqueue(new Callback<Entrenador>() {
            @Override
            public void onResponse(Call<Entrenador> call, Response<Entrenador> response) {
                if(response.code() == 200){
                    Log.i("Web","Conexion todo ok :D");
                    Entrenador entrenador3  = response.body();
                    nombres.setText(entrenador3.getNombre());
                    pueblos.setText(entrenador3.getPueblo());

                    Picasso.get().load( entrenador3.getImagen()).into(image);

                }else {
                    Log.i("Web","Conexion nada ok F");
                }
            }

            @Override
            public void onFailure(Call<Entrenador> call, Throwable t) {
                Log.i("Web","NO pudimos conectarnos al servidor");
            }
        });

        entrenador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Crear_Entrenador.class);
                startActivity(intent);
            }
        });

        listar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListarPokemon.class);
                startActivity(intent);
            }
        });
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,nuevo_pokemon.class);
                startActivity(intent);
            }
        });
    }
}