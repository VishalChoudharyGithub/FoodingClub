package com.example.foodingclub;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

public class items_adapter1 extends RecyclerView.Adapter<items_adapter1.dataholder> {

    private List<items> listitems;
    Context context;

    public items_adapter1(List<items> listitems) {

        this.listitems = listitems;
    }

    @NonNull
    @Override
    public dataholder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout,viewGroup,false);

        return new dataholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dataholder holder,final int i) {
        items item= listitems.get(i);
        holder.title.setText(item.getName());
        Glide.with(holder.icon.getContext()).load(item.getPic()).into(holder.icon);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items item= listitems.get(i);
                Intent intent= new Intent(v.getContext(),subCategory.class);
                String[] catenames=  new String[item.getSubCategoryNames().size()];

                String[] catepics=  new String[item.getSubCategoryPics().size()];
                String[] caterates=  new String[item.getSubCategoryRates().size()];
                for(int x =0;x<item.getSubCategoryNames().size();x++)
                {
                    catenames[x]=item.getSubCategoryNames().get(x);
                    catepics[x]=item.getSubCategoryPics().get(x);
                    caterates[x]=item.getSubCategoryRates().get(x);
                }
                intent.putExtra("mainPic",item.getPic());
                intent.putExtra("cateNames",catenames);
                intent.putExtra("catePics",catepics);
                intent.putExtra("cateRates",caterates);
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class dataholder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;
        CardView card;

        public dataholder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title_id);
            icon= itemView.findViewById(R.id.imageicon_id);
            card= itemView.findViewById(R.id.maincard);
        }
    }

}
