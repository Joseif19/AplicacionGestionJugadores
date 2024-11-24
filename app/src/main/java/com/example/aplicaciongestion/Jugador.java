package com.example.aplicaciongestion;

public class Jugador {
    private String nombre, posicion, webEquipo, telefonoJugador;
    private int dorsal, mediaPuntosPorPartido, imagen;
    private float valoracionJugador;

    public Jugador() {
    }

    public Jugador(String nombre, String posicion, int imagen, String webEquipo, int dorsal, int mediaPuntosPorPartido, String telefonoJugador, float valoracionJugador) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.imagen = imagen;
        this.webEquipo = webEquipo;
        this.dorsal = dorsal;
        this.mediaPuntosPorPartido = mediaPuntosPorPartido;
        this.telefonoJugador = telefonoJugador;
        this.valoracionJugador = valoracionJugador;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getWebEquipo() {
        return webEquipo;
    }

    public void setWebEquipo(String webEquipo) {
        this.webEquipo = webEquipo;
    }

    public String getTelefonoJugador() {
        return telefonoJugador;
    }

    public void setTelefonoJugador(String telefonoJugador) {
        this.telefonoJugador = telefonoJugador;
    }

    public float getValoracionJugador() {
        return valoracionJugador;
    }

    public void setValoracionJugador(float valoracionJugador) {
        this.valoracionJugador = valoracionJugador;
    }
}


