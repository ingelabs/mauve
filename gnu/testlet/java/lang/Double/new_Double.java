// Tags: JDK1.0

package gnu.testlet.java.lang.Double;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class new_Double implements Testlet
{
  public void test (TestHarness harness)
    {
      // Some broken implementations convert "7.79625120912E289" to
      // the value 7.796251209119999E289.
      harness.check (new Double("7.79625120912E289").toString (),
		     "7.79625120912E289");

      // A few simple test cases.
      harness.check (new Double(22.0/7.0).toString (), "3.142857142857143");
      harness.check (new Double(Math.PI).toString (), "3.141592653589793");
      harness.check (Double.valueOf (new Double(Math.PI).toString ()),
		     new Double(Math.PI));
      harness.check (Double.valueOf (new Double(-Math.PI).toString ()),
		     new Double(-Math.PI));
      harness.check (new Double(Double.MAX_VALUE).toString (), 
		     "1.7976931348623157E308");
      harness.check (new Double(-Double.MAX_VALUE).toString (), 
		     "-1.7976931348623157E308");
      harness.check (new Double(Double.POSITIVE_INFINITY).toString (), 
		     "Infinity");
      harness.check (new Double(-Double.POSITIVE_INFINITY).toString (), 
		     "-Infinity");
      harness.check (new Double(Double.NaN).toString (), 
		     "NaN");
    }
}

