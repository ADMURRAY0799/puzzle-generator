package com.puzzlegame.model;

public class TestClass{
    public static void main(String[] args) {
        PuzzleGenerator generator = new PuzzleGenerator(10, 0.2f, 0.3f, 0.4f, 0.5f, 1.0f, 0.8f);

        Puzzle puzzle = generator.createRandomPuzzle(DifficultyScorer.DifficultyLevel.EASY);
        PuzzlePrinter printer = new PuzzlePrinter();
        printer.printPuzzleGrid(puzzle.getGrid(), puzzle.getBlocks());
    }
}