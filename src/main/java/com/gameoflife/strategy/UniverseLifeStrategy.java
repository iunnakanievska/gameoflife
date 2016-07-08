package com.gameoflife.strategy;

public class UniverseLifeStrategy extends PlainLifeStrategy {
    @Override
    int countNeighbours(boolean[][] population, int row, int column) {
        int neighboursCount = 0;
        for (int neighbourRow = row - 1; neighbourRow <= row + 1; neighbourRow++) {
            int neighbourUniverseRow = neighbourRow;
            if (neighbourUniverseRow == -1) {
                neighbourUniverseRow = population.length - 1;
                if (neighbourUniverseRow == row || neighbourUniverseRow == row + 1) {
                    continue;
                }
            } else if (neighbourUniverseRow == population.length) {
                neighbourUniverseRow = 0;
                if (neighbourUniverseRow == row || neighbourUniverseRow == row - 1) {
                    continue;
                }
            }
            for (int neighbourColumn = column - 1; neighbourColumn <= column + 1; neighbourColumn++) {
                int neighbourUniverseColumn = neighbourColumn;
                if (neighbourUniverseColumn == -1) {
                    neighbourUniverseColumn = population[0].length - 1;
                    if (neighbourUniverseColumn == column || neighbourUniverseColumn == column + 1) {
                        continue;
                    }
                } else if (neighbourUniverseColumn == population[0].length) {
                    neighbourUniverseColumn = 0;
                    if (neighbourUniverseColumn == column || neighbourUniverseColumn == column - 1) {
                        continue;
                    }
                }
                if (neighbourUniverseRow == row && neighbourUniverseColumn == column)
                    continue;
                if (isAlive(population, neighbourUniverseRow, neighbourUniverseColumn)) {
                    ++neighboursCount;
                }
            }
        }
        return neighboursCount;
    }
}
