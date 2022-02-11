package com.mlkyh.hertz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button1;
    EditText TextBr,TextBm,TextBp,TextFm,TextFp,TextL,TextCr,TextCa,TextPmoy,TextPmax,TextDp;

    float Br,Bm,Bp,Fm,Fp,L,Estar,Cr,Ca,Pmoy,Pmax,Dp;

   // boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        TextBr = (EditText) findViewById(R.id.edtBr);
        TextBm = (EditText) findViewById(R.id.edtBm);
        TextBp = (EditText) findViewById(R.id.edtBp);
        TextFm = (EditText) findViewById(R.id.edtFm);
        TextFp = (EditText) findViewById(R.id.edtFp);
        TextL = (EditText) findViewById(R.id.edtL);
        TextCr = (EditText) findViewById(R.id.edtCR);
        TextCa = (EditText) findViewById(R.id.edtCa);
        TextPmoy = (EditText) findViewById(R.id.edtPmoy);
        TextPmax = (EditText) findViewById(R.id.edtPmax);
        TextDp = (EditText) findViewById(R.id.edtDp);

        //Preset values
        TextBr.setText(R.string.RadiusPreset);
        TextBm.setText(R.string.BallModulusPreset);
        TextBp.setText(R.string.BallPoissonPreset);
        TextFm.setText(R.string.FlatModulusPreset);
        TextFp.setText(R.string.FlatPoissonPreset);
        TextL.setText(R.string.LoadPreset);



        button1.setOnClickListener(v -> {
            Br= (float) ((Float.parseFloat(TextBr.getText() + ""))*Math.pow(10,-3));
            Bm= (float) ((Float.parseFloat(TextBm.getText() + ""))*Math.pow(10,9));
            Bp=Float.parseFloat(TextBp.getText() + "");
            Fm= (float) ((Float.parseFloat(TextFm.getText() + ""))*Math.pow(10,9));
            Fp=Float.parseFloat(TextFp.getText() + "");
            L=Float.parseFloat(TextL.getText() + "");
            Estar= (float) (1/((1-Math.pow(Bp,2))/Bm+(1-Math.pow(Fp,2))/Fm));
            Cr= (float) ((Math.pow(3*L*Br/(4*Estar),0.3333333333333333))*Math.pow(10,6));
            Ca=(float) (3.14159*Math.pow(Cr,2));
            Pmoy= (float) ((float) (L*Math.pow(10,6)) /(3.14159*Math.pow(Cr,2)));
            Pmax= (float) (1.5*Pmoy);
            Dp = (float) (Cr*Cr/(Br*1000));

            TextCr.setText(Cr + "  ");
            TextCa.setText(Ca + "  ");
            TextPmoy.setText(Pmoy + "  ");
            TextPmax.setText(Pmax + "  ");
            TextDp.setText(Dp + "  ");
        });

    }
}