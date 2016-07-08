package com.gameoflife.strategy;

public class PlainLifeStrategy implements LifeStrategy {
    @Override
    public boolean[][] live(boolean[][] population) {
        int rows = population.length, columns = population[0].length;
        boolean[][] newPopulation = new boolean[rows][columns];
        for (int row = 0; row < rows; row++)
            for (int column = 0; column < columns; column++)
                newPopulation[row][column] = cellFutureState(population, row, column);
        return newPopulation;
    }

    boolean cellFutureState(boolean[][] population, int row, int column) {
        int neighboursCount = countNeighbours(population, row, column);
        if (population[row][column]) {
            if (neighboursCount < 2 || neighboursCount > 3)
                return false;
            return true;
        } else {
            if (neighboursCount == 3)
                return true;
            return false;
        }
    }

    int countNeighbours(boolean[][] population, int row, int column) {
        int neighboursCount = 0;
        for (int neighbourRow = row - 1; neighbourRow <= row + 1; neighbourRow++)
            for (int neighbourColumn = column - 1; neighbourColumn <= column + 1; neighbourColumn++) {
                if (neighbourRow == row && neighbourColumn == column)
                    continue;
                if (isAlive(population, neighbourRow, neighbourColumn)) {
                    ++neighboursCount;
                }
            }
        return neighboursCount;
    }

    boolean isAlive(boolean[][] population, int row, int column) {
        if (row < 0 || column < 0 || row == population.length || column == population[0].length) {
            return false;
        } else if (population[row][column]) {
            return true;
        }
        return false;
    }
}
