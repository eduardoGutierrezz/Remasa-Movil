package com.example.remasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.remasa.adapter.ProdAdapter;
import com.example.remasa.adapter.ProductoAdapter;
import com.example.remasa.model.Producto;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class activity_SurtidorCatalogo extends AppCompatActivity {

    ImageButton btnHome, btnCatalogo, btnPedidosFinalizados, btnPedidosEnProceso, btnNuevosPedidos, btnCuenta;

    //-----
    ProdAdapter mAdapter;
    RecyclerView mRecycler;
    FirebaseFirestore mFirestore;
    Query query;
    //----

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surtidor_catalogo);

        //Consulta a bd
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.RvCatalogo);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        query = mFirestore.collection("Producto");

        FirestoreRecyclerOptions<Producto> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Producto>().setQuery(query, Producto.class).build();

        mAdapter = new ProdAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        mRecycler.setItemAnimator(null);
        //Consulta bd

        btnHome = findViewById(R.id.btnHome);
        btnCatalogo = findViewById(R.id.btnCatalogo);
        btnPedidosFinalizados = findViewById(R.id.btnPedidosFinalizados);
        btnPedidosEnProceso = findViewById(R.id.btnPedidosEnProceso);
        btnNuevosPedidos = findViewById(R.id.btnNuevosPedidos);
        btnCuenta = findViewById(R.id.btnCuenta);

        //---------Metodos OnClick para los botones-------------------------------------------------

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent siguiente = new Intent(getApplicationContext(), activity_SurtidorMenu.class);
                startActivity(siguiente);
            }
        });
        //------------------------------------------------------------------------------------------
        btnCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent siguiente = new Intent(getApplicationContext(), activity_SurtidorCatalogo.class);
                startActivity(siguiente);
            }
        });
        //------------------------------------------------------------------------------------------
        btnPedidosFinalizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent siguiente = new Intent(getApplicationContext(), activity_SurtidorPedidosFinalizados.class);
                startActivity(siguiente);
            }
        });
        //------------------------------------------------------------------------------------------
        btnPedidosEnProceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent siguiente = new Intent(getApplicationContext(), activity_SurtidorPedidosEnProceso.class);
                startActivity(siguiente);
            }
        });
        //------------------------------------------------------------------------------------------
        btnNuevosPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent siguiente = new Intent(getApplicationContext(), activity_SurtidorNuevosPedidos.class);
                startActivity(siguiente);
            }
        });
        //------------------------------------------------------------------------------------------
        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent siguiente = new Intent(getApplicationContext(), activity_SurtidorCuenta.class);
                startActivity(siguiente);
            }
        });
        //------------------------------------------------------------------------------------------
    }

    //Consulta bd
    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
    //Consulta bd
}