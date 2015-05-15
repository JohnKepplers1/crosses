package ru.ifmo.kravchenko;

public interface Command {
    void execute(int i, int j);

    void undo(int i, int j);
}
