// Tags: JDK1.2

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class classify12 implements Testlet
{
  public void test (TestHarness harness)
    {
      harness.check (! Character.isUpperCase('\u2102'));
    }

  public classify12 ()
    {
    }
}
