package com.thuillier.guillaume.topquiz.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.thuillier.guillaume.topquiz.R;
import com.thuillier.guillaume.topquiz.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    private String[] mTextScore;

    private ArrayList<Player> mPlayers;

    private TextView mScore1;
    private TextView mScore2;
    private TextView mScore3;
    private TextView mScore4;
    private TextView mScore5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mScore1 = findViewById(R.id.activity_history_scoreNumber1_text);
        mScore2 = findViewById(R.id.activity_history_scoreNumber2_text);
        mScore3 = findViewById(R.id.activity_history_scoreNumber3_text);
        mScore4 = findViewById(R.id.activity_history_scoreNumber4_text);
        mScore5 = findViewById(R.id.activity_history_scoreNumber5_text);
        Button byScoreButton = findViewById(R.id.activity_history_by_score_button);
        Button byPlayerButton = findViewById(R.id.activity_history_by_player_button);
        mPreferences = getSharedPreferences("scores.scores", MODE_PRIVATE);

       // mTextScore = new String[5];

        mPlayers = new ArrayList<>(5);

        for (int i = 0; i <= 4; i++) {
            mPlayers.add(new Player(mPreferences.getString(MainActivity.PREF_KEY_FIRSTNAME_TABULAR[i], null), mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[i], -1)));
        }

        createStringScore();

        setTextScore();

        byScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byScore();
            }
        });

        byPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byPlayer();
            }
        });
    }

    /**
     * Test if there is scores to display, if not : display nothing, if yes, display "player : score"
     */

    private void createStringScore() {
/*
        for (int i = 0; i <= 4; i++) {

            if (mPlayers.get(i).contains("-1")) {

                mTextScore[i] = "";

            } else {

                mTextScore[i] = mPlayers.get(i);

            }
        }
        */
    }

    private void setTextScore() {

        mScore1.setText(mPlayers.get(0).toString());
        mScore2.setText(mPlayers.get(1).toString());
        mScore3.setText(mPlayers.get(2).toString());
        mScore4.setText(mPlayers.get(3).toString());
        mScore5.setText(mPlayers.get(4).toString());
    }


    private void byScore() {

        Collections.sort(mPlayers, new Comparator<Player>(){

            @Override
            public int compare(Player o1, Player o2) {
                return o1.getScore() - o2.getScore();
            }
        });
        createStringScore();
        setTextScore();

    }

    private void byPlayer() {

        Collections.sort(mPlayers, new Comparator<Player>(){

            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getName() == null && o2.getName() != null) return -1;
                if (o2.getName() == null && o1.getName() != null) return 1;
                if (o1.getName() == null && o2.getName() == null) return 0;
                
                return o1.getName().compareTo(o2.getName());
            }
        });
        createStringScore();
        setTextScore();
    }

}
