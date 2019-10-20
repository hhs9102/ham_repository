package me.ham.async.coffee;

import java.util.concurrent.Future;

public interface CoffeeService {
    int getPrice(String name);
    Future<Integer> getPriceAsync(String name);
    Future<Integer> getDiscountPriceAsync(Integer price);

}
