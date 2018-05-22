package com.inteligenciacomercia.controlinventarioapp.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetalleMovimientoActivity extends AppCompatActivity {

    public static final String MOVIMIENTO = "Movimiento";

    private Movimiento movimiento;

    private ImageView ivImagen;
    private TextView tvProdcuto;
    private TextView tvFecha;
    private TextView tvHora;
    private TextView tvCantidad;
    private TextView tvStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_movimiento);

        movimiento = (Movimiento) getIntent().getSerializableExtra(MOVIMIENTO);

        setToolbar();

        ivImagen = findViewById(R.id.civImagen_detalle_movimiento);
        Picasso.get().load(movimiento.getProducto().getUrlImagen()).placeholder(R.mipmap.img_placeholder).into(ivImagen);

        tvProdcuto = findViewById(R.id.tvProducto_detalle_movimiento);
        tvProdcuto.setText(movimiento.getProducto().getDescripcion());

        tvFecha = findViewById(R.id.tvFecha_detalle_movimiento);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(movimiento.getFechaHora());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

        tvFecha.setText(newFormat.format(date));

        tvHora = findViewById(R.id.tvHora_detalle_movimiento);
        tvHora.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(movimiento.getFechaHora())));

        tvCantidad = findViewById(R.id.tvCantidad_detalle_movimiento);
        tvCantidad.setText((movimiento.getTipo() == 1 ? "+ " : "- ") + movimiento.getCantidad() + " UNIDAD(ES)");
        tvCantidad.setTextColor(movimiento.getTipo() == 1 ? ContextCompat.getColor(this,R.color.green) : ContextCompat.getColor(this,R.color.red));

        tvStock = findViewById(R.id.tvStock_detalle_movimiento);
        tvStock.setText(movimiento.getProducto().getStock() + " UNIDAD(ES)");
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Info del movimiento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
