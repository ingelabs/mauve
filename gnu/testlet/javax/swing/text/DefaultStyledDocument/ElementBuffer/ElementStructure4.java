// Tags: JDK1.2

// Copyright (C) 2005 Red Hat.

// This file is part of Mauve.

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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

package gnu.testlet.javax.swing.text.DefaultStyledDocument.ElementBuffer;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class ElementStructure4 implements Testlet
{
  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    try
      {
        DefaultStyledDocument doc = new DefaultStyledDocument();
        SimpleAttributeSet atts = new SimpleAttributeSet();
        Element root = doc.getDefaultRootElement();

        // Add strike trough text.
        atts.addAttribute(StyleConstants.StrikeThrough, Boolean.TRUE);
        doc.insertString(0, "Strike through text.\n", atts);
        atts.removeAttributes(atts);
        harness.check(root.getElementCount() == 2);
        harness.check(root.getElement(0).getStartOffset() == 0);
        harness.check(root.getElement(0).getEndOffset() == 21);
        harness.check(root.getElement(1).getStartOffset() == 21);
        harness.check(root.getElement(1).getEndOffset() == 22);


        // Add plain text in front.
        doc.insertString(0, "a", null);
        harness.check(root.getElement(0).getElementCount() == 2);
        harness.check(root.getElement(1).getElementCount() == 1);
        doc.insertString(1, "b", null);
        harness.check(root.getElement(0).getElementCount() == 2);
        harness.check(root.getElement(1).getElementCount() == 1);


        Element first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 2);

        Element second = root.getElement(0).getElement(1);
        harness.check(second.getStartOffset() == 2);
        harness.check(second.getEndOffset() == 23);
      }
    catch (Throwable t)
      {
        harness.fail(t.toString());
      }
  }
}
