package edu.gatech.m1ndbl33d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.m1ndbl33d.controller.PlayerEngine;
import edu.gatech.m1ndbl33d.controller.TowerEngine;

public class ShopActivity extends AppCompatActivity {
    private int bugPrice;
    private int rootkitPrice;
    private int virusPrice;
    private int upgradeAttackPowerPrice;
    private int upgradeAttackRangePrice;
    private int money;

    // These accessor methods exist to comply
    public int getBugPrice() {
        return bugPrice;
    }

    public void setBugPrice(int bugPrice) {
        this.bugPrice = bugPrice;
    }

    public int getRootkitPrice() {
        return rootkitPrice;
    }

    public void setRootkitPrice(int rootkitPrice) {
        this.rootkitPrice = rootkitPrice;
    }

    public int getVirusPrice() {
        return virusPrice;
    }

    public void setVirusPrice(int virusPrice) {
        this.virusPrice = virusPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        TextView textViewBugPrice = findViewById(R.id.cost_bug);
        bugPrice = TowerEngine.getTowerPrice("Bug");
        textViewBugPrice.setText(Integer.toString(bugPrice));

        TextView textViewRootKitPrice = findViewById(R.id.cost_root_kit);
        rootkitPrice = TowerEngine.getTowerPrice("Rootkit");
        textViewRootKitPrice.setText(Integer.toString(rootkitPrice));

        TextView textViewVirusPrice = findViewById(R.id.cost_virus);
        virusPrice = TowerEngine.getTowerPrice("Virus");
        textViewVirusPrice.setText(Integer.toString(virusPrice));

        TextView textViewPowerUpgradePrice = findViewById(R.id.cost_power_upgrade);
        upgradeAttackPowerPrice = TowerEngine.getUpdateAttackPowerPrice();
        textViewPowerUpgradePrice.setText(Integer.toString(upgradeAttackPowerPrice));

        TextView textViewRangeUpgradePrice = findViewById(R.id.cost_range_upgrade);
        upgradeAttackRangePrice = TowerEngine.getUpdateAttackRangePrice();
        textViewRangeUpgradePrice.setText(Integer.toString(upgradeAttackRangePrice));

        money = PlayerEngine.getPlayerMoney();
        //Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
    }

    public void previousScreen(View view) {
        finish();
    }


    public void buyBug(View view) {
        // It may be possible to generalize this further as the following methods work similarly
        // TowerEngine only cares about adding Towers
        boolean success = TowerEngine.addNewTower("bug", money);
        if (success) {
            // PlayerEngine only cares about the effect of the purchase on the money
            PlayerEngine.decreaseMoneyByAmount(bugPrice);
            money = PlayerEngine.getPlayerMoney();
            //increase number of bugs bought by 1
            TowerEngine.increaseNumBug();
        } else {
            // This isn't the prettiest way to tell a user this, but it works
            Toast.makeText(getApplicationContext(), "You don't have enough Panopticoin to "
                    + "buy a Bug tower", Toast.LENGTH_SHORT).show();
        }
    }

    public void buyRootKit(View view) {
        boolean success = TowerEngine.addNewTower("RootKit", money);
        if (success) {
            PlayerEngine.decreaseMoneyByAmount(rootkitPrice);
            money = PlayerEngine.getPlayerMoney();
            TowerEngine.increaseNumRootKit();
        } else {
            Toast.makeText(getApplicationContext(), "You don't have enough Panopticoin to "
                    + "buy a RootKit tower", Toast.LENGTH_SHORT).show();
        }
    }

    public void buyVirus(View view) {
        boolean success = TowerEngine.addNewTower("Virus", money);
        if (success) {
            PlayerEngine.decreaseMoneyByAmount(virusPrice);
            money = PlayerEngine.getPlayerMoney();
            TowerEngine.increaseNumVirus();
        } else {
            Toast.makeText(getApplicationContext(), "You don't have enough Panopticoin to "
                    + "buy a Virus tower", Toast.LENGTH_SHORT).show();
        }
    }

    public void buyPowerUpgrade(View view) {
        if (upgradeAttackPowerPrice <= money) {
            if (!TowerEngine.getUpdateAttackPower()) {
                PlayerEngine.decreaseMoneyByAmount(upgradeAttackPowerPrice);
                money = PlayerEngine.getPlayerMoney();
                TowerEngine.setUpdateAttackPower(true); //player cannot by another power upgrade
            } else {
                Toast.makeText(getApplicationContext(), "You already bought a power upgrade."
                        + " Please place the upgrade on a Tower", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "You don't have enough Panopticoin to "
                    + " buy a Tower Power upgrade", Toast.LENGTH_SHORT).show();
        }
    }

    public void buyRangeUpgrade(View view) {
        if (upgradeAttackRangePrice <= money) {
            if (!TowerEngine.getUpdateAttackRange()) {
                PlayerEngine.decreaseMoneyByAmount(upgradeAttackRangePrice);
                money = PlayerEngine.getPlayerMoney();
                TowerEngine.setUpdateAttackRange(true); //player cannot by another range upgrade
            } else {
                Toast.makeText(getApplicationContext(), "You already bought a range upgrade."
                        + " Please place the upgrade on a Tower", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "You don't have enough Panopticoin to "
                    + " buy a Tower Range upgrade", Toast.LENGTH_SHORT).show();
        }
    }

    public void inventory(View view) {
        Intent inventoryIntent = new Intent(this, InventoryActivity.class);
        startActivity(inventoryIntent);
    }
}
