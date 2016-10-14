using System;
using Moq;
using NUnit.Framework;

[TestFixture]
public class MockSampleTest
{
    [Test]
    public void test_with_mock_object() 
    {
        var mock = new Mock<HikerHelper>();
        mock.Setup((m => m.GetMultiplier())).Returns(7);

        Hiker douglas = new Hiker(mock.Object);

        Assert.That(douglas.AnswerFor(6), Is.EqualTo(42));
    }
}
