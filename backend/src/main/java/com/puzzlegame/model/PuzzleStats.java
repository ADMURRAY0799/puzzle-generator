package com.puzzlegame.model;

public class PuzzleStats{
    private int gridSize;
    private int blockCount;
    private int blockVariety;
    private float mobilityScore;
    private int moveEstimate;
    private int winConditionScore;

    public PuzzleStats(int gridSize, int blockCount, int blockVariety, float mobilityScore,int moveEstimate, int winConditionScore){
            this.gridSize = gridSize;
            this.blockCount = blockCount;
            this.blockVariety = blockVariety;
            this.mobilityScore = mobilityScore;
            this.moveEstimate = moveEstimate;
            this.winConditionScore = winConditionScore;
    }

    public int getGridSize(){
        return gridSize;
    }

    public int getBlockCount(){
        return blockCount;
    }

    public int getBlockVariety(){
        return blockVariety;
    }

    public float getMobilityScore(){
        return mobilityScore;
    }

    public int getMoveEstimate(){
        return moveEstimate;
    }

    public int getWinConditionScore(){
        return winConditionScore;
    }
}