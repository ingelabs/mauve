// Tags: JDK1.0

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class equals_Character implements Testlet
{
  public void test (TestHarness harness)
    {
      Character a = new Character ('\uffda');
      Character b = new Character ('Z');
      Character c = new Character ('\uffda');
      Boolean d = new Boolean ("true");

      harness.check (! a.equals(null));
      harness.check (! a.equals(b));
      harness.check (a.equals(c));
      harness.check (a.equals(a));
      harness.check (! b.equals(d));
    }

  public equals_Character ()
    {
    }
}
