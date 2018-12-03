package com.thuillier.guillaume.topquiz.Controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.thuillier.guillaume.topquiz.R;

import static android.content.Context.MODE_PRIVATE;

public class HistoryActivity extends AppCompatActivity {

    private TextView mScore1;
    private TextView mScore2;
    private TextView mScore3;
    private TextView mScore4;
    private TextView mScore5;
    private Button mByScoreButton;
    private Button mByPlayerButton;
    SharedPreferences mPreferences;

    String mTextScore1;
    String mTextScore2;
    String mTextScore3;
    String mTextScore4;
    String mTextScore5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mScore1 = findViewById(R.id.activity_history_scoreNumber1_text);
        mScore2 = findViewById(R.id.activity_history_scoreNumber2_text);
        mScore3 = findViewById(R.id.activity_history_scoreNumber3_text);
        mScore4 = findViewById(R.id.activity_history_scoreNumber4_text);
        mScore5 = findViewById(R.id.activity_history_scoreNumber5_text);
        mByScoreButton = findViewById(R.id.activity_history_by_score_button);
        mByPlayerButton = findViewById(R.id.activity_history_by_player_button);
        mPreferences = getSharedPreferences("scores.scores",MODE_PRIVATE);

        createStringScore();

        mScore1.setText(mTextScore1);
        mScore2.setText(mTextScore2);
        mScore3.setText(mTextScore3);
        mScore4.setText(mTextScore4);
        mScore5.setText(mTextScore5);
    }

    /**
     * Test if there is scores to display, if not : display nothing, if yes, display "player : score"
     */

    private void createStringScore(){

        if (mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[0], -1) != -1) {

            mTextScore1 = mPreferences.getString(MainActivity.PREF_KEY_FIRSTNAME_TABULAR[0], null) + " : " +
                    mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[0], -1);

        } else mTextScore1 = "";

        if (mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[1], -1) != -1) {

            mTextScore2 = mPreferences.getString(MainActivity.PREF_KEY_FIRSTNAME_TABULAR[1], null) + " : " +
                    mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[1], -1);

        } else mTextScore2 = "";

        if (mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[2], -1) != -1) {

            mTextScore3 = mPreferences.getString(MainActivity.PREF_KEY_FIRSTNAME_TABULAR[2], null) + " : " +
                    mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[2], -1);

        } else mTextScore3 = "";

        if (mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[3], -1) != -1) {

            mTextScore4 = mPreferences.getString(MainActivity.PREF_KEY_FIRSTNAME_TABULAR[3], null) + " : " +
                    mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[3], -1);

        } else mTextScore4 = "";

        if (mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[4], -1) != -1) {

            mTextScore5 = mPreferences.getString(MainActivity.PREF_KEY_FIRSTNAME_TABULAR[4], null) + " : " +
                    mPreferences.getInt(MainActivity.PREF_KEY_SCORE_TABULAR[4], -1);

        } else mTextScore5 = "";
    }

    private void byScore(){


    }

    private void byPlayer(){

    }
}
