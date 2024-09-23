import java.util.Scanner;

/**
 * Universal Automaton.
 * 
 * TODO 3: Fill in your names and student IDs
 * 
 * @author Milan Versteegh
 * @ID 2136279
 * @author Egor Bryantsev
 * @ID 2087782
 */
class UniversalAutomaton {
    Scanner scanner = new Scanner(System.in);

    String genToString(boolean[] gen) {
        String result = "";
        for (int i = 0; i < gen.length; i++) {
            if(gen[i]){
                result += "*";
            } else {
                result += " ";
            }
        }
        return result;
    }

    boolean[] nextGen(boolean[] ruleSequence, boolean[] gen) {
        int length = gen.length;
        boolean[] nextGen = new boolean[length];
        for (int i = 0; i < length; i++) {
            boolean left = (i > 0) ? gen[i-1] : false;
            boolean current = gen[i];
            boolean right = (i < length - 1) ? gen[i + 1] : false;
            int patternIndex = (left ? 4 : 0) + (current ? 2 : 0) + (right ? 1 : 0);
            nextGen[i] = ruleSequence[patternIndex];
        }
        return nextGen;
    }

    boolean[] readInitalGeneration(int length) {
        boolean[] generation = new boolean[length]; // Create an array to represent the generation
        // Read the first token
        String token = scanner.next();
        // Keep processing tokens until "init_end" is found
        while (!token.equals("init_end")) {
            // If the token is "init_start", just read the next token and skip further processing
            if (token.equals("init_start")) {
                token = scanner.next();
                continue;
            }
            // Convert the token to an integer, then subtract 1 to convert it from 1-based to 0-based indexing
            int pos = Integer.parseInt(token) - 1;
            // Check if the position is within the bounds of the generation array
            if (pos >= 0 && pos < length) {
                generation[pos] = true; // Mark this cell as occupied
            }
            // Read the next token
            token = scanner.next();
        }
        // Return the completed generation array
        return generation;
    }

    boolean[] readRuleSequence() {
        boolean[] ruleSequence = new boolean[8];
        for (int i = 0; i < 8; i++) {
            ruleSequence[i] = scanner.nextInt() == 1;
        }
        return ruleSequence;
    }

    void run() {
        // Read input to configure the universal automaton
        boolean[] ruleSequence = readRuleSequence();
        int generationLength = scanner.nextInt();
        int numberOfGenerations = scanner.nextInt();
        boolean[] initGen = readInitalGeneration(generationLength);

        // Run the automaton
        boolean[] gen = initGen;

        for (int i = 0; i < numberOfGenerations; i++) {
            // Display the current generation
            System.out.println(genToString(gen));
            // Determine the next generation
            gen = nextGen(ruleSequence, gen);
        }
    }

    public static void main(String[] args) {
        new UniversalAutomaton().run();
    }
}