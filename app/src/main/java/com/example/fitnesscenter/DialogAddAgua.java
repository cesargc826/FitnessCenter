package com.example.fitnesscenter;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.fitnesscenter.BDInventario.Inventario;
import com.example.fitnesscenter.model.ItemList;
import com.example.fitnesscenter.ui.gallery.Caja;

import java.time.LocalDate;

public class DialogAddAgua extends DialogFragment {
    Button añadir, cancelar;
    EditText editTxtCant;
    TextView TxtPrecio;
    RadioButton rbChica, rbGrande;
    RadioGroup tamaño;
    String tama;
    String[] lista;


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View DialogAddAgua = inflater.inflate(R.layout.fragment_dialog_addagua, null);
        builder.setView(DialogAddAgua);

        editTxtCant = (EditText) DialogAddAgua.findViewById(R.id.editTxtCant);
        añadir = (Button) DialogAddAgua.findViewById(R.id.BtnComprar);
        cancelar = (Button) DialogAddAgua.findViewById(R.id.BtnCancelar);
        TxtPrecio = (TextView) DialogAddAgua.findViewById(R.id.TxtPrecio);
        rbChica = (RadioButton) DialogAddAgua.findViewById(R.id.rbChica);
        rbGrande = (RadioButton) DialogAddAgua.findViewById(R.id.rbGrande);
        tamaño = (RadioGroup) DialogAddAgua.findViewById(R.id.size);
        lista = getResources().getStringArray(R.array.Marcas_Preentreno);


        tamaño.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!(editTxtCant.getText().toString().isEmpty())) {
                    printprecio();
                } else {

                }
            }
        });


        editTxtCant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTxtCant.getText().toString().isEmpty()) {
                    editTxtCant.setHint("Escribe un número");
                    TxtPrecio.setText("$0");
                } else {
                    printprecio();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TxtPrecio.getText().equals("$0")) {
                    Toast.makeText(getActivity(), "Ingrese la cantidad", Toast.LENGTH_SHORT).show();
                } else {

                    Caja cantidad = new Caja();
                    cantidad.setFecha(LocalDate.now().toString());
                    cantidad.setCantidad(String.valueOf((-1) * Integer.parseInt((TxtPrecio.getText().toString().substring(1)))));
                    cantidad.setDescripcion(tama);

                    Inventario inventario = new Inventario();
                    inventario.setFechaInventario(LocalDate.now().toString());
                    inventario.setProteina(MainActivity.dbI.daoInventario().LastProteina());
                    inventario.setCreatina(MainActivity.dbI.daoInventario().LastCreatina());
                    inventario.setPreworkout(MainActivity.dbI.daoInventario().LastPreworkout());
                    inventario.setAgua(MainActivity.dbI.daoInventario().LastAgua() + Integer.parseInt(editTxtCant.getText().toString()));
                    inventario.setTermogenico(MainActivity.dbI.daoInventario().LastTermogenico());

                    if (Integer.parseInt(TxtPrecio.getText().toString().substring(1)) > MainActivity.db.metodosDAO().SumaCantidades()) {
                        Toast.makeText(getActivity(), "No hay suficientes fondos", Toast.LENGTH_SHORT).show();
                    } else {
                        MainActivity.db.metodosDAO().insertAll(cantidad);
                        MainActivity.dbI.daoInventario().insertTodo(inventario);
                        Toast.makeText(getActivity(), "La compra se ha realizado con éxito", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return builder.create();
    }

    public void printprecio() {
        if (rbChica.isChecked()) {
            tama = "Agua Chica";
            TxtPrecio.setText("$" + String.valueOf(Integer.parseInt(editTxtCant.getText().toString()) * 5));
        } else {
            tama = "Agua Grande";
            TxtPrecio.setText("$" + String.valueOf(Integer.parseInt(editTxtCant.getText().toString()) * 11));
        }
    }


}
