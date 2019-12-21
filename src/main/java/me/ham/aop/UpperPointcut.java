package me.ham.aop;

import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.stereotype.Component;

@Component
public class UpperPointcut extends NameMatchMethodPointcut {
}
