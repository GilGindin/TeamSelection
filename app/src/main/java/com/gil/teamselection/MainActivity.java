package com.gil.teamselection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerviewTeam1;
    private RecyclerView recyclerviewTeam2;
    private RecyclerView recyclerviewTeam3;
    Myadapter myadapter;
    private ArrayList<Player> myBigList;
    String num;
    private EditText editTextName;
    private ArrayList<Player> teamAdding1;
    private ArrayList<Player> teamAdding2;
    private ArrayList<Player> teamAdding3;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerviewTeam3 = findViewById(R.id.recyclerViewTeam3);
        recyclerviewTeam3.setHasFixedSize(true);
        recyclerviewTeam2 = findViewById(R.id.recyclerviewTeam2);
        recyclerviewTeam2.setHasFixedSize(true);
        recyclerviewTeam1 = findViewById(R.id.recyclerviewTeam1);
        recyclerviewTeam1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerviewTeam1.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerviewTeam2.setLayoutManager(layoutManager2);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(this);
        recyclerviewTeam3.setLayoutManager(layoutManager3);

        final Button btn1 = (Button) findViewById(R.id.btnRating);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowDialog();
            }
        });

        editTextName = findViewById(R.id.editTextName);
        myBigList = new ArrayList<Player>();
        teamAdding1 = new ArrayList<>();
        teamAdding2 = new ArrayList<>();
        teamAdding3 = new ArrayList<>();
    }

    public void ShowDialog() {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        LinearLayout linearLayout = new LinearLayout(this);
        final RatingBar rating = new RatingBar(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rating.setLayoutParams(lp);
        rating.setNumStars(5);
        rating.setStepSize((float) 0.5);

        //add ratingBar to linearLayout
        linearLayout.addView(rating);

        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Add Rating: ");

        //add linearLayout to dailog
        popDialog.setView(linearLayout);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
              num = String.valueOf(ratingBar.getRating());
            }
        });
        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                // Button Cancel
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();
    }

    public void addButton_onClick(View view) {

        String name = editTextName.getText().toString().trim();

        Player p = new Player(name, num, false);
        if (myBigList.size() < 15) {
            List<Player> all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num) && x.isUsed == false).collect(Collectors.toList());

            if (all1Plyers.size() < 3) {
                myBigList.add(p);
            } else {
                Toast.makeText(this, "You already have 3 players of " + p.getNum() + " lvl", Toast.LENGTH_LONG).show();
            }
        }

        editTextName.setText("");
        teamSelection();
    }

    public void teamSelection() {
        //split 15 to 3 groups
        //equal strength

        int index = 0;
        int strength = 1;
        myadapter = new Myadapter(this, myBigList);
        //  Player cuurent =  null;
        myBigList.get(index);
        while (strength < 6) {


            final int innerStrength = strength;
            //fill first list
            List<Player> all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num) && (x.isUsed == false)).collect(Collectors.toList());

            if (all1Plyers.get(index).isUsed == false && all1Plyers != null) {

                teamAdding1.add(all1Plyers.get(index));
                all1Plyers.get(index).isUsed = true;
                myadapter = new Myadapter(this, teamAdding1);
                recyclerviewTeam1.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();
                //  }
            }

            //fill second list
            all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num) && x.isUsed == false).collect(Collectors.toList());

            if ( all1Plyers != null) {

                if (all1Plyers.get(index).isUsed == false) {
                    teamAdding2.add(all1Plyers.get(index));
                    all1Plyers.get(index).isUsed = true;
                    myadapter = new Myadapter(this, teamAdding2);
                    recyclerviewTeam2.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
               }
            }
            //fill third list
            all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num) && (x.isUsed == false)).collect(Collectors.toList());

            if (all1Plyers.get(index).isUsed == false && all1Plyers != null) {

            //    if (all1Plyers.get(index).isUsed == false) {
//
                    teamAdding3.add(all1Plyers.get(index));
                    all1Plyers.get(index).isUsed = true;
                    myadapter = new Myadapter(this, teamAdding3);
                    recyclerviewTeam3.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
              //  }
            }
            strength++;

        }

    }
    private static boolean isIndexOutOfBounds(final List<Player> list, int index) {
        return index < 0 || index >= list.size();
    }

   }
