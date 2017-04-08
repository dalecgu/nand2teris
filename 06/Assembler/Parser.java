package assembler;

import java.io.*;

public class Parser {
    private BufferedReader reader;
    private String command;

    public static final int A_COMMAND = 0;
    public static final int C_COMMAND = 1;
    public static final int L_COMMAND = 2;

    public Parser(File file) {
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasMoreCommands() {
        try {
            return reader.ready();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void advance() {
        try {
            command = reader.readLine().trim();
            if (command.startsWith("//") || command.equals("")) {
                advance();
            }
            while (command.contains("//")) {
                command = command.split("//")[0].trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int commandType() {
        if (command.startsWith("@")) {
            return A_COMMAND;
        }
        if (command.startsWith("(") && command.endsWith(")")) {
            return L_COMMAND;
        }
        return C_COMMAND;
    }

    public String symbol() {
        if (commandType() == A_COMMAND) {
            return command.substring(1);
        }
        if (commandType() == L_COMMAND) {
            return command.substring(1, command.length()-1);
        }
        return null;
    }

    public String dest() {
        if (command.contains("=")) {
            return command.split("=")[0];
        }
        return "null";
    }

    public String comp() {
        if (command.contains(";")) {
            if (command.contains("=")) {
                return command.split(";")[0].split("=")[1];
            }
            return command.split(";")[0];
        }
        if (command.contains("=")) {
            return command.split("=")[1];
        }
        return "null";
    }

    public String jump() {
        if (command.contains(";")) {
            return command.split(";")[1];
        }
        return "null";
    }
}
