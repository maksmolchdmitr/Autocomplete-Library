package org.example.interact;

import java.util.Scanner;

import static java.lang.System.out;

public final class CommandLineInteract {
    private static final Scanner in = new Scanner(System.in);

    public static String getString(String message) {
        out.print(message);
        return in.nextLine();
    }

    public static void println(String message) {
        out.println(message);
    }
}
