package com.gameoflife;

import com.gameoflife.printer.PopulationPrinter;
import com.gameoflife.strategy.LifeStrategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameOfLifeTest {
    @Spy
    @InjectMocks
    private GameOfLife gameOfLife;

    @Mock
    private LifeStrategy lifeStrategy;

    @Mock
    private PopulationPrinter populationPrinter;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void population_startGame_shouldProcessRequiredNumberOfGenerations() throws Exception {
        boolean[][] population = GameOfLife.populate();

        when(lifeStrategy.live(population)).thenReturn(population);

        gameOfLife.startGame(population);

        verify(gameOfLife, times(gameOfLife.getGenerationsNumber() + 1)).printPopulation(anyObject());
        verify(gameOfLife, times(gameOfLife.getGenerationsNumber())).nextGeneration(anyObject());
    }

    @Test
    public void population_nextGeneration_shouldInvokeRequiredStrategy() throws Exception {
        boolean[][] population = GameOfLife.populate();

        gameOfLife.startGame(population);

        verify(lifeStrategy).live(population);
    }

    @Test
    public void population_printPopulation_shouldInvokePopulationPrinter() throws Exception {
        boolean[][] population = GameOfLife.populate();

        gameOfLife.startGame(population);

        verify(populationPrinter).printPopulation(population);
    }
}