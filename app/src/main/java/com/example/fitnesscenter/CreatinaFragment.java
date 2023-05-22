package com.example.fitnesscenter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesscenter.BDInventario.Inventario;
import com.example.fitnesscenter.ui.gallery.Caja;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreatinaFragment extends Fragment {

    public List<CarouselItem> list = new ArrayList<>();
    ImageCarousel carousel;
    View root;
    TextView Precio;
    ImageView Proteina;
    Button compra;
    EditText Cantidad;

    FloatingActionButton addInventario;

    public int marca;
    String marc = "";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_creatina, container, false);

        carousel = (ImageCarousel) root.findViewById(R.id.carousel);
        compra = (Button) root.findViewById(R.id.btnCompra);
        Proteina = (ImageView) root.findViewById(R.id.imgProteina);
        Precio = (TextView) root.findViewById(R.id.txtPrecio);
        Cantidad = (EditText) root.findViewById(R.id.editTxtCantidad);
        addInventario = (FloatingActionButton) root.findViewById(R.id.BtnAgregar);

        list.add(new CarouselItem(R.drawable.monohydrate, "Jacked Factory"));
        list.add(new CarouselItem("https://m.media-amazon.com/images/I/71-HYlEU2+L._AC_SL1500_.jpg", "Birdman"));
        list.add(new CarouselItem("https://res.cloudinary.com/walmart-labs/image/upload/w_960,dpr_auto,f_auto,q_auto:best/mg/" +
                "gm/3pp/asr/eeaff85e-4627-487b-81c0-84e25acfc250.e1d2afeb45a59ea624628318a718e1eb.jpeg?odnHeight=2000&odnWidth=2000&odnBg=ffffff", "Vitazona"));
        carousel.setData(list);

        carousel.setOnScrollListener(new CarouselOnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i, int i1, @Nullable CarouselItem carouselItem) {
                marc = carouselItem.getCaption();
                //Toast.makeText(getActivity(), "Creatina: " + marc, Toast.LENGTH_SHORT).show();
                if (Cantidad.getText().toString().isEmpty()) {
                    Precio.setText("$0");
                } else if (marc.equals("Jacked Factory")) {
                    Precio.setText("$" + String.valueOf(Integer.parseInt(Cantidad.getText().toString()) * 23));
                } else if (marc.equals("Birdman")) {
                    Precio.setText("$" + String.valueOf(Integer.parseInt(Cantidad.getText().toString()) * 28));
                } else if (marc.equals("Vitazona")) {
                    Precio.setText("$" + String.valueOf(Integer.parseInt(Cantidad.getText().toString()) * 32));
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i1, int i2, @Nullable CarouselItem carouselItem) {

            }
        });

        Cantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Cantidad.getText().toString().isEmpty()) {
                    Cantidad.setHint("Escribe un número");
                    Precio.setText("$0");
                } else if (marc.equals("Jacked Factory")) {
                    Precio.setText("$" + String.valueOf(Integer.parseInt(Cantidad.getText().toString()) * 23));
                } else if (marc.equals("Birdman")) {
                    Precio.setText("$" + String.valueOf(Integer.parseInt(Cantidad.getText().toString()) * 28));
                } else if (marc.equals("Vitazona")) {
                    Precio.setText("$" + String.valueOf(Integer.parseInt(Cantidad.getText().toString()) * 32));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Precio.getText().equals("$0")) {
                    Toast.makeText(getActivity(), "Ingrese la cantidad ", Toast.LENGTH_SHORT).show();
                } else {
                    Caja cantidad = new Caja();
                    cantidad.setFecha(LocalDate.now().toString());
                    cantidad.setCantidad(Precio.getText().toString().substring(1));
                    cantidad.setDescripcion("Creatina: " + marc);

                    Inventario inventario = new Inventario();
                    inventario.setFechaInventario(LocalDate.now().toString());
                    inventario.setProteina(MainActivity.dbI.daoInventario().LastProteina());
                    inventario.setCreatina(MainActivity.dbI.daoInventario().LastCreatina() - Integer.parseInt(Cantidad.getText().toString()));
                    inventario.setPreworkout(MainActivity.dbI.daoInventario().LastPreworkout());
                    inventario.setAgua(MainActivity.dbI.daoInventario().LastAgua());
                    inventario.setTermogenico(MainActivity.dbI.daoInventario().LastTermogenico());

                    if (MainActivity.dbI.daoInventario().LastCreatina() - Integer.parseInt(Cantidad.getText().toString()) < 0) {
                        Toast.makeText(getActivity(), "No hay suficientes creatinas", Toast.LENGTH_SHORT).show();
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

                    DialogoAddCrea dialogo = new DialogoAddCrea();
                    dialogo.show(getActivity().getSupportFragmentManager(), "DialogoAddCrea");
            }
        });


        return root;
    }


}