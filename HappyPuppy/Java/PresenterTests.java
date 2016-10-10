import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PresenterTests
{
    private ByteArrayOutputStream _consoleOut;

    @Before
    public void SetUp()
    {
        _consoleOut = captureStdOut();
    }

    @After
    public void TearDown()
    {
        resetStdOutToConsole();
    }

    @Test
    public void ListPresenter_displays_use_case_heading()
    {
        Presenters.ListPresenter cmd = new Presenters.ListPresenter(
            new TestDoubles.FakeActionRequest("THIS IS THE HEADING", "IRRELEVANT"));

        cmd.Execute();

        assertThat(_consoleOut.toString(), 
            containsString("THIS IS THE HEADING"));
    }

    @Test
    public void ListPresenter_triggers_the_output_port_supplying_a_callback()
    {
        TestDoubles.FakeActionRequest fakePort = 
            new TestDoubles.FakeActionRequest("IRRELEVANT", "IRRELEVANT");
        Presenters.ListPresenter cmd = new Presenters.ListPresenter(fakePort);

        cmd.Execute();

        assertThat(fakePort.observedPort, is(notNullValue()));
    }

    @Test
    public void ListPresenter_displays_results_on_console()
    {
        Presenters.ListPresenter cmd = new Presenters.ListPresenter(
            new TestDoubles.FakeActionRequest("IRRELEVANT", "FAKE OUTPUT"));

        cmd.Execute();

        assertThat(_consoleOut.toString(), containsString("FAKE OUTPUT"));
    }

    @Test
    public void AdoptPuppyPresenter_displays_use_case_heading()
    {
        Presenters.AdoptPuppyPresenter cmd = new Presenters.AdoptPuppyPresenter(
            "IRRELEVANT", "IRRELEVANT", 
            new TestDoubles.FakeAdoptPuppyRequest("ADOPT PUPPY HEADING"));

        cmd.Execute();

        assertThat(_consoleOut.toString(), 
            containsString("ADOPT PUPPY HEADING"));
    }

    @Test
    public void AdoptPuppyPresenter_triggers_execution_through_input_port()
    {
        TestDoubles.FakeAdoptPuppyRequest fakePort =
             new TestDoubles.FakeAdoptPuppyRequest("IRRELEVANT");
        Presenters.AdoptPuppyPresenter cmd = new Presenters.AdoptPuppyPresenter(
            "the puppy", "the owner", fakePort);

        cmd.Execute();

        assertThat(fakePort.observedPuppy, is("the puppy"));
        assertThat(fakePort.observedOwner, is("the owner"));
    }

    @Test
    public void AdoptPuppyPresenter_displays_adoption_confirmation()
    {
        Presenters.AdoptPuppyPresenter cmd = new Presenters.AdoptPuppyPresenter(
            "Spike", "Tom", 
            new TestDoubles.FakeAdoptPuppyRequest("IRRELEVANT"));

        cmd.Execute();

        assertThat(_consoleOut.toString(), 
            containsString("Spike was adopted by: Tom"));
    }

    public static ByteArrayOutputStream captureStdOut()
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);
        
        return stream;
    }
    
    public static void resetStdOutToConsole()
    {
        System.setOut(new PrintStream(
        new FileOutputStream(FileDescriptor.out)));
    }
}
