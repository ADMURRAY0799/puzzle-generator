
import com.puzzlegame.model.DifficultyScorer.DifficultyLevel;
import com.puzzlegame.model.GridSize;
import com.puzzlegame.model.Puzzle;

public class PuzzleGenerator{
    int maxAttempts;

    public PuzzleGenerator(int maxAttempts){
        this.maxAttempts = maxAttempts;
    }

    public Puzzle generate(DifficultyLevel difficultyLevel){
        int attempt = 0;
        while(attempt < maxAttempts){
            Puzzle candidatePuzzle = createRandomPuzzle();
        }
    }

    public Puzzle createRandomPuzzle(DifficultyLevel difficultyLevel){
        GridSize gridSize = getGridDimensions(difficultyLevel);

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

}