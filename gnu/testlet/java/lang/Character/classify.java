// Tags: JDK1.0

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class classify implements Testlet
{
  public void test (TestHarness harness)
    {
      harness.checkPoint ("isDefined");
      harness.check (Character.isDefined('9'));
      harness.check (! Character.isDefined('\uffef'));

      harness.checkPoint ("isIdentifierIgnorable");
      harness.check (! Character.isIdentifierIgnorable('Z'));
      harness.check (Character.isIdentifierIgnorable('\u202c'));
      harness.check (Character.isIdentifierIgnorable('\ufeff'));

      harness.checkPoint ("isISOControl");
      harness.check (! Character.isISOControl('Q'));
      harness.check (Character.isISOControl('\u0081'));
      harness.check (Character.isISOControl('\u0009'));

      harness.checkPoint ("isJavaIdentifierPart");
      harness.check (Character.isJavaIdentifierPart('\u0903'));

      harness.checkPoint ("isJavaIdentifierStart");
      harness.check (Character.isJavaIdentifierStart('\u20a0'));
      harness.check (Character.isJavaIdentifierStart('Z'));

      harness.checkPoint ("isLetter");
      harness.check (Character.isLetter('A'));
      harness.check (Character.isLetter('\u2102'));
      harness.check (Character.isLetter('\u01cb'));
      harness.check (Character.isLetter('\u02b2'));
      harness.check (Character.isLetter('\uffda'));
      harness.check (Character.isLetter('\u1fd3'));

      harness.checkPoint ("isLetterOrDigit");
      harness.check (Character.isLetterOrDigit('7'));
      harness.check (! Character.isLetterOrDigit('_'));

      harness.checkPoint ("isLowerCase");
      harness.check (Character.isLowerCase('\u03d0'));
      harness.check (Character.isLowerCase('z'));
      harness.check (! Character.isLowerCase('Q'));
      harness.check (! Character.isLowerCase('\u249f'));

      harness.checkPoint ("isUpperCase");
      harness.check (Character.isUpperCase('Q'));
      harness.check (! Character.isUpperCase('\u01c5'));

      harness.checkPoint ("isWhitespace");
      harness.check (Character.isWhitespace('\u0009'));
      harness.check (! Character.isWhitespace('\u00a0'));
      harness.check (Character.isWhitespace('\u2000'));
    }

  public classify ()
    {
    }
}
