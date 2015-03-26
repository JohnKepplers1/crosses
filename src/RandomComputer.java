import java.util.Random;


public class RandomComputer implements Player {
   private Beginner exemplar = new Beginner();
   private String playerName = "RandomPlayer";
   private Cell.Type playerType;

    RandomComputer(Cell.Type type) {

        if (exemplar.chooseSymbol().equals("X")) {
            playerType = Cell.Type.O;

        } else {
            playerType = Cell.Type.X;
        }
    }


    public Cell.Type getType() {
        return playerType;
    }

    public String getPlayerName() {
        return "RandomComputer";
    }

    public Cell getTurn(Field field) {
        int size = field.size();

        Random random = new Random();

        while (true) // цикл хода
        {
            int j = random.nextInt(size);
            int i = random.nextInt(size);

            if (field.cellType(i, j) == Cell.Type.Null) {
                System.out.println("Игрок RandomComputer(" + playerType + ") сделал ход на клетку (" + j + "," + i + ")");
                return new Cell(i, j, playerType);
            }
        }
    }
}
