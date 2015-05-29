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
    private int it = 0;
    private int y = 0;
    private int[] ar = new int[10];
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

    public void setSymbolComp(boolean trushin) {
        Random random = new Random();
       /* if (controller == 1) {
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


        } else {*/
        if (Window.getSizeOfField() == 17) {
        } else {
            if ((Window.getSizeOfField() == 3) && (Window.getWinCount() == 3)) {
                for (int i = 0; i < 3; i++) {
                    if ((field[0][i] == field[1][i]) && (field[0][i] == Cell.Type.O) && (field[2][i] == Cell.Type.Null)) {
                        field[2][i] = Cell.Type.O;
                        controller = 2;
                        break;

                    }
                    if ((field[0][i] == field[2][i]) && (field[0][i] == Cell.Type.O) && (field[1][i] == Cell.Type.Null)) {
                        field[1][i] = Cell.Type.O;
                        controller = 2;
                        break;
                    }
                    if ((field[1][i] == field[2][i]) && (field[1][i] == Cell.Type.O) && (field[0][i] == Cell.Type.Null)) {
                        field[0][i] = Cell.Type.O;
                        controller = 2;
                        break;
                    }
                }
                if (controller != 2) {
                    for (int i = 0; i < 3; i++) {
                        if ((field[i][0] == field[i][1]) && (field[i][0] == Cell.Type.O) && (field[i][2] == Cell.Type.Null)) {
                            field[i][2] = Cell.Type.O;
                            controller = 2;
                            break;

                        }
                        if ((field[i][0] == field[i][2]) && (field[i][0] == Cell.Type.O) && (field[i][1] == Cell.Type.Null)) {
                            field[1][i] = Cell.Type.O;
                            controller = 2;
                            break;
                        }
                        if ((field[i][1] == field[i][2]) && (field[i][1] == Cell.Type.O) && (field[i][0] == Cell.Type.Null)) {
                            field[i][0] = Cell.Type.O;
                            controller = 2;
                            break;
                        }
                    }
                }
                if (controller != 2) {
                    if ((field[0][0] == field[1][1]) && (field[0][0] == Cell.Type.O) && (field[2][2] == Cell.Type.Null)) {
                        field[2][2] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[0][0] == field[2][2]) && (field[0][0] == Cell.Type.O) && (field[1][1] == Cell.Type.Null)) {
                        field[1][1] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[1][1] == field[2][2]) && (field[1][1] == Cell.Type.O) && (field[0][0] == Cell.Type.Null)) {
                        field[0][0] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[2][0] == field[1][1]) && (field[2][0] == Cell.Type.O) && (field[0][2] == Cell.Type.Null)) {
                        field[0][2] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[2][0] == field[0][2]) && (field[2][0] == Cell.Type.O) && (field[1][1] == Cell.Type.Null)) {
                        field[1][1] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[1][1] == field[0][2]) && (field[1][1] == Cell.Type.O) && (field[2][0] == Cell.Type.Null)) {
                        field[2][0] = Cell.Type.O;
                        controller = 2;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    if ((field[0][i] == field[1][i]) && (field[0][i] == Cell.Type.X) && (field[2][i] == Cell.Type.Null)) {
                        field[2][i] = Cell.Type.O;
                        controller = 2;
                        break;

                    }
                    if ((field[0][i] == field[2][i]) && (field[0][i] == Cell.Type.X) && (field[1][i] == Cell.Type.Null)) {
                        field[1][i] = Cell.Type.O;
                        controller = 2;
                        break;
                    }
                    if ((field[1][i] == field[2][i]) && (field[1][i] == Cell.Type.X) && (field[0][i] == Cell.Type.Null)) {
                        field[0][i] = Cell.Type.O;
                        controller = 2;
                        break;
                    }
                }
                if (controller != 2) {
                    for (int i = 0; i < 3; i++) {
                        if ((field[i][0] == field[i][1]) && (field[i][0] == Cell.Type.X) && (field[i][2] == Cell.Type.Null)) {
                            field[i][2] = Cell.Type.O;
                            controller = 2;
                            break;

                        }
                        if ((field[i][0] == field[i][2]) && (field[i][0] == Cell.Type.X) && (field[i][1] == Cell.Type.Null)) {
                            field[1][i] = Cell.Type.O;
                            controller = 2;
                            break;
                        }
                        if ((field[i][1] == field[i][2]) && (field[i][1] == Cell.Type.X) && (field[i][0] == Cell.Type.Null)) {
                            field[i][0] = Cell.Type.O;
                            controller = 2;
                            break;
                        }
                    }
                }
                if (controller != 2) {
                    if ((field[0][0] == field[1][1]) && (field[0][0] == Cell.Type.X) && (field[2][2] == Cell.Type.Null)) {
                        field[2][2] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[0][0] == field[2][2]) && (field[0][0] == Cell.Type.X) && (field[1][1] == Cell.Type.Null)) {
                        field[1][1] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[1][1] == field[2][2]) && (field[1][1] == Cell.Type.X) && (field[0][0] == Cell.Type.Null)) {
                        field[0][0] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[2][0] == field[1][1]) && (field[2][0] == Cell.Type.X) && (field[0][2] == Cell.Type.Null)) {
                        field[0][2] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[2][0] == field[0][2]) && (field[2][0] == Cell.Type.X) && (field[1][1] == Cell.Type.Null)) {
                        field[1][1] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if ((field[1][1] == field[0][2]) && (field[1][1] == Cell.Type.X) && (field[2][0] == Cell.Type.Null)) {
                        field[2][0] = Cell.Type.O;
                        controller = 2;
                    }
                }
                if (controller != 2) {
                    if (it == 0) {
                        if (field[1][1] == Cell.Type.X) {
                            y = 1;
                        }
                    }
                }
                if ((y == 1) && (controller != 2)) {
                    if (field[0][2] == Cell.Type.Null) {
                        field[0][2] = Cell.Type.O;
                    } else if (field[2][0] == Cell.Type.Null) {
                        field[2][0] = Cell.Type.O;
                    } else if (field[0][0] == Cell.Type.Null) {
                        field[0][0] = Cell.Type.O;
                    } else if (field[2][2] == Cell.Type.Null) {
                        field[2][2] = Cell.Type.O;
                    } else {
                        while (true) // цикл хода
                        {
                            int j = random.nextInt(Window.getSizeOfField());
                            int i = random.nextInt(Window.getSizeOfField());
                            if (field[i][j] == Cell.Type.Null) {
                                field[i][j] = Cell.Type.O;
                            }
                        }

                    }
                    controller = 2;
                }
                if (controller != 2) {
                    if (it == 0) {
                        if (field[2][2] == Cell.Type.X) {
                            field[1][1] = Cell.Type.O;
                            y = 2;
                            ar[0] = 2;
                            ar[1] = 2;

                        }
                        if (field[0][2] == Cell.Type.X) {
                            field[1][1] = Cell.Type.O;
                            y = 2;
                            ar[0] = 0;
                            ar[1] = 2;

                        }
                        if (field[2][0] == Cell.Type.X) {
                            field[1][1] = Cell.Type.O;
                            y = 2;
                            ar[0] = 2;
                            ar[1] = 0;

                        }
                        if (field[0][0] == Cell.Type.X) {
                            field[1][1] = Cell.Type.O;
                            y = 2;
                            ar[0] = 0;
                            ar[1] = 0;

                        }
                    }
                }
                if ((it == 1) && (controller != 2) && (y == 2)) {
                    if ((ar[0] == 0) && (ar[1] == 0)) {
                        if (field[2][2] == Cell.Type.Null) {
                            field[2][2] = Cell.Type.O;
                            y = 3;
                        }
                    }
                    if (y != 3) {
                        if ((ar[0] == 2) && (ar[1] == 0)) {
                            if (field[0][2] == Cell.Type.Null) {
                                field[0][2] = Cell.Type.O;
                                y = 3;
                            }
                        }
                    }
                    if (y != 3) {
                        if ((ar[0] == 0) && (ar[1] == 2)) {
                            if (field[2][0] == Cell.Type.Null) {
                                field[2][0] = Cell.Type.O;
                                y = 3;
                            }
                        }
                    }
                    if (y != 3) {
                        if ((ar[0] == 2) && (ar[1] == 2)) {
                            if (field[0][0] == Cell.Type.Null) {
                                field[0][0] = Cell.Type.O;
                                y = 3;
                            }
                        }
                    }
                    if (y != 3) {
                        if (field[1][0] == Cell.Type.Null) {
                            field[1][0] = Cell.Type.O;
                            y = 3;
                        }
                    }
                    if (y != 3) {
                        if (field[0][1] == Cell.Type.Null) {
                            field[0][1] = Cell.Type.O;
                            y = 3;
                        }
                    }
                    if (y != 3) {
                        if (field[1][2] == Cell.Type.Null) {
                            field[1][2] = Cell.Type.O;
                            y = 3;
                        }
                    }
                    if (y != 3) {
                        if (field[2][1] == Cell.Type.Null) {
                            field[2][1] = Cell.Type.O;
                            y = 3;
                        }
                    }
                }
                if ((controller != 2) && (it == 0)) {
                    if (field[1][0] == Cell.Type.X) {
                        ar[0] = 0;
                        ar[1] = 1;
                        ar[2] = 0;
                        ar[3] = 1;
                        ar[4] = 2;
                        ar[5] = 1;
                        ar[6] = 0;
                        ar[7] = 0;
                        ar[8] = 2;
                        ar[9] = 0;
                        field[1][1] = Cell.Type.O;
                        y = 4;
                    }
                    if (field[0][1] == Cell.Type.X) {
                        ar[0] = 1;
                        ar[1] = 0;
                        ar[2] = 1;
                        ar[3] = 0;
                        ar[4] = 1;
                        ar[5] = 2;
                        ar[6] = 0;
                        ar[7] = 0;
                        ar[8] = 0;
                        ar[9] = 2;
                        field[1][1] = Cell.Type.O;
                        y = 4;

                    }
                    if (field[1][2] == Cell.Type.X) {
                        ar[0] = 2;
                        ar[1] = 1;
                        ar[2] = 0;
                        ar[3] = 1;
                        ar[4] = 2;
                        ar[5] = 1;
                        ar[6] = 0;
                        ar[7] = 2;
                        ar[8] = 2;
                        ar[9] = 2;
                        field[1][1] = Cell.Type.O;
                        y = 4;
                    }
                    if (field[2][1] == Cell.Type.X) {
                        ar[0] = 1;
                        ar[1] = 2;
                        ar[2] = 1;
                        ar[3] = 0;
                        ar[4] = 1;
                        ar[5] = 2;
                        ar[6] = 2;
                        ar[7] = 0;
                        ar[8] = 2;
                        ar[9] = 2;
                        field[1][1] = Cell.Type.O;
                        y = 4;
                    }
                }
                if ((controller != 2) && (it == 1) && (y == 4)) {
                    if (field[0][0] == Cell.Type.X) {
                        field[2][2] = Cell.Type.O;
                    }
                    if (field[0][2] == Cell.Type.X) {
                        field[2][0] = Cell.Type.O;
                    }
                    if (field[2][0] == Cell.Type.X) {
                        field[0][2] = Cell.Type.O;
                    }
                    if (field[2][2] == Cell.Type.X) {
                        field[0][0] = Cell.Type.O;
                    }
                    if (field[ar[0]][ar[1]] == Cell.Type.X) {
                        field[0][0] = Cell.Type.O;
                        y++;
                    }
                    if (y == 4) {
                        if (field[ar[2]][ar[3]] == Cell.Type.X) {
                            field[ar[6]][ar[7]] = Cell.Type.O;
                            y++;
                        }
                        if (y == 4) {
                        }
                        if (field[ar[3]][ar[4]] == Cell.Type.X) {
                            field[ar[8]][ar[9]] = Cell.Type.O;
                        }
                    }
                    controller = 2;
                }


                controller = 0;
                it++;

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
