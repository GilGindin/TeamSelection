package com.gil.teamselection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

class Myadapter extends RecyclerView.Adapter<MyHolder> {


    private Context context;
    private ArrayList<Player> adpterList;
    private ArrayList<Player> finalList;
   // HashMap<k , Player> mIdMap = new HashMap<Player, Integer>();

    public Myadapter(Context context, ArrayList<Player> adpterList) {
        this.context = context;
        this.adpterList.addAll(adpterList);
//        finalList.addAll(adpterList);



    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list , viewGroup , false);
        MyHolder holder = new MyHolder(linearLayout);
        return holder;

    }


    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        Player player = adpterList.get(position);
        myHolder.textView.setText(player.toString());


    }


    public int getItemCount() {
        return adpterList.size();
    }
}
