package com.epam.training.sportsbetting.ui.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class InputProviderTest {

    private static final String TEST_STRING = "test";

    private InputProvider mockedInstance;

    @Before
    public void setUp() {
        InputStream in = new ByteArrayInputStream(TEST_STRING.getBytes());
        System.setIn(in);
        mockedInstance = InputProvider.getInstance();
    }

    @Test
    public void shouldReadWithScanner() {
        String actual = mockedInstance.readWithScanner();
        assertEquals(TEST_STRING, actual);
    }

}