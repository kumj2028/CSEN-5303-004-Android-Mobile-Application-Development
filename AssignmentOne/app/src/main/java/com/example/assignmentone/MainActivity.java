package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String inputType, outputType;
    HashMap<String, Double> unitToMeterMap;
    EditText inputText;
    TextView outputText;

    RadioButton defaultInputType;
    RadioButton defaultOutputType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitToMeterMap = new HashMap<String, Double>();
        unitToMeterMap.put("inch", 0.0254);
        unitToMeterMap.put("foot", 0.3048);
        unitToMeterMap.put("yard", 0.9144);
        unitToMeterMap.put("mile", 1609.34);
        unitToMeterMap.put("centimeter", 0.01);
        unitToMeterMap.put("meter", 1.0);
        unitToMeterMap.put("kilometer", 1000.0);

        inputText = findViewById(R.id.inputDistance);
        outputText = findViewById(R.id.outputDistance);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            int id = view.getId();
            // Check which radio button was clicked
            if (id == R.id.inputInch) {
                inputType = "inch";
            } else if (id == R.id.inputFoot) {
                inputType = "foot";
            } else if (id == R.id.inputYard) {
                inputType = "yard";
            } else if (id == R.id.inputMile) {
                inputType = "mile";
            } else if (id == R.id.inputCentimeter) {
                inputType = "centimeter";
            } else if (id == R.id.inputMeter) {
                inputType = "meter";
            } else if (id == R.id.inputKilometer) {
                inputType = "kilometer";
            } else if (id == R.id.outputInch) {
                outputType = "inch";
            } else if (id == R.id.outputFoot) {
                outputType = "foot";
            } else if (id == R.id.outputYard) {
                outputType = "yard";
            } else if (id == R.id.outputMile) {
                outputType = "mile";
            } else if (id == R.id.outputCentimeter) {
                outputType = "centimeter";
            } else if (id == R.id.outputMeter) {
                outputType = "meter";
            } else if (id == R.id.outputKilometer) {
                outputType = "kilometer";
            }
        }
    }

    public void onConvertButtonClicked(View view) {
        double inputValue;
        try {
            inputValue = Double.valueOf(inputText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "input value is not a number",Toast.LENGTH_LONG).show();
            return;
        }
        if (inputType == null || outputType == null) {
            Toast.makeText(this, "please select an input unit and output unit",Toast.LENGTH_LONG).show();
            return;
        }

        double valueInMeters = inputValue * unitToMeterMap.get(inputType);
        double outputValue = valueInMeters / unitToMeterMap.get(outputType);
        outputText.setText(String.format("%8.4g", outputValue));
    }
}