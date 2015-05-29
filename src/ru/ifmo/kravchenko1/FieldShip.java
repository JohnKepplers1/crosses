package ru.ifmo.kravchenko1;

import java.util.ArrayList;

public class FieldShip {
    public final static int SHUT_MISSED = 1;
    public final static int SHUT_INJURED = 2;
    public final static int SHUT_KILLED = 3;
    public final static int SHUT_WON = 4;

    private CellClass[][] cells;
    private ArrayList<Ship> ships;
    private int width;
    private int height;
    private int maxShip;
    private int numLiveShips;

    public FieldShip(int x, int y, int ship) {
        setDimention(x, y, ship);
        setShip();
    }

    public void setDimention(int x, int y, int ship) {
        setWidth(x);
        setHeight(y);
        setMaxShip(ship);
    }

    public void setShip() {
        setNumLiveShips(0);
        cells = new CellClass[getWidth()][getHeight()];
        for (int j = 0; j < getHeight(); j++) {
            for (int i = 0; i < getWidth(); i++) {

                cells[i][j] = new CellClass(i, j);
            }
        }
        ships = new ArrayList<Ship>();
        for (int i = getMaxShip(); i > 0; i--) {
            for (int j = (getMaxShip() - i + 1); j > 0; j--) {
                Ship ship = new Ship(this, i);
                ships.add(ship);
            }
        }
        for (int j = 0; j < getHeight(); j++) {
            for (int i = 0; i < getWidth(); i++) {
                CellClass cell = cells[i][j];
                if (cell.getState() == CellClass.getCellBorder()) {
                    cell.setState(CellClass.getCellWater());
                }
            }
        }
    }

    public int doShot(int x, int y) {
        int shot = getCell(x, y).doShot();
        return shot;
    }

    public boolean isBound(int x, int y) {
        return !((x < 0) || (x > (getWidth() - 1)) || (y < 0) || (y > (getHeight() - 1)));
    }

    public CellClass getCell(int x, int y) {
        return cells[x][y];
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxShip() {
        return maxShip;
    }

    public void setMaxShip(int maxShip) {
        this.maxShip = maxShip;
    }

    public int getNumLiveShips() {
        return numLiveShips;
    }

    public void setNumLiveShips(int numLiveShips) {
        this.numLiveShips = numLiveShips;
    }

}