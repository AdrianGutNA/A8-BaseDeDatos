package com.example.a8_basededatos;

import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a8_basededatos.controllers.AlumnoController;
import com.example.a8_basededatos.modelos.Alumno;

public class EditarAlumnoActivity extends AppCompatActivity {
    private EditText etEditarNombre, etEditarEdad, txtEditarApellidoP;
    private Button btnGuardarCambios;
    private Alumno alumno;
    private AlumnoController mascotasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);
        getSupportActionBar().setTitle("Editar Alumno");

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        mascotasController      = new AlumnoController(EditarAlumnoActivity.this);

        long idMascota          = extras.getLong("idMascota");
        String nombreMascota    = extras.getString("nombreMascota");
        int edadMascota         = extras.getInt("edadMascota");
        String apellidoPaterno    = extras.getString("apellidoPaterno");

        alumno                  = new Alumno(nombreMascota, edadMascota, apellidoPaterno, idMascota);


        etEditarEdad            = findViewById(R.id.txtMatricula);
        etEditarNombre          = findViewById(R.id.txtNombre);
        txtEditarApellidoP      = findViewById(R.id.txtApellidoP);
        btnGuardarCambios       = findViewById(R.id.btnGuardar);


        etEditarEdad.setText(String.valueOf(alumno.getEdad()));
        etEditarNombre.setText(alumno.getNombre());
        txtEditarApellidoP.setText(alumno.getApellidoPaterno());


        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etEditarNombre.setError(null);
                etEditarEdad.setError(null);
                txtEditarApellidoP.setError(null);

                String nuevoNombre = etEditarNombre.getText().toString();
                String posibleNuevaEdad = etEditarEdad.getText().toString();
                String nuevoApellidoPaterno = txtEditarApellidoP.getText().toString();


                if (nuevoNombre.isEmpty()) {
                    etEditarNombre.setError("Escribe el nombre");
                    etEditarNombre.requestFocus();
                    return;
                }
                if (posibleNuevaEdad.isEmpty()) {
                    etEditarEdad.setError("Escribe la edad");
                    etEditarEdad.requestFocus();
                    return;
                }
                if (nuevoApellidoPaterno.isEmpty()) {
                    txtEditarApellidoP.setError("Escribe el apellido Paterno");
                    txtEditarApellidoP.requestFocus();
                    return;
                }


                int nuevaEdad;
                try {
                    nuevaEdad = Integer.parseInt(posibleNuevaEdad);
                } catch (NumberFormatException e) {
                    etEditarEdad.setError("Escribe un número");
                    etEditarEdad.requestFocus();
                    return;
                }

                Alumno alumnoConNuevosCambios = new Alumno(nuevoNombre, nuevaEdad,nuevoApellidoPaterno, alumno.getId());
                int filasModificadas = mascotasController.guardarCambios(alumnoConNuevosCambios);
                if (filasModificadas != 1) {

                    Toast.makeText(EditarAlumnoActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });
    }
}
