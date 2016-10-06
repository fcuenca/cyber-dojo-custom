using System;
using System.IO;

namespace UglyTrivia
{
  public class StdOut
  {
    public static StringWriter captureStdOut()
    {
      var consoleOut = new StringWriter();
      Console.SetOut(consoleOut);
      return consoleOut;
    }

    public static void restoreStdOut()
    {
      var standardOutput = new StreamWriter(Console.OpenStandardOutput());
      standardOutput.AutoFlush = true;
      Console.SetOut(standardOutput);
    }
  }
}
