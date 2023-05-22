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
public class RutinaFragment extends Fragment {
    View root;
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private ArrayList<ItemList> items;
    ItemList itemselec;
    FloatingActionButton addInventario;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_rutina, container, false);

        rvLista = (RecyclerView) root.findViewById(R.id.rvLista);
        addInventario = (FloatingActionButton) root.findViewById(R.id.BtnAgregar);
        items = new ArrayList<>();

        items.add(new ItemList("Lunes", "", R.drawable.psychotic_red));
        items.add(new ItemList("Martes", "18", R.drawable.psychotic_black));
        items.add(new ItemList("Miercoles", "25", R.drawable.psychotic));
        items.add(new ItemList("Jueves", "l. Jalón abierto al frente en triserie con remo sentado y" +
                "expansión de dorsales 4 de 12, \n" +
                "2. Remo con mancuerna a 2 manos en triserie con peso muerto " +
                "y jalona al frente con triangulo 4 de 12, \n" +
                "3. Jalón nuca en biserie con buenos días 4 de 12, \n" +
                "4. Jalón con cuerda en biserie con copa a dos manos 4 de 12, \n" +
                "5, Jalón con cuerda barra corta en triserie con press francés y " +
                "press california 4 de 12 y " +
                "6. Fondos en biserie con patada de mula 4 de 12.", R.drawable.optimun_nutrition));
        items.add(new ItemList("Viernes", "30", R.drawable.pw_nitraflex));

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