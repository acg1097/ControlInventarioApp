package com.inteligenciacomercia.controlinventarioapp.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inteligenciacomercia.controlinventarioapp.Constantes;
import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.adapters.RvAdapterMovimiento;
import com.inteligenciacomercia.controlinventarioapp.fragments.MovimientosFragment;
import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.inteligenciacomercia.controlinventarioapp.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetalleProductoActivity extends AppCompatActivity {

    public static final String PRODUCTO = "Producto";

    private Producto producto;

    private ImageView ivImagen;
    private TextView tvProdcuto;
    private TextView tvStock;
    private TextView tvCodigo;

    private RecyclerView mRvMovimientos;
    private RecyclerView.LayoutManager mLayoutManager;
    private RvAdapterMovimiento rvAdapterMovimiento;

    private LinearLayout llEmpty_view;
    private TextView tvAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        producto = (Producto) getIntent().getSerializableExtra(PRODUCTO);

        setToolbar();

        llEmpty_view = findViewById(R.id.view_empty);
        tvAlerta = findViewById(R.id.tvAlerta_detalle_producto);

        if (producto.getStock() == 0) {
            tvAlerta.setVisibility(View.VISIBLE);
            tvAlerta.setText("STOCK AGOTADO");
        } else if (producto.getStock() <= 5) {
            tvAlerta.setVisibility(View.VISIBLE);
            tvAlerta.setText("STOCK CASI AGOTADO");
        } else if (producto.getStock() <= 10) {
            tvAlerta.setVisibility(View.VISIBLE);
            tvAlerta.setText("STOCK CASI AGOTADO");
            tvAlerta.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
        }else{
            tvAlerta.setVisibility(View.VISIBLE);
            tvAlerta.setText("STOCK DISPONIBLE");
            tvAlerta.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        }

        ivImagen = findViewById(R.id.civImagen_detalle_producto);
        Picasso.get().load(producto.getUrlImagen()).placeholder(R.mipmap.img_placeholder).into(ivImagen);

        tvStock = findViewById(R.id.tvStock_detalle_producto);
        tvStock.setText(String.valueOf(producto.getStock()));

        tvCodigo = findViewById(R.id.tvCodigo_detalle_producto);
        tvCodigo.setText(producto.getCodigoBarra());

        tvProdcuto = findViewById(R.id.tvProducto_detalle_producto);
        tvProdcuto.setText(producto.getDescripcion());

        mRvMovimientos = findViewById(R.id.rvMovimientos);
        mRvMovimientos.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRvMovimientos.setLayoutManager(mLayoutManager);

        rvAdapterMovimiento = new RvAdapterMovimiento(this, mMovimientos(), true);
        mRvMovimientos.setAdapter(rvAdapterMovimiento);

    }

    private List<Movimiento> mMovimientos() {
        List<Movimiento> movimientos = new ArrayList<>();
        for (Movimiento m : Constantes.mMovimientos) {
            if (m.getProducto().getId() == producto.getId()) {
                movimientos.add(m);
            }
        }

        if (movimientos.size() == 0) {
            llEmpty_view.setVisibility(View.VISIBLE);
        } else {
            mRvMovimientos.setVisibility(View.VISIBLE);
        }

        return movimientos;
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Info del producto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
