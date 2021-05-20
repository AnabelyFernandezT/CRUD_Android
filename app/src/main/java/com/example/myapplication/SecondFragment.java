package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.data.DbEmpresa;

public class SecondFragment extends Fragment {

    Spinner lstEmpresa;
    String tipo_empresa, nombre_empresa;
    EditText txtNombreEmpresa;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

            view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nombre_empresa = txtNombreEmpresa.getText().toString();
                    if(!txtNombreEmpresa.getText().toString().equals("")&& !tipo_empresa.equals("")) {
                        DbEmpresa dbEmpresa = new DbEmpresa(getContext());
                        long id= dbEmpresa.insertarEmpresa(nombre_empresa,tipo_empresa);
                        if (id > 0) {
                            Toast.makeText(getContext(), "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            //limpiar();
                            NavHostFragment.findNavController(SecondFragment.this)
                                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
                        } else {
                            Toast.makeText(getContext(), "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                    }

                }
            });
            view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            });
    }

    private void init(){
        lstEmpresa = (Spinner)getView().findViewById(R.id.lista_empresas);
        lstEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipo_empresa=(String) lstEmpresa.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtNombreEmpresa = (EditText) getView().findViewById(R.id.txtnombre);


    }
}