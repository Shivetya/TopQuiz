package com.thuillier.guillaume.topquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mWelcomeText;
    private EditText mNameInput;
    private Button mPlayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWelcomeText = findViewById(R.id.activity_main_text_field_welcome);
        mNameInput = findViewById(R.id.activity_main_input_text_name);
        mPlayBtn = findViewById(R.id.activity_main_play_btn);

        mPlayBtn.setEnabled(false);
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mPlayBtn.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
