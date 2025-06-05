package com.puzzlegame.model;
import com.puzzlegame.model.DifficultyScorer.DifficultyLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PuzzleGenerator{
    private Random random;
    private int maxAttempts;
    private DifficultyScorer difficultyScorer;
    
    public PuzzleGenerator(int maxAttempts, float weightGridSize, float weightBlockCount, float weightBlockVariety,
    float weightMobility, float weightMoveEstimate, float weightWinCondition){
        this.random = new Random();
        this.maxAttempts = maxAttempts;
        this.difficultyScorer = new DifficultyScorer(weightGridSize, weightBlockCount, weightBlockVariety, weightMobility, weightMoveEstimate, weightWinCondition);
    }

    public Puzzle generate(DifficultyLevel difficultyLevel){
        int attempt = 0;
        PuzzleAnalyser analyser = new PuzzleAnalyser();

        while(attempt < maxAttempts){
            Puzzle candidatePuzzle = createRandomPuzzle(difficultyLevel);
            
            PuzzleStats stats = analyser.analyse(candidatePuzzle);
            
            DifficultyLevel predictedDifficulty = this.difficultyScorer.score(stats);
            if(predictedDifficulty == difficultyLevel){
                return candidatePuzzle;
            }
            attempt += 1;
        }
        throw new RuntimeException("Puzzle generation failed after " + maxAttempts + " attempts.");
    }

    public Puzzle createRandomPuzzle(DifficultyLevel difficultyLevel){
        GridSize gridSize = getGridDimensions(difficultyLevel);
        Tile[][] grid = initialiseEmptyGrid(gridSize);
        List<Block> blocklist = new ArrayList<>();
        int numBlocks = DifficultyConfig.getNumBlocks(difficultyLevel);

        Block targetBlock = generateRandomTargetBlock(gridSize, grid);
        markBlockOccupied(grid, targetBlock);
        blocklist.add(targetBlock);

        while(blocklist.size() < numBlocks){
            Block block = generateRandomBlock(gridSize, grid);
            markBlockOccupied(grid, block);
            blocklist.add(block);
        }
        Position goalPosition = findFreeBorderTile(grid);
        grid[goalPosition.getRow()][goalPosition.getCol()].setType(Tile.TileType.GOAL);
        return new Puzzle(grid, blocklist);
    }

    //Helper method - find a walkable tile on the border, place goal there
    public Position findFreeBorderTile(Tile[][] grid){
        List<Position> candidates = new ArrayList<>();

        int numRows = grid.length;
        int numCols = grid[0].length;

        for(int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols ; col++){
                if(row == 0 || row == numRows -1 || col == 0 || col == numCols - 1){
                    Tile tile = grid[row][col];
                    if(tile.isWalkable()){
                        candidates.add(new Position(row, col));
                    }
                }
            }
        }
        if(candidates.isEmpty()){
            throw new RuntimeException("No free border tiles found!");
        }
        return candidates.get(random.nextInt(candidates.size()));
    }


    public GridSize getGridDimensions(DifficultyLevel difficultyLevel){
        switch(difficultyLevel){
            case EASY -> {
                return new GridSize(4, 4);
            } 
            case MEDIUM -> {
                return new GridSize(5, 5);
            } 
            case HARD -> {
                return new GridSize(6,6);
            }
            default -> throw new IllegalArgumentException("Unknown Difficulty: " + difficultyLevel);
        }
    }

    public Tile[][] initialiseEmptyGrid(GridSize gridSize){
       int rows = gridSize.getRows();
       int cols = gridSize.getCols();
       Tile [][] grid = new Tile[rows][cols];
       for(int i = 0; i < rows; i++){
        for(int j = 0; j < cols; j++){
            grid[i][j] = new Tile(i, j, Tile.TileType.EMPTY, false);
            }
        }
        return grid;
    }



    public Position generateRandomPosition(int rows, int cols){
        int randomRow = random.nextInt(rows);
        int randomCol = random.nextInt(cols);

        Position randomPosition = new Position(randomRow, randomCol);
        return randomPosition;
    }

    public Orientation generateRandomOrientation(){
        return random.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
    }

    public Block generateRandomBlockInternal(GridSize gridSize, Tile[][] grid, boolean isTarget){
        while(true){
            Position position = generateRandomPosition(gridSize.getRows(), gridSize.getCols());
            Orientation orientation = generateRandomOrientation();
            String id = generateRandomBlockID();
            int length = isTarget ? 2 : (random.nextBoolean() ? 1 : 2);
            Block block = new Block(id, position, length, orientation, true);
            if(blockFitsWithoutOverlap(grid, block)){
                return block;
            }
        }
    }

    public Block generateRandomBlock(GridSize gridSize, Tile[][] grid){
        return generateRandomBlockInternal(gridSize, grid, false);
    }

    public Block generateRandomTargetBlock(GridSize gridSize, Tile[][] grid){
        return generateRandomBlockInternal(gridSize, grid, true);
    }

    public String generateRandomBlockID(){
        int randomNumber = random.nextInt();
        return "B" + randomNumber;
    }

        
    public void markBlockOccupied(Tile[][] grid, Block block){
        for(Position position : block.getOccupiedPositions()){
            int row = position.getRow();
            int col = position.getCol();
            Tile tile = grid[row][col];

            tile.setOccupied(true);
            tile.setType(Tile.TileType.BLOCK);
        }
    }
    

    public Boolean blockFitsWithoutOverlap(Tile[][] grid, Block block){
        for (Position position : block.getOccupiedPositions()){
            int row = position.getRow();
            int col = position.getCol();

            if(row < 0 || row >= grid.length){
                return false;
            }
            if(col < 0|| col >= grid.length){
                return false;
            }
            Tile tile = grid[row][col];

            if(tile.isOccupied()){
                return false;
            }
        }return true;
    }
}
