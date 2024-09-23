import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test ABAutomaton.
 * 
 * TODO 3: Fill in your names and student IDs
 * @author NAME
 * @id ID
 * @author NAME
 * @id ID
 */
public class ABAutomatonTestGPT {

    ABAutomatonGPT automaton = new ABAutomatonGPT();

    @Test
    public void testGenToString() {
        // Test with all empty cells (all false)
        assertEquals("     ", automaton.genToString(new boolean[] { false, false, false, false, false }));
        // Test with all occupied cells (all true)
        assertEquals("*****", automaton.genToString(new boolean[] { true, true, true, true, true }));
        // Test with alternating pattern
        assertEquals("* * *", automaton.genToString(new boolean[] { true, false, true, false, true }));
        // Test with first cell occupied and rest empty
        assertEquals("*    ", automaton.genToString(new boolean[] { true, false, false, false, false }));
        // Test with last cell occupied and rest empty
        assertEquals("    *", automaton.genToString(new boolean[] { false, false, false, false, true }));
    }

    @Test
    public void testNextGenA() {
        // Test: Single occupied cell in the middle should stay occupied (no neighbors)
        assertArrayEquals(new boolean[] { false, false, false }, automaton.nextGenA(new boolean[] { false, true, false }));
        
        // Test: Single occupied cell with both neighbors empty stays empty
        assertArrayEquals(new boolean[] { false, false, false }, automaton.nextGenA(new boolean[] { true, false, false }));
        
        // Test: Single occupied cell with one neighbor occupied should remain occupied
        assertArrayEquals(new boolean[] { false, true, false }, automaton.nextGenA(new boolean[] { true, false, true }));
        
        // Test: All cells occupied but should reduce to two in the next generation
        assertArrayEquals(new boolean[] { true, false, true }, automaton.nextGenA(new boolean[] { true, true, true }));
        
        // Test: Cells with no neighbors should become empty
        assertArrayEquals(new boolean[] { false, false, false }, automaton.nextGenA(new boolean[] { true, false, false }));
        
        // Test: Alternating pattern should change based on neighbors
        assertArrayEquals(new boolean[] { false, true, false, true, false }, automaton.nextGenA(new boolean[] { true, false, true, false, true }));
        
        // Test: First and last cell rules (border case handling)
        assertArrayEquals(new boolean[] { false, true, false }, automaton.nextGenA(new boolean[] { true, true, false }));
        
        // Test: Larger array with specific patterns
        assertArrayEquals(new boolean[] { true, false, true, false }, automaton.nextGenA(new boolean[] { true, true, true, true }));
    }

    @Test
    public void testNextGenB() {
        // Test: Single occupied cell in the middle should stay occupied since right neighbor is empty
        assertArrayEquals(new boolean[] { false, true, false }, automaton.nextGenB(new boolean[] { false, true, false }));
        
        // Test: Cell with right neighbor occupied should become empty
        assertArrayEquals(new boolean[] { false, false, false }, automaton.nextGenB(new boolean[] { true, true, true }));
        
        // Test: Cell with left neighbor occupied and right neighbor empty should become occupied
        assertArrayEquals(new boolean[] { false, true, false }, automaton.nextGenB(new boolean[] { true, false, false }));
        
        // Test: Both neighbors are empty, cell remains empty
        assertArrayEquals(new boolean[] { false, false, false }, automaton.nextGenB(new boolean[] { false, false, false }));
        
        // Test: Only one neighbor occupied and current empty, should become occupied
        assertArrayEquals(new boolean[] { false, true, false }, automaton.nextGenB(new boolean[] { true, false, false }));
        
        // Test: Alternating pattern changes based on right-hand rule
        assertArrayEquals(new boolean[] { true, true, false, false }, automaton.nextGenB(new boolean[] { true, false, true, false }));
        
        // Test: First and last cell rules (border handling)
        assertArrayEquals(new boolean[] { true, false, false }, automaton.nextGenB(new boolean[] { true, true, false }));
        
        // Test: Full array of occupied cells should reduce to none
        assertArrayEquals(new boolean[] { false, false, false }, automaton.nextGenB(new boolean[] { true, true, true }));
    }
}
