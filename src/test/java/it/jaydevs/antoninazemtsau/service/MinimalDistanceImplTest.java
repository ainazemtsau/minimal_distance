package it.jaydevs.antoninazemtsau.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimalDistanceImplTest {

    public static final String LONG_STR_1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public static final String LONG_STRING_2 = "Eget nunc scelerisque viverra mauris. Eu sem integer vitae justo eget magna. Magna eget est lorem ipsum dolor sit. Ultricies mi eget mauris pharetra. Ut venenatis tellus in metus vulputate eu scelerisque felis. Elementum eu facilisis sed odio. Eleifend mi in nulla posuere sollicitudin aliquam. Accumsan sit amet nulla facilisi morbi tempus. In tellus integer feugiat scelerisque varius morbi enim nunc faucibus. Et netus et malesuada fames ac turpis egestas. Mauris pharetra et ultrices neque ornare aenean euismod elementum. Amet purus gravida quis blandit turpis cursus in hac habitasse. Adipiscing diam donec adipiscing tristique risus nec feugiat. Eu lobortis elementum nibh tellus molestie nunc non.";
    public static final String WORD_1 = "cat";
    public static final String WORD_2 = "roll";
    public static final String STR_WITH_SPECIFIC_CHARACTER_1 = "\\.[]{}()<>*+-=!?^$|";
    public static final String STRING_WITH_SPECIFIC_CHARACTER_2 = "\\.[]{}()<>cat*+-=!?^$|";
    private final MinimalDistanceService minimalDistance = new MinimalDistanceServiceImpl();
    @Test
    @DisplayName("Should return minimal distance between two words cat and dog equals 3")
    void minimalDistanceBetweenCatAndDog() {
        assertEquals(3, minimalDistance.minimalDistance(WORD_1, "dog"));
    }

    @Test
    @DisplayName("Should return minimal distance between two words rock and roll equals 4")
    void minimalDistanceBetweenRockAndRoll() {
        assertEquals(2, minimalDistance.minimalDistance("rock", WORD_2));
    }

    @Test
    @DisplayName("Should return minimal distance between two words cat and roll equals 4")
    void minimalDistanceBetweenCatAndRoll() {
        assertEquals(4, minimalDistance.minimalDistance(WORD_1, WORD_2));
    }

    @Test
    @DisplayName("Should return minimal distance between empty string and cat equals 3")
    void minimalDistanceBetweenEmptyStringAndCat() {
        assertEquals(3, minimalDistance.minimalDistance("", WORD_1));
    }

    @Test
    @DisplayName("Should return minimal distance between cat and empty string equals 3")
    void minimalDistanceBetweenCatAndEmptyString() {
        assertEquals(3, minimalDistance.minimalDistance(WORD_1, ""));
    }

    @Test
    @DisplayName("Should return minimal distance between cat and empty string equals 3")
    void minimalDistanceBetweenVeryLongStrings() {
        assertEquals(515, minimalDistance.minimalDistance(LONG_STR_1, LONG_STRING_2));
    }
    @Test
    @DisplayName("Should return minimal distance between cat and empty string equals 3")
    void minimalDistanceBetweenStrWithSpecificCharacters() {
        assertEquals(3, minimalDistance.minimalDistance(STR_WITH_SPECIFIC_CHARACTER_1, STRING_WITH_SPECIFIC_CHARACTER_2));
    }
    @Test()
    @DisplayName("Should return minimal distance between cat and empty string equals 3")
    void minimalDistanceWithNullParams() {
        assertThrows(IllegalArgumentException.class, () -> minimalDistance.minimalDistance("", null));
    }
}