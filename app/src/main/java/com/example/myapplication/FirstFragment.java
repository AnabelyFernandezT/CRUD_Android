package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adaptadores.ListaEmpresasAdapter;
import com.example.myapplication.data.DbEmpresa;
import com.example.myapplication.entidades.Empresas;

import java.util.ArrayList;

public class FirstFragment extends Fragment {
    RecyclerView listaEmpresas;
    ArrayList<Empresas> listaArrayEmpresas;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    private void init(){

        listaEmpresas = getView().findViewById(R.id.recyclerviewEmpresas);
        listaEmpresas.setLayoutManager(new LinearLayoutManager(getContext()));
        DbEmpresa dbEmpresa= new DbEmpresa(getContext());
        listaArrayEmpresas = new ArrayList<>();
        ListaEmpresasAdapter adapter = new ListaEmpresasAdapter(getContext(), dbEmpresa.mostrarEmpresas());
        listaEmpresas.setAdapter(adapter);

    }
}