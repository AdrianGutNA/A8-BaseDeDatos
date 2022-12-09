package com.example.a8_basededatos.modelos;

public class Alumno {

    private  int matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private String fechaNacimiento;

    private long id;

    public Alumno(String nombre, int matricula, String apellidoPaterno) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.apellidoPaterno = apellidoPaterno;
    }

    public Alumno(String nombre, int matricula,String apellidoPaterno, long id) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
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

    public int getMatricula() {
        return matricula;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno()
    {
        return apellidoMaterno;
    }

    public String getSexo()
    {
        return sexo;
    }

    public String getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + " "+ apellidoPaterno+ '\'' +
                ", matricula=" + matricula +
                '}';
    }
}
