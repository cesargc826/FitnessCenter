package com.example.fitnesscenter;

import static com.example.fitnesscenter.R.id.TxtDescripcion;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.fitnesscenter.BDInventario.Inventario;
import com.example.fitnesscenter.model.ItemList;
import com.example.fitnesscenter.ui.gallery.Caja;

import java.time.LocalDate;

public class DialogoPrentrenos extends DialogFragment {
    Button compra, cancelar;
    EditText editTxtCant;
    TextView TxtTituloDialogo;
    TextView TxtPrecio;
    TextView TxtDescripcion;
    String TituloDialog;
    String precio;

    ItemList Prentre;
    View root;


    public DialogoPrentrenos() {
        this.Prentre = null;
    }


    public DialogoPrentrenos(View root, ItemList items) {
        this.Prentre = items;
        this.root = root;
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View DialogoPrentrenos = inflater.inflate(R.layout.fragment_dialogo_prentrenos, null);
        builder.setView(DialogoPrentrenos);

        TituloDialog = this.Prentre.getTitulo();
        precio = this.Prentre.getPrecio();

        TxtTituloDialogo = (TextView) DialogoPrentrenos.findViewById(R.id.TxtTituloDialogo);
        TxtTituloDialogo.setText(TituloDialog);

        TxtDescripcion = (TextView) DialogoPrentrenos.findViewById(R.id.TxtDescripcion);
        TxtDescripcion.setText(precio);


        cancelar = (Button) DialogoPrentrenos.findViewById(R.id.BtnCancelar);

        //editTxtCant = (EditText) DialogoPrentrenos.findViewById(R.id.editTxtCant);

       /* editTxtCant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTxtCant.getText().toString().isEmpty()) {
                    editTxtCant.setHint("Escribe un número");
                    TxtPrecio.setText("$0");
                } else {
                    TxtPrecio.setText("$" + String.valueOf(precio *
                            Integer.parseInt(editTxtCant.getText().toString())));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });*/

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        /*compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TxtPrecio.getText().equals("$0")) {
                    Toast.makeText(getActivity(), "Ingrese la cantidad ", Toast.LENGTH_SHORT).show();
                } else {

                    Caja cantidad = new Caja();
                    cantidad.setFecha(LocalDate.now().toString());
                    cantidad.setCantidad(TxtPrecio.getText().toString().substring(1));
                    cantidad.setDescripcion("PreWorkout: " + TituloDialog);

                    Inventario inventario = new Inventario();
                    inventario.setFechaInventario(LocalDate.now().toString());
                    inventario.setProteina(MainActivity.dbI.daoInventario().LastProteina());
                    inventario.setCreatina(MainActivity.dbI.daoInventario().LastCreatina());
                    inventario.setPreworkout(MainActivity.dbI.daoInventario().LastPreworkout() - Integer.parseInt(editTxtCant.getText().toString()));
                    inventario.setAgua(MainActivity.dbI.daoInventario().LastAgua());
                    inventario.setTermogenico(MainActivity.dbI.daoInventario().LastTermogenico());

                    if (MainActivity.dbI.daoInventario().LastPreworkout() - Integer.parseInt(editTxtCant.getText().toString()) < 0) {
                        Toast.makeText(getActivity(), "No hay suficientes preentrenos", Toast.LENGTH_SHORT).show();
                    } else {
                        MainActivity.db.metodosDAO().insertAll(cantidad);
                        MainActivity.dbI.daoInventario().insertTodo(inventario);
                        Toast.makeText(getActivity(), "La venta se ha realizado con éxito", Toast.LENGTH_LONG).show();
                    }

                    dismiss();
                }

            }
        });*/

        return builder.create();
    }

}
