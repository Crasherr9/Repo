package com.repoimage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends  RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<DataItem> worldpopulation;
    private Context context;

    private OnItemClickListener mListener;
    public interface  OnItemClickListener{
        void onItemClick(int position);
    }
    public Adapter(Context context, List<DataItem> worldpopulation) {
        this.worldpopulation = worldpopulation;
        this.context = context;


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        DataItem currentitem=worldpopulation.get(position);
        int Rank=currentitem.getRank();
        String Country=currentitem.getCountry();
        String Population=currentitem.getPopulation();
        holder.country.setText("Country:"+Country);
        holder.population.setText("Population:"+Population);
        holder.rank.setText("Rank:"+Rank);
        Picasso.with(context).load(worldpopulation.get(position).getFlag()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return worldpopulation.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView country, population, rank;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            country = itemView.findViewById(R.id.country);
            population = itemView.findViewById(R.id.population);
            rank = itemView.findViewById(R.id.rank);
               itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(mListener!=null ){
                           int position=getAdapterPosition();
                           if(position!=RecyclerView.NO_POSITION){
                               mListener.onItemClick(position);
                           }
                       }
                   }
               });
        }
    }
}