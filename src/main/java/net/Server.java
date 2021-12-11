package net;

import game.Game;
import game.GameSerialization;
import game.Move;
import game.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) throws IOException, ParseException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("Server is running");
        Socket client = server.accept(); //присоединяем клиента
        System.out.println("Client successfully connected");

        PrintWriter writer = new PrintWriter(client.getOutputStream());
        Scanner scanner = new Scanner(client.getInputStream());
        File file = new File("game.json");
        Game game;
        Move move1;
        Boolean isLoad = false;
        if (file.exists()) {
            isLoad = Connection.getBoolean("Do you want to load the game?");
        }
        Connection.send(String.valueOf(isLoad), writer);
        if (isLoad) {
            game = GameSerialization.FileToGame("game.json");
            String strG = GameSerialization.GameTOJSON(game).toString();
            Connection.send(strG, writer);
            System.out.println();
            System.out.println(game.getResult());
            System.out.println("\nYou are " + game.getPlayer1().getName());
            move1 = Connection.getMoveFromConsole();
            game.getPlayer1().setMove(move1);
            String p2str = Connection.recv(scanner);
            game.setPlayer2(GameSerialization.PlayerFromString(p2str));
            game.playGame();
        } else {
            String name1 = Connection.getSMTFromConsole("Enter player name");
            move1 = Connection.getMoveFromConsole();
            Player player1 = new Player(name1, move1);
            String p2str = Connection.recv(scanner);
            Player player2 = GameSerialization.PlayerFromString(p2str);
            game = new Game(player1, player2);
        }
        System.out.println();
        System.out.println(game.getResult());
        JSONObject gameTOClient = GameSerialization.GameTOJSON(game);
        Connection.send(gameTOClient.toString(), writer);
        System.out.println();
        boolean toSave = Connection.getBoolean("Do you want to save the game?");
        if (toSave) {
            GameSerialization.GameToFile(game, "game.json");
        }
        System.out.println();
        System.out.println("Server closed");
        server.close();
        client.close();
        writer.close();
        scanner.close();
    }
}
