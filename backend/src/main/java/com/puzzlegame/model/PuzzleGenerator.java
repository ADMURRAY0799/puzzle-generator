package com.puzzlegame.model;
import com.puzzlegame.model.DifficultyScorer.DifficultyLevel;
import com.puzzlegame.model.GridSize;
import com.puzzlegame.model.Puzzle;
import com.puzzlegame.model.PuzzleAnalyser;
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
        Block Grid = initialiseEmptyGrid(gridSize, null);
        
        Puzzle blocklist;

        
        

        int rows = gridSize.getRows();
        int cols = gridSize.getCols();

        int grid = initialiseEmptyGrid(rows, cols);
        Block targetBlock = generateRandomTargetBlock(gridSize, block);
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

    public Block generateRandomBlock(GridSize gridSize, Tile[][] grid){
       while(true){
        Position position = generateRandomPosition(gridSize.getRows(), gridSize.getCols());
        Direction direction = generateRandomDirection()
        int length = random.nextInt(1, 2);

        Block block = new Block(
            this.id = 
        )
       }
    }

    public Block generateRandomTargetBlock(GridSize gridSize, Tile[][] grid){
        Position position = generateRandomPosition(gridSize.getRows(), gridSize.getCols());
        int length = 2;
        
        String id = generateRandomBlockID();

        Block block = new Block(id, position, length, direction, true);

        if(blockFitsWithoutOverlap(grid, block)){
            return block;
        }
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
