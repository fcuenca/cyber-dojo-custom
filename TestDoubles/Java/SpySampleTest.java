import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SpySampleTest
{
    @Test
    public void test_with_spy() 
    {
        HikerSpy spy = new HikerSpy();
        Hiker douglas = new Hiker(spy);

        assertThat(douglas.answerFor(10), is(90));
        assertThat(spy.multiplierWasCalled, is(true));
    }

    class HikerSpy extends HikerHelper
    {
        public boolean multiplierWasCalled = false;

        @Override
        public int multiplier() 
        {
            multiplierWasCalled = true;
            return super.multiplier();
        }
    }
}
