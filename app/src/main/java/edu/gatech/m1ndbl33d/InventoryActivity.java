package edu.gatech.m1ndbl33d;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import edu.gatech.m1ndbl33d.controller.TowerEngine;

public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        TextView textViewNumBug = findViewById(R.id.num_bug);
        textViewNumBug.setText(String.format("%d", TowerEngine.getNumBug()));
        TextView textViewNumRootKit = findViewById(R.id.num_root_kit);
        textViewNumRootKit.setText(String.format("%d", TowerEngine.getNumRootKit()));
        TextView textViewNumVirus = findViewById(R.id.num_virus);
        textViewNumVirus.setText(String.format("%d", TowerEngine.getNumVirus()));
        CheckBox checkboxPowerUpgrade = findViewById((R.id.checkbox_power_upgrade));
        checkboxPowerUpgrade.setChecked(TowerEngine.getUpdateAttackPower());
        CheckBox checkboxRangeUpgrade = findViewById((R.id.checkbox_range_upgrade));
        checkboxRangeUpgrade.setChecked(TowerEngine.getUpdateAttackRange());
    }
    public void previousScreen(View view) {
        finish();
    }
}