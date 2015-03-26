package ru.ifmo.kravchenko;

// тип для ячейки
public class Cell {

    public int i;
    public int j;
    public Type type;

    Cell(int i, int j, Type type) {
        this.i = i;
        this.j = j;
        this.type = type;
    }

    public enum Type {
        Null(""),
        X("X"),
        O("0"),;

        private String name;

        private Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
