package com.example.a8_basededatos.modelos;

public class Alumno {

    private  int matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private String fechaNacimiento;
    private int edad;

    private long id;

    public Alumno(String nombre, int edad, String apellidoPaterno) {
        this.nombre = nombre;
        this.edad = edad;
        this.apellidoPaterno = apellidoPaterno;
    }

    public Alumno(String nombre, int edad,String apellidoPaterno, long id) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
        this.apellidoPaterno = apellidoPaterno;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + " "+ apellidoPaterno+ '\'' +
                ", edad=" + edad +
                '}';
    }
}
