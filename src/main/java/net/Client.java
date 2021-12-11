package net;

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
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        PrintWriter writer = new PrintWriter(client.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));
        Scanner scanner = new Scanner(client.getInputStream());

        String name = Connection.getSMTFromConsole("Enter player name");
        Move move = Connection.getMoveFromConsole();
        Player player = new Player(name, move);
        JSONObject playerToServer = GameSerialization.PlayerTOJSON(player);

        //Connection.send(playerToServer.toString(), writer);

        writer.println(playerToServer.toString());
        writer.flush();

        //scanner.nextLine();
        String gameStr = scanner.nextLine(); //reader.readLine();
        Game game = GameSerialization.GameFromString(gameStr);
        System.out.println();
        System.out.println(game.getResult());

        System.out.println();
        System.out.println("Client closed");
        client.close();
        writer.close();
        reader.close();
        scanner.close();
    }
}
