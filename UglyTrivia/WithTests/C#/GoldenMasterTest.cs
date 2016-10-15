using System;
using System.IO;
using NUnit.Framework;

namespace UglyTrivia
{
  [TestFixture]
  public class GoldenMasterTest
  {
    private bool DISABLED = false;

    private const int NUMBER_OF_SEEDS = 2;
    private const bool GENERATE = false;

    #pragma warning disable 162 // disable the "code unreachable" warning
    [Test]
    public void golden_master_test()
    {
      if (DISABLED) return;

      if (GENERATE)
      {
        generate_golden_master();
      }
      else
      {
        verify_game_against_golden_master();
      }
    }
    #pragma warning restore 162

    private void generate_golden_master()
    {
      Console.WriteLine(">>>>");
      for (int seed = 0; seed < NUMBER_OF_SEEDS; seed++)
      {
        StringWriter stdOut;
        try
        {
          stdOut = StdOut.captureStdOut();
          GameRunner.Run(new Random(seed));
        }
        finally
        {
          StdOut.restoreStdOut();
        }

        writeToGoldenMaster(stdOut.ToString(), seed);
      }
      Console.WriteLine("<<<<");
    }

    private void verify_game_against_golden_master() 
    {
      for (int seed = 0; seed < NUMBER_OF_SEEDS; seed++)
      {
        try
        {
          StringWriter stdOut = StdOut.captureStdOut();
          string goldenMaster = readFromGoldenMaster(seed);
          GameRunner.Run(new Random(seed));

          Assert.That(stdOut.ToString(), Is.EqualTo(goldenMaster),
                 "golden master mismatch for seed " + seed);
        }
        finally
        {
          StdOut.restoreStdOut();
        }
      }
    }

    private void writeToGoldenMaster(String content, long seed)
    {
            Console.WriteLine("\"" +
                content.Replace("\n", "\\n") +
                "\",");
    }

    private String readFromGoldenMaster(long seed) 
    {
      return GoldenMasterData.data[seed];
    }

  } 
}