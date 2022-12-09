package com.example.a8_basededatos.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a8_basededatos.daoAlumnos;
import com.example.a8_basededatos.modelos.Alumno;

import java.util.ArrayList;

public class AlumnoController {
    private daoAlumnos daoAlumnos;
    private String NOMBRE_TABLA = "v1";

    public AlumnoController(Context contexto) {
        daoAlumnos = new daoAlumnos(contexto);
    }

    public int eliminarAlumno(Alumno alumno) {

        SQLiteDatabase baseDeDatos = daoAlumnos.getWritableDatabase();
        String[] argumentos = {String.valueOf(alumno.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevoAlumno(Alumno alumno) {
        SQLiteDatabase baseDeDatos = daoAlumnos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", alumno.getNombre());
        valoresParaInsertar.put("matricula", alumno.getMatricula());
        valoresParaInsertar.put("apellidoPaterno", alumno.getApellidoPaterno());
        valoresParaInsertar.put("apellidoMaterno", alumno.getApellidoMaterno());
        valoresParaInsertar.put("sexo", alumno.getSexo());
        valoresParaInsertar.put("fechaNacimiento", alumno.getFechaNacimiento());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Alumno alumnoEditada) {
        SQLiteDatabase baseDeDatos = daoAlumnos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", alumnoEditada.getNombre());
        valoresParaActualizar.put("matricula", alumnoEditada.getMatricula());
        valoresParaActualizar.put("apellidoPaterno", alumnoEditada.getApellidoPaterno());
        valoresParaActualizar.put("apellidoMaterno", alumnoEditada.getApellidoMaterno());
        valoresParaActualizar.put("sexo", alumnoEditada.getSexo());
        valoresParaActualizar.put("fechaNacimiento", alumnoEditada.getFechaNacimiento());

        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(alumnoEditada.getId())};

        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Alumno> obtenerAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        SQLiteDatabase baseDeDatos = daoAlumnos.getReadableDatabase();

        String[] columnasAConsultar = {"nombre", "matricula","apellidoPaterno","apellidoMaterno","sexo","fechaNacimiento", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null)
        {
            return alumnos;
        }

        if (!cursor.moveToFirst()) return alumnos;
        do
        {
            String nombreBD = cursor.getString(0);
            int matriculaBD = cursor.getInt(1);
            String apellidoPaternoBD = cursor.getString(2);
            String apellidoMaternoBD = cursor.getString(3);
            String sexoBD = cursor.getString(4);
            String fechaNacimientoBD = cursor.getString(5);
            long idAlumno = cursor.getLong(6);

            Alumno alumnoObtenidaDeBD = new Alumno(nombreBD, matriculaBD,apellidoPaternoBD,apellidoMaternoBD,sexoBD,fechaNacimientoBD, idAlumno);
            alumnos.add(alumnoObtenidaDeBD);
        } while (cursor.moveToNext());

        cursor.close();
        return alumnos;
    }
}