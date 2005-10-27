// Tags: JDK1.2

// Copyright (C) 2005 Roman Kennke <kennke@aicas.com>

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
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

package gnu.testlet.javax.swing.text.PlainDocument;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Tests if insertString in PlainDocument works correctly.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class insertString implements Testlet
{

  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    testNewline(harness);
    testFilterNewline(harness);
  }

  /**
   * Tests inserting a string with a newline. This should lead to a document
   * structure with one BranchElement as root, and two LeafElement as children.
   *
   * @param harness the test harness to use
   */
  private void testNewline(TestHarness harness)
  {
    PlainDocument doc = new PlainDocument();
    try
      {
        doc.insertString(0, "Hello\nWorld", new SimpleAttributeSet());
      }
    catch (BadLocationException ex)
      {
        harness.fail("BadLocationException");
      }
    Element root = doc.getRootElements()[0];
    harness.check(root instanceof AbstractDocument.BranchElement);
    harness.check(root.getElementCount(), 2);
    Element el1 = root.getElement(0);
    harness.check(el1 instanceof AbstractDocument.LeafElement);
    harness.check(el1.getStartOffset(), 0);
    harness.check(el1.getEndOffset(), 6);
    Element el2 = root.getElement(1);
    harness.check(el2 instanceof AbstractDocument.LeafElement);
    harness.check(el2.getStartOffset(), 6);
    harness.check(el2.getEndOffset(), 12);
  }

  /**
   * Tests inserting a string with a newline with the filterNewLine property
   * set to Boolean.TRUE. This should lead to a document
   * structure with one BranchElement as root, and one LeafElement as child,
   * spanning the whole content.
   *
   * @param harness the test harness to use
   */
  private void testFilterNewline(TestHarness harness)
  {
    PlainDocument doc = new PlainDocument();
    doc.putProperty("filterNewlines", Boolean.TRUE);
    try
      {
        doc.insertString(0, "Hello\nWorld", new SimpleAttributeSet());
      }
    catch (BadLocationException ex)
      {
        harness.fail("BadLocationException");
      }
    Element root = doc.getRootElements()[0];
    harness.check(root instanceof AbstractDocument.BranchElement);
    harness.check(root.getElementCount(), 1);
    Element el1 = root.getElement(0);
    harness.check(el1 instanceof AbstractDocument.LeafElement);
    harness.check(el1.getStartOffset(), 0);
    harness.check(el1.getEndOffset(), 12);
  }
}
