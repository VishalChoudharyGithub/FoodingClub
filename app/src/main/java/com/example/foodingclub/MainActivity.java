package com.example.foodingclub;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    EditText editText;
    ProgressBar progressBar;
    Button Next;
    public FirebaseAuth mAuth;
    String  value;
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rootRef =db.getReference();



    public void verification_send_method(View view) {
        if (editText.getText().length() != 10) {
            editText.setError("Enter a vaid number");
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(50);
            ObjectAnimator animation = ObjectAnimator.ofFloat(Next, "TranslationX", 60f, -60f, 60f, -60f, 0f);
            animation.setDuration(500);
            animation.start();

        } else {
            String s = "+91" + editText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            Intent intent2 = new Intent(this, OTP_activity.class);
            intent2.putExtra("number", s);
            startActivity(intent2);
            progressBar.setVisibility(View.INVISIBLE);
            finish();


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = findViewById(R.id.view_pager);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setInAnimation(this, R.anim.right_to_left);
        viewFlipper.setOutAnimation(this, R.anim.left_to_right);
        Next = findViewById(R.id.next);
        editText = findViewById(R.id.enter_num_id);
        progressBar = findViewById(R.id.enter_num_progress);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            rootRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@Nonnull DataSnapshot dataSnapshot) {
                    value= (String) dataSnapshot.getValue();
                }

                @Override
                public void onCancelled(@Nonnull DatabaseError databaseError) {

                }
            });if(value=="0"){
                Intent intent7= new Intent(this,register1.class);
                startActivity(intent7);
                finish();

            }else{


                Intent intent8 = new Intent(this, Home.class);
                startActivity(intent8);
                finish();

        }}
    }
}
