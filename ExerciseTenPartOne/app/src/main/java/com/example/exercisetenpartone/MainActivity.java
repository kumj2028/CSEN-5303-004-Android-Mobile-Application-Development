package com.example.exercisetenpartone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    Button mStartTaskButton, mToastButton;
    TextView mTextView1, mTextView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToastButton = (Button) findViewById(R.id.toast_button);
        mTextView2 = (TextView) findViewById(R.id.textview2);
        mToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button was pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClick(View v) {
        mTextView2.setText("currently on stage 0 of 20");
        Thread myWorkerThread = new Thread(new Runnable() {
            public void run() {
                // do long running task here
                for(int i=0; i < 20; i=i+1) {
                    final int k = i + 1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mTextView2.post(new Runnable() {
                        public void run() {
                            mTextView2.setText("currently on stage " + k + " of 20");
                        }
                    });
                }

                mTextView2.post(new Runnable() {
                    public void run() {
                        mTextView2.setText("finished with long running task");
                    }
                });
            }
        });
        myWorkerThread.start();

    }
}