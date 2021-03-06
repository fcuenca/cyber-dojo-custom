using System;

namespace UglyTrivia
{
  public class GameRunner
  {
    private static bool notAWinner;

    public static void PlayTheGame()
    {
      Random rand = new Random();
      Game aGame = new Game();

      Console.WriteLine("\n\n--- Trivia Game ---");

      aGame.add("Chet");
      aGame.add("Pat");
      aGame.add("Sue");

      do
      {
        aGame.roll(rand.Next(5) + 1);

        if (rand.Next(9) == 7)
        {
          notAWinner = aGame.wrongAnswer();
        }
        else
        {
          notAWinner = aGame.wasCorrectlyAnswered();
        }

      } while (notAWinner);
    }
  }
}
