package me.ham.validation.model.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidationRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requestMappingTest() throws Exception {
        String body = "{\n" +
                "\t\"name\" : \"함\"\n" +
                "\t,\"start\" : \"2020-03-09 11:30:44\"\n" +
                "\t,\"end\" : \"2020-03-09 11:40:44\"\n" +
                "\t,\"time\" : \"1:33 PM\"\n" +
                "}";
        mockMvc.perform(get("/validation/basic")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void badRequestTest() throws Exception {
        String body = "{\n" +
                "\t\"name\" : \"함\"\n" +
                "\t,\"start\" : \"2020-03-09 11:30:44\"\n" +
                "\t,\"end\" : \"2020-03-09 11:40:44\"\n" +
                "\t,\"time\" : \"1:33\"\n" +
                "}";
        mockMvc.perform(get("/validation/basic")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}