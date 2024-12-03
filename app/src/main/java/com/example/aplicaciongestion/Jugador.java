package com.example.aplicaciongestion;

import java.io.Serializable; // Añadido para usar Serializable

public class Jugador implements Serializable { // Implementar Serializable
    private String nombre, posicion, webEquipo, telefonoJugador, ImagenUri;
    private int dorsal, mediaPuntosPorPartido, imagen;
    private float valoracionJugador;

    public Jugador() {
    }

    public Jugador(String nombre, String posicion, int imagen, String ImagenUri, String webEquipo, int dorsal, int mediaPuntosPorPartido, String telefonoJugador, float valoracionJugador) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.imagen = imagen;
        this.ImagenUri = ImagenUri;
        this.webEquipo = webEquipo;
        this.dorsal = dorsal;
        this.mediaPuntosPorPartido = mediaPuntosPorPartido;
        this.telefonoJugador = telefonoJugador;
        this.valoracionJugador = valoracionJugador;
    }

    public Jugador(String nombre, String posicion, String telefono) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.telefonoJugador = telefonoJugador;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getImagenUri() { return ImagenUri; }

    public void setImagenUri(String imagenUri) { ImagenUri = imagenUri; }

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
