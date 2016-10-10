import java.util.*;

public class TestDoubles
{

    public static class FakeActionRequest implements Boundaries.ActionRequest
    {
        public Boundaries.DisplayResponse observedPort = null;
        private String heading;
        private String outText;
    
        public FakeActionRequest(String heading, String outText)
        {
            this.heading = heading;
            this.outText = outText;
        }
    
        public String Heading()
        { 
            return heading; 
        }
    
        public void Execute(Boundaries.DisplayResponse port) 
        {
            observedPort = port;
            port.Accept(outText);
        }
    }

    public static class FakeAdoptPuppyRequest implements Boundaries.AdoptPuppyRequest
    {
        public String observedPuppy = "";
        public String observedOwner = "";
        private String heading;
    
        public FakeAdoptPuppyRequest(String heading)
        {
            this.heading = heading;
        }
    
        public String Heading() { return heading; }
    
        public void Execute(String puppyName, String ownerName, 
            Boundaries.NotificationResponse port)
        {
            observedPuppy = puppyName;
            observedOwner = ownerName;
            port.ActionWasCompleted();
        }
    }

    public static class DisplayListUseCase_ForTest 
            extends UseCases.DisplayListUseCase
    {
        public DisplayListUseCase_ForTest()
        { 
            super(null);
        }
    
        @Override
        public String Heading() { return "NOT USED"; }
    
        @Override
        protected Iterable<String> GetList()
        {
            return Arrays.asList(new String[] { "one", "two", "three" });
        }
    }

    public static Boundaries.NotificationResponse doNothingNotification = 
        new Boundaries.NotificationResponse () 
    {
        @Override public void ActionWasCompleted() { /* do nothing */ } 
    };

    public static class NotificationSpy implements Boundaries.NotificationResponse
    {
        public boolean notificationReceived = false;

        @Override 
        public void ActionWasCompleted() 
        {
            notificationReceived = true; 
        } 
    }

    public static class DisplaySpy implements Boundaries.DisplayResponse
    {
        List<String> observedValues = new ArrayList<String>();  

        @Override
        public void Accept(String s)
        {
            observedValues.add(s);
        }
    }

    public static class FakeRepository implements Repository.HappyPuppyRepository
    {
        public boolean removeWasCalled = false;
        public boolean addWasCalled = false;
        public String observedRemoveName = "";
        public String observedAddName = "";
    
        public Iterable<String> RetrieveOwners()
        {
            return Arrays.asList(new String[] { "Joe", "Mary" });
        }
    
        public Iterable<String> RetrievePuppies()
        {
            return Arrays.asList(new String[] { "Spike", "Betsy" });
        }
    
        public void RemovePuppy(String name)
        {
            removeWasCalled = true;
            observedRemoveName = name;
        }
    
        public void AddOwner(String name)
        {
            addWasCalled = true;
            observedAddName = name;
        }
    }
}