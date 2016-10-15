using System;
using NUnit.Framework;

namespace UglyTrivia
{
    [TestFixture]
    public class GameRunnerTest
    {
        [Test]
        public void play_the_game()
        {    
            GameRunner.PlayTheGame();
        }
    }
}
