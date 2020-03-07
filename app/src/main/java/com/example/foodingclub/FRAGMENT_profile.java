package com.example.foodingclub;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FRAGMENT_profile extends Fragment {
    TextView p_name,p_num,p_address,p_email;
    Button p_update;
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rootRef =db.getReference();
    DatabaseReference idref=rootRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    String num,email,address;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_profile, container, false);
        p_name= view.findViewById(R.id.profile_name);
        p_num= view.findViewById(R.id.profile_number);
        p_address= view.findViewById(R.id.profile_address);
        p_email= view.findViewById(R.id.profile_email);
        p_update= view.findViewById(R.id.profile_update);
        getValues();
        p_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9= new Intent(getActivity(),update_profile.class);
                intent9.putExtra("name",p_name.getText());
                intent9.putExtra("num",p_num.getText());
                intent9.putExtra("address",p_address.getText());
                intent9.putExtra("email",p_email.getText());
                startActivity(intent9);
            }
        });



        return  view;
    }

    private void getValues() {
        idref.child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String name=dataSnapshot.getValue().toString();
               p_name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        idref.child("Number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                num=dataSnapshot.getValue().toString();
                p_num.setText(num);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        idref.child("Email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                email=dataSnapshot.getValue().toString();
                p_email.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        idref.child("Address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                address=dataSnapshot.getValue().toString();
                p_address.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
