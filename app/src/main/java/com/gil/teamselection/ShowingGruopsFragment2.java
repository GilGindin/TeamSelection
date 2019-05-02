package com.gil.teamselection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.gil.teamselection.ListOfPlayersFragment.KEY_PLAYERS;


public class ShowingGruopsFragment2 extends Fragment {

    private static final String TAG = "ShowingGruopsFragment";
    private RecyclerView recyclerviewTeam1;
    private double getAvg;
    private int getTeams;
    private Myadapter myadapter;
    private ArrayList<Player> getArrayList;
    private static Random rand = new Random();
    private Button refreshButton;
    private Button backFromShowinBtn;
    private View v;
    private GridView mGridLayout;

    public ShowingGruopsFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_showing_gruops, container, false);

        setButtons();

        getListToCreateLists(getArrayList, getTeams, getAvg);
        onClick(refreshButton);
        onClick(backFromShowinBtn);
        return v;
    }

    public void setButtons() {
        // recyclerviewTeam1 = v.findViewById(R.id.recyclerviewTeam1);
        mGridLayout = v.findViewById(R.id.recyclerviewTeam1);
        refreshButton = v.findViewById(R.id.refreshButton);
        backFromShowinBtn = v.findViewById(R.id.backFromShowinBtn);
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

    private boolean checkPlayerIfExists(String name, ArrayList<Player> list) {
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
        //bigList = number of Players the user insert;
        // teams = number of teams the user insert;
        // avg = the avarge of all Players.getnum() / teams;
        //object.getnum() = Player (object) int num (value);
        int z = 0;
        ArrayList<Player>[] d = (ArrayList<Player>[]) new ArrayList[teams];

        for (int i = 0; i < teams; i++) {
            d[i] = new ArrayList<Player>();
            initRecycerView(d[i]);

            if (z != 0) {
                z = 0;
            }
            Collections.shuffle(bigList);
            for (int j = 0; j < bigList.size(); j++) {
                Player current = bigList.get(j);

                for (; z <= avg; z++) {
                    if (checkPlayerIfExists(current.getName(), d[i]) == false) {
                        d[i].add(current);
                        z += current.getNum();
                        bigList.remove(current);
                        break;
                    }
                }
            }
        }
    }


    public void initRecycerView(List<Player> list) {

    }


//            if (d[i].size() != d[i].size()) {
//                checkTeamsSize(d[i]);
//            }


}
