

public class FreestyleGomoku extends Game {

    private final Field gameField;
    private final String gameName = "FreestyleGomoku";

    FreestyleGomoku(Player first, Player second, int size) {

        super(first, second, size);
        gameField = new Field(size);
    }

    public String getGameName() {
        return "FreestyleGomoku";
    }
}
