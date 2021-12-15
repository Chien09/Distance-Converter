package com.example.distanceconverter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //TextViews for Miles or KM
    private TextView FirstTextView;
    private TextView SecondTextView;

    //User Input Field TextView (Number decimals)
    private TextView InputField;

    //Output Field TextView
    private TextView OutputField;

    //Used to detect whether it is Miles to KM or KM to Miles
    private View ConverterMode;

    //TextView for Converting Histories
    private TextView History;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the reference or bind screen widgets (Including Text View and other objects)
        FirstTextView = findViewById(R.id.textView1);
        SecondTextView = findViewById(R.id.textView2);
        InputField = findViewById(R.id.InputValue);
        OutputField = findViewById(R.id.OutputValue);
        History = findViewById(R.id.ConversionHistory);

        //Allow TextView to be scrollable
        History.setMovementMethod(new ScrollingMovementMethod());

        //set the default of RadioGroup selected when first starting up the activity/application
        ConverterMode = findViewById(R.id.MilestoKM);
    }


    //RadioGroup & RadioButton
    public void RadioButtonClicked(View v){

        //used to determine mode in ConvertButtonCLicked()
        ConverterMode = v;

        switch(v.getId()){
            case R.id.MilestoKM:
                FirstTextView.setText("Miles Value:");
                SecondTextView.setText("Kilometers Value:");
                break;
            case R.id.KMtoMiles:
                FirstTextView.setText("Kilometers Value:");
                SecondTextView.setText("Miles Value:");
                break;
        }
    }

    //Convert Button
    public void ConvertButtonClicked(View v){
        String UserInput = InputField.getText().toString(); //get user input
        double Result;

        String TmpString;
        String HistoryText = History.getText().toString();

        if(ConverterMode.getId() == R.id.MilestoKM){
            if(UserInput.isEmpty()){ //Check
                Result = 1 * 1.60934; //Default 1 as Miles if User didn't input value

                TmpString = String.format("Mi to KM: 1 ==> %.1f", Result); //for conversion history
            }
            else {
                Result = Double.parseDouble(UserInput);  //convert from string to double
                Result = Result * 1.60934; //converting Miles to KM

                TmpString = String.format("Mi to KM: %s ==> %.1f", UserInput, Result); //for conversion history
            }
        }
        else{ //ConverterMode.getId() == R.id.KMtoMiles
            if(UserInput.isEmpty()){ //Check
                Result = 1 * 0.621371; //Default 1 as KM if User didn't input value

                TmpString = String.format("KM to Mi: 1 ==> %.1f", Result); //for conversion history
            }
            else {
                Result = Double.parseDouble(UserInput);  //convert from string to double
                Result = Result * 0.621371; //converting KM to Miles

                TmpString = String.format("KM to Mi: %s ==> %.1f", UserInput, Result); //for conversion history
            }
        }

        InputField.setText(""); //clearing the User input field
        OutputField.setText(String.format("%.1f", Result)); //display results to TextView rounding to 1 decimal places
        History.setText(String.format("%s\n%s", TmpString, HistoryText)); //update the Conversion History
    }


    //Clear Button
    public void ClearButtonClicked(View v){
        History.setText("");
    }


    //Adding Bundle so when screen rotated TextView Contents will appear
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("FirstTextView", FirstTextView.getText().toString());
        outState.putString("SecondTextView", SecondTextView.getText().toString());
        outState.putString("OutputView", OutputField.getText().toString());
        outState.putString("HISTORY", History.getText().toString());

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        FirstTextView.setText(savedInstanceState.getString("FirstTextView"));
        SecondTextView.setText(savedInstanceState.getString("SecondTextView"));
        OutputField.setText(savedInstanceState.getString("OutputView"));
        History.setText(savedInstanceState.getString("HISTORY"));

    }
}