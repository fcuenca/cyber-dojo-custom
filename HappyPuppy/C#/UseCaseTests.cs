using System;
using NUnit.Framework;
using System.Collections.Generic;

[TestFixture]
public class UseCaseTests
{
    [Test]
    public void DisplayListUseCase_shows_every_item_in_supplied_list()
    {
        assertUseCaseDisplaysList(
            new DisplayListUseCase_ForTest(),
            new string[] { "one", "two", "three" });
    }

    [Test]
    public void listing_puppies_returns_all_available_puppies()
    {
        FakeRepository fakeRepo = new FakeRepository(); 

        assertUseCaseDisplaysList(
            new ListPuppiesUseCase(fakeRepo),
            new string[] { "Spike", "Betsy" });
    }

    [Test]
    public void listing_owners_returns_all_knwown_owners()
    {
        FakeRepository fakeRepo = new FakeRepository(); 

        assertUseCaseDisplaysList(
            new ListOwnersUseCase(fakeRepo),
            new string[] { "Joe", "Mary" });
    }

    [Test]
    public void adopting_puppy_makes_it_unavailable()
    {
        FakeRepository fakeRepo = new FakeRepository(); 

        AdoptPuppyUseCase uc = new AdoptPuppyUseCase(fakeRepo);

        uc.Execute("Spike", "Marc", () => {});

        Assert.That(fakeRepo.removeWasCalled, Is.True);
        Assert.That(fakeRepo.observedRemoveName, Is.EqualTo("Spike"));
    }

    [Test]
    public void adopting_puppy_registers_the_new_owner()
    {
        FakeRepository fakeRepo = new FakeRepository(); 

        AdoptPuppyUseCase uc = new AdoptPuppyUseCase(fakeRepo);

        uc.Execute("Spike", "Marc", () => {});

        Assert.That(fakeRepo.addWasCalled, Is.True);
        Assert.That(fakeRepo.observedAddName, Is.EqualTo("Marc"));
    }

    [Test]
    public void adopting_a_puppy_generates_notification_on_completion()
    {
        FakeRepository fakeRepo = new FakeRepository(); 
        bool notificationReceived = false;

        AdoptPuppyUseCase uc = new AdoptPuppyUseCase(fakeRepo);

        uc.Execute("Spike", "Marc", () => notificationReceived = true);

        Assert.That(notificationReceived, Is.True);
    }

    private void assertUseCaseDisplaysList(DisplayListUseCase uc,
        IEnumerable<string> expectedList)
    {
        List<string> observedItems = new List<string>();
        uc.Execute(s => observedItems.Add(s));
        
        Assert.That(observedItems, Is.EqualTo(expectedList));
    }

}
