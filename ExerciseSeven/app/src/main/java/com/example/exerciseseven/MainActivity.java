package com.example.exerciseseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlInput = findViewById(R.id.editText1);
    }

    public void navigateToURL(View view) {
        String rawUrl = urlInput.getText().toString();
        Log.d("results", rawUrl);
        Uri uri = Uri.parse(rawUrl);
        Log.d("results", uri.toString());
        Intent urlIntent = new Intent(Intent.ACTION_VIEW, uri);
        Intent browserChooser = Intent.createChooser(urlIntent, "browser chooser");
        try {
            startActivity(browserChooser);
        } catch (ActivityNotFoundException e) {
            Log.d("results", "failed to open URL in a browser");
        }
    }
}