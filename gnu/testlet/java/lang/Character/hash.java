// Tags: JDK1.0

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class hash implements Testlet
{
  public void test (TestHarness harness)
    {
      Character a = new Character ('\uffda');
      Character b = new Character ('Z');

      harness.check (a.hashCode(), 65498);
      harness.check (b.hashCode(), 90);
    }

  public hash ()
    {
    }
}
