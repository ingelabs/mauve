// Tags: JDK1.0

package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class startsWith implements Testlet
{
  public void test (TestHarness harness)
    {
      char[] cstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };

      String b = new String(" abc\tABC 123\t");
      String d = new String(cstr);

      harness.check (! b.endsWith("123"));
      harness.check (d.endsWith("123"));
      harness.check (! b.startsWith("abc"));
      harness.check (d.startsWith("abc"));
      harness.check (b.startsWith("abc", 1));
      harness.check (! b.startsWith("abc", 2));
      harness.check (! b.startsWith("abc", -1));
      harness.check (! b.startsWith("abc", b.length()));
    }

  public startsWith ()
    {
    }
}
