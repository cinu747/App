/*
Name: Cini Kolath Abraham
Lab 1
Brief program description: This code is for an Android temperature conversion application.
It accepts user input and converts temperatures between celsius and fahrenheit, changes the background
colors and shows respective images based on the temperature value entered by the user.*/

package com.example.tempconverter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    View view;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.editTextNumberDecimal3);
    }
// This method is called when the property 'OnClick' button is clicked
    public void onClick(View view) {
        if(view.getId()==R.id.button2) {
                // Radio button widgets for celsius and fahrenheit
                RadioButton celsiusButton =
                    findViewById(R.id.radioButton);
            RadioButton fahrenheitButton =
                    findViewById(R.id.radioButton2);
            // Toaster message is popped up if the input field is left empty
            if (text.getText().length() == 0) {
                Toast.makeText(this, "Please enter a valid number",
                        Toast.LENGTH_LONG).show();
                return;
            }
            // Input text is converted to floating point number
            float inputValue =
                    Float.parseFloat(text.getText().toString());
            // Check if celsius or fahrenheit was selected
            if (celsiusButton.isChecked()) {
                text.setText(String.valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                celsiusButton.setChecked(false);
                fahrenheitButton.setChecked(true);
            }
            else {
                text.setText(String.valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                fahrenheitButton.setChecked(false);
                celsiusButton.setChecked(true);
            }
            inputValue = Float.parseFloat(text.getText().toString());
            view = findViewById(R.id.activity_main);
            iv=findViewById(R.id.imageView);
            // Change the background color of the screen and displays the corresponding image icon
            if (inputValue>90) {
//set hex color to skyblue
                view.setBackgroundColor(Color.parseColor("#87ceff"));
                iv.setVisibility(View.VISIBLE);
                ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                iv.setImageResource(R.drawable.hot);
            }
            // Change the background color if the condition changes
            else if (inputValue <0){
                view.setBackgroundColor(Color.RED);
                iv.setVisibility(View.VISIBLE);
                ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                iv.setImageResource(R.drawable.cold);

            }
            else
            {
                view.setBackgroundColor(Color.YELLOW);
                iv.setVisibility(View.INVISIBLE);
                ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
            }



        }
    }
}
