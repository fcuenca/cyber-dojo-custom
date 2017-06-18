'use strict';

let ugly_trivia = require('./ugly_trivia.js');
let captures = require('./captures.js');

describe('Ugly Triva Game', function () {

  var stdout;
  var game;

  function _it(test_name, test_func) {
    it(test_name, function() {
      stdout = new captures.stdout();
      game = new ugly_trivia.Game();
      try {
        test_func();  
      }
      finally {
        stdout.restore();
      }
    });
  }

  _it('outputs nothing when game is created', function () {
      stdout.capturedOutput.should.be.equal('');
  });

  _it('outputs player name and number when added', function () {
    game.add("Jim");		
    game.add("Bones");		

    stdout.capturedOutput.should.be.equal(
      "Jim was added\n" + 
      "They are player number 1\n" + 
      "Bones was added\n" + 
      "They are player number 2\n");
  });

  _it('is playable with at least two players', function () {
      game.isPlayable().should.be.equal(false);
		
      game.add("Jim");
      game.isPlayable().should.be.equal(false);

      game.add("Bones");
      game.isPlayable().should.be.equal(true);
  });

  _it('operates on undefined values when rolling without adding a player', function () {
    var IRRELEVANT_VALUE = 0;
		
    game.roll(IRRELEVANT_VALUE);

    stdout.capturedOutput.should.have.string(
      'undefined is the current player\n' +
      'They have rolled a 0\n' + 
      'undefined\'s new location is NaN');
  });

  _it('uses first added player as current in the first roll', function () {
    game.add("Jim");
    game.add("Spock");
		
    game.roll(5);
		
    stdout.capturedOutput.should.have.string("Jim is the current player\n");
  });
});
