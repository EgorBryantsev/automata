import java.util.Scanner;

/**
 * Automatons A and B.
 * 
 * 
 * 
 * @author Milan Versteegh
 * @ID 2136279
 * @author Egor Bryantsev
 * @ID 2087782
 */
class ABAutomaton {
    Scanner scanner = new Scanner(System.in);

    // Converts a generation array to a string representation. Occupied cells are '*', empty cells are ' '.
    String genToString(boolean[] gen) {
        String result = "";
        for (int i = 0; i < gen.length; i++) {
            if (gen[i]) {
                result += "*";
            } else {
                result += " ";
            }
        }
        return result;
    } 

    boolean[] nextGenA(boolean[] gen) {
        int length = gen.length;
        boolean[] nextGen = new boolean[length];

        for (int i = 0; i < length; i++) {

            boolean left = false;
            if (i > 0) {
                left = gen[i - 1];
            }
            boolean right = false;
            if (i < length - 1) {
                right = gen[i + 1];
            }

            boolean center = gen[i];

            if (center) {
                // current cell full
                if (left != right) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            } else {
                // current cell empty
                if (left || right) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            }
        }
        return nextGen;
    }

    boolean[] nextGenB(boolean[] gen) {
        int length = gen.length;
        boolean[] nextGen = new boolean[length];

        for (int i = 0; i < length; i++) {
            boolean left = false;
            if (i > 0) {
                left = gen[i - 1];
            }
            boolean right = false;
            if (i < length - 1) {
                right = gen[i + 1];
            }
            boolean center = gen[i];

            if (center) {
                // Occupied cell
                if (right == false) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            } else {
                // Empty cell
                if (left != right) {
                    nextGen[i] = true;
                } else {
                    nextGen[i] = false;
                }
            }
        }
        return nextGen;
    }

    boolean[] readInitialGeneration(int length) {
        boolean[] gen = new boolean[length];

        // Read until 'init_start'
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (token.equals("init_start")) {
                break;
            }
        }

        // Read  until 'init_end'
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (token.equals("init_end")) {
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

            // And determine the next generation
            if ("A".equals(automaton)) {
                gen = nextGenA(gen);
            } else {
                // B
                gen = nextGenB(gen);
            }
        }
    }

    public static void main(String[] args) {
        new ABAutomaton().run();
    }
}

wassup