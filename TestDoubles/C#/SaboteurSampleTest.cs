using System;
using NUnit.Framework;

[TestFixture]
public class SaboteurSampleTest
{
    [Test]
    public void test_with_saboteur() 
    {
        HikerSaboteur saboteur = new HikerSaboteur();
        Hiker douglas = new Hiker(saboteur);

        Assert.Throws<ApplicationException>(() => douglas.AnswerFor(6));
    }


    class HikerSaboteur: RealHelper
    {
        public override int GetMultiplier() 
        {
            throw new ApplicationException();
        }
    }
}
