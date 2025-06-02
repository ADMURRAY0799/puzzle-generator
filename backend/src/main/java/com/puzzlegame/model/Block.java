package com.puzzlegame.model;

import java.util.List;

public class Block {

    private String id; 
    private Position position;
    public int length; 
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
        if(direction == Direction.LEFT && length == 2 || direction == Direction.RIGHT && length == 2){
            return List.of(position, new Position(position.row, position.col + 1));
        }else if (direction == Direction.UP && length == 2 || direction == Direction.DOWN && length == 2){
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
            if(direction == Direction.LEFT){
                Position second = new Position(newStart.row, newStart.col + 1);
                return List.of(newStart, second);
            }
            if(direction == Direction.RIGHT){
                Position second = new Position(newStart.row, newStart.col - 1);
                return List.of(newStart, second);
            }   
            if(direction == Direction.UP){
                Position second = new Position(newStart.row - 1, newStart.col);
                return List.of(newStart, second);
            }else {
                Position second = new Position(newStart.row + 1, newStart.col);
                return List.of(newStart, second);
            }
        }return List.of(newStart);
    }

    // Actually perform the move by delta in allowed direction
    public void move(int delta){
        if(direction == Direction.LEFT || direction == Direction.RIGHT){
            position.col+= delta;
        }else if(direction == Direction.UP || direction == direction.DOWN){
            position.row+= delta;
        }
    }

    //Validate whether move is legal given the current grid 
    public boolean canMove(int delta, Tile[][] grid){
        int rowDelta = 0;
        int colDelta = 0;
        if(direction == Direction.LEFT || direction == Direction.RIGHT){
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
