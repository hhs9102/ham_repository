package me.ham.custom.controller;

import me.ham.custom.adapter.SimpleHandlerAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
/**
 * HandlerAdapter를 구현하여 만든 SimpleHandler를 Component로 빈을 등록했다.
 * 따라서 요청이 왔을때 해당 Adapter가 handle하여 Controller의 control 메서드를 호출한다.
 * Controller의 paramter를 매핑시키고, 추가적으로 파라미터를 가공하여 전달시켜준다.
 * (공통적으로 파라미터를 변동시켜 컨트롤러에 파라미터를 전달하고 싶은경우 사용하면 될 듯)
 */
public class HelloControllerTest{

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SimpleHandlerAdapter adapter;

    @Test
    public void contrllerMappingTest() throws Exception {
        Assert.assertNotNull(adapter);
        mockMvc.perform(get("/adapter/hello")
                .param("name", "hosik"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("thisIsAddedParamInCustomHandler"))
                .andDo(print());
    }
}