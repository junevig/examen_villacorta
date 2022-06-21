package com.upn.examen_villacorta;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.upn.examen_villacorta.entity.Producto;
import com.upn.examen_villacorta.model.ProductoDAO;

import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.VistaFilaProducto> {

    private Context context;
    private List<Producto> listaProductos;

    public  AdaptadorProductos(Context context, List<Producto> listaProductos){
        this.context = context;
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public AdaptadorProductos.VistaFilaProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila_rv_producto, parent, false);
        return new VistaFilaProducto(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductos.VistaFilaProducto holder, @SuppressLint("RecyclerView") int position) {
        holder.fila_txtMarca.setText(listaProductos.get(position).getMarca());
        holder.fila_txtColor.setText(listaProductos.get(position).getColor());
        holder.fila_txtSerie.setText(listaProductos.get(position).getSerie());
        holder.fila_txtPrecioVenta.setText(listaProductos.get(position).getPrecio_venta()+"");
        holder.fila_txtAnioFabricacion.setText(listaProductos.get(position).getAnio_fabricacion());

        holder.fila_btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegistrarProductosActivity.class);
                intent.putExtra("producto_Id", listaProductos.get(position).getId()+"");
                intent.putExtra("producto_Marca", listaProductos.get(position).getMarca());
                intent.putExtra("producto_Color", listaProductos.get(position).getColor());
                intent.putExtra("producto_Serie", listaProductos.get(position).getSerie());
                intent.putExtra("producto_PrecioVenta", listaProductos.get(position).getPrecio_venta()+"");
                intent.putExtra("producto_AnioFabricacion", listaProductos.get(position).getAnio_fabricacion()+"");
                context.startActivity(intent);
            }
        });
        holder.fila_btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setMessage("Desea eliminar el producto?");
                ventana.setNegativeButton("NO", null);
                ventana.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProductoDAO productoDAO = new ProductoDAO(context);
                        productoDAO.abrirBD();
                        String mensaje = productoDAO.eliminarProducto(listaProductos.get(position).getId());

                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, ListarProductosActivity.class));
                    }
                });
                ventana.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class VistaFilaProducto extends RecyclerView.ViewHolder {
        TextInputEditText fila_txtMarca, fila_txtColor, fila_txtSerie, fila_txtPrecioVenta, fila_txtAnioFabricacion;
        ImageButton fila_btnEditar, fila_btnEliminar;

        public VistaFilaProducto(@NonNull View itemView) {
            super(itemView);
            fila_txtMarca = itemView.findViewById(R.id.fila_txtMarca);
            fila_txtColor = itemView.findViewById(R.id.fila_txtColor);
            fila_txtSerie = itemView.findViewById(R.id.fila_txtSerie);
            fila_txtPrecioVenta = itemView.findViewById(R.id.fila_txtPrecioVenta);
            fila_txtAnioFabricacion = itemView.findViewById(R.id.fila_txtAnioFabricacion);

            fila_btnEditar = itemView.findViewById(R.id.fila_btnEditar);
            fila_btnEliminar = itemView.findViewById(R.id.fila_btnEliminar);
        }
    }
}
