/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package maow.shatter.console;

import maow.ncycloapi.Server;
import maow.quickconsole.console.Argument;
import maow.quickconsole.console.Console;
import maow.quickconsole.util.ParametricRunnable;

import java.util.Arrays;
import java.util.Scanner;

public class Main extends Console {
    protected static final Scanner scanner = new Scanner(System.in);
    private static boolean enableLogging = false;

    public static void main(String[] args) {
        addArgument(new Argument("l", "Enable logging on the client", new ParametricRunnable<String>() {
            @Override
            protected void onRun() {
                enableLogging = true;
            }
        }));
        processArgs(args);
        System.out.println("- Sending begin message to client.");
        Server.getInstance().send("ShatterBegin{log=" + enableLogging + "}");
        awaitInput();
    }

    private static void awaitInput() {
        System.out.print("> ");
        String[] input = scanner.nextLine().split("\\s+");
        String command = input[0];
        String[] args = Arrays.copyOfRange(input, 1, input.length);
        processInput(command, args);
    }

    private static void processInput(String command, String[] args) {
        Server.getInstance().send("ShatterCommand{command=" + command + ",args=" + Arrays.toString(args) + "}");
        awaitInput();
    }
}
