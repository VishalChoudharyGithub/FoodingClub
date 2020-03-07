package com.example.foodingclub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FRAGMENT_home extends Fragment {
    RecyclerView recyclerView;
    ProgressBar pb;
    public FirebaseFirestore db=FirebaseFirestore.getInstance();
    public items_adapter1 adapter;
    List<items> listitems;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_home, container, false);
        recyclerView=  view.findViewById(R.id.recyclerView1);
        pb=view.findViewById(R.id.item_load_progress);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        listitems= new ArrayList<>();
        adapter= new items_adapter1(listitems);
        recyclerView.setAdapter(adapter);
        db.collection("Categories").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                pb.setVisibility(View.GONE);
            if(!queryDocumentSnapshots.isEmpty()){
                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list){
                    items item = d.toObject(items.class);
                    listitems.add(item);
                }
                adapter.notifyDataSetChanged();
            }
            }
        });


        return view;
    }

}
