import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RunLengthEncoding {
    public RunLengthEncoding() {
    }

    public static String encode(String sequence) {
        String encoded = "";

        int ocurrences;
        for (int i = 0; i < sequence.length(); i++) {
            ocurrences = 1;
            while (i+1 < sequence.length() && sequence.charAt(i) == sequence.charAt(i+1)) {
                ocurrences++;
                i++;
            }
            encoded += ocurrences + String.valueOf(sequence.charAt(i));
        }
        return encoded;
    }

    public static String decode(String sequence) {
        String decoded = "";
        for (int i = 0; i < sequence.length(); i += 2) {
            int finalI = i+1;
            decoded += IntStream.range(0, Integer.parseInt(String.valueOf(sequence.charAt(i)))).mapToObj(ir -> String.valueOf(sequence.charAt(finalI))).collect(Collectors.joining(""));
        }
        return decoded;
    }
}
