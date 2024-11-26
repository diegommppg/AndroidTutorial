package com.diegoppg.tutorialapp.modelo;

public class Pato {

    private String nombre;
    private int edad;

    public Pato(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Pato(){
    }

    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
