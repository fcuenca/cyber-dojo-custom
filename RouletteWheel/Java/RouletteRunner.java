import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RouletteRunner
{
    boolean DISABLED = false;

    @Test
    public void run_simulation()
    {
        if(DISABLED) return;

        System.out.println("\n<<<");
        System.out.println("** your simulation goes here **");
        System.out.println("<<<\n");
    }
}
