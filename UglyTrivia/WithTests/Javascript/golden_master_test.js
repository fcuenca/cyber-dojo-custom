'use strict';

let ugly_trivia = require('./ugly_trivia.js');
let captures = require('./captures.js');
let golden_master = require('./golden_master_data.js');

let GENERATE = false;
let NUMBER_OF_SEEDS = 2;

describe('Golden Master Test', function () {

  var stdout;
  var random;

  if(GENERATE)
  {
    it('generates the Golden Master', function () {

      console.log(">>>>>");
  
      for (var seed = 0; seed < NUMBER_OF_SEEDS; seed++) {
        stdout = new captures.stdout();
        random = new captures.random(seed);

        ugly_trivia.Run();

        random.restore();
        stdout.restore();

        writeToGoldenMaster(stdout.capturedOutput);
      }
      console.log("<<<<<");
    });
  }
  else
  {
    it('checks simulation against the Golden Master', function () {
  
      for (var seed = 0; seed < NUMBER_OF_SEEDS; seed++) {
        stdout = new captures.stdout();
        random = new captures.random(seed);

        ugly_trivia.Run();

        random.restore();
        stdout.restore();

        var goldenMaster = readFromGoldenMaster(seed);
        stdout.capturedOutput.should.equal(goldenMaster, 
          "Golden Master mismatch for seed " + seed);
      }
    });
  }

  function writeToGoldenMaster(content) {
     console.log("\"" +
            content.replace(/\n/g, "\\n") +
                "\",");
  }

  function readFromGoldenMaster(seed) {
    return golden_master.data[seed];
  }


});

