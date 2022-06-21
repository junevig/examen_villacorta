package com.upn.examen_villacorta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upn.examen_villacorta.entity.Producto;
import com.upn.examen_villacorta.model.ProductoDAO;

import java.util.ArrayList;
import java.util.List;

public class ListarProductosActivity extends AppCompatActivity {

    RecyclerView rvProductos;
    FloatingActionButton fl_btnRegistrarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);

        rvProductos = findViewById(R.id.rvProductos);
        fl_btnRegistrarProducto = findViewById(R.id.fl_btnRegistrarProducto);
        fl_btnRegistrarProducto.setOnClickListener(v -> startActivity(new Intent(ListarProductosActivity.this, RegistrarProductosActivity.class)));

        mostrarProductos();
    }

    private void mostrarProductos(){
        List<Producto> listaProductos = new ArrayList<>();

        ProductoDAO productoDAO = new ProductoDAO(this);
        productoDAO.abrirBD();
        listaProductos = productoDAO.listarProductos();

        AdaptadorProductos adaptador = new AdaptadorProductos(ListarProductosActivity.this, listaProductos);

        rvProductos.setAdapter(adaptador);
        rvProductos.setLayoutManager(new LinearLayoutManager(ListarProductosActivity.this));
    }
}