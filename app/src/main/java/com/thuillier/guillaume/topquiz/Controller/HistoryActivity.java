package com.thuillier.guillaume.topquiz.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.thuillier.guillaume.topquiz.R;

public class HistoryActivity extends AppCompatActivity {

    private TextView mScore1;
    private TextView mScore2;
    private TextView mScore3;
    private TextView mScore4;
    private TextView mScore5;
    private Button mByScoreButton;
    private Button mByPlayerButton;

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



    }
}
