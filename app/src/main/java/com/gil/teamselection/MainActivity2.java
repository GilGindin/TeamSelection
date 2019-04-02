package com.gil.teamselection;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static com.gil.teamselection.ListOfPlayersFragment.KEY_PLAYERS;

public class MainActivity2 extends AppCompatActivity implements AddaptionFragment.FragmentAddpationListener, ListOfPlayersFragment.FragmentListOfPlayersListener, ShowingGruopsFragment.FragmentShowListener {
    private static final String TAG = "MainActivity";

    private Button createTeamsButton;
    private ListOfPlayersFragment listOfPlayersFragment;
    private AddaptionFragment addaptionFragment;
    private ShowingGruopsFragment showingGruopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listOfPlayersFragment = new ListOfPlayersFragment();
        addaptionFragment = new AddaptionFragment();
        showingGruopsFragment = new ShowingGruopsFragment();

        createTeamsButton = findViewById(R.id.createTeamsButton);
        viewVisable(createTeamsButton);
    }

    @Override
    public void onBackPressed() {
        viewVisable(createTeamsButton);
        super.onBackPressed();
    }

    public View viewVisable(View view) {
        view.setVisibility(View.VISIBLE);
        onClick(createTeamsButton);
        return view;
    }

    public View viewInvisable(View view) {
        view.setVisibility(View.GONE);
        return view;
    }


    public void onClick(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addaptionFragment = new AddaptionFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainConteiner, addaptionFragment).addToBackStack("go to addaptionFragment from mainactivity").commit();

//                listOfPlayersFragment = new ListOfPlayersFragment();
//                fragmentManager.beginTransaction().replace(R.id.mainConteiner, listOfPlayersFragment).addToBackStack("go to listFragment from MainActivity").commit();
                viewInvisable(v);
            }
        });
    }

    public void refresh() {
//        //onInputShowListener(showingGruopsFragment.getMyBigList() , listOfPlayersFragment.getAvg());
        onInputListSent(listOfPlayersFragment.getMyBigList(), listOfPlayersFragment.getTeams(), listOfPlayersFragment.getAvg());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(R.id.mainConteiner, showingGruopsFragment);
        ft.commit();

//        ShowingGruopsFragment showingGruopsFragment = new ShowingGruopsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("key_list", listOfPlayersFragment.getMyBigList());
//        bundle.putSerializable("avarge", listOfPlayersFragment.getAvg());
//        showingGruopsFragment.setArguments(bundle);
//        ft.replace(android.R.id.content, showingGruopsFragment);
//        ft.addToBackStack("going to showFrag from listFrag");
//        ft.commit();
    }


    @Override
    public void onInputAsent(int numberOfTeams, int numberOfPlayers) {
        listOfPlayersFragment.updateArgumentsFromAddaptionFrag(numberOfTeams, numberOfPlayers);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainConteiner, listOfPlayersFragment).addToBackStack("").commit();
    }

    @Override
    public void onInputListSent(ArrayList<Player> list, int teams, double avarge) {
        showingGruopsFragment.onUpdate(list, teams, avarge);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainConteiner, showingGruopsFragment).addToBackStack("").commit();
        Log.d(TAG, "onInputListSent: in mainactivity " + list + " , " + teams + " , " + avarge);
    }

    @Override
    public void onInputShowListener(ArrayList<Player> list, double avarge) {
        listOfPlayersFragment.updateFromShowFrag(list, avarge);
    }
}

