// Tags: JDK1.1

package gnu.testlet.java.beans;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.beans.*;

public class IntrospectorTest implements Testlet {
  public void tryone (TestHarness harness, Class k1, Class k2, boolean force,
		      int dlen, int evlen, int gmlen)
    {
      try
	{
	  BeanInfo b;
	  if (! force && k2 == null)
	    b = Introspector.getBeanInfo (k1);
	  else
	    b = Introspector.getBeanInfo (k1, k2);
	  harness.check (b.getPropertyDescriptors().length == dlen
			 && b.getEventSetDescriptors().length == evlen
			 && b.getMethodDescriptors().length == gmlen);
	}
      catch (IntrospectionException e)
	{
	  harness.check (false);
	}
    }

  public void tryone (TestHarness harness, Class k1, Class k2,
		      int dlen, int evlen, int gmlen)
    {
      tryone (harness, k1, k2, false, dlen, evlen, gmlen);
    }

  public void tryone (TestHarness harness, Class k,
		      int dlen, int evlen, int gmlen)
    {
      tryone (harness, k, null, false, dlen, evlen, gmlen);
    }

  public void test (TestHarness harness)
    {
      tryone (harness, java.awt.Component.class, 6, 5, 128);
      tryone (harness, java.util.BitSet.class, 2, 0, 17);
      tryone (harness, java.lang.Object.class, 1, 0, 9);
      tryone (harness, java.applet.Applet.class, 24, 6, 168);
      tryone (harness, java.awt.Button.class, 8, 6, 134);
      tryone (harness, java.applet.Applet.class, 8, 0, 22);
      tryone (harness, java.applet.Applet.class, java.awt.Component.class,
	      18, 1, 65);
      tryone (harness, java.applet.Applet.class, java.lang.Object.class,
	      24, 6, 160);
      tryone (harness, java.applet.Applet.class, null, true, 24, 6, 168);
      tryone (harness, java.applet.Applet.class, 24, 6, 168);
    }

  public IntrospectorTest () 
    {
    }
}
