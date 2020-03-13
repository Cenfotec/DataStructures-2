import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Huffman {
    // Traverse the Huffman Tree & store the Huffman Codes in a map.
    public static void encode(Node root, String str,
                              Map<Character, String> huffmanCode) {
        if (root == null)
            return;

        // Found a node with no available children.
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, str);
        }


        // Travels through possible path (left & right).
        encode(root.left, str + "0", huffmanCode);
        encode(root.right, str + "1", huffmanCode);
    }

    // Traverse the Huffman Tree & decode each set of Huffman Codes.
    public static int decode(Node root, int index, StringBuilder sb) {
        if (root == null)
            return index;

        // Found a node with no available children.
        if (root.left == null && root.right == null) {
            System.out.print(root.ch);
            return index;
        }

        index++;

        if (sb.charAt(index) == '0')
            index = decode(root.left, index, sb);
        else
            index = decode(root.right, index, sb);

        return index;
    }

    // Build Huffman Tree based on a character and occurrence relationship
    public static void build(String text) {
        // Store each character's frequency in a map using the char. as the key.
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            // If the key hasn't been stored yet, it inserts it with a 0 as its initial value.
            if (!freq.containsKey(text.charAt(i))) {
                freq.put(text.charAt(i), 0);
            }
            freq.put(text.charAt(i), freq.get(text.charAt(i)) + 1);
        }

        // Create a priority queue, using low occurrence as high priority.
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(l -> l.freq));

        // Create a node for each character with its value & add it to the priority queue.
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Repeat until there're no nodes left.
        while (pq.size() != 1) {
            // Remove the 2 nodes with highest priority.
            Node left = pq.poll();
            Node right = pq.poll();

            // Create an empty node using the left and right node as its children
            // with the sum of both node's occurrence as its own occurrence value.
            int sum = left.freq + right.freq;
            pq.add(new Node('\0', sum, left, right));
        }

        // Create a node root pointing to Huffman's Coding Tree root.
        Node root = pq.peek();

        // Traverse the Huffman tree and store the Huffman codes in a map.
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        // Print the Huffman Codes of each entry.
        System.out.println("Huffman Codes are:\n");
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // Print the initial string.
        System.out.println("\nOriginal string was:\n" + text);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(huffmanCode.get(text.charAt(i)));
        }

        // Print the encoded string.
        System.out.println("\nEncoded string is:\n" + sb);

        // Traverse the Huffman Tree & decode each entry to its respective character.
        int index = -1;
        // Print the decoded string.
        System.out.println("\nDecoded string is: ");
        while (index < sb.length() - 2) {
            index = decode(root, index, sb);
        }
    }
}