package ru.ifmo.kravchenko;

// тип для ячейки
public class Cell {
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
