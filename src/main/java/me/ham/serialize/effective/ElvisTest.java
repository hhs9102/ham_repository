package me.ham.serialize.effective;

import me.ham.serialize.User;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

public class ElvisTest {

    static final String serializedFile = "/Users/hamhosik/Documents/protocol-buf-file/elvis";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        byte[] byteArray;
        File file = new File(serializedFile);

        //Effective Java Item 89
        /*
        Elvis 클래스는 싱글톤 클래스로 만들어졌다.
        -> 그러나 직렬화를 통해 하나의 객체만 존재하는 rule을 깰 수 있다.

        기본적인 직렬화 내용
        역직렬화 과정에서 trasient로 선언되지 않은 필드는 readResolve가 호출되기 전에 역직렬화 된다.
        (readResolve는 ObjectInputStrean을 읽는, readObject 메서드 호출 이후에 호출됨.)
        따라서 '잘 조작된 스트림'을 통해 기존에는 참조할 수 없었던 필드를 참조 시킨다면 2개의 인스턴스를 만들어 낼 수 있다.
        ('잘 조작된 스트림'이란 해당 소스에서 serializedFile로 선언된 파일의 byte stream을 조작한 것이다.)
        -> Elvis가 역직렬화되는 과정에서 ElvisStealer의 impersonator를 참조하게 byte stream을 조작한다면
           해당 인스턴스는 참조가 되고 있기에 gc의 대상이 되지 않는다. 따라서 인스턴스가 생성된다.
           -> 해당 ElvisStealer.impersonator를 다시 Elvis impersonator로 참조하게 되면서
              조작된 스트림에 의해 새로 생성된 인스턴스와 기존에 정상적으로 생성된 인스턴스
              즉, 2개의 인스턴스가 만들어진다.

        0xac, 0x00, 0x05 등의 byte값을 변경하면서 실행을 해야하는 내용이라 해당 테스트는 진행하지 않음.
        */
        byteArray = IOUtils.toByteArray(new FileInputStream(file));
        Elvis elvis = (Elvis)Elvis.deserialize(byteArray);
        Elvis impersonator = ElvisStealer.impersonator;

        elvis.printFavorites();
        impersonator.printFavorites();
    }
}
