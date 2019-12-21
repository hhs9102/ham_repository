package me.ham.circuitbreaker.resilience.controller.connector;

import org.springframework.stereotype.Service;

public interface Connector {

    String sucess();

    String failure();

    String waitting(long ms);
}
