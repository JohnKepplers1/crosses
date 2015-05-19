package ru.ifmo.kravchenko;

import javax.swing.*;

public class Controller {
    private Field f;

    public Controller(Field board) {
        this.f = board;
    }


    public void newGame() {
        int reply = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите выйти?", "Выход", JOptionPane.INFORMATION_MESSAGE);

        if (reply == JOptionPane.YES_OPTION) {

            System.exit(0);

        }
        if (reply == JOptionPane.NO_OPTION) {


        }

    }
}
