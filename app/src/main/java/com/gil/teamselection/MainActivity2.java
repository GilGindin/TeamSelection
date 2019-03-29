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

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button createTeamsButton;
    FragmentManager fragmentManager = getSupportFragmentManager();
    ListOfPlayersFragment listOfPlayersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
                listOfPlayersFragment = new ListOfPlayersFragment();
                fragmentManager.beginTransaction().replace(R.id.mainConteiner, listOfPlayersFragment).addToBackStack("go to listFragment from MainActivity").commit();
                viewInvisable(v);
            }
        });
    }

    public void refresh() {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ShowingGruopsFragment showingGruopsFragment = new ShowingGruopsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", listOfPlayersFragment.getMyBigList());
        bundle.putSerializable("avarge", listOfPlayersFragment.getAvg());
        showingGruopsFragment.setArguments(bundle);
        ft.replace(android.R.id.content, showingGruopsFragment ,"SHOWING_FRAG");
        ft.addToBackStack("going to showFrag from listFrag");
        ft.commit();
    }
    
}

