package dev.francisco.briceno.prisongame.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MazeServiceImplTest {

    @Autowired
    private MazeService mazeService;

    @Test
    void hasSolution() {
        List<String> prisonStructure = Arrays.asList(
                "||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |",
                "|   |   |","|||||||||");

        boolean hasSolution = mazeService.canEscape(prisonStructure);
        Assertions.assertNotNull(hasSolution);
        Assertions.assertTrue(hasSolution);
    }
}