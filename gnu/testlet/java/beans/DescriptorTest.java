// Tags: JDK1.1 !libjava

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
	new PropertyDescriptor("class",java.lang.Object.class);
      } catch(IntrospectionException e) {
	ok = false;
      }
      harness.check (ok);

      ok = true;
      try {
	new IndexedPropertyDescriptor("test",TestClass.class);
      } catch(IntrospectionException e) {
	ok = false;
      }
      harness.check (ok);

      ok = true;
      try {
	new EventSetDescriptor(java.awt.Button.class,"action",java.awt.event.ActionListener.class,"actionPerformed");
      } catch(IntrospectionException e) {
	ok = false;
      }
      harness.check (ok);

      ok = true;
      try {
	new MethodDescriptor(java.awt.Component.class.getMethod("getLocation",new Class[0]));
      } catch(NoSuchMethodException e) {
	ok = false;
      }
      harness.check (ok);
    }

  public DescriptorTest ()
    {
    }
}
