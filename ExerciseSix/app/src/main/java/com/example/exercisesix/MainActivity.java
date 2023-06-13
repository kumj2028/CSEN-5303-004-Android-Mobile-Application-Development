package com.example.exercisesix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textView1);
        String oldText = tv1.getText().toString();
        tv1.setText(oldText + "\n" + "in the onCreate() method override");
    }

    @Override
    protected void onStart(){
        super.onStart();
        String oldText = tv1.getText().toString();
        tv1.setText(oldText + "\n" + "in the onStart() method override");
    }

    @Override
    protected void onResume(){
        super.onResume();
        String oldText = tv1.getText().toString();
        tv1.setText(oldText + "\n" + "in the onResume() method override");
    }

    @Override
    protected void onPause(){
        super.onPause();
        String oldText = tv1.getText().toString();
        tv1.setText(oldText + "\n" + "in the onPause() method override");
    }

    @Override
    protected void onStop(){
        super.onStop();
        String oldText = tv1.getText().toString();
        tv1.setText(oldText + "\n" + "in the onStop() method override");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        String oldText = tv1.getText().toString();
        tv1.setText(oldText + "\n" + "in the onRestart() method override");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        String oldText = tv1.getText().toString();
        tv1.setText(oldText + "\n" + "in the onDestroy() method override");
    }
}