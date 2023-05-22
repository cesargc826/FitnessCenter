package com.example.fitnesscenter.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.room.Room;

import com.example.fitnesscenter.BDInventario.Inventario;
import com.example.fitnesscenter.MainActivity;
import com.example.fitnesscenter.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    TableLayout tlTabla;
    Button Mostrar, Eliminar, Actualizar;

    TableRow fila;

    TextView tvFecha, tvProteina, tvCreatina, tvPreWorkouts, tvAgua, tvTermogenicos, txtDinero;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        tlTabla = root.findViewById(R.id.TablaInventario);
        txtDinero = root.findViewById(R.id.txtCapital);
        Mostrar = (Button) root.findViewById(R.id.btnMostrar);
        Eliminar = (Button) root.findViewById(R.id.btnEliminar);
        Actualizar = (Button) root.findViewById(R.id.btnActualizar);

        txtDinero.setText("$" + MainActivity.db.metodosDAO().SumaCantidades());

        List<Inventario> listaElementos = MainActivity.dbI.daoInventario().getInventarios();

        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutFecha = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutProteina = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutCreatina = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutPreWorkouts = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutAgua = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutTermogenicos = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        // TABLA
        for (int i = 0; i < listaElementos.size(); i++) {
            fila = new TableRow(getActivity().getApplicationContext());
            fila.setLayoutParams(layoutFila);

            tvFecha = new TextView(getActivity().getApplicationContext());
            tvFecha.setText(listaElementos.get(i).getFechaInventario());
            tvFecha.setGravity(Gravity.CENTER);
            tvFecha.setTextColor(Color.rgb(255, 190, 190));
            tvFecha.setPadding(10, 20, 20, 10);
            tvFecha.setLayoutParams(layoutFecha);
            fila.addView(tvFecha);

            tvProteina = new TextView(getActivity().getApplicationContext());
            tvProteina.setText("" + listaElementos.get(i).getProteina());
            tvProteina.setGravity(Gravity.CENTER);
            tvProteina.setTextColor(Color.WHITE);
            tvProteina.setPadding(10, 20, 20, 10);
            tvProteina.setLayoutParams(layoutProteina);
            fila.addView(tvProteina);

            tvCreatina = new TextView(getActivity().getApplicationContext());
            tvCreatina.setGravity(Gravity.CENTER);
            tvCreatina.setTextColor(Color.WHITE);
            tvCreatina.setText("" + listaElementos.get(i).getCreatina());
            tvCreatina.setPadding(140, 20, 20, 10);
            tvCreatina.setLayoutParams(layoutCreatina);
            fila.addView(tvCreatina);

            tvPreWorkouts = new TextView(getActivity().getApplicationContext());
            tvPreWorkouts.setText("" + listaElementos.get(i).getPreworkout());
            tvPreWorkouts.setGravity(Gravity.CENTER);
            tvPreWorkouts.setTextColor(Color.WHITE);
            tvPreWorkouts.setPadding(140, 20, 20, 10);
            tvPreWorkouts.setLayoutParams(layoutPreWorkouts);
            fila.addView(tvPreWorkouts);

            tvAgua = new TextView(getActivity().getApplicationContext());
            tvAgua.setText("" + listaElementos.get(i).getAgua());
            tvAgua.setGravity(Gravity.CENTER);
            tvAgua.setTextColor(Color.WHITE);
            tvAgua.setPadding(110, 20, 20, 10);
            tvAgua.setLayoutParams(layoutAgua);
            fila.addView(tvAgua);

            tvTermogenicos = new TextView(getActivity().getApplicationContext());
            tvTermogenicos.setText("" + listaElementos.get(i).getTermogenico());
            tvTermogenicos.setGravity(Gravity.CENTER);
            tvTermogenicos.setTextColor(Color.WHITE);
            tvTermogenicos.setPadding(100, 20, 10, 10);
            tvTermogenicos.setLayoutParams(layoutTermogenicos);
            fila.addView(tvTermogenicos);
            tlTabla.addView(fila);
        }

        Mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.contenido, new ViewFragment(), null).addToBackStack(null).commit();
            //Mostrar.setEnabled(false);
            }
        });


        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialogo = new AlertDialog
                        .Builder(getActivity()) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Caja caja = new Caja();
                                caja.setFecha(LocalDate.now().toString());
                                caja.setDescripcion("Cantidad en caja");
                                caja.setCantidad(String.valueOf(MainActivity.db.metodosDAO().SumaCantidades()));

                                MainActivity.db.metodosDAO().DeleteALL();

                                MainActivity.db.metodosDAO().insertAll(caja);
                                
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón negativo, no confirmaron
                                // Simplemente descartamos el diálogo
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar") // El título
                        .setMessage("¿Desea eliminar el historial de ventas?") // El mensaje
                        .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!
                dialogo.show();
            }
        });


        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(getActivity()) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Inventario inventario = new Inventario();
                                inventario.setFechaInventario(LocalDate.now().toString());
                                inventario.setProteina(MainActivity.dbI.daoInventario().LastProteina());
                                inventario.setCreatina(MainActivity.dbI.daoInventario().LastCreatina());
                                inventario.setPreworkout(MainActivity.dbI.daoInventario().LastPreworkout());
                                inventario.setAgua(MainActivity.dbI.daoInventario().LastAgua());
                                inventario.setTermogenico(MainActivity.dbI.daoInventario().LastTermogenico());

                                MainActivity.dbI.daoInventario().DeleteInventario();

                                MainActivity.dbI.daoInventario().insertTodo(inventario);

                                tlTabla.removeAllViews();

                                List<Inventario> UltimoElemento = MainActivity.dbI.daoInventario().getInventarios();

                                UltimoRegistro(UltimoElemento);


                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón negativo, no confirmaron
                                // Simplemente descartamos el diálogo
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar") // El título
                        .setMessage("¿Desea eliminar el inventario?") // El mensaje
                        .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!
                dialogo.show();
            }
        });

        return root;
    }
    
    public void UltimoRegistro (List<Inventario> listaElementos) {

        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutFecha = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutProteina = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutCreatina = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutPreWorkouts = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutAgua = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutTermogenicos = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        
        // TABLA
        for (int i = 0; i < listaElementos.size(); i++) {
            fila = new TableRow(getActivity().getApplicationContext());
            fila.setLayoutParams(layoutFila);

            tvFecha = new TextView(getActivity().getApplicationContext());
            tvFecha.setText(listaElementos.get(i).getFechaInventario());
            tvFecha.setGravity(Gravity.CENTER);
            tvFecha.setTextColor(Color.rgb(255, 190, 190));
            tvFecha.setPadding(10, 20, 20, 10);
            tvFecha.setLayoutParams(layoutFecha);
            fila.addView(tvFecha);

            tvProteina = new TextView(getActivity().getApplicationContext());
            tvProteina.setText("" + listaElementos.get(i).getProteina());
            tvProteina.setGravity(Gravity.CENTER);
            tvProteina.setTextColor(Color.WHITE);
            tvProteina.setPadding(10, 20, 20, 10);
            tvProteina.setLayoutParams(layoutProteina);
            fila.addView(tvProteina);

            tvCreatina = new TextView(getActivity().getApplicationContext());
            tvCreatina.setGravity(Gravity.CENTER);
            tvCreatina.setTextColor(Color.WHITE);
            tvCreatina.setText("" + listaElementos.get(i).getCreatina());
            tvCreatina.setPadding(140, 20, 20, 10);
            tvCreatina.setLayoutParams(layoutCreatina);
            fila.addView(tvCreatina);

            tvPreWorkouts = new TextView(getActivity().getApplicationContext());
            tvPreWorkouts.setText("" + listaElementos.get(i).getPreworkout());
            tvPreWorkouts.setGravity(Gravity.CENTER);
            tvPreWorkouts.setTextColor(Color.WHITE);
            tvPreWorkouts.setPadding(140, 20, 20, 10);
            tvPreWorkouts.setLayoutParams(layoutPreWorkouts);
            fila.addView(tvPreWorkouts);

            tvAgua = new TextView(getActivity().getApplicationContext());
            tvAgua.setText("" + listaElementos.get(i).getAgua());
            tvAgua.setGravity(Gravity.CENTER);
            tvAgua.setTextColor(Color.WHITE);
            tvAgua.setPadding(110, 20, 20, 10);
            tvAgua.setLayoutParams(layoutAgua);
            fila.addView(tvAgua);

            tvTermogenicos = new TextView(getActivity().getApplicationContext());
            tvTermogenicos.setText("" + listaElementos.get(i).getTermogenico());
            tvTermogenicos.setGravity(Gravity.CENTER);
            tvTermogenicos.setTextColor(Color.WHITE);
            tvTermogenicos.setPadding(100, 20, 10, 10);
            tvTermogenicos.setLayoutParams(layoutTermogenicos);
            fila.addView(tvTermogenicos);
            tlTabla.addView(fila);
        }
    }
    
}