package com.example.exerciseeight;

import static java.lang.Integer.max;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.textView1);

        String name = "";
        String columnNames[];

        SQLiteDatabase db = openOrCreateDatabase("EmployeeDatabase", MODE_PRIVATE, null);

        db.execSQL("DROP TABLE EmployeeInformation;");
        db.execSQL("CREATE TABLE EmployeeInformation (LastName VARCHAR, FirstName VARCHAR, PhoneNumber VARCHAR, Address VARCHAR);");
        db.execSQL("INSERT INTO EmployeeInformation VALUES('Jones', 'Randy', '555-4325', '123 Ave A');");
        db.execSQL("INSERT INTO EmployeeInformation VALUES('Smith', 'Robert', '555-1641', '456 Lane B');");
        db.execSQL("INSERT INTO EmployeeInformation VALUES('Garcia', 'Roland', '555-4142', '789 Street C');");

        // following lines query database and echo results to logcat
        Cursor cursor = db.rawQuery("SELECT * from EmployeeInformation", null);
        columnNames = cursor.getColumnNames();

        cursor.moveToFirst();
        for (String column: columnNames) {
            name += cursor.getString(cursor.getColumnIndexOrThrow(column)) + ", ";
        }
        name = name.substring(0, name.length() - 2);
        Log.d("results", name);
        tv1.append(name + "\n");

        name = "";
        cursor.moveToNext();
        for (String column: columnNames) {
            name += cursor.getString(cursor.getColumnIndexOrThrow(column)) + ", ";
        }
        name = name.substring(0, name.length() - 2);
        Log.d("results", name);
        tv1.append(name + "\n");

        name = "";
        cursor.moveToNext();
        for (String column: columnNames) {
            name += cursor.getString(cursor.getColumnIndexOrThrow(column)) + ", ";
        }
        name = name.substring(0, name.length() - 2);
        Log.d("results", name);
        tv1.append(name + "\n");

        db.close();
    }
}