import java.util.Scanner;

/**
 * Automatons A and B.
 * 
 * TODO 3: Fill in your names and student IDs:
 * 
 * @author NAME
 * @id ID
 * @author NAME
 * @id ID
 */
class ABAutomatonGPT {
    Scanner scanner = new Scanner(System.in);

    /**
     * Converts a generation of cells (boolean array) to a string of '*' for occupied and ' ' for empty cells.
     *
     * @param gen The boolean array representing the generation
     * @return The string representation of the generation
     */
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

    /**
     * Generates the next generation for automaton A.
     * 
     * @param gen The current generation
     * @return The next generation
     */
    boolean[] nextGenA(boolean[] gen) {
        int length = gen.length;
        boolean[] nextGen = new boolean[length];
        // Loop through each cell in the current generation
        for (int i = 0; i < length; i++) {
            // Check the left neighbor, if it's the first cell, there is no left neighbor, so we assume it's empty
            boolean left;
            if (i == 0) {
                left = false;
            } else {
                left = gen[i - 1];
            }
            // Check the right neighbor, if it's the last cell, there is no right neighbor, so we assume it's empty
            boolean right;
            if (i == length - 1) {
                right = false;
            } else {
                right = gen[i + 1];
            }
            // Check the current cell
            boolean current = gen[i];
            // Apply the rules for Automaton A
            if (current) {
                // If the current cell is occupied, it stays occupied if exactly one of its neighbors is occupied
                if (left != right) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            } else {
                // If the current cell is empty, it becomes occupied if at least one neighbor is occupied
                if (left || right) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            }
        }
        return nextGen;
    }

    /**
     * Generates the next generation for automaton B.
     * 
     * @param gen The current generation
     * @return The next generation
     */
    boolean[] nextGenB(boolean[] gen) {
        int length = gen.length;
        boolean[] nextGen = new boolean[length];
        // Loop through each cell in the current generation
        for (int i = 0; i < length; i++) {
            // Check the left neighbor, if it's the first cell, there is no left neighbor, so we assume it's empty
            boolean left;
            if (i == 0) {
                left = false;
            } else {
                left = gen[i - 1];
            }
            // Check the right neighbor, if it's the last cell, there is no right neighbor, so we assume it's empty
            boolean right;
            if (i == length - 1) {
                right = false;
            } else {
                right = gen[i + 1];
            }
            // Check the current cell
            boolean current = gen[i];
            // Apply the rules for Automaton B
            if (current) {
                // If the current cell is occupied, it stays occupied only if the right neighbor is empty
                if (right == false) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            } else {
                // If the current cell is empty, it becomes occupied if exactly one of its neighbors is occupied
                if (left != right) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            }
        }
        return nextGen;
    }

    /**
     * Reads the initial generation from user input. The input specifies occupied cells by their positions.
     * 
     * @param length The length of the generation
     * @return The boolean array representing the initial generation
     */
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

    void run() {
        // Read input to configure the automaton
        String automaton = scanner.next();
        int genLength = scanner.nextInt();
        int numOfGens = scanner.nextInt();
        boolean[] initGen = readInitalGeneration(genLength);

        // Run the automaton
        boolean[] gen = initGen;

        for (int i = 0; i < numOfGens; i++) {
            // Display the current generation
            System.out.println(genToString(gen));

            // Determine the next generation
            if ("A".equals(automaton)) {
                gen = nextGenA(gen);
            } else {
                // Automaton B
                gen = nextGenB(gen);
            }
        }
    }

    public static void main(String[] args) {
        new ABAutomatonGPT().run();
    }
}
