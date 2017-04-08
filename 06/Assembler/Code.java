package assembler;

import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.AbstractMap.SimpleEntry;

class Code {
    private static final Map<String, String> destMap = Stream.of(
            new SimpleEntry<>("null", "000"),
            new SimpleEntry<>("M",    "001"),
            new SimpleEntry<>("D",    "010"),
            new SimpleEntry<>("MD",   "011"),
            new SimpleEntry<>("A",    "100"),
            new SimpleEntry<>("AM",   "101"),
            new SimpleEntry<>("AD",   "110"),
            new SimpleEntry<>("AMD",  "111"))
            .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    private static final Map<String, String> compMap = Stream.of(
            new SimpleEntry<>("0",   "0101010"),
            new SimpleEntry<>("1",   "0111111"),
            new SimpleEntry<>("-1",  "0111010"),
            new SimpleEntry<>("D",   "0001100"),
            new SimpleEntry<>("A",   "0110000"),
            new SimpleEntry<>("M",   "1110000"),
            new SimpleEntry<>("!D",  "0001101"),
            new SimpleEntry<>("!A",  "0110001"),
            new SimpleEntry<>("!M",  "1110001"),
            new SimpleEntry<>("-D",  "0001111"),
            new SimpleEntry<>("-A",  "0110011"),
            new SimpleEntry<>("-M",  "1110011"),
            new SimpleEntry<>("D+1", "0011111"),
            new SimpleEntry<>("A+1", "0110111"),
            new SimpleEntry<>("M+1", "1110111"),
            new SimpleEntry<>("D-1", "0001110"),
            new SimpleEntry<>("A-1", "0110010"),
            new SimpleEntry<>("M-1", "1110010"),
            new SimpleEntry<>("D+A", "0000010"),
            new SimpleEntry<>("D+M", "1000010"),
            new SimpleEntry<>("D-A", "0010011"),
            new SimpleEntry<>("D-M", "1010011"),
            new SimpleEntry<>("A-D", "0000111"),
            new SimpleEntry<>("M-D", "1000111"),
            new SimpleEntry<>("D&A", "0000000"),
            new SimpleEntry<>("D&M", "1000000"),
            new SimpleEntry<>("D|A", "0010101"),
            new SimpleEntry<>("D|M", "1010101"))
            .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    private static final Map<String, String> jumpMap = Stream.of(
            new SimpleEntry<>("null", "000"),
            new SimpleEntry<>("JGT",  "001"),
            new SimpleEntry<>("JEQ",  "010"),
            new SimpleEntry<>("JGE",  "011"),
            new SimpleEntry<>("JLT",  "100"),
            new SimpleEntry<>("JNE",  "101"),
            new SimpleEntry<>("JLE",  "110"),
            new SimpleEntry<>("JMP",  "111"))
            .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    public static String dest(String arg) {
        return destMap.get(arg);
    }

    public static String comp(String arg) {
        return compMap.get(arg);
    }

    public static String jump(String arg) {
        return jumpMap.get(arg);
    }

    public static void main(String[] args) {
        // test dest
        // System.out.println(dest("null"));
        // System.out.println(dest("M"));
        // System.out.println(dest("D"));
        // System.out.println(dest("MD"));
        // System.out.println(dest("A"));
        // System.out.println(dest("AM"));
        // System.out.println(dest("AD"));
        // System.out.println(dest("AMD"));

        // test jump
        // System.out.println(jump("null"));
        // System.out.println(jump("JGT"));
        // System.out.println(jump("JEQ"));
        // System.out.println(jump("JGE"));
        // System.out.println(jump("JLT"));
        // System.out.println(jump("JNE"));
        // System.out.println(jump("JLE"));
        // System.out.println(jump("JMP"));
    }
}
