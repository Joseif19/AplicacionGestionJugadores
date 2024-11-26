package com.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertarModificarJugador extends AppCompatActivity {

    private Button btnInsertar, btnModificar;
    private EditText edtNombre, edtPosicion, edtTelefono;
    private Jugador jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.botones_insertar_modificar);

        btnInsertar = findViewById(R.id.btn_insertar);
        btnModificar = findViewById(R.id.btn_modificar);

        edtNombre = findViewById(R.id.edt_nombre);
        edtPosicion = findViewById(R.id.edt_posicion);
        edtTelefono = findViewById(R.id.edt_telefono);

        jugador = (Jugador) getIntent().getSerializableExtra("jugador");

        if (jugador != null) {
            edtNombre.setText(jugador.getNombre());
            edtPosicion.setText(jugador.getPosicion());
            edtTelefono.setText(jugador.getTelefonoJugador());

            btnInsertar.setVisibility(View.GONE);
            btnModificar.setVisibility(View.VISIBLE);
        } else {
            btnModificar.setVisibility(View.GONE);
        }

        btnInsertar.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString();
            String posicion = edtPosicion.getText().toString();
            String telefono = edtTelefono.getText().toString();

            if (nombre.isEmpty() || posicion.isEmpty() || telefono.isEmpty()) {
                // Mostrar un mensaje de error si los campos están vacíos
                Toast.makeText(InsertarModificarJugador.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            Jugador nuevoJugador = new Jugador(nombre, posicion, R.drawable.lebron_james, "web", 10, 20, telefono, 4.5f);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nuevoJugador", nuevoJugador);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnModificar.setOnClickListener(v -> {
            if (jugador != null) {
                String nombre = edtNombre.getText().toString();
                String posicion = edtPosicion.getText().toString();
                String telefono = edtTelefono.getText().toString();

                if (nombre.isEmpty() || posicion.isEmpty() || telefono.isEmpty()) {
                    Toast.makeText(InsertarModificarJugador.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                jugador.setNombre(nombre);
                jugador.setPosicion(posicion);
                jugador.setTelefonoJugador(telefono);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("jugadorModificado", jugador);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}
