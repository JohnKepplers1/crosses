package ru.ifmo.kravchenko;

public class SmartComputerForField3X3 implements Player {
    int i = 1;
    int a = 0;
    int b = 0;
    private Beginner exemplar = new Beginner();
    private String playerName = "RandomPlayer";
    private Cell.Type playerType;

    SmartComputerForField3X3(Cell.Type type) {

        if (exemplar.chooseSymbol().equals("X")) {
            playerType = Cell.Type.O;

        } else {
            playerType = Cell.Type.X;
        }
    }

    public Cell.Type getType() {
        return playerType;
    }

    public String getPlayerName() {
        return "SmartComputerForField3X3";
    }

    public Cell getTurn(Field field) {
        i++;
        if (playerType == Cell.Type.X) {
            if (i == 1) {
                a = 1;
                b = 1;
                i++;
            }
            if (i == 3) {

            }

        }


        return new Cell(a, b, playerType);


    }
}



