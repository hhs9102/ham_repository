package me.ham.aop.annotation.proxy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("proxyUserByAnnotation")
public class ProxyUser implements ProxyUserInterface{
    private String username = "username";
    private boolean isProxyUserByAnnotation;
}
