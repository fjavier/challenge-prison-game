package dev.francisco.briceno.prisongame.controller;

import dev.francisco.briceno.prisongame.dto.MazeDto;
import dev.francisco.briceno.prisongame.service.MazeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class GameController {

    @Autowired
    private final MazeService mazeService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/prisoner")
    public void canScape(@Validated @RequestBody MazeDto maze) {
        maze.getPrison().forEach( structure -> {
            System.out.println(structure);
        });
    }
}
