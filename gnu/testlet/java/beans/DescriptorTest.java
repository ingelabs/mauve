// Tags: JDK1.1

package gnu.testlet.java.beans;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.beans.*;

public class DescriptorTest implements Testlet
{
  public void test (TestHarness harness)
    {
      boolean ok;

      ok = true;
      try {
	new PropertyDescriptor("name", java.awt.Component.class);
      } catch (IntrospectionException e) {
	harness.debug(e);
	ok = false;
      }
      harness.check(ok);

      ok = true;
      try {
	new PropertyDescriptor("visible", java.awt.Component.class);
      } catch (IntrospectionException e) {
	harness.debug(e);
	ok = false;
      }
      harness.check(ok);

      ok = false;
      try {
	// This should fail because there is no 'setClass' method
	// on 'java.lang.Object'
	new PropertyDescriptor("class", java.lang.Object.class);
      } catch (IntrospectionException e) {
	ok = true;
      }
      harness.check(ok);

      ok = true;
      try {
	new EventSetDescriptor(java.awt.Button.class, "action", 
			       java.awt.event.ActionListener.class,
			       "actionPerformed");
      } catch (IntrospectionException e) {
	harness.debug(e);
	ok = false;
      }
      harness.check(ok);

      ok = true;
      try {
	new MethodDescriptor(java.awt.Component.class.getMethod("getLocation",
								new Class[0]));
      } catch (NoSuchMethodException e) {
	harness.debug(e);
	ok = false;
      }
      harness.check(ok);
    }
}
