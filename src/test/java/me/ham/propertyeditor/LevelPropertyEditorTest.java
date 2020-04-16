package me.ham.propertyeditor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LevelController.class})
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LevelPropertyEditorTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void propertyEditorTest(){
        LevelPropertyEditor propertyEditor = new LevelPropertyEditor();
        propertyEditor.setValue(Level.BASIC);
        Assert.assertEquals("1", propertyEditor.getAsText());


        propertyEditor.setAsText("3");
        Assert.assertEquals(Level.GOLD, (Level) propertyEditor.getValue());
    }

    @Test
    public void levelPropertyGetMappingTest() throws Exception {
        mockMvc.perform(get("/level")
                            .param("level","GOLD"))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("GOLD"));
    }

    /**param 'level'을 '3'으로 전달했을 때
     * 원래 Level 파라미터와 맞지 않아 500 에러를 리턴하지만
     * CustomPropertyEditor를 WebDataBinder에 regist하여 해당 customProperty
     * setAsText 메서드에서 Level로 매칭시켜주어 올바른 Level 형태로 파라미터가 전달된다.
     * @throws Exception
     */
    @Test
    public void levelPropertyByIntValueGetMappingTest() throws Exception {
        mockMvc.perform(get("/level")
                            .param("level","3"))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("GOLD"));
    }

}