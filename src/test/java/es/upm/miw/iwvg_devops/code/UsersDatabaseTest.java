package es.upm.miw.iwvg_devops.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void testFindUserFamilyNameInitialBySomeProperFraction() {
        List<String> initials = usersDatabase.findUserFamilyNameInitialBySomeProperFraction()
                .toList();

        assertEquals(4, initials.size(), "Should have exactly 4 entries with a proper fraction");
        assertIterableEquals(List.of("F", "B", "L", "B"), initials);
    }

    @Test
    void testFindUserFamilyNameBySomeImproperFraction() {
        List<String> flawedResult = usersDatabase
                .findUserFamilyNameBySomeImproperFraction()
                .toList();

        assertIterableEquals(List.of("Fernandez", "Blanco", "López", "Blanco"), flawedResult,
                "Flawed method returns users who have a proper fraction instead of improper ones");
    }

    @Test
    void testFindUserIdBySomeProperFraction() {
        List<String> userIdsWithProper = usersDatabase.findUserIdBySomeProperFraction()
                .toList();

        assertEquals(4, userIdsWithProper.size(),
                "There should be exactly 4 user IDs with a proper fraction");
        assertIterableEquals(List.of("1", "2", "3", "5"), userIdsWithProper,
                "The user IDs should match exactly [1, 2, 3, 5]");
    }
}
