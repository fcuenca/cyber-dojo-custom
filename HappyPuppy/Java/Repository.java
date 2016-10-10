import java.util.*;

public class Repository
{
    public interface HappyPuppyRepository
    {
        Iterable<String> RetrieveOwners();
        Iterable<String> RetrievePuppies();
        void RemovePuppy(String name);
        void AddOwner(String name);
    }

    public static class ListBasedRepository implements HappyPuppyRepository
    {
            private Map<String, List<String>> database;
    
            public ListBasedRepository(Map<String, List<String>> database)
            {
                this.database = database;
            }
    
            public Iterable<String> RetrieveOwners()
            {
                return database.get("owners");
            }
    
            public Iterable<String> RetrievePuppies()
            {
                return database.get("puppies");
            }
    
            public void RemovePuppy(String name)
            {
                database.get("puppies").remove(name);
            }
    
            public void AddOwner(String name)
            {
                database.get("owners").add(name);
            }
    }
}
