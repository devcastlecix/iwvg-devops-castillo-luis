package es.upm.miw.iwvg_devops.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class UserTest {
    private User userEmpty;
    private User user;
    private final String userID = "1";
    private final String userFamilyName = "Castillo";
    private final String userName = "Elera";
    private final List<Fraction> fractions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        fractions.add(new Fraction(1, 1));
        user = new User(userID, userName, userFamilyName, fractions);
        userEmpty = new User();
    }

    @Test
    void testUserInitial() {
        String initialAssert = this.userName.charAt(0) + ".";
        assertEquals(initialAssert, user.initials());
    }

    @Test
    void testUserIDEquals() {
        assertEquals(userID, user.getId());
    }

    @Test
    void testUserEmptyID() {
        assertNull(userEmpty.getId());
    }

    @Test
    void testUserNameEquals() {
        assertEquals(userName, user.getName());
    }

    @Test
    void testUserEmptyName() {
        assertNull(userEmpty.getName());
    }

    @Test
    void testUserEmptySetName() {
        String name = "Luis";
        userEmpty.setName(name);
        assertEquals(name, userEmpty.getName());
    }

    @Test
    void testUserFamilyNameEquals() {
        assertEquals(userFamilyName, user.getFamilyName());
    }

    @Test
    void testUserEmptyFamilyName() {
        assertNull(userEmpty.getFamilyName());
    }

    @Test
    void testUserEmptySetFamilyName() {
        String familyName = "Castillo";
        userEmpty.setFamilyName(familyName);
        assertEquals(familyName, userEmpty.getFamilyName());
    }

    @Test
    void testUserEmptyFractions() {
        assertEquals(0, userEmpty.getFractions().size());
    }

    @Test
    void testUserFractions() {
        assertEquals(1, user.getFractions().size());
    }

    @Test
    void testUserEmptyAddFractionToList() {
        Fraction fraction = new Fraction(1, 2);
        userEmpty.addFraction(fraction);
        assertEquals(1, userEmpty.getFractions().size());
        Fraction userFraction = userEmpty.getFractions().get(0);
        assertEquals(fraction.getNumerator(), userFraction.getNumerator());
        assertEquals(fraction.getDenominator(), userFraction.getDenominator());
    }

    @Test
    void testUserSetFractions() {
        List<Fraction> newFractions = List.of(new Fraction(1, 3), new Fraction(2, 4));
        user.setFractions(newFractions);
        assertEquals(2, user.getFractions().size());
    }

    @Test
    void testUserFullName() {
        String fullNameAssert = userName + " " + userFamilyName;
        assertEquals(fullNameAssert, user.fullName());
    }

    @Test
    void testUserToString() {
        String userString = "User{" +
                "id='" + userID + '\'' +
                ", name='" + userName + '\'' +
                ", familyName='" + userFamilyName + '\'' +
                ", fractions=" + fractions +
                '}';
        assertEquals(userString, user.toString());
    }
}