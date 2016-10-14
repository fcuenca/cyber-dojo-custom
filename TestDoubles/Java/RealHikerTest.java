import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

public class RealHikerTest
{
    @Test
    public void test_the_real_thing() 
    {
        Hiker douglas = new Hiker(new HikerHelper());

        assertThat(douglas.answerFor(10), is(90));
    }
}
