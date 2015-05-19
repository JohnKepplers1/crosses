package ru.ifmo.kravchenko;

import java.util.ArrayList;;


public class AIRandom extends AIBase {

    public AIRandom(AI ai) {
        super(ai);
    }

    public int doShot() {
        ArrayList<CellClass> list = new ArrayList<CellClass>();
        for (int j = 0; j < ai.getField().getWidth(); j++) {
            for (int i = 0; i < ai.getField().getHeight(); i++) {
                CellClass e = ai.getField().getCell(i, j);
                if (! e.isMark() ) {
                    list.add(e);
                }
            }
        }
        if (list.size() == 0) {
            return FieldShip.SHUT_MISSED;
        }
        CellClass cell = list.get(ai.rand.nextInt(list.size()));
        int shot = cell.doShot();
        if (shot == FieldShip.SHUT_INJURED) {
            ai.action = new AIPlace(ai);
            ai.action.setPosition(cell.x, cell.y);
        }
        return shot;
    }

}