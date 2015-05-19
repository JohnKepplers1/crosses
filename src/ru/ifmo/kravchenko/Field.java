package ru.ifmo.kravchenko;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Field implements ModelInterface  {
    private Cell.Type[][] field;
    private int n;
    private int jconst = 0;
    private int iconst = 0;
    private int five1 = 0;
    private int five2 = 0;
    private Collection<Integer[]> winItems;

    Field(int n) {
        this.n = n;
        field = new Cell.Type[n][n];
        winItems = new ArrayList<Integer[]>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = Cell.Type.Null;
            }
        }
    }
    public int getJconst(){
        return jconst;
    }
    public int getIconst(){
        return iconst;
    }

    public void setItemAt(Cell.Type value, int i, int j) {

      /* if (!(i < exemplar.getSize() && j < exemplar.getSize()))
            throw new IllegalArgumentException(
                    "Неверное значение индекса. Допустимо значение от 0 до "
                            + String.valueOf(exemplar.getSize() - 1));*/

        field[i][j] = value;
    }

    public void setItemAt1(boolean trushin) {
        Random random = new Random();

        while (true) // цикл хода
        {
            int j = random.nextInt(Window.getSizeOfField());
            int i = random.nextInt(Window.getSizeOfField());

            if (field[i][j] == Cell.Type.Null) {
                iconst = i;
                jconst = j;
                if (trushin == true) {

                    field[i][j] = Cell.Type.O;
                } else {
                    field[i][j] = Cell.Type.X;
                }
                break;
            }
        }

    }

    public Cell.Type getItemAt(int i, int j) {
        return (i < n && j < n) ? field[i][j] : null;
    }

    public void myMethod(boolean trushin, int i, int j) {
        if (trushin == false) {

            field[i][j] = Cell.Type.O;
        } else {
            field[i][j] = Cell.Type.X;
        }
    }

    public void myAnotherMethod(int i, int j) {
        field[i][j] = Cell.Type.Null;
    }

    public Cell.Type getNextWinner(int winCount) {

        if (winCount > Window.getSizeOfField())
            throw new IllegalArgumentException("Длина выигрышной линии не может быть больше размера доски.");

        Cell.Type result = null;

        for (int i = 0; i < Window.getSizeOfField(); i++)
            for (int j = 0; j < Window.getSizeOfField(); j++) {
                result = findWin(i, j, winCount);
                if (result != null)
                    return result;
            }

        return result;
    }


    private Cell.Type findWin(int i, int j, int winCount) {

        if (!field[i][j].equals(Cell.Type.X) && !field[i][j].equals(Cell.Type.O))
            return null;

        int cnti = i;
        int cntj = j;

        Cell.Type result = null;

        winItems.clear();
        //поиск влево
        while (cntj >= 0) {
            result = searchAndReplace(i, j, i, cntj, winCount);
            cntj--;

            if (result != null && !result.equals(Cell.Type.Null))
                return result;
            else if (result == null)
                break;
        }


        cnti = i;
        winItems.clear();
        //Поиск вниз.
        while (cnti < Window.getSizeOfField()) {
            result = searchAndReplace(i, j, cnti, j, winCount);
            cnti++;

            if (result != null && !result.equals(Cell.Type.Null))
                return result;
            else if (result == null)
                break;

        }
        //Поиск по диагоналям.
        cnti = i;
        cntj = j;
        winItems.clear();
        while (cnti < Window.getSizeOfField() && cntj >= 0) {

            result = searchAndReplace(i, j, cnti, cntj, winCount);
            cnti++;
            cntj--;

            if (result != null && !result.equals(Cell.Type.Null))
                return result;
            else if (result == null)
                break;

        }
        cnti = i;
        cntj = j;
        winItems.clear();
        while (cnti < Window.getSizeOfField() && cntj < Window.getSizeOfField()) {
            result = searchAndReplace(i, j, cnti, cntj, winCount);
            cnti++;
            cntj++;

            if (result != null && !result.equals(Cell.Type.Null))
                return result;
            else if (result == null)
                break;

        }

        return null;
    }


    public int size() {
        return n;
    }


    private Cell.Type searchAndReplace(int i, int j, int cnti, int cntj,
                                       int winCount) {

        Cell.Type item = field[cnti][cntj];

        if (item.equals(field[i][j])) {

            //Список для возможной выигрышной комбинации. Добавляем индекс элемента.
            winItems.add(new Integer[]{cnti, cntj});

            //Комбинация составлена.
            if (winItems.size() == winCount) {
                //Отмечаем выигрышные элементы.
                for (Integer[] buff : winItems) {

                    field[buff[0]][buff[1]] = field[buff[0]][buff[1]].equals(Cell.Type.X) ?
                            Cell.Type.X
                            : Cell.Type.O;
                }
                return item;
            }
        } else

            return null;


        return Cell.Type.Null;

    }


}
