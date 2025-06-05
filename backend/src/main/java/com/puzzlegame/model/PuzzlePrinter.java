package com.puzzlegame.model;
import java.util.List;

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

        for(Block block : blockList){
            for(Position position : block.getOccupiedPositions()){
                if(block.isTarget()){
                    displayGrid[position.row][position.col] = "T";
                }else{
                    displayGrid[position.row][position.col] = "B";
                }
            }
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Tile tile = grid[i][j];
                if(tile.getType() == Tile.TileType.GOAL){
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