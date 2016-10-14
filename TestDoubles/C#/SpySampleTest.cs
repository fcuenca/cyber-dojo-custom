using System;
using NUnit.Framework;

[TestFixture]
public class SpySampleTest
{
    [Test]
    public void test_with_spy() 
    {
        HikerSpy spy = new HikerSpy();
        Hiker douglas = new Hiker(spy);

        Assert.That(douglas.AnswerFor(6), Is.EqualTo(42));
        Assert.That(spy.multiplierWasCalled, Is.EqualTo(true));
    }


    class HikerSpy: RealHelper
    {
        public bool multiplierWasCalled = false;

        public override int GetMultiplier() 
        {
            multiplierWasCalled = true;
            return base.GetMultiplier();
        }
    }
}
