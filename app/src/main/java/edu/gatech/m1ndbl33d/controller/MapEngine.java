package edu.gatech.m1ndbl33d.controller;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.gatech.m1ndbl33d.R;
import edu.gatech.m1ndbl33d.model.EmptyTile;
import edu.gatech.m1ndbl33d.model.MapTile;
import edu.gatech.m1ndbl33d.model.Path;
import edu.gatech.m1ndbl33d.model.Position;
import edu.gatech.m1ndbl33d.model.Tower;

public class MapEngine {
    // Presently, this class combines model, controller, and view functionalities.
    private final String mapSource;
    private MapTile[][] map;
    private int[][] idArray; //shared with Game Screen to assist with updating tile views
    private int gridWidth;
    private int gridHeight;
    private Position startPosition;

    private List<Position> pathSequence;
    private List<Position> placedTowers;

    public MapEngine(Context context, String mapSource) {
        this.mapSource = mapSource;
        placedTowers = new ArrayList<>();
        initializeMap(context);
        // Avoid adding anything here. The overall process is around O(n^6) and needs optimization.
        // The app is highly likely to crash otherwise.
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public List<Position> getPathSequence() {
        // Do not conflate this with generatePathSequence(), which should only be done once
        return pathSequence;
    }

    public int[][] getIdArray() {
        return idArray;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    // Checks if tile is a valid tile within the bounds of the grid
    public boolean isValid(int x, int y) {
        if (y < 0 || y >= gridHeight) {
            return false;
        }
        return x >= 0 && x < gridWidth;
    }

    // Checks if requested tile is empty (unoccupied)
    public boolean isEmpty(int x, int y) {
        // No longer uses (Tower).isEmpty() as that is abstract in Tower
        MapTile tile = getTile(x, y);
        return (isValid(x, y) && tile instanceof EmptyTile);
    }

    // Returns requested tile
    public MapTile getTile(int x, int y) {
        // Previously used map[y][x]
        // Inconsistencies with the order of x and y may remain
        return map[x][y];
    }

    public MapTile getTileByPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        return map[x][y];
        //return getTile(position.getX(), position.getY());
    }

    // Places tower at requested tile
    public void placeTower(int x, int y, Tower tower) {
        map[x][y] = tower;
        placedTowers.add(new Position(x, y));
    }

    public void placeTowerByPosition(Position position, Tower tower) {
        changeTile(position, tower);
        placedTowers.add(position);
    }

    // Removes tower from requested tile
    public void removeTower(int x, int y) {
        if (isValid(x, y) || !(map[x][y] instanceof Tower)) {
            throw new IllegalArgumentException("Tile does not have a tower!");
        }
        Tower tower = (Tower) map[x][y];
        placedTowers.remove(new Position(x, y));
        map[x][y] = new EmptyTile();

    }

    public boolean checkValidityByPosition(Position position) {
        return isValid(position.getX(), position.getY());
    }

    public void changeTile(Position position, MapTile tile) {
        // Needs to be tested
        map[position.getX()][position.getY()] = tile;
    }

    public void removeTowerByPosition(Position position) {
        MapTile tile = getTileByPosition(position);
        if (checkValidityByPosition(position) || !(tile instanceof Tower)) {
            throw new IllegalArgumentException("Tile does not have a tower!");
        }
        Tower tower = (Tower) tile;
        placedTowers.remove(position);
        changeTile(position, new EmptyTile());
    }


    public List<Position> getPlacedTowers() {
        return placedTowers;
    }

    /// Generates visualization for map
    public void render(Context context, ConstraintLayout layout) {
        // Avoid adding anything here. The overall process is around O(n^6) and needs optimization.
        // The app is highly likely to crash otherwise.
        ImageView imageView;
        ConstraintLayout.LayoutParams lp;
        int id;
        idArray = new int[gridHeight][gridWidth];
        ConstraintSet cs = new ConstraintSet();

        // Add our views to the ConstraintLayout.
        for (int iRow = 0; iRow < gridHeight; iRow++) {
            for (int iCol = 0; iCol < gridWidth; iCol++) {
                // Reminder: this does not traverse the map sequentially
                imageView = new ImageView(context);
                lp = new ConstraintLayout.LayoutParams(ConstraintSet.MATCH_CONSTRAINT,
                        ConstraintSet.MATCH_CONSTRAINT);
                // As per the 2D map array, an id Array is created to obtain each tile's view
                id = View.generateViewId();
                idArray[iRow][iCol] = id;
                imageView.setId(id);
                imageView.setClickable(true);
                // Main class handler for tile rendering
                if (map[iRow][iCol] instanceof EmptyTile) {
                    imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                } else if (map[iRow][iCol] instanceof Path) {
                    imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.tron2));
                } else if (map[iRow][iCol] instanceof Tower) {
                    imageView.setImageDrawable(map[iRow][iCol].getSprite(context));
                } else {
                    throw new IllegalArgumentException("Invalid map tile when rendering!");
                }
                layout.addView(imageView, lp);
            }
        }

