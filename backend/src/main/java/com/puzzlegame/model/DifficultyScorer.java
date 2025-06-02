package com.puzzlegame.model;

public class DifficultyScorer{
    private float weightGridSize;
    private float weightBlockCount;
    private float weightBlockVariety;
    private float weightMobility;
    private float weightMoveEstimate;
    private float weightWinCondition;

    public enum DifficultyLevel{
        EASY, MEDIUM, HARD;
    }

    public DifficultyScorer(float weightGridSize, float weightBlockCount, float weightBlockVariety,
            float weightMobility, float weightMoveEstimate, float weightWinCondition){

            this.weightGridSize = 0.2f;
            this.weightBlockCount = 0.3f;
            this.weightBlockVariety = 0.4f;
            this.weightMobility = 0.5f;
            this.weightMoveEstimate = 1.0f;
            this.weightWinCondition = 0.8f;
    }

    public DifficultyLevel score(PuzzleStats puzzleStats){
        return null;
        
    }

    public double normaliseGridSize(Integer gridSize){
        return gridSize; // divide by max expected grid size
    }

    public double normaliseBlockCount(Integer blockCount){
        return blockCount; //divide by max expected blocks 
    }

    public double normaliseBlockVariety(Integer blockVariety){
        return blockVariety; //divide by expected block variety
    }

    public double normaliseMobility(float mobilityScore){
        return mobilityScore;
    }
    
    public double normaliseMoveEstimate(Integer moveEstimate){
        return moveEstimate; // divide by maax expected moves
    }

    public double normaliseWinConditionScore(Integer winConditionScore){
        return winConditionScore; // divide by max win condition score
    }
}