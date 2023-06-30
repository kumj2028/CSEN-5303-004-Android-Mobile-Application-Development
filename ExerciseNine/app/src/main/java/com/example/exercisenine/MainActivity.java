package com.example.exercisenine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void selectFragment(View view) {
        Fragment fr;
        if(view == findViewById(R.id.button1)) {
            fr = new FragmentOne();
        } else if (view == findViewById(R.id.button2)) {
            fr = new FragmentTwo();
        } else if (view == findViewById(R.id.button3)) {
            fr = new FragmentThree();
        } else {
            fr = new FragmentFour();
        }
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_place, fr, null)
                .commit();
    }
}