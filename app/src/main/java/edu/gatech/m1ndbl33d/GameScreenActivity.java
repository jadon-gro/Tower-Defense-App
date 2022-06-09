package edu.gatech.m1ndbl33d;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.TimerTask;

import androidx.core.content.ContextCompat;
import edu.gatech.m1ndbl33d.controller.EnemyEngine;
import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.controller.PlayerEngine;
import edu.gatech.m1ndbl33d.controller.TimerEngine;
import edu.gatech.m1ndbl33d.controller.TowerEngine;
import edu.gatech.m1ndbl33d.model.Bug;
import edu.gatech.m1ndbl33d.model.RootKit;
import edu.gatech.m1ndbl33d.model.Virus;
import edu.gatech.m1ndbl33d.model.EmptyTile;
import edu.gatech.m1ndbl33d.model.Position;
import edu.gatech.m1ndbl33d.model.Tower;

public class GameScreenActivity extends AppCompatActivity {

    private MapEngine mapEngine;
    private boolean combatStarted;
    private boolean continueSpawning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Context context = getApplicationContext();
        ConstraintLayout layout = findViewById(R.id.activity_game_screen);

        // Programatically change the layout from the initial XML to render the map
        mapEngine = new MapEngine(context, "map1");
        mapEngine.render(context, layout);

        TimerEngine.createTimer();

