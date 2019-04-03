package com.gil.teamselection;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class ShowingGruopsFragment extends Fragment {

    private static final String TAG = "ShowingGruopsFragment";
    private FragmentShowListener listener;
    private RecyclerView recyclerviewTeam1;
    private RecyclerView recyclerviewTeam2;
    private RecyclerView recyclerviewTeam3;
    private double getAvg;
    private int getTeams;
    private Myadapter myadapter;
    private ArrayList<Player> getArrayList;
    private static Random rand = new Random();
    private Button refreshButton;
    private Button backFromShowinBtn;

    public interface FragmentShowListener {
        void onInputShowListener(ArrayList<Player> list, double avarge);
    }

    public ShowingGruopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_showing_gruops, container, false);

        refreshButton = v.findViewById(R.id.refreshButton);
        backFromShowinBtn = v.findViewById(R.id.backFromShowinBtn);
//        recyclerviewTeam3 = v.findViewById(R.id.recyclerViewTeam3);
//        recyclerviewTeam3.setHasFixedSize(true);
//        recyclerviewTeam2 = v.findViewById(R.id.recyclerviewTeam2);
//        recyclerviewTeam2.setHasFixedSize(true);
        recyclerviewTeam1 = v.findViewById(R.id.recyclerviewTeam1);
//        recyclerviewTeam1.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerviewTeam1.setLayoutManager(layoutManager);
//        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
//        recyclerviewTeam2.setLayoutManager(layoutManager2);
//        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext());
//        recyclerviewTeam3.setLayoutManager(layoutManager3);

//        Bundle bundle = this.getArguments();
        //   getArrayList = (ArrayList<Player>) getArguments().getSerializable();

//        int teams = bundle.getInt("number_of_teams" , 0);
//        double avg = bundle.getDouble("avg" , 0);

        getListToCreateLists(getArrayList, getTeams, getAvg);
        onClick(refreshButton);
        onClick(backFromShowinBtn);
        return v;
    }

    public void onUpdate(ArrayList<Player> list, int teams, double avarge) {

        if (list.size() != 0 && teams != 0 && avarge != 0) {
            getArrayList = list;
            getArrayList = (ArrayList<Player>) getArrayList.clone();
            getTeams = teams;
            getAvg = avarge;
            Log.d(TAG, "onUpdate: " + getArrayList.size() + " , number of teams " + getTeams + " , avarge is " + getAvg);
        }
    }

    private boolean checkPlayerIfExists(String name, Collection<Player> list) {
        for (Player o : list) {
            if (o != null && o.getName().equals(name)) {
                return true;
            }
        }
        return false;

    }

    public void onClick(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.backFromShowinBtn:
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack();
                        break;
                    case R.id.refreshButton:
                        ((MainActivity2) getActivity()).refresh();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void getListToCreateLists(ArrayList<Player> bigList, int teams, double avg) {

        Log.d(TAG, "GetArrayList: " + getArrayList.size() + ",--------- avg " + avg + ", ------ teams " + teams);

        ArrayList<Player>[] d = (ArrayList<Player>[]) new ArrayList[teams];
        for (int i = 0; i < teams; i++) {
            d[i] = new ArrayList<Player>();
            Log.d(TAG, String.format("getListToCreateLists: d=----" + d.length));

        }
        ArrayList<Player> redTeam = new ArrayList<>(5);
        ArrayList<Player> blueTeam = new ArrayList<>(5);
        avg = 16;
        double j = 0;
        double z = 0;

        Collections.shuffle(bigList);
        for (int i = 0; i < bigList.size(); i++) {

            Player current = bigList.get(i);
            Log.d(TAG, "CurrentPLAYER: --------------" + current);

            for (; j <= avg; j++) {
                if (checkPlayerIfExists(current.getName(), redTeam) == false) {
                    redTeam.add(current);
                    j += current.getNum();
                    Log.d(TAG, "RED team: " + checkPlayerIfExists(current.getName(), redTeam) + " PLAYER NAME " + current.getName() + " PLAYER NUM " + current.getNum());
                    bigList.remove(i);
                    break;
                }
            }
        }
        Log.d(TAG, "check list size: " + bigList.size());
        for (int i = 0; i < bigList.size(); i++) {
            Player current = bigList.get(i);
            for (; z <= avg; z++) {
                if (checkPlayerIfExists(current.getName(), blueTeam) == false) {
                    blueTeam.add(current);
                    z += current.getNum();
                    Log.d(TAG, "BLUE team: " + checkPlayerIfExists(current.getName(), blueTeam) + " PLAYER NAME " + current.getName() + " PLAYER NUM " + current.getNum());
                    // bigList.remove(i);
                    break;
                }
            }
            Log.d(TAG, "J count: " + j + "-" + " AVG: " + avg);
            Log.d(TAG, "Z count: " + z + "--" + " AVG: " + avg);
        }

        initRecycerView(redTeam);
        initRecycerView(blueTeam);
//        myadapter = new Myadapter(getContext(), redTeam);
//        recyclerviewTeam1.setAdapter(myadapter);
//
//        myadapter = new Myadapter(getContext(), blueTeam);
//        recyclerviewTeam2.setAdapter(myadapter);
    }

    public void initRecycerView(ArrayList<Player> list) {
        myadapter = new Myadapter(list);
        recyclerviewTeam1.setAdapter(myadapter);
        recyclerviewTeam1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewTeam1.setHasFixedSize(true);
        myadapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentShowListener) {
            listener = (FragmentShowListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentShowListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
