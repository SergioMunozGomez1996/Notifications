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

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class SnackBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

        final EditText editText = findViewById(R.id.snackbar_editText);
        final Button button = findViewById(R.id.snackbar_button);
        final TextView textView = findViewById(R.id.snackbar_textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(editText.getText().toString().trim().length() > 0) {
                    final String text = editText.getText().toString() + "\n";
                    final String previuosText = textView.getText().toString();
                    textView.append(text);
                    Snackbar.make(textView, "Tarea añadida", Snackbar.LENGTH_SHORT)
                            .setAction("Deshacer", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    textView.setText(previuosText);
                                }
                            })
                            .show();

                }else{
                    Snackbar.make(textView, "Escribe un texto", Snackbar.LENGTH_SHORT).show();
                }

                editText.setText("");

            }
        });
    }
}
