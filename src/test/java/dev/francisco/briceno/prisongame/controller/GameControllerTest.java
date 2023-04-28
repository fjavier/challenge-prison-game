package dev.francisco.briceno.prisongame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.francisco.briceno.prisongame.dto.MazeDto;
import dev.francisco.briceno.prisongame.dto.PrisonerSummaryResponseDto;
import dev.francisco.briceno.prisongame.service.MazeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    private static final String URL_CANSCAPE_ENDPOINT = "/prisoner";

    private static final String URL_STATUS_ENDPOINT = "/stats";
    private static final String[] MAZE1 = {"||||||S||", "|P ||   |", "||  | | |", "|v| | < |", "| |   | |","|   |   |", "|||||||||"};

    private static final String[] MAZE2 = {"| | | | | | |","| P |v| | S |","|   | | | | |","|||   | | | |"};


    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MazeService mazeService;

    @Test
    void canScape_shouldReturnOk() throws Exception {

        List<String> prisonStructure = Arrays.asList(MAZE1);
        MazeDto mazeDtoRequest = new MazeDto();
        mazeDtoRequest.setPrison(prisonStructure);

        String request = objectMapper.writeValueAsString(mazeDtoRequest);
        Mockito.when(mazeService.canEscape(Mockito.anyList())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CANSCAPE_ENDPOINT)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    void canScape_shouldReturnForbidden_whenPrisonerCanNotEscap() throws Exception {

        List<String> prisonStructure = Arrays.asList(MAZE2);
        MazeDto mazeDtoRequest = new MazeDto();
        mazeDtoRequest.setPrison(prisonStructure);

        String request = objectMapper.writeValueAsString(mazeDtoRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL_CANSCAPE_ENDPOINT)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void canScape_shouldReturnBadRequest_whenPrissonIsEmpty() throws Exception {
        MazeDto mazeDtoRequest = new MazeDto();
        String request = objectMapper.writeValueAsString(mazeDtoRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL_CANSCAPE_ENDPOINT)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getStatus_shouldReturnValidResponse() throws Exception {
        PrisonerSummaryResponseDto summary = PrisonerSummaryResponseDto
                .builder()
                .countUnsuccessfulScape(100)
                .countSuccesfulScape(40)
                .ratio(0.28).build();

        Mockito.when(mazeService.getPrisonerSummary()).thenReturn(summary);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL_STATUS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}