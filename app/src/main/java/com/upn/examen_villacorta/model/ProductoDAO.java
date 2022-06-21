package com.upn.examen_villacorta.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upn.examen_villacorta.entity.Producto;
import com.upn.examen_villacorta.util.ProductoTB;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    ProductoTB productoTB;
    SQLiteDatabase db;
    Context context;

    public ProductoDAO(Context context){
        productoTB = new ProductoTB(context);
        this.context = context;
    }

    public void abrirBD(){
        db = productoTB.getWritableDatabase();
    }

    public String registrarProducto(Producto producto){
        String mensaje = "";

        try{
            ContentValues valores = new ContentValues();
            valores.put("marca", producto.getMarca());
            valores.put("color", producto.getColor());
            valores.put("serie", producto.getSerie());
            valores.put("precio_venta", producto.getPrecio_venta());
            valores.put("anio_fabricacion", producto.getAnio_fabricacion());

            long resultado = db.insert("productos", null, valores);
            if (resultado != -1){
                mensaje = "Se registró el producto correctamente";
            } else {
                mensaje = "No se pudo registrar el producto";
            }

        } catch (Exception e){
            Log.d("Error => ", e.getMessage());
        }

        return mensaje;
    }

    public List<Producto> listarProductos(){
        List<Producto> listaProductos = new ArrayList<>();
        try{
            Cursor c = db.rawQuery("SELECT * FROM productos", null);
            while(c.moveToNext()){
                listaProductos.add(new Producto(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getDouble(4),
                        c.getString(5)));
            }
        } catch (Exception e){
            Log.d("Error => ", e.getMessage());
        }

        return listaProductos;
    }

    public String modificarProducto(Producto producto){
        String mensaje = "";

        try{
            ContentValues valores = new ContentValues();
            valores.put("marca", producto.getMarca());
            valores.put("color", producto.getColor());
            valores.put("serie", producto.getSerie());
            valores.put("precio_venta", producto.getPrecio_venta());
            valores.put("anio_fabricacion", producto.getAnio_fabricacion());

            long resultado = db.update("productos", valores, "id="+producto.getId(), null);
            if (resultado != -1){
                mensaje = "Se modificó el producto correctamente";
            } else {
                mensaje = "No se pudo modificar el producto";
            }

        } catch (Exception e){
            Log.d("Error => ", e.getMessage());
        }

        return mensaje;
    }

    public String eliminarProducto(int id){
        String mensaje = "";

        try{
            long resultado = db.delete("productos", "id="+id, null);
            if (resultado != -1){
                mensaje = "Se eliminó el producto correctamente";
            } else {
                mensaje = "No se pudo eliminar el producto";
            }

        } catch (Exception e){
            Log.d("Error => ", e.getMessage());
        }

        return mensaje;
    }
}
