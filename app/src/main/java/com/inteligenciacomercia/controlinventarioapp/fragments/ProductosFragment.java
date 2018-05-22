package com.inteligenciacomercia.controlinventarioapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.inteligenciacomercia.controlinventarioapp.Constantes;
import com.inteligenciacomercia.controlinventarioapp.R;
import com.inteligenciacomercia.controlinventarioapp.activities.NuevoMovimientoActivity;
import com.inteligenciacomercia.controlinventarioapp.adapters.RvAdapterMovimiento;
import com.inteligenciacomercia.controlinventarioapp.adapters.RvAdapterProducto;
import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.inteligenciacomercia.controlinventarioapp.models.Producto;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductosFragment extends Fragment {

    private RecyclerView mRvProductos;
    private RecyclerView.LayoutManager mLayoutManager;
    public static RvAdapterProducto mRvAdapterProducto;

    public ProductosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_productos, container, false);

        mRvProductos = v.findViewById(R.id.rvProductos);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRvProductos.setLayoutManager(mLayoutManager);
        mRvProductos.setHasFixedSize(true);
        mRvProductos.setItemViewCacheSize(20);
        mRvProductos.setDrawingCacheEnabled(true);

        mRvAdapterProducto = new RvAdapterProducto(getActivity(), Constantes.mProductos ,true);
        mRvProductos.setAdapter(mRvAdapterProducto);
    }

}
