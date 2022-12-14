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

    private Button              btnGuardar;
    private EditText            txtMatricula;
    private EditText            txtNombre;
    private EditText            txtApellidoPaterno;
    private EditText            txtApellidoMaterno;
    private EditText            txtSexo;
    private EditText            txtFechaNacimiento;
    private AlumnoController    alumnoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);
        getSupportActionBar().setTitle("Nuevo Alumno");

        txtMatricula            = findViewById(R.id.txtMatricula);
        txtNombre               = findViewById(R.id.txtNombre);
        txtApellidoPaterno      = findViewById(R.id.txtApellidoP);
        txtApellidoMaterno      = findViewById(R.id.txtApellidoM);
        txtSexo                 = findViewById(R.id.txtSexo);
        txtFechaNacimiento      = findViewById(R.id.txtFechaNacimiento);
        btnGuardar              = findViewById(R.id.btnGuardar);


        alumnoController = new AlumnoController(NuevoAlumnoActivity.this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtMatricula.setError(null);
                txtNombre.setError(null);
                txtApellidoPaterno.setError(null);
                txtApellidoMaterno.setError(null);
                txtSexo.setError(null);
                txtFechaNacimiento.setError(null);

                String nombre = txtNombre.getText().toString();
                String apellidoPaterno = txtApellidoPaterno.getText().toString();
                String apellidoMaterno = txtApellidoMaterno.getText().toString();
                String sexo = txtSexo.getText().toString();
                String fechaNacimiento = txtFechaNacimiento.getText().toString();
                String matriculaTexto = txtMatricula.getText().toString();

                if ("".equals(nombre)) {
                    txtNombre.setError("Escribe el nombre del Alumno");
                    txtNombre.requestFocus();
                    return;
                }
                if ("".equals(apellidoPaterno)) {
                    txtApellidoPaterno.setError("Escribe el apellido paterno del Alumno");
                    txtApellidoPaterno.requestFocus();
                    return;
                }
                if ("".equals(apellidoMaterno)) {
                    txtApellidoMaterno.setError("Escribe el apellido Materno del Alumno");
                    txtApellidoMaterno.requestFocus();
                    return;
                }
                if ("".equals(sexo)) {
                    txtSexo.setError("Escribe el sexo del Alumno");
                    txtSexo.requestFocus();
                    return;
                }
                if ("".equals(fechaNacimiento)) {
                    txtSexo.setError("Escribe la fecha de nacimiento del Alumno");
                    txtSexo.requestFocus();
                    return;
                }
                if ("".equals(matriculaTexto)) {
                    txtMatricula.setError("Escribe la matricula del alumno");
                    txtMatricula.requestFocus();
                    return;
                }

                int matricula;
                try {
                    matricula = Integer.parseInt(txtMatricula.getText().toString());
                } catch (NumberFormatException e) {
                    txtMatricula.setError("Escribe un n??mero");
                    txtMatricula.requestFocus();
                    return;
                }
                Alumno nuevaAlumno = new Alumno(nombre, matricula,apellidoPaterno,apellidoMaterno,sexo,fechaNacimiento);
                long id = alumnoController.nuevoAlumno(nuevaAlumno);
                if (id == -1) {
                    Toast.makeText(NuevoAlumnoActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });


    }
}
