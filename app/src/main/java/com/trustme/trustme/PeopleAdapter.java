package com.trustme.trustme;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PeopleAdapter  extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private ArrayList<People> list;
    private OnItemClickListener listener;

    public PeopleAdapter(ArrayList<People> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Busco el archivo de diseño que será mi vistita
        //Guardar la vista como u objeto
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Poner datos y agregar acciones
        People people= list.get(position);

        holder.txtName.setText(people.getName());
        holder.txtDescription.setText(people.getDescription());
        holder.txtPhone.setText(people.getPhone().toString());

        holder.onClick(listener, people);
    }

    @Override
    public int getItemCount() {
        //Indicar cuantos elementos tiene la lista
        return list.size();
    }

    //Esta clase modela el diseño de la vistita y se buscan los elementos por separado

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtDescription;
        private TextView txtPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPhone = itemView.findViewById(R.id.txtPhone);
        }

        //Crear la accion que configura el click sobre un elemento
        //Pasamos al objeto los datos que selecciona tu clase
        public void onClick(OnItemClickListener listener, People people) {
            itemView.setOnClickListener(v-> listener.onItemClick(people));
        }
    }

    //Creación de un contrato (comportamiento al actuar) para accionar el click
    //Sobre un elemento de la lista
    public interface OnItemClickListener {
        void onItemClick(People people);
    }

    public void addPeople(People people) {
        list.add(people);
        notifyDataSetChanged();
    }
}
