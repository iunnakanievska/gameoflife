package com.gameoflife.strategy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlainLifeStrategyTest {
    private PlainLifeStrategy plainLifeStrategy;

    @Before
    public void setUp() {
        plainLifeStrategy = new PlainLifeStrategy();
    }

    @Test
    public void underPopulation_flip_ShouldDead() {
        boolean[][] population = {
                {false, false, false},
                {false, true, false},
                {false, true, false}
        };
        assertFalse(plainLifeStrategy.cellFutureState(population, 1, 1));
    }

    @Test
    public void cellWithTwoNeighbours_flip_ShouldRemainAlive() {
        boolean[][] population = {
                {false, true, false},
                {false, true, false},
                {false, true, false}
        };
        assertTrue(plainLifeStrategy.cellFutureState(population, 1, 1));
    }

    @Test
    public void cellWithThreeNeighbours_flip_ShouldRemainAlive() {
        boolean[][] population = {
                {false, true, false},
                {false, true, true},
                {false, true, false}
        };
        assertTrue(plainLifeStrategy.cellFutureState(population, 1, 1));
    }

    @Test
    public void overPopulation_flip_ShouldDie() {
        boolean[][] population = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        assertFalse(plainLifeStrategy.cellFutureState(population, 1, 1));
    }

    @Test
    public void reproduction_flip_ShouldResurrect() {
        boolean[][] population = {
                {false, false, true},
                {false, false, true},
                {false, false, true}
        };
        assertTrue(plainLifeStrategy.cellFutureState(population, 1, 1));
    }

    @Test
    public void cellWithoutNeighbours_countNeighbours_shouldReturnZero() throws Exception {
        boolean[][] population = {
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };
        assertEquals(0, plainLifeStrategy.countNeighbours(population, 1, 1));
    }

    @Test
    public void cellWithNeighbours_countNeighbours_shouldReturnNumberOfNeighbours() throws Exception {
        boolean[][] population = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        assertEquals(8, plainLifeStrategy.countNeighbours(population, 1, 1));
    }

    @Test
    public void isolatedCell_countNeighbours_shouldReturnZero() throws Exception {
        boolean[][] population = {
                {true}
        };
        assertEquals(0, plainLifeStrategy.countNeighbours(population, 0, 0));
    }

    @Test
    public void populationWithAliveCell_isAlive_shouldReturnTrue() throws Exception {
        boolean[][] population = new boolean[1][1];
        population[0][0] = true;
        assertTrue(plainLifeStrategy.isAlive(population, 0, 0));
    }

    @Test
    public void populationWithDeadCell_isAlive_shouldReturnFalse() throws Exception {
        boolean[][] population = new boolean[1][1];
        population[0][0] = false;
        assertFalse(plainLifeStrategy.isAlive(population, 0, 0));
    }

    // Oscillators
    @Test
    public void blinker_live_ShouldBeChangedAsExpected() throws Exception {
        boolean[][] population = {
                {false, true, false},
                {false, true, false},
                {false, true, false}
        };
        boolean[][] expectedPopulation = {
                {false, false, false},
                {true, true, true},
                {false, false, false}
        };
        assertArrayEquals(expectedPopulation, plainLifeStrategy.live(population));
        assertArrayEquals(population, plainLifeStrategy.live(expectedPopulation));
    }

    @Test
    public void toad_live_ShouldBeChangedAsExpected() throws Exception {
        boolean[][] population = {
                {false, false, false, false, false, false},
                {false, false, false, true, false, false},
                {false, true, false, false, true, false},
                {false, true, false, false, true, false},
                {false, false, true, false, false, false},
                {false, false, false, false, false, false}
        };
        boolean[][] expectedPopulation = {
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, true, true, true, false},
                {false, true, true, true, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false}
        };
        assertArrayEquals(expectedPopulation, plainLifeStrategy.live(population));
        assertArrayEquals(population, plainLifeStrategy.live(expectedPopulation));
    }

    @Test
    public void beacon_live_ShouldBeChangedAsExpected() throws Exception {
        boolean[][] population = {
                {false, false, false, false, false, false},
                {false, true, true, false, false, false},
                {false, true, true, false, false, false},
                {false, false, false, true, true, false},
                {false, false, false, true, true, false},
                {false, false, false, false, false, false}
        };
        boolean[][] expectedPopulation = {
                {false, false, false, false, false, false},
                {false, true, true, false, false, false},
                {false, true, false, false, false, false},
                {false, false, false, false, true, false},
                {false, false, false, true, true, false},
                {false, false, false, false, false, false}
        };
        assertArrayEquals(expectedPopulation, plainLifeStrategy.live(population));
        assertArrayEquals(population, plainLifeStrategy.live(expectedPopulation));
    }

    // Still Lifes Patterns
    @Test
    public void block_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {false, true, true},
                {false, true, true},
                {false, false, false}
        };
        boolean[][] expectedPopulation = {
                {false, true, true},
                {false, true, true},
                {false, false, false}
        };
        assertArrayEquals(expectedPopulation, plainLifeStrategy.live(population));
    }

    @Test
    public void beehive_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {false, false, false, false, false, false},
                {false, false, true, true, false, false},
                {false, true, false, false, true, false},
                {false, false, true, true, false, false},
                {false, false, false, false, false, false}
        };
        boolean[][] expectedPopulation = {
                {false, false, false, false, false, false},
                {false, false, true, true, false, false},
                {false, true, false, false, true, false},
                {false, false, true, true, false, false},
                {false, false, false, false, false, false}
        };
        assertArrayEquals(expectedPopulation, plainLifeStrategy.live(population));
    }

    @Test
    public void loaf_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {false, false, false, false, false, false},
                {false, false, true, true, false, false},
                {false, true, false, false, true, false},
                {false, false, true, false, true, false},
                {false, false, false, true, false, false},
                {false, false, false, false, false, false}
        };
        boolean[][] expectedPopulation = {
                {false, false, false, false, false, false},
                {false, false, true, true, false, false},
                {false, true, false, false, true, false},
                {false, false, true, false, true, false},
                {false, false, false, true, false, false},
                {false, false, false, false, false, false}
        };
        assertArrayEquals(expectedPopulation, plainLifeStrategy.live(population));
    }

    @Test
    public void boat_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {false, false, false, false, false},
                {false, true, true, false, false},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, false, false, false, false}
        };
        boolean[][] expectedPopulation = {
                {false, false, false, false, false},
                {false, true, true, false, false},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, false, false, false, false}
        };
        assertArrayEquals(expectedPopulation, plainLifeStrategy.live(population));
    }
}