        // Create horizontal chain for each row and set the 1:1 dimensions.
        // but first make sure the layout frame has the right ratio set.
        cs.clone(layout);
        cs.setDimensionRatio(R.id.gridFrame, gridWidth + ":" + gridHeight);
        for (int iRow = 0; iRow < gridHeight; iRow++) {
            for (int iCol = 0; iCol < gridWidth; iCol++) {
                // Reminder: this does not traverse the map sequentially
                id = idArray[iRow][iCol];
                cs.setDimensionRatio(id, "1:1");
                if (iRow == 0) {
                    // Connect the top row to the top of the frame.
                    cs.connect(id, ConstraintSet.TOP, R.id.gridFrame, ConstraintSet.TOP);
                } else {
                    // Connect top to bottom of row above.
                    cs.connect(id, ConstraintSet.TOP, idArray[iRow - 1][0], ConstraintSet.BOTTOM);
                }
            }
            // Create a horizontal chain that will determine the dimensions of our squares.
            // Could also be createHorizontalChainRtl() with START/END.
            cs.createHorizontalChain(R.id.gridFrame, ConstraintSet.LEFT,
                    R.id.gridFrame, ConstraintSet.RIGHT,
                    idArray[iRow], null, ConstraintSet.CHAIN_PACKED);
        }

