package com.example.myapplication.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.EditFragment;
import com.example.myapplication.R;
import com.example.myapplication.SecondFragment;
import com.example.myapplication.entidades.Empresas;

import java.util.ArrayList;

public class ListaEmpresasAdapter extends RecyclerView.Adapter<ListaEmpresasAdapter.MyViewHolder> {

    ArrayList<Empresas> listaEmpresas;
    Context mcontext;

    public ListaEmpresasAdapter(Context mcontext, ArrayList<Empresas> listaEmpresas){
        this.listaEmpresas = listaEmpresas;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.lista_item_contacto,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.viewNombre.setText(listaEmpresas.get(position).getNombre_empresa());
        holder.viewTipo.setText(listaEmpresas.get(position).getTipo_empresa());

    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewTipo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTipo = itemView.findViewById(R.id.viewTipo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EditFragment.class);
                    intent.putExtra ("ID", listaEmpresas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

}
