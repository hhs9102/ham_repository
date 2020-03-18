package me.ham.custom.controller;

import me.ham.custom.annotation.ContentType;
import me.ham.custom.annotation.RequiredParams;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerAdapter;

import java.util.Map;

@Controller
public class HelloController implements SimpleController{
    @Autowired
    ApplicationContext context;

    @ContentType("json")
    @RequiredParams({"name"})
    @Override
    @GetMapping("/adapter/hello")
    public String control(Map<String, String> params, Map<String, Object> model) {
        model.put("message", "Hello "+params.get("name"));

        Map<String, HandlerAdapter> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(
                context, HandlerAdapter.class, true, false);

        matchingBeans.forEach((k, v) -> System.out.printf("%s=%s%n",
                k,
                v.getClass().getSimpleName()));
        return "test";
    }
}
