import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HappyPuppyTest
{
    private boolean DISABLED = true;

    @Test
    public void run_the_app()
    {
        if(DISABLED) return;

        InitializeDatabase();
        RunApplication();
    }

    private void RunApplication()
    {
        HappyPuppyApp hp = new HappyPuppyApp();

        hp.StartSession();
        hp.ListPuppies(); 
        hp.ListOwners();
        hp.Adopt("Brook", "Fernando");
        hp.ListOwners();
        hp.ListPuppies();
        hp.EndSession();
    }

    private void InitializeDatabase()
    {
        Map<String, List<String>> db = 
            new HashMap<String, List<String>>();
        
        db.put("owners", new ArrayList<String>(
            Arrays.asList(new String[] { "Mary", "Peter" })));
        
        db.put("puppies", new ArrayList<String>(
            Arrays.asList(
                new String[] { "Brook", "Hanna", "Maggie Mae", "Ruby Sue" })));
        
        AppEnv.repo = new Repository.ListBasedRepository(db);
    }

}
