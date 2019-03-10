package de.cm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button server7_button = findViewById(R.id.server7_button);
        server7_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("url", "https://server7.bbn.de/cm4_5/");
                startActivity(intent);
            }
        });


        final EditText editText = findViewById(R.id.url_editText);
        final SharedPreferences sharedpreferences = getSharedPreferences("cm_pref", Context.MODE_PRIVATE);
        String default_url = sharedpreferences.getString("url", "");

        if (default_url != null) {
            editText.setText(default_url);
        }

        Button custom_button = findViewById(R.id.custom);
        custom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                if (!url.equals("")) {
                    sharedpreferences.edit().putString("url", url).apply();
                    Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Leer Input", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
