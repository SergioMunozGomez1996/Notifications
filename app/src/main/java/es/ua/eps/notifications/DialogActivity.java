package es.ua.eps.notifications;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    int mSelectedSingleItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button tamañoButton = findViewById(R.id.tamanoButton);
        Button colorButton = findViewById(R.id.colorButton);
        final TextView textView = findViewById(R.id.contentTextView);

        tamañoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = {"Pequeño", "Normal", "Grande"};


                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);

                builder.setTitle("Tamaño de texto")
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mSelectedSingleItem = which;
                            }
                        })
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                switch (mSelectedSingleItem){
                                    case 0:
                                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                                        break;
                                    case 1:
                                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                                        break;
                                    case 2:
                                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
            }
        });
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = {"Blanco y Negro", "Negro y Blanco", "Negro y verde"};


                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);

                builder.setTitle("Color Fondo y Texto")
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mSelectedSingleItem = which;
                            }
                        })
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                switch (mSelectedSingleItem){
                                    case 0:
                                        textView.setBackgroundColor(Color.WHITE);
                                        textView.setTextColor(Color.BLACK);
                                        break;
                                    case 1:
                                        textView.setBackgroundColor(Color.BLACK);
                                        textView.setTextColor(Color.WHITE);
                                        break;
                                    case 2:
                                        textView.setBackgroundColor(Color.BLACK);
                                        textView.setTextColor(Color.GREEN);
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
            }
        });
    }
}
