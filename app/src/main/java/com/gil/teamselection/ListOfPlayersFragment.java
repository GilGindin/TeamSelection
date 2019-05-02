package com.gil.teamselection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collection;

public class ListOfPlayersFragment extends Fragment {

    public static final String KEY_PLAYERS = "number_of_players";
    public static final String KEY_TEAMS = "number_of_teams";
    private FragmentListOfPlayersListener listener;
    private RecyclerView playersRecyclerView;
    private EditText editTextName;
    private Button btn1;
    private Button create_btn;
    private ArrayList<Player> myBigList;
    private double num = 0;
    private double sum = 0;
    private double avg = 0;
    private int numberOfPlayers;
    private int numberOfTeams;
    private Myadapter myadapter;
    private TextView textViewPlayers;
    private View v;
    private ImageView imageView;

    public interface FragmentListOfPlayersListener {
        void onInputListSent(ArrayList<Player> list, int teams, double avarge);
    }

    public ListOfPlayersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list_of_players, container, false);

        createList();
        buildRecyclerView();
        setButtons();

        return v;
    }

    public void createList() {
        myBigList = new ArrayList<Player>();
        myBigList.add(new Player("Gil", 3));
        myBigList.add(new Player("Ron", 3));
        myBigList.add(new Player("Dan", 3));
        myBigList.add(new Player("Shahar", 1));
        myBigList.add(new Player("Eli", 5));
        myBigList.add(new Player("Ali", 5));
//        myBigList.add(new Player("Kobi", 4));
//        myBigList.add(new Player("Yossi", 4));
//        myBigList.add(new Player("Avi", 1));
//        myBigList.add(new Player("Ran", 4));
//        myBigList.add(new Player("גיל", 5));
//        myBigList.add(new Player("סהר", 5));
//        myBigList.add(new Player("רן", 4));
//        myBigList.add(new Player("יעקב", 4));
//        myBigList.add(new Player("עוזי", 1));
//        myBigList.add(new Player("Ran", 4));
        myadapter = new Myadapter(myBigList);
        for (Player p : myBigList) {
            sum += p.getNum();
        }
        avg = sum / numberOfTeams;
    }

    private void buildRecyclerView() {
        playersRecyclerView = v.findViewById(R.id.playersRecyclerView);
        playersRecyclerView.setHasFixedSize(true);
        playersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playersRecyclerView.setAdapter(myadapter);
        myadapter.setOnItemClickListener(new Myadapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //TODO create option to new textname
                //   changeItem(position ,);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void setButtons() {
        editTextName = v.findViewById(R.id.editTextName);
        imageView = v.findViewById(R.id.image_delete);
        textViewPlayers = v.findViewById(R.id.textViewPlayers);
        textViewPlayers.setText("כמות השחקנים שצריך להזין: " + numberOfPlayers);

         btn1 = (Button) v.findViewById(R.id.btnRating);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowDialog();
            }
        });

         create_btn = (Button) v.findViewById(R.id.create_btn);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onInputListSent(myBigList, numberOfTeams, avg);
            }
        });
    }

    public void updateArgumentsFromAddaptionFrag(int teams, int players) {
        if (teams != 0 && players != 0) {
            numberOfTeams = teams;
            numberOfPlayers = players;
        }
    }

    public void updateFromShowFrag(ArrayList<Player> list, double avarge) {
        if (list.size() != 0 && avarge != 0) {
            myBigList = list;
            avg = avarge;
        }
    }

    public ArrayList<Player> getMyBigList() {
        return myBigList;
    }

    public double getAvg() {
        return avg;
    }

    public int getTeams() {
        return numberOfTeams;
    }

    public void ShowDialog() {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(getContext());
        LinearLayout linearLayout = new LinearLayout(getContext());
        final RatingBar rating = new RatingBar(getContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rating.setLayoutParams(lp);
        rating.setNumStars(5);
        rating.setStepSize((float) 1.0);

        //add ratingBar to linearLayout
        linearLayout.addView(rating);

        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Add Rating: ");

        //add linearLayout to dailog
        popDialog.setView(linearLayout);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                num = ratingBar.getRating();
            }
        });
        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editTextName.getText().toString().trim();
                        Player p = new Player(name, num);


                        if (checkObjectNotNull(p.getNum(), p.getName()) == true) {

                            if (myBigList.size() <= numberOfPlayers) {

                                if (checkPlayersName(myBigList, p.getName()) == false) {

                                    myBigList.add(p);
                                    numberOfPlayers--;
                                    textViewPlayers.setText("כמות השחקנים שצריך למלא: " + numberOfPlayers);
                                    num = p.getNum();
                                    sum = sum + num;
                                    avg = sum / numberOfTeams;
                                    num = 0;
                                    myadapter = new Myadapter(myBigList);
                                    myadapter.setOnItemClickListener(new Myadapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {

                                        }

                                        @Override
                                        public void onDeleteClick(int position) {
                                            removeItem(position);
                                        }
                                    });
                                    playersRecyclerView.setAdapter(myadapter);
                                    myadapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(getContext(), "You already have " + p.getName() + " in your list", Toast.LENGTH_LONG).show();
                                    editTextName.setText("");
                                }
                            } else {
                                Toast.makeText(getContext(), "You already have " + myBigList.size() + " players", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(getContext(), "Missing player name ...", Toast.LENGTH_SHORT).show();
                            num = 0;
                        }
                        editTextName.setText("");
                        dialog.dismiss();
                    }
                })
                // Button Cancel
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                editTextName.setText("");
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();
    }

    public static boolean checkPlayersName(Collection<Player> list, String name) {
        for (Player o : list) {
            if (o != null && o.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkObjectNotNull(double number, String name) {

        double num = 0;
        num = number;

        if (num > 0)
            if (name.trim() != null && !name.trim().isEmpty()) {
                return true;
            }
        return false;
    }

    public void removeItem(int position) {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setTitle("Are you sure you want to delete?");
        dialog.setView(linearLayout);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myBigList.remove(position);
                myadapter.notifyItemRemoved(position);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.create();
        dialog.show();

    }

    public void changeItem(int position, String text) {
        myBigList.get(position).changeText1(text);
        myadapter.notifyItemChanged(position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListOfPlayersListener) {
            listener = (FragmentListOfPlayersListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentListOfPlayersListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
