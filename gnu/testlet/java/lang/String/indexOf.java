// Tags: JDK1.0

package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class indexOf implements Testlet
{
  public void test (TestHarness harness)
    {
      char[] cstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };

      String b = new String(" abc\tABC 123\t");
      String d = new String(cstr);

      harness.check (b.indexOf(' '), 0);
      harness.check (b.indexOf(' ', 1), 8);
      harness.check (b.indexOf(' ', 10), -1);
      harness.check (b.indexOf(' ', -1), 0);
      harness.check (b.indexOf(' ', b.length()), -1);
      harness.check (b.indexOf("abc"), 1);
      harness.check (b.indexOf("abc", 1), 1);
      harness.check (b.indexOf("abc", 10), -1);

      harness.check (b.lastIndexOf(' '), 8);
      harness.check (b.lastIndexOf(' ', 1), 0);
      harness.check (b.lastIndexOf(' ', 10), 8);
      harness.check (b.lastIndexOf(' ', -1), -1);
      harness.check (b.lastIndexOf(' ', b.length()), 8);
      harness.check (b.lastIndexOf("abc"), 1);
      harness.check (b.lastIndexOf("abc", 1), 1);
      harness.check (b.lastIndexOf("abc", 10), 1);
    }
}
