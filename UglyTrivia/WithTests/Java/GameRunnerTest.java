import org.junit.*;

public class GameRunnerTest
{
        private static Boolean DISABLED = true;

	@Test
	public void play_the_game()
	{
                if(DISABLED) return;

		GameRunner.playTheGame();
	}
}
