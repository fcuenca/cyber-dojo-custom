'use strict';

module.exports.stdout = CapturedStdout;
module.exports.random = CapturedRandomWithSeed;

function CapturedStdout() {
  var self = this;
  self.oldStdout = process.stdout.write;
  self.capturedOutput = "";

  process.stdout.write = function(data) {
    self.capturedOutput += data;

  };
}

CapturedStdout.prototype.restore = function() {
  process.stdout.write = this.oldStdout;
};

function CapturedRandomWithSeed(seed) {
  var self = this;
  self.oldRandom = Math.random;
  self.seed = seed + 1;
  Math.random = _randomUsingSeed;

  function _randomUsingSeed() {
      var x = Math.sin(self.seed++) * 10000;
      return x - Math.floor(x);
  }
}

CapturedRandomWithSeed.prototype.restore = function() {
  Math.random = this.oldRandom;
};

