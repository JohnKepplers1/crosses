// интерфейс для игроков

public interface Player {
    public Cell getTurn(Field field);  // метод, с помощью которого везде будем получать ход игрока

    public Cell.Type getType();    // метод, отдающий тип игрока

    public String getPlayerName(); // метод, отдающий название игрока
}
