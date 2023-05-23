package com.example.fitnesscenter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesscenter.adapter.RecyclerAdapter;
import com.example.fitnesscenter.model.ItemList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AlimentacionFragment extends Fragment {
    View root;
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private ArrayList<ItemList> items;
    ItemList itemselec;
    FloatingActionButton addInventario;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_alimentacion, container, false);

        rvLista = (RecyclerView) root.findViewById(R.id.rvLista);
        addInventario = (FloatingActionButton) root.findViewById(R.id.BtnAgregar);
        items = new ArrayList<>();

        items.add(new ItemList("Pierna", "", R.drawable.pierna_rutina));
        items.add(new ItemList("Espalda", "", R.drawable.espalda_rutina));
        items.add(new ItemList("Brazo", "" , R.drawable.brazo_rutina));
        items.add(new ItemList("Hombro", "", R.drawable.hombro_rutina));
        items.add(new ItemList("Gluteo", "", R.drawable.gluteo_rutina));

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