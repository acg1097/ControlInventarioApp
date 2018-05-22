package com.inteligenciacomercia.controlinventarioapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.fragments.MovimientosFragment;
import com.inteligenciacomercia.controlinventarioapp.fragments.ProductosFragment;

public class MainActivity extends AppCompatActivity {

    private NavigationTabStrip mNavigationTabStrip;

    private MovimientosFragment movimientosFragment = new MovimientosFragment();
    private ProductosFragment productosFragment = new ProductosFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        mNavigationTabStrip = findViewById(R.id.nts);
        mNavigationTabStrip.setTabIndex(0);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, movimientosFragment).commit();

        mNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                if (index == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,movimientosFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,productosFragment).commit();
                }
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Control de inventario");
    }

}
