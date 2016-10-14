using System;
using Moq;
using NUnit.Framework;

[TestFixture]
public class RealHikerTest
{
    [Test]
    public void test_the_real_thing() 
    {
        Hiker douglas = new Hiker(new RealHelper());

        Assert.That(douglas.AnswerFor(6), Is.EqualTo(42));
    }

}
