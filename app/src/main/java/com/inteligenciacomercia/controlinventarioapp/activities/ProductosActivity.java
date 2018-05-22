package com.inteligenciacomercia.controlinventarioapp.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.inteligenciacomercia.controlinventarioapp.Constantes;
import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.adapters.RvAdapterMovimiento;
import com.inteligenciacomercia.controlinventarioapp.adapters.RvAdapterProducto;
import com.inteligenciacomercia.controlinventarioapp.fragments.ProductosFragment;
import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.inteligenciacomercia.controlinventarioapp.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductosActivity extends AppCompatActivity {

    private RecyclerView mRvProductos;
    private RecyclerView.LayoutManager mLayoutManager;
    private RvAdapterProducto mRvAdapterProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        setToolbar();

        mRvProductos = findViewById(R.id.rvProductos);
        mRvProductos.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRvProductos.setLayoutManager(mLayoutManager);

        mRvAdapterProducto = new RvAdapterProducto(this,Constantes.mProductos,false);
        mRvProductos.setAdapter(mRvAdapterProducto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_productos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                for (Producto p : Constantes.mProductos) {
                    if (p.getCodigoBarra().equals(result.getContents())) {

                        data = new Intent();
                        data.putExtra(NuevoMovimientoActivity.PRODUCTO, p);
                        setResult(Activity.RESULT_OK, data);
                        finish();

                        Toast.makeText(this, "Producto encontrado", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }
                Toast.makeText(this, "No existe ningúun producto con ese código", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_barcode:

                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setPrompt("Escanee el código de barra");
                integrator.setBeepEnabled(true);
                integrator.initiateScan();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Seleccione producto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
