package com.gameoflife;



public class GameOfLife {
    //Grid size and number of generations
    int rows = 10, columns = 10, generationsNumber = 10;

    public static void main(String[] args) {
        GameOfLife now = new GameOfLife();
        now.setGame();
    }

    private static void populate(boolean[][] population, float alivePercentage) {
        for (int i = 0; i < population.length; i++)
            populateRow(population[i], alivePercentage);
    }

    private static void populateRow(boolean[] population, float alivePercentage) {
        for (int i = 0; i < population.length; i++) {
            if (Math.random() < alivePercentage) {
                population[i] = true;
            } else {
                population[i] = false;
            }
        }
    }

    void setGame() {
        boolean[][] config = new boolean[rows][columns];
        startGame(config);
    }

    void startGame(boolean[][] population) {
        //Enter percentage of grid to be filled.
        float alivePercentage = 0.50f;//(float)Math.random();
        populate(population, alivePercentage);
        printPopulation(population);
        for (int generation = 0; generation < generationsNumber; generation++) {
            System.out.println("Generation #" + generation);
            population = nextGeneration(population);
            printPopulation(population);
        }
        System.out.println("Game Over");
    }

    boolean[][] nextGeneration(boolean[][] population) {
        int rows = population.length, columns = population[0].length;
        boolean[][] newPopulation = new boolean[rows][columns];
        for (int row = 0; row < rows; row++)
            for (int column = 0; column < columns; column++)
                newPopulation[row][column] = flip(population, row, column);
        return newPopulation;
    }

    boolean flip(boolean[][] population, int row, int column) {
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
        }
        else if (population[row][column]) {
            return true;
        }
        return false;

    }

    void printPopulation(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++)
            printPopulationRow(arr[i]);
        System.out.println();
    }

    void printPopulationRow(boolean[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] ? '#' : '_');
        System.out.println();
    }
}