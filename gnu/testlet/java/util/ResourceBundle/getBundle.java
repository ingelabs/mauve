// Tags: JDK1.1
// Uses: Resource1 Resource2_en Resource3_bo Resource4_en Resource4_en_CA Resource4 Resource4_jp Resource4_jp_JA Resource4_jp_JA_WIN Resource4_jp_JA_WIN_95 Resource5_en Resource5_en_CA Resource5 Resource5_jp Resource5_jp_JA Resource5_jp_JA_WIN Resource6_en Resource6_en_CA Resource6 Resource6_jp Resource6_jp_JA Resource7_en Resource7_en_CA Resource7 Resource7_jp Resource8_en Resource8_en_CA Resource8 Resource9_en Resource9_en_CA Resource10_en

package gnu.testlet.java.util.ResourceBundle;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

public class getBundle implements Testlet
{
  static private final String MISSING = "**missing**";

  // Load the resource bundle BUNDLE, and return it's class name.
  // Return MISSING if it cannot be loaded.
  private String loadCheck (String bundle)
    {
      ResourceBundle rb;
      try 
	{
	  rb = ResourceBundle.getBundle (bundle);
	}
      catch (MissingResourceException ex)
	{
	  return MISSING;
	}
      return rb.getString ("class");
    }

  // Load the resource bundle BUNDLE with locale LOCALE , and return
  // it's class name.  Return MISSING if it cannot be loaded.
  private String loadCheck (String bundle, Locale locale)
    {
      ResourceBundle rb;
      try 
	{
	  rb = ResourceBundle.getBundle (bundle, locale);
	}
      catch (MissingResourceException ex)
	{
	  return MISSING;
	}
      return rb.getString ("class");
    }

  // This is a simple helper function to save typing below.
  private String c (String bundle)
    {
      return ("gnu.testlet.java.util.ResourceBundle." + bundle);
    }

  public void test (TestHarness harness)
    {
      // Save the default locale, and restore it after this test.
      Locale defaultLocale = Locale.getDefault ();

      // Try loading a few resource bundles with a default locale of
      // Canada.
      Locale.setDefault (Locale.CANADA);
      harness.checkPoint ("with locale of Canada");
      harness.check (loadCheck (c ("Resource1")), c ("Resource1"));
      harness.check (loadCheck (c ("Resource1"), Locale.CANADA), 
		     c ("Resource1"));
      harness.check (loadCheck (c ("Resource1"), Locale.JAPAN), 
		     c ("Resource1"));
      harness.check (loadCheck (c ("Resource2"), Locale.CANADA), 
		     c ("Resource2_en"));
      harness.check (loadCheck (c ("Resource2"), Locale.JAPAN), 
		     c ("Resource2_en"));
      harness.check (loadCheck (c ("Resource3"), Locale.JAPAN),
		     MISSING);

      // Try loading a few resource bundles with a default locale of
      // France.
      Locale.setDefault (Locale.FRANCE);
      harness.checkPoint ("with locale of France");
      harness.check (loadCheck (c ("Resource1")), c ("Resource1"));
      harness.check (loadCheck (c ("Resource1"), Locale.CANADA), 
		     c ("Resource1"));
      harness.check (loadCheck (c ("Resource1"), Locale.JAPAN), 
		     c ("Resource1"));
      harness.check (loadCheck (c ("Resource2"), Locale.CANADA), 
		     c ("Resource2_en"));
      harness.check (loadCheck (c ("Resource2"), Locale.JAPAN), 
		     MISSING);
      harness.check (loadCheck (c ("Resource3"), Locale.JAPAN),
		     MISSING);

      // Set the locale back to Canada, and make sure resources are loaded
      // back in the proper order.
      Locale.setDefault (Locale.CANADA);

      // Create a test Locale
      Locale testLocale = new Locale("jp", "JA", "WIN_95");

      // These are based on a sample from "The Java Class Libraries,
      // Second Edition", page 1437
      harness.checkPoint ("book sample");
      harness.check (loadCheck (c ("Resource4"), testLocale), 
		     c ("Resource4_jp_JA_WIN_95"));
      harness.check (loadCheck (c ("Resource5"), testLocale), 
		     c ("Resource5_jp_JA_WIN"));
      harness.check (loadCheck (c ("Resource6"), testLocale), 
		     c ("Resource6_jp_JA"));
      harness.check (loadCheck (c ("Resource7"), testLocale), 
		     c ("Resource7_jp"));
      harness.check (loadCheck (c ("Resource8"), testLocale), 
		     c ("Resource8"));
      harness.check (loadCheck (c ("Resource9"), testLocale), 
		     c ("Resource9_en_CA"));
      harness.check (loadCheck (c ("Resource10"), testLocale), 
		     c ("Resource10_en"));

      // Restore the default locale.
      Locale.setDefault (defaultLocale);
    }
}

