package com.puzzlegame.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzlePrinter{

    public void printPuzzleGrid(Tile[][] grid, List <Block> blockList){
        int row = grid.length;
        int col = grid[0].length;

        String[][] displayGrid = new String[row][col];

      

        for (int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                displayGrid[i][j] = ".";
            }     
        }
        // Make labels
        Map <Block, String> blockLabelMap = new HashMap<>();
        int blockCounter = 1;

        for(Block block : blockList){
            if(block.isTarget()){
                blockLabelMap.put(block, "T");
            } else {
                blockLabelMap.put(block, "B" + blockCounter);
                blockCounter++;
            }
        }

        for(Block block : blockList){
            String label = blockLabelMap.get(block);
            for(Position position : block.getOccupiedPositions()){
                displayGrid[position.getRow()][position.getCol()] = label;
            }
        }

        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j].getType() == Tile.TileType.GOAL){
                    displayGrid[i][j] = "G";
                }      
            }   
        }

        for(String[] rowArray : displayGrid){
            //Join all columns in the row with a space separator
            String rowString = String.join(" ", rowArray);
            System.out.println(rowString);
        }
    }
}