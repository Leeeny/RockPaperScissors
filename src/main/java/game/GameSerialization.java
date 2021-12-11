package game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSerialization {
    public static JSONObject GameTOJSON(Game game) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("player1_name", game.getPlayer1().getName());
        jsonObject.put("player1_last_move", game.getPlayer1().getMove().toString());
        jsonObject.put("player1_score", game.getPlayer1Score());

        jsonObject.put("player2_name", game.getPlayer2().getName());
        jsonObject.put("player2_last_move", game.getPlayer2().getMove().toString());
        jsonObject.put("player2_score", game.getPlayer2Score());
        return jsonObject;
    }

    public static JSONObject PlayerTOJSON(Player player) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("player_name", player.getName());
        jsonObject.put("player_last_move", player.getMove().toString());
        return jsonObject;
    }

    public static Player PlayerFromJSON(JSONObject jsonObject) {
        String name = (String) jsonObject.get("player_name");
        Move move = Move.valueOf((String) jsonObject.get("player_last_move"));
        return new Player(name, move);
    }

    public static Player PlayerFromString(String str) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(str);
        return PlayerFromJSON(json);
    }

    public static Game GameFromString(String str) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(str);
        String name = (String) jsonObject.get("player1_name");
        Move move = Move.valueOf((String) jsonObject.get("player1_last_move"));
        Player player1 = new Player(name, move);
        name = (String) jsonObject.get("player2_name");
        move = Move.valueOf((String) jsonObject.get("player2_last_move"));
        Player player2 = new Player(name, move);
        Game game = new Game(player1, player2);
        long player1Score = (long) jsonObject.get("player1_score");
        long player2Score = (long) jsonObject.get("player2_score");
        game.setPlayer1Score(player1Score);
        game.setPlayer2Score(player2Score);
        return game;
    }

    public static void GameToFile(Game game, String filename) {
        JSONObject jsonObject = GameTOJSON(game);
        try (FileWriter fileWriter = new FileWriter(filename)) { //try-with-res
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject FileToJSON(String filename) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try (FileReader fileReader = new FileReader(filename)) {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Game FileToGame(String filename) {
        JSONObject jsonObject = FileToJSON(filename);
        String name = (String) jsonObject.get("player1_name");
        Move move = Move.valueOf((String) jsonObject.get("player1_last_move"));
        Player player1 = new Player(name, move);
        name = (String) jsonObject.get("player2_name");
        move = Move.valueOf((String) jsonObject.get("player2_last_move"));
        Player player2 = new Player(name, move);
        Game game = new Game(player1, player2);
        long player1Score = (long) jsonObject.get("player1_score");
        long player2Score = (long) jsonObject.get("player2_score");
        game.setPlayer1Score(player1Score);
        game.setPlayer2Score(player2Score);
        return game;
    }

}
