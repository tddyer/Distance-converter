package com.example.distanceconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // input/output textViews and input fields
    private TextView inputTextView;
    private TextView outputTextView;
    private EditText inputField;
    private TextView outputField;

    // conversion constants
    final double MILESTOKILOMETERS = 1.60934;
    final double KILOMETERSTOMILES = 0.621371;

    // holds the appropriate conversion constant to use
    double selectedConversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.conversionInputField);
        outputField = findViewById(R.id.conversionOutputTextView);
        inputTextView = findViewById(R.id.inputValueTextView);
        outputTextView = findViewById(R.id.outputValueTextView);
    }

    // updates textViews and conversion multiplier based upon radio button selection
    public void radioSelected(View v) {
        switch (v.getId()) {
            case R.id.radioMiletoKm:
                inputTextView.setText(R.string.milesTextView);
                outputTextView.setText(R.string.kilometersTextView);
                selectedConversion = MILESTOKILOMETERS;
                break;
            case R.id.radioKmToMile:
                inputTextView.setText(R.string.kilometersTextView);
                outputTextView.setText(R.string.milesTextView);
                selectedConversion = KILOMETERSTOMILES;
                break;
        }
    }


    public void convert(View v) {
        // get input value -> convert to appropriate output value
        double inputValue = Double.parseDouble(inputField.getText().toString());
        double outputValue = inputValue * selectedConversion;

        // update input/output fields after converting
        outputField.setText(String.format(Locale.US, "%.1f", outputValue));
        inputField.setText("");
    }
}