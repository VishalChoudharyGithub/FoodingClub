package com.example.foodingclub;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class update_profile extends AppCompatActivity {
    Toolbar mtoolbar;
    String Name,Email,Num,Address;
    EditText namebox,emailbox,addressbox,phonebox;
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rootRef= db.getReference();
    DatabaseReference idref=rootRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        mtoolbar=findViewById(R.id.toolbar_id_fav1);
        namebox= findViewById(R.id.Edit_Text_Name);
        phonebox= findViewById(R.id.Edit_Text_Password);
        emailbox= findViewById(R.id.Edit_Text_Email);
        addressbox= findViewById(R.id.Edit_text_Address);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_id);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Name= getIntent().getStringExtra("name");
        Num= getIntent().getStringExtra("num");
        Address= getIntent().getStringExtra("address");
        Email= getIntent().getStringExtra("email");
        namebox.setText(Name);
        phonebox.setText(Num);
        emailbox.setText(Email);
        addressbox.setText(Address);




    }

    public void updateIt(View view) {
        idref.child("Name").setValue(namebox.getText().toString());
        idref.child("Email").setValue(emailbox.getText().toString());
        idref.child("Number").setValue(phonebox.getText().toString());
        idref.child("Address").setValue(addressbox.getText().toString());
        Toasty.success(this,"Updated Successfully",Toasty.LENGTH_SHORT).show();
        Intent intent= new Intent(this,Home.class);
        startActivity(intent);
        finish();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.home_menu){
            NavUtils.navigateUpFromSameTask(update_profile.this);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
