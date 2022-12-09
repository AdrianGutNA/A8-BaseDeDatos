package com.example.a8_basededatos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a8_basededatos.modelos.Alumno;

import java.util.List;


public class AdapterAlumnos extends RecyclerView.Adapter<AdapterAlumnos.MyViewHolder> {

    private List<Alumno> listaDeAlumnos;

    public void setListaDeMascotas(List<Alumno> listaDeAlumnos) {
        this.listaDeAlumnos = listaDeAlumnos;
    }

    public AdapterAlumnos(List<Alumno> alumnos) {
        this.listaDeAlumnos = alumnos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaAlumno = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new MyViewHolder(filaAlumno);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Alumno alumno           = listaDeAlumnos.get(i);

        String nombre           = alumno.getNombre();
        String apellidoPaterno  = alumno.getApellidoPaterno();
        String apellidoMaterno  = alumno.getApellidoMaterno();
        int matricula           = alumno.getMatricula();

        myViewHolder.nombre.setText(nombre + " " + apellidoPaterno + " " + apellidoMaterno);
        myViewHolder.matricula.setText(String.valueOf(matricula));
    }

    @Override
    public int getItemCount() {
        return listaDeAlumnos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, matricula;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.txtvNombreCompleto);
            this.matricula = itemView.findViewById(R.id.txtvMatricula);
        }
    }
}
