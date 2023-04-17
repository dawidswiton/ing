package com.dawidswitonmaniakowski.ing.atmsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportGeneratorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    @Test
    void generateReport() throws Exception {
        String given = """
[
   {
     "region": 4,
     "requestType": "STANDARD",
     "atmId": 1
   },
   {
     "region": 1,
     "requestType": "STANDARD",
     "atmId": 1
   },
   {
     "region": 2,
     "requestType": "STANDARD",
     "atmId": 1
   },
   {
     "region": 3,
     "requestType": "PRIORITY",
     "atmId": 2
   },
   {
     "region": 3,
     "requestType": "STANDARD",
     "atmId": 1
   },
   {
     "region": 2,
     "requestType": "SIGNAL_LOW",
     "atmId": 1
   },
   {
     "region": 5,
     "requestType": "STANDARD",
     "atmId": 2
   },
   {
     "region": 5,
     "requestType": "FAILURE_RESTART",
     "atmId": 1
   }
 ]
                                 
                """;
        String expected = """
[
   {
     "region": 1,
     "atmId": 1
   },
   {
     "region": 2,
     "atmId": 1
   },
   {
     "region": 3,
     "atmId": 2
   },
   {
     "region": 3,
     "atmId": 1
   },
   {
     "region": 4,
     "atmId": 1
   },
   {
     "region": 5,
     "atmId": 1
   },
   {
     "region": 5,
     "atmId": 2
   }
 ]
                """;
        MvcResult mvcResult = this.mockMvc.perform(
                        post("/atms/calculateOrder")
                                .content(given)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = mapperBuilder.build();
        AtmTestHelper[] resultResponse = mapper.readValue(expected, AtmTestHelper[].class);
        AtmTestHelper[] expectedResponse = mapper.readValue(response, AtmTestHelper[].class);

        assertThat(resultResponse).isEqualTo(expectedResponse);

    }

    @Test
    void generateReport2() throws Exception {
        String given = """
[
  {
    "region": 1,
    "requestType": "STANDARD",
    "atmId": 2
  },
  {
    "region": 1,
    "requestType": "STANDARD",
    "atmId": 1
  },
  {
    "region": 2,
    "requestType": "PRIORITY",
    "atmId": 3
  },
  {
    "region": 3,
    "requestType": "STANDARD",
    "atmId": 4
  },
  {
    "region": 4,
    "requestType": "STANDARD",
    "atmId": 5
  },
  {
    "region": 5,
    "requestType": "PRIORITY",
    "atmId": 2
  },
  {
    "region": 5,
    "requestType": "STANDARD",
    "atmId": 1
  },
  {
    "region": 3,
    "requestType": "SIGNAL_LOW",
    "atmId": 2
  },
  {
    "region": 2,
    "requestType": "SIGNAL_LOW",
    "atmId": 1
  },
  {
    "region": 3,
    "requestType": "FAILURE_RESTART",
    "atmId": 1
  }
]

                """;
        String expected = """
[
    {
      "region": 1,
      "atmId": 2
    },
    {
      "region": 1,
      "atmId": 1
    },
    {
      "region": 2,
      "atmId": 3
    },
    {
      "region": 2,
      "atmId": 1
    },
    {
      "region": 3,
      "atmId": 1
    },
    {
      "region": 3,
      "atmId": 2
    },
    {
      "region": 3,
      "atmId": 4
    },
    {
      "region": 4,
      "atmId": 5
    },
    {
      "region": 5,
      "atmId": 2
    },
    {
      "region": 5,
      "atmId": 1
    }
  ]
  
                """;
        MvcResult mvcResult = this.mockMvc.perform(
                        post("/atms/calculateOrder")
                                .content(given)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = mapperBuilder.build();
        AtmTestHelper[] resultResponse = mapper.readValue(expected, AtmTestHelper[].class);
        AtmTestHelper[] expectedResponse = mapper.readValue(response, AtmTestHelper[].class);

        assertThat(resultResponse).isEqualTo(expectedResponse);

    }
}