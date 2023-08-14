package com.abhidev.heathub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button TempConvert;
    TextView TempChoose, ResultTemp;
    String itemFrom, itemTo;
    ArrayList<String> arrayListFrom, arrayListTo;
    Spinner spinnerFrom, spinnerTo;
    ArrayAdapter<String> adapterTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.fromTemp);
        spinnerTo = findViewById(R.id.toTemp);

        // initialize animations
        Animation bottom2 = AnimationUtils.loadAnimation(this, R.anim.bottom_animation2);
        Animation bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //setting bottom animation
        LinearLayout linearLayout1 = findViewById(R.id.mainlinearlayout);
        linearLayout1.setAnimation(bottom);

        //setting bottom2 animation
        LinearLayout linearLayout2 = findViewById(R.id.linearLayoutmain);
        linearLayout2.setAnimation(bottom2);

        // Spinner from
        arrayListFrom = new ArrayList<>();
        arrayListFrom.add("Celsius");
        arrayListFrom.add("Fahrenheit");
        arrayListFrom.add("Kelvin");
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListFrom);
        adapterFrom.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerFrom.setAdapter(adapterFrom);

        // Initialize arrayListTo and adapterTo
        arrayListTo = new ArrayList<>();
        adapterTo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListTo);
        adapterTo.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerTo.setAdapter(adapterTo);

        // Spinner from selection listener
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                itemFrom = spinnerFrom.getSelectedItem().toString();
                // Update arrayListTo based on the new selection
                updateArrayListTo();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Spinner to selection listener
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                itemTo = spinnerTo.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Temperature conversion logic setup
        TempConvert = findViewById(R.id.convertTemp);
        TempChoose = findViewById(R.id.chooseTemp);
        ResultTemp = findViewById(R.id.resultTemp);

        // TempConvert button setup
        TempConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double inputTemperature = Double.parseDouble(TempChoose.getText().toString());

                    // Conversion logic
                    double convertedTemperature;

                    if (itemFrom.equals("Celsius")) {
                        if (itemTo.equals("Fahrenheit")) {
                            convertedTemperature = (inputTemperature * 9 / 5) + 32;
                        } else if (itemTo.equals("Kelvin")) {
                            convertedTemperature = inputTemperature + 273.15;
                        } else {
                            convertedTemperature = inputTemperature; // No conversion needed
                        }
                    } else if (itemFrom.equals("Fahrenheit")) {
                        if (itemTo.equals("Celsius")) {
                            convertedTemperature = (inputTemperature - 32) * 5 / 9;
                        } else if (itemTo.equals("Kelvin")) {
                            convertedTemperature = (inputTemperature - 32) * 5 / 9 + 273.15;
                        } else {
                            convertedTemperature = inputTemperature; // No conversion needed
                        }
                    } else if (itemFrom.equals("Kelvin")) {
                        if (itemTo.equals("Celsius")) {
                            convertedTemperature = inputTemperature - 273.15;
                        } else if (itemTo.equals("Fahrenheit")) {
                            convertedTemperature = (inputTemperature - 273.15) * 9 / 5 + 32;
                        } else {
                            convertedTemperature = inputTemperature; // No conversion needed
                        }
                    } else {
                        convertedTemperature = inputTemperature; // No conversion needed
                    }

                    //make ResultTemp visible
                    ResultTemp.setVisibility(View.VISIBLE);
                    // Display the result
                    ResultTemp.setText(String.valueOf(convertedTemperature));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter a valid temperature", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Update arrayListTo based on the selected unit
    private void updateArrayListTo() {
        arrayListTo.clear(); // Clear previous items
        if (itemFrom.equals("Celsius")) {
            arrayListTo.add("Fahrenheit");
            arrayListTo.add("Kelvin");
        } else if (itemFrom.equals("Fahrenheit")) {
            arrayListTo.add("Celsius");
            arrayListTo.add("Kelvin");
        } else if (itemFrom.equals("Kelvin")) {
            arrayListTo.add("Celsius");
            arrayListTo.add("Fahrenheit");
        }
        adapterTo.notifyDataSetChanged(); // Notify the adapter that the data has changed
    }
}
