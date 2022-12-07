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
    private AdapterAlumnos adaptadorMascotas;
    private AlumnoController mascotasController;
    private FloatingActionButton fabAgregarMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Inicio de la app");

        mascotasController = new AlumnoController(MainActivity.this);

        recyclerView = findViewById(R.id.recyclerAlumnos);
        fabAgregarMascota = findViewById(R.id.btnNuevoAlumno);

        recyclerView.setHasFixedSize(true);
        Configuration orientation = new Configuration();

        if(this.recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        }
        else if(this.recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        };
        listaDeAlumnos = new ArrayList<>();
        adaptadorMascotas = new AdapterAlumnos(listaDeAlumnos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorMascotas);

        refrescarListaDeMascotas();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Alumno alumnoSeleccionada = listaDeAlumnos.get(position);
                Intent intent = new Intent(MainActivity.this, EditarAlumnoActivity.class);
                intent.putExtra("idMascota", alumnoSeleccionada.getId());
                intent.putExtra("nombreMascota", alumnoSeleccionada.getNombre());
                intent.putExtra("edadMascota", alumnoSeleccionada.getEdad());
                intent.putExtra("apellidoPaterno", alumnoSeleccionada.getApellidoPaterno());

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
                                mascotasController.eliminarMascota(alumnoParaEliminar);
                                refrescarListaDeMascotas();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar a la mascota " + alumnoParaEliminar.getNombre() + "?")
                        .create();
                dialog.show();

            }
        }));

        fabAgregarMascota.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NuevoAlumnoActivity.class);
                startActivity(intent);
            }
        });


        fabAgregarMascota.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Acerca de")
                        .setMessage("CRUD de Android con SQLite creado por AdrianGutNa ")
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogo, int which) {
                                dialogo.dismiss();
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        refrescarListaDeMascotas();
    }

    public void refrescarListaDeMascotas()
    {
        if (adaptadorMascotas == null) return;
        listaDeAlumnos = mascotasController.obtenerMascotas();
        adaptadorMascotas.setListaDeMascotas(listaDeAlumnos);
        adaptadorMascotas.notifyDataSetChanged();
    }
}