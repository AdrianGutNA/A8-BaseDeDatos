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
        View filaMascota = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new MyViewHolder(filaMascota);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Alumno alumno           = listaDeAlumnos.get(i);

        String nombreMascota    = alumno.getNombre();
        String apellidoPaterno  = alumno.getApellidoPaterno();
        int edadMascota         = alumno.getEdad();

        myViewHolder.nombre.setText(nombreMascota + " " + apellidoPaterno);
        myViewHolder.edad.setText(String.valueOf(edadMascota));
    }

    @Override
    public int getItemCount() {
        return listaDeAlumnos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, edad;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.txtvNombreCompleto);
            this.edad = itemView.findViewById(R.id.txtvMatricula);
        }
    }
}
