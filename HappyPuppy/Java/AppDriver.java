import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AppDriver
{
    private static Map<String, List<String>> database;
    private static AppDriver app = null;

    private boolean adoptionWasConfirmed = false;

    public static void InitializeDatabase()
    {
        database = new HashMap<String, List<String>>();
    }
    
    public static void AddTable(String tableName, List<String> data)
    {
        database.put(tableName, new ArrayList<String>(data));
    }

    
    public static AppDriver App()
    {
        if(app == null)
        {
            AppEnv.repo = new Repository.ListBasedRepository(database);
            app = new AppDriver();
        }
        return app;
    }

    public void AdoptPuppy(String puppyName, String customerName)
    {
        adoptionWasConfirmed = false;
       
        TestDoubles.NotificationSpy spy = new TestDoubles.NotificationSpy();

        AppEnv.AdoptPuppy().Execute(puppyName, customerName, spy);

        adoptionWasConfirmed = spy.notificationReceived;
    }

    public void CustomerShouldBeListedAsOwner(String newOwner)
    {
        TestDoubles.DisplaySpy spy = new TestDoubles.DisplaySpy();
 
        AppEnv.ListOwners().Execute(spy);

        assertThat(spy.observedValues, hasItem(newOwner));
    }

    public void PuppyListShouldNotInclude(String puppyName)
    {
        TestDoubles.DisplaySpy spy = new TestDoubles.DisplaySpy();
 
        AppEnv.ListPuppies().Execute(spy);

        assertThat(spy.observedValues, not(hasItem(puppyName)));
    }

    public void ShouldHaveReceivedAdoptionConfirmation()
    {
        assertThat(adoptionWasConfirmed, is(true));
    }
    
}
