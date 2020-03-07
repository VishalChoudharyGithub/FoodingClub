package com.example.foodingclub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    BottomNavigationView  bottomNavigationView;
    Toolbar mtoolbar;
    int backButtonCount=1;
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    FRAGMENT_home fragment_home = new FRAGMENT_home();
    Fragment selectedFragment=fragment_home;
    FRAGMENT_profile fragment_profile= new FRAGMENT_profile();
    FRAGMENT_cart fragment_cart= new FRAGMENT_cart();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.nav_bar);
        mtoolbar=findViewById(R.id.toolbar_id);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new FRAGMENT_home()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);

    }
private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.home_menu:
                        selectedFragment= fragment_home;
                        break;
                    case R.id.Cart_id:
                        selectedFragment= fragment_cart;
                        break;
                    case  R.id.profile_id:
                        selectedFragment= fragment_profile;
                        break;
                }{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,selectedFragment).commit();
                }
                return true;
            }
        };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
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
                builder.setMessage("Coded by Vishal , Yash\nManaged by Varun\nUI designing Shivant, sachin");
                builder.show();
                break;
            case R.id.fav_id:
                Intent intent= new Intent(this,Favourite.class);
                startActivity(intent);
                break;
            case R.id.logout_id:
                mauth.signOut();
                mauth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if(FirebaseAuth.getInstance().getCurrentUser()==null){
                            Intent intent5= new Intent(Home.this, MainActivity.class);
                            startActivity(intent5);

                        }
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        if(selectedFragment==fragment_home){
            if(backButtonCount==1){
                Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
                backButtonCount--;
            }else{
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                backButtonCount=1;
                startActivity(intent);

            }

        }

        if(selectedFragment!= fragment_home){
            selectedFragment=fragment_home;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,selectedFragment).commit();
            bottomNavigationView.setSelectedItemId(R.id.home_menu);
            backButtonCount=1;

        }



    }

}

