package es.ua.eps.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        final EditText editText = findViewById(R.id.toast_editText);
        final Button button = findViewById(R.id.toast_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getBaseContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View layout = inflater.inflate(R.layout.toast_layout, null);
                TextView txtMsg=(TextView)layout.findViewById(R.id.txtMsg);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.setGravity(Gravity.CENTER,0,0);


                if(editText.getText().toString().trim().length() > 0) {
                    txtMsg.setText(editText.getText().toString());
                    toast.show();

                }else{
                    txtMsg.setText("Escribe un texto");
                    toast.show();
                }

                editText.setText("");

            }
        });
    }
}
