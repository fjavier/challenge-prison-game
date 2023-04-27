package dev.francisco.briceno.prisongame.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.francisco.briceno.prisongame.dto.MazeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.PrintingResultHandler;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    private static final String URL = "/prisoner";

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    void canScape_shouldReturnOk() throws Exception {

        List<String> prisonStructure = Arrays.asList(
                                                    "||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |",
                                                    "|   |   |","|||||||||");
        MazeDto mazeDtoRequest = new MazeDto();
        mazeDtoRequest.setPrison(prisonStructure);

        String request = objectMapper.writeValueAsString(mazeDtoRequest);

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }
}