// Tags: JDK1.0

package gnu.testlet.java.lang.Integer;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class new_Integer implements Testlet
{
  public void test (TestHarness harness)
    {
      Integer a = new Integer(0);
      Integer b = new Integer(1);
      Integer c = new Integer(-1);
      Integer d = new Integer(Integer.MAX_VALUE);
      Integer e = new Integer(Integer.MIN_VALUE);
      Integer f = new Integer("0");
      Integer g = new Integer("1");
      Integer h = new Integer("-1");
      Integer i = new Integer("2147483647");
      Integer j = new Integer("-2147483648");
      Integer k = new Integer("-0");
      Integer l = new Integer("012345");
      Integer m = new Integer("0012345");

      harness.check (a + " " + b + " " + c + " " + d + " " + e,
		     "0 1 -1 2147483647 -2147483648");
      harness.check (f + " " + g + " " + h + " " + i + " " + j,
		     "0 1 -1 2147483647 -2147483648");
      harness.check (k + " " + l + " " + m,
		     "0 12345 12345");
      harness.check (Integer.MAX_VALUE, 2147483647);
      harness.check (Integer.MAX_VALUE + 1, -2147483648);
      harness.check (Integer.MAX_VALUE + 2, -2147483647);
      harness.check (Integer.MIN_VALUE, -2147483648);
      harness.check (Integer.MIN_VALUE - 1, 2147483647);
      harness.check (Integer.MIN_VALUE - 2, 2147483646);
      harness.check (c.toString(), "-1");
      harness.check (e.toString(), "-2147483648");
      harness.check (Integer.toString(-1, 2),
		     "-1");
      harness.check (Integer.toString(Integer.MIN_VALUE + 1, 2),
		     "-1111111111111111111111111111111");
      harness.check (Integer.toString(Integer.MIN_VALUE, 2),
		     "-10000000000000000000000000000000");
      harness.check (Integer.toString(Integer.MAX_VALUE, 2),
		     "1111111111111111111111111111111");
      harness.check (Integer.toString(-1, 16),
		     "-1");
      harness.check (Integer.toString(Integer.MIN_VALUE + 1, 16),
		     "-7fffffff");
      harness.check (Integer.toString(Integer.MIN_VALUE, 16),
		     "-80000000");
      harness.check (Integer.toString(Integer.MAX_VALUE, 16),
		     "7fffffff");
      harness.check (Integer.toString(-1, 36),
		     "-1");
      harness.check (Integer.toString(Integer.MIN_VALUE + 1, 36),
		     "-zik0zj");
      harness.check (Integer.toString(Integer.MIN_VALUE, 36),
		     "-zik0zk");
      harness.check (Integer.toString(Integer.MAX_VALUE, 36),
		     "zik0zj");

      Integer bad = null;
      try
	{
	  bad = new Integer("2147483648");
	}
      catch (NumberFormatException ex)
	{
	}
      harness.check (bad, null);

      bad = null;
      try
	{
	  bad = new Integer("-2147483649");
	}
      catch (NumberFormatException ex)
	{
	}
      harness.check (bad, null);

      bad = null;
      try
	{
	  bad = new Integer("12345a");
	}
      catch (NumberFormatException ex)
	{
	}
      harness.check (bad, null);

      bad = null;
      try
	{
	  bad = new Integer("-");
	}
      catch (NumberFormatException ex)
	{
	}
      harness.check (bad, null);

      bad = null;
      try
	{
	  bad = new Integer("0x1e");
	}
      catch (NumberFormatException ex)
	{
	}
      harness.check (bad, null);

      harness.check (a.hashCode(), 0);
      harness.check (b.hashCode(), 1);
      harness.check (c.hashCode(), -1);
      harness.check (d.hashCode(), 2147483647);
      harness.check (e.hashCode(), -2147483648);

    // harness.check (a.compareTo(a));
    // harness.check (b.compareTo(c));
    // harness.check (c.compareTo(b));
    // harness.check (d.compareTo(e));
    // harness.check (e.compareTo(d));

      harness.check (Integer.decode("123456789"), new Integer (123456789));
      harness.check (Integer.decode("01234567"), new Integer (342391));
      harness.check (Integer.decode("0x1234FF"), new Integer (1193215));
      harness.check (Integer.decode("#1234FF"), new Integer (1193215));
      harness.check (Integer.decode("-123456789"), new Integer (-123456789));
      harness.check (Integer.decode("-01234567"), new Integer (-1234567));
      harness.check (Integer.decode("-0"), new Integer (0));
      harness.check (Integer.decode("0"), new Integer (0));
      harness.check (Integer.decode(Integer.toString(Integer.MIN_VALUE)),
		     new Integer (-2147483648));

      boolean ok = false;
      try
	{
	  Integer.decode("");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.decode(" ");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.decode("0X1234");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.decode("0xF0000000");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.decode("0x");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.decode("-");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.decode("#");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.decode("-0x1234FF");
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      harness.check (Integer.toBinaryString(0),
		     "0");
      harness.check (Integer.toBinaryString(1),
		     "1");
      harness.check (Integer.toBinaryString(-1),
		     "11111111111111111111111111111111");
      harness.check (Integer.toBinaryString(Integer.MIN_VALUE),
		     "10000000000000000000000000000000");
      harness.check (Integer.toBinaryString(Integer.MAX_VALUE),
		     "1111111111111111111111111111111");
      harness.check (Integer.toBinaryString(Integer.MIN_VALUE - 1),
		     "1111111111111111111111111111111");
      harness.check (Integer.toBinaryString(Integer.MAX_VALUE + 1),
		     "10000000000000000000000000000000");

      harness.check (Integer.toOctalString(0),
		     "0");
      harness.check (Integer.toOctalString(1),
		     "1");
      harness.check (Integer.toOctalString(-1),
		     "37777777777");
      harness.check (Integer.toOctalString(Integer.MIN_VALUE),
		     "20000000000");
      harness.check (Integer.toOctalString(Integer.MAX_VALUE),
		     "17777777777");
      harness.check (Integer.toOctalString(Integer.MIN_VALUE - 1),
		     "17777777777");
      harness.check (Integer.toOctalString(Integer.MAX_VALUE + 1),
		     "20000000000");

      harness.check (Integer.toHexString(0),
		     "0");
      harness.check (Integer.toHexString(1),
		     "1");
      harness.check (Integer.toHexString(-1),
		     "ffffffff");
      harness.check (Integer.toHexString(Integer.MIN_VALUE),
		     "80000000");
      harness.check (Integer.toHexString(Integer.MAX_VALUE),
		     "7fffffff");
      harness.check (Integer.toHexString(Integer.MIN_VALUE - 1),
		     "7fffffff");
      harness.check (Integer.toHexString(Integer.MAX_VALUE + 1),
		     "80000000");

      harness.check (Integer.parseInt("0012345", 8),
		     5349);
      harness.check (Integer.parseInt("xyz", 36),
		     44027);
      harness.check (Integer.parseInt("12345", 6),
		     1865);
      harness.check (Integer.parseInt("abcdef", 16),
		     11259375);
      harness.check (Integer.parseInt("-0012345", 8),
		     -5349);
      harness.check (Integer.parseInt("-xyz", 36),
		     -44027);
      harness.check (Integer.parseInt("-12345", 6),
		     -1865);
      harness.check (Integer.parseInt("-abcdef", 16),
		     -11259375);

      ok = false;
      try
	{
	  Integer.parseInt("0", 1);
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  Integer.parseInt("0", 37);
	}
      catch (NumberFormatException ex)
	{
	  ok = true;
	}
      harness.check (ok);
    }

  public new_Integer ()
    {
    }
}
