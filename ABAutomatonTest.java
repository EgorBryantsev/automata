import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test ABAutomaton.
 * 
 * TODO 3: Fill in your names and student IDs
 * 
 * @author NAME
 * @id ID
 * @author NAME
 * @id ID
 */
public class ABAutomatonTest {
    ABAutomaton automaton = new ABAutomaton();

    /**
     * Tests the genToString method with various scenarios.
     */
    @Test
    public void testGenToString() {
        // Test with empty array
        boolean[] gen1 = {};
        assertEquals("", automaton.genToString(gen1));

        // Test with all occupied cells
        boolean[] gen2 = {true, true, true};
        assertEquals("***", automaton.genToString(gen2));

        // Test with all empty cells
        boolean[] gen3 = {false, false, false};
        assertEquals("   ", automaton.genToString(gen3));

        // Test with mixed cells
        boolean[] gen4 = {true, false, true, false};
        assertEquals("* * ", automaton.genToString(gen4));

        // Test with single occupied cell
        boolean[] gen5 = {true};
        assertEquals("*", automaton.genToString(gen5));
    }

    /**
     * Tests the nextGenA method with various scenarios.
     */
    @Test
    public void testNextGenA() {
        // Test with a single occupied cell
        boolean[] gen1 = {true};
        boolean[] expected1 = {false};
        assertArrayEquals(expected1, automaton.nextGenA(gen1));

        // Test with two occupied cells
        boolean[] gen2 = {true, true};
        boolean[] expected2 = {true, true};
        assertArrayEquals(expected2, automaton.nextGenA(gen2));

        // Test with three occupied cells
        boolean[] gen3 = {true, true, true};
        boolean[] expected3 = {true, false, true};
        assertArrayEquals(expected3, automaton.nextGenA(gen3));

        // Test with all empty cells
        boolean[] gen4 = {false, false, false};
        boolean[] expected4 = {false, false, false};
        assertArrayEquals(expected4, automaton.nextGenA(gen4));

        // Test with mixed cells
        boolean[] gen5 = {true, false, true};
        boolean[] expected5 = {false, true, false};
        assertArrayEquals(expected5, automaton.nextGenA(gen5));

        // Test with occupied cell at the edge
        boolean[] gen6 = {true, false, false};
        boolean[] expected6 = {false, true, false};
        assertArrayEquals(expected6, automaton.nextGenA(gen6));

        // Test with empty cell with one occupied neighbor
        boolean[] gen7 = {false, true, false};
        boolean[] expected7 = {true, true, true};
        assertArrayEquals(expected7, automaton.nextGenA(gen7));

        // Test with a complex pattern
        boolean[] gen8 = {true, false, true, false, true};
        boolean[] expected8 = {false, true, false, true, false};
        assertArrayEquals(expected8, automaton.nextGenA(gen8));
    }

    /**
     * Tests the nextGenB method with various scenarios.
     */
    @Test
    public void testNextGenB() {
        // Test with a single occupied cell
        boolean[] gen1 = {true};
        boolean[] expected1 = {true};
        assertArrayEquals(expected1, automaton.nextGenB(gen1));

        // Test with two occupied cells
        boolean[] gen2 = {true, true};
        boolean[] expected2 = {false, true};
        assertArrayEquals(expected2, automaton.nextGenB(gen2));

        // Test with all empty cells
        boolean[] gen3 = {false, false, false};
        boolean[] expected3 = {false, false, false};
        assertArrayEquals(expected3, automaton.nextGenB(gen3));

        // Test with empty cell having one occupied neighbor
        boolean[] gen4 = {false, true, false};
        boolean[] expected4 = {true, false, true};
        assertArrayEquals(expected4, automaton.nextGenB(gen4));

        // Test with occupied cell with empty right neighbor
        boolean[] gen5 = {true, false};
        boolean[] expected5 = {true, false};
        assertArrayEquals(expected5, automaton.nextGenB(gen5));

        // Test with occupied cell with occupied right neighbor
        boolean[] gen6 = {true, true, false};
        boolean[] expected6 = {false, false, true};
        assertArrayEquals(expected6, automaton.nextGenB(gen6));

        // Test with a complex pattern
        boolean[] gen7 = {true, false, true, false};
        boolean[] expected7 = {true, true, false, true};
        assertArrayEquals(expected7, automaton.nextGenB(gen7));

        // Test with all occupied cells
        boolean[] gen8 = {true, true, true};
        boolean[] expected8 = {false, false, true};
        assertArrayEquals(expected8, automaton.nextGenB(gen8));
    }
}
