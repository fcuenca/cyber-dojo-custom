import org.junit.*;
import static org.junit.Assert.*;

public class DemoTest
{
    private boolean DEMO_TIME = true;

    @Test
    public void elephant_carpaccio_demo() 
    {
        if(!DEMO_TIME) return;

        ElephantCarpaccio.sliceIt();
    }
}
