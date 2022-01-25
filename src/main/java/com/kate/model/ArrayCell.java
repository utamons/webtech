package com.kate.model;

public class ArrayCell {
   private int value;
   private String style="";

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public ArrayCell copy(){
        ArrayCell copy=new ArrayCell();
        copy.setValue(this.value);
        copy.setStyle(this.style);
        return copy;
    }
}
