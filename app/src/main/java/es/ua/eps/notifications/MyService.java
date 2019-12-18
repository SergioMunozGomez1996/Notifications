package es.ua.eps.notifications;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    MiTarea miTarea;
    private int contador = 1;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        miTarea = new MiTarea();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contador = 101;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if((flags & START_FLAG_RETRY)!=0){
            //el servicio se ha reiniciado
        }else {
            //primera llamada
        }
        if(miTarea.getStatus() != AsyncTask.Status.RUNNING)
            miTarea.execute(); // Así aseguramos que no se llama a la ejecucion del asyntasck si éste está corriendo, evitando lanzar una excepcion

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class MiTarea extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            for(; contador<=10; contador++){
                publishProgress(contador); //llama a onProgressUpdate
                try{
                    Thread.sleep(5000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getApplicationContext(),"Contador " + values[0],Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            stopSelf(); //finaliza el servicio cuando se acaba la ejecución del asyntask
        }
    }
}
