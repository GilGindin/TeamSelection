package com.gil.teamselection;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class AddaptionFragment extends Fragment {

    private FragmentAddpationListener listener;
    private EditText textViewCountPlayers;
    private TextView textViewCountTeams;
    private SeekBar seekBarPlayers;
    private SeekBar seekBarTeams;
    private Button continueButton;
    private Switch switchButtonRating;
    private Switch switchButtonDataBase;
    private int seekBarPlayersProgress;
    private int seekBarTeamsProgress;

    public interface FragmentAddpationListener {
        void onInputAsent(int teams, int players);
    }

    public AddaptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_addaption, container, false);

        seekBarPlayers = v.findViewById(R.id.seekBarPlayers);
        seekBarTeams = v.findViewById(R.id.seekBarTeams);
        continueButton = v.findViewById(R.id.continueButton);
        switchButtonRating = v.findViewById(R.id.switchButtonRating);
        switchButtonDataBase = v.findViewById(R.id.switchButtonDataBase);

        if (seekBarPlayers != null) {
            seekBarPlayers.setProgress(4);
            seekBarPlayersProgress = 0;
            seekBarPlayers.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seekBarPlayersProgress = progress;
                    textViewCountPlayers = v.findViewById(R.id.textViewCountPlayers);
                    textViewCountPlayers.setText("" + seekBarPlayersProgress);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    textViewCountPlayers.setText("" + seekBarPlayersProgress);
                }
            });
        }
        if (seekBarTeams != null) {
            seekBarTeams.setProgress(2);
            seekBarPlayersProgress = 0;
            seekBarTeams.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                    seekBarTeamsProgress = progressValue;
                    if (fromUser) {
                        seekBarPlayers.setProgress(seekBarTeamsProgress * 2);
                    }
                    textViewCountTeams = v.findViewById(R.id.textViewCountTeams);
                    textViewCountTeams.setText("" + seekBarTeamsProgress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    textViewCountTeams.setText("" + seekBarTeamsProgress);
                }
            });
        }


        switchButtonRating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        switchButtonDataBase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Bundle bundle_from_addaptionFrag = new Bundle();
                int numberOPlayers = seekBarPlayers.getProgress();
                int numberOfTeams = seekBarTeams.getProgress();
                Log.d("", "onClick: " + seekBarPlayers + " , " + seekBarTeams);
                listener.onInputAsent(numberOfTeams, numberOPlayers);

                // bundle_from_addaptionFrag.putInt("key_players", numberOPlayers);
                //  bundle_from_addaptionFrag.putInt("key_teams", numberOfTeams);
//                ListOfPlayersFragment listOfPlayersFragment = new ListOfPlayersFragment();
//                listOfPlayersFragment.setArguments(bundle_from_addaptionFrag);
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//                ft.replace(R.id.mainConteiner, listOfPlayersFragment, "LIST_FRAG_TAG");
//                ft.addToBackStack("going to lisFrag from addaptFrag");
//                ft.remove(AddaptionFragment.this);
//                ft.commit();

            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAddpationListener) {
            listener = (FragmentAddpationListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentAddpationListener ");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
