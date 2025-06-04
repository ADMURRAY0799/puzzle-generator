package com.puzzlegame.model;
import java.util.List;

public class Puzzle{
    protected final Tile[][] grid;
    public List<Block> blockList;
    public int rows;
    public int cols;
    
    public Puzzle(int rows, int cols, List<Block> blockList){
        this.rows = rows;
        this.cols = cols;
        this.blockList = blockList;
        this.grid = new Tile[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.grid[i][j] = new Tile(j, j, Tile.TileType.EMPTY, false);
            }
        }
    }

    public void processBlocks() {
        for (Block block : blockList) {
            for(Position position: block.getOccupiedPositions()){
                Tile tile = grid[position.getRow()][position.getCol()];
                tile.setType(Tile.TileType.BLOCK);
                tile.setOccupied(true);
            }
            if(block.isTarget()){
                for(Position position: block.getOccupiedPositions()){
                    grid[position.getRow()][position.getCol()].setType(Tile.TileType.GOAL);
                }
            }
        } 
    }

    public Tile getTileAt(Position position){
        return grid[position.getRow()][position.getCol()];
    }

    public Block getBlockByID(String blockID){
        for(Block block : blockList){
            if(block.getID().equals(blockID)){
                return block;
            }
        }return null;
    }

    public Boolean moveBlock(String blockID, int delta){
        Block block = getBlockByID(blockID);
        if(block == null){
            return false;
        }
        if(!block.canMove(null, delta, grid)){
            return false;
        }
        //Clear current positions
        for(Position position : block.getOccupiedPositions()){
            grid[position.getRow()][position.getCol()].setType(Tile.TileType.EMPTY);
            grid[position.getRow()][position.getCol()].setOccupied(false);

            //move block 
            block.move(null, delta);
        }

        //Update grid with new positions
        for(Position position : block.getOccupiedPositions()){
            grid[position.getRow()][position.getCol()].setType(Tile.TileType.BLOCK);
            grid[position.getRow()][position.getCol()].setOccupied(true);
        }return true;
    }

    // Check if puzzle is solved
    public Boolean isSolved(){
        for(Block block : blockList){
            if(block.isTarget()){
                for(Position position : block.getOccupiedPositions()){
                    if(grid[position.getRow()][position.getCol()].getType() == Tile.TileType.GOAL){
                        return true;
                    }
                }
            }
        }return false;
    }

    public void add(Block targetBlock) {
        
    }

 }