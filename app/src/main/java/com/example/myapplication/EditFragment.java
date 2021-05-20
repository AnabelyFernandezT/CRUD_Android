package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.data.DbEmpresa;
import com.example.myapplication.entidades.Empresas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditFragment extends AppCompatActivity {

    Spinner lstEmpresa;
    String tipo_empresa;
    EditText txtNombreEmpresa ;
    TextView txtTipoEmpresa;
    FloatingActionButton fabEditar, fabEliminar;
    Empresas empresa;
    Button btnGuardar;
    int id = 0;
    boolean correcto = false;
    final DbEmpresa dbContactos = new DbEmpresa(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);
        init();
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        empresa = dbContactos.verEmpresas(id);
        if(empresa != null){
            txtNombreEmpresa.setText(empresa.getNombre_empresa());
           txtTipoEmpresa.setText(empresa.getTipo_empresa());
        }
    }

    private void init(){
        lstEmpresa = findViewById(R.id.lista_empresas);
        lstEmpresa.setVisibility(View.GONE);
        lstEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipo_empresa=(String) lstEmpresa.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtNombreEmpresa = (EditText)findViewById(R.id.txtnombre);
        txtTipoEmpresa = (TextView) findViewById(R.id.tipo_empresa);
        txtNombreEmpresa.setEnabled(false);
        fabEditar = findViewById(R.id.edit);
        fabEliminar = findViewById(R.id.delete);
        btnGuardar = (Button)findViewById(R.id.button_second);
        btnGuardar.setVisibility(View.GONE);

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNombreEmpresa.setEnabled(true);
                txtTipoEmpresa.setVisibility(View.GONE);
                lstEmpresa.setVisibility(View.VISIBLE);
                btnGuardar.setVisibility(View.VISIBLE);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombreEmpresa.getText().toString().equals("") && !tipo_empresa.equals("")) {
                    correcto = dbContactos.editarEmpresa(id, txtNombreEmpresa.getText().toString(), tipo_empresa);

                    if(correcto){
                        Toast.makeText(EditFragment.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditFragment.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditFragment.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditFragment.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbContactos.eliminarEmpresa(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void verRegistro(){
        txtNombreEmpresa.setEnabled(false);
        btnGuardar.setVisibility(View.GONE);
        lstEmpresa.setVisibility(View.GONE);
        txtTipoEmpresa.setText(empresa.getTipo_empresa());
        txtTipoEmpresa.setVisibility(View.VISIBLE);

    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}