package com.gameoflife;

import com.gameoflife.printer.ConsolePopulationPrinter;
import com.gameoflife.printer.PopulationPrinter;
import com.gameoflife.strategy.LifeStrategy;
import com.gameoflife.strategy.PlainLifeStrategy;

public class GameOfLife {
    // Default values for Grid size
    private static int ROWS = 10, COLUMNS = 10;
    // Default value for percentage of alive cells in grid
    private static float ALIVE_PERCENTAGE = 0.5f;

    private int generationsNumber = 10;

    private LifeStrategy lifeStrategy;
    private PopulationPrinter populationPrinter;

    public GameOfLife() {
        // Default Life Strategy
        this.lifeStrategy = new PlainLifeStrategy();
        // Default Population Printer
        this.populationPrinter = new ConsolePopulationPrinter();
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();

        boolean[][] population = populate();

        gameOfLife.startGame(population);
    }

    public static boolean[][] populate() {
        return populate(ALIVE_PERCENTAGE);
    }

    public static boolean[][] populate(float alivePercentage) {
        return populate(ROWS, COLUMNS, alivePercentage);
    }

    public static boolean[][] populate(int rows, int columns, float alivePercentage) {
        boolean[][] population = new boolean[rows][columns];
        populate(population, alivePercentage);
        return population;
    }

    public static void populate(boolean[][] population, float alivePercentage) {
        for (boolean[] populationRow : population) {
            populateRow(populationRow, alivePercentage);
        }
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

    public void startGame(boolean[][] population) {
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

    void printPopulation(boolean[][] population) {
        this.getPopulationPrinter().printPopulation(population);
    }

    public int getGenerationsNumber() {
        return generationsNumber;
    }

    public void setGenerationsNumber(int generationsNumber) {
        this.generationsNumber = generationsNumber;
    }

    public LifeStrategy getLifeStrategy() {
        return lifeStrategy;
    }

    public void setLifeStrategy(LifeStrategy lifeStrategy) {
        this.lifeStrategy = lifeStrategy;
    }

    public PopulationPrinter getPopulationPrinter() {
        return populationPrinter;
    }

    public void setPopulationPrinter(PopulationPrinter populationPrinter) {
        this.populationPrinter = populationPrinter;
    }
}