public class Main {
    public static void main(String[] args) {
        String sequence = "aaaaaaaabbbbbbcc";
        System.out.println("Sequence: " + sequence);

        String encoded = RunLengthEncoding.encode(sequence);
        System.out.println("Encoded: " + encoded);

        String decoded = RunLengthEncoding.decode(encoded);
        System.out.println("Decoded: " + decoded);
    }
}
