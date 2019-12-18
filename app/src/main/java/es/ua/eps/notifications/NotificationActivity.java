package es.ua.eps.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity {
    private int contTareas = 0;
    private int NOTIF_ALERTA_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button iniciarButton = findViewById(R.id.iniciarButton);
        Button detenerButton = findViewById(R.id.detenerButton);

        //Obtenemos una referencia al servicio de notificaciones
        String ns = Context.NOTIFICATION_SERVICE;
        final NotificationManager notManager = (NotificationManager) getSystemService(ns);

        iniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contTareas = contTareas + 1;

                // Configuramos el canal
                String CHANNEL_ID = "default";
                NotificationChannel mChannel = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    mChannel = new NotificationChannel(CHANNEL_ID, "default",
                            NotificationManager.IMPORTANCE_LOW);
                    notManager.createNotificationChannel(mChannel);
                }

                // Configuramos la notificación
                NotificationCompat.Builder notBuilder;
                notBuilder = new NotificationCompat.Builder( getApplicationContext(), CHANNEL_ID )
                        .setContentTitle("Tareas iniciadas: ")
                        .setContentText(String.valueOf(contTareas))
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        .setLights(Color.YELLOW,100,100);

                // Configuramos el PendingIntent
                Intent intent = new Intent(NotificationActivity.this, NotificationActivity.class);
                PendingIntent pendIntent = PendingIntent.getActivity(
                        NotificationActivity.this, 0, intent, 0);
                // Asignar a la notificación
                notBuilder.setContentIntent( pendIntent );

                // Mostramos la notificación
                int notificationID = NOTIF_ALERTA_ID;
                notManager.notify( notificationID, notBuilder.build() );

            }
        });

        detenerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contTareas = contTareas - 1;
                if(contTareas>0) {
                    // Configuramos la notificación
                    NotificationCompat.Builder notBuilder;
                    notBuilder = new NotificationCompat.Builder( getApplicationContext(), "default" )
                            .setContentTitle("Tareas iniciadas: ")
                            .setContentText(String.valueOf(contTareas))
                            .setSmallIcon(android.R.drawable.stat_sys_warning)
                            .setAutoCancel(true)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                            .setLights(Color.YELLOW,100,100);

                    // Configuramos el PendingIntent
                    Intent intent = new Intent(NotificationActivity.this, NotificationActivity.class);
                    PendingIntent pendIntent = PendingIntent.getActivity(
                            NotificationActivity.this, 0, intent, 0);
                    // Asignar a la notificación
                    notBuilder.setContentIntent( pendIntent );

                    // Mostramos la notificación
                    int notificationID = NOTIF_ALERTA_ID;
                    notManager.notify( notificationID, notBuilder.build() );
                }else
                    notManager.cancel(NOTIF_ALERTA_ID);

            }
        });
    }
}
