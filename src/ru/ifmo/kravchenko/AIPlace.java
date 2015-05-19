package ru.ifmo.kravchenko;

import java.util.ArrayList;

public class AIPlace extends AIBase {

    public AIPlace(AI ai) {
        super(ai);
    }

    @Override
    public int doShot() {
        int m, n;
        ArrayList<CellClass> list = new ArrayList<CellClass>();
        for(int i = 0; i < 2; i++) {
            m = x + i * 2 - 1;
            n = y;
            if (ai.getField().isBound(m, n) ) {
                CellClass e = ai.getField().getCell(m, n);
                if (! e.isMark() ) {
                    list.add(e);
                }
            }

            m = x;
            n = y + i * 2 - 1;
            if (ai.getField().isBound(m, n) ) {
                CellClass e = ai.getField().getCell(m, n);
                if (! e.isMark() ) {
                    list.add(e);
                }
            }
        }

        if (list.size() > 0) {
            CellClass e = list.get(ai.rand.nextInt(list.size()));
            int shot = e.doShot();
            if (  shot == FieldShip.SHUT_INJURED ) {
                ai.action = new AIDirection(ai);
                ai.action.setPosition(x, y);
                ai.action.setDirection(e.x - x, e.y - y);
            }
            return shot;
        }

        ai.action = new AIRandom(ai);
        return ai.doShot();
    }

}