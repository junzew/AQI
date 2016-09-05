package com.example.junze.aqiconverter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;
    private TextView indicator;
    private  TextView  convertedConcentration;
    private EditText inputAQI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.concentration);
        mTextView = (TextView) findViewById(R.id.aqi);
        indicator = (TextView) findViewById(R.id.indicator);
        mButton = (Button) findViewById(R.id.button_convert);
        inputAQI = (EditText) findViewById(R.id.et_aqi);
        convertedConcentration = (TextView) findViewById(R.id.tv_aqi);
        inputAQI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                handle();
            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onClickHandler();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHandler();
            }
        });
    }
    private void handle() {
        try {
            String info = inputAQI.getText().toString();
            int concentration = 0;
            if (!info.equals("")) {
                double ip = Double.parseDouble(info);
                concentration = (int) AQICalculator.calculateConcentration(ip);
            }

            convertedConcentration.setText(concentration + " μg/m^3");
        } catch (Exception e) {

        }

    }
    private void onClickHandler() {
        try {
            String t = "";
            int id = Color.WHITE;
            String info  = mEditText.getText().toString();
            int aqi = 0;
            if (!info.equals("")) {
                double cp = Double.parseDouble(info);
                aqi = (int) AQICalculator.calculateAQI(cp);

                if (cp < 0) {
                    t ="Out of range";
                    id = Color.WHITE;
                } else if (cp <= 12) {
                    t ="Good ";
                    id = R.color.good;
                } else if (cp <= 35.4) {
                    t ="Moderate";
                    id = R.color.moderate;
                } else if (cp <= 55.4) {
                    t ="Unhealthy for Sensitive Groups";
                    id = R.color.unhealthy1;
                } else if (cp <= 150.4) {
                    t ="Unhealthy";
                    id = R.color.unhealthy2;
                } else if (cp <= 250.4) {
                    t ="Very Unhealthy";
                    id = R.color.veryunhealthy;
                } else if (cp <= 350.4) {
                    t ="Hazardous";
                    id = R.color.hazardous;
                } else if (cp <= 500){
                    t ="Hazardous";
                    id = R.color.hazardous;
                } else {
                    t ="Hazardous";
                    id = R.color.hazardous;
                }
            }

            mTextView.setText(t + "\nAQI = "+String.valueOf(aqi));
            indicator.setBackgroundResource(id);
        } catch (Exception e) {
            return;
        }

    }
}

