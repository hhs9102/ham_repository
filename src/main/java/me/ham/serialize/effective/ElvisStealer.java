package me.ham.serialize.effective;

import java.io.Serializable;

public class ElvisStealer implements Serializable {
    private static final long serialVersionUID = 1L;
    static Elvis impersonator;
    private Elvis payload;

    private Object readResolve(){
        impersonator = payload;

        return new String[]{"This is Stealer"};
    }
}
