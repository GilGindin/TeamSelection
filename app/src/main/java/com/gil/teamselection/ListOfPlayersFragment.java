package com.gil.teamselection;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class ListOfPlayersFragment extends Fragment {

    private RecyclerView playersRecyclerView;
    private EditText editTextName;
    private ArrayList<Player> myBigList;
    private double num = 0;
    private double sum = 0;
    private double avg = 0;
    private Myadapter myadapter;

    public ListOfPlayersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_of_players, container, false);

        editTextName = v.findViewById(R.id.editTextName);
        myBigList = new ArrayList<Player>();
        playersRecyclerView = v.findViewById(R.id.playersRecyclerView);
        playersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playersRecyclerView.setHasFixedSize(true);

        final Button btn1 = (Button) v.findViewById(R.id.btnRating);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowDialog();
            }
        });

//        Button add_btn = v.findViewById(R.id.add_btn);
//        add_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        final Button create_btn = (Button) v.findViewById(R.id.create_btn);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBigList.size() == 4) {

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ShowingGruopsFragment showingGruopsFragment = new ShowingGruopsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", myBigList);
                    bundle.putSerializable("avarge", avg);
                    showingGruopsFragment.setArguments(bundle);
                    ft.replace(android.R.id.content, showingGruopsFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                }
            }
        });
        return v;
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

                            if (myBigList.size() < 15) {

                                if (checkPlayersName(myBigList, p.getName()) == false) {
                                    myBigList.add(p);
                                    num = p.getNum();
                                    sum = sum + num;
                                    avg = sum / 2;
                                    num = 0;
                                    myadapter = new Myadapter(getContext(), myBigList);
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
                            Toast.makeText(getContext(), "Missing player name or rating...", Toast.LENGTH_SHORT).show();
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

}
