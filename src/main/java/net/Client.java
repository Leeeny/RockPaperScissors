package net;

import exeptions.KeyInStringOfGameNotFound;
import game.Game;
import game.GameSerialization;
import game.Move;
import game.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ParseException {
        Socket client = new Socket("localhost", 8888);
        System.out.println("Client is running");

        //ненавижу потоки явы
        PrintWriter writer = new PrintWriter(client.getOutputStream());
        Scanner scanner = new Scanner(client.getInputStream());
        Game game;
        Player player;
        Move move;
        Boolean isLoad = Boolean.valueOf(Connection.recv(scanner));
        try {
            if (isLoad) {
                String getG = Connection.recv(scanner);
                game = GameSerialization.GameFromString(getG);
                player = game.getPlayer2();
                System.out.println();
                System.out.println(game.getResult());
                System.out.println("\nYou are " + player.getName());
                move = Connection.getMoveFromConsole();
                player.setMove(move);
            } else {
                String name = Connection.getSMTFromConsole("Enter player name");
                move = Connection.getMoveFromConsole();
                player = new Player(name, move);
            }
            JSONObject playerToServer = GameSerialization.PlayerTOJSON(player);
            Connection.send(playerToServer.toString(), writer);
            String gameStr = Connection.recv(scanner);
            game = GameSerialization.GameFromString(gameStr);
            System.out.println();
            System.out.println(game.getResult());
            System.out.println();
            System.out.println("Client closed");
            client.close();
            writer.close();
        } catch (KeyInStringOfGameNotFound e) {
            System.out.println("Whoops! Something gone wrong with the transfer of the game json to the client!");
            e.printStackTrace();
        }
    }
}
