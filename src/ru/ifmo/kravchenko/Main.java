package ru.ifmo.kravchenko;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

class Main {

    public static void main(final String[] args) throws InterruptedException,
            InvocationTargetException, ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
   /*    try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        GameModel model = new GameModel(15, 15, 6);
        GameView view = new GameView(model);
        view.setVisible(true);*/






    EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                // Создание главного окна.
                Window wnd = new Window();
                wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                wnd.setTitle("Tic-tack-toe");
                wnd.setLocation(200, 200);
                wnd.setResizable(false);
                wnd.setVisible(true);
            }
        });

    }


}

