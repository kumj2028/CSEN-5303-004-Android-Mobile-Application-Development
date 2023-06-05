package com.example.exercisetwo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addButton, subtractButton, resetButton;
    TextView tv1;
    EditText scoreText;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);
        resetButton = findViewById(R.id.resetButton);
        scoreText = findViewById(R.id.editText);
        scoreText.setText(Integer.toString(x));
        tv1 = findViewById(R.id.myTextTitle);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNumber = scoreText.getText().toString();
                try {
                    x = Integer.valueOf(textNumber);
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "value in 'Number:' is not a number", Toast.LENGTH_LONG).show();
                    return;
                }
                x+=1;
                scoreText.setText(Integer.toString(x));
                scoreText.setBackgroundColor(Color.CYAN);
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNumber = scoreText.getText().toString();
                try {
                    x = Integer.valueOf(textNumber);
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "value in 'Number:' is not a number",Toast.LENGTH_LONG).show();
                    return;
                }
                x-=1;
                scoreText.setText(Integer.toString(x));
                scoreText.setBackgroundColor(Color.CYAN);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreText.setText(Integer.toString(0));
                scoreText.setBackgroundColor(Color.CYAN);
            }
        });
    }
}