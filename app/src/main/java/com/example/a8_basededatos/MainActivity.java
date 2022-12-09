package com.example.a8_basededatos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a8_basededatos.controllers.AlumnoController;
import com.example.a8_basededatos.modelos.Alumno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Alumno> listaDeAlumnos;
    private RecyclerView recyclerView;
    private AdapterAlumnos adaptadorAlumnos;
    private AlumnoController alumnosController;
    private FloatingActionButton fabNuevoAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Inicio de la app");

        alumnosController = new AlumnoController(MainActivity.this);

        recyclerView = findViewById(R.id.recyclerAlumnos);
        fabNuevoAlumno = findViewById(R.id.btnNuevoAlumno);

        listaDeAlumnos = new ArrayList<>();
        adaptadorAlumnos = new AdapterAlumnos(listaDeAlumnos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorAlumnos);

        actualizarListaAlumnos();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Alumno alumnoSeleccionada = listaDeAlumnos.get(position);
                Intent intent = new Intent(MainActivity.this, EditarAlumnoActivity.class);
                intent.putExtra("id", alumnoSeleccionada.getId());
                intent.putExtra("nombre", alumnoSeleccionada.getNombre());
                intent.putExtra("matricula", alumnoSeleccionada.getMatricula());
                intent.putExtra("apellidoPaterno", alumnoSeleccionada.getApellidoPaterno());
                intent.putExtra("apellidoMaterno", alumnoSeleccionada.getApellidoMaterno());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Alumno alumnoParaEliminar = listaDeAlumnos.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alumnosController.eliminarAlumno(alumnoParaEliminar);
                                actualizarListaAlumnos();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar al alumno " + alumnoParaEliminar.getNombre() + "?")
                        .create();
                dialog.show();

            }
        }));

        fabNuevoAlumno.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NuevoAlumnoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        actualizarListaAlumnos();
    }

    public void actualizarListaAlumnos()
    {
        if (adaptadorAlumnos == null) return;
        listaDeAlumnos = alumnosController.obtenerAlumnos();
        adaptadorAlumnos.setListaDeMascotas(listaDeAlumnos);
        adaptadorAlumnos.notifyDataSetChanged();
    }
}