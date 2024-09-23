import java.util.Scanner;

/**
 * Automatons A and B.
 * 
 * TODO 3: Fill in your names and student IDs:
 * 
 * @author 
 * @id 
 * @author 
 * @id 
 */
public class ABAutomatonEgor {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Converts a generation array to a string representation.
     *
     * @param gen The boolean array representing the generation.
     * @return A string where occupied cells are '*', and empty cells are ' '.
     */
    String genToString(boolean[] gen) {
        StringBuilder sb = new StringBuilder();
        for (boolean cell : gen) {
            sb.append(cell ? '*' : ' ');
        }
        return sb.toString();
    }

    /**
     * Computes the next generation for Automaton A.
     *
     * Rules for Automaton A:
     * - Occupied cells remain occupied only if exactly one of the neighbors is occupied.
     * - Empty cells remain empty only if both neighbors are empty.
     *
     * @param gen The current generation.
     * @return The next generation.
     */
    boolean[] nextGenA(boolean[] gen) {
        int length = gen.length;
        boolean[] nextGen = new boolean[length];
        
        for (int i = 0; i < length; i++) {
            boolean left = (i > 0) ? gen[i - 1] : false;
            boolean center = gen[i];
            boolean right = (i < length - 1) ? gen[i + 1] : false;
            
            if (center) {
                // Occupied cell rules: Remain occupied only if exactly one neighbor is occupied.
                int occupiedNeighbors = (left ? 1 : 0) + (right ? 1 : 0);
                nextGen[i] = (occupiedNeighbors == 1);
            } else {
                // Empty cell rules: Remain empty only if both neighbors are empty.
                // Otherwise, become occupied.
                nextGen[i] = !(left == false && right == false);
            }
        }
        return nextGen;
    }

    /**
     * Computes the next generation for Automaton B.
     *
     * Rules for Automaton B:
     * - Occupied cells remain occupied only if the right-hand neighbor is empty.
     * - Empty cells become occupied if exactly one neighbor is occupied.
     *
     * @param gen The current generation.
     * @return The next generation.
     */
    boolean[] nextGenB(boolean[] gen) {
        int length = gen.length;
        boolean[] nextGen = new boolean[length];
        
        for (int i = 0; i < length; i++) {
            boolean left = (i > 0) ? gen[i - 1] : false;
            boolean center = gen[i];
            boolean right = (i < length - 1) ? gen[i + 1] : false;
            
            if (center) {
                // Occupied cell rules: Remain occupied only if the right-hand neighbor is empty.
                nextGen[i] = !right;
            } else {
                // Empty cell rules: Become occupied if exactly one neighbor is occupied.
                int occupiedNeighbors = (left ? 1 : 0) + (right ? 1 : 0);
                nextGen[i] = (occupiedNeighbors == 1);
            }
        }
        return nextGen;
    }

    /**
     * Reads the initial generation from user input.
     *
     * Input format:
     * init_start <positions> init_end
     * Positions are positive integers representing the 1-based index of occupied cells.
     *
     * @param length The length of the generation.
     * @return The initial generation array.
     */
    boolean[] readInitialGeneration(int length) {
        boolean[] gen = new boolean[length];
        // Read tokens until 'init_start'
        while (scanner.hasNext()) {
            String token = scanner.next();
            if ("init_start".equals(token)) {
                break;
            }
        }
        // Read positions until 'init_end'
        while (scanner.hasNext()) {
            String token = scanner.next();
            if ("init_end".equals(token)) {
                break;
            }
            try {
                int position = Integer.parseInt(token);
                if (position >= 1 && position <= length) {
                    gen[position - 1] = true;
                }
            } catch (NumberFormatException e) {
                // Ignore invalid numbers
            }
        }
        return gen;
    }

    /**
     * Runs the automaton based on user input configuration.
     *
     * Input Sequence:
     * <Automaton> <Length> <Generations> init_start <positions> init_end
     *
     * Output:
     * Prints G lines representing the successive generations.
     */
    void run() {
        // Read input to configure the automaton
        String automaton = scanner.next();
        int genLength = scanner.nextInt();
        int numOfGens = scanner.nextInt();
        boolean[] initGen = readInitialGeneration(genLength);

        // Run the automaton
        boolean[] gen = initGen;

        for (int i = 0; i < numOfGens; i++) {
            // Display the current generation
            System.out.println(genToString(gen));

            // Determine the next generation
            if ("A".equalsIgnoreCase(automaton)) {
                gen = nextGenA(gen);
            } else {
                // Assume Automaton B
                gen = nextGenB(gen);
            }
        }
    }

    /**
     * The main method to start the automaton program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new ABAutomaton().run();
    }
}