        // Create a timer task and runnable handler for periodically updating text values
        final Handler updatePlayerValuesHandler = new Handler();
        TimerTask updatePlayerValuesTask = new TimerTask() {
            @Override
            public void run() {
                updatePlayerValuesHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        updatePlayerValues();
                    }
                });
            }
        };
        TimerEngine.scheduleTask(updatePlayerValuesTask, 0, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Tells us what towers we purchased
        Stack<Tower> towerStack = TowerEngine.getTowerStack();

        // Obtain views of each tile from idArray
        int[][] idArray = mapEngine.getIdArray();
        int id = 0;

        if (!towerStack.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Place your towers!",
                    Toast.LENGTH_SHORT).show();
        }

        for (int iRow = 0; iRow < mapEngine.getGridHeight(); iRow++) {
            for (int iCol = 0; iCol < mapEngine.getGridWidth(); iCol++) {
                // Grab each view from the idArray to manipulate tiles
                id = idArray[iRow][iCol];

                // Cast to ImageView to change sprites
                ImageView view = findViewById(id);

                // Create new final attributes for the inner class OnClick()
                final int x = iRow;
                final int y = iCol;
                final int newID = id;

                // Assign onClick() to the view of each time
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Only care about clicks on empty tiles when there's unplaced towers
                        if (!towerStack.isEmpty()
                                && (mapEngine.getTile(x, y) instanceof EmptyTile)) {
                            //Pop off the tower that will be placed
                            Tower tower = towerStack.pop();

                            // Place the tower in the internal representation of the map
                            mapEngine.placeTower(x, y, tower);

                            // Show the tower's sprite on screen, indicating a placed tower
                            if (view instanceof ImageView) {
                                ((ImageView) view).setImageDrawable(
                                        tower.getSprite(getApplicationContext()));
                            }
                        } else {
                            //assigning power upgrades to a tower
                            //only care when tile has a tower and a power update has been bought
                            if (TowerEngine.getUpdateAttackPower()
                                    && (mapEngine.getTile(x, y) instanceof Tower)) {
                                Tower towerTile = (Tower) mapEngine.getTile(x, y);
                                upgradePower(towerTile);
                                ((ImageView) view).setImageDrawable(
                                        towerTile.getSprite(getApplicationContext()));
                            }
                            //assigning range upgrades to a tower
                            //only care when tile has a tower and a range update has been bought
                            if (TowerEngine.getUpdateAttackRange()
                                    && (mapEngine.getTile(x, y) instanceof Tower)) {
                                Tower towerTile = (Tower) mapEngine.getTile(x, y);
                                upgradeRange(towerTile);
                                ((ImageView) view).setImageDrawable(
                                        towerTile.getSprite(getApplicationContext()));
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        clearEngines();
        PlayerEngine.initializePlayer(PlayerEngine.getPlayerName(),
                PlayerEngine.getPlayerDifficulty());
        super.onBackPressed();
    }

    public void menuScreen(View view) {
        Intent towerIntent = new Intent(this, ShopActivity.class);
        startActivity(towerIntent);
    }

    public void startCombat(View view) {
        if (combatStarted) {
            Toast.makeText(getApplicationContext(),
                    "Already in combat!!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Toast.makeText(getApplicationContext(), "We are starting!!", Toast.LENGTH_SHORT).show();
        continueSpawning = true;
        combatStarted = true;
        /*

        // This is some old logic for collecting path tiles that may be useful in the future
        // This had to be scrapped or else the enemy would appear at the top- & right-most path tile

        int[][] idArray = mapEngine.getIdArray();
        int id = 0;
        ArrayList<View> pathViews = new ArrayList<View>();

        for (int iRow = 0; iRow < mapEngine.getGridHeight(); iRow++) {
            for (int iCol = 0; iCol < mapEngine.getGridWidth(); iCol++) {
                id = idArray[iRow][iCol];

                //Add all Path tiles to an ArrayList
                if (mapEngine.getTile(iRow, iCol) instanceof Path) {
                    pathViews.add(findViewById(id));
                }

            }
        }         */

        //Generate view sequence from path and IDs
        List<Position> pathSequence = mapEngine.generatePathSequence();
        int[][] idArray = mapEngine.getIdArray();

        // Generates view list based on the IDs and sequence coordinates of path tiles
        // This could be generated alongside pathSequence if Start Combat has runtime issues
        // Such a change would put more View functionality in MapEngine so preferably avoided
        List<ImageView> pathViewSequence = new ArrayList<>();
        for (Position pathPosition : pathSequence) {
            int row = pathPosition.getX();
            int col = pathPosition.getY();
            int id = idArray[row][col];
            pathViewSequence.add((ImageView) findViewById(id));
        }


        // Create a timer task and runnable handler for periodically spawning new enemies
        final Handler spawnEnemiesHandler = new Handler();
        TimerTask spawnEnemiesTask = new TimerTask() {
            @Override
            public void run() {
                spawnEnemiesHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        spawnEnemies(pathViewSequence.get(0));
                    }
                });
            }
        };

        // Create a timer task and runnable handler for moving enemies
        final Handler moveEnemiesHandler = new Handler();
        TimerTask moveEnemiesTask = new TimerTask() {
            @Override
            public void run() {
                moveEnemiesHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        moveEnemies(pathViewSequence);
                    }
                });
            }
        };

        // Schedule tasks
        TimerEngine.scheduleTask(spawnEnemiesTask, 0, 4000);
        TimerEngine.scheduleTask(moveEnemiesTask, 0, 1000);

        /*
        // Toast spits out the sequence of locations in the Path
        // (I placed this here to make movement much easier to program)
        // OPTION: Consider creating a corresponding sequence of the path's ImageViews
        for (Position pathPosition : pathSequence) {
            Toast.makeText(getApplicationContext(), pathPosition.getX() + ","
                    + pathPosition.getY(), Toast.LENGTH_SHORT).show();
        } */
    }

    // Periodically updates text values for money and health
    public void updatePlayerValues() {
        TextView textViewMoney = findViewById(R.id.money);
        TextView textViewHealth = findViewById(R.id.health);
        textViewMoney.setText(String.format("%d", PlayerEngine.getPlayerMoney()));
        textViewHealth.setText(String.format("%d", PlayerEngine.getPlayerHealth()));

        //if health is less than or equal to 0 end game
        if (PlayerEngine.getPlayerHealth() <= 0) {
            endGame(false);
        }
    }

    // Periodically spawns new enemies per Piazza post @541
    public void spawnEnemies(ImageView firstView) {

        // checks to see if a final boss has been spawned, if so, don't spawn any after.
        if (continueSpawning) {
            if (EnemyEngine.initializeEnemy() == 1) {
                continueSpawning = false;
            }
            int enemyCount = EnemyEngine.getCurrentEnemies().size();
            for (int enemyInd = 0; enemyInd < enemyCount; enemyInd++) {
                firstView.setImageDrawable(EnemyEngine.getEnemySprite(enemyInd,
                        getApplicationContext()));
            }
        }
    }

    // Much of this code should be moved out of the method
    // In particular, this activity knows too much about Viruses and Bugs

    // Moves enemies between tiles on path
    public void moveEnemies(List<ImageView> pathViewSequence) {
        int enemyCount = EnemyEngine.getCurrentEnemies().size();

        for (int enemyInd = 0; enemyInd < enemyCount; enemyInd++) {
            int pathPosition = EnemyEngine.getEnemyPosition(enemyInd);
            // Remove enemy from current tile
            ImageView currentView = pathViewSequence.get(pathPosition);
            currentView.setImageDrawable(null);
            currentView.setBackgroundColor(ContextCompat.getColor(
                    getApplicationContext(), R.color.tron2));
            if (pathPosition < pathViewSequence.size() - 1) {
                // Move enemy to next tile
                pathPosition += 1;
                EnemyEngine.setEnemyPosition(enemyInd, pathPosition);
                // Draw enemy on next tile
                ImageView nextView = pathViewSequence.get(pathPosition);
                nextView.setImageDrawable(EnemyEngine.getEnemySprite(enemyInd,
                        getApplicationContext()));
                int alphaValue = 127 + (int) (EnemyEngine.getEnemyCurrentHealth(enemyInd)
                        * 128.0 / EnemyEngine.getEnemyMaxHealth(enemyInd));
                nextView.setImageAlpha(alphaValue);
            } else {
                int damage = EnemyEngine.getCurrentEnemies().get(enemyInd).getDamage();
                EnemyEngine.removeEnemy(enemyInd, false);
                enemyCount--;
                // This can vary across enemies when we need to add new behaviors to enemies
                PlayerEngine.decreaseHealthByAmount(damage);
            }
        }

        List<Position> towerPositions = mapEngine.getPlacedTowers();
        List<Integer> enemyPositions = EnemyEngine.getEnemyPositionList();

        for (Position towerPosition : towerPositions) {
            Tower towerTile = (Tower) mapEngine.getTile(towerPosition.getX(), towerPosition.getY());
            //This currently implements the "Bug" and "Rootkit" behavior
            //Check for tower type at position, use "findClosestEnemy" if Bug
            if ((towerTile instanceof Bug) || (towerTile instanceof RootKit)) {
                Integer targetInd = mapEngine.findClosestEnemy(towerPosition,
                        towerTile.getAttackRange(), enemyPositions);
                combat(targetInd, towerPosition, pathViewSequence, towerTile);
                enemyPositions = EnemyEngine.getEnemyPositionList();
            }

            // This currently implements the "Virus" behavior
            // Check for tower type at position, use "findEnemiesInRange" if Virus
            if (towerTile instanceof Virus) {
                List<Integer> targetInds = mapEngine.findEnemiesInRange(towerPosition,
                        towerTile.getAttackRange(), enemyPositions);
                Collections.reverse(targetInds);
                for (Integer targetInd : targetInds) {
                    combat(targetInd, towerPosition, pathViewSequence, towerTile);

                    enemyPositions = EnemyEngine.getEnemyPositionList();
                }
            }
        }
    }

    //tower engaging in combat
    public void combat(Integer targetInd, Position towerPosition,
                       List<ImageView> pathViewSequence, Tower towerTile) {
        if (targetInd == null) {
            // Change tower background color to passive
            int towerId = mapEngine.getIdArray()[towerPosition.getX()]
                    [towerPosition.getY()];
            findViewById(towerId).setBackgroundColor(Color.BLACK);
        } else {
            // Change tower background color to active
            int towerId = mapEngine.getIdArray()[towerPosition.getX()]
                    [towerPosition.getY()];
            findViewById(towerId).setBackgroundColor(Color.RED);
            // Change enemy view to being targeted
            ImageView enemyView = pathViewSequence.get(EnemyEngine.
                    getEnemyPosition(targetInd));
            enemyView.setBackground(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.targeted_path));
            // Current main damage process, split apart to make it easy to alter
            int health = EnemyEngine.getEnemyCurrentHealth(targetInd);
            // Multiplies by rootkit multi, refactor to its own method in CombatEngine
            int attackPower = (int) (towerTile.getAttackPower()
                    * (1 + TowerEngine.getNumRootKit() / 10.0));
            int newHealth = Math.max(0, health - attackPower);
            EnemyEngine.setEnemyCurrentHealth(targetInd, newHealth);
            //if health of enemy is less than or 0 the enemy cease to exist
            if (newHealth <= 0) {
                PlayerEngine.increaseMoneyByAmount(EnemyEngine.getMoney(targetInd));
                ImageView view = pathViewSequence.get(EnemyEngine.
                        getEnemyPosition(targetInd));
                view.setImageDrawable(null);
                view.setImageAlpha(255);
                view.setBackgroundColor(ContextCompat.getColor(
                        getApplicationContext(), R.color.tron2));
                EnemyEngine.removeEnemy(targetInd, true);

            }
            if (EnemyEngine.getCurrentEnemies().size() == 0 && !continueSpawning) {
                endGame(true);
            }
        }
    }

    //upgrade attack power of a tower
    public void upgradePower(Tower towerTile) {
        //upgrade power of a tower
        boolean success = towerTile.upgradeAttackPower();
        //power of a tower can only be upgraded once
        if (success) {
            //change color of tower to show upgrade in power
            //ImageView view =
            //view.setImageDrawable(towerTile.getSprite(getApplicationContext()));
            TowerEngine.setUpdateAttackPower(false); //player can by another power upgrade
        } else {
            Toast.makeText(getApplicationContext(), "You have upgraded the"
                    + " power of this tower before.", Toast.LENGTH_SHORT).show();
        }
    }

    //upgrade attack range of a tower
    public void upgradeRange(Tower towerTile) {
        //upgrade range of a tower
        boolean success = towerTile.upgradeAttackRange();
        //range of a tower can only be upgraded once
        if (success) {
            //change color of tower to show upgrade in range
            //ImageView view =
            //view.setImageDrawable(towerTile.getSprite(getApplicationContext()));
            TowerEngine.setUpdateAttackRange(false); //player can by another range upgrade
        } else {
            Toast.makeText(getApplicationContext(), "You have upgraded the"
                    + " range of this tower before.", Toast.LENGTH_SHORT).show();
        }
    }

    // End game
    public void endGame(boolean win) {
        Intent startGameOverIntent;
        if (win) {
            startGameOverIntent = new Intent(this, WinActivity.class);
        } else {
            startGameOverIntent = new Intent(this, GameOverActivity.class);
        }
        clearEngines();
        startActivity(startGameOverIntent);
        finish();

    }

    public void clearEngines() {
        TimerEngine.cancelTasks();
        EnemyEngine.clearEnemies();
        TowerEngine.clearTowers();
    }
}