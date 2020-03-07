package com.example.foodingclub;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Favourite extends AppCompatActivity {
    Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        mtoolbar=findViewById(R.id.toolbar_id_fav);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_id);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return true;
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_id :
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.create();
                builder.setCancelable(true);
                builder.setTitle("Created for UIIT");
                builder.setMessage("Coded by Vishal,Yash\nManaged by Varun\nUI designing Shivant, sachin");
                builder.show();
                break;
            case R.id.logout_id:
                Intent intent1= new Intent(this,MainActivity.class);
                startActivity(intent1);
                finish();
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(Favourite.this);
                finish();
                break;



        }
        return super.onOptionsItemSelected(item);
    }
}
