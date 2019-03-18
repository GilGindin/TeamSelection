package com.gil.teamselection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

class Myadapter extends RecyclerView.Adapter<Myadapter.MyHolder> {

    private Context context;
    private ArrayList<Player> adpterList;

    public Myadapter(Context context, ArrayList<Player> adpterList) {
        this.context = context;
        this.adpterList = adpterList;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        MyHolder holder = new MyHolder(relativeLayout);
        return holder;
    }

    public int getItemCount() {
        return adpterList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.textItemHolder);

        }
    }

    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        Player player = adpterList.get(position);
        myHolder.textView.setText(" " + player.toString());


    }
}


