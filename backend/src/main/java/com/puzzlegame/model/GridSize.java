package com.puzzlegame.model;

public class GridSize {
    private final int rows;
    private final int cols;

    public GridSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
