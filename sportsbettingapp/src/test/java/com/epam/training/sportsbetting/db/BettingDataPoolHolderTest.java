package com.epam.training.sportsbetting.db;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(BettingDataPoolHolder.class)
public class BettingDataPoolHolderTest {

    private static final int OUTCOME_ID = 1;
    private static final String EVENT_TITLE = "Arsenal vs Chelsea";

    private BettingDataPoolHolder mockedInstance = BettingDataPoolHolder.getInstance();

    @Test
    public void shouldReturnListOfOddsWhenGetOutcomeOdds() {
        assertThat(mockedInstance.getOutcomeOddsData().get(0).getId(), is(1));
    }

    @Test
    public void shouldReturnListOfEventsWhenGetSportEvents() {
        assertThat(mockedInstance.getSportEventsData().get(0).getTitle(), is(EVENT_TITLE));
    }


}
