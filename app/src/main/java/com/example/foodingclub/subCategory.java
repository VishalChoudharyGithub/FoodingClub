package com.example.foodingclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class subCategory extends AppCompatActivity {
    RecyclerView recyclerView;
    subitem_adapter adapter;
    ImageView mainpicbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        recyclerView=findViewById(R.id.subrecycler);
        mainpicbox=findViewById(R.id.category_pic1);
        String mainpic= getIntent().getStringExtra("mainPic");

        String[] catNames=getIntent().getStringArrayExtra("cateNames");
        String[] catPics= getIntent().getStringArrayExtra("catePics");
        String[] catRates= getIntent().getStringArrayExtra("cateRates");
        Glide.with(this).load(mainpic).centerCrop().into(mainpicbox);

       adapter= new subitem_adapter(new subitems(catNames,catPics,catRates),catNames.length);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
}
