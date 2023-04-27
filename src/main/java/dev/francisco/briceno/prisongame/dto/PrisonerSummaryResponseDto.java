package dev.francisco.briceno.prisongame.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrisonerSummaryResponseDto {
    private Integer countSuccesfulScape;
    private Integer countUnsuccessfulScape;
    private double ratio;
}
