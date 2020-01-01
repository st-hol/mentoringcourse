package com.epam.training.sportsbetting.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(BettingDataPoolHolder.class)
public class BettingDataPoolHolderTest {

    private static final int OUTCOME_ID = 1;
    private static final String EVENT_TITLE = "Arsenal vs Chelsea";

    private BettingDataPoolHolder mockedInstance = BettingDataPoolHolder.getInstance();

    @Test
    public void whenGetOutcomeOdds_thenReturnListOfOdds() {
        assertThat(mockedInstance.getOutcomeOddsData().get(0).getId(), is(1));
    }

    @Test
    public void whenGetSportEvents_thenReturnListOfEvents() {
        assertThat(mockedInstance.getSportEventsData().get(0).getTitle(), is(EVENT_TITLE));
    }


}


//@RunWith(MockitoJUnitRunner.class)
//public class BettingDataPoolHolderTest {
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @Test
//    public void test(){
//        BettingDataPoolHolder underTest = BettingDataPoolHolder.getInstance();
//        underTest.getOutcomeOdds().forEach(sportEvent -> System.out.println(sportEvent));
//        assertTrue(1==1);
//    }
//}
//@RunWith(MockitoJUnitRunner.class)
//public class MyService_SayHello_Test {
//    @Mock FileWriter fw;
//    @Test
//    public void doSomeService_SayHello() throws Exception {
//        MyService service = MyService.getInstance();
//        service.init("Hello!");
//        service.doSomeService(fw);
//        verify(fw).write("Hello!");
//    }
//}