// Tags: JDK1.0

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class to implements Testlet
{
  public void test (TestHarness harness)
    {
      harness.check (Character.toUpperCase ('a'), 'A');
      harness.check (Character.toUpperCase ('A'), 'A');
      harness.check (Character.toUpperCase ('\uff5a'), '\uff3a');
      harness.check (Character.toUpperCase ('7'), '7');
      harness.check (Character.toUpperCase ('\u01f2'), '\u01f1');

      harness.check (Character.toLowerCase ('q'), 'q');
      harness.check (Character.toLowerCase ('Q'), 'q');
      harness.check (Character.toLowerCase ('\u2638'), '\u2638');
      harness.check (Character.toLowerCase ('\u01cb'), '\u01cc');
      harness.check (Character.toLowerCase ('\u01ca'), '\u01cc');
      harness.check (Character.toLowerCase ('\u00df'), '\u00df');
      harness.check (Character.toLowerCase ('\u2160'), '\u2170');

      harness.check (Character.toTitleCase ('a'), 'A');
      harness.check (Character.toTitleCase ('\u01f3'), '\u01f2');
      harness.check (Character.toTitleCase ('\u01f1'), '\u01f2');
    }
}
