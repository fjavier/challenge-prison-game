package dev.francisco.briceno.prisongame.service;

import dev.francisco.briceno.prisongame.dto.PrisonerSummaryResponseDto;

import java.util.List;

public interface MazeService {
     boolean canEscape(List<String> prison);

     PrisonerSummaryResponseDto getPrisonerSummary();
}
