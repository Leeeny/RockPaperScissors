import java.util.ArrayList;

public class Game {
    private long player1Score;
    private long player2Score;
    private Player player1;
    private Player player2;
    //private ArrayList<Move> player1Moves;
    //private ArrayList<Move> player2Moves;

    /**
     * Как играть в эту игру:
     * Инициализируем игроков уже с их первыми ходами, затем создаем класс игры, он автоматически для первых ходов считает
     * счет, затем через игроков мы задаем им ходы и вызываем паблик метод playGame, он обновляет счет, после этого его надо
     * выводить через getResult или геттеры счета
     *
     * @param player1 ЗАРАНЕЕ инициализированный первый игрок
     * @param player2 ЗАРАНЕЕ инициализированный второй игрок
     */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Score = 0;
        player2Score = 0;
        playGame(player1, player2);
    }

    private void playGame(Player player1, Player player2) {
        Move move1 = player1.getMove();
        Move move2 = player2.getMove();
        switch (move1) {
            case ROCK:
                switch (move2) {
                    case PAPER -> {
                        player2Score++;
                        return;
                    }
                    case SCISSORS -> {
                        player1Score++;
                        return;
                    }
                    case ROCK -> {
                        return;
                    }
                }
            case PAPER:
                switch (move2) {
                    case SCISSORS -> {
                        player2Score++;
                        return;
                    }
                    case ROCK -> {
                        player1Score++;
                        return;
                    }
                    case PAPER -> {
                        return;
                    }
                }
            case SCISSORS:
                switch (move2) {
                    case ROCK -> {
                        player2Score++;
                        return;
                    }
                    case PAPER -> {
                        player1Score++;
                        return;
                    }
                    case SCISSORS -> {
                        return;
                    }
                }
        }
    }

    public void playGame() {
        playGame(player1, player2);
    }

    public String getResult() {
        return "Player1 " + player1.getName() + " last move " + player1.getMove().toString() + " score " + player1Score +
                "\nPlayer2 " + player2.getName() + " last move " + player2.getMove().toString() + " score " + player2Score;
    }

    public long getPlayer1Score() {
        return player1Score;
    }

    public long getPlayer2Score() {
        return player2Score;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1Score(long player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(long player2Score) {
        this.player2Score = player2Score;
    }
}
