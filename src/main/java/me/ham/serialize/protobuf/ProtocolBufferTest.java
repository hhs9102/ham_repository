package me.ham.serialize.protobuf;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class ProtocolBufferTest {

    static final String serializedFile = "/Users/hamhosik/Documents/protocol-buf-file/userProto";
    public static void main(String[] args) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i=0; i<10000 ; i++){
            serializeByProtocolBuffer();
            deserializeByProtocolBuffer();
        }
        stopWatch.stop();
        log.info("직렬화, 역직렬화 1000번 실행 시간 : {}",stopWatch.getTime());
    }

    private static void deserializeByProtocolBuffer() throws IOException{
        File file = new File(serializedFile);
        try(
            FileInputStream  fileInputStream =  new FileInputStream(file);
            ){
            UserProto.User parsedUser = UserProto.User.parseFrom(fileInputStream);
//          log.info("########bytes to Object(from File)######");
//          log.info("{}",parsedUser);
        }
    }

    private static void serializeByProtocolBuffer() throws IOException {
        UserProto.User u = UserProto.User.newBuilder().setAge(29).setName("hosik ham").build();
        byte[] bytes = u.toByteArray();
        try(
            FileOutputStream fileOutputStream = new FileOutputStream(new File(serializedFile));
            ){
            fileOutputStream.write(bytes);
            UserProto.User parsedUser = UserProto.User.parseFrom(bytes);
//          log.info("########bytes to Object######");
//          log.info("{}",parsedUser);
        }
    }
}
