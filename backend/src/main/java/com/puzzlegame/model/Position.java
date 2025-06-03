package com.puzzlegame.model;

import java.util.Objects;

public class Position {

    protected int row; 
    protected int col;
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }
    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public Position move(Direction direction){
        switch (direction){
            case UP -> {
                return new Position(this.row -1, this.col);
            }
            case DOWN -> {
                return new Position(this.row +1, this.col);
            }
            case LEFT -> {
                return new Position(this.row, this.col - 1);
            }
            case RIGHT -> {
                return new Position(this.row, this.col + 1);
            }
            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    public Position copy(){
        return new Position(row, col);
    }

     @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
    
}
