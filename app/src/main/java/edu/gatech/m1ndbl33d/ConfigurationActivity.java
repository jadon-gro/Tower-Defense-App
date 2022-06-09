package edu.gatech.m1ndbl33d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE1 = "edu.gatech.m1ndbl33d.MESSAGE1";
    public static final String EXTRA_MESSAGE2 = "edu.gatech.m1ndbl33d.MESSAGE2";
    private String name;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

    }

    public void submitName(View view) {
        EditText editText = findViewById(R.id.PlayerName);
        name = editText.getText().toString();
    }

    public void chooseEasy(View view) {
        difficulty = "Easy";
    }

    public void chooseMedium(View view) {
        difficulty = "Medium";
    }

    public void chooseHard(View view) {
        difficulty = "Hard";
    }

    public void confirmInfo(View view) {
        if (name != null && !name.trim().isEmpty() && difficulty != null) {
            //Initialize Player name
            Intent intent = new Intent(this, ConfirmConfigActivity.class);
            intent.putExtra(EXTRA_MESSAGE1, name);
            intent.putExtra(EXTRA_MESSAGE2, difficulty);
            startActivity(intent);
        }
    }


}