package edu.gatech.m1ndbl33d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.m1ndbl33d.controller.PlayerEngine;
import edu.gatech.m1ndbl33d.controller.TowerEngine;

public class ConfirmConfigActivity extends AppCompatActivity {
    private String difficulty;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_config);

        Intent intent = getIntent();
        name = intent.getStringExtra(ConfigurationActivity.EXTRA_MESSAGE1);
        difficulty = intent.getStringExtra(ConfigurationActivity.EXTRA_MESSAGE2);
        // Initialize money, health, and prices from difficulty and name of player
        PlayerEngine.initializePlayer(name, difficulty);
        TowerEngine.setTowerPrices(difficulty);

        TextView textView = findViewById(R.id.textView3);
        String message = "Hello " + name + "! " + "You are playing on "
                + difficulty + " mode. ";
        textView.setText(message);
    }

    public void nextScreen(View view) {
        Intent gameIntent = new Intent(this, GameScreenActivity.class);
        startActivity(gameIntent);
    }
}