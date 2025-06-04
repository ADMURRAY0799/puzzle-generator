package com.puzzlegame.model;

public enum Orientation{
    HORIZONTAL,
    VERTICAL;


public boolean allows(Direction direction){
    if(this == HORIZONTAL){
        return direction == Direction.LEFT || direction == Direction.RIGHT;
    }else{
        return direction == Direction.UP || direction == Direction.DOWN;
        }
    }
}