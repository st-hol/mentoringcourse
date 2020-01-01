package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.db.BettingDataPoolHolder;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.domain.user.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApplicationService.class, BettingDataPoolHolder.class})
public class ApplicationServiceTest {

    private static final String TEST_EVENT = "test";
    private static final int USER_CHOICE = 123;
    private static final int OUTCOME_ID = 1;
    private static final BigDecimal TEST_OUTCOME_VALUE = BigDecimal.TEN;
    private static final BigDecimal WAGER_AMOUNT = BigDecimal.ONE;


    private ApplicationService serviceUnderTest = ApplicationService.getInstance();
    private BettingDataPoolHolder bettingDataPoolHolder;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(BettingDataPoolHolder.class);
        bettingDataPoolHolder = mock(BettingDataPoolHolder.class);
        when(BettingDataPoolHolder.getInstance()).thenReturn(bettingDataPoolHolder);

        serviceUnderTest.setBettingDataPoolHolder(bettingDataPoolHolder);
    }

    @Test
    public void givenUserChoiceForExistingOdd_whenFindOutcomeOddByNumber_thenReturnOddWithGivenId() {
        when(bettingDataPoolHolder.getOutcomeOddsData())
                .thenReturn(new ArrayList<>(Collections.singletonList(new OutcomeOdd() {{
                    setId(OUTCOME_ID);
                    setValue(TEST_OUTCOME_VALUE);
                }})));
        assertThat(serviceUnderTest.findOutcomeOddByNumber(OUTCOME_ID).getValue(), is(TEST_OUTCOME_VALUE));
    }

    @Test
    public void givenUserChoiceForNonExistingOdd_whenFindOutcomeOddByNumber_thenReturnNull() {
        when(bettingDataPoolHolder.getOutcomeOddsData())
                .thenReturn(Collections.emptyList());
        assertThat(serviceUnderTest.findOutcomeOddByNumber(USER_CHOICE), is(nullValue()));
    }

    @Test
    public void whenGetSportEvents_thenReturnListOfEvents() {
        when(bettingDataPoolHolder.getSportEventsData())
                .thenReturn(Collections.singletonList(populateSportEvent()));
        assertThat(serviceUnderTest.getSportEventsData().get(0).getTitle(), is(TEST_EVENT));
    }

    @Test
    public void givenParams_whenCreateWager_thenBuildWager() {
        Wager actual = serviceUnderTest.createWager(
                populatePlayer(),
                WAGER_AMOUNT,
                populateOutcomeOdd()
        );
        assertThat(actual.getPlayer(), is(equalTo(populatePlayer())));
        assertThat(actual.getAmount(), is(WAGER_AMOUNT));
        assertThat(actual.getOutcomeOdd().getId(), is(populateOutcomeOdd().getId()));
    }

    @Test
    public void givenSportEvents_whenGenerateResult_thenReturnResultForWinner() {
        List<SportEvent> sportEvents = Collections.singletonList(populateSportEvent());
        sportEvents.get(0).setBets(populateBets());
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(0);
        serviceUnderTest.setRandom(random);

        List<Wager> userWagers = populateWagers();
        Player player = populatePlayer();
        BigDecimal prevBalance = player.getBalance();

        Result actual = serviceUnderTest.generateResult(sportEvents, userWagers, player);
        assertThat(actual.getWinnerOutcomes().size(), is(1));
        assertThat(player.getBalance(), is(not(prevBalance)));
    }

    @Test
    public void givenTwoNumbers_whenCalculateTotalWonSum_thenCalculateResult(){
        BigDecimal actual = serviceUnderTest.calculateTotalWonSum(BigDecimal.ONE,BigDecimal.TEN);
        assertThat(actual, is(BigDecimal.valueOf(9)));

    }

    private SportEvent populateSportEvent() {
        return new FootballSportEvent() {{
            setTitle(TEST_EVENT);
        }};
    }

    private Player populatePlayer() {
        Player player = new Player();
        player.setName("TestPlayer");
        player.setBalance(BigDecimal.TEN);
        player.setAccountNumber(123);
        return player;
    }

    private OutcomeOdd populateOutcomeOdd() {
        OutcomeOdd outcomeOdd = new OutcomeOdd();
        outcomeOdd.setId(OUTCOME_ID);
        outcomeOdd.setValue(BigDecimal.ONE);
        outcomeOdd.setOutcome(populateOutcome());
        return outcomeOdd;
    }

    private Outcome populateOutcome() {
        Outcome outcome = new Outcome();
        outcome.setDescription("TestOutcome");
        return outcome;
    }

    private Bet populateBet() {
        Bet bet = new Bet();
        Outcome outcome = populateOutcome();
        outcome.setBet(bet);
        List<Outcome> outcomes = Collections.singletonList(outcome);
        bet.setOutcomes(outcomes);
        bet.setDescription("TestBet");
        bet.setType(Bet.BetType.GOALS);
        bet.setSportEvent(populateSportEvent());
        return bet;
    }

    private List<Bet> populateBets() {
        return Collections.singletonList(populateBet());
    }

    private List<Wager> populateWagers() {
        Wager wager = new Wager();
        OutcomeOdd outcomeOdd = new OutcomeOdd();
        outcomeOdd.setOutcome(populateOutcome());
        outcomeOdd.getOutcome().setOutcomeOdds(Collections.singletonList(populateOutcomeOdd()));
        outcomeOdd.getOutcome().setBet(populateBet());
        outcomeOdd.setValue(BigDecimal.TEN);
        wager.setOutcomeOdd(outcomeOdd);
        wager.setAmount(BigDecimal.TEN);

        return Collections.singletonList(wager);
    }


}