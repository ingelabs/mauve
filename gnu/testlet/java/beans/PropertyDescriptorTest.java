// Tags: JDK1.1

package gnu.testlet.java.beans;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.beans.*;

public class PropertyDescriptorTest implements Testlet
{
  // Integer valued 'prop1'
  public int getProp1() {
    return 0;
  }
  public void setProp1(int val) {}

  // Boolean valued 'prop2' with a 'get' getter
  public boolean getProp2() {
    return false;
  }
  public void setProp2(boolean val) {}

  // Boolean valued 'prop3' with an 'is' getter
  public boolean isProp3() {
    return false;
  }
  public void setProp3(boolean val) {}

  // A property with type 'int' and an 'is' getter.  Apparently Sun's
  // implementation of PropertyDescriptor allows this case.
  public int isProp4() {
    return 0;
  }
  public void setProp4(int val) {}



  // Erroneous property with no setter
  public boolean getError1() {
    return false;
  }

  // Erroneous property with no setter
  public boolean isError2() {
    return false;
  }

  // Erroneous property with no getter
  public void setError3(boolean val) {
  }

  // Erroneous property with type 'void' getter
  public void getError4() {
  }
  public void setError4() {}

  // Erroneous property with type 'void' getter
  public void isError5() {
  }
  public void setError5(int val) {}

  // Erroneous property with a type mismatch between the getter and setter
  public int getError6() {
    return 0;
  }
  public void setError6(boolean val) {}

  // Erroneous property with a type mismatch between the getter and setter
  public boolean isError7() {
    return false;
  }
  public void setError7(int val) {}

  // Erroneous property with a type mismatch between the getter and setter
  public Object getError8() {
    return null;
  }
  public void setError8(String val) {}

  // Erroneous property with a type mismatch between the getter and setter
  public String getError9() {
    return null;
  }
  public void setError9(Object val) {}

  // Erroneous property with type 'void' setter
  public boolean isError10() {
    return false;
  }
  public void setError10() {}

  // Erroneous property with an empty 'name'
  public Object get() {
    return null;
  }
  public void set(Object val) {}



  public void test (TestHarness harness) {
    String[] goodProperties = {"prop1", "prop2", "prop3", "prop4"};
    
    for (int i = 0; i < goodProperties.length; i++) {
      boolean ok = true;
      try {
	new PropertyDescriptor(goodProperties[i],
			       PropertyDescriptorTest.class);
      } catch (IntrospectionException e) {
	harness.debug(e);
	ok = false;
      }
      harness.check(ok);
    }
    
    String[] badProperties = {
      "", "foo", "error1", "error2", "error3", "error4", "error5",
      "error6", "error7", "error8", "error9", "error10"
    };
    for (int i = 0; i < badProperties.length; i++) {
      boolean ok = false;
      try {
	new PropertyDescriptor(badProperties[i],
			       PropertyDescriptorTest.class);
      } catch (IntrospectionException e) {
	ok = true;
      }
      harness.check(ok);
    }
  }
}
