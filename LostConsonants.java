import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LostConsonants {
    private static final String VOWELS = "aeiou";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Expected 2 command line arguments, but got " + args.length + ".");
            System.out.println(
                    "Please provide the path to the dictionary file as the first argument and a sentence as the second argument.");
        } else {
            File file = new File(args[0]);
            try {
                // Create HashSet
                BufferedReader input = new BufferedReader(new FileReader(file));
                Set<String> dictionary = new HashSet<String>();
                String line = null;
                while ((line = input.readLine()) != null) {
                    dictionary.add(line.toLowerCase());
                }
                input.close();
                // Perform consonant removal operation
                int count = 0;
                String[] words = args[1].split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    for (int j = 0; j < words[i].length(); j++) {
                        String[] arrClone = words.clone();
                        char currentChar = arrClone[i].toLowerCase().charAt(j);
                        if (VOWELS.indexOf(currentChar) == -1 && Character.isLetter(currentChar)) {
                            arrClone[i] = removeCharAt(arrClone[i], j);
                            if (dictionary.contains(arrClone[i].toLowerCase().replaceAll("[^a-zA-Z ]", ""))) {
                                System.out.println(String.join(" ", arrClone));
                                count = count + 1;
                            }
                        }
                    }
                }

                // Line to print alternatives
                if (count == 0) {
                    System.out.println("Could not find any alternatives.");
                } else {
                    System.out.println("Found " + count + " alternatives.");
                }

            } catch (IOException e) {
                System.out.println("File not found: " + args[0] + " (No such file or directory)");
                System.out.println("Invalid dictionary, aborting.");
            }
        }

    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
}
