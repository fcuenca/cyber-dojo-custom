import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

public class MockSampleTest 
{
    @Test
    public void test_with_mock_object() 
    {
        HikerHelper mock = mock(HikerHelper.class);
        Hiker douglas = new Hiker(mock);

        when(mock.multiplier()).thenReturn(7);

        assertThat(douglas.answerFor(6), is(42));
    }
}
