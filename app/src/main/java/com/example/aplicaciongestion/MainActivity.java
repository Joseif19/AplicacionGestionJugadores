package com.example.aplicaciongestion;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaJugadores = findViewById(R.id.lista);

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Lebron James", "Alero", R.drawable.lebron_james, "https://www.nba.com/lakers/los-lakers-homepage", 23, 27, "(212) 555-9876", 4.5f));
        jugadores.add(new Jugador("Stephen Curry", "Base", R.drawable.stephen_curry, "https://www.nba.com/warriors/", 30, 22, "(415) 555-1234", 4.1f));


        JugadorAdapter jugadorAdapter = new JugadorAdapter(this, jugadores);
        listaJugadores.setAdapter(jugadorAdapter);
    }
}
