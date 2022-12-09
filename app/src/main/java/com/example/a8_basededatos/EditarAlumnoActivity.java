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

    private EditText    txtMatricula;
    private EditText    txtNombre;
    private EditText    txtApellidoPaterno;
    private EditText    txtApellidoMaterno;
    private EditText    txtSexo;
    private EditText    txtFechaNacimiento;
    private Button      btnGuardarCambios;
    private Alumno      alumno;

    private AlumnoController alumnoController;

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

        alumnoController            = new AlumnoController(EditarAlumnoActivity.this);

        long    idAlumno            = extras.getLong("id");
        String  nombreAlumno        = extras.getString("nombre");
        int     matriculaAlumno     = extras.getInt("matricula");
        String  apellidoPaterno     = extras.getString("apellidoPaterno");
        String  apellidoMaterno     = extras.getString("apellidoMaterno");
        String  sexo                = extras.getString("sexo");
        String  fechaNacimiento     = extras.getString("fechaNacimiento");
        alumno                      = new Alumno(nombreAlumno, matriculaAlumno, apellidoPaterno,apellidoMaterno, sexo, fechaNacimiento, idAlumno);


        txtMatricula                = findViewById(R.id.txtMatricula);
        txtNombre                   = findViewById(R.id.txtNombre);
        txtApellidoPaterno          = findViewById(R.id.txtApellidoP);
        txtApellidoMaterno          = findViewById(R.id.txtApellidoM);
        txtSexo                     = findViewById(R.id.txtSexo);
        txtFechaNacimiento          = findViewById(R.id.txtFechaNacimiento);
        btnGuardarCambios           = findViewById(R.id.btnGuardar);


        txtMatricula.setText(String.valueOf(alumno.getMatricula()));
        txtNombre.setText(alumno.getNombre());
        txtApellidoPaterno.setText(alumno.getApellidoPaterno());
        txtApellidoMaterno.setText(alumno.getApellidoMaterno());
        txtSexo.setText(alumno.getSexo());
        txtFechaNacimiento.setText(alumno.getFechaNacimiento());

        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtNombre.setError(null);
                txtMatricula.setError(null);
                txtApellidoPaterno.setError(null);
                txtApellidoMaterno.setError(null);
                txtSexo.setError(null);
                txtFechaNacimiento.setError(null);

                String nuevoNombre          = txtNombre.getText().toString();
                String nuevaMatricula1      = txtMatricula.getText().toString();
                String nuevoApellidoPaterno = txtApellidoPaterno.getText().toString();
                String nuevoApellidoMaterno = txtApellidoMaterno.getText().toString();
                String sexo                 = txtSexo.getText().toString();
                String fechaNacimiento      = txtFechaNacimiento.getText().toString();

                if (nuevaMatricula1.isEmpty()) {
                    txtMatricula.setError("Escribe la matricula");
                    txtMatricula.requestFocus();
                    return;
                }
                int nuevaMatricula;
                try {
                    nuevaMatricula = Integer.parseInt(nuevaMatricula1);
                } catch (NumberFormatException e) {
                    txtMatricula.setError("Escribe un número");
                    txtMatricula.requestFocus();
                    return;
                }


                if (nuevoNombre.isEmpty()) {
                    txtNombre.setError("Escribe el nombre");
                    txtNombre.requestFocus();
                    return;
                }
                if (nuevoApellidoPaterno.isEmpty()) {
                    txtApellidoPaterno.setError("Escribe el apellido Paterno");
                    txtApellidoPaterno.requestFocus();
                    return;
                }
                if (nuevoApellidoMaterno.isEmpty()) {
                    txtApellidoMaterno.setError("Escribe el apellido Materno");
                    txtApellidoMaterno.requestFocus();
                    return;
                }
                if (sexo.isEmpty()) {
                    txtSexo.setError("Escribe el sexo del alumno");
                    txtSexo.requestFocus();
                    return;
                }
                if (fechaNacimiento.isEmpty()) {
                    txtFechaNacimiento.setError("Escribe la fecha de nacimiento del alumno");
                    txtFechaNacimiento.requestFocus();
                    return;
                }

                Alumno alumnoConNuevosCambios = new Alumno(nuevoNombre, nuevaMatricula,nuevoApellidoPaterno,nuevoApellidoMaterno,sexo,fechaNacimiento, alumno.getId());
                int filasModificadas = alumnoController.guardarCambios(alumnoConNuevosCambios);
                if (filasModificadas != 1) {

                    Toast.makeText(EditarAlumnoActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });
    }
}
