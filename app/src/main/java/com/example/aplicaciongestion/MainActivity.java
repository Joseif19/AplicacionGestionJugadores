package com.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_INSERTAR = 1;
    private static final int REQUEST_CODE_MODIFICAR = 2;

    private List<Jugador> jugadores;
    private JugadorAdapter jugadorAdapter;
    private int jugadorSeleccionadoIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaJugadores = findViewById(R.id.lista);

        jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Lebron James", "Alero", R.drawable.lebron_james, "https://www.nba.com/lakers/los-lakers-homepage", 23, 27, "(212) 555-9876", 4.5f));
        jugadores.add(new Jugador("Stephen Curry", "Base", R.drawable.stephen_curry, "https://www.nba.com/warriors/", 30, 22, "(415) 555-1234", 4.1f));

        jugadorAdapter = new JugadorAdapter(this, jugadores);
        listaJugadores.setAdapter(jugadorAdapter);

        FloatingActionButton fabAgregar = findViewById(R.id.fab_agregar);
        fabAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InsertarModificarJugador.class);
            startActivityForResult(intent, REQUEST_CODE_INSERTAR);
        });

        registerForContextMenu(listaJugadores);

        listaJugadores.setOnItemLongClickListener((adapterView, view, position, id) -> {
            jugadorSeleccionadoIndex = position;
            return false;
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_editar) {
            if (jugadorSeleccionadoIndex != -1) {
                Intent intent = new Intent(MainActivity.this, InsertarModificarJugador.class);
                intent.putExtra("jugador", jugadores.get(jugadorSeleccionadoIndex));
                startActivityForResult(intent, REQUEST_CODE_MODIFICAR);
            }
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            // Intent extraes el jugador, asegúrate de que se ha recibido correctamente
            Jugador jugador = (Jugador) data.getSerializableExtra("jugador");

            if (jugador != null) {
                if (requestCode == REQUEST_CODE_INSERTAR) {
                    jugadores.add(jugador);
                } else if (requestCode == REQUEST_CODE_MODIFICAR && jugadorSeleccionadoIndex != -1) {
                    jugadores.set(jugadorSeleccionadoIndex, jugador);
                    jugadorSeleccionadoIndex = -1;
                }
                jugadorAdapter.notifyDataSetChanged();
            }
        }
    }

}
