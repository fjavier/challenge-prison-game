package dev.francisco.briceno.prisongame.controller;

import dev.francisco.briceno.prisongame.dto.MazeDto;
import dev.francisco.briceno.prisongame.service.MazeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@RestController
public class GameController {

    private final MazeService mazeService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/prisoner")
    public void canScape(@Validated @RequestBody MazeDto maze) {
        if(!mazeService.canEscape(maze.getPrison())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
