package ru.ifmo.kravchenko;

public class MyCommand implements Command {

    public void execute(int i, int j) {
        Window.getBoard().setSymbol(Window.getMove() ? Cell.Type.X : Cell.Type.O, i, j);

    }

    public void undo(int i, int j) {
        Window.getBoard().setSymbol(Cell.Type.Null, i, j);
    }
}
