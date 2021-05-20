package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.entidades.Empresas;

import java.util.ArrayList;

public class DbEmpresa extends DbHelper {
    Context context;
    public DbEmpresa(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEmpresa(String nombre_empresa, String tipo_empresa ) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_empresa", nombre_empresa);
            values.put("tipo_empresa", tipo_empresa);

            id = db.insert(TABLE_EMPRESAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }

    public ArrayList<Empresas> mostrarEmpresas() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Empresas> listaEmpresas = new ArrayList<>();
        Empresas empresas;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_EMPRESAS, null);

        if (cursorContactos.moveToFirst()) {
            do {
                empresas = new Empresas();
                empresas.setId(cursorContactos.getInt(0));
                empresas.setNombre_empresa(cursorContactos.getString(1));
                empresas.setTipo_empresa(cursorContactos.getString(2));
                listaEmpresas.add(empresas);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaEmpresas;
    }

    public Empresas verEmpresas(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Empresas contacto = null;
        Cursor cursorEmpresas;

        cursorEmpresas = db.rawQuery("SELECT * FROM " + TABLE_EMPRESAS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorEmpresas.moveToFirst()) {
            contacto = new Empresas();
            contacto.setId(cursorEmpresas.getInt(0));
            contacto.setNombre_empresa(cursorEmpresas.getString(1));
            contacto.setTipo_empresa(cursorEmpresas.getString(2));
        }

        cursorEmpresas.close();

        return contacto;
    }

    public boolean editarEmpresa(int id, String nombre, String tipo) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_EMPRESAS + " SET nombre_empresa = '" + nombre + "', tipo_empresa = '" + tipo + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarEmpresa(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_EMPRESAS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}
