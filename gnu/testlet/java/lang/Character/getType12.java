// Tags: JDK1.2

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class getType12 implements Testlet
{
  public static void p (TestHarness harness, char c, String expected)
    {
      String s;
      switch (Character.getType (c))
	{
	case Character.SPACE_SEPARATOR:
	  s = "space_separator";
	  break;
	case Character.LINE_SEPARATOR:
	  s = "line_separator";
	  break;
	case Character.PARAGRAPH_SEPARATOR:
	  s = "paragraph_separator";
	  break;
	case Character.UPPERCASE_LETTER:
	  s = "uppercase_letter";
	  break;
	case Character.LOWERCASE_LETTER:
	  s = "lowercase_letter";
	  break;
	case Character.TITLECASE_LETTER:
	  s = "titlecase_letter";
	  break;
	case Character.MODIFIER_LETTER:
	  s = "modifier_letter";
	  break;
	case Character.OTHER_LETTER:
	  s = "other_letter";
	  break;
	case Character.DECIMAL_DIGIT_NUMBER:
	  s = "decimal_digit_number";
	  break;
	case Character.LETTER_NUMBER:
	  s = "letter_number";
	  break;
	case Character.OTHER_NUMBER:
	  s = "other_number";
	  break;
	case Character.NON_SPACING_MARK:
	  s = "non_spacing_mark";
	  break;
	case Character.ENCLOSING_MARK:
	  s = "enclosing_mark";
	  break;
	case Character.COMBINING_SPACING_MARK:
	  s = "combining_spacing_mark";
	  break;
	case Character.DASH_PUNCTUATION:
	  s = "dash_punctuation";
	  break;
	case Character.START_PUNCTUATION:
	  s = "start_punctuation";
	  break;
	case Character.END_PUNCTUATION:
	  s = "end_punctuation";
	  break;
	case Character.CONNECTOR_PUNCTUATION:
	  s = "connector_punctuation";
	  break;
	case Character.OTHER_PUNCTUATION:
	  s = "other_punctuation";
	  break;
	case Character.MATH_SYMBOL:
	  s = "math_symbol";
	  break;
	case Character.CURRENCY_SYMBOL:
	  s = "currency_symbol";
	  break;
	case Character.MODIFIER_SYMBOL:
	  s = "modifier_symbol";
	  break;
	case Character.OTHER_SYMBOL:
	  s = "other_symbol";
	  break;
	case Character.CONTROL:
	  s = "control";
	  break;
	case Character.FORMAT:
	  s = "format";
	  break;
	case Character.UNASSIGNED:
	  s = "unassigned";
	  break;
	case Character.PRIVATE_USE:
	  s = "private_use";
	  break;
	case Character.SURROGATE:
	  s = "surrogate";
	  break;
	default:
	  s = "???";
	  break;
	}

      harness.check (s, expected);
    }

  public void test (TestHarness harness)
    {
      p (harness, '\u20ac', "currency_symbol");
    }
}
