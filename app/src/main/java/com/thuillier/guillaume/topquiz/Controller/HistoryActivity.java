package com.thuillier.guillaume.topquiz.Controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.thuillier.guillaume.topquiz.R;

import java.util.ArrayList;

import static java.util.Collections.sort;

public class HistoryActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    private String[] mTextScore;

    private ArrayList<String> mPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView score1 = findViewById(R.id.activity_history_scoreNumber1_text);
        TextView score2 = findViewById(R.id.activity_history_scoreNumber2_text);
        TextView score3 = findViewById(R.id.activity_history_scoreNumber3_text);
        TextView score4 = findViewById(R.id.activity_history_scoreNumber4_text);
        TextView score5 = findViewById(R.id.activity_history_scoreNumber5_text);
        Button byScoreButton = findViewById(R.id.activity_history_by_score_button);
        Button byPlayerButton = findViewById(R.id.activity_history_by_player_button);
        mPreferences = getSharedPreferences("scores.scores",MODE_PRIVATE);

        mTextScore = new String[5];

        mPlayers = new ArrayList<>(5);

        for (int i = 0; i <= 4; i++){
            mPlayers.add(mPreferences.getString(MainActivity.PREF_KEY_FIRSTNAME_TABULAR[i], null) + " : " +
                    mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[i], -1));
        }

        createStringScore();

        score1.setText(mTextScore[0]);
        score2.setText(mTextScore[1]);
        score3.setText(mTextScore[2]);
        score4.setText(mTextScore[3]);
        score5.setText(mTextScore[4]);

        byScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        byPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byPlayer();
                createStringScore();
            }
        });
    }

    /**
     * Test if there is scores to display, if not : display nothing, if yes, display "player : score"
     */

    private void createStringScore(){

        for (int i = 0; i <= 4; i++){

            if (mPlayers.get(i).contains("null")){

                mTextScore[i] = "";

            } else {

                mTextScore[i] = mPlayers.get(i);

            }
        }
    }

    private void byScore(){


    }

    private void byPlayer(){

        sort(mPlayers);
    }
}
