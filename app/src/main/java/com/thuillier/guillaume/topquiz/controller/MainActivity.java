package com.thuillier.guillaume.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thuillier.guillaume.topquiz.R;
import com.thuillier.guillaume.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mHistoryButton;
    private User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";

    static final String[] PREF_KEY_SCORE_TABULAR = new String[] {"PREF_KEY_SCORE1","PREF_KEY_SCORE2","PREF_KEY_SCORE3",
            "PREF_KEY_SCORE4","PREF_KEY_SCORE5"};

    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    static final String[] PREF_KEY_FIRSTNAME_TABULAR = new String[]{"PREF_KEY_FIRSTNAME1","PREF_KEY_FIRSTNAME2",
            "PREF_KEY_FIRSTNAME3", "PREF_KEY_FIRSTNAME4", "PREF_KEY_FIRSTNAME5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = new User();

        mPreferences = getSharedPreferences("scores.scores",MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mHistoryButton = (Button) findViewById(R.id.activity_main_history_button);

        if (mPreferences.getInt(PREF_KEY_SCORE, -1) == -1) {
            mHistoryButton.setVisibility(View.INVISIBLE);
        } else {
            mHistoryButton.setVisibility(View.VISIBLE);
        }

        mPlayButton.setEnabled(false);

        greetUser();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = mNameInput.getText().toString();
                mUser.setFirstname(firstname);

                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstname()).apply();

                // User clicked the button
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();

            putScoreInHistory(score);

            mHistoryButton.setVisibility(View.VISIBLE);

            greetUser();
        }
    }

    private void greetUser() {
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);

        if (null != firstname) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = "Welcome back, " + firstname
                    + "!\nYour last score was " + score
                    + ", will you do better this time?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
        }
    }

    private void putScoreInHistory(int score){

        int minScore = 100;
        int minScoreIndex = 4;
        boolean scoreWritten = false;

        for (int i = 0; i <= 4; i++) {
            if (mPreferences.getInt(PREF_KEY_SCORE_TABULAR[i], -1) == -1){

                mPreferences.edit().putInt(PREF_KEY_SCORE_TABULAR[i], score).apply();
                mPreferences.edit().putString(PREF_KEY_FIRSTNAME_TABULAR[i], mUser.getFirstname()).apply();
                scoreWritten = true;
                break;
            }
        }
        if (!scoreWritten) {
            for (int i = 0; i <= 4; i++) {
                if (mPreferences.getInt(PREF_KEY_SCORE_TABULAR[i], -1) < minScore && mPreferences.getInt(PREF_KEY_SCORE_TABULAR[i], -1) != -1) {
                    minScore = mPreferences.getInt(PREF_KEY_SCORE_TABULAR[i], -1);
                    minScoreIndex = i;
                }
            }
            mPreferences.edit().putInt(PREF_KEY_SCORE_TABULAR[minScoreIndex], score).apply();
            mPreferences.edit().putString(PREF_KEY_FIRSTNAME_TABULAR[minScoreIndex], mUser.getFirstname()).apply();
        }
    }
}
