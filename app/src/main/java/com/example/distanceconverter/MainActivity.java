package com.example.distanceconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
    private TextView conversionHistoryTextView;

    // conversion constants
    private final double MILESTOKILOMETERS = 1.60934;
    private final double KILOMETERSTOMILES = 0.621371;

    // holds the appropriate conversion constant to use - default is Mi -> Km
    private double selectedConversion = MILESTOKILOMETERS;

    // contains record of all completed conversions
    private String conversions = "";

    private String inputString;
    private String outputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.conversionInputField);
        outputField = findViewById(R.id.conversionOutputTextView);
        inputTextView = findViewById(R.id.inputValueTextView);
        outputTextView = findViewById(R.id.outputValueTextView);
        conversionHistoryTextView = findViewById(R.id.conversionHistoryOutputTextView);
        conversionHistoryTextView.setMovementMethod(new ScrollingMovementMethod());
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
        inputString = inputField.getText().toString();
        double inputValue;
        // error handling -> if no input then do nothing
        if (inputString.equals(""))
            return;
        else
            inputValue = Double.parseDouble(inputString);

        double outputValue = inputValue * selectedConversion;

        // round input/output values
        String inputRounded = String.format(Locale.US, "%.1f", inputValue);
        outputString = String.format(Locale.US, "%.1f", outputValue);

        // update input/output fields after converting
        outputField.setText(outputString);
        inputString = "";
        inputField.setText(inputString);

        // update conversion history string
        if (selectedConversion == MILESTOKILOMETERS)
            conversions =  inputRounded + " Mi ==> " + outputString + " Km\n" + conversions;
        else
            conversions =  inputRounded + " Km ==> " + outputString + " Mi\n" + conversions;
        conversionHistoryTextView.setText(conversions);
    }


    public void clearHistory(View v) {
        conversions = "";
        conversionHistoryTextView.setText(conversions);
    }

    // overriding default save + restore state methods to save data upon change of state
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // saving current values
        outState.putString("CONVERSION HISTORY", conversions);
        outState.putString("INPUT VALUE", inputField.getText().toString());
        outState.putString("OUTPUT VALUE", outputString);

        // calling super after saving values
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        // calling super before restoring saved values
        super.onRestoreInstanceState(savedInstanceState);

        // restoring values from saved state
        conversions = savedInstanceState.getString("CONVERSION HISTORY");
        conversionHistoryTextView.setText(conversions);
        inputString = savedInstanceState.getString("INPUT VALUE");
        inputField.setText(inputString);
        outputString = savedInstanceState.getString("OUTPUT VALUE");
        outputField.setText(outputString);
    }
}