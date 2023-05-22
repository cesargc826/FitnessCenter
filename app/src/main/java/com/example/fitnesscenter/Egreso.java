package com.example.fitnesscenter;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesscenter.ui.gallery.Caja;

import java.time.LocalDate;

public class Egreso extends Fragment {

    View root;
    RadioGroup accion;
    RadioButton rbSacar, rbIngresar;
    TextView txtAccion;
    Button btnAccion;
    EditText etCantidad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_egreso, container, false);

        accion = (RadioGroup) root.findViewById(R.id.accion);
        rbSacar = (RadioButton) root.findViewById(R.id.rbSacar);
        rbIngresar = (RadioButton) root.findViewById(R.id.rbIngresar);
        txtAccion = (TextView) root.findViewById(R.id.txtAccion);
        btnAccion = (Button) root.findViewById(R.id.btnAccion);
        etCantidad = (EditText) root.findViewById(R.id.etCantidad);

        btnAccion.setEnabled(false);

        accion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbIngresar.isChecked()) {
                    txtAccion.setText("¿Cuánto dinero desea ingresar?");
                    btnAccion.setEnabled(true);
                } else if (rbSacar.isChecked()) {
                    txtAccion.setText("¿Cuánto dinero desea sacar?");
                    btnAccion.setEnabled(true);
                }
            }
        });

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = getActivity().getIntent().getExtras().getString("Usuario");
                if (usuario.equals("fitnessadmin@gmail.com")) {
                    if (etCantidad.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                    } else {
                        if (rbSacar.isChecked()) {
                            Caja caja = new Caja();
                            caja.setFecha(LocalDate.now().toString());
                            caja.setCantidad("-" + etCantidad.getText().toString());
                            caja.setDescripcion("Retiro de dinero en caja");

                            if (MainActivity.db.metodosDAO().SumaCantidades() < Integer.parseInt(etCantidad.getText().toString())) {
                                Toast.makeText(getActivity(), "No suficiente dinero para recoger", Toast.LENGTH_SHORT).show();
                            } else {
                                muestraNotificacion("Fitness Center", "Usted ha retirado $" + etCantidad.getText() + " de la caja");
                                MainActivity.db.metodosDAO().insertAll(caja);
                                Toast.makeText(getActivity(), "El retiro se ha realizado con éxito", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Caja caja = new Caja();
                            caja.setFecha(LocalDate.now().toString());
                            caja.setCantidad(etCantidad.getText().toString());
                            caja.setDescripcion("Ingreso de dinero en caja");

                            muestraNotificacion("Fitness Center", "Usted ha ingresado $" + etCantidad.getText() + " a la caja");
                            MainActivity.db.metodosDAO().insertAll(caja);
                            Toast.makeText(getActivity(), "El ingreso se ha realizado con éxito", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Solo los administradores pueden acceder a este apartado", Toast.LENGTH_LONG).show();
                }

            }
        });




        return root;
    }

    //Muestra la notificación
    public void muestraNotificacion(String cabecera, String cuerpo) {
        int icon = R.drawable.pesa;
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(getActivity().getApplicationContext(), "FitnessCenter")
                .setAutoCancel(true)
                .setContentTitle(cabecera)
                .setContentText(cuerpo)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setTicker(cuerpo)
                .setAutoCancel(false)
                .build();
        manager.notify(1, notification);
    }
}