// Tags: JDK1.2

// Copyright (C) 2005 Audrius Meskauskas, AudriusA@Bluewin.ch

// // This file is part of Mauve.

// Mauve is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.

// Mauve is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with Mauve; see the file COPYING.  If not, write to
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.javax.swing.text.html.parser.ParserDelegator;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

/**
 * Checking the token positons that must be available for the callback.
 * @author Audrius Meskauskas (AudriusA@Bluewin.ch)
 */
public class tokenLocations
  extends parsingTester implements Testlet
{
  public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes,
                              int position
                             )
  {
    if (tag.toString().equals("#pcdata"))
      return;
    out.append("<" + tag + "'" + position);
    dumpAttributes(attributes);
    out.append("/>");
  }

  public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes,
                             int position
                            )
  {
    out.append("<" + tag + "'" + position);
    dumpAttributes(attributes);
    out.append('>');
  }

  public void testHTMLParsing()
  {
    hideImplied = true;

         // 0123456789012345678901234567890
    verify("<table><tr><td>a<td>b<td>c</tr>",
           "<html'0><head'0></head><body'0><table'0>" +
           "<tr'7><td'11>'a'</td><td'16>'b'</td><td'21>'c'</td>" +
           "</tr></table></body></html>", "Token locations"
          );

         // 012345678901234567
    verify("<p>b<p>c<p>d",
           "<html'0><head'0></head><body'0>" +
           "<p'0>'b'</p><p'4>'c'</p><p'8>'d'</p></body></html>",
           "Token locations"
          );
  }

  public void test(TestHarness a_harness)
  {
      super.test(a_harness);
  }

}
