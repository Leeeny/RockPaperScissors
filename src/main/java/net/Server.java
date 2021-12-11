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

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

        PrintWriter writer = new PrintWriter(client.getOutputStream());
        //BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));
        //InputStreamReader reader = new InputStreamReader(client.getInputStream());
        Scanner scanner = new Scanner(client.getInputStream());

        String name1 = Connection.getSMTFromConsole("Enter player name");
        Move move1 = Connection.getMoveFromConsole();
        Player player1 = new Player(name1, move1);
        String p2str = Connection.recv(scanner); //scanner.nextLine(); //reader.readLine();

        Player player2 = GameSerialization.PlayerFromString(p2str);

        Game game = new Game(player1, player2);
        System.out.println();
        System.out.println(game.getResult());
        JSONObject gameTOClient = GameSerialization.GameTOJSON(game);

        writer.println(gameTOClient.toString());
        writer.flush();

        System.out.println();
        System.out.println("Server closed");
        server.close();
        client.close();
        writer.close();
        //reader.close();
        scanner.close();
    }
}
