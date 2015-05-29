package ru.ifmo.kravchenko;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Field implements ModelInterface {
    int[][] a = new int[Window.getSizeOfField()][Window.getSizeOfField()];
    private Cell.Type[][] field;
    private int size;
    private int jconst = 0;
    private int iconst = 0;
    private int iterator = 0;
    private int controller = 0;
    private Collection<Integer[]> winItems;

    Field(int size) {
        this.size = size;
        field = new Cell.Type[size][size];
        winItems = new ArrayList<Integer[]>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = Cell.Type.Null;
            }
        }
    }

    public int getJconst() {
        return jconst;
    }

    public int getIconst() {
        return iconst;
    }

    public void setSymbol(Cell.Type value, int i, int j) {
        field[i][j] = value;
    }

    public void setSymbolByRandomComputer(boolean trushin) {
        Random random = new Random();
        if (controller == 1) {
            controller = 0;
            int k;
            int m;
            int maxvalue = 0;
            int minvalue = 0;
            int max = 0;
            int stroka = 0;
            int min = Window.getWinCount();
            for (k = 0; k < Window.getSizeOfField(); k++) {
                for (m = 0; m < Window.getSizeOfField(); m++) {
                    if (a[k][m] != 0) {
                        stroka = k;
                    }
                    if (a[k][m] < min) {
                        min = a[k][m];
                        minvalue = m;
                    }
                    if (a[k][m] > max) {
                        max = a[k][m];
                        maxvalue = m;
                    }

                }
            }


            if (trushin == true) {

                field[stroka][minvalue - 1] = Cell.Type.O;
            } else {
                field[stroka][minvalue - 1] = Cell.Type.X;
            }


        } else {

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
    }

    public Cell.Type getSymbol(int i, int j) {
        return (i < size && j < size) ? field[i][j] : null;
    }

    public void myMethod(boolean trushin, int i, int j) {
        if (trushin == false) {

            field[i][j] = Cell.Type.O;
        } else {
            field[i][j] = Cell.Type.X;
        }
    }

    public void setNull(int i, int j) {
        field[i][j] = Cell.Type.Null;
    }

    private void getWin(int winCount) {
        if (winCount > Window.getSizeOfField())
            throw new IllegalArgumentException("Длина выигрышной линии не может быть больше размера доски.");

        Cell.Type result = null;

        for (int i = 0; i < Window.getSizeOfField(); i++) {
            for (int j = 0; j < Window.getSizeOfField(); j++) {
                result = searchWinner(i, j, winCount);
                if (result != null) {
                    iterator++;
                    a[i][j] = iterator;
                    if (iterator == Window.getWinCount() - 2) {
                        controller = 1;
                        for (int k = 0; k < Window.getSizeOfField(); k++) {
                            for (int m = 0; m < Window.getSizeOfField(); m++) {
                                System.out.println(a[k][m]);
                            }
                        }
                    }

                }
            }
        }

    }

    public Cell.Type getWinner(int winCount) {
        if (winCount > Window.getSizeOfField())
            throw new IllegalArgumentException("Длина выигрышной линии не может быть больше размера доски.");

        Cell.Type result = null;

        for (int i = 0; i < Window.getSizeOfField(); i++)
            for (int j = 0; j < Window.getSizeOfField(); j++) {
                result = findWinner(i, j, winCount);
                if (result != null)
                    return result;
            }

        return result;
    }

    private Cell.Type searchWinner(int i, int j, int winCount) {
        if (!field[i][j].equals(Cell.Type.X) && !field[i][j].equals(Cell.Type.O))
            return null;

        int cnti;
        int cntj = j;

        Cell.Type result = null;

        winItems.clear();
        //поиск влево
        while (cntj >= 0) {
            result = findWin(i, j, i, cntj, winCount);
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
            result = searchWin(i, j, cnti, j, winCount);
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

            result = searchWin(i, j, cnti, cntj, winCount);
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
            result = searchWin(i, j, cnti, cntj, winCount);
            cnti++;
            cntj++;

            if (result != null && !result.equals(Cell.Type.Null))
                return result;
            else if (result == null)
                break;

        }

        return null;
    }


    private Cell.Type findWinner(int i, int j, int winCount) {

        if (!field[i][j].equals(Cell.Type.X) && !field[i][j].equals(Cell.Type.O))
            return null;

        int cnti;
        int cntj = j;

        Cell.Type result = null;

        winItems.clear();
        //поиск влево
        while (cntj >= 0) {
            result = searchWin(i, j, i, cntj, winCount);
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
            result = searchWin(i, j, cnti, j, winCount);
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

            result = searchWin(i, j, cnti, cntj, winCount);
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
            result = searchWin(i, j, cnti, cntj, winCount);
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
        return size;
    }

    private Cell.Type findWin(int i, int j, int cnti, int cntj, int winCount) {
        Cell.Type item = field[cnti][cntj];


        if (item.equals(field[i][j])) {

            //Список для возможной выигрышной комбинации. Добавляем индекс элемента.
            winItems.add(new Integer[]{cnti, cntj});

            //Комбинация составлена.
            if (winItems.size() == winCount - 2) {
                //Отмечаем выигрышные элементы.
                for (Integer[] buff : winItems) {

                    field[buff[0]][buff[1]] = field[buff[0]][buff[1]].equals(Cell.Type.X) ? Cell.Type.X : Cell.Type.O;
                }
                return item;
            }
        } else

            return null;
        return Cell.Type.Null;
    }


    private Cell.Type searchWin(int i, int j, int cnti, int cntj, int winCount) {

        Cell.Type item = field[cnti][cntj];


        if (item.equals(field[i][j])) {

            //Список для возможной выигрышной комбинации. Добавляем индекс элемента.
            winItems.add(new Integer[]{cnti, cntj});

            //Комбинация составлена.
            if (winItems.size() == winCount) {
                //Отмечаем выигрышные элементы.
                for (Integer[] buff : winItems) {

                    field[buff[0]][buff[1]] = field[buff[0]][buff[1]].equals(Cell.Type.X) ? Cell.Type.X : Cell.Type.O;
                }
                return item;
            }
        } else

            return null;


        return Cell.Type.Null;

    }


}
