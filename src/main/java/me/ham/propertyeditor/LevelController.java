package me.ham.propertyeditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LevelController {

    @GetMapping("/level")
    public String levelPropertyEditor(@RequestParam Level level){
        return level.toString();
    }

    /**
     * WebDataBinder에서 Level 파라미터 타입을 바인딩할 때
     * CustomPropertyEditor를 이용하여 매핑을 시도한다.
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());
    }
}
