// Tags: JDK1.1 !libjava

package gnu.testlet.java.beans;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.beans.*;

public class IntrospectorTest implements Testlet {
  public void try (TestHarness harness, Class k1, Class k2, boolean force,
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

  public void try (TestHarness harness, Class k1, Class k2,
		   int dlen, int evlen, int gmlen)
    {
      try (harness, k1, k2, false, dlen, evlen, gmlen);
    }

  public void try (TestHarness harness, Class k,
		   int dlen, int evlen, int gmlen)
    {
      try (harness, k, null, false, dlen, evlen, gmlen);
    }

  public void test (TestHarness harness)
    {
      try (java.awt.Component.class, 6, 5, 128);
      try (java.util.BitSet.class, 2, 0, 17);
      try (java.lang.Object.class, 1, 0, 9);
      try (java.applet.Applet.class, 24, 6, 168);
      try (java.awt.Button.class, 8, 6, 134);
      try (java.applet.Applet.class, 8, 0, 22);
      try (java.applet.Applet.class, java.awt.Component.class, 18, 1, 65);
      try (java.applet.Applet.class, java.lang.Object.class, 24, 6, 160);
      try (java.applet.Applet.class, null, true, 24, 6, 168);
      try (java.applet.Applet.class, 24, 6, 168);
    }

  public IntrospectorTest () 
    {
    }
}
