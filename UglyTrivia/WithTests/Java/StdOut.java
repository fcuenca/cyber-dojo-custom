import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;

public class StdOut
{
	public static ByteArrayOutputStream capture()
	{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(stream);
		System.setOut(printStream);

		return stream;
	}

        public static void resetToConsole()
        {
                System.setOut(new PrintStream(
                    new FileOutputStream(FileDescriptor.out)));
        }
}
