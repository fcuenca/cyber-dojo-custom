import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UseCaseTests
{
    @Test
    public void DisplayListUseCase_shows_every_item_in_supplied_list()
    {
        assertUseCaseDisplaysList(
            new TestDoubles.DisplayListUseCase_ForTest(),
            new String[] { "one", "two", "three" });
    }

    @Test
    public void listing_puppies_returns_all_available_puppies()
    {
        TestDoubles.FakeRepository fakeRepo = new TestDoubles.FakeRepository(); 

        assertUseCaseDisplaysList(
            new UseCases.ListPuppiesUseCase(fakeRepo),
            new String[] { "Spike", "Betsy" });
    }

    @Test
    public void listing_owners_returns_all_knwown_owners()
    {
        TestDoubles.FakeRepository fakeRepo = new TestDoubles.FakeRepository(); 

        assertUseCaseDisplaysList(
            new UseCases.ListOwnersUseCase(fakeRepo),
            new String[] { "Joe", "Mary" });
    }

    @Test
    public void adopting_puppy_makes_it_unavailable()
    {
        TestDoubles.FakeRepository fakeRepo = new TestDoubles.FakeRepository(); 

        UseCases.AdoptPuppyUseCase uc = new UseCases.AdoptPuppyUseCase(fakeRepo);

        uc.Execute("Spike", "IRRELEVANT", TestDoubles.doNothingNotification);

        assertThat(fakeRepo.removeWasCalled, is(true));
        assertThat(fakeRepo.observedRemoveName, is("Spike"));
    }

    @Test
    public void adopting_puppy_registers_the_new_owner()
    {
        TestDoubles.FakeRepository fakeRepo = new TestDoubles.FakeRepository(); 

        UseCases.AdoptPuppyUseCase uc = new UseCases.AdoptPuppyUseCase(fakeRepo);

        uc.Execute("IRRELEVANT", "Marc", TestDoubles.doNothingNotification);

        assertThat(fakeRepo.addWasCalled, is(true));
        assertThat(fakeRepo.observedAddName, is("Marc"));
    }

    @Test
    public void adopting_a_puppy_generates_notification_on_completion()
    {
        TestDoubles.FakeRepository fakeRepo = new TestDoubles.FakeRepository(); 
        TestDoubles.NotificationSpy spy = new TestDoubles.NotificationSpy();

        UseCases.AdoptPuppyUseCase uc = new UseCases.AdoptPuppyUseCase(fakeRepo);

        uc.Execute("Spike", "Marc", spy);

        assertThat(spy.notificationReceived, is(true));
    }

    private void assertUseCaseDisplaysList(
        UseCases.DisplayListUseCase uc, String[] expectedList)
    {
        List<String> observedItems = new ArrayList<String>();

        Boundaries.DisplayResponse port = new Boundaries.DisplayResponse()
        {
            @Override
            public void Accept(String s) { observedItems.add(s); }
        };

        uc.Execute(port);
        
        assertThat(observedItems, hasItems(expectedList));
    }

}
