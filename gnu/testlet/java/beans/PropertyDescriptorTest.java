// Tags: JDK1.1

package gnu.testlet.java.beans;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.beans.*;
import java.lang.reflect.*;

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

  // A property with type 'int' and an 'is' getter.  Sun's
  // implementation of PropertyDescriptor allows this case.
  public int isProp4() {
    return 0;
  }
  public void setProp4(int val) {}

  // A property with both 'is' and 'get' getters.  Sun's
  // implementation of PropertyDescriptor allows this case.
  public boolean isProp5() {
    return false;
  }
  public boolean getProp5() {
    return false;
  }
  public void setProp5(boolean val) {}

  // A property with both 'get' and 'is' getters.  Sun's
  // implementation of PropertyDescriptor allows this case.
  public boolean getProp6() {
    return false;
  }
  public boolean isProp6() {
    return false;
  }
  public void setProp6(boolean val) {}

  // A property with multiple setters with different types.  Sun's
  // implementation of PropertyDescriptor allows this case.
  
  public boolean getProp7() {
    return false;
  }
  public void setProp7(boolean val) {}
  public void setProp7(int val) {}


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
  
  // Erroneous property whose getter takes an argument
  public boolean getError11(int arg) {
    return false;
  }
  public void setError11(boolean val) {}

  // Erroneous property whose getter takes an argument
  public boolean isError12(int arg) {
    return false;
  }
  public void setError12(boolean val) {}

  // Erroneous property whose setter takes an extra argument
  public boolean isError13() {
    return false;
  }
  public void setError13(boolean val, int arg) {}

  // Erroneous property with an empty 'name'
  public Object get() {
    return null;
  }
  public void set(Object val) {}

  // Erroneous property with getter/setter with incorrect casing
  public Object getknotted() {
    return null;
  }
  public void setknotted(Object val) {}


  // Test the PropertyDescriptor(Class, String) constructor
  private void constructor1Tests (TestHarness harness)
  {
    String[] goodProperties = {
      "prop1", "prop2", "prop3", "prop4", "prop5", "prop6", "prop7"
    };
    for (int i = 0; i < goodProperties.length; i++) {
      harness.checkPoint("new PropertyDescriptor(" + goodProperties[i] + 
			 ") (should succeed)");
      PropertyDescriptor pd;
      try {
	pd = new PropertyDescriptor(goodProperties[i],
				    PropertyDescriptorTest.class);
	harness.check(true);
      }
      catch (IntrospectionException e) {
	harness.debug(e);
	harness.check(false);
	continue;
      }
      harness.check(pd.getReadMethod() != null);
      harness.check(pd.getWriteMethod() != null);
    }

    harness.checkPoint("string constructor - binding");
    String[] bindProperties = {
      "prop5", "prop6"
    };
    for (int i = 0; i < bindProperties.length; i++) {
      harness.checkPoint("new PropertyDescriptor(" + bindProperties[i] + 
			 ") (check bindings)");
      try {
	PropertyDescriptor pd = 
	  new PropertyDescriptor(bindProperties[i],
				 PropertyDescriptorTest.class);
        Class propType = pd.getPropertyType();
	Method readMethod = pd.getReadMethod();
	harness.check(readMethod != null);
	if (readMethod == null) {
	  continue;
	}
	harness.check(readMethod.getName().startsWith("is"));
	Class resType = readMethod.getReturnType();
	harness.check(resType.equals(propType));

	Method writeMethod = pd.getWriteMethod();
	harness.check(writeMethod != null);
	if (writeMethod == null) {
	  continue;
	}
	Class argType = writeMethod.getParameterTypes()[0];
	harness.check(argType.equals(propType));

      } 
      catch (IntrospectionException e) {
      }
    }
  
    String[] badProperties = {
      "", "foo", "knotted", "???",
      "error1", "error2", "error3", "error4", "error5",
      "error6", "error7", "error8", "error9", "error10", 
      "error11", "error12", "error13"
    };
    for (int i = 0; i < badProperties.length; i++) {
      harness.checkPoint("new PropertyDescriptor(" + badProperties[i] + 
			 ") (should fail)");
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

  private void constructor2Tests (TestHarness harness)
  {
    String[][] goodProperties = {
      {"prop1", "getProp1", "setProp1"},
      {"prop2", "getProp2", "setProp2"},
      {"prop3", "isProp3", "setProp3"},
      {"prop4", "isProp4", "setProp4"},
      {"prop5", "isProp5", "setProp5"},    
      {"prop5", "getProp5", "setProp5"},    
      {"prop6", "isProp6", "setProp6"},    
      {"prop6", "getProp6", "setProp6"},
      {"prop7", "getProp7", "setProp7"},
      {"prop7", "getProp7", null},
      {"prop7", null, "setProp7"},
      {"prop7", null, null},
    };

    for (int i = 0; i < goodProperties.length; i++) {
      harness.checkPoint("new PropertyDescriptor(" + 
			 goodProperties[i][0] + ", " +
			 goodProperties[i][1] + ", " +
			 goodProperties[i][2] + ") (should succeed)");
      boolean ok = true;
      try {
	new PropertyDescriptor(goodProperties[i][0],
			       PropertyDescriptorTest.class,
			       goodProperties[i][1], goodProperties[i][2]);
      } catch (IntrospectionException e) {
	harness.debug(e);
	ok = false;
      }
      harness.check(ok);
    }

    String[][] badProperties = {
      {"foo", "getFoo", "setFoo"},
      {"foo2", null, "setFoo2"},
      {"foo3", "getFoo3", null},
      {"bar", "", ""},
      {"error4", "getError4", "setError4"},
      {"error5", "isError5", "setError5"},
      {"error6", "getError6", "setError6"},
      {"error7", "isError7", "setError7"},
      {"error8", "getError8", "setError8"},
      {"error9", "getError9", "setError9"},
      {"error10", "isError10", "setError10"},
      {"error11", "getError11", "setError11"},
      {"error12", "isError12", "setError12"},
      {"error13", "isError13", "setError13"},
    };

    for (int i = 0; i < badProperties.length; i++) {
      harness.checkPoint("new PropertyDescriptor(" + 
			 badProperties[i][0] + ", " +
			 badProperties[i][1] + ", " +
			 badProperties[i][2] + ") (should fail)");
      boolean ok = false;
      try {
	new PropertyDescriptor(badProperties[i][0],
			       PropertyDescriptorTest.class,
			       badProperties[i][1], badProperties[i][2]);
      } catch (IntrospectionException e) {
	ok = true;
      }
      harness.check(ok);
    }
  }

  public void test (TestHarness harness)
  {
    constructor1Tests(harness);   
    constructor2Tests(harness);
  }
}
