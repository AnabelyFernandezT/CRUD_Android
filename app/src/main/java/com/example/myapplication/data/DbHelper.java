package com.example.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "TBL_EMPRESA.db";
    public static final String TABLE_EMPRESAS = "t_empresa";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabas) {
        sqLiteDatabas.execSQL("CREATE TABLE " + TABLE_EMPRESAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_empresa TEXT NOT NULL," +
                "tipo_empresa TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EMPRESAS);
        onCreate(sqLiteDatabase);

    }
}
