import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test UniversalAutomaton.
 * 
 * @author Milan Versteegh
 * @ID 2136279
 * @author Egor Bryantsev
 * @ID 2087782
 */
public class UniversalAutomatonTest {
    UniversalAutomaton automaton = new UniversalAutomaton();

    @Test
    public void testGenToString() {
        // Test empty generation
        assertEquals("   ", automaton.genToString(new boolean[]{false, false, false}));
        
        // Test fully occupied generation
        assertEquals("***", automaton.genToString(new boolean[]{true, true, true}));
        
        // Test mixed generation
        assertEquals("* *", automaton.genToString(new boolean[]{true, false, true}));
    }

    @Test
    public void testNextGen() {
        // Test rule sequence 01011110 (Rule 94 in Wolfram's numbering)
        boolean[] ruleSequence = {false, true, false, true, true, true, true, false};
        
        // Test case 1: All cells empty
        boolean[] gen = {false, false, false, false, false};
        boolean[] expected = {false, false, false, false, false};
        assertArrayEquals(expected, automaton.nextGen(ruleSequence, gen));
        
        // Test case 2: Single cell occupied
        gen = new boolean[]{false, false, true, false, false};
        expected = new boolean[]{false, true, true, true, false};
        assertArrayEquals(expected, automaton.nextGen(ruleSequence, gen));
        
        // Test case 3: All cells occupied
        gen = new boolean[]{true, true, true, true, true};
        expected = new boolean[]{true, false, false, false, true};
        assertArrayEquals(expected, automaton.nextGen(ruleSequence, gen));
    }
}