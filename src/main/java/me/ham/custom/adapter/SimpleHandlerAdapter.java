package me.ham.custom.adapter;

import me.ham.custom.annotation.RequiredParams;
import me.ham.custom.controller.HelloController;
import me.ham.custom.controller.SimpleController;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        try{
            SimpleController target = (SimpleController)(((HandlerMethod) handler).getBean());
        }catch (ClassCastException e){
            return false;
        }
        return true;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method m = ReflectionUtils.findMethod(HelloController.class, "control", Map.class, Map.class);
        RequiredParams requiredParams = AnnotationUtils.getAnnotation(m, RequiredParams.class);

        Map<String, String> params = new HashMap<>();
        for(String param : requiredParams.value()){
            String value = request.getParameter(param);
            if(value == null){
                throw new IllegalStateException();
            }
            params.put(param, value);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("param",params);
        model.put("thisIsAddedParamInCustomHandler","이게되네");

        SimpleController simpleController = (SimpleController)(((HandlerMethod) handler).getBean());
        simpleController.control(params, model);
        return new ModelAndView("test", model);
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }
}
