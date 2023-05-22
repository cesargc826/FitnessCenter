package com.example.fitnesscenter.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesscenter.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<Caja> list;

    public RecyclerAdapter(List<Caja> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sigle_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvID.setText(String.valueOf(list.get(position).getId()));
        holder.tvFecha.setText(list.get(position).getFecha());
        holder.tvDescripcion.setText(list.get(position).getDescripcion());
        holder.tvCantidad.setText(list.get(position).getCantidad());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvID, tvFecha, tvCantidad, tvDescripcion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.id);
            tvFecha = itemView.findViewById(R.id.Fecha);
            tvDescripcion = itemView.findViewById(R.id.Descripcion);
            tvCantidad = itemView.findViewById(R.id.Cantidad);

        }
    }
}


