using System.Collections.Generic;

class DisplayListUseCase_ForTest: DisplayListUseCase
{
    public DisplayListUseCase_ForTest(): base(null) {}

    public override string Heading { get { return "NOT USED"; } }

    protected override IEnumerable<string> GetList()
    {
        return new string[] { "one", "two", "three" };
    }
}

class FakeActionRequest: ActionRequest
{
    public DisplayResponse observedPort = null;
    private string heading;
    private string outText;

    public FakeActionRequest(string heading, string outText)
    {
        this.heading = heading;
        this.outText = outText;
    }

    public string Heading 
    { 
        get { return heading; }
    }

    public void Execute(DisplayResponse port) 
    {
        observedPort = port;
        port(outText);
    }
}

class FakeAdoptPuppyRequest: AdoptPuppyRequest
{
    public string observedPuppy = "";
    public string observedOwner = "";
    private string heading;

    public FakeAdoptPuppyRequest(string heading)
    {
        this.heading = heading;
    }

    public string Heading { get { return heading; } }

    public void Execute(string puppyName, string ownerName, 
        NotificationResponse port)
    {
        observedPuppy = puppyName;
        observedOwner = ownerName;
        port();
    }
}

public class FakeRepository: HappyPuppyRepository
{
    public bool removeWasCalled = false;
    public bool addWasCalled = false;
    public string observedRemoveName = "";
    public string observedAddName = "";

    public IEnumerable<string> RetrieveOwners()
    {
        return new string[] { "Joe", "Mary" };
    }

    public IEnumerable<string> RetrievePuppies()
    {
        return new string[] { "Spike", "Betsy" };
    }

    public void RemovePuppy(string name)
    {
        removeWasCalled = true;
        observedRemoveName = name;
    }

    public void AddOwner(string name)
    {
        addWasCalled = true;
        observedAddName = name;
    }
}
