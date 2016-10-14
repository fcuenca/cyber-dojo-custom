import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SaboteurSampleTest
{
    @Test(expected = RuntimeException.class)
    public void test_with_saboteur()
    {
        HikerSaboteur saboteur = new HikerSaboteur();
        Hiker douglas = new Hiker(saboteur);

        douglas.answerFor(10);
    }

    class HikerSaboteur extends HikerHelper
    {
        @Override
        public int multiplier() 
        {
            throw new RuntimeException();
        }
    }
}
