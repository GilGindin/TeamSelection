package com.gil.teamselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

        Spinner spinner = findViewById(R.id.numberSpinner);
        ArrayAdapter<CharSequence> spinnerAdpater = ArrayAdapter.createFromResource(this, R.array.number_array, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdpater);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num = String.valueOf(Integer.parseInt(parent.getItemAtPosition(position).toString()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        editTextName = findViewById(R.id.editTextName);

        myBigList = new ArrayList<Player>();
        teamAdding1 = new ArrayList<>();
        teamAdding2 = new ArrayList<>();
        teamAdding3 = new ArrayList<>();
    }

    public void addButton_onClick(View view) {

        String name = editTextName.getText().toString().trim();

        Player p = new Player(name, num, false);
        if (myBigList.size() < 15) {
            List<Player> all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num)  && x.isUsed == false).collect(Collectors.toList());

            if (all1Plyers.size() < 3) {
                myBigList.add(p);
            } else {
                Toast.makeText(this, "You already have 3 players of " + p.getNum() + " lvl", Toast.LENGTH_LONG).show();
            }
        }

        editTextName.setText("");
        teamSelection();

    }

    public void teamSelection(){
        //split 15 to 3 groups
        //equal strength

        int index = 0;
        int strength = 1;
        myadapter = new Myadapter(this, myBigList );
      //  Player cuurent =  null;
                myBigList.get(index);
        while (strength < 6) {


            final int innerStrength = strength;
            //fill first list
            List<Player> all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num) && x.isUsed == false).collect(Collectors.toList());

         if (all1Plyers != null) {
       //  cuurent = all1Plyers.get(index);
               // if (all1Plyers.get(index).isUsed == false) {
                  //  cuurent.isUsed = true;
                    all1Plyers.get(index).isUsed = true;
                    teamAdding1.add(all1Plyers.get(index));
                    myadapter = new Myadapter(this, teamAdding1 );
                    recyclerviewTeam1.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
             //  }
            }
            //fill second list
            all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num) && x.isUsed == false).collect(Collectors.toList());

            if (all1Plyers != null) {
               if (all1Plyers.get(index).isUsed == false) {
//                    cuurent = all1Plyers.get(index);
//                      cuurent.isUsed = true;
                    all1Plyers.get(index).isUsed = true;
                    teamAdding2.add(all1Plyers.get(index));
                    myadapter = new Myadapter(this, teamAdding2 );
                    recyclerviewTeam2.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
               }
            }
            //fill third list
            all1Plyers
                    = myBigList.stream().filter(x -> x.getNum().equals(num) && x.isUsed == false).collect(Collectors.toList());

            if (all1Plyers != null) {

               if (all1Plyers.get(index).isUsed == false) {
//                    cuurent = all1Plyers.get(index);
//                      cuurent.isUsed = true;
                    all1Plyers.get(index).isUsed = true;
                    teamAdding3.add(all1Plyers.get(index));
                    myadapter = new Myadapter(this, teamAdding3 );
                    recyclerviewTeam3.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
                }
            }
            strength++;

        }
    }

}
