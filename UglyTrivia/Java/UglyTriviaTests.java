import org.junit.*;
import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class UglyTriviaTests
{
	private ByteArrayOutputStream _stream;
	private Game _game;

	@Before
	public void setUp() 
	{
		_stream = StdOut.capture();
		_game = new Game();		
	}

	@Test
	public void outputs_nothing_when_created()
	{
		assertThat(_stream.toString(), is(""));
	}
	
	@Test
	public void outputs_player_name_and_number_when_added()
	{
		_game.add("Jim");		
		_game.add("Bones");		

		assertThat(_stream.toString(), is(
				"Jim was added\n" + 
				"They are player number 1\n" + 
				"Bones was added\n" + 
				"They are player number 2\n"));
	}

	@Test
	public void is_playable_with_at_least_two_players()
	{
		assertThat(_game.isPlayable(), is(false));
		
		_game.add("Jim");
		assertThat(_game.isPlayable(), is(false));

		_game.add("Bones");
		assertThat(_game.isPlayable(), is(true));
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void rolling_without_adding_player_throws_exception()
	{
		int IRRELEVANT_VALUE = 0;
		
		_game.roll(IRRELEVANT_VALUE);
	}

	@Test
	public void player_added_first_is_current_when_rolling()
	{
		_game.add("Jim");
		_game.add("Spock");
		
		_game.roll(5);
		
		assertThat(_stream.toString(), containsString("Jim is the current player\n"));
	}


}

