import java.util.Scanner;

public class Beginner {
    private byte choice;
    private String y = "♥";
    Scanner sc = new Scanner(System.in);
    private int size = 10;
    private byte i = 0;

    public int getSize() {
        return size;

    }

    public void chooseField() {
        if (i > 0) {
            System.out.println("Выбран недопустимый размер игрового поля.");
        }
        System.out.println("Введите линейную характеристику квадратного игрового поля ");
        while (1 < 2) {
            try {
                size = Integer.parseInt(sc.next());
            } catch (NumberFormatException ex) {
                System.out.println("Ошибка!");
                break;
            }

            i++;
            break;


        }
    }

    public String chooseSymbol() {
        System.out.println("Поставьте 'X', если хотите играть крестиками, и 'О', если хотите играть ноликами.(английская раскладка на клавиатуре) ");

        while (1 < 2) {

            y = sc.next();
            if ((y.equals("X")) || (y.equals("O"))) {
                break;

            } else {
                System.out.println("Вы ввели недопустимое значение. Попробуйте ещё раз.");
            }

        }
        return y;
    }

    public void chooseVariantOfGame() {
        System.out.println("Введите '1', если хотите играть  с консоли, и '2', если хотите играть в отдельном окошке. ");
        while (1 < 2) {
            choice = sc.nextByte();
            if ((choice == 1) || (choice == 2)) {
                break;
            } else {
                System.out.println("Вы ввели недопустимое значение. Попробуйте ещё раз.");
            }
        }
    }
    public byte getChoice(){
        return choice;
    }


}