        cs.applyTo(layout);

    }

    public List<Position> generateNeighbors(Position position, List<Position> pathSequence) {
        // Find the neighbors of a position on the map. This assists enemy movement
        int row = position.getX();
        int col = position.getY();
        List<Position> neighbors = new ArrayList<>();

        // Find left, right, top, and bottom positions from the position in question
        // Eliminate positions that aren't on the grid
        if (!(row < 1)) {
            Position topPosition = new Position(row - 1, col);
            neighbors.add(topPosition);
        }
        if (!(col > gridWidth - 1)) {
            Position rightPosition = new Position(row, col + 1);
            neighbors.add(rightPosition);
        }
        if (!(row > gridHeight - 1)) {
            Position bottomPosition = new Position(row + 1, col);
            neighbors.add(bottomPosition);
        }
        if (!(col < 1)) {
            Position leftPosition = new Position(row, col - 1);
            neighbors.add(leftPosition);
        }

        // toRemove is instantiated b/c removing from a list while iterating over it leads to a
        // ConcurrencyModificationException
        List<Position> toRemove = new ArrayList<Position>();

        for (Position neighbor : neighbors) {
            MapTile temp = getTile(neighbor.getX(), neighbor.getY());
            if (!(temp instanceof Path)) {
                // Only tiles that are instances of path matter for enemy movement
                toRemove.add(neighbor);
            } else if (pathSequence.contains(neighbor)) {
                // Tiles that are already in the path don't need to be added again
                toRemove.add(neighbor);
            }
        }
        for (Position rem : toRemove) {
            neighbors.remove(rem);
        }
        return neighbors;
    }

    public List<Position> generatePathSequence() {
        pathSequence = new ArrayList<>(); // sequential positions of path
        Queue<Position> pathQueue = new LinkedList<>();

        // Tells us what position we're looking at at the moment
        pathQueue.add(startPosition);
        // Preserves every position we've looked at and decided was part of the path
        pathSequence.add(startPosition);

        while (!(pathQueue.isEmpty())) {
            // This overall algorithm is similar to DFS
            Position pos = (pathQueue.peek());

            // Remove visited path
            pathQueue.remove();

            List<Position> temp = generateNeighbors(pos, pathSequence);

            if (!(temp.isEmpty())) {
                // Only one of the neighbors should be valid
                Position newPost = temp.get(0);

                // Add the valid neighbor of the original position
                pathQueue.add(newPost);
                pathSequence.add(newPost);
            } else {
                break;
            }

        }
        return pathSequence;
    }

    // Returns index of closest enemy, or null if none in range
    public Integer findClosestEnemy(Position position, int range, List<Integer> enemyPathPos) {
        int xMin = Math.max(0, position.getX() - range);
        int yMin = Math.max(0, position.getY() - range);
        int xMax = Math.min(gridHeight - 1, position.getX() + range);
        int yMax = Math.min(gridWidth - 1, position.getY() + range);

        // Java doesn't have an integer infinite, so a practically infinite number for our purposes
        int closestDistance = 1000000;
        int closestIndex = -1;

        for (int enemyInd = 0; enemyInd < enemyPathPos.size(); enemyInd++) {
            Position enemyPos = pathSequence.get(enemyPathPos.get(enemyInd));
            if (enemyPos.getX() >= xMin && enemyPos.getX() <= xMax
                    && enemyPos.getY() >= yMin && enemyPos.getY() <= yMax) {
                int distance = Math.abs(enemyPos.getX() - position.getX())
                        + Math.abs(enemyPos.getY() - position.getY());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestIndex = enemyInd;
                }
            }
        }

        if (closestIndex == -1) {
            return null;
        }
        return closestIndex;
    }

    // Returns all enemies in range
    public List<Integer> findEnemiesInRange(Position position,
                                            int range, List<Integer> enemyPathPos) {
        int xMin = Math.max(0, position.getX() - range);
        int yMin = Math.max(0, position.getY() - range);
        int xMax = Math.min(gridHeight - 1, position.getX() + range);
        int yMax = Math.min(gridWidth - 1, position.getY() + range);

        List<Integer> enemiesInRange = new ArrayList<>();

        for (int enemyInd = 0; enemyInd < enemyPathPos.size(); enemyInd++) {
            Position enemyPos = pathSequence.get(enemyPathPos.get(enemyInd));
            if (enemyPos.getX() >= xMin && enemyPos.getX() <= xMax
                    && enemyPos.getY() >= yMin && enemyPos.getY() <= yMax) {
                enemiesInRange.add(enemyInd);
            }
        }

        return enemiesInRange;
    }


    //Create initial map layout
    public void initializeMap(Context context) {
        // Avoid adding anything here. The overall process is around O(n^6) and needs optimization.
        // The app is highly likely to crash otherwise.

        // I considered doing String[] -> String[][] -> Integer[][] as
        // that would make future operations much easier BUT
        // it would require heavily changing MapEngine


        //Find map starter from resources
        int resId = context.getResources().getIdentifier(mapSource,
                "array", context.getPackageName());
        String[] mapStarter = context.getResources().getStringArray(resId);

        //Reads an encoded start position in the first row (primarily done for efficiency)
        String[] rawStartPosition = mapStarter[0].split(",");
        startPosition = new Position(parseInt(rawStartPosition[0]), parseInt(rawStartPosition[1]));

        gridWidth = parseInt(mapStarter[1]);
        gridHeight = parseInt(mapStarter[2]);
        map = new MapTile[gridHeight][gridWidth];

        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                char tile = mapStarter[i + 3].charAt(j);
                // Main class handler for tiles at initialization
                // Reminder: this does not traverse the map sequentially b
                switch (tile) {
                case '0':
                    map[i][j] = new EmptyTile();
                    break;
                case '1':
                    map[i][j] = new Path();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid map tile when initializing!");
                }
            }
        }

    }

}
