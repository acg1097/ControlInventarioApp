package com.inteligenciacomercia.controlinventarioapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inteligenciacomercia.controlinventarioapp.Constantes;
import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.activities.DetalleMovimientoActivity;
import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RvAdapterMovimiento extends RecyclerView.Adapter<RvAdapterMovimiento.MovimientoViewHolder> {

    private Context mContext;
    private boolean esDetalleProducto;
    private List<Movimiento> movimientos;

    public RvAdapterMovimiento(Context mContext, List<Movimiento> movimientos ,boolean esDetalleProducto) {
        this.mContext = mContext;
        this.esDetalleProducto = esDetalleProducto;
        this.movimientos = movimientos;
    }

    @NonNull
    @Override
    public RvAdapterMovimiento.MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent, false);
        return new MovimientoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterMovimiento.MovimientoViewHolder holder, int position) {
        final Movimiento m = Constantes.mMovimientos.get(position);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(m.getFechaHora());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat;
        if(esDetalleProducto) {
            newFormat = new SimpleDateFormat("dd MMM yyyy");
        }else{
            newFormat = new SimpleDateFormat("dd\nMMM\nyyyy");
        }
        holder.tvFecha.setText(newFormat.format(date).replace(".", ""));

        Picasso.get().load(m.getProducto().getUrlImagen()).resize(100,100).placeholder(R.mipmap.img_placeholder_mini).error(R.mipmap.img_placeholder).centerCrop().into(holder.civImagen);

        holder.tvProducto.setText(m.getProducto().getDescripcion());
        holder.tvCantidad.setTextColor(m.getTipo() == 1 ? ContextCompat.getColor(mContext, R.color.green) : ContextCompat.getColor(mContext, R.color.red));
        holder.tvCantidad.setText(String.format("%s%d", m.getTipo() == 1 ? "+ " : "- ", m.getCantidad()));
        holder.tvUnidad.setTextColor(m.getTipo() == 1 ? ContextCompat.getColor(mContext, R.color.green) : ContextCompat.getColor(mContext, R.color.red));

        if (esDetalleProducto) {
            holder.civImagen.setVisibility(View.GONE);
            holder.tvProducto.setVisibility(View.GONE);
            holder.tvUnidad.setVisibility(View.GONE);

            holder.llItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            holder.llItem.setGravity(Gravity.RIGHT);
            holder.llItem.setPadding(10,10,10,10);

            holder.tvFecha.setTextSize(17);
            holder.tvFecha.setPadding(10,10,10,10);

            holder.tvCantidad.setText(holder.tvCantidad.getText() + " UNIDAD(ES)");
            holder.tvCantidad.setTextSize(17);
            holder.tvCantidad.setTypeface(Typeface.DEFAULT);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, DetalleMovimientoActivity.class).putExtra(DetalleMovimientoActivity.MOVIMIENTO, m));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.movimientos.size();
    }

    public void setAll(List<Movimiento> movimientos){
        this.movimientos = movimientos;
    }

    static class MovimientoViewHolder extends RecyclerView.ViewHolder {
        TextView tvFecha;
        CircularImageView civImagen;
        TextView tvProducto;
        TextView tvCantidad;
        TextView tvUnidad;
        LinearLayout llItem;

        MovimientoViewHolder(View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvFecha_item_movimiento);
            civImagen = itemView.findViewById(R.id.civImagenProducto_item_movimiento);
            tvProducto = itemView.findViewById(R.id.tvDescripcionProducto_item_movimiento);
            tvCantidad = itemView.findViewById(R.id.tvCantidad_item_movimiento);
            tvUnidad = itemView.findViewById(R.id.tvUnidad_item_movimiento);
            llItem = itemView.findViewById(R.id.llItemMovimiento);
        }

    }

}
