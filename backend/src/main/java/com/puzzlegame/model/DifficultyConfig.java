package com.puzzlegame.model;

public class DifficultyConfig {

    public static int getNumBlocks(DifficultyScorer.DifficultyLevel difficultyLevel) {
        return switch (difficultyLevel) {
            case EASY -> 4;   // 4 blocks for 4x4 grid
            case MEDIUM -> 6; // 6 blocks for 5x5 grid
            case HARD -> 8;   // 8 blocks for 6x6 grid
        };
    }

    public static GridSize getGridSize(DifficultyScorer.DifficultyLevel difficultyLevel) {
        return switch (difficultyLevel) {
            case EASY -> new GridSize(4, 4);
            case MEDIUM -> new GridSize(5, 5);
            case HARD -> new GridSize(6, 6);
        };
    }
}
