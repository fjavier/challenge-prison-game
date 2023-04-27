package dev.francisco.briceno.prisongame.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeHelperTest {

    private final static String[] PRISON = {
            "||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |","|   |   |","|||||||||"
    };
    private static final String[] PRISON_1 = { "P   S" };
    private static final String[] PRISON_2 = { "P| S" };
    private static final String[] PRISON_3 = { "P>  S"};
    private static final String[] PRISON_4 = { "P >  S", "  |   "};
    private static final String[] PRISON_5 = {
            "||||||S||",
            "|P ||   |",
            "||  | | |",
            "|v| | < |",
            "| |   | |",
            "|   |   |",
            "|||||||||"
    };

    private static final String[] PRISON6 = {
            "| | | | | | |","| P | | | S |","|   | | | | |","|||  <| | | |"
    };

    private static final String[] PRISON7 = {"| | | | | | |","| P |v| | S |","|   | | | | |","|||   | | | |"};
    private static final String[] PRISON8 = {"| |>| | |v| |","| P ||<| S  |","|   |   | | |","|||   | | | |"};
    private static final String[] PRISON9 = {"| |>| | |v| |","| P ||<| |  |","|   |    S| |","|||   | | | |"};
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
        assertTrue(MazeHelper.canEscape(PRISON9));

    }

    @Test
    public void testCanEscapeCannotScape(){
        assertFalse(MazeHelper.canEscape(PRISON6));
        assertFalse(MazeHelper.canEscape(PRISON7));
        assertFalse(MazeHelper.canEscape(PRISON8));
    }
}