package com.example.aplicaciongestion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

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
        Button botonModificar = convertView.findViewById(R.id.botonModificar);
        botonModificar.setOnClickListener(v -> {
            Intent intent = new Intent(context, InsertarModificarJugador.class);
            intent.putExtra("jugador", jugador); // Jugador debe implementar Serializable
            ((MainActivity) context).startActivityForResult(intent, MainActivity.REQUEST_MODIFICAR_JUGADOR);
        });

        return convertView;
    }


}
