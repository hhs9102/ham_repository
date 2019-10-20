package me.ham.async.coffee;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Coffee {

    String name;
    int price;
}
