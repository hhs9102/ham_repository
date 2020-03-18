package me.ham.custom.controller;

import java.util.Map;

public interface SimpleController {
    String control(Map<String, String> params, Map<String,Object> model);
}
