/* ElementStructure7.java --
   Copyright (C) 2006 Red Hat
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

package gnu.testlet.javax.swing.text.DefaultStyledDocument.ElementBuffer;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.util.Vector;

import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;

public class ElementStructure7 extends DefaultStyledDocument implements Testlet
{
  /**
   * Starts the test run.
   * 
   * @param harness
   *          the test harness to use
   */
  public void test(TestHarness harness)
  {
    ElementStructure7 doc = new ElementStructure7();
    h2 = harness;
    try
      {
        doc.insertString(0, "aaaaaaaaa\nbbbbbbbbb", null);
        doc.insertString(5, "\nN", null);
      }
    catch (Exception ex)
      {
        // ex.printStackTrace();
        h2.debug(ex);
      }
    catch (AssertionError e)
      {
        // e.printStackTrace();
        harness.debug(e);
      }
    // printElements(doc.getDefaultRootElement(), 0);
  }
  
  static TestHarness h2;
  
  static DefaultDocumentEvent2 docEvent = null;

  static int numInserts = 0;

  static int numLeaves = 0;

  static int numBranches = 0;  

  // We override the constructor so we can explicitly set the type of the
  // buffer to be our ElementBuffer2, allowing us to test some internals.
  public ElementStructure7()
  {
    super();
    buffer = new ElementBuffer2(createDefaultRoot());
  }

  // Overriding this method allows us to check that the right number
  // of newLines was encountered and that the event has the proper
  // offset and length.
  protected void insertUpdate(DefaultDocumentEvent ev, AttributeSet attr)
  {
    int l = ev.getLength();
    int o = ev.getOffset();
    if (numInserts == 0)
      {
        h2.checkPoint("first doc event");
        h2.check(o == 0);
        h2.check(l == 19);
      }
    else if (numInserts == 1)
      {
        h2.checkPoint("second doc event");
        h2.check(o == 5);
        h2.check(l == 2);
      }
    else
      h2.fail ("too many calls to DefaultStyledDocument.insertUpdate");

    super.insertUpdate (ev, attr);
  }

  // Overriding this method allows us to check that the proper LeafElements
  // are being created.
  protected Element createLeafElement(Element parent, AttributeSet a, int p0,
                                      int p1)
  {
    numLeaves++;
    if (numLeaves == 1)
      {
        h2.checkPoint("create first leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 10);
        try
          {
            h2.check(parent.getStartOffset() == 0);
            h2.check(parent.getEndOffset() == 20);
          }
        catch (Exception e)
          {
            // I put 2 fails here so that the total number of tests will remain
            // the same whether we pass or fail these tests.
            h2.fail("parent Element should have children, but has none.");
            h2.fail("parent Element should have children, but has none.");
          }        
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 2)
      {
        h2.checkPoint("create second leaf element");
        h2.check(p0 == 19);
        h2.check(p1 == 20);
        h2.check(parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 3)
      {
        h2.checkPoint("create third leaf element");
        h2.check(p0 == 10);
        h2.check(p1 == 20);
        try
        {
          h2.check(parent.getStartOffset() == 19);
          h2.check(parent.getEndOffset() == 20);
        }
      catch (Exception e)
        {
          // I put 2 fails here so that the total number of tests will remain
          // the same whether we pass or fail these tests.
          h2.fail("parent Element should have children, but has none.");
          h2.fail("parent Element should have children, but has none.");
        }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 4)
      {
        h2.checkPoint("create fourth leaf element");
        h2.check(p0 == 0);
        h2.check(p1 == 6);
        try
        {
          h2.check(parent.getStartOffset() == 0);
          h2.check(parent.getEndOffset() == 12);
        }
      catch (Exception e)
        {
          // I put 2 fails here so that the total number of tests will remain
          // the same whether we pass or fail these tests.
          h2.fail("parent Element should have children, but has none.");
          h2.fail("parent Element should have children, but has none.");
        }
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 5)
      {
        h2.checkPoint("create fifth leaf element");
        h2.check(p0 == 7);
        h2.check(p1 == 12);
        h2.check (parent.getElementCount() == 0);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numLeaves == 6)
      {
        h2.checkPoint("create sixth leaf element");
        h2.check(p0 == 6);
        h2.check(p1 == 12);
        try
        {
          h2.check(parent.getStartOffset() == 7);
          h2.check(parent.getEndOffset() == 12);
        }
      catch (Exception e)
        {
          // I put 2 fails here so that the total number of tests will remain
          // the same whether we pass or fail these tests.
          h2.fail("parent Element should have children, but has none.");
          h2.fail("parent Element should have children, but has none.");
        }
        h2.check(a.getAttributeCount() == 0);
      }
    else
      h2.fail ("too many leaf elements created");
    return super.createLeafElement(parent, a, p0, p1);
  }

  // Overriding this method allows us to check that the proper BranchElements
  // are being created.
  protected Element createBranchElement(Element parent, AttributeSet a)
  {    
    numBranches ++;
    if (numBranches == 1)
      {
        h2.checkPoint("create first branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 20);
        h2.check(a.getAttributeCount() == 0);
      }
    else if (numBranches == 2)
      {
        h2.checkPoint("create second branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 22);
        h2.check(a.getAttributeCount() == 0);
      }
    else
      h2.fail ("too many branch elements created");
    return super.createBranchElement(parent, a);
  }

  // Prints some spaces.
  public static void pad(int pad)
  {
    for (int i = 0; i < pad; i++)
      System.out.print(" ");
  }

  // Displays the Element hierarchy starting with <code>start</code>.
  // This is just debugging code.
  public static void printElements(Element start, int pad)
  {
    pad(pad);
    if (pad == 0)
      System.out.println("ROOT ELEMENT (" + start.getStartOffset() + ", "
                         + start.getEndOffset() + ")");
    else if (start instanceof AbstractDocument.BranchElement)
      System.out.println("BranchElement (" + start.getStartOffset() + ", "
                         + start.getEndOffset() + ")");
    else
      {
        try
          {
            System.out.println("LeafElement ("
                               + start.getStartOffset()
                               + ", "
                               + start.getEndOffset()
                               + "): "
                               + start.getAttributes().getAttributeCount()
                               + ": "
                               + start.getDocument().
                               getText(
                                       start.getStartOffset(),
                                       start.getEndOffset()
                                       - start.getStartOffset()));
          }
        catch (BadLocationException ble)
          {
          }
      }
    for (int i = 0; i < start.getElementCount(); i++)
      printElements(start.getElement(i), pad + 3);
  }

  // A class to be the buffer of the styled document.
  // This allows us to check that some values are correct internally within
  // the ElementBuffer.
  public class ElementBuffer2 extends ElementBuffer
  {
    public ElementBuffer2(Element root)
    {
      super(root);
    }

    // This method allows us to check that the ElementSpecs generated by 
    // DefaultStyledDocument.insertUpdate are correct.
    protected void insertUpdate(ElementSpec[] data)
    {
      numInserts++;
      if (numInserts == 1)
        {
          h2.checkPoint("ElementBuffer insertUpdate: first insertion");
          h2.check (data.length == 4);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 10);

          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);

          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0);
          
          h2.check(data[3].getType() == ElementSpec.ContentType);
          h2.check(data[3].getDirection() == ElementSpec.JoinNextDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 9);
        }
      else if (numInserts == 2)
        {
          h2.checkPoint("ElementBuffer insertUpdate: second insertion");
          h2.check (data.length == 4);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 1);
          
          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);
          
          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0);
          
          h2.check(data[3].getType() == ElementSpec.ContentType);
          h2.check(data[3].getDirection() == ElementSpec.JoinNextDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 1);

        }
      else
        h2.fail("too many ElementSpecs created");
      h2.check(docEvent.getChange(getDefaultRootElement()) == null);
      super.insertUpdate(data);
      h2.check(docEvent.getChange(getDefaultRootElement()) == null);
    }
    
    public void insert(int offset, int length, ElementSpec[] data,
                       DefaultDocumentEvent ev)
    {
      docEvent = new DefaultDocumentEvent2(ev.getOffset(), ev.getLength(),
                                           ev.getType());
      super.insert(offset, length, data, docEvent);
      Vector edits = docEvent.getEdits();
      h2.check(data.length == 4);
      h2.check(data[0].getType() == ElementSpec.ContentType);
      h2.check(data[0].getDirection() == ElementSpec.JoinPreviousDirection);
      h2.check(data[1].getType() == ElementSpec.EndTagType);
      h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
      h2.check(data[2].getType() == ElementSpec.StartTagType);
      h2.check(data[2].getDirection() == ElementSpec.JoinFractureDirection);
      h2.check(data[3].getType() == ElementSpec.ContentType);
      h2.check(data[3].getDirection() == ElementSpec.JoinNextDirection);
      h2.check(edits.size(), 3);
      Object zero = edits.get(0);
      h2.check(zero instanceof AbstractDocument.ElementEdit);
      h2.check(((AbstractDocument.ElementEdit) zero).canUndo());
      Object one = edits.get(1);
      h2.check(one instanceof AbstractDocument.ElementEdit);
      h2.check(((AbstractDocument.ElementEdit) one).canUndo());
      Object two = edits.get(2);
      h2.check(two instanceof AbstractDocument.ElementEdit);
      h2.check(((AbstractDocument.ElementEdit) two).canUndo());
    }
  }
  public class DefaultDocumentEvent2 extends DefaultDocumentEvent
  {

    public DefaultDocumentEvent2(int offset, int length,
                                 DocumentEvent.EventType type)
    {
      super(offset, length, type);
    }

    public Vector getEdits()
    {
      return edits;
    }
  }
}
