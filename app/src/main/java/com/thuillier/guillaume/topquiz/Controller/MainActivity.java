package com.thuillier.guillaume.topquiz.Controller;

import android.content.Intent;
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

    private TextView mWelcomeText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWelcomeText = findViewById(R.id.activity_main_text_field_welcome);
        mNameInput = findViewById(R.id.activity_main_input_text_name);
        mPlayButton = findViewById(R.id.activity_main_play_btn);
        mUser = new User();

        mPlayButton.setEnabled(false);
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

                mUser.setFirstName(mNameInput.getText().toString());
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivity);
            }
        });
    }
}
