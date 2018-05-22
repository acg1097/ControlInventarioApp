package com.inteligenciacomercia.controlinventarioapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.activities.DetalleProductoActivity;
import com.inteligenciacomercia.controlinventarioapp.activities.NuevoMovimientoActivity;
import com.inteligenciacomercia.controlinventarioapp.models.Producto;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RvAdapterProducto extends RecyclerView.Adapter<RvAdapterProducto.MovimientoViewHolder> {

    private Context mContext;
    private Boolean esDetalle;
    private List<Producto> productos;

    public RvAdapterProducto(Context mContext, List<Producto> productos, boolean esDetalle) {
        this.mContext = mContext;
        this.esDetalle = esDetalle;
        this.productos = productos;
    }

    @NonNull
    @Override
    public RvAdapterProducto.MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new MovimientoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterProducto.MovimientoViewHolder holder, int position) {
        final Producto p = productos.get(position);

        Picasso.get().load(p.getUrlImagen()).resize(200,200).placeholder(R.mipmap.img_placeholder_mini).error(R.mipmap.img_placeholder).centerCrop().into(holder.civImagen);

        holder.tvProducto.setText(p.getDescripcion());
        holder.tvStock.setTextColor(colorSegunCantidad(p.getStock()));
        holder.tvStock.setText(String.valueOf(p.getStock()));
        holder.tvUnidad.setTextColor(colorSegunCantidad(p.getStock()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (esDetalle) {
                    mContext.startActivity(new Intent(mContext, DetalleProductoActivity.class).putExtra(DetalleProductoActivity.PRODUCTO, p));
                } else {
                    Intent data = new Intent();
                    data.putExtra(NuevoMovimientoActivity.PRODUCTO, p);
                    ((AppCompatActivity) mContext).setResult(Activity.RESULT_OK, data);
                    ((AppCompatActivity) mContext).finish();
                }
            }
        });
    }

    private int colorSegunCantidad(int stock) {
        if(stock <= 5) {
            return ContextCompat.getColor(mContext, R.color.red);
        }else if(stock<=10){
            return ContextCompat.getColor(mContext, R.color.orange);
        }else{
            return ContextCompat.getColor(mContext, R.color.green);
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class MovimientoViewHolder extends RecyclerView.ViewHolder {
        CircularImageView civImagen;
        TextView tvProducto;
        TextView tvStock;
        TextView tvUnidad;

        MovimientoViewHolder(View itemView) {
            super(itemView);
            civImagen = itemView.findViewById(R.id.civImagen_item_producto);
            tvProducto = itemView.findViewById(R.id.tvProducto_item_producto);
            tvStock = itemView.findViewById(R.id.tvStock_item_producto);
            tvUnidad = itemView.findViewById(R.id.tvUnidad_item_producto);
        }

    }

}
