// Tags: JDK1.0

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class forDigit implements Testlet
{
  public void test (TestHarness harness)
    {
      harness.check (Character.forDigit(-1, 5), 0);
      harness.check (Character.forDigit(7, 5), 0);
      harness.check (Character.forDigit(0, 1), 0);
      harness.check (Character.forDigit(5, 37), 0);
      harness.check (Character.forDigit(8, 10), '8');
      harness.check (Character.forDigit(12, 16), 'c');
      harness.check (Character.forDigit(34,36), 'y');
    }

  public forDigit ()
    {
    }
}
