// Tags: not-a-test

package gnu.testlet.java.util.ResourceBundle;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Enumeration;

public class Resource4_jp_JA_WIN_95 extends ResourceBundle 
{
  protected Object handleGetObject(String key) 
    throws MissingResourceException
    {
      if (key.compareTo ("class") == 0)
	return this.getClass().getName();
      else
	throw new MissingResourceException ("s", "className", "key");
    }

  public Enumeration getKeys()
    {
      return null;
    }
}

