package com.example.foodingclub;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class OTP_activity extends AppCompatActivity {
    private String VerificationId;
    EditText enterOtp;
    ProgressBar otpProgress;
    Button SignIn_btn,change_btn,resend_btn;
    private FirebaseAuth mAuth;
    String number1;
    TextView text;
    int c= 30;
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rootRef =db.getReference();


    public  void getOTP(){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number1, 30, TimeUnit.SECONDS, OTP_activity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otpProgress.setVisibility(View.VISIBLE);
                        VerificationId=s;


                        text.setVisibility(View.VISIBLE);
                        new CountDownTimer(30000,1000){
                            @Override
                            public void onTick(long millisUntilFinished) {
                               c=c-1;
                                text.setText("We sent a One Time Password valid\n Only for "+c+" seconds ");
                            }

                            @Override
                            public void onFinish() {
                                c=15;
                                otpProgress.setVisibility(View.INVISIBLE);
                                text.setVisibility(View.INVISIBLE);
                                SignIn_btn.setEnabled(false);
                                SignIn_btn.setVisibility(View.INVISIBLE);
                                change_btn.setVisibility(View.VISIBLE);
                                change_btn.setEnabled(true);
                                resend_btn.setVisibility(View.VISIBLE);
                                resend_btn.setEnabled(true);
                            }
                        }.start();
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    String code=phoneAuthCredential.getSmsCode();
                    enterOtp.setText(code);
                    otpProgress.setVisibility(View.INVISIBLE);
                    if(code!=null){
                        SignIn_btn.setVisibility(View.INVISIBLE);
                        VerifyCode(code);}
                    SignIn_btn.setText("VERIFY");
                    SignIn_btn.setTextColor(Color.WHITE);

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        enterOtp.setVisibility(View.INVISIBLE);
                        SignIn_btn.setEnabled(false);
                        SignIn_btn.setVisibility(View.INVISIBLE);
                        change_btn.setVisibility(View.VISIBLE);
                        change_btn.setEnabled(true);
                        resend_btn.setVisibility(View.VISIBLE);
                        resend_btn.setEnabled(true);

                    }
                }
        );
    }
    public  void VerifyCode(String code){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(VerificationId,code);
        sign_in_credential(credential);
    }

    private void sign_in_credential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    boolean isNew=task.getResult().getAdditionalUserInfo().isNewUser();
                    if(isNew){
                        rootRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Name").setValue("0");
                        Intent intent4 = new Intent(OTP_activity.this,register1.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent4);

                    }else{
                    Intent intent4 = new Intent(OTP_activity.this,Home.class);
                    intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent4);
                }}else{
                    Toasty.error(OTP_activity.this,"Incorrect OTP", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    public void Change_num_click(View view){
        Intent intent3= new Intent(this, MainActivity.class);
        startActivity(intent3);
        finish();
    }
    public  void Resend_btn_click(final View view){
        enterOtp.setVisibility(View.VISIBLE);
         change_btn.setEnabled(false);
         text.setVisibility(View.VISIBLE);
         change_btn.setVisibility(View.INVISIBLE);
        SignIn_btn.setEnabled(true);
        SignIn_btn.setVisibility(View.VISIBLE);
        resend_btn.setVisibility(View.INVISIBLE);
        resend_btn.setEnabled(false);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number1, 30, TimeUnit.SECONDS, OTP_activity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otpProgress.setVisibility(View.VISIBLE);
                        VerificationId=s;

                        text.setVisibility(View.VISIBLE);
                        new CountDownTimer(30000,1000){
                            @Override
                            public void onTick(long millisUntilFinished) {
                                c=c-1;
                                text.setText("We sent a One Time Password valid\n Only for "+c+" seconds ");
                            }

                            @Override
                            public void onFinish() {
                                c=30;
                                otpProgress.setVisibility(View.INVISIBLE);
                                text.setVisibility(View.INVISIBLE);
                                SignIn_btn.setEnabled(false);
                                SignIn_btn.setVisibility(View.INVISIBLE);
                                change_btn.setVisibility(View.VISIBLE);
                                change_btn.setEnabled(true);
                                resend_btn.setVisibility(View.VISIBLE);
                                resend_btn.setEnabled(true);
                            }
                        }.start();
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        String code=phoneAuthCredential.getSmsCode();
                        enterOtp.setText(code);
                        otpProgress.setVisibility(View.INVISIBLE);
                        if(code!=null)
                        {SignIn_btn.setVisibility(View.INVISIBLE);
                            VerifyCode(code);}
                        SignIn_btn.setText("VERIFY CODE");
                        SignIn_btn.setTextColor(Color.parseColor("#ffffff"));

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        SignIn_btn.setEnabled(false);
                        SignIn_btn.setVisibility(View.INVISIBLE);
                        change_btn.setVisibility(View.VISIBLE);
                        change_btn.setEnabled(true);
                        resend_btn.setVisibility(View.VISIBLE);
                        resend_btn.setEnabled(true);
                    }
                }
        );}
        public  void sign_in_listner(View view){
        String code = enterOtp.getText().toString();
        if (code.length()!=6){
            enterOtp.setError("Enter Code");
            Vibrator v= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(50);
            ObjectAnimator animation = ObjectAnimator.ofFloat(SignIn_btn,"TranslationX",60f,-60f,60f,-60f,0f);
            animation.setDuration(500);
            animation.start();
        }else{
        VerifyCode(code);
        }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_activity);
        enterOtp= findViewById(R.id.enter_otp);
        otpProgress= findViewById(R.id.Otp_progress);
       SignIn_btn= findViewById(R.id.Sign_in);
       change_btn= findViewById(R.id.change_num_id);
       resend_btn= findViewById(R.id.Resend_code_id);
       text= findViewById(R.id.time_TextView);
        mAuth = FirebaseAuth.getInstance();
        number1= getIntent().getStringExtra("number");
        getOTP();

    }
}
