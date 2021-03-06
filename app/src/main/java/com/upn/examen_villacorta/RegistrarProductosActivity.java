package com.upn.examen_villacorta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.upn.examen_villacorta.entity.Producto;
import com.upn.examen_villacorta.model.ProductoDAO;

import java.util.Calendar;

public class RegistrarProductosActivity extends AppCompatActivity {

    TextInputEditText txtMarca, txtColor, txtSerie, txtPrecioVenta, txtAnioFabricacion;
    Button btnRegistrar;
    TextView lblTitulo;
    TextInputLayout txtiAnioFabricacion;
    DatePickerDialog.OnDateSetListener escogerFecha;

    boolean registra = true;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_productos);

        asignarReferencias();

        txtiAnioFabricacion.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });
        escogerFecha = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String formatoFecha = String.format("%02d" , dayOfMonth)+"/"+String.format("%02d" , month)+"/"+String.format("%02d" , year);
                txtAnioFabricacion.setText(new StringBuilder().append(formatoFecha));
            }
        };

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProducto();
            }
        });
        verificarModificar();
    }

    private void asignarReferencias(){
        txtMarca = findViewById(R.id.r_p_txtMarca);
        txtColor = findViewById(R.id.r_p_txtColor);
        txtSerie = findViewById(R.id.r_p_txtSerie);
        txtPrecioVenta = findViewById(R.id.r_p_txtPrecioVenta);
        txtAnioFabricacion = findViewById(R.id.r_p_txtAnioFabricacion);
        txtiAnioFabricacion = findViewById(R.id.r_p_txtiAnioFabricacion);
        btnRegistrar = findViewById(R.id.r_p_btnRegistrar);

        lblTitulo = findViewById(R.id.r_p_lblTitulo);
    }

    private void verificarModificar(){
        if (getIntent().hasExtra("producto_Id")){
            lblTitulo.setText("Modificar Producto");
            btnRegistrar.setText("Modificar Producto");
            registra = false;
            id = Integer.parseInt(getIntent().getStringExtra("producto_Id"));
            txtMarca.setText(getIntent().getStringExtra("producto_Marca"));
            txtColor.setText(getIntent().getStringExtra("producto_Color"));
            txtSerie.setText(getIntent().getStringExtra("producto_Serie"));
            txtPrecioVenta.setText(getIntent().getStringExtra("producto_PrecioVenta"));
            txtAnioFabricacion.setText(getIntent().getStringExtra("producto_AnioFabricacion"));
        }
    }

    private void registrarProducto(){
        String marca = txtMarca.getText().toString();
        String color = txtColor.getText().toString();
        String serie = txtSerie.getText().toString();
        String precio_venta = txtPrecioVenta.getText().toString();
        String anio_fabricacion = txtAnioFabricacion.getText().toString();

        if(marca.isEmpty()){
            txtMarca.setError("Este campo es obligatorio");
            return;
        }
        if(color.isEmpty()){
            txtMarca.setError("Este campo es obligatorio");
            return;
        }
        if(serie.isEmpty()){
            txtMarca.setError("Este campo es obligatorio");
            return;
        }
        if(precio_venta.isEmpty()){
            txtMarca.setError("Este campo es obligatorio");
            return;
        }
        if(anio_fabricacion.isEmpty()){
            txtMarca.setError("Este campo es obligatorio");
            return;
        }

        ProductoDAO productoDAO = new ProductoDAO(RegistrarProductosActivity.this);
        productoDAO.abrirBD();
        String mensaje = "";

        Producto producto;

        if(registra){
            producto = new Producto(marca, color, serie, Float.parseFloat(precio_venta), anio_fabricacion);
            mensaje = productoDAO.registrarProducto(producto);
        }else{
            producto = new Producto(id, marca, color, serie, Float.parseFloat(precio_venta), anio_fabricacion);
            mensaje = productoDAO.modificarProducto(producto);
        }

        mostrarAlerta(mensaje);
    }

    private void mostrarAlerta(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarProductosActivity.this);
        ventana.setTitle("Mensaje de Acci??n");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(RegistrarProductosActivity.this, ListarProductosActivity.class));
            }
        });
        ventana.create().show();
    }

    private void mostrarDatePicker(){
        Calendar calendario = Calendar.getInstance();

        final int anio = calendario.get(Calendar.YEAR);
        final int mes = calendario.get(Calendar.MONTH);
        final int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegistrarProductosActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                escogerFecha, anio, mes, dia);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }
}