// Tags: JDK1.1

package gnu.testlet.java.lang.reflect.Modifier;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.lang.reflect.Modifier;

public class toString implements Testlet
{
  public void test (TestHarness harness)
    {
      harness.check (Modifier.toString (Modifier.PUBLIC),
		     "public");
      harness.check (Modifier.toString (Modifier.PRIVATE),
		     "private");
      harness.check (Modifier.toString (Modifier.PROTECTED),
		     "protected");
      harness.check (Modifier.toString (Modifier.STATIC),
		     "static");
      harness.check (Modifier.toString (Modifier.FINAL),
		     "final");
      harness.check (Modifier.toString (Modifier.SYNCHRONIZED),
		     "synchronized");
      harness.check (Modifier.toString (Modifier.VOLATILE),
		     "volatile");
      harness.check (Modifier.toString (Modifier.TRANSIENT),
		     "transient");
      harness.check (Modifier.toString (Modifier.NATIVE),
		     "native");
      harness.check (Modifier.toString (Modifier.INTERFACE),
		     "interface");
      harness.check (Modifier.toString (Modifier.ABSTRACT),
		     "abstract");

      // Spot-check a few combinations.  Add more as desired.
      harness.check (Modifier.toString (Modifier.PRIVATE | Modifier.INTERFACE),
		     "private interface");
      harness.check (Modifier.toString (Modifier.ABSTRACT | Modifier.NATIVE),
		     "abstract native");
    }
}
