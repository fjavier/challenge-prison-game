package dev.francisco.briceno.prisongame.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeHelperTest {

    private final static String[] PRISON = new String[]{
            "||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |","|   |   |","|||||||||"
    };
    private static final String[] PRISON_1 = new String[]{ "P   S" };
    private static final String[] PRISON_2 = new String[]{ "P| S" };
    private static final String[] PRISON_3 = new String[]{ "P>  S"};
    private static final String[] PRISON_4 = new String[]{ "P >  S", "  |   "};
    private static final String[] PRISON_5 = new String[]{
            "||||||S||",
            "|P ||   |",
            "||  | | |",
            "|v| | < |",
            "| |   | |",
            "|   |   |",
            "|||||||||"
    };

    @Test
    public void testCanEscapeDirectPath() {
        assertTrue(MazeHelper.canEscape(PRISON_1));
    }

    @Test
    public void testCanEscapeBlockedByWall() {
        assertFalse(MazeHelper.canEscape(PRISON_2));
    }

    @Test
    public void testCanEscapeBlockedByGuard() {
        assertFalse(MazeHelper.canEscape(PRISON_3));
    }

    @Test
    public void testCanEscapeAvoidingGuard() {
        assertFalse(MazeHelper.canEscape(PRISON_4));
    }

    @Test
    public void testCanEscapeSurroundedByGuardsAndWalls() {
        assertTrue(MazeHelper.canEscape(PRISON_5));
    }

    @Test
    public void testCanEscapeSorroundedByGuardsAndWallsComplet(){
        assertTrue(MazeHelper.canEscape(PRISON));
    }
}