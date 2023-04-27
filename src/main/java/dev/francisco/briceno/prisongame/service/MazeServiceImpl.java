package dev.francisco.briceno.prisongame.service;

import dev.francisco.briceno.prisongame.domain.Prisoner;
import dev.francisco.briceno.prisongame.dto.PrisonerSummaryResponseDto;
import dev.francisco.briceno.prisongame.helper.MazeHelper;
import dev.francisco.briceno.prisongame.repository.MazeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MazeServiceImpl implements MazeService {

    private static final String DELIMITER = ", ";
    private final MazeRepository mazeRepository;

    /**
     Determines if the prisoner can escape from the given prison.
     @param prison a List of Strings representing the prison
     @return a boolean indicating whether or not the prisoner can escape
     */
    @Override
    public boolean canEscape(List<String> prison) {
        boolean canScape = MazeHelper.canEscape(prison.toArray(String[]::new));

        Prisoner prisoner = new Prisoner();
        prisoner.setMazze(prison.stream().collect(Collectors.joining(DELIMITER)));
        prisoner.setCanScape(canScape);

        mazeRepository.save(prisoner);
        return canScape;
    }

    /**
     * Retrieves a summary of prisoner escape attempts from the maze repository.
     * @return a PrisonerSummaryResponseDto object containing the count of successful and unsuccessful escape attempts, as well as the success ratio.
     */
    @Override
    public PrisonerSummaryResponseDto getPrisonerSummary(){
        Integer successEscape = mazeRepository.countAllByCanScape(true);
        Integer failedEscape = mazeRepository.countAllByCanScape(false);
        Integer total = successEscape + failedEscape;
        Double ratio = (double) successEscape / (double) total;

        return PrisonerSummaryResponseDto.builder()
                .countSuccesfulScape(successEscape)
                .countUnsuccessfulScape(failedEscape)
                .ratio(ratio)
                .build();
    }
}
