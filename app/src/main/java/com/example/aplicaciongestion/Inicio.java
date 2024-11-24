package com.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

    private EditText usuarioInput, claveInput;
    private Button btnLogin;
    private List<Login> usuarios;
    private CheckBox mostrarContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioInput = findViewById(R.id.usuarioInput);
        claveInput = findViewById(R.id.claveInput);
        btnLogin = findViewById(R.id.btnLogin);
        mostrarContraseña = findViewById(R.id.mostrarContraseña);

        usuarios = new ArrayList<>();
        usuarios.add(new Login("usuario", "usuario"));
        usuarios.add(new Login("usuario2", "usuario2"));

        mostrarContraseña.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                claveInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
            } else {
                claveInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }

            claveInput.setSelection(claveInput.getText().length());
        });

        btnLogin.setOnClickListener(v -> validarUsuario());
    }

    private void validarUsuario() {
        String nombre = usuarioInput.getText().toString();
        String clave = claveInput.getText().toString();

        boolean esValido = false;

        for (Login usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getClave().equals(clave)) {
                esValido = true;
                break;
            }
        }

        if (esValido) {
            Intent intent = new Intent(Inicio.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(Inicio.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
