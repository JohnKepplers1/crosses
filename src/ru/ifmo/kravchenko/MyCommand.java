package ru.ifmo.kravchenko;

public class MyCommand implements Command {
    private int counter = 0;

    public void execute(int i, int j) {
        Window.getBoard().setItemAt(Window.getMove() ? Cell.Type.X : Cell.Type.O, i, j);
        counter++;

    }
    public void undo(int i, int j){
        Window.getBoard().setItemAt(Cell.Type.Null, i, j);
    }
    public int getCounter(){
        System.out.println(counter);
        return counter;

    }
}
