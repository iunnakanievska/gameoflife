package com.gameoflife.strategy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniverseLifeStrategyTest {
    private UniverseLifeStrategy universeLifeStrategy;

    @Before
    public void setUp() {
        universeLifeStrategy = new UniverseLifeStrategy();
    }

    @Test
    public void underPopulation_flip_ShouldDead() {
        boolean[][] population = {
                {false, false, false},
                {false, true, false},
                {false, true, false}
        };
        assertFalse(universeLifeStrategy.cellFutureState(population, 1, 1));
    }

    @Test
    public void cellWithTwoNeighbours_flip_ShouldRemainAlive() {
        boolean[][] population = {
                {true, false, true},
                {false, false, false},
                {false, false, true}
        };
        assertTrue(universeLifeStrategy.cellFutureState(population, 0, 0));
    }

    @Test
    public void cellWithThreeNeighbours_flip_ShouldRemainAlive() {
        boolean[][] population = {
                {true, false, true},
                {false, false, true},
                {true, false, false}
        };
        assertTrue(universeLifeStrategy.cellFutureState(population, 0, 0));
    }

    @Test
    public void overPopulation_flip_ShouldDie() {
        boolean[][] population = {
                {true, false, true},
                {false, false, true},
                {true, false, true}
        };
        assertFalse(universeLifeStrategy.cellFutureState(population, 0, 0));
    }

    @Test
    public void reproduction_flip_ShouldResurrect() {
        boolean[][] population = {
                {false, false, true},
                {false, false, false},
                {true, false, true}
        };
        assertTrue(universeLifeStrategy.cellFutureState(population, 0, 0));
    }

    @Test
    public void cellWithoutNeighbours_countNeighbours_shouldReturnZero() throws Exception {
        boolean[][] population = {
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };
        assertEquals(0, universeLifeStrategy.countNeighbours(population, 1, 1));
    }

    @Test
    public void cellWithNeighbours_countNeighbours_shouldReturnNumberOfNeighbours() throws Exception {
        boolean[][] population = {
                {true, true, false, true},
                {true, true, false, true},
                {false, false, false, false},
                {true, true, false, true}
        };
        assertEquals(8, universeLifeStrategy.countNeighbours(population, 0, 0));
    }

    @Test
    public void isolatedCell_countNeighbours_shouldReturnEight() throws Exception {
        boolean[][] population = {
                {true}
        };
        assertEquals(0, universeLifeStrategy.countNeighbours(population, 0, 0));
    }

    @Test
    public void twoRowsGrid_countNeighbours_shouldCountBottomNeighbourOnlyOnce() throws Exception {
        boolean[][] population = {
                {true},
                {true}
        };
        assertEquals(1, universeLifeStrategy.countNeighbours(population, 0, 0));
    }

    @Test
    public void twoRowsGrid_countNeighbours_shouldCountNeighbourAboveOnlyOnce() throws Exception {
        boolean[][] population = {
                {true},
                {true}
        };
        assertEquals(1, universeLifeStrategy.countNeighbours(population, 1, 0));
    }

    @Test
    public void twoColumnsGrid_countNeighbours_shouldCountRightNeighbourOnlyOnce() throws Exception {
        boolean[][] population = {
                {true, true}
        };
        assertEquals(1, universeLifeStrategy.countNeighbours(population, 0, 0));
    }

    @Test
    public void twoColumnsGrid_countNeighbours_shouldCountLeftNeighbourOnlyOnce() throws Exception {
        boolean[][] population = {
                {true, true}
        };
        assertEquals(1, universeLifeStrategy.countNeighbours(population, 0, 1));
    }

    @Test
    public void populationWithAliveCell_isAlive_shouldReturnTrue() throws Exception {
        boolean[][] population = new boolean[1][1];
        population[0][0] = true;
        assertTrue(universeLifeStrategy.isAlive(population, 0, 0));
    }

    @Test
    public void populationWithDeadCell_isAlive_shouldReturnFalse() throws Exception {
        boolean[][] population = new boolean[1][1];
        population[0][0] = false;
        assertFalse(universeLifeStrategy.isAlive(population, 0, 0));
    }

    // Oscillators
    @Test
    public void blinker_live_ShouldBeChangedAsExpected() throws Exception {
        boolean[][] population = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {true, true, false, true}
        };
        boolean[][] expectedPopulation = {
                {true, false, false, false},
                {false, false, false, false},
                {true, false, false, false},
                {true, false, false, false}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
        assertArrayEquals(population, universeLifeStrategy.live(expectedPopulation));
    }

    @Test
    public void toad_live_ShouldBeChangedAsExpected() throws Exception {
        boolean[][] population = {
                {true, false, false, false, true, true},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {true, true, false, false, false, true}
        };
        boolean[][] expectedPopulation = {
                {false, true, false, false, true, false},
                {false, false, false, false, false, true},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {true, false, false, false, false, false},
                {false, true, false, false, true, false}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
        assertArrayEquals(population, universeLifeStrategy.live(expectedPopulation));
    }

    @Test
    public void beacon_live_ShouldBeChangedAsExpected() throws Exception {
        boolean[][] population = {
                {false, true, false, false, false, false},
                {true, true, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, true, true},
                {false, false, false, false, true, false}
        };
        boolean[][] expectedPopulation = {
                {true, true, false, false, false, false},
                {true, true, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, true, true},
                {false, false, false, false, true, true}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
        assertArrayEquals(population, universeLifeStrategy.live(expectedPopulation));
    }

    // Still Lifes Patterns@Test
    @Test
    public void column_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {true},
                {true},
                {true}
        };
        boolean[][] expectedPopulation = {
                {true},
                {true},
                {true}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
    }

    @Test
    public void block_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {true, false, false},
                {true, false, true},
                {false, false, true}
        };
        boolean[][] expectedPopulation = {
                {true, false, false},
                {true, false, true},
                {false, false, true}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
    }

    @Test
    public void beehive_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {true, false, false, false, false, true},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {true, false, false, false, false, true},
                {false, true, false, false, true, false}
        };
        boolean[][] expectedPopulation = {
                {true, false, false, false, false, true},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {true, false, false, false, false, true},
                {false, true, false, false, true, false}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
    }

    @Test
    public void loaf_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {false, true, false, false, false, true},
                {true, false, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {true, false, false, false, false, true},
                {false, true, false, false, true, false}
        };
        boolean[][] expectedPopulation = {
                {false, true, false, false, false, true},
                {true, false, false, false, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, false},
                {true, false, false, false, false, true},
                {false, true, false, false, true, false}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
    }

    @Test
    public void boat_live_ShouldRemain() throws Exception {
        boolean[][] population = {
                {false, true, false, false, true},
                {true, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {true, false, false, false, true}
        };
        boolean[][] expectedPopulation = {
                {false, true, false, false, true},
                {true, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {true, false, false, false, true}
        };
        assertArrayEquals(expectedPopulation, universeLifeStrategy.live(population));
    }
}