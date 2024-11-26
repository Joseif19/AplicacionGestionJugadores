package com.example.aplicaciongestion;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_INSERTAR = 1;
    private ListView listViewJugadores;
    private List<Jugador> listaJugadores;
    private JugadorAdapter jugadorAdapter;

    public static final int REQUEST_INSERTAR_JUGADOR = 1;
    public static final int REQUEST_MODIFICAR_JUGADOR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewJugadores = findViewById(R.id.lista);

        // Inicializar la lista de jugadores con datos de ejemplo
        listaJugadores = new ArrayList<>();
        listaJugadores.add(new Jugador("LeBron James", "Alero", R.drawable.lebron_james, "https://www.lakers.com", 23, 27, "123456789", 4.5f));
        listaJugadores.add(new Jugador("Stephen Curry", "Base", R.drawable.stephen_curry, "https://www.warriors.com", 30, 28, "987654321", 4.8f));

        jugadorAdapter = new JugadorAdapter(this, listaJugadores, REQUEST_MODIFICAR_JUGADOR);
        listViewJugadores.setAdapter(jugadorAdapter);

        // Configurar el click largo en los botones "Modificar"
        listViewJugadores.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Jugador jugadorSeleccionado = listaJugadores.get(position);
            Intent intent = new Intent(MainActivity.this, InsertarModificarJugador.class);
            intent.putExtra("jugador", jugadorSeleccionado);
            startActivityForResult(intent, REQUEST_MODIFICAR_JUGADOR);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_insertar:
                // Abre la actividad para insertar un nuevo jugador
                Intent intent = new Intent(MainActivity.this, InsertarModificarJugador.class);
                startActivityForResult(intent, REQUEST_CODE_INSERTAR);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Manejar el resultado de las actividades de insertar o modificar jugador
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_INSERTAR_JUGADOR) {
                Jugador nuevoJugador = (Jugador) data.getSerializableExtra("nuevoJugador");
                listaJugadores.add(nuevoJugador);
                jugadorAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Jugador añadido con éxito.", Toast.LENGTH_SHORT).show();
            } else if (requestCode == REQUEST_MODIFICAR_JUGADOR) {
                Jugador jugadorModificado = (Jugador) data.getSerializableExtra("jugadorModificado");
                actualizarJugador(jugadorModificado);
                jugadorAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Jugador modificado con éxito.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para actualizar un jugador en la lista
    private void actualizarJugador(Jugador jugadorModificado) {
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getNombre().equals(jugadorModificado.getNombre())) {
                listaJugadores.set(i, jugadorModificado);
                break;
            }
        }
    }
}
