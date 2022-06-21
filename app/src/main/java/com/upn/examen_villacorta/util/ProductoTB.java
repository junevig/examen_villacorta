package com.upn.examen_villacorta.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductoTB extends SQLiteOpenHelper {

    public ProductoTB(Context context){
        super(context, "catalogo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE productos" +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "marca TEXT NOT NULL," +
                        "serie TEXT NOT NULL," +
                        "precio_venta FLOAT NOT NULL," +
                        "anio_fabricacion DATE NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
