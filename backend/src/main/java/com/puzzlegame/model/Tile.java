package com.puzzlegame.model;

/**
 * Tile.java
 * 
 * Represents a single square (cell) on the puzzle grid.
 * 
 * Each Tile holds:
 * - Its position in the grid (row, col)
 * - Its current type (EMPTY, BLOCK, PLAYER, GOAL)
 * - A flag indicating whether it's currently occupied
 * 
 * The Tile class also provides:
 * - Utility methods to check walkability (can the player move onto it?)
 * - Getters and setters for relevant attributes
 * 
 * Used as the fundamental building block of the grid in Puzzle.java.
 */




public class Tile{

    private int row;
    private int col;
    private TileType type;
    private boolean isOccupied;

    public Tile(int row, int col, TileType type, boolean isOccupied){
        this.row = row;
        this.col = col;
        this.type = type;
        this.isOccupied = isOccupied;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public TileType getType(){
        return type;
    }

    public void setType(TileType type){
        this.type = type;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }

    public boolean isWalkable(){
        if(type == TileType.EMPTY || type == TileType.GOAL){
            return true;
        }return false;
    }

    public enum TileType{
        EMPTY, PLAYER, BLOCK, GOAL; 
    }
   
}   