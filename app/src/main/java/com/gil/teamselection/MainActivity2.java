package com.gil.teamselection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity";

   private Button toListFragment;
   FragmentManager fragmentManager = getSupportFragmentManager();
   ListOfPlayersFragment listOfPlayersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toListFragment = findViewById(R.id.toListFragment);

        toListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfPlayersFragment = new ListOfPlayersFragment();
                fragmentManager.beginTransaction().add(R.id.mainConteiner , listOfPlayersFragment).addToBackStack("").commit();
            }
        });


    }

   }
