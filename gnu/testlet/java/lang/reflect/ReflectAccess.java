// Tags: JDK1.1
// Uses: sub/OtherPkg sub/Super Other

// Test reflection member accessibility checks.

package gnu.testlet.java.lang.reflect;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.lang.reflect.*;
import gnu.testlet.java.lang.reflect.sub.*;

public class ReflectAccess extends Super implements Testlet
{
  TestHarness harness;
  
  public void test(TestHarness harness)
  {
    this.harness = harness;
    try 
    {
      doTest();
    }
    catch (Exception x)
    {
      harness.debug(x);
      harness.fail(x.toString());
    }
  }
  
  void doTest() throws Exception
  {
    Method methodA = ReflectAccess.class.getDeclaredMethod("a", null);
    Method methodB = ReflectAccess.class.getDeclaredMethod("b", null);
    Method methodC = ReflectAccess.class.getDeclaredMethod("c", null);

    Field fieldD = ReflectAccess.class.getDeclaredField("d");
    Field fieldE = ReflectAccess.class.getDeclaredField("e");
    Field fieldF = ReflectAccess.class.getDeclaredField("f");
    
    Method methodG = OtherPkg.class.getDeclaredMethod("g", null);
    Method methodH = OtherPkg.class.getDeclaredMethod("h", null);
    Method methodI = OtherPkg.class.getDeclaredMethod("i", null);

    Field fieldJ = OtherPkg.class.getDeclaredField("j");
    Field fieldK = OtherPkg.class.getDeclaredField("k");
    Field fieldL = OtherPkg.class.getDeclaredField("l");
    
    Method methodM = Other.class.getDeclaredMethod("m", null);
    Method methodN = Other.class.getDeclaredMethod("n", null);
    Method methodO = Other.class.getDeclaredMethod("o", null);

    Field fieldP = Other.class.getDeclaredField("p");
    Field fieldQ = Other.class.getDeclaredField("q");
    Field fieldR = Other.class.getDeclaredField("r");
    
    try
    {
      Method methodT = ReflectAccess.class.getDeclaredMethod("t", null);
      harness.fail(methodT + " is not declared in class ReflectAccess");
    }
    catch (NoSuchMethodException x)
    {
      // ok
    }
    
    Method methodS = Super.class.getDeclaredMethod("s", null);
    Method methodT = Super.class.getDeclaredMethod("t", null);
    Method methodU = Super.class.getDeclaredMethod("u", null);
    Method methodV = Super.class.getDeclaredMethod("v", null);

    Field fieldW = Super.class.getDeclaredField("w");
    Field fieldX = Super.class.getDeclaredField("x");
    Field fieldY = Super.class.getDeclaredField("y");
    Field fieldZ = Super.class.getDeclaredField("z");

    Object obj = new ReflectAccess();
    
    methodA.invoke(obj, null);
    methodB.invoke(null, null);
    methodC.invoke(obj, null);
    
    harness.check (fieldD.getChar(obj) == 'd');
    harness.check (fieldE.getChar(obj) == 'e');
    harness.check (fieldF.getChar(obj) == 'f');

    obj = new OtherPkg();
    
    try
    {
      methodG.invoke(obj, null);
      harness.fail(methodG + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    try
    {
      methodH.invoke(obj, null);
      harness.fail(methodH + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    try
    {
      methodI.invoke(obj, null);
      harness.fail(methodI + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    try
    {
      fieldJ.getChar(obj);
      harness.fail(fieldJ + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    try
    {
      fieldK.getChar(obj);
      harness.fail(fieldK + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    try
    {
      fieldL.getChar(obj);
      harness.fail(fieldL + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    
    obj = new Other();

    methodM.invoke(null, null);
    methodN.invoke(obj, null);
    
    try
    {
      methodO.invoke(obj, null);
      harness.fail(methodO + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    
    methodO.setAccessible(true);
    methodO.invoke(obj, null);

    harness.check (fieldP.getChar(obj) == 'p');
    harness.check (fieldQ.getChar(obj) == 'q');
    
    try
    {
      fieldR.getChar(obj);
      harness.fail(fieldR + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    
    fieldR.setAccessible(true);
    harness.check (fieldR.getChar(obj) == 'r');
    
    obj = new ReflectAccess();
    
    try 
    {
      methodS.invoke(obj, null);
      harness.fail(methodS + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    
    methodT.invoke(obj, null);
    methodU.invoke(obj, null);
    
    try 
    { 
      methodV.invoke(obj, null);
      harness.fail(methodV + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }

    harness.check (fieldW.getChar(obj) == 'w');
    harness.check (fieldX.getChar(obj) == 'x');

    try 
    { 
      fieldY.getChar(obj);
      harness.fail(fieldY + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
    
    try
    { 
      fieldZ.getChar(obj); 
      harness.fail(fieldZ + " should not be accessible");
    }
    catch (IllegalAccessException x)
    {
      // ok
    }
  }
  
  private void a() 
  { 
  }

  private static void b()
  {
  }
  
  protected void c()
  {
  }
  
  private char d = 'd';
  private static char e = 'e';
  protected char f = 'f';
}
