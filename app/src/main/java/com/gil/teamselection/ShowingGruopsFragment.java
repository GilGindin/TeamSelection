package com.gil.teamselection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class ShowingGruopsFragment extends Fragment {

    private RecyclerView recyclerviewTeam1;
    private RecyclerView recyclerviewTeam2;
    private RecyclerView recyclerviewTeam3;
    Myadapter myadapter;
    private static Random rand = new Random();


    public ShowingGruopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_showing_gruops, container, false);

        recyclerviewTeam3 = v.findViewById(R.id.recyclerViewTeam3);
        recyclerviewTeam3.setHasFixedSize(true);
        recyclerviewTeam2 = v.findViewById(R.id.recyclerviewTeam2);
        recyclerviewTeam2.setHasFixedSize(true);
        recyclerviewTeam1 = v.findViewById(R.id.recyclerviewTeam1);
        recyclerviewTeam1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerviewTeam1.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerviewTeam2.setLayoutManager(layoutManager2);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        recyclerviewTeam3.setLayoutManager(layoutManager3);

        Bundle bundle = this.getArguments();
        ArrayList<Player> getArrayList = (ArrayList<Player>) getArguments().getSerializable("key");
        double avg = (double) getArguments().getSerializable("avarge");
        getArrayList.size();
        ArrayList<Player> redTeam = new ArrayList<>(2);
        ArrayList<Player> blueTeam = new ArrayList<>(2);
        ArrayList<Player> greenTeam = new ArrayList<>();

        for (int i = 0; i < getArrayList.size(); i++) {

            Collections.shuffle(getArrayList);
            Player current = getArrayList.get(i);

            for (double j = current.getNum(); j <= avg && j <= redTeam.size(); j++) {
                if (checkPlayer(current.getName(), redTeam) == false) {
                    redTeam.add(current);
                    j += current.getNum();
                } else {
                    for (int z = 0; z <= avg && z <= blueTeam.size(); z++) {
                        if (checkPlayer(current.getName(), blueTeam) == false) {

                            blueTeam.add(current);
                            z += current.getNum();
                        }
                    }

                }
            }


        }

        myadapter = new Myadapter(getContext(), redTeam);
        recyclerviewTeam1.setAdapter(myadapter);

        myadapter = new Myadapter(getContext(), blueTeam);
        recyclerviewTeam2.setAdapter(myadapter);

        return v;
    }

    private boolean checkPlayer(String name, ArrayList<Player> list) {
        for (Player o : list) {
            if (o != null && o.getName().equals(name)) {
                return true;
            }
        }
        return false;

    }

}
