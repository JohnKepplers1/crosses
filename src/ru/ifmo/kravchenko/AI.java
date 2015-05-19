package ru.ifmo.kravchenko;

import java.util.Random;


public class AI {

    public FieldShip field;
    public AIBase action;
    public Random rand;


    public AI(FieldShip field) {
        this.rand = new Random();
        this.field = field;
        this.action = new AIRandom(this);
    }

    public int doShot() {
        return action.doShot();
    }

    public FieldShip getField() {
        return field;
    }

}