package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.UnsupportedEncodingException;

public class decode implements Testlet
{
  public void test (TestHarness harness)
    {
      byte[] bstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };
      char[] cstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };

      String a = new String(bstr);
      String b = new String(bstr, 3, 3);
      String c = "";
      String d = "";

      try
	{
	  c = new String(bstr, "8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	}

      try
	{
	  d = new String(bstr, 3, 3, "8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	}

      harness.check (a, "abc	ABC 123");
      harness.check (b, "	AB");
      harness.check (c, "abc	ABC 123");
      harness.check (d, "	AB");

      boolean ok = false;
      try
	{
	  c = new String(bstr, "foobar8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  d = new String(bstr, 3, 3, "foobar8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      harness.check (String.copyValueOf(cstr), "abc	ABC 123");
      harness.check (String.copyValueOf(cstr, 3, 3), "	AB");
    }

  public decode ()
    {
    }
}
