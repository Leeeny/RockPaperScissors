package net;

import game.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Connection {

    public static String getSMTFromConsole(String print) {
        Scanner in = new Scanner(System.in);
        System.out.println(print);
        String name = in.next();
        //in.close();
        return name;
    }

    public static Move getMoveFromConsole() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a move (ROCK PAPER or SCISSORS)");
        String move = null;
        do {
            move = in.next();
        } while (!Objects.equals(move, "ROCK") && !Objects.equals(move, "PAPER") && !Objects.equals(move, "SCISSORS"));
        System.out.println("Your choose " + move);
        return Move.valueOf(move);
    }


    public static boolean getBoolean(String str){
        Scanner in = new Scanner(System.in);
        System.out.println(str + " (Y/N)");
        String yn = null;
        do {
            yn = in.next();
        } while (!Objects.equals(yn, "Y") && !Objects.equals(yn, "N"));
        return yn.equals("Y");
    }

    public static void send(String str, PrintWriter writer) {
        writer.println(str);
        writer.flush();
    }

    public static String recv(Scanner scanner) throws IOException {
        return scanner.nextLine();
    }
}
