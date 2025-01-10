package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsersDatabaseTest {

    private UsersDatabase usersDatabase;
    @BeforeEach
    void setUp() {
        usersDatabase = new UsersDatabase();
    }

    @Test
    void testFindFirstProperFractionByUserIdWith1() {
        Fraction fraction = usersDatabase.findFirstProperFractionByUserId("1");
        assertNotNull(fraction, "Fraction should not be null for user 1");
        assertEquals(0, fraction.getNumerator());
        assertEquals(1, fraction.getDenominator());
    }

    @Test
    void testFindFirstProperFractionByUserIdWith2() {
        Fraction fraction = usersDatabase.findFirstProperFractionByUserId("2");
        assertNotNull(fraction, "Fraction should not be null for user 2");
        assertEquals(-1, fraction.getNumerator());
        assertEquals(5, fraction.getDenominator());
    }

    @Test
    void testFindFirstProperFractionByUserIdWith3() {
        Fraction fraction = usersDatabase.findFirstProperFractionByUserId("3");
        assertNotNull(fraction, "Fraction should not be null for user 3");
        assertEquals(1, fraction.getNumerator());
        assertEquals(5, fraction.getDenominator());
    }

    @Test
    void testFindFirstProperFractionByUserIdWith4() {
        Fraction fraction = usersDatabase.findFirstProperFractionByUserId("4");
        assertNull(fraction, "Fraction should be null for user 4");
    }

    @Test
    void testFindFirstProperFractionByUserIdWith5() {
        Fraction fraction = usersDatabase.findFirstProperFractionByUserId("5");
        assertNotNull(fraction, "Fraction should not be null for user 5");
        assertEquals(0, fraction.getNumerator());
        assertEquals(1, fraction.getDenominator());
    }

    @Test
    void testFindFirstProperFractionByUserIdWith6() {
        Fraction fraction = usersDatabase.findFirstProperFractionByUserId("6");
        assertNull(fraction, "Fraction should be null for user 6");
    }

    @Test
    void testFindFirstProperFractionByUserIdAndNonExisting() {
        Fraction fraction = usersDatabase.findFirstProperFractionByUserId("999");
        assertNull(fraction, "Fraction should be null for non-existing user id");
    }
}
