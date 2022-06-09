package edu.gatech.m1ndbl33d.model;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Override hashCode() and equals() for a functional contains() method in List<Position>

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;

        int compX = ((Position) o).getX();
        int compY = ((Position) o).getY();

        boolean sameX = (compX == x);
        boolean sameY = (compY == y);

        return (sameX && sameY);

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
