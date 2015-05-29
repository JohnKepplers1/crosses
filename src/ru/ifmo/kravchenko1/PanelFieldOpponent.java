package ru.ifmo.kravchenko1;

import java.awt.Color;


public class PanelFieldOpponent extends PanelField {

    public PanelFieldOpponent(FieldShip field) {
        super(field);
    }

    protected Color getColorByStateElement(int state) {
        switch (state) {
            case CellClass.CELL_BORDER:
                return new Color(225, 225, 255);
            case CellClass.CELL_WATER:
                return new Color(225, 225, 255);
            case CellClass.CELL_WELL:
                return new Color(225, 225, 255);
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