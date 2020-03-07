package com.example.foodingclub;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class items_adapter extends FirestoreRecyclerAdapter<items, items_adapter.itemholder> {


    public items_adapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }

    @Override
    public void onBindViewHolder(@NonNull itemholder holder, int position, @NonNull items model) {
        holder.textViewTitle.setText(model.getName());
        Glide.with(holder.imageViewIcon.getContext()).load(model.getPic()).into(holder.imageViewIcon);
    }

    @NonNull
    @Override
    public itemholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout,viewGroup,false);
        return new itemholder(v);
    }


    public  class itemholder extends RecyclerView.ViewHolder{
     TextView textViewTitle;
     ImageView imageViewIcon;

        public itemholder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title_id);
            imageViewIcon = itemView.findViewById(R.id.imageicon_id);

        }
    }
}
