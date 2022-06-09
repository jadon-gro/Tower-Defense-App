package edu.gatech.m1ndbl33d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.m1ndbl33d.controller.EnemyEngine;
import edu.gatech.m1ndbl33d.controller.PlayerEngine;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        TextView textViewPlayerName = findViewById(R.id.player_name);
        textViewPlayerName.setText(PlayerEngine.getPlayerName().toUpperCase() + " !");

        TextView textViewKills = findViewById(R.id.kills);
        String kills = Integer.toString(EnemyEngine.getNumEnemiesRemoved());
        textViewKills.setText(kills);

        TextView textViewMoneyEarned = findViewById(R.id.money_earned);
        String moneyEarned = Integer.toString(PlayerEngine.getMoneyEarned());
        textViewMoneyEarned.setText(moneyEarned);

        TextView textViewLivesLost = findViewById(R.id.lives_lost);
        String livesLost = Integer.toString(PlayerEngine.getLivesLost());
        textViewLivesLost.setText(livesLost);


    }
    public void startGame(View view) {
        Intent startConfigurationIntent = new Intent(this, ConfigurationActivity.class);
        startActivity(startConfigurationIntent);
    }
    public void quitGame(View view) {
        Intent startMainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(startMainActivityIntent);
    }
}

