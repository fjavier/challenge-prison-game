package dev.francisco.briceno.prisongame.service;

import dev.francisco.briceno.prisongame.domain.Prisoner;
import dev.francisco.briceno.prisongame.dto.PrisonerSummaryResponseDto;
import dev.francisco.briceno.prisongame.repository.MazeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MazeServiceImplTest {

    private static final int TOTAL_FALED_SCAPES = 100;
    private static final int TOTAL_SUCCESSFUL_SCAPES = 40;
    @Autowired
    private MazeService mazeService;

    @MockBean
    private MazeRepository mazeRepository;

    @Test
    void canEscape_shouldReturnTrue_whenPrisonHasSolution() {
        List<String> prisonStructure = Arrays.asList(
                "||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |",
                "|   |   |","|||||||||");
        Prisoner prisoner = new Prisoner();
        prisoner.setCanScape(true);
        prisoner.setMazze(prisonStructure.stream().collect(Collectors.joining(",")));
        Mockito.when(mazeRepository.save(Mockito.any(Prisoner.class))).thenReturn(prisoner);
        Assertions.assertTrue(mazeService.canEscape(prisonStructure));
        Mockito.verify(mazeRepository).save(Mockito.any(Prisoner.class));
    }

    @Test
    void canEscape_shouldReturnFalse_whenPrisonIsBadRequest() {
        List<String> prisonStructure = Arrays.asList(
                "||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |",
                "|   |   |","|||||||||");

        Assertions.assertTrue(mazeService.canEscape(prisonStructure));
    }

    @Test
    void getPrisonerSummary_shouldReturnSummaryDto() {
        Mockito.when(mazeRepository.countAllByCanScape(false)).thenReturn(TOTAL_FALED_SCAPES);
        Mockito.when(mazeRepository.countAllByCanScape(true)).thenReturn(TOTAL_SUCCESSFUL_SCAPES);
        PrisonerSummaryResponseDto prisonerSummary = mazeService.getPrisonerSummary();
        Assertions.assertNotNull(prisonerSummary);
        Assertions.assertEquals(TOTAL_FALED_SCAPES, prisonerSummary.getCountUnsuccessfulScape());
        Assertions.assertEquals(TOTAL_SUCCESSFUL_SCAPES, prisonerSummary.getCountSuccesfulScape());
    }


}