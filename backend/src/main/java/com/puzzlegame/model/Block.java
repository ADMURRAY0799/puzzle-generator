package com.puzzlegame.model;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private String id; 
    private Position position;
    public int length; 
    private Direction direction;
    private Orientation orientation;
    private boolean isTarget;

    public Block(String id, Position position, int length, Orientation orientation, boolean isTarget){
        this.id = id;
        this.position = position;
        this.length = length;
        this.orientation = orientation;
        this.isTarget = isTarget;
    }

    public List<Position> getOccupiedPositions(){
        List<Position> positions = new ArrayList<>();

        if(position == null || orientation == null){
            return positions;
        }
        positions.add(position);

        if(length == 2){
            if(orientation == Orientation.HORIZONTAL){
                positions.add(new Position(position.getRow(), position.getCol() + 1));
            }else if(orientation == Orientation.VERTICAL){
                positions.add(new Position(position.getRow() + 1 , position.getCol()));
            }else{
                throw new IllegalStateException("Unknown Orientation " + orientation);
                }
            } return positions;
        }

    public boolean isTarget(){
        return isTarget;
    }

    public String getID(){
        return id;
    }

    //Calculate future tile positions based on proposed movement
    public List<Position> getOffsetPositions(int rowDelta, int colDelta){
        List<Position> futurePositions = new ArrayList<>();

        for (Position position : getOccupiedPositions()){
            int newRow = position.getRow() + rowDelta;
            int newCol = position.getCol() + colDelta;
            futurePositions.add(new Position(newRow, newCol));
        }
        return futurePositions;
    }

    // Actually perform the move by delta in allowed direction
    public boolean canMove(Direction direction, int delta, Tile[][] grid) {
        if (!orientation.allows(direction)) {
            return false;
        }
    
        Delta d = getDelta(direction, delta);
    
        List<Position> futurePositions = getOffsetPositions(d.rowDelta, d.colDelta);
    
        for (Position pos : futurePositions) {
            int row = pos.getRow();
            int col = pos.getCol();
    
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
                return false;
            }
    
            Tile tile = grid[row][col];
            if (tile == null || !tile.isWalkable()) {
                return false;
            }
        }
    
        return true;
    }

    public void move(Direction direction, int delta) {
        if (!orientation.allows(direction)) {
            throw new IllegalArgumentException("Invalid move: orientation does not allow " + direction);
        }
    
        Delta d = getDelta(direction, delta);
    
        int newRow = position.getRow() + d.rowDelta;
        int newCol = position.getCol() + d.colDelta;
    
        this.position = new Position(newRow, newCol);
    }

    private Delta getDelta(Direction direction, int delta) {
        return switch (direction) {
            case UP -> new Delta(-delta, 0);
            case DOWN -> new Delta(delta, 0);
            case LEFT -> new Delta(0, -delta);
            case RIGHT -> new Delta(0, delta);
        };
    }
    
    
}
