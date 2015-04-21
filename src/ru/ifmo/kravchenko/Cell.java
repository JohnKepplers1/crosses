package ru.ifmo.kravchenko;

// тип для ячейки
public class Cell {

    private int i;
    private  int j;
    private Type type;
    public int get1(){
        return i;
    }
    public int get2(){
        return j;
    }
    public Type get3(){
        return type;
    }

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
