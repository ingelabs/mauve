// Tags: JDK1.2

// Copyright (C) 2005 Roman Kennke <kennke@aicas.com>
// Copyright (C) 2006 David Gilbert <david.gilbert@object-refinery.com>

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

package gnu.testlet.javax.swing.text.PlainDocument;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;
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
    testArguments(harness);
    testPositions(harness);
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
  
  public void testArguments(TestHarness harness)
  {
    harness.checkPoint("testArguments");
    PlainDocument d = new PlainDocument();
    
    // negative index
    boolean pass = false;
    try
    {
      d.insertString(-1, "XYZ", SimpleAttributeSet.EMPTY);
    }
    catch (BadLocationException e)
    {
      pass = true;
    }
    harness.check(pass);

    // index > length
    pass = false;
    try
    {
      d.insertString(2, "XYZ", SimpleAttributeSet.EMPTY);
    }
    catch (BadLocationException e)
    {
      pass = true;
    }
    harness.check(pass);
  
    // null string is OK (ignored)
    pass = true;
    try
    {
      d.insertString(0, null, SimpleAttributeSet.EMPTY);
    }
    catch (Exception e)
    {
      pass = false;
    }
    harness.check(pass);
    
    // null attribute set is OK
    pass = true;
    try
    {
      d.insertString(0, "ABC", null);
    }
    catch (Exception e)
    {
      pass = false;
    }
    harness.check(pass);

  }
  
  public void testPositions(TestHarness harness) 
  {
    harness.checkPoint("testPositions");
    PlainDocument d = new PlainDocument();
    try
      {
        d.insertString(0, "ABC", null);
        Position p0 = d.createPosition(0);
        harness.check(p0.getOffset(), 0);
        Position p1 = d.createPosition(1);
        harness.check(p1.getOffset(), 1);
        Position p2 = d.createPosition(3);
        harness.check(p2.getOffset(), 3);
        Position p3 = d.createPosition(4);
        harness.check(p3.getOffset(), 4);
         
        d.insertString(1, "XYZ", null);
        harness.check(p0.getOffset(), 0);
        harness.check(p1.getOffset(), 4);
        harness.check(p2.getOffset(), 6);
        harness.check(p3.getOffset(), 7);
         
        d.remove(2, 3);
        harness.check(p0.getOffset(), 0);
        harness.check(p1.getOffset(), 2);
        harness.check(p2.getOffset(), 3);
        harness.check(p3.getOffset(), 4);      
      }
    catch (BadLocationException e)
      { 
      }
  }

}
