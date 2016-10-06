using System;
using NUnit.Framework;

namespace UglyTrivia
{
    [TestFixture]
    public class GameRunnerTest
    {
        private static bool DISABLED = true;
    
        [Test]
        public void play_the_game()
        {
            if(DISABLED) return;
    
            GameRunner.PlayTheGame();
        }
    }
}
