package me.ham.serialize.effective;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

public class Elvis implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Elvis INSTANCE = new Elvis();

    private  Elvis() {
    }
    private String[] favoriteSongs = {"First Song", "Twice Song"};

    public void printFavorites(){
        System.out.println(Arrays.toString(favoriteSongs));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        s.defaultReadObject();
    }

    private Object readResolve(){
        return INSTANCE;
    }

    static Object deserialize(byte[] sf){
        try{
            ElvisStealer.impersonator = INSTANCE;
            return new ObjectInputStream(new ByteArrayInputStream(sf)).readObject();
        }catch (IOException | ClassNotFoundException e){
            throw new IllegalArgumentException(e);
        }
    }
}
