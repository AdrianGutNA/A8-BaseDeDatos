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

public class NuevoAlumnoActivity extends AppCompatActivity {

    private EditText etNombre, etEdad;

    private Button btnAgregarMascota;
    private EditText txtMatricula;
    private EditText txtNombre;
    private EditText txtApellidoPaterno;
    private EditText txtAapellidoMaterno;
    private EditText txtSexo;
    private EditText txtFechaNacimiento;

    private AlumnoController alumnoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Nuevo Alumno");

        setContentView(R.layout.activity_nuevo_alumno);

        etNombre                = findViewById(R.id.txtNombre);
        etEdad                  = findViewById(R.id.txtMatricula);
        btnAgregarMascota       = findViewById(R.id.btnGuardar);

        txtMatricula            = findViewById(R.id.txtMatricula);
        txtNombre               = findViewById(R.id.txtNombre);
        txtApellidoPaterno      = findViewById(R.id.txtApellidoP);
        txtAapellidoMaterno     = findViewById(R.id.txtApellidoM);
        txtSexo                 = findViewById(R.id.txtSexo);
        txtFechaNacimiento      = findViewById(R.id.txtFechaNacimiento);

        alumnoController = new AlumnoController(NuevoAlumnoActivity.this);

        btnAgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etNombre.setError(null);
                etEdad.setError(null);
                txtApellidoPaterno.setError(null);

                String nombre = etNombre.getText().toString();
                String apellidoPaterno = txtApellidoPaterno.getText().toString();
                String edadComoCadena = etEdad.getText().toString();

                if ("".equals(nombre)) {
                    etNombre.setError("Escribe el nombre del Alumno");
                    etNombre.requestFocus();
                    return;
                }
                if ("".equals(apellidoPaterno)) {
                    txtApellidoPaterno.setError("Escribe el apellido paterno del Alumno");
                    txtApellidoPaterno.requestFocus();
                    return;
                }
                if ("".equals(edadComoCadena)) {
                    etEdad.setError("Escribe la edad del alumno");
                    etEdad.requestFocus();
                    return;
                }

                int edad;
                try {
                    edad = Integer.parseInt(etEdad.getText().toString());
                } catch (NumberFormatException e) {
                    etEdad.setError("Escribe un número");
                    etEdad.requestFocus();
                    return;
                }
                Alumno nuevaAlumno = new Alumno(nombre, edad,apellidoPaterno);
                long id = alumnoController.nuevaMascota(nuevaAlumno);
                if (id == -1) {
                    Toast.makeText(NuevoAlumnoActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });


    }
}
