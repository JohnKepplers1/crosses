package ru.ifmo.kravchenko;

public class TickTackToe extends Game {

    final Field gameField;
    final private String gameName = "Tick-tack-toe";


    TickTackToe(Player first, Player second, int size) {
      super(first, second, size);
        gameField = new Field(size);
    }

    public String getGameName() {
        return "Tick-tack-toe";
    }
}


