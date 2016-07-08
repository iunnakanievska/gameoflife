package com.gameoflife.printer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ConsolePopulationPrinterTest {
    @Spy
    private ConsolePopulationPrinter consolePopulationPrinter;
    @Mock
    private PrintStream printStream;
    @Captor
    private ArgumentCaptor<boolean[]> cellCaptor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        System.setOut(printStream);
    }

    @Test
    public void populationRow_printPopulationRow_shouldPrintPopulationRow() {
        boolean aliveCell = true;
        boolean deadCell = false;
        boolean[] populationRow = {aliveCell, deadCell, aliveCell};

        consolePopulationPrinter.printPopulationRow(populationRow);

        InOrder order = inOrder(printStream);
        order.verify(printStream).print(consolePopulationPrinter.getAliveChar());
        order.verify(printStream).print(consolePopulationPrinter.getDeadChar());
        order.verify(printStream).print(consolePopulationPrinter.getAliveChar());
        order.verify(printStream).println();
        verifyNoMoreInteractions(printStream);
    }

    @Test
    public void population_printPopulation_shouldPrintPopulation() {
        boolean[] populationFirstRow = {true, false, true};
        boolean[] populationSecondRow = {false, true, false};
        boolean[][] population = {populationFirstRow, populationSecondRow};

        consolePopulationPrinter.printPopulation(population);

        verify(consolePopulationPrinter, atLeast(1)).printPopulationRow(cellCaptor.capture());

        List<boolean[]> capturedPopulationRows = cellCaptor.getAllValues();

        assertEquals(2, capturedPopulationRows.size());
        assertArrayEquals(populationFirstRow, capturedPopulationRows.get(0));
        assertArrayEquals(populationSecondRow, capturedPopulationRows.get(1));

        verify(printStream, times(3)).println();
    }

}