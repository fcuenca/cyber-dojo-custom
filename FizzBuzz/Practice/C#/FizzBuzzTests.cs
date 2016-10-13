using System;
using NUnit.Framework;

[TestFixture]
public class FizzBuzzTests
{
    [Test]
    public void run()
    {
      Console.WriteLine(">>>>");
      for(int i = 1; i <= 100; i++)
      {
        if(i != 1) Console.Write(", ");
        Console.Write(FizzBuzz.ForNumber(i));
      }
      Console.WriteLine("\n<<<");
    }
}
