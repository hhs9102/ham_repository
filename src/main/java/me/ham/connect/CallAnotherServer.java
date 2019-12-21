package me.ham.connect;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/call")
public class CallAnotherServer {

    @RequestMapping("")
    public String callAnother(){
        RestTemplate restTemplate = new RestTemplate((uri, httpMethod) -> {
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setReadTimeout(2000);
           return simpleClientHttpRequestFactory.createRequest(uri,httpMethod);
        });

        String map = restTemplate.getForObject("http://localhost:8090/resilience/success", String.class);
        System.out.println(map.toString());
        return "";
    }
}
