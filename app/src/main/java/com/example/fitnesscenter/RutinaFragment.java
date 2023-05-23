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

        items.add(new ItemList("Pierna", "Calentamiento 10-15 minutos\n" +
                "\n" +
                "Sentadilla libre 4/12-10-8-failure\n" +
                "Femoral acostado 4/12-15 (ya sabes la velocidad)\n" +
                "\n" +
                "Goblet squat en disco con desplante 4/12 por cada pie.\n" +
                "Desplantes parado 4/ 15 por cada pie\n" +
                "\n" +
                "Sumo squat 4/12-10-8-8\n" +
                "Leg extension 4/12-8 (Drop sets)\n" +
                "\n" +
                "Pantorrilla en máquina 4/25-30", R.drawable.pierna_rutina));
        items.add(new ItemList("Espalda", "Calenamiento 10 - 15 minutos\n" +
                "\n" +
                "Remo con barra 4/8-10 \n" +
                "Pullover con soga 4/12-15 \n" +
                "\n" +
                "Remo neutro en maquina 4/8-10\n" +
                "Pulldown prono 4/10\n" +
                "\n" +
                "Remo con mancuernas 4/10\n" +
                "Pulldown neutro 4/10", R.drawable.espalda_rutina));
        items.add(new ItemList("Brazo", "Calenamiento 10 - 15 minutos\n" +
                "\n" +
                "Curl de bicep 4/8-12\n" +
                "Dips 4/failure\n" +
                "\n" +
                "Martillos 4/8-10\n" +
                "Extensiones en soga 4/10\n" +
                "\n" +
                "Predicador 4/8-12\n" +
                "Kickback 4/10-12", R.drawable.brazo_rutina));
        items.add(new ItemList("Hombro", "Calenamiento 10 - 15 minutos\n" +
                "\n" +
                "Press miltar 4/8-10\n" +
                "Facepull 4/12-15\n" +
                "\n" +
                "Laterales 4/10-12\n" +
                "Frontales 4/10-12\n" +
                "\n" +
                "Elevacion de hombros 4/20", R.drawable.hombro_rutina));
        items.add(new ItemList("Gluteo", "Calentamiento 10-15 minutos\n" +
                "\n" +
                "Sentadilla libre 4/8-12\n" +
                "Abductor 4/15\n" +
                "\n" +
                "Leg extension 2/12-15 y 2/drop sets\n" +
                "Hip thrust 4/8-12 con 3s en la última isometrica\n" +
                "\n" +
                "Femoral sentado o acostado 4/12-15\n" +
                "Patada en cables (si se puede en el banco) 4/12 LENTAS\n" +
                "\n" +
                "Pantorrilla 4/20-25", R.drawable.gluteo_rutina));

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