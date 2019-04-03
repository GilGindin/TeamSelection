package com.gil.teamselection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class Myadapter extends RecyclerView.Adapter<Myadapter.MyHolder> {

    private ArrayList<Player> adpterList;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mlistener = listener;
    }

    public Myadapter( ArrayList<Player> adpterList) {
        this.adpterList = adpterList;
    }


    public static class MyHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView image_delete;

        public MyHolder(@NonNull View view, OnItemClickListener listener) {
            super(view);
            textView = view.findViewById(R.id.textItemHolder);
            image_delete = view.findViewById(R.id.image_delete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }

                }
            });
            image_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        MyHolder holder = new MyHolder(view , mlistener);
        return holder;
    }

    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {

        Player player = adpterList.get(position);
        myHolder.textView.setText(" " + player.toString());
        myHolder.image_delete.setImageResource(R.drawable.ic_delete);
    }

    public int getItemCount() {
        return adpterList.size();
    }

}


