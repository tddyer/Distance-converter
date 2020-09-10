package com.example.distanceconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;
    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputValueTextView);
        outputTextView = findViewById(R.id.outputValueTextView);
    }

    public void radioSelected(View v) {
        switch (v.getId()) {
            case R.id.radioMiletoKm:
                inputTextView.setText(R.string.milesTextView);
                outputTextView.setText(R.string.kilometersTextView);
                break;
            case R.id.radioKmToMile:
                inputTextView.setText(R.string.kilometersTextView);
                outputTextView.setText(R.string.milesTextView);
                break;
        }
    }
}