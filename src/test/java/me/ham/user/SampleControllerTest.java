package me.ham.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class SampleControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Value("${ham.name}")
    private String hamName;

    @Autowired
    Environment environment;

    @Test
    public void hello() throws  Exception {
        mockMvc.perform(get("/hateoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void readPropertyTest(){
        assertEquals("hosik", environment.getProperty("ham.name"));
        Assert.assertEquals(hamName,"hosik");
    }
}
