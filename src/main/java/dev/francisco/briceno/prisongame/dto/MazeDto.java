package dev.francisco.briceno.prisongame.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MazeDto {

    @NotEmpty
    private List<String> prison;

}
