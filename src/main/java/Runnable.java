import game.Game;
import game.GameSerialization;
import game.Move;
import game.Player;
import org.json.simple.JSONObject;

public class Runnable {
    public static void main(String[] args) {
        Player player1 = new Player("Вася", Move.PAPER);
        Player player2 = new Player("Петя", Move.ROCK);

        Game game = new Game(player1, player2);
        String result = game.getResult();
        System.out.println(result);

        System.out.println();

        player1.setMove(Move.PAPER);
        player2.setMove(Move.SCISSORS);

        game.playGame();

        result = game.getResult();
        System.out.println(result);
        //JSONObject serr = game.GameSerialization.GameTOJSON(game);
        GameSerialization.GameToFile(game, "game.json");
        JSONObject serr = GameSerialization.FileToJSON("game.json");
        System.out.println("\n" + serr);

        Game game1 = GameSerialization.FileToGame("game.json");

        //Move move = Connection.getMoveFromConsole();
    }
}
