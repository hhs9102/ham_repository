package me.ham.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Data
@ToString
public class User implements Serializable {
    private String name;
    private int age;
}
