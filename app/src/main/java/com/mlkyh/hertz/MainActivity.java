package com.mlkyh.hertz;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
        {

    EditText TextBr,TextBm,TextBp,TextFm,TextFp,TextL,TextCr,TextCa,TextPmoy,TextPmax,TextDp;
    float Br,Bm,Bp,Fm,Fp,L,Estar,Cr,Ca,Pmoy,Pmax,Dp;

   // boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                setTheme(R.style.AppTheme);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.darkTheme);
                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                setTheme(R.style.AppTheme);// We don't know what mode we're in, assume notnight
        }


        setContentView(R.layout.activity_main);
        TextBr = findViewById(R.id.edtBr);
        TextBm = findViewById(R.id.edtBm);
        TextBp = findViewById(R.id.edtBp);
        TextFm = findViewById(R.id.edtFm);
        TextFp = findViewById(R.id.edtFp);
        TextL = findViewById(R.id.edtL);
        TextCr = findViewById(R.id.edtCR);
        TextCa = findViewById(R.id.edtCa);
        TextPmoy = findViewById(R.id.edtPmoy);
        TextPmax = findViewById(R.id.edtPmax);
        TextDp = findViewById(R.id.edtDp);

        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Materials, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Materials, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        //Preset values
        TextBr.setText(R.string.RadiusPreset);
        TextL.setText(R.string.LoadPreset);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   //String state = adapterView.getItemAtPosition(i).toString();
                   Resources res = getResources(); //assuming in an activity for example, otherwise you can provide a context.
                   String[] BYoung = res.getStringArray(R.array.Young);
                   String[] BPoisson = res.getStringArray(R.array.Poisson);


                   TextBm.setText(BYoung[i]);
                   TextBp.setText(BPoisson[i]);

               }

               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {

               }
           });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String state = adapterView.getItemAtPosition(i).toString();
                Resources res = getResources(); //assuming in an activity for example, otherwise you can provide a context.
                String[] BYoung = res.getStringArray(R.array.Young);
                String[] BPoisson = res.getStringArray(R.array.Poisson);


                TextFm.setText(BYoung[i]);
                TextFp.setText(BPoisson[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        new MultiTextWatcher()
                .registerEditText(TextBr)
                .registerEditText(TextBm)
                .registerEditText(TextBp)
                .registerEditText(TextL)
                .registerEditText(TextFm)
                .registerEditText(TextFp)
                .setCallback(new MultiTextWatcher.TextWatcherWithInstance() {
                    @Override
                    public void beforeTextChanged() {
                    //no
                    }
                    @Override
                    public void onTextChanged() {

                        try {
                            Br = (float) ((Float.parseFloat(TextBr.getText() + "")) * Math.pow(10, -3));
                            Bm = (float) ((Float.parseFloat(TextBm.getText() + "")) * Math.pow(10, 9));
                            Bp = Float.parseFloat(TextBp.getText() + "");
                            Fm = (float) ((Float.parseFloat(TextFm.getText() + "")) * Math.pow(10, 9));
                            Fp = Float.parseFloat(TextFp.getText() + "");
                            L = Float.parseFloat(TextL.getText() + "");
                        } catch (Exception e) {
                            Br=0.0f;
                            Bm=0.0f;
                            Bp=0.0f;
                            Fm=0.0f;
                            Fp=0.0f;
                            L=0.0f;
                        }


                        Estar = (float) (1 / ((1 - Math.pow(Bp, 2)) / Bm + (1 - Math.pow(Fp, 2)) / Fm));
                        Cr = (float) ((Math.pow(3 * L * Br / (4 * Estar), 1.0/3.0)) * 1000000);
                        Ca = (float) (Math.PI * Math.pow(Cr, 2));
                        Pmoy = (float) ((L * 1000000) / (Math.PI * Math.pow(Cr, 2)));
                        Pmax = (float) (1.5 * Pmoy);
                        Dp = Cr * Cr / (Br * 1000000);


                            DecimalFormat dfrmt = new DecimalFormat();
                            dfrmt.setMaximumFractionDigits(3);


                            TextCr.setText(dfrmt.format(Cr));
                            TextCa.setText(dfrmt.format(Ca));
                            TextPmoy.setText(dfrmt.format(Pmoy));
                            TextPmax.setText(dfrmt.format(Pmax));
                            TextDp.setText(dfrmt.format(Dp));
                        }

                    @Override
                    public void afterTextChanged() {
                        //no
                    }
                });



    }

}