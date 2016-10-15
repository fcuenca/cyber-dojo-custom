
import java.util.Random;


public class GameRunner {

  public static void playTheGame() {

    boolean notAWinner;

    Random rand = new Random();
    Game aGame = new Game();

    System.out.println("\n\n--- Trivia Game ---");

    aGame.add("Chet");
    aGame.add("Pat");
    aGame.add("Sue");

    do {

      aGame.roll(rand.nextInt(5) + 1);

      if (rand.nextInt(9) == 7) {
        notAWinner = aGame.wrongAnswer();
      } else {
        notAWinner = aGame.wasCorrectlyAnswered();
      }

    } while (notAWinner);
  }
}
