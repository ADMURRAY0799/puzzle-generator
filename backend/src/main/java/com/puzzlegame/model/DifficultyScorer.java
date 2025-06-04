package com.puzzlegame.model;

public class DifficultyScorer{
    private final float weightGridSize;
    private final float weightBlockCount;
    private final float weightBlockVariety;
    private final float weightMobility;
    private final float weightMoveEstimate;
    private final float weightWinCondition;

    public enum DifficultyLevel{
        EASY, MEDIUM, HARD;
    }

    public DifficultyScorer(float weightGridSize, float weightBlockCount, float weightBlockVariety,
            float weightMobility, float weightMoveEstimate, float weightWinCondition){

            this.weightGridSize = weightGridSize;
            this.weightBlockCount = weightBlockCount;
            this.weightBlockVariety = weightBlockVariety;
            this.weightMobility = weightMobility;
            this.weightMoveEstimate = weightMoveEstimate;
            this.weightWinCondition = weightWinCondition;
    }

    public DifficultyLevel score(PuzzleStats puzzleStats){
        double gridScore = normaliseGridSize(puzzleStats.getGridSize()) * weightGridSize;
        double blockScore = normaliseBlockCount(puzzleStats.getBlockCount()) * weightBlockCount;
        double varietyScore = normaliseBlockVariety(puzzleStats.getBlockVariety()) * weightBlockVariety;
        double mobilityScore = normaliseMobility(puzzleStats.getMobilityScore()) * weightMobility;
        double moveEstimateScore = normaliseMoveEstimate(puzzleStats.getMoveEstimate()) * weightMoveEstimate;
        double winConditionScore = normaliseWinConditionScore(puzzleStats.getinConditionScore()) * weightWinCondition;
        
        double totalScore = gridScore + blockScore + varietyScore + mobilityScore + moveEstimateScore + winConditionScore;
        return classifyScore(totalScore);
    }

    public DifficultyLevel classifyScore(double totalScore){
        if(totalScore < 3.0){
            return DifficultyLevel.EASY;
        }else if(totalScore < 5.0){
            return DifficultyLevel.MEDIUM;
        }else{
            return DifficultyLevel.HARD;
        }
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