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

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.DefaultStyledDocument;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class ElementStructure1 implements Testlet
{
  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    DefaultStyledDocument doc = new DefaultStyledDocument();
    Element root = doc.getDefaultRootElement();
    try
      {
        // Add some regular text
        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check (root.getElement(0).getElement(0).getElementCount() == 0);
        doc.insertString(0, "the quick brown fox", null);

        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check (root.getElement(0).getElement(0).getElementCount() == 0);
        doc.insertString(6, "MIDDLE", null);

        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check (root.getElement(0).getElement(0).getElementCount() == 0);
        doc.insertString(0, "START", null);

        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check (root.getElement(0).getElement(0).getElementCount() == 0);
        doc.insertString(30, "END", null);        
      }
    catch (BadLocationException ex)
      {
        harness.debug(ex);
      }        

  }
}
