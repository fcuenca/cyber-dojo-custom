import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FakeSampleTest
{
    @Test
    public void test_with_fake() 
    {
        HikerFake fake = new HikerFake();
        Hiker douglas = new Hiker(fake);

        assertThat(douglas.answerFor(10), is(10));
    }

    class HikerFake extends HikerHelper
    {
        @Override
        public int multiplier() 
        {
            return 1;
        }
    }
}
