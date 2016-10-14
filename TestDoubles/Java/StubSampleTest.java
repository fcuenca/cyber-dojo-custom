import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StubSampleTest
{
    @Test
    public void test_with_stub()
    {
        HikerStub stub = new HikerStub();
        Hiker douglas = new Hiker(stub);

        assertThat(douglas.answerAfterDelay(10), is(42));
    }

    class HikerStub extends HikerHelper
    {
        @Override
        public void waitFor(int minutes)
        {
            //do nothing and avoid long wait
        }
    }
}
