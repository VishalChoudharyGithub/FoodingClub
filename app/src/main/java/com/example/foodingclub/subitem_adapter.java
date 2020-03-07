package com.example.foodingclub;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class subitem_adapter extends RecyclerView.Adapter<subitem_adapter.subholder> {
   subitems subitem;
   int length;

    public subitem_adapter(subitems subitem, int length) {
        this.subitem = subitem;
        this.length = length;
    }

    @NonNull
    @Override
    public subholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_item_layout,viewGroup,false);
        return new subholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull subholder subholder, int i) {
        String[] cateNames= subitem.getCatNames();
        String[] cateRates= subitem.getCatRates();

        subholder.itemRate.setText(cateRates[i]);
        Glide.with(subholder.itemPic.getContext()).load(R.drawable.wings).into(subholder.itemPic);

    }

    @Override
    public int getItemCount() {
        return length;
    }

    public class subholder extends RecyclerView.ViewHolder{
        ImageView itemPic;
        TextView Rate,itemRate;
        Switch favBtn,cartBtn;


        public subholder(@NonNull View itemView) {
            super(itemView);
            itemPic=itemView.findViewById(R.id.item_pic);
            Rate=itemView.findViewById(R.id.rate);
            itemRate=itemView.findViewById(R.id.item_rate);
        }
    }
}
