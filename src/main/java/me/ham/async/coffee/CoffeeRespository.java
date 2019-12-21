package me.ham.async.coffee;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CoffeeRespository {

    public Map<String, Coffee> coffeeMap = new HashMap<>();

    @PostConstruct
    public void init(){
        coffeeMap.put("latte", Coffee.builder().name("latte").price(5100).build());
        coffeeMap.put("americano", Coffee.builder().name("americano").price(4100).build());
        coffeeMap.put("choco", Coffee.builder().name("choco").price(4600).build());
        coffeeMap.put("choco", Coffee.builder().name("choco").price(4600).build());
    }

    public int getPriceByName(String name){

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        return coffeeMap.get(name).getPrice();
    }
}
