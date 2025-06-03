package com.puzzlegame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.puzzlegame.model.Tile.TileType;

//Class responsible for creating PuzzleStats
public class PuzzleAnalyser{

    public PuzzleStats analyse(Puzzle puzzle){
        int gridSize = calculateGridSize(puzzle);
        int blockCount = calculateBlockCount(puzzle);
        int blockVariety =  calculateBlockVariety(puzzle);
        float mobilityScore = calculateMobilityScore(puzzle);
        int moveEstimate = estimateMinimalMoves(puzzle);
        int winConditionScore = analyseWinCondition(puzzle);

        return new PuzzleStats(gridSize, blockCount, blockVariety, mobilityScore, moveEstimate, winConditionScore);
    }

    public Integer calculateGridSize(Puzzle puzzle){
        return puzzle.rows * puzzle.cols;
    }

    public Integer calculateBlockCount(Puzzle puzzle){
        return puzzle.blockList.size();
    }

    //More block variety = more difficulty. 
    public Integer calculateBlockVariety(Puzzle puzzle){
        Set<Integer> uniqueLengths = Collections.emptySet();
        for(Block block : puzzle.blockList){
            uniqueLengths.add(block.length);
        }return uniqueLengths.size();
    }

    public Float calculateMobilityScore(Puzzle puzzle){
        int totalFreeSpaces = 0;
        int totalPositions = 0;
        
        for(Block block : puzzle.blockList){
            for(Position position : block.getOccupiedPositions()){
                totalPositions += 1;
                for(Direction direction : Direction.values()){
                   Position newPosition = position.move(direction);
                   if(newPosition.row >= 0 && newPosition.row < puzzle.grid.length &&
                   newPosition.col >= 0 && newPosition.col < puzzle.grid.length &&
                   puzzle.grid[newPosition.row][newPosition.col].isWalkable()){
                    totalFreeSpaces +=1;
                   }
                }
            }
        } float mobilityScore = totalFreeSpaces/totalPositions;
        return mobilityScore;   
    }

    public Integer estimateMinimalMoves(Puzzle puzzle){
        Block targetBlock = null;
        for(Block block : puzzle.blockList){
            if(block.isTarget()){
                targetBlock = block;
                break;
            }
        }
        if(targetBlock == null){
            throw new IllegalArgumentException("No target block found in puzzle!");
        }

        List<Position> goalPositions = new ArrayList<>();
        for (int row = 0; row < puzzle.rows; row++) {
            for (int col = 0; col < puzzle.cols; col++) {
                if (puzzle.grid[row][col].getType() == TileType.GOAL) {
                    goalPositions.add(new Position(row, col));
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for(Position position : targetBlock.getOccupiedPositions()){
            for(Position goalPosition : goalPositions){
                int distance = Math.abs(goalPosition.row - position.row) + Math.abs(goalPosition.col - position.col);
                if(distance < minDistance){
                    minDistance = distance;
                }
            }
        }return minDistance; 
    }


    public Integer analyseWinCondition(Puzzle puzzle){
        for(Block block : puzzle.blockList){
            if(block.isTarget()){
                for(Position position : block.getOccupiedPositions()){
                    Tile tile = puzzle.grid[position.getRow()][position.getCol()];
                    if(tile.getType() == Tile.TileType.GOAL){
                        return 1;
                        //Target blockl touching goal tile
                    }
                }
            }
        }return 2;
    }

    public Integer countFreeAdjacentTiles(Block block, Tile[][] grid){
        //check tiles adjacent to block's occupied positions 
        int freeTiles = 0;
        for(Position position: block.getOccupiedPositions()){
            for(Direction direction : Direction.values()){
               Position newPosition = position.move(direction);
               if (newPosition.row >= 0 && newPosition.row < grid.length &&
                   newPosition.col >= 0 && newPosition.col < grid[0].length &&
                   grid[newPosition.row][newPosition.col].isWalkable()){
                    freeTiles += 1;
                   }
            }
        }return freeTiles;
    }


    
}