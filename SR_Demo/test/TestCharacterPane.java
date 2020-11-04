/*
 * Author: Kaden Payne
 * Date: 9/2/2020
 * 
 * Testing the X's and Y's of p1, p2, and p3.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kjpay
 */
public class TestCharacterPane {
    @Test
    public void TestCharacterPane() {
        CharacterPane test = new CharacterPane();
        assertEquals(1000, test.getP1X(), 0.1);
        assertEquals(470, test.getP1Y(), 0.1);
        assertEquals(970, test.getP2X(), 0.1);
        assertEquals(530, test.getP2Y(), 0.1);
        assertEquals(1030, test.getP3X(), 0.1);
        assertEquals(530, test.getP3Y(), 0.1);
    }
    
}
