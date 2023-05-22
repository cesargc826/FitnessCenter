package com.example.fitnesscenter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fitnesscenter.adapter.RecyclerAdapter;
import com.example.fitnesscenter.model.ItemList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PrentrenosFragment extends Fragment {
    View root;
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private ArrayList<ItemList> items;
    ItemList itemselec;
    FloatingActionButton addInventario;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_prentrenos, container, false);

        rvLista = (RecyclerView) root.findViewById(R.id.rvLista);
        addInventario = (FloatingActionButton) root.findViewById(R.id.BtnAgregar);
        items = new ArrayList<>();

        items.add(new ItemList("Psychotic", "20", R.drawable.psychotic_red));
        items.add(new ItemList("Psychotic Black", "18", R.drawable.psychotic_black));
        items.add(new ItemList("Psychotic Gold", "25", R.drawable.psychotic));
        items.add(new ItemList("Optimun Nutrition", "30", R.drawable.optimun_nutrition));
        items.add(new ItemList("Nitraflex GAT", "30", R.drawable.pw_nitraflex));
        items.add(new ItemList("C4 Original", "26", R.drawable.c4));
        items.add(new ItemList("Venom", "22", R.drawable.pw_venom));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvLista.setLayoutManager(manager);

        adapter = new RecyclerAdapter(items);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemselec = items.get(rvLista.getChildAdapterPosition(view));

                DialogoPrentrenos dialogo = new DialogoPrentrenos(root, itemselec);
                dialogo.show(getActivity().getSupportFragmentManager(), "DialogoPrentrenos");

            }
        });
        rvLista.setAdapter(adapter);

        addInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogAddPrentre dialogo = new DialogAddPrentre();
                dialogo.show(getActivity().getSupportFragmentManager(), "DialogoAddPrentre");
            }
        });

        return root;
    }
}