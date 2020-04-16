package me.ham.propertyeditor;

public enum Level {
    GOLD(3,null)
    ,SILVER(2,GOLD)
    ,BASIC(1,SILVER);

    int intValue;
    Level value;

    Level(int intValue, Level value) {
        this.intValue = intValue;
        this.value = value;
    }

    public int intValue(){
        return intValue;
    }

    public static Level valueOf(int intValue){
        if(1 == intValue) {
            return BASIC;
        }else if(2 == intValue){
            return SILVER;
        }else if(3 == intValue){
            return GOLD;
        }else{
            throw new AssertionError("Unknown value : "+intValue);
        }
    }
}
