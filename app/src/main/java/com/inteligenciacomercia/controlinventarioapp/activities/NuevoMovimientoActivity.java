package com.inteligenciacomercia.controlinventarioapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.fragments.MovimientosFragment;
import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.inteligenciacomercia.controlinventarioapp.models.Producto;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class NuevoMovimientoActivity extends AppCompatActivity {

    public static final int REQUEST_PRODUCTO = 1;
    public static final String MOVIMIENTO = "Movimiento";
    public static final String PRODUCTO = "Producto";
    private Movimiento movimiento;

    private RadioRealButton rrbgTiposEntrada;
    private EditText etProducto;
    private EditText etCantidad;
    private FloatingActionButton fabNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_movimiento);

        movimiento = new Movimiento();

        setToolbar();

        rrbgTiposEntrada = findViewById(R.id.rrb_tipo_entrada);
        etProducto = findViewById(R.id.etProducto_nuevo_movimiento);
        etProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(NuevoMovimientoActivity.this,ProductosActivity.class),REQUEST_PRODUCTO);
            }
        });
        etCantidad = findViewById(R.id.etCantidad_nuevo_movimiento);
        fabNuevo = findViewById(R.id.fabNuevo_nuevo_movimiento);
        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movimiento.setTipo(rrbgTiposEntrada.isChecked() ? 1 : 2);
                if (validar()) {
                    movimiento.setCantidad(Integer.parseInt(etCantidad.getText().toString()));

                    if(movimiento.getTipo() == 2) {
                        String mensaje;

                        int cantidad = movimiento.getProducto().getStock() - movimiento.getCantidad();

                        if(cantidad == 0){
                            mensaje = "El producto " + movimiento.getProducto().getDescripcion() + " se queda sin stock.";
                        }else{
                            mensaje = "El producto " + movimiento.getProducto().getDescripcion() + " se queda con " + cantidad + " unidad(es).";
                        }

                        new AlertDialog.Builder(NuevoMovimientoActivity.this)
                                .setTitle("Mensaje")
                                .setMessage(mensaje)
                                .setCancelable(false)
                                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent data = new Intent();
                                        data.putExtra(MOVIMIENTO, movimiento);
                                        setResult(RESULT_OK, data);
                                        finish();
                                    }
                                })
                                .show();
                    }else{
                        Intent data = new Intent();
                        data.putExtra(MOVIMIENTO, movimiento);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PRODUCTO){
            if(resultCode == RESULT_OK){
                movimiento.setProducto((Producto) data.getSerializableExtra(PRODUCTO));
                etProducto.setText(movimiento.getProducto().getDescripcion());
            }
        }
    }

    private boolean validar() {
        if (etProducto.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Seleccione el producto", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etCantidad.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Ingrese la cantidad", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (movimiento.getTipo() == 2 && Integer.parseInt(etCantidad.getText().toString()) == 0) {
            Toast.makeText(this, "La cantidad debe ser mayor a 0", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (movimiento.getTipo() == 2 && movimiento.getProducto().getStock() < Integer.parseInt(etCantidad.getText().toString())) {
            Toast.makeText(this, "La cantidad requerida es mayor al stock disponible (" + movimiento.getProducto().getStock() + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nuevo movimiento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }
}
