package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class compareTo implements Testlet
{
  public void test (TestHarness harness)
    {
      char[] cstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };

      String a = new String();
      String b = new String(" abc\tABC 123\t");
      String d = new String(cstr);
      String e = new String(cstr, 3, 3);

      harness.check (d.compareTo(b.trim()), 0);
      harness.check (d.compareTo(a), 11);
      harness.check (d.compareTo(b), 65);
      harness.check (d.compareTo(e), 88);
      harness.check (d.toLowerCase().compareTo(d), 32);
      harness.check (d.compareTo(d.substring(0, d.length() - 2)), 2);

      harness.check (a.compareTo(d), -11);
      harness.check (b.compareTo(d), -65);
      harness.check (e.compareTo(d), -88);
      harness.check (d.compareTo(d.toLowerCase()), -32);
      harness.check (d.substring(0, d.length() - 2).compareTo(d), -2);

      harness.check (b.charAt(7), 'C');
    }

  public compareTo ()
    {
    }
}
