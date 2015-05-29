package ru.ifmo.kravchenko1;


public class PlaceShipSet extends PlaceShip {

    private Ship ship;

    public PlaceShipSet(Ship ship) {
        super(ship);
        this.ship = ship;
    }

    @Override
    public boolean setShip(int x, int y) {
        getField().getCell(x, y).setState(CellClass.CELL_WELL);
        ship.getListCells().add(getField().getCell(x, y));
        getField().getCell(x, y).setShip(ship);
        return true;
    }

    @Override
    public boolean setBorder(int x, int y) {
        if ( getField().isBound(x, y) ) {
            getField().getCell(x, y).setState(CellClass.CELL_BORDER);
            ship.getListBorders().add(getField().getCell(x, y));
        }
        return true;
    }
}