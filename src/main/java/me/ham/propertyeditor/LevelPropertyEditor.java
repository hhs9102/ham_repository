package me.ham.propertyeditor;

import java.beans.PropertyEditorSupport;

public class LevelPropertyEditor extends PropertyEditorSupport {
    @Override
    public String getAsText(){
        return String.valueOf(((Level)this.getValue()).intValue);
    }

    @Override
    public void setAsText(String text){
        this.setValue(Level.valueOf(Integer.parseInt(text.trim())));
    }
}
