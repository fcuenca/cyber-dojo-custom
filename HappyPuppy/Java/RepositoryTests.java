import java.util.*;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class RepositoryTests
{
    private Repository.ListBasedRepository repo;

    @Before
    public void SetUp()
    {
         repo = new Repository.ListBasedRepository(CreateTestDatabase());   
    }

    @Test
    public void retrieving_puppies()
    {
        assertThat(repo.RetrievePuppies(), hasItems(
           "Spike", "Pluto", "Daisy"));
    }

    @Test
    public void retrieving_owners()
    {
        assertThat(repo.RetrieveOwners(), hasItems("Tom", "Jane"));
    }

    @Test
    public void removing_puppies()
    {
        repo.RemovePuppy("Daisy");

        assertThat(repo.RetrievePuppies(), not(hasItem("Daisy")));
    }

    @Test
    public void adding_owners()
    {
        repo.AddOwner("Fernando");
        assertThat(repo.RetrieveOwners(), hasItem("Fernando") );
    }

    private Map<String, List<String>> CreateTestDatabase()
    {
        Map<String, List<String>> db = 
            new HashMap<String, List<String>>();

        db.put("owners", new ArrayList<String>(
            Arrays.asList(new String[] { "Tom", "Jane" })));

        db.put("puppies", new ArrayList<String>(
            Arrays.asList(new String[] { "Spike", "Pluto", "Daisy" })));

        return db;
     }
}
