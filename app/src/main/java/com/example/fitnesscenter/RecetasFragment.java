package com.example.fitnesscenter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesscenter.DialogAddPrentre;
import com.example.fitnesscenter.DialogoPrentrenos;
import com.example.fitnesscenter.R;
import com.example.fitnesscenter.adapter.RecyclerAdapter;
import com.example.fitnesscenter.model.ItemList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the factory method to
 * create an instance of this fragment.
 */
public class RecetasFragment extends Fragment {
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

        items.add(new ItemList("Quinoa con verduras",
                "Ingredientes" +
                        "1 taza de quinoa\n" +
                        "2 tazas de agua\n" +
                        "Verduras a gusto\n" +
                        "Aceite de oliva, sal y pimienta\n\n" +
                        "Pasos:\n" +
                        "Lava la quinoa hasta que el agua salga clara.\n" +
                        "En una olla, sofríe las verduras en aceite de oliva.\n" +
                        "Agrega la quinoa a la olla y cocina por un par de minutos.\n" +
                        "Añade agua, sal y pimienta. Deja hervir.\n" +
                        "Reduce el fuego, cubre la olla y deja cocinar por 15 minutos.\n" +
                        "Deja reposar 5 minutos, revuelve con un tenedor y ¡listo para servir!",
                R.drawable.quinoa));
        items.add(new ItemList("Ensalada Espinaca-Feta",
                "Ingredientes:\n" +
                "\n" +
                "2 tazas de espinacas frescas\n" +
                "1/2 cebolla roja, cortada en rodajas finas\n" +
                "1 manzana, cortada en rodajas finas\n" +
                "50 gramos de nueces, tostadas\n" +
                "Queso feta al gusto\n" +
                "Aceite de oliva, vinagre balsámico, sal y pimienta para el aderezo\n\n" +
                "Pasos:\n" +
                "Lava bien las espinacas y colócalas en un tazón grande.\n" +
                "Agrega la cebolla roja, la manzana y las nueces.\n" +
                "Desmorona el queso feta por encima.\n" +
                "Mezcla aceite de oliva, vinagre balsámico, sal y pimienta para hacer el aderezo.\n" +
                "Rocía la ensalada con el aderezo justo antes de servir.",
                R.drawable.espinacas));
        items.add(new ItemList("Frijoles Negros Sazonados",
                "Ingredientes:\n" +
                        "\n" +
                        "1 taza de frijoles negros secos\n" +
                        "3 tazas de agua\n" +
                        "1/2 cebolla, picada\n" +
                        "2 dientes de ajo, picados\n" +
                        "Sal al gusto\n\n" +
                        "Pasos:\n" +
                        "Remoja los frijoles negros en agua durante la noche.\n" +
                        "Al día siguiente, escurre los frijoles y colócalos en una olla.\n" +
                        "Agrega 3 tazas de agua, la cebolla picada y el ajo.\n" +
                        "Lleva la olla a ebullición, luego reduce el fuego y deja que los frijoles se cocinen a fuego lento durante unos 60-90 minutos, o hasta que estén suaves.\n" +
                        "Añade sal al gusto.",
                R.drawable.frijoles_negros_sazonados));
        items.add(new ItemList("Ensalada Refrescante de Papaya",
                "Ingredientes:\n" +
                        "\n" +
                        "1 papaya madura grande\n" +
                        "Jugo de 1 limón\n" +
                        "1 cucharada de miel\n" +
                        "Hojas de menta fresca al gusto\n\n" +
                        "Pasos:\n" +
                        "Corta la papaya por la mitad a lo largo y retira las semillas con una cuchara.\n" +
                        "Corta la papaya en rebanadas o cubos, según tu preferencia.\n" +
                        "En un tazón grande, mezcla el jugo de limón y la miel hasta obtener una mezcla homogénea.\n" +
                        "Añade la papaya al tazón y mezcla bien para que se impregne del aderezo.\n" +
                        "Añade las hojas de menta fresca al gusto.\n" +
                        "Refrigera la ensalada durante al menos 30 minutos antes de servir.",
                R.drawable.ensalada_refrescante_papaya));
        items.add(new ItemList("Guacamole Fresco y Cremoso",
                "Ingredientes:\n" +
                        "\n" +
                        "2 aguacates maduros\n" +
                        "1 tomate mediano\n" +
                        "1/4 de cebolla\n" +
                        "Jugo de 1 limón\n" +
                        "Sal y pimienta al gusto\n" +
                        "Cilantro fresco al gusto\n\n" +
                        "Pasos:\n" +
                        "Corta los aguacates por la mitad, retira la semilla y extrae la pulpa con una cuchara.\n" +
                        "En un tazón, machaca el aguacate con un tenedor hasta obtener una consistencia cremosa.\n" +
                        "Pica el tomate y la cebolla en cubos pequeños y añádelos al tazón.\n" +
                        "Añade el jugo de limón, sal y pimienta al gusto.\n" +
                        "Pica el cilantro fresco y añádelo al tazón.\n" +
                        "Mezcla todo bien y sirve inmediatamente.",
                R.drawable.guacamole));

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