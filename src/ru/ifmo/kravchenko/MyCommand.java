package ru.ifmo.kravchenko;

public class MyCommand implements Command {

    private Human human;

    public void execute(int i, int j) {
        Window.getBoard().setItemAt(Window.getMove() ? Cell.Type.X : Cell.Type.O, i, j);

    }
}
