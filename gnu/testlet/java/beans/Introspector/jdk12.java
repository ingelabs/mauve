// Tags: JDK1.2 JAVA2

package gnu.testlet.java.beans.Introspector;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.beans.*;

public class jdk12 implements Testlet 
{
  public void test (TestHarness harness)
  {
    harness.checkPoint ("USE_ALL_BEANINFO");
    harness.debug ("USE_ALL_BEANINFO value: " + Introspector.USE_ALL_BEANINFO);
    
    harness.checkPoint ("IGNORE_IMMEDIATE_BEANINFO");
    harness.debug ("IGNORE_IMMEDIATE_BEANINFO value: " + Introspector.IGNORE_IMMEDIATE_BEANINFO);
    
    harness.checkPoint ("IGNORE_ALL_BEANINFO");
    harness.debug ("IGNORE_ALL_BEANINFO value: " + Introspector.IGNORE_ALL_BEANINFO);

  }
}
