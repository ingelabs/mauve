package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class getNumericValue implements Testlet
{
  public void test (TestHarness harness)
    {
      harness.check (Character.getNumericValue('0'), 0);
      harness.check (Character.getNumericValue('\u0be8'), 2);
      harness.check (Character.getNumericValue('\u246d'), 14);
      harness.check (Character.getNumericValue('\u2182'), 10000);
      harness.check (Character.getNumericValue('\u00bd'), -2);
      harness.check (Character.getNumericValue('A'), 10);
      harness.check (Character.getNumericValue('\u2155'), -2);
      harness.check (Character.getNumericValue('\u221e'), -1);
    }

  public getNumericValue ()
    {
    }
}
