package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class new_String implements Testlet
{
  public void test (TestHarness harness)
    {
      char[] cstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };

      String a = new String();
      String b = new String(" abc\tABC 123\t");
      String c = new String(new StringBuffer("abc\tABC 123"));
      String d = new String(cstr);
      String e = new String(cstr, 3, 3);

      harness.check (a, "");
      harness.check (b, " abc	ABC 123	");
      harness.check (c, "abc	ABC 123");
      harness.check (d, "abc	ABC 123");
      harness.check (e, "	AB");
    }

  public new_String ()
    {
    }
}
