package com.inteligenciacomercia.controlinventarioapp.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.inteligenciacomercia.controlinventarioapp.Constantes;
import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.activities.NuevoMovimientoActivity;
import com.inteligenciacomercia.controlinventarioapp.adapters.RvAdapterMovimiento;
import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.inteligenciacomercia.controlinventarioapp.models.Producto;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovimientosFragment extends Fragment {

    private static final int REQUEST_MOVIMIENTO = 1;


    private RecyclerView mRvMovimientos;
    private RecyclerView.LayoutManager mLayoutManager;
    private RvAdapterMovimiento rvAdapterMovimiento;
    private FloatingActionButton mFabNuevoMovimiento;

    private LinearLayout llEmptyView;

    public MovimientosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movimientos, container, false);

        llEmptyView = v.findViewById(R.id.view_empty);
        if(Constantes.mMovimientos.size() == 0){
            llEmptyView.setVisibility(View.VISIBLE);
        }else{
            llEmptyView.setVisibility(View.GONE);
        }

        mFabNuevoMovimiento = v.findViewById(R.id.fabNuevoMovimiento);
        mFabNuevoMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), NuevoMovimientoActivity.class), REQUEST_MOVIMIENTO);
            }
        });

        mRvMovimientos = v.findViewById(R.id.rvMovimientos);
        mRvMovimientos.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRvMovimientos.setLayoutManager(mLayoutManager);

        rvAdapterMovimiento = new RvAdapterMovimiento(getActivity(), Constantes.mMovimientos ,false);
        mRvMovimientos.setAdapter(rvAdapterMovimiento);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MOVIMIENTO) {
            if (resultCode == Activity.RESULT_OK) {
                Movimiento m = (Movimiento) data.getSerializableExtra(NuevoMovimientoActivity.MOVIMIENTO);

                for (Producto p : Constantes.mProductos) {
                    if (m.getProducto().getId() == p.getId()) {
                        if (m.getTipo() == 1) {
                            p.setStock(p.getStock() + m.getCantidad());
                        } else {
                            p.setStock(p.getStock() - m.getCantidad());
                        }
                        m.setProducto(p);
                        break;
                    }
                }

                llEmptyView.setVisibility(View.GONE);

                Constantes.mMovimientos.add(0, m);
                rvAdapterMovimiento.setAll(Constantes.mMovimientos);
                rvAdapterMovimiento.notifyDataSetChanged();

                Toast.makeText(getActivity(), "Movimiento realizado correctamente", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
