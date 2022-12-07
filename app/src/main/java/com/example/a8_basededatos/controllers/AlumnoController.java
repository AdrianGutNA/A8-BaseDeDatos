package com.example.a8_basededatos.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a8_basededatos.daoAlumnos;
import com.example.a8_basededatos.modelos.Alumno;

import java.util.ArrayList;



public class AlumnoController {
    private daoAlumnos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "v1";

    public AlumnoController(Context contexto) {
        ayudanteBaseDeDatos = new daoAlumnos(contexto);
    }

    public int eliminarMascota(Alumno alumno) {

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(alumno.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaMascota(Alumno alumno) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", alumno.getNombre());
        valoresParaInsertar.put("edad", alumno.getEdad());
        valoresParaInsertar.put("apellidoPaterno", alumno.getApellidoPaterno());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Alumno alumnoEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", alumnoEditada.getNombre());
        valoresParaActualizar.put("edad", alumnoEditada.getEdad());
        valoresParaActualizar.put("apellidoPaterno", alumnoEditada.getApellidoPaterno());

        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(alumnoEditada.getId())};

        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Alumno> obtenerMascotas() {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();

        String[] columnasAConsultar = {"nombre", "edad","apellidoPaterno", "id"};
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
            String nombreObtenidoDeBD = cursor.getString(0);
            int edadObtenidaDeBD = cursor.getInt(1);
            String apellidoPaternoObtenidoDeBD = cursor.getString(2);
            long idMascota = cursor.getLong(3);

            Alumno alumnoObtenidaDeBD = new Alumno(nombreObtenidoDeBD, edadObtenidaDeBD,apellidoPaternoObtenidoDeBD, idMascota);
            alumnos.add(alumnoObtenidaDeBD);
        } while (cursor.moveToNext());

        cursor.close();
        return alumnos;
    }
}