package ru.ifmo.kravchenko;


import java.util.ArrayList;


public class AIDirection extends AIBase {

    public AIDirection(AI ai) {
        super(ai);
    }


    public void draw(ArrayList<CellClass> list, int i, int j) {
        int m = x;
        int n = y;
        do {
            m+= i;
            n+= j;
        } while ( ai.getField().isBound(m, n) && (ai.getField().getCell(m, n).getState() == CellClass.CELL_INJURED) );

        if (ai.getField().isBound(m, n) ) {
            CellClass e = ai.getField().getCell(m, n);
            if (! e.isMark() ) {
                list.add(e);
            }
        }
    }

    @Override
    public int doShot() {
        ArrayList<CellClass> list = new ArrayList<CellClass>();
        draw(list, dx, dy);
        draw(list, -dx, -dy);

        if (list.size() > 0) {
            return list.get(ai.rand.nextInt(list.size())).doShot();
        }

        ai.action = new AIRandom(ai);
        return ai.doShot();
    }

}