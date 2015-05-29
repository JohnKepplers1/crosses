package ru.ifmo.kravchenko1;

import java.util.ArrayList;
import java.util.Random;

public class Ship {
    public final static int SHIP_WELL = 1;
    public final static int SHIP_INJURED = 2;
    public final static int SHIP_KILLED = 3;

    public int x;
    public int y;
    public int dx;
    public int dy;
    private int size;
    private int health;
    private int state;
    private FieldShip field;
    private ArrayList<CellClass> listCells;
    private ArrayList<CellClass> listBorders;

    public Ship(FieldShip field, int size) {
        this.size = size;
        this.health = size;
        this.field = field;
        this.state = Ship.SHIP_WELL;

        do {
            this.getPlace();
        } while (!checkPlace());

        this.listCells = new ArrayList<CellClass>();
        this.listBorders = new ArrayList<CellClass>(

        );
        this.setShip();

        getField().setNumLiveShips(getField().getNumLiveShips() + 1);
    }

    public ArrayList<CellClass> getListCells() {
        return listCells;
    }

    public ArrayList<CellClass> getListBorders() {
        return listBorders;
    }

    private void getPlace() {
        Random rand = new Random();
        this.x = rand.nextInt(getField().getWidth());
        this.y = rand.nextInt(getField().getHeight());
        this.dx = 0;
        this.dy = 0;
        if (rand.nextInt(2) == 1) {
            this.dx = 1;
        } else {
            this.dy = 1;
        }
    }

    private boolean byPass(PlaceShip tp) {
        int i, m, n;

        for (i = 0; i < size; i++) {

            m = y + i * dy;
            n = x + i * dx;
            if (!tp.setShip(m, n)) {
                return false;
            }


            m = y + i * dy - dx;
            n = x + i * dx - dy;
            if (!tp.setBorder(m, n)) {
                return false;
            }
            m = y + i * dy + dx;
            n = x + i * dx + dy;
            if (!tp.setBorder(m, n)) {
                return false;
            }
        }
        for (i = -1; i < 2; i++) {
            m = y + i * dx - dy;
            n = x + i * dy - dx;
            if (!tp.setBorder(m, n)) {
                return false;
            }
            m = y + i * dx + (dy * size);
            n = x + i * dy + (dx * size);
            if (!tp.setBorder(m, n)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPlace() {
        return byPass(new PlaceShipCheck(this));
    }

    private void setShip() {
        byPass(new PlaceShipSet(this));
    }

    public int doShot() {
        if (health != 0) {
            health--;
            if (health == 0) {
                getField().setNumLiveShips(getField().getNumLiveShips() - 1);
                state = Ship.SHIP_KILLED;
                for (CellClass e : listCells) {
                    e.setState(CellClass.CELL_KILLED);
                }
                for (CellClass e : listBorders) {
                    e.setState(CellClass.CELL_MISSED);
                    e.setMark(true);
                }
                return FieldShip.SHUT_KILLED;
            } else {
                state = SHIP_INJURED;
            }
        }
        return FieldShip.SHUT_INJURED;
    }

    public int getSize() {
        return size;
    }

    public int getState() {
        return state;
    }

    public FieldShip getField() {
        return field;
    }

}