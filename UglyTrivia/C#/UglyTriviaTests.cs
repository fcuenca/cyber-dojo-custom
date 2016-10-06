using System;
using System.IO;
using NUnit.Framework;

namespace UglyTrivia
{
    [TestFixture]
    public class UglyTriviaTests
    {
        private StringWriter _stream;
        private Game _game;
        
        [SetUp]
        public void SetUp()
        {
          _stream = StdOut.captureStdOut();
          _game = new Game();
        }
        
        [TearDown]
        public void TearDown()
        {
          StdOut.restoreStdOut();
        }
        
        [Test]
        public void outputs_nothing_when_created()
        {
          Assert.That(_stream.ToString(), Is.EqualTo(""));
        }
        
        [Test]
        public void outputs_player_name_and_number_when_added()
        {
        
          _game.add("Jim");
          _game.add("Bones");
        
          Assert.That(_stream.ToString(), Is.EqualTo(
              "Jim was added\n" +
              "They are player number 1\n" +
              "Bones was added\n" +
              "They are player number 2\n"));
        }
        
        [Test]
        public void is_playable_with_at_least_two_players()
        {
          Assert.That(_game.isPlayable(), Is.EqualTo(false));
        
          _game.add("Jim");
          Assert.That(_game.isPlayable(), Is.EqualTo(false));
        
          _game.add("Bones");
          Assert.That(_game.isPlayable(), Is.EqualTo(true));
        }

        [Test]
        public void rolling_without_adding_player_throws_exception()
        {
          int IRRELEVANT_VALUE = 0;
        
          Assert.Throws<ArgumentOutOfRangeException>(
            () => _game.roll(IRRELEVANT_VALUE));
        }


        [Test]
        public void player_added_first_is_current_when_rolling()
        {
          _game.add("Jim");
          _game.add("Spock");
        
          _game.roll(5);
        
          Assert.That(_stream.ToString(), Is.StringContaining(
            "Jim is the current player\n"));
        }
    }
}
