package com.dawidswitonmaniakowski.ing.transactions;

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
    "debitAccount": "32309111922661937852684864",
    "creditAccount": "06105023389842834748547303",
    "amount": 10.90
  },
  {
    "debitAccount": "31074318698137062235845814",
    "creditAccount": "66105036543749403346524547",
    "amount": 200.90
  },
  {
    "debitAccount": "66105036543749403346524547",
    "creditAccount": "32309111922661937852684864",
    "amount": 50.10
  }
]
""";
        String expected = """
[
  {
    "account": "06105023389842834748547303",
    "debitCount": 0,
    "creditCount": 1,
    "balance": 10.90
  },
  {
    "account": "31074318698137062235845814",
    "debitCount": 1,
    "creditCount": 0,
    "balance": -200.90
  },
  {
    "account": "32309111922661937852684864",
    "debitCount": 1,
    "creditCount": 1,
    "balance": 39.20
  },
  {
    "account": "66105036543749403346524547",
    "debitCount": 1,
    "creditCount": 1,
    "balance": 150.80
  }
]
""";
        MvcResult mvcResult = this.mockMvc.perform(
                        post("/transactions/report")
                                .content(given)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = mapperBuilder.build();
        AccountTestHelper[] resultResponse = mapper.readValue(expected, AccountTestHelper[].class);
        AccountTestHelper[] expectedResponse = mapper.readValue(response, AccountTestHelper[].class);
        assertThat(resultResponse).isEqualTo(expectedResponse);
    }
}