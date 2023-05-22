package com.example.fitnesscenter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesscenter.BDInventario.Inventario;
import com.example.fitnesscenter.ui.gallery.Caja;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;


public class AguaFragment extends Fragment {
    View root;
    ImageView imgAguaChica, imgAguaGrande;
    EditText editTextCantidad;
    TextView TvPrecio;
    Button compra;
    String tamaño = "";
    boolean press = false, pressgrande = false;
    FloatingActionButton addInventario;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_agua, container, false);
        imgAguaChica = (ImageView) root.findViewById(R.id.imgAguaChica);
        imgAguaGrande = (ImageView) root.findViewById(R.id.imgAguaGrande);
        editTextCantidad = (EditText) root.findViewById(R.id.editTextCantidad);
        TvPrecio = (TextView) root.findViewById(R.id.TvPrecio);
        compra = (Button) root.findViewById(R.id.btnCompra);
        addInventario = (FloatingActionButton) root.findViewById(R.id.BtnAgregar);

        imgAguaChica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAguaChica.setImageResource(R.drawable.aguachicallena);
                imgAguaGrande.setImageResource(R.drawable.aguagrandevacia);
                press = true;
                pressgrande = false;
                if (editTextCantidad.getText().toString().isEmpty()) {
                    editTextCantidad.setHint("Escribe un número");
                    TvPrecio.setText("$0");
                } else {
                    TvPrecio.setText("$" + String.valueOf(8 * Integer.parseInt(editTextCantidad.getText().toString())));
                }
                tamaño = "Agua chica";
            }
        });

        imgAguaGrande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAguaChica.setImageResource(R.drawable.aguachicavacia);
                imgAguaGrande.setImageResource(R.drawable.aguagrandellena);
                press = false;
                pressgrande = true;
                if (editTextCantidad.getText().toString().isEmpty()) {
                    editTextCantidad.setHint("Escribe un número");
                    TvPrecio.setText("$0");
                } else {
                    TvPrecio.setText("$" + String.valueOf(15 * Integer.parseInt(editTextCantidad.getText().toString())));
                }
                tamaño = "Agua grande";
            }

        });

        editTextCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextCantidad.getText().toString().isEmpty()) {
                    editTextCantidad.setHint("Escribe un número");
                    TvPrecio.setText("$0");
                } else if (press)
                    TvPrecio.setText("$" + String.valueOf(8 * Integer.parseInt(editTextCantidad.getText().toString())));
                else if (pressgrande)
                    TvPrecio.setText("$" + String.valueOf(15 * Integer.parseInt(editTextCantidad.getText().toString())));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TvPrecio.getText().equals("$0")) {
                    Toast.makeText(getActivity(), "Ingrese la cantidad ", Toast.LENGTH_SHORT).show();
                } else {

                    Caja cantidad = new Caja();
                    cantidad.setFecha(LocalDate.now().toString());
                    cantidad.setCantidad(TvPrecio.getText().toString().substring(1));
                    cantidad.setDescripcion(tamaño);

                    Inventario inventario = new Inventario();
                    inventario.setFechaInventario(LocalDate.now().toString());
                    inventario.setProteina(MainActivity.dbI.daoInventario().LastProteina());
                    inventario.setCreatina(MainActivity.dbI.daoInventario().LastCreatina());
                    inventario.setPreworkout(MainActivity.dbI.daoInventario().LastPreworkout());
                    inventario.setAgua(MainActivity.dbI.daoInventario().LastAgua() - Integer.parseInt(editTextCantidad.getText().toString()));
                    inventario.setTermogenico(MainActivity.dbI.daoInventario().LastTermogenico());

                    if (MainActivity.dbI.daoInventario().LastAgua() - Integer.parseInt(editTextCantidad.getText().toString()) < 0) {
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

                DialogAddAgua dialogo = new DialogAddAgua();
                dialogo.show(getActivity().getSupportFragmentManager(), "DialogAddAgua");

            }
        });

        return root;
    }
}