package com.dawidswitonmaniakowski.ing.onlinegame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlanGeneratorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    @Test
    void generateReport() throws Exception {
        String given = """
                                {
                                   "groupCount": 6,
                                   "clans": [
                                     {
                                       "numberOfPlayers": 4,
                                       "points": 50
                                     },
                                     {
                                       "numberOfPlayers": 2,
                                       "points": 70
                                     },
                                     {
                                       "numberOfPlayers": 6,
                                       "points": 60
                                     },
                                     {
                                       "numberOfPlayers": 1,
                                       "points": 15
                                     },
                                     {
                                       "numberOfPlayers": 5,
                                       "points": 40
                                     },
                                     {
                                       "numberOfPlayers": 3,
                                       "points": 45
                                     },
                                     {
                                       "numberOfPlayers": 1,
                                       "points": 12
                                     },
                                     {
                                       "numberOfPlayers": 4,
                                       "points": 40
                                     }
                                   ]
                                 }
                                 
                """;
        String expected = """
                                [
                                   [
                                     {
                                       "numberOfPlayers": 2,
                                       "points": 70
                                     },
                                     {
                                       "numberOfPlayers": 4,
                                       "points": 50
                                     }
                                   ],
                                   [
                                     {
                                       "numberOfPlayers": 6,
                                       "points": 60
                                     }
                                   ],
                                   [
                                     {
                                       "numberOfPlayers": 3,
                                       "points": 45
                                     },
                                     {
                                       "numberOfPlayers": 1,
                                       "points": 15
                                     },
                                     {
                                       "numberOfPlayers": 1,
                                       "points": 12
                                     }
                                   ],
                                   [
                                     {
                                       "numberOfPlayers": 4,
                                       "points": 40
                                     }
                                   ],
                                   [
                                     {
                                       "numberOfPlayers": 5,
                                       "points": 40
                                     }
                                   ]
                                 ]
                """;
        MvcResult mvcResult = this.mockMvc.perform(
                        post("/onlinegame/calculate")
                                .content(given)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = mapperBuilder.build();
        TypeReference<List<List<Clan>>> ref = new TypeReference<>() {};
        List<List<Clan>> resultResponse = mapper.readValue(expected, ref);
        List<List<Clan>> expectedResponse = mapper.readValue(response, ref);

        assertThat(resultResponse).isEqualTo(expectedResponse);

    }
}