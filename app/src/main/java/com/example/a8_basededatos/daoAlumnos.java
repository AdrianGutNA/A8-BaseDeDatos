package com.example.a8_basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class daoAlumnos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "alumnos",
            NOMBRE_TABLA = "v1";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public daoAlumnos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombre text, matricula integer, apellidoPaterno text, apellidoMaterno text)", NOMBRE_TABLA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
