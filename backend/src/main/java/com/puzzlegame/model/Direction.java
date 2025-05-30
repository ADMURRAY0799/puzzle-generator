package com.puzzlegame.model;

public enum Direction{
    HORIZONTAL, VERTICAL; 

    public boolean isHorizontal(){
        return this == HORIZONTAL;  
    }
    
    public boolean isVertical(){
        return this == VERTICAL;
    }
}



