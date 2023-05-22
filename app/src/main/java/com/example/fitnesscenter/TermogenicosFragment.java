package com.example.fitnesscenter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesscenter.BDInventario.Inventario;
import com.example.fitnesscenter.ui.gallery.Caja;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TermogenicosFragment extends Fragment {
    public List<CarouselItem> list = new ArrayList<>();
    ImageCarousel carousel;
    View root;
    TextView Precio;
    Button compra;
    NumberPicker Cantidad;
    String marc = "";
    FloatingActionButton addInventario;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_termogenicos, container, false);

        carousel = (ImageCarousel) root.findViewById(R.id.carousel);
        compra = (Button) root.findViewById(R.id.btnCompra);
        Precio = (TextView) root.findViewById(R.id.txtPrecio);
        Cantidad = (NumberPicker) root.findViewById(R.id.Cantidad);
        addInventario = (FloatingActionButton) root.findViewById(R.id.BtnAgregar);

        Cantidad.setMinValue(1);
        Cantidad.setMaxValue(10);
        //Cantidad.getValue();


        list.add(new CarouselItem(R.drawable.turmeric, "B-life T+G"));
        list.add(new CarouselItem(R.drawable.menstplat, "B-life MTP"));
        list.add(new CarouselItem(R.drawable.getripped, "Get Ripped"));
        list.add(new CarouselItem(R.drawable.lipo6, "Lipo 6"));
        list.add(new CarouselItem(R.drawable.essentialnut, "Essential NT"));

        carousel.setData(list);

        carousel.setOnScrollListener(new CarouselOnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i, int i1, @Nullable CarouselItem carouselItem) {
                marc = carouselItem.getCaption();
                if (marc.equals("B-life T+G")) {
                    Precio.setText("$" + String.valueOf(Cantidad.getValue() * 8));
                } else if (marc.equals("B-life MTP")) {
                    Precio.setText("$" + String.valueOf(Cantidad.getValue() * 8));
                } else if (marc.equals("Get Ripped")) {
                    Precio.setText("$" + String.valueOf(Cantidad.getValue() * 9));
                } else if (marc.equals("Lipo 6")) {
                    Precio.setText("$" + String.valueOf(Cantidad.getValue() * 12));
                } else if (marc.equals("Essential NT")) {
                    Precio.setText("$" + String.valueOf(Cantidad.getValue() * 11));
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i1, int i2, @Nullable CarouselItem carouselItem) {

            }
        });

        Cantidad.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                if (!(marc.equals(""))) {
                    if (marc.equals("B-life T+G")) {
                        Precio.setText("$" + String.valueOf(Cantidad.getValue() * 8));
                    } else if (marc.equals("B-life MTP")) {
                        Precio.setText("$" + String.valueOf(Cantidad.getValue() * 8));
                    } else if (marc.equals("Get Ripped")) {
                        Precio.setText("$" + String.valueOf(Cantidad.getValue() * 9));
                    } else if (marc.equals("Lipo 6")) {
                        Precio.setText("$" + String.valueOf(Cantidad.getValue() * 12));
                    } else if (marc.equals("Essential NT")) {
                        Precio.setText("$" + String.valueOf(Cantidad.getValue() * 11));
                    }
                }
            }
        });

        compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Precio.getText().equals("$0")) {
                    Toast.makeText(getActivity(), "Ingrese la marca ", Toast.LENGTH_SHORT).show();
                } else {
                    Caja cantidad = new Caja();
                    cantidad.setFecha(LocalDate.now().toString());
                    cantidad.setCantidad(Precio.getText().toString().substring(1));
                    cantidad.setDescripcion("Termogénico: " + marc);

                    Inventario inventario = new Inventario();
                    inventario.setFechaInventario(LocalDate.now().toString());
                    inventario.setProteina(MainActivity.dbI.daoInventario().LastProteina());
                    inventario.setCreatina(MainActivity.dbI.daoInventario().LastCreatina());
                    inventario.setPreworkout(MainActivity.dbI.daoInventario().LastPreworkout());
                    inventario.setAgua(MainActivity.dbI.daoInventario().LastAgua());
                    inventario.setTermogenico(MainActivity.dbI.daoInventario().LastTermogenico() - Cantidad.getValue());

                    if (MainActivity.dbI.daoInventario().LastTermogenico() - Cantidad.getValue() < 0) {
                        Toast.makeText(getActivity(), "No hay suficientes aguas", Toast.LENGTH_SHORT).show();
                    } else {
                        MainActivity.db.metodosDAO().insertAll(cantidad);
                        MainActivity.dbI.daoInventario().insertTodo(inventario);
                        Toast.makeText(getActivity(), "La venta se ha realizado con éxito", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        addInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = getActivity().getIntent().getExtras().getString("Usuario");

                if(usuario.equals("fitnessadmin@gmail.com")){
                    DialogAddTermo dialogo = new DialogAddTermo();
                    dialogo.show(getActivity().getSupportFragmentManager(), "DialogAddTermo");
                }else{
                    Toast.makeText(getActivity(), "Solo los administradores pueden acceder a este apartado", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }
}