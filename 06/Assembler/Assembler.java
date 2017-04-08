package assembler;

import java.io.*;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.AbstractMap.SimpleEntry;

class Assembler {
    private static Map<String, String> symbolTable = Stream.of(
            new SimpleEntry<>("SP",     "0"),
            new SimpleEntry<>("LCL",    "1"),
            new SimpleEntry<>("ARG",    "2"),
            new SimpleEntry<>("THIS",   "3"),
            new SimpleEntry<>("THAT",   "4"),
            new SimpleEntry<>("R0",     "0"),
            new SimpleEntry<>("R1",     "1"),
            new SimpleEntry<>("R2",     "2"),
            new SimpleEntry<>("R3",     "3"),
            new SimpleEntry<>("R4",     "4"),
            new SimpleEntry<>("R5",     "5"),
            new SimpleEntry<>("R6",     "6"),
            new SimpleEntry<>("R7",     "7"),
            new SimpleEntry<>("R8",     "8"),
            new SimpleEntry<>("R9",     "9"),
            new SimpleEntry<>("R10",    "10"),
            new SimpleEntry<>("R11",    "11"),
            new SimpleEntry<>("R12",    "12"),
            new SimpleEntry<>("R13",    "13"),
            new SimpleEntry<>("R14",    "14"),
            new SimpleEntry<>("R15",    "15"),
            new SimpleEntry<>("SCREEN", "16384"),
            new SimpleEntry<>("KBD",    "24576"))
            .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    public static void main(String[] args) throws IOException {
        File input = new File(args[0]);
        String inputFileName = input.getName();
        String fileName = inputFileName.split("\\.")[0];
        File output = new File(fileName + ".hack");
        FileWriter writer = new FileWriter(output);

        Parser parser = new Parser(input);
        int count = 0;
        while (parser.hasMoreCommands()) {
            parser.advance();
            int type = parser.commandType();
            switch (type) {
                case Parser.A_COMMAND:
                case Parser.C_COMMAND:
                    count++;
                    break;
                case Parser.L_COMMAND:
                    String symbol = parser.symbol();
                    symbolTable.put(symbol, String.valueOf(count));
                    break;
                default:
                    break;
            }
        }

        int address = 16;
        parser = new Parser(input);
        while (parser.hasMoreCommands()) {
            parser.advance();
            int type = parser.commandType();
            switch (type) {
                case Parser.A_COMMAND:
                    String symbol = parser.symbol();
                    int number;
                    if (symbol.matches("\\d+")) {
                        number = Integer.parseInt(symbol);
                    } else {
                        if (!symbolTable.containsKey(symbol)) {
                            symbolTable.put(symbol, String.valueOf(address));
                            address++;
                        }
                        number = Integer.parseInt(symbolTable.get(symbol));
                    }
                    String numberString = Integer.toBinaryString(number);
                    String aCommand = "0" + String.format("%015d", Long.parseLong(numberString)) + "\n";
                    writer.write(aCommand);
                    break;
                case Parser.C_COMMAND:
                    String comp = Code.comp(parser.comp());
                    String dest = Code.dest(parser.dest());
                    String jump = Code.jump(parser.jump());
                    String cCommand = "111" + comp + dest + jump + "\n";
                    writer.write(cCommand);
                    break;
                case Parser.L_COMMAND:
                    break;
                default:
                    break;
            }
        }

        writer.close();
    }
}
