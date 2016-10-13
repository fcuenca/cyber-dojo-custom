import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FizzBuzzTest {

    @Test
    public void test()
    {
      System.out.println(">>>");
      for(int i = 1; i <= 100; i++)
      {
        if(i != 1) System.out.print(", ");
        System.out.print(FizzBuzz.fromNumber(i));
      }
      System.out.println("\n<<<");
    }
}
