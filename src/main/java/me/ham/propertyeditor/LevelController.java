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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());
    }
}
