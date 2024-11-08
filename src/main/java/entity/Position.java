package entity;

/**
 * Represents a 2D position with x and y coordinates.
 */
public class Position {
    private final int positionX;
    private final int positionY;

    public Position(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
    }
}
