package com.example.foodingclub;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class register1 extends AppCompatActivity {
    EditText Name2,Email2,Address2,Number2;
    Button sign_up;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rootRef =db.getReference();
    DatabaseReference idref=rootRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
         Name2=findViewById(R.id.Edit_Text_Name);
         Email2=findViewById(R.id.Edit_Text_Email);
         Number2=findViewById(R.id.Edit_Text_Password);
         Address2=findViewById(R.id.Edit_text_Address);
         sign_up=findViewById(R.id.Button_Sign_Up_registeractivity);
         idref.child("Name");
         idref.child("Number");
         idref.child("Email");
         idref.child("Address");

    }

    public void final_signUp(View view) {
        String properEmail=Email2.getText().toString().trim();
        if(Name2.getText().length()<1||properEmail.matches(emailPattern)==false
                ||Number2.getText().length()!=10||Address2.getText().length()<1)
        {   Vibrator v= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(50);
            ObjectAnimator animation = ObjectAnimator.ofFloat(sign_up,"TranslationX",60f,-60f,60f,-60f,0f);
            animation.setDuration(500);
            animation.start();
            Toasty.warning(register1.this,"Information Incomplete", Toast.LENGTH_SHORT).show();
        }else{
        Toasty.success(register1.this,"Success", Toast.LENGTH_SHORT).show();
        String name= Name2.getText().toString().trim();
        String email= Email2.getText().toString().trim();
        String number= Number2.getText().toString().trim();
        String address= Address2.getText().toString().trim();


            idref.child("Name").setValue(name);
            idref.child("Email").setValue(email);
            idref.child("Number").setValue(number);
           idref.child("Address").setValue(address);

            Intent intent6= new Intent(this,Home.class);
            intent6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent6);
        }
    }
}
