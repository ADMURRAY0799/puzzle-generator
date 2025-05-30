package com.puzzlegame.model;

import java.util.List;

public class Block {

    private String id; 
    private Position position;
    private int length; 
    private Direction direction;
    private boolean isTarget;

    public Block(String id, Position position, int length, Direction direction, boolean isTarget){
        this.id = id;
        this.position = position;
        this.length = length;
        this.direction = direction;
        this.isTarget = isTarget;
    }

    public List<Position> getOccupiedPositions(){
        if(direction == Direction.HORIZONTAL && length == 2){
            return List.of(position, new Position(position.row, position.col + 1));
        }else if (direction == Direction.VERTICAL && length == 2){
                return List.of(position, new Position(position.row + 1, position.col));
            }
            else return (List<Position>) position;
    }

    public boolean isTarget(){
        return isTarget;
    }

    public String getID(){
        return id;
    }

    //Calculate future tile positions based on proposed movement
    public List<Position> getOffsetPositions(int rowDelta, int colDelta){
        Position newStart = new Position(position.row + rowDelta, position.col + colDelta);
        if(length == 2){
            if(direction == Direction.HORIZONTAL){
                Position second = new Position(newStart.row, newStart.col + 1);
                return List.of(newStart, second);
            } else {
                Position second = new Position(newStart.row + 1, newStart.col);
                return List.of(newStart, second);
            }
        }return List.of(newStart);
    }

    // Actually perform the move by delta in allowed direction
    public void move(int delta){
        if(direction == Direction.HORIZONTAL){
            position.col+= delta;
        }else if(direction == Direction.VERTICAL){
            position.row+= delta;
        }
    }

    //Validate whether move is legal given the current grid 
    public boolean canMove(int delta, Tile[][] grid){
        int rowDelta = 0;
        int colDelta = 0;
        if(direction == Direction.HORIZONTAL){
             colDelta = delta;
        }else{
             colDelta = 0;
        }
        List<Position> futurePositions = getOffsetPositions(rowDelta, colDelta);

        for(Position position: futurePositions){
            int row = position.getRow();
            int col = position.getCol();

            if(row < 0 || row >= grid.length || col < 0 || col > grid.length){
                return false;
            }

            Tile tile = grid[row][col];
            if(tile == null || tile.isWalkable() == false){
                return false;
            }
        }
       return true;
    }
       
}
