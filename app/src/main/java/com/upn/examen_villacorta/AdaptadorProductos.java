package com.upn.examen_villacorta;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.VistaFilaProducto> {
    @NonNull
    @Override
    public AdaptadorProductos.VistaFilaProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductos.VistaFilaProducto holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VistaFilaProducto extends RecyclerView.ViewHolder {

        public VistaFilaProducto(@NonNull View itemView) {
            super(itemView);
        }
    }
}
