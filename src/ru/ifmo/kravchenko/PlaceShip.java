package ru.ifmo.kravchenko;


public abstract class PlaceShip {

    public FieldShip field;

    public PlaceShip(Ship ship) {
        this.field = ship.getField();
    }

    protected FieldShip getField() {
        return field;
    }

    abstract public boolean setShip(int x, int y);
    abstract public boolean setBorder(int x, int y);
}
