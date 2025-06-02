package com.puzzlegame.model;

import java.util.Collections;
import java.util.List;
import java.util.Set;

//Class responsible for creating PuzzleStats
public class PuzzleAnalyser{

    public PuzzleStats analyse(Puzzle puzzle){
        int gridSize = calculateGridSize(puzzle);
        int blockCount = calculateBlockCount(puzzle);
        int blockVariety =  calculateBlockVariety(puzzle);
        float mobilityScore = calculateMobilityScore(puzzle);
        int moveEstimate = calculateMoveEstimate(puzzle);
        int winConditionScore = calculateWinConditonScore(puzzle);
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
        for(Block block : puzzle.blockList){
            int freeSpaces = countFreeAdjacentTiles(block, puzzle.grid);
            totalFreeSpaces += freeSpaces;
        }return totalFreeSpaces/blockCount;
    }

    public Integer estimateMinimalMoves(Puzzle puzzle){
        // Heuristic or A* solver
        return heuristicMoveEstimate(puzzle);
    }

    public Integer countFreeAdjacentTiles(Block block, Tile[][] grid){
        //check tiles adjacent to block's occupied positions 
        int freeTiles = 0;
        for(Position position: block.getOccupiedPositions()){
            for(Direction direction : )
        }
    }
}
    
    