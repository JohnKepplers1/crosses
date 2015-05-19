package ru.ifmo.kravchenko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {
    // Размер игровой доски(количество ячеек).
    private final static int FIELD_SIZE = 3;
    // Индикатор хода (если true, то первыми игру начинают крестики).
    private final static boolean MOVE = false;
    private static Field board;
    static private int[] list1 = new int[FIELD_SIZE * FIELD_SIZE];
    // Размер окна(px).
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 520;
    // Размер ячейки(px).
    private final int CELL_WIDTH = 34;
    private MyCommand cmd = new MyCommand();
    private int[] list2 = new int[FIELD_SIZE * FIELD_SIZE];
    private int iterator = -1;
    private int counter = 0;
    //Минимальное количество крестиков или ноликов в непрерывной линии, при котором засчитывается выигрыш.
    private int WIN_COUNT = 3;
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
        JMenuItem undo = new JMenuItem("Отмена");
        JMenuItem clearItem = new JMenuItem("Новая игра");
        fileMenu.add(exitItem);
        fileMenu.add(clearItem);
        fileMenu.add(undo);
        JMenuItem aboutItem = new JMenuItem("О программе");
        helpMenu.add(aboutItem);
        mainMenu.add(fileMenu);
        mainMenu.add(helpMenu);

        // Панель, на которой размещена игровая доска.
        gamePanel = new JPanel(new GridLayout(FIELD_SIZE, FIELD_SIZE));

        JScrollPane scrollFrame = new JScrollPane(gamePanel);
        gamePanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(FRAME_WIDTH + 100, FRAME_HEIGHT + 100));

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

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iterator >= 0) {
                    cmd.undo(list1[iterator], list2[iterator]);
                    board.myAnotherMethod(list1[iterator], list2[iterator]);
                    renderGameField();
                    iterator--;
                    counter--;
                    cmd.undo(list1[iterator], list2[iterator]);
                    board.myAnotherMethod(list1[iterator], list2[iterator]);
                    renderGameField();
                    iterator--;
                    counter--;

                    //System.out.println(iterator);
                }
            }
        });

        SwingUtilities.updateComponentTreeUI(mainMenu);
        SwingUtilities.updateComponentTreeUI(gamePanel);
    }
    Controller cnt = new Controller(board);

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


        // Индекс ячейки.
        private int i, j;


        public ButtonClickAction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            JButton source = (JButton) e.getSource();
            //    JLabel label = new JLabel();

            // Клик по пустому полю.
            if (!board.getItemAt(i, j).equals(Cell.Type.Null)) {
                return;
            }
            cmd.execute(i, j);
            board.myMethod(MOVE, i, j);
            iterator++;
            list1[iterator] = i;
            list2[iterator] = j;
            renderGameField();
            counter++;

            //  source.setText(board.getItemAt(i, j).getName());
            //  source.setEnabled(false);
            //     System.out.println(counter);
            if (counter < FIELD_SIZE * FIELD_SIZE) {
                iterator++;
                board.setItemAt1(MOVE);
                list1[iterator] = board.getIconst();
                list2[iterator] = board.getJconst();
                renderGameField();

                counter++;

            }


            // Проверка на наличие победителя.
            Cell.Type winner = board.getNextWinner(WIN_COUNT);


            if (winner != null && !winner.equals(Cell.Type.Null)) {
                counter = 0;
                iterator = -1;
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
            if (counter == FIELD_SIZE * FIELD_SIZE) {
                counter = 0;
                iterator = -1;
                JOptionPane.showMessageDialog(Window.this, "Ничья "
                                + "\nКрестики — Нолики (" + xCount + "-" + oCount + ")", "Ничья!",
                        JOptionPane.INFORMATION_MESSAGE);
                clearGamePanel();


            }
        }
    }

    // Выход из приложения.
    private class ExitActionListener implements ActionListener {

        public ExitActionListener() {
        }

        public void actionPerformed(ActionEvent event) {
            cnt.newGame();
         //   System.exit(0);
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
