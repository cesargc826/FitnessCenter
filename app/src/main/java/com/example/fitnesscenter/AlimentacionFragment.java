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

        items.add(new ItemList("Quinoa", "    Calorías: 120 kcal\n" +
                "    Proteínas: 4 g\n" +
                "    Carbohidratos: 21 g\n" +
                "    Grasas: 2 g\n" +
                "    Fibra: 2.5 g", R.drawable.quinoa_alimentacion));
        items.add(new ItemList("Frijoles Negros", "    Calorías: 132 kcal\n" +
                "    Proteínas: 8.86 g\n" +
                "    Carbohidratos: 23.71 g\n" +
                "    Grasas: 0.54 g\n" +
                "    Fibra: 7.4 g", R.drawable.frijolesnegros_alimentacion));
        items.add(new ItemList("Espinacas", "    Calorías: 23 kcal\n" +
                "    Proteínas: 2.86 g\n" +
                "    Carbohidratos: 3.63 g\n" +
                "    Grasas: 0.39 g\n" +
                "    Fibra: 2.2 g" , R.drawable.espinacas_alimentacion));
        items.add(new ItemList("Papaya", "    Calorías: 43 kcal\n" +
                "    Proteínas: 0.47 g\n" +
                "    Carbohidratos: 11.07 g\n" +
                "    Grasas: 0.26 g\n" +
                "    Fibra: 1.7 g", R.drawable.papaya_alimentacion));
        items.add(new ItemList("Aguacate", "    Calorías: 160 kcal\n" +
                "    Proteínas: 2 g\n" +
                "    Carbohidratos: 8.53 g\n" +
                "    Grasas: 14.66 g\n" +
                "    Fibra: 6.7 g", R.drawable.aguacate_alimentacion));

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