using System;
using NUnit.Framework;

[TestFixture]
public class FakeSampleTest
{
    [Test]
    public void test_with_fake() 
    {
        HikerFake fake = new HikerFake();
        Hiker douglas = new Hiker(fake);

        Assert.That(douglas.AnswerFor(6), Is.EqualTo(42));
    }


    class HikerFake: RealHelper
    {
        public override int GetMultiplier() 
        {
            return 7;
        }
    }
}
