package com.diegoppg.tutorialapp.modelo;

public class Perro {
    //Atributos
    private String nombre;
    private String raza;
    private int edad;
    private boolean isPeligroso;

    public Perro(String nombre, String raza, int edad, boolean isPeligroso) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.isPeligroso = isPeligroso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isPeligroso() {
        return isPeligroso;
    }

    public void setPeligroso(boolean peligroso) {
        isPeligroso = peligroso;
    }


}
