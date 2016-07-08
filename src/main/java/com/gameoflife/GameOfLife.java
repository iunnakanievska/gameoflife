package com.gameoflife;

import com.gameoflife.strategy.LifeStrategy;
import com.gameoflife.strategy.PlainLifeStrategy;

public class GameOfLife {
    //Grid size and number of generations
    private int rows = 10, columns = 10, generationsNumber = 10;

    private LifeStrategy lifeStrategy;

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.setLifeStrategy(new PlainLifeStrategy());
        gameOfLife.setGame();
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
        return this.getLifeStrategy().live(population);
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

    public LifeStrategy getLifeStrategy() {
        return lifeStrategy;
    }

    public void setLifeStrategy(LifeStrategy lifeStrategy) {
        this.lifeStrategy = lifeStrategy;
    }
}