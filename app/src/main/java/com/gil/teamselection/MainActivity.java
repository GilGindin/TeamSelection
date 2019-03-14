package com.gil.teamselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView RVfirst;
    private ArrayList<Player> myBigList;
    private ArrayList<Player> firstTeamList;
    private ArrayList<Player> secondTeamList;
    private ArrayList<Player> thirdTeamList;
    private ArrayList<Player> fourthTeamList;
    private ArrayList<Player> fifthTeamList;
    private EditText editTextName;
    private EditText editTextNum;
    private ArrayList<Player> teamAdding1;
    private ArrayList<Player> teamAdding2;
    private ArrayList<Player> teamAdding3;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RVfirst = findViewById(R.id.RVfirst);
        editTextName = findViewById(R.id.editTextName);
        editTextNum = findViewById(R.id.editTextNum);

        firstTeamList = new ArrayList<>(2);
        secondTeamList = new ArrayList<>(2);
        thirdTeamList = new ArrayList<>(2);
        fourthTeamList = new ArrayList<>(2);
        fifthTeamList = new ArrayList<>(2);

        myBigList = new ArrayList<>(14);

        teamAdding1 = new ArrayList<>(5);
        teamAdding2 = new ArrayList<>(5);
        teamAdding3 = new ArrayList<>(5);



    }

    public void addButton_onClick(View view) {

        String name = editTextName.getText().toString();
        int num = Integer.parseInt(editTextNum.getText().toString());
        if (num <= 0 || num > 5) {
            Toast.makeText(this, "number must be between 1-5", Toast.LENGTH_SHORT).show();
            editTextNum.setText("");
        }
        if (num > 0 && num <= 5) {
            Player newPlayer = new Player(name, num);

            if (newPlayer.getNum() == 1 && firstTeamList.size() < 3) {
                firstTeamList.add(newPlayer); // first team that get only players with int num 1 , and the list size is 3 objects.
                myBigList.add(newPlayer); // the big list that get all the 15 players , the list size is 15 objects

                if (teamAdding1.size() == 1 && teamAdding2.size() == 1 && teamAdding3.size() < 1) {
                    teamAdding3.add(newPlayer);
                }
                if (teamAdding1.size() == 1 && teamAdding2.size() < 1) {
                    teamAdding2.add(newPlayer);
                }
                if ( teamAdding1.size() < 1 ) {
                    teamAdding1.add(newPlayer);
                }


                Toast.makeText(this, "new player added " + newPlayer.getName(), Toast.LENGTH_SHORT).show();
            }

            if (newPlayer.getNum() == 2 && secondTeamList.size() < 3) {
                secondTeamList.add(newPlayer);
                myBigList.add(newPlayer);

                if (teamAdding1.size() == 1 && teamAdding2.size() == 1 && teamAdding3.size() < 1) {
                    teamAdding3.add(newPlayer);
                }
                if (teamAdding1.size() == 1 && teamAdding2.size() < 1) {
                    teamAdding2.add(newPlayer);
                }
                if ( teamAdding1.size() < 1 ) {
                    teamAdding1.add(newPlayer);
                }


                Toast.makeText(this, "new player added " + newPlayer.getName(), Toast.LENGTH_SHORT).show();
            }


            if (newPlayer.getNum() == 3 && thirdTeamList.size() < 3) {
                thirdTeamList.add(newPlayer);
                myBigList.add(newPlayer);

                if (teamAdding1.size() == 1 && teamAdding2.size() == 1 && teamAdding3.size() < 1) {
                    teamAdding3.add(newPlayer);
                }
                if (teamAdding1.size() == 1 && teamAdding2.size() < 1) {
                    teamAdding2.add(newPlayer);
                }
                if ( teamAdding1.size() < 1 ) {
                    teamAdding1.add(newPlayer);
                }


                Toast.makeText(this, "new player added " + newPlayer.getName(), Toast.LENGTH_SHORT).show();
            }

            if (newPlayer.getNum() == 4 && fourthTeamList.size() < 3) {
                fourthTeamList.add(newPlayer);
                myBigList.add(newPlayer);

                if (teamAdding1.size() == 1 && teamAdding2.size() == 1 && teamAdding3.size() < 1) {
                    teamAdding3.add(newPlayer);
                }
                if (teamAdding1.size() == 1 && teamAdding2.size() < 1) {
                    teamAdding2.add(newPlayer);
                }
                if ( teamAdding1.size() < 1 ) {
                    teamAdding1.add(newPlayer);
                }

                Toast.makeText(this, "new player added " + newPlayer.getName(), Toast.LENGTH_SHORT).show();
            }

            if (newPlayer.getNum() == 5 && fifthTeamList.size() < 3) {
                fifthTeamList.add(newPlayer);
                myBigList.add(newPlayer);

                if (teamAdding1.size() == 1 && teamAdding2.size() == 1 && teamAdding3.size() < 1) {
                    teamAdding3.add(newPlayer);
                }
                if (teamAdding1.size() == 1 && teamAdding2.size() < 1) {
                    teamAdding2.add(newPlayer);
                }
                if ( teamAdding1.size() < 1 ) {
                    teamAdding1.add(newPlayer);
                }

                Toast.makeText(this, "new player added " + newPlayer.getName(), Toast.LENGTH_SHORT).show();
            }


            if (teamAdding1.contains(newPlayer) && teamAdding2.contains(newPlayer) ) {
                teamAdding3.add(newPlayer);
            }
            if (teamAdding1.contains(newPlayer) && teamAdding3.contains(newPlayer) ) {
                teamAdding2.add(newPlayer);
            }
            if ( teamAdding2.contains(newPlayer) && teamAdding3.contains(newPlayer) ) {
                teamAdding1.add(newPlayer);
            }
//            teamAdding1.add(newPlayer);
//            teamAdding2.add(newPlayer);
//            teamAdding3.add(newPlayer);
            editTextNum.setText("");
        }


    }

    public void buttonShow_onClick(View view) {

        Log.d(TAG, "buttonShow_onClick: " + teamAdding1.size() + teamAdding2.size() + teamAdding3.size());

    }



}
