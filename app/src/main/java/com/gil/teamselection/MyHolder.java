package com.gil.teamselection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MyHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public MyHolder(@NonNull View view) {
        super(view);
        textView = view.findViewById(R.id.textItemHolder);

    }
}
