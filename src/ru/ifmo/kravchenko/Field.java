package ru.ifmo.kravchenko;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Field {
    private final Beginner exemplar = new Beginner();
    private Cell.Type[][] field;
    private int n;
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

    public void setItemAt(Cell.Type value, int i, int j) {

      /* if (!(i < exemplar.getSize() && j < exemplar.getSize()))
            throw new IllegalArgumentException(
                    "Неверное значение индекса. Допустимо значение от 0 до "
                            + String.valueOf(exemplar.getSize() - 1));*/

        field[i][j] = value;
    }

    public void setItemAt1() {
        Random random = new Random();

        while (true) // цикл хода
        {
            int j = random.nextInt(exemplar.getSize());
            int i = random.nextInt(exemplar.getSize());

            if (field[i][j] == Cell.Type.Null) {

                field[i][j] = Cell.Type.O;
                break;
            }
        }

    }

    public Cell.Type getItemAt(int i, int j) {
        return (i < n && j < n) ? field[i][j] : null;
    }

    public Cell.Type getNextWinner(int winCount) {

        if (winCount > exemplar.getSize())
            throw new IllegalArgumentException("Длина выигрышной линии не может быть больше размера доски");

        Cell.Type result = null;

        for (int i = 0; i < exemplar.getSize(); i++)
            for (int j = 0; j < exemplar.getSize(); j++) {
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
        //поиск вниз
        while (cnti < exemplar.getSize()) {
            result = searchAndReplace(i, j, cnti, j, winCount);
            cnti++;

            if (result != null && !result.equals(Cell.Type.Null))
                return result;
            else if (result == null)
                break;

        }
        //поиск по диагоналям
        cnti = i;
        cntj = j;
        winItems.clear();
        while (cnti < exemplar.getSize() && cntj >= 0) {

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
        while (cnti < exemplar.getSize() && cntj < exemplar.getSize()) {
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


    //Получение значения ячейки.
    public void setCell(Cell cell) {
        field[cell.i][cell.j] = cell.type;
    }

    public int size() {
        return n;
    }

    // возвращает значение ячейки.
    Cell.Type cellType(int i, int j) {
        return field[i][j];
    }

    private Cell.Type searchAndReplace(int i, int j, int cnti, int cntj,
                                       int winCount) {

        Cell.Type item = field[cnti][cntj];

        if (item.equals(field[i][j])) {

            //список для возможной выигрышной комбинации - добавляем индекс элемента
            winItems.add(new Integer[]{cnti, cntj});

            //комбинация составлена
            if (winItems.size() == winCount) {
                // отмечаем выигрышные элементы
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


    // проверка на выигрышную комбинацию (пять в ряд).
    boolean isWinner(Cell cell) {
        Main detector = new Main();
        if (detector.getDetector() == 1) {
            for (int i = 0; i < n; i++) {
                if ((five1 == 5) || (five2 == 5)) {
                    break;
                }
                five1 = 0;
                five2 = 0;
                for (int j = 0; j < n; j++) {
                    if (field[j][i] == cell.type) {
                        five1++;
                    } else {
                        five1 = 0;
                    }
                    if (field[i][j] == cell.type) {
                        five2++;
                    } else {
                        five2 = 0;
                    }
                    if ((five1 == 5) || (five2 == 5)) {
                        return true;
                    }
                    if ((five1 == 5) || (five2 == 5)) {
                        break;
                    }
                }
            }
            int z = 4;
            five1 = 0;
            five2 = 0;
            int five3 = 0;
            int five4 = 0;
            int k = 4;
            for (int i = k; i < n; i++) {
                if ((five1 == 5) || (five2 == 5) || (five3 == 5) || (five4 == 5)) {
                    break;
                }
                five1 = 0;
                five2 = 0;
                five3 = 0;
                five4 = 0;
                z = i;
                for (int j = 0; j < n; j++) {
                    if (field[z][j] == cell.type) {
                        five1++;
                    } else {
                        five1 = 0;
                    }
                    if (field[n - 1 - j][n - 1 - z] == cell.type) {
                        five2++;
                    } else {
                        five2 = 0;
                    }
                    if (field[n - 1 - j][z] == cell.type) {
                        five3++;
                    } else {
                        five3 = 0;
                    }
                    if (field[z][n - 1 - z] == cell.type) {
                        five4++;
                    } else {
                        five4 = 0;
                    }
                    z--;
                    if ((five1 == 5) || (five2 == 5) || (five3 == 5) || (five4 == 5)) {
                        return true;
                    }
                    if ((five1 == 5) || (five2 == 5) || (z < 0) || (five3 == 5) || (five4 == 5)) {
                        break;
                    }
                }


            }
            k = n;
            for (int i = k; i < n - 1; i++) {
                if (five1 == 5) {
                    break;
                }
                five1 = 0;
                for (int j = 0; j < k + 1; j++) {
                    if (field[z][j] == cell.type) {
                        five1++;
                        z--;
                    }
                }
                z = i;


                if (five1 == 5)
                    return true;
            }


            for (int i = n - 1; i < n; i--) {
                if (field[i][n - 1 - i] != cell.type)
                    break;
                if (i == 0)
                    return true;
            }
        }
        if (detector.getDetector() == 2) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (field[i][j] != cell.type) {
                        break;
                    }

                    if (j == n - 1) {
                        return true;
                    }
                }

                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (field[k][j] != cell.type)
                            break;
                        if (k == n - 1)
                            return true;
                    }
                }
            }

            if (cell.i == cell.j) {
                for (int i = 0; i < n; i++) {
                    if (field[i][i] != cell.type)
                        break;
                    if (i == n - 1)
                        return true;
                }
            }

            for (int i = n - 1; i < n; i--) {
                if (field[i][n - 1 - i] != cell.type)
                    break;
                if (i == 0)
                    return true;
            }


        }
        return false;

    }    //Проверка на выигрпышную комбинацию.
    //(Полное заполнение горизонтали, вертикали или диагонали.)


    // проверка, на наличие свободных ячеек поля.

    boolean TheEnd() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i][j] == Cell.Type.Null) {
                    return false;
                }
            }
        }

        return true;
    }

    // текущее поле.
    void printField() {
        System.out.print("@  ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        String d;

        for (int i = 0; i < n; i++) {
            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
            for (int j = 0; j < n; j++) {
                if (j < 10) {
                    d = ".";
                    if (field[i][j] == Cell.Type.X) {
                        d = "X";
                    } else if (field[i][j] == Cell.Type.O) {
                        d = "O";
                    }
                } else {
                    d = " .";
                    if (field[i][j] == Cell.Type.X) {
                        d = " X";
                    } else if (field[i][j] == Cell.Type.O) {
                        d = " O";
                    }
                }

                System.out.print(d + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
