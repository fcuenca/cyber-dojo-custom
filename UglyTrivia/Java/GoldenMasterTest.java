import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import org.junit.Test;

public class GoldenMasterTest
{
        private static Boolean DISABLED = true;

	private static final int NUMBER_OF_SEEDS = 2;
	private static final boolean GENERATE = false;

	@Test
	public void golden_master_test() 
	{
                if(DISABLED) return;

		if(GENERATE) 
		{
			generate_golden_master();
		}
		else
		{
			verify_game_against_golden_master();
		}
	}
	
	public void generate_golden_master() 
	{
                System.out.println(">>>>");
		for (int seed = 0; seed < NUMBER_OF_SEEDS; seed++)
		{
			ByteArrayOutputStream stream = StdOut.capture();
			GameRunner.playTheGame(new Random(seed));	
                        StdOut.resetToConsole();	

			writeToGoldenMaster(stream.toString(), seed);
		}
                System.out.println("<<<<");
	}
	
	public void verify_game_against_golden_master() 
	{
		for (int seed = 0; seed < NUMBER_OF_SEEDS; seed++)
		{
			ByteArrayOutputStream stream = StdOut.capture();
			String goldenMaster = readFromGoldenMaster(seed);
			GameRunner.playTheGame(new Random(seed));
			assertThat( "golden master mismatch for seed " + seed, stream.toString(), is(goldenMaster));		
		}
	}
	
	private void writeToGoldenMaster(String content, int seed) 
	{
            System.out.println("\"" +
                content.replaceAll("\n", "\\\\n") +
                "\",");
	}

	private String readFromGoldenMaster(int seed) 
	{
            return GoldenMasterData.data[seed];
	}
}

