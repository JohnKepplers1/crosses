package ru.ifmo.kravchenko;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

class Main {
    static private int detector = 0;

public int getDetector(){
    return detector;
}
    public static void main(final String[] args) throws InterruptedException,
            InvocationTargetException, ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        Scanner sc = new Scanner(System.in);
        int k = 0;
        Beginner exemplar = new Beginner();
        exemplar.chooseVariantOfGame();
        if (exemplar.getChoice() == 2) {
            System.out.println("Введите линейную характеристику поля. ");
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    // создание главного окна
                    Window wnd = new Window();
                    wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    wnd.setTitle("Tic-tack-toe");
                    wnd.setLocation(200, 200);
                    wnd.setResizable(false);
                    wnd.setVisible(true);
                }
            });

        } else {
            Player first = new Human(Cell.Type.Null);
            Player second = new RandomComputer(Cell.Type.Null);
            exemplar.chooseSymbol();
            while (k < 1) {
                System.out.println("Выберите игру (введите соответствующее число):\n   1)FreeslyleGomoku(15Х15 или 19Х19).\n   2)Tick-tack-toe(3х3).");

                try {
                    k = Integer.parseInt(sc.next());
                } catch (NumberFormatException ex) {
                    System.out.println("Введено недопустимое значение.");

                }
                if (k > 2) {
                    System.out.println("Такого числа в наборе нет.");
                    k = 0;
                }
            }
            if (k == 1) {
                while ((exemplar.getSize() != 15) && (exemplar.getSize() != 19)) {
                    exemplar.chooseField();

                }
                Game game = new FreestyleGomoku(first, second, exemplar.getSize());

                detector = 1;
                game.startGame();

            }
            if (k == 2) {
                while (exemplar.getSize() != 3) {
                    exemplar.chooseField();

                }
                Game game = new TickTackToe(first, second, exemplar.getSize());

                detector = 2;
                game.startGame();
            }
        }

    }


}

