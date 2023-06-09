package com.example.exercisethree;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        addButton.setOnClickListener(this);
        subtractButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.resetButton) {
            scoreText.setText(Integer.toString(0));
            scoreText.setBackgroundColor(Color.YELLOW);
        }
        else {
            String textNumber = scoreText.getText().toString();
            try {
                x = Integer.valueOf(textNumber);
            } catch (Exception e) {
                Toast.makeText(this, "value in 'Number:' is not a number", Toast.LENGTH_LONG).show();
                return;
            }
            if (v.getId() == R.id.addButton) {
                x += 1;
                scoreText.setText(Integer.toString(x));
                scoreText.setBackgroundColor(Color.CYAN);
            } else if (v.getId() == R.id.subtractButton) {
                x -= 1;
                scoreText.setText(Integer.toString(x));
                scoreText.setBackgroundColor(Color.GREEN);
            }
        }
    }
}