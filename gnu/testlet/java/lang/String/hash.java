// Tags: JDK1.2

package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class hash implements Testlet
{
  public void test (TestHarness harness)
    {
      char[] cstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };

      String a = new String();
      String b = new String(" abc\tABC 123\t");
      String c = new String(new StringBuffer("abc\tABC 123"));

      /* These results are for JDK 1.2; the hashCode algorithm changed
	 from JDK 1.1.  */
      harness.check (a.hashCode(), 0);
      harness.check (b.hashCode(), -524164548);
      harness.check (c.hashCode(), -822419571);
    }

  public hash ()
    {
    }
}
