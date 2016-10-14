using System;
using NUnit.Framework;

[TestFixture]
public class StubSampleTest
{
    [Test]
    public void test_with_stub() 
    {
        HikerStub stub = new HikerStub();
        Hiker douglas = new Hiker(stub);

        Assert.That(douglas.AnswerAfterDelay(10), Is.EqualTo(42));
    }


    class HikerStub: RealHelper
    {
        public bool multiplierWasCalled = false;

        public override void WaitFor(int minutes)
        {
            //do nothing and avoid long wait
        }
    }
}
