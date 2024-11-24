package com.example.aplicaciongestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class JugadorAdapter extends BaseAdapter {

    private Context context;
    private List<Jugador> jugadores;

    public JugadorAdapter(Context context, List<Jugador> jugadores) {
        this.context = context;
        this.jugadores = jugadores;
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

        ImageView imagenJugador = convertView.findViewById(R.id.imagenJugador);
        TextView nombreJugador = convertView.findViewById(R.id.nombreJugador);
        TextView posicionJugador = convertView.findViewById(R.id.posicionJugador);
        TextView urlWeb = convertView.findViewById(R.id.urlWeb);
        TextView telefono = convertView.findViewById(R.id.telefono);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);
        RatingBar ratingBar = convertView.findViewById(R.id.ratingBar);

        Jugador jugador = jugadores.get(position);

        nombreJugador.setText(jugador.getNombre());
        posicionJugador.setText(jugador.getPosicion());
        urlWeb.setText(jugador.getWebEquipo());
        telefono.setText(String.valueOf(jugador.getTelefonoJugador()));
        ratingBar.setRating(jugador.getValoracionJugador());

        imagenJugador.setImageResource(jugador.getImagen());

        return convertView;
    }

}
