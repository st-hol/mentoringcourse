package com.epam.training.sportsbetting.ui.util;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.epam.training.sportsbetting.domain.user.Player;

public class ApplicationViewTest {

    private static final String TEST_STRING = "test";
    private static final String NO_EVENTS_MESSAGE = "No events.";
    private static final String RESULTS = "Results:";

    private ApplicationView mockedInstance = ApplicationView.getInstance();
    private ByteArrayOutputStream bo = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        //Redirect System.out to buffer
        System.setOut(new PrintStream(bo));
    }

    @Test
    public void shouldPrintLine() throws IOException {
        mockedInstance.printLine(TEST_STRING);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        assertTrue(allWrittenLines.contains(TEST_STRING));
    }

    @Test
    public void shouldPrintMessageWhenSuggestOutcomeWithOddsTableWithNullEvents() throws IOException {
        mockedInstance.suggestOutcomeWithOddsTable(null);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        assertTrue(allWrittenLines.contains(NO_EVENTS_MESSAGE));
    }

    @Test
    public void shouldPrintResults() throws IOException {
        Player player = new Player() {{
            setBalance(BigDecimal.TEN);
        }};
        mockedInstance.printResults(player, Collections.emptyList());
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        assertTrue(allWrittenLines.contains(RESULTS));
    }

}