package ru.ifmo.kravchenko;

public abstract class Game implements GameInterface {
    protected final Player firstPlayer;
    protected final Player secondPlayer;
    protected final Field gameField;
    protected final int fieldSize;

    protected Game(Player first, Player second, int size) {

        firstPlayer = first;
        secondPlayer = second;
        fieldSize = size;
        gameField = new Field(size);
    }

    public void startGame() {
        Player currentPlayer = firstPlayer;
        Cell turn;
        while (true) {
            gameField.printField();

            turn = currentPlayer.getTurn(gameField);


                gameField.setCell(turn);


            // проверка на победителя
            if (gameField.isWinner(turn)) {
                gameField.printField();
                System.out.println(currentPlayer.getPlayerName() + "(" + currentPlayer.getType() + ") одержал победу!");
                return;
            }

            // проверка на ничью
            if (gameField.TheEnd()) {
                gameField.printField();
                System.out.println("Ничья.");
                return;
            }

            // смена хода
            if (currentPlayer == firstPlayer)
                currentPlayer = secondPlayer;
            else
                currentPlayer = firstPlayer;

        }

    }


}

