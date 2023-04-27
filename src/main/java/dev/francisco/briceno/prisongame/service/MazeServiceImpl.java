package dev.francisco.briceno.prisongame.service;

import dev.francisco.briceno.prisongame.helper.MazeHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MazeServiceImpl implements MazeService {

    @Override
    public boolean canEscape(List<String> prison) {
        return MazeHelper.canEscape(prison.toArray(String[]::new));
    }
}
