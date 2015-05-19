package ru.ifmo.kravchenko;

import java.awt.Color;


public class PanelFieldPlayer extends PanelField {

    public PanelFieldPlayer(FieldShip field) {
        super(field);
    }

    @Override
    protected Color getColorByStateElement(int state) {
        switch (state) {
            case CellClass.CELL_BORDER:
                return new Color(215, 215, 255);
            case CellClass.CELL_WATER:
                return new Color(225, 225, 255);
            case CellClass.CELL_WELL:
                return Color.green;
            case CellClass.CELL_INJURED:
                return Color.red;
            case CellClass.CELL_KILLED:
                return Color.gray;
            case CellClass.CELL_MISSED:
                return Color.black;
        }

        return Color.blue;
    }

}