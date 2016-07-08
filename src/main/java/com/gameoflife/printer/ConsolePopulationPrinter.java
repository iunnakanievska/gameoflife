package com.gameoflife.printer;

public class ConsolePopulationPrinter implements PopulationPrinter {
    // Symbols to represent Cell state in console
    private char dead, alive;

    public ConsolePopulationPrinter(char dead, char alive) {
        this.dead = dead;
        this.alive = alive;
    }

    public ConsolePopulationPrinter() {
        this('_', '#');
    }

    void printPopulationRow(boolean[] population) {
        for (boolean cell : population) {
            System.out.print(cell ? getAliveChar() : getDeadChar());
        }
        System.out.println();
    }

    @Override
    public void printPopulation(boolean[][] population) {
        for (boolean[] populationRow : population) {
            printPopulationRow(populationRow);
        }
        System.out.println();
    }

    public char getDeadChar() {
        return dead;
    }

    public void setDeadChar(char dead) {
        this.dead = dead;
    }

    public char getAliveChar() {
        return alive;
    }

    public void setAliveChar(char alive) {
        this.alive = alive;
    }
}
