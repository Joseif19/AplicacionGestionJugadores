package com.example.aplicaciongestion;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class JugadorAdapter extends BaseAdapter {

    private Context context;
    private List<Jugador> jugadores;

    private final int requestModificarJugador;

    public JugadorAdapter(Context context, List<Jugador> jugadores, int requestModificarJugador) {
        this.context = context;
        this.jugadores = jugadores;
        this.requestModificarJugador = requestModificarJugador;
    }


    @Override
    public int getCount() {
        return jugadores.size();
    }

    @Override
    public Object getItem(int position) {
        return jugadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_jugador, parent, false);
        }

        // Vincular vistas
        ImageView imagenJugador = convertView.findViewById(R.id.imagenJugador);
        TextView nombreJugador = convertView.findViewById(R.id.nombreJugador);
        TextView posicionJugador = convertView.findViewById(R.id.posicionJugador);
        TextView urlWeb = convertView.findViewById(R.id.urlWeb);
        TextView telefono = convertView.findViewById(R.id.telefono);
        RatingBar ratingBar = convertView.findViewById(R.id.ratingBar);

        // Obtener el jugador actual
        Jugador jugador = jugadores.get(position);

        // Asignar datos a las vistas
        imagenJugador.setImageResource(jugador.getImagen());
        nombreJugador.setText(jugador.getNombre());
        posicionJugador.setText(jugador.getPosicion());
        urlWeb.setText(jugador.getWebEquipo());
        telefono.setText(jugador.getTelefonoJugador());
        ratingBar.setRating(jugador.getValoracionJugador());

        // Configurar el evento del botón Modificar
        ImageButton botonModificar = convertView.findViewById(R.id.botonModificar);
        botonModificar.setOnClickListener(v -> {
            Intent intent = new Intent(context, InsertarModificarJugador.class);
            intent.putExtra("jugador", jugador); // Jugador debe implementar Serializable
            ((MainActivity) context).startActivityForResult(intent, MainActivity.REQUEST_MODIFICAR_JUGADOR);
        });

        ImageButton botonBorrar = convertView.findViewById(R.id.botonBorrar);
        botonBorrar.setOnClickListener(v -> {
            // Eliminar el jugador de la lista
            Jugador jugadorEliminado = jugadores.get(position);
            jugadores.remove(position);
            notifyDataSetChanged(); // Actualizar el ListView

            // Mostrar el Toast personalizado
            showCustomToast(context, jugadorEliminado);
        });

        return convertView;
    }

    private void showCustomToast(Context context, Jugador jugador) {
        // Inflar el layout del toast personalizado
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_eliminar, null);

        // Configurar los elementos del layout
        ImageView imgJugador = layout.findViewById(R.id.imgJugador);
        TextView tvNombreJugador = layout.findViewById(R.id.nombreJugador);

        // Asignar datos del jugador
        tvNombreJugador.setText(jugador.getNombre());
        // Aquí deberías asignar la imagen del jugador si tienes una URL o un recurso
        // Por ejemplo: imgJugador.setImageResource(jugador.getImagenResourceId());

        // Crear el Toast
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout); // Asignar el layout personalizado
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }



}
