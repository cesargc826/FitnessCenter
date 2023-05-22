package com.example.fitnesscenter.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.fitnesscenter.BDInventario.Inventario;
import com.example.fitnesscenter.DialogoAddProte;
import com.example.fitnesscenter.MainActivity;
import com.example.fitnesscenter.R;
import com.example.fitnesscenter.ui.gallery.BDCaja;
import com.example.fitnesscenter.ui.gallery.Caja;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class SlideshowFragment extends Fragment {

    private int aux, val;
    TextView Precio;
    Spinner marcas, cantidades;
    View root;
    ImageView Proteina;
    Button compra;
    FloatingActionButton addInventario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        compra = (Button) root.findViewById(R.id.btnCompra);
        Proteina = (ImageView) root.findViewById(R.id.imgProteina);
        Precio = (TextView) root.findViewById(R.id.txtPrecio);
        marcas = (Spinner) root.findViewById(R.id.spmarca);
        addInventario = (FloatingActionButton) root.findViewById(R.id.BtnAgregar);
        ArrayAdapter Marcas = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinner_item,
                getResources().getStringArray(R.array.Marcas_Proteina));

        Marcas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marcas.setAdapter(Marcas);

        marcas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        if (val == 0) {
                            Precio.setText("$" + String.valueOf(28 * 1));
                        } else if (val == 1) {
                            Precio.setText("$" + String.valueOf(28 * 2));
                        } else if (val == 2) {
                            Precio.setText("$" + String.valueOf(28 * 3));
                        } else if (val == 3) {
                            Precio.setText("$" + String.valueOf(28 * 4));
                        } else if (val == 4) {
                            Precio.setText("$" + String.valueOf(28 * 5));
                        }
                        Proteina.setImageResource(R.drawable.p_optimum);
                        aux = 0;
                        break;
                    case 1:
                        if (val == 0) {
                            Precio.setText("$" + String.valueOf(20 * 1));
                        } else if (val == 1) {
                            Precio.setText("$" + String.valueOf(20 * 2));
                        } else if (val == 2) {
                            Precio.setText("$" + String.valueOf(20 * 3));
                        } else if (val == 3) {
                            Precio.setText("$" + String.valueOf(20 * 4));
                        } else if (val == 4) {
                            Precio.setText("$" + String.valueOf(20 * 5));
                        }
                        Proteina.setImageResource(R.drawable.p_43);
                        aux = 1;
                        break;

                    case 2:
                        if (val == 0) {
                            Precio.setText("$" + String.valueOf(23 * 1));
                        } else if (val == 1) {
                            Precio.setText("$" + String.valueOf(23 * 2));
                        } else if (val == 2) {
                            Precio.setText("$" + String.valueOf(23 * 3));
                        } else if (val == 3) {
                            Precio.setText("$" + String.valueOf(23 * 4));
                        } else if (val == 4) {
                            Precio.setText("$" + String.valueOf(23 * 5));
                        }
                        Proteina.setImageResource(R.drawable.p_birdman);
                        aux = 2;
                        break;
                    case 3:
                        if (val == 0) {
                            Precio.setText("$" + String.valueOf(25 * 1));
                        } else if (val == 1) {
                            Precio.setText("$" + String.valueOf(25 * 2));
                        } else if (val == 2) {
                            Precio.setText("$" + String.valueOf(25 * 3));
                        } else if (val == 3) {
                            Precio.setText("$" + String.valueOf(25 * 4));
                        } else if (val == 4) {
                            Precio.setText("$" + String.valueOf(25 * 5));
                        }
                        Proteina.setImageResource(R.drawable.p_nitrotech);
                        aux = 3;
                        break;
                    case 4:
                        if (val == 0) {
                            Precio.setText("$" + String.valueOf(30 * 1));
                        } else if (val == 1) {
                            Precio.setText("$" + String.valueOf(30 * 2));
                        } else if (val == 2) {
                            Precio.setText("$" + String.valueOf(30 * 3));
                        } else if (val == 3) {
                            Precio.setText("$" + String.valueOf(30 * 4));
                        } else if (val == 4) {
                            Precio.setText("$" + String.valueOf(30 * 5));
                        }
                        Proteina.setImageResource(R.drawable.p_is0100);
                        aux = 4;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        cantidades = (Spinner) root.findViewById(R.id.spcantidades);
        ArrayAdapter Cantidades = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinner_item,
                getResources().getStringArray(R.array.Cantidades_Proteina));
        Cantidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cantidades.setAdapter(Cantidades);

        cantidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        if (aux == 0) {
                            Precio.setText("$" + String.valueOf(28 * 1));
                        } else if (aux == 1) {
                            Precio.setText("$" + String.valueOf(20 * 1));
                        } else if (aux == 2) {
                            Precio.setText("$" + String.valueOf(23 * 1));
                        } else if (aux == 3) {
                            Precio.setText("$" + String.valueOf(25 * 1));
                        } else if (aux == 4) {
                            Precio.setText("$" + String.valueOf(30 * 1));
                        }
                        val = 0;
                        break;
                    case 1:
                        if (aux == 0) {
                            Precio.setText("$" + String.valueOf(28 * 2));
                        } else if (aux == 1) {
                            Precio.setText("$" + String.valueOf(20 * 2));
                        } else if (aux == 2) {
                            Precio.setText("$" + String.valueOf(23 * 2));
                        } else if (aux == 3) {
                            Precio.setText("$" + String.valueOf(25 * 2));
                        } else if (aux == 4) {
                            Precio.setText("$" + String.valueOf(30 * 2));
                        }
                        val = 1;
                        break;
                    case 2:
                        if (aux == 0) {
                            Precio.setText("$" + String.valueOf(28 * 3));
                        } else if (aux == 1) {
                            Precio.setText("$" + String.valueOf(20 * 3));
                        } else if (aux == 2) {
                            Precio.setText("$" + String.valueOf(23 * 3));
                        } else if (aux == 3) {
                            Precio.setText("$" + String.valueOf(25 * 3));
                        } else if (aux == 4) {
                            Precio.setText("$" + String.valueOf(30 * 3));
                        }
                        val = 2;
                        break;
                    case 3:
                        if (aux == 0) {
                            Precio.setText("$" + String.valueOf(28 * 4));
                        } else if (aux == 1) {
                            Precio.setText("$" + String.valueOf(20 * 4));
                        } else if (aux == 2) {
                            Precio.setText("$" + String.valueOf(23 * 4));
                        } else if (aux == 3) {
                            Precio.setText("$" + String.valueOf(25 * 4));
                        } else if (aux == 4) {
                            Precio.setText("$" + String.valueOf(30 * 4));
                        }
                        val = 3;
                        break;
                    case 4:
                        if (aux == 0) {
                            Precio.setText("$" + String.valueOf(28 * 5));
                        } else if (aux == 1) {
                            Precio.setText("$" + String.valueOf(20 * 5));
                        } else if (aux == 2) {
                            Precio.setText("$" + String.valueOf(23 * 5));
                        } else if (aux == 3) {
                            Precio.setText("$" + String.valueOf(25 * 5));
                        } else if (aux == 4) {
                            Precio.setText("$" + String.valueOf(30 * 5));
                        }
                        val = 4;
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Caja cantidad = new Caja();
                cantidad.setFecha(LocalDate.now().toString());
                cantidad.setCantidad(Precio.getText().toString().substring(1));
                cantidad.setDescripcion("Proteina: " + marcas.getSelectedItem().toString());

                Inventario inventario = new Inventario();
                inventario.setFechaInventario(LocalDate.now().toString());
                inventario.setProteina(MainActivity.dbI.daoInventario().LastProteina() - Integer.parseInt(cantidades.getSelectedItem().toString()));
                inventario.setCreatina(MainActivity.dbI.daoInventario().LastCreatina());
                inventario.setPreworkout(MainActivity.dbI.daoInventario().LastPreworkout());
                inventario.setAgua(MainActivity.dbI.daoInventario().LastAgua());
                inventario.setTermogenico(MainActivity.dbI.daoInventario().LastTermogenico());

                if (MainActivity.dbI.daoInventario().LastProteina() - Integer.parseInt(cantidades.getSelectedItem().toString()) < 0) {
                    Toast.makeText(getActivity(), "No hay suficientes proteínas", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.db.metodosDAO().insertAll(cantidad);
                    MainActivity.dbI.daoInventario().insertTodo(inventario);
                    Toast.makeText(getActivity(), "La venta se ha realizado con éxito", Toast.LENGTH_LONG).show();
                }
            }
        });


        addInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    DialogoAddProte dialogo = new DialogoAddProte();
                    dialogo.show(getActivity().getSupportFragmentManager(), "DialogoAddProte");

            }
        });

        return root;
    }

}