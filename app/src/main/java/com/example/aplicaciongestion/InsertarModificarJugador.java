package com.example.aplicaciongestion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertarModificarJugador extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 100;

    private Button btnInsertar, btnModificar, btnSeleccionarImagen;
    private EditText edtNombre, edtPosicion, edtTelefono;
    private ImageView imgJugador;
    private Jugador jugador;
    private Uri imagenUri; // Para almacenar la URI de la imagen seleccionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.botones_insertar_modificar);

        // Inicializar vistas
        Button btnGuardar = findViewById(R.id.btn_guardar);
        Button btnCancelar = findViewById(R.id.btn_cancelar);
        btnSeleccionarImagen = findViewById(R.id.btn_seleccionar_imagen);
        edtNombre = findViewById(R.id.edt_nombre);
        edtPosicion = findViewById(R.id.edt_posicion);
        edtTelefono = findViewById(R.id.edt_telefono);
        imgJugador = findViewById(R.id.img_jugador);

        // Recuperar datos del jugador si existen
        jugador = (Jugador) getIntent().getSerializableExtra("jugador");

        if (jugador != null) {
            edtNombre.setText(jugador.getNombre());
            edtPosicion.setText(jugador.getPosicion());
            edtTelefono.setText(jugador.getTelefonoJugador());
            // Si ya tiene una imagen, mostrarla
            if (jugador.getImagenUri() != null) {
                imgJugador.setImageURI(Uri.parse(jugador.getImagenUri()));
            }
            btnGuardar.setText("Modificar");
        }

        // Configurar eventos
        btnSeleccionarImagen.setOnClickListener(v -> seleccionarImagen());

        btnGuardar.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString();
            String posicion = edtPosicion.getText().toString();
            String telefono = edtTelefono.getText().toString();

            if (nombre.isEmpty() || posicion.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(InsertarModificarJugador.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (jugador == null) {
                // Crear un nuevo jugador
                Jugador nuevoJugador = new Jugador(
                        nombre,
                        posicion,
                        R.drawable.lebron_james,
                        imagenUri != null ? imagenUri.toString() : null,
                        "web",
                        10,
                        20,
                        telefono,
                        4.5f
                );

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nuevoJugador", nuevoJugador);
                setResult(RESULT_OK, resultIntent);
            } else {
                // Modificar jugador existente
                jugador.setNombre(nombre);
                jugador.setPosicion(posicion);
                jugador.setTelefonoJugador(telefono);
                if (imagenUri != null) {
                    jugador.setImagenUri(imagenUri.toString());
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("jugadorModificado", jugador);
                setResult(RESULT_OK, resultIntent);
            }
            finish();
        });

        btnCancelar.setOnClickListener(v -> finish());
    }

    // Método para seleccionar imagen desde la galería
    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            imagenUri = data.getData(); // Obtener la URI de la imagen seleccionada
            imgJugador.setImageURI(imagenUri); // Mostrar la imagen seleccionada en el ImageView
        }
    }
}
