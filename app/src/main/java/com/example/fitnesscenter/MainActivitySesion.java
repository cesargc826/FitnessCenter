package com.example.fitnesscenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivitySesion extends AppCompatActivity {
    private TextView TxtRegistrarse ;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sesion);

        //Crer notificaion
        createNotificationChannel();

        auth = FirebaseAuth.getInstance();
    }

    private void createNotificationChannel() {
        String channelId="FitnessCenter";
        String channelName = "mensaje de FitnessCenter";
        int importancia = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importancia);
        channel.setShowBadge(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

    }

    public void muestraNotificacion(String cabecera, String cuerpo){
        int icon= R.drawable.pesa;
        NotificationManager manager = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder (getApplicationContext(),  "FitnessCenter").setAutoCancel (true)
                .setContentTitle (cabecera)
                .setContentText(cuerpo)
                .setPriority (NotificationCompat.PRIORITY_LOW)
                .setSmallIcon (icon)
                .setLargeIcon (BitmapFactory.decodeResource (getResources (), R.mipmap.ic_launcher))
                .setTicker (cuerpo)
                .setAutoCancel (false).build();
        manager.notify( 1, notification);
    }


    public void Iniciar(View view) {
        EditText editEmail = (TextInputEditText) findViewById(R.id.tilEmail_et);
        EditText editPwd = (EditText) findViewById(R.id.tilPassword_et);

        String Correo = editEmail.getText().toString().trim();
        String Contrasena = editPwd.getText().toString().trim();
        if (Correo.isEmpty()) {
            Toast.makeText(this, "Introduzca su correo electronico", Toast.LENGTH_SHORT).show();
        } else if(Contrasena.isEmpty()){
            Toast.makeText(this, "Introduzca su contraseña", Toast.LENGTH_SHORT).show();
        }else if(Contrasena.length()<6){
            Toast.makeText(this, "La contraseña no debe tener menos de 6 caracteres", Toast.LENGTH_SHORT).show();
        }else{
            // Autenticar usuario
            auth.signInWithEmailAndPassword(Correo, Contrasena)
                    .addOnCompleteListener(MainActivitySesion.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                muestraNotificacion("FitnessCenter","Bienvendio a la app de FitnessCenter");
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("Usuario", Correo);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(MainActivitySesion.this, "Fallo la autenticación, revise sus credenciales", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}