package com.example.foodingclub;

;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Sign_in extends AppCompatActivity {
    EditText e2;
    Button sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        e2=findViewById(R.id.Password_Id);
        sign_in=findViewById(R.id.Sign_in_SignActivity_Id);

    }

    public void final_sign_in(View view) {
        if(e2.getText().length()<1){
            Vibrator v= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(50);
            ObjectAnimator animation = ObjectAnimator.ofFloat(sign_in,"TranslationX",60f,-60f,60f,-60f,0f);
            animation.setDuration(500);
            animation.start();
        }
        else {
            Toasty.success(this, "Sign In Successful", Toast.LENGTH_SHORT, true).show();
        }

    }
}


