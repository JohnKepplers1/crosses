package ru.ifmo.kravchenko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {


    // Размер игровой доски(количество ячеек).
    private final static int FIELD_SIZE = 10;
    // Индикатор хода (если true, то первыми игру начинают крестики).
    private final static boolean MOVE = false;
    static int spy1;
    static int spy2;
    private static Field board;
    // Размер окна(px).
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 520;
    // Размер ячейки(px).
    private final int CELL_WIDTH = 34;
    Beginner exemplar = new Beginner();
    //Минимальное количество крестиков или ноликов в непрерывной линии, при котором засчитывается выигрыш.
    private int WIN_COUNT = 5;
    private JPanel gamePanel;
    // счет игры
    private int xCount = 0;
    private int oCount = 0;

    public Window() {

        board = new Field(FIELD_SIZE);

        // Создание меню.
        JMenuBar mainMenu = new JMenuBar();
        setJMenuBar(mainMenu);
        JMenu fileMenu = new JMenu("Файл");
        JMenu helpMenu = new JMenu("Справка");
        JMenuItem exitItem = new JMenuItem("Выход");
        JMenuItem clearItem = new JMenuItem("Новая игра");
        fileMenu.add(exitItem);
        fileMenu.add(clearItem);
        JMenuItem aboutItem = new JMenuItem("О программе");
        helpMenu.add(aboutItem);
        mainMenu.add(fileMenu);
        mainMenu.add(helpMenu);

        // Панель, на которой размещена игровая доска.
        gamePanel = new JPanel(new GridLayout(FIELD_SIZE, FIELD_SIZE));

        JScrollPane scrollFrame = new JScrollPane(gamePanel);
        gamePanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        for (int i = 0; i < board.size(); i++) {

            for (int j = 0; j < board.size(); j++) {

                JButton myButton = new JButton(board.getItemAt(i, j).getName());
                myButton.setPreferredSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
                myButton.setAction(new ButtonClickAction(i, j));
                gamePanel.add(myButton);
            }
        }
        setContentPane(scrollFrame);
        pack();

        // события
        exitItem.addActionListener(new ExitActionListener());
        aboutItem.addActionListener(new AboutActionListener());
        clearItem.addActionListener(new ClearActionListener());

        SwingUtilities.updateComponentTreeUI(mainMenu);
        SwingUtilities.updateComponentTreeUI(gamePanel);
    }

    public static int getSizeOfField() {
        return FIELD_SIZE;
    }

    public static Field getBoard() {
        return board;
    }

    public static boolean getMove() {
        return MOVE;
    }

    // Очистить игровую доску.
    private void clearGamePanel() {

        int count = 0;

        //Очистка использованных элементов игровой доски.
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {

                Cell.Type item = board.getItemAt(i, j);
                if (!item.equals(Cell.Type.Null)) {
                    JButton button = (JButton) gamePanel.getComponents()[count];
                    button.setText(new String());
                    button.setEnabled(true);
                    button.setBackground(null);
                    button.setForeground(null);
                }
                count++;
            }
        }

        board = new Field(FIELD_SIZE);
    }

    // Показать поле.
    private void renderGameField() {
        int count = 0;
        for (int i = 0; i < board.size(); i++)
            for (int j = 0; j < board.size(); j++) {
                JButton buff = (JButton) gamePanel.getComponents()[count++];
                buff.setText(board.getItemAt(i, j).getName());

                if (board.getItemAt(i, j).equals(Cell.Type.O)
                        || board.getItemAt(i, j).equals(Cell.Type.X)) {
                    buff.setOpaque(true);
                    buff.setForeground(Color.BLACK);
                }
            }
    }


    // Действие при нажатии на кнопку(игровую ячейку).
    private class ButtonClickAction extends AbstractAction {


        MyCommand cmd = new MyCommand();
        // Индекс ячейки.
        private int i, j;

        public ButtonClickAction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            JButton source = (JButton) e.getSource();
            JLabel label = new JLabel();

            // Клик по пустому полю.
            if (!board.getItemAt(i, j).equals(Cell.Type.Null))
                return;

            // Установка крестика или нолика на поле.
          /*  for (int i = 0; i < board.size(); i++)
                for (int j = 0; j < board.size(); j++) {
                    JButton buff = (JButton) gamePanel.getComponents()[5];
                    buff.setText(board.getItemAt(i, j).getName());

                    if (board.getItemAt(i, j).equals(Cell.Type.O)
                            || board.getItemAt(i, j).equals(Cell.Type.X)) {
                        buff.setOpaque(true);
                        buff.setForeground(Color.RED);
                    }
                }
                      */

            cmd.execute(i, j);
            source.setText(board.getItemAt(i, j).getName());
            source.setEnabled(false);
            spy1 = i;
            spy2 = j;

            board.setItemAt1(MOVE);

            renderGameField();


            // Проверка на наличие победителя.
            Cell.Type winner = board.getNextWinner(WIN_COUNT);

            if (winner != null && !winner.equals(Cell.Type.Null)) {

                String msg;

                if (winner.equals(Cell.Type.X)) {
                    msg = "крестиков!";
                    xCount++;
                } else {
                    msg = "ноликов!";
                    oCount++;
                }

                renderGameField();

                JOptionPane.showMessageDialog(Window.this, "Победа " + msg
                                + "\nКрестики — Нолики (" + xCount + "-" + oCount + ")", "Победа",
                        JOptionPane.INFORMATION_MESSAGE);

                clearGamePanel();

            }
        }
    }

    // Выход из приложения.
    private class ExitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    // Новая игра.
    private class ClearActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            clearGamePanel();
            xCount = 0;
            oCount = 0;
        }
    }

    // О программе.
    private class AboutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            JOptionPane.showMessageDialog(Window.this, "Крестики-нолики "
                            + FIELD_SIZE + "x" + FIELD_SIZE
                            + ". \nДля победы составьте линию (миниумум) из " + WIN_COUNT
                            + " крестиков или ноликов.", "О программе",
                    JOptionPane.INFORMATION_MESSAGE);


        }
    }

}
