// Tags: JDK1.2

// Copyright (C) 2006 Red Hat.

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

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;

public class ElementStructure1 extends DefaultStyledDocument implements Testlet
{
	/**
	 * Starts the test run.
	 * 
	 * @param harness
	 *            the test harness to use
	 */
  public void test(TestHarness harness)
  {
    h2 = harness;
    ElementStructure1 doc = new ElementStructure1();
    Element root = doc.getDefaultRootElement();
    try
      {
        // In this test no new LeafElements or BranchElements should be 
        // created.  We're able to add everything to the original Element
        // that was created when the document was constructed.
        harness.checkPoint ("initial setup");
        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check 
          (root.getElement(0).getElement(0).getElementCount() == 0);        
        doc.insertString(0, "the quick brown fox", null);

        harness.checkPoint("after first insertion");
        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check 
          (root.getElement(0).getElement(0).getElementCount() == 0);
        harness.check (root.getEndOffset() == 20);
        doc.insertString(6, "MIDDLE", null);        

        harness.checkPoint("after second insertion");
        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check 
          (root.getElement(0).getElement(0).getElementCount() == 0);
        harness.check (root.getEndOffset() == 26);
        doc.insertString(0, "START", null);        

        harness.checkPoint("after third insertion");
        harness.check (root.getElementCount() == 1);
        harness.check (root.getElement(0).getElementCount() == 1);
        harness.check 
          (root.getElement(0).getElement(0).getElementCount() == 0);
        harness.check (root.getEndOffset() == 31);
        doc.insertString(30, "END", null);
        
        harness.checkPoint("after fourth insertion");
        harness.check(root.getEndOffset() == 34);

        // printElements(doc.getDefaultRootElement(), 0);
      }
    catch (BadLocationException ex)
      {
        // ex.printStackTrace();
        harness.debug(ex);
      }
    catch (AssertionError ex)
      {
        // ex.printStackTrace();
        harness.debug(ex);
      }
  }
  
  static TestHarness h2;
  static int numInserts = 0;
  static int numLeaves = 0;
  static int numBranches = 0;

  public ElementStructure1()
  {
    // We override the constructor so we can explicitly set the type of the
    // buffer to be our ElementBuffer2, allowing us to test some internals.
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
        h2.check(o == 0);
        h2.check(l == 19);
      }
    else if (numInserts == 1)
    {
      h2.check(o == 6);
      h2.check(l == 6);
    } 
    else if (numInserts == 2)
      {
        h2.check(o == 0);
        h2.check(l == 5);
      }
    else if (numInserts == 3)
      {
        h2.check(o == 30);
        h2.check(l == 3);
      }
    else
      h2.fail ("too many calls to DefaultStyledDocument.insertUpdate");
    super.insertUpdate(ev, attr);
  }
  
  // Overriding this method allows us to check that the proper LeafElements
  // are being created.
  protected Element createLeafElement(Element parent, 
                                      AttributeSet a, int p0, int p1)
  {
    numLeaves ++;
    // In this test if we create a new LeafElement the test should fail.
    h2.fail("no new leaf elements should be created");
    return super.createLeafElement(parent, a, p0, p1);
  }
  
  // Overriding this method allows us to check that the proper BranchElements
  // are being created.
  protected Element createBranchElement(Element parent,
                                        AttributeSet a)
  {
    numBranches ++;
    // In this test if we create a new BranchElement the test should fail.
    h2.fail("no new branch elements should be created");
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
  public static void printElements (Element start, int pad)
  {
    pad(pad);
    if (pad == 0)
      System.out.println ("ROOT ELEMENT ("+start.getStartOffset()+", "
                          + start.getEndOffset()+")");
    else if (start instanceof AbstractDocument.BranchElement)
      System.out.println ("BranchElement ("+start.getStartOffset()+", "
                          + start.getEndOffset()+")");
    else
      {
        try
          {
            System.out.println ("LeafElement ("+start.getStartOffset()+", "
                                + start.getEndOffset()+"): "
                                + start.getAttributes().getAttributeCount() + 
                                ": "
                                + start.getDocument().
                                getText(start.getStartOffset(), 
                                        start.getEndOffset() - 
                                        start.getStartOffset()));
          }
        catch (BadLocationException ble)
          {
          }
      }
    for (int i = 0; i < start.getElementCount(); i ++)
      printElements (start.getElement(i), pad+3);
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
      numInserts ++;
      if (numInserts == 1)
        {
          h2.checkPoint("ElementBuffer insertUpdate: first insertion");
          h2.check (data.length == 1);
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check 
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 19);
        }
      else if (numInserts == 2)
        {
          h2.checkPoint("ElementBuffer insertUpdate: second insertion");
          h2.check (data.length == 1);
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check 
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 6);
        }
      else if (numInserts == 3)
        {
          h2.checkPoint("ElementBuffer insertUpdate: third insertion");
          h2.check (data.length == 1);
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check 
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 5);
        }
      else if (numInserts == 4)
        {
          h2.checkPoint("ElementBuffer insertUpdate: fourth insertion");
          h2.check (data.length == 1);
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check 
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 3);
        }
      else
        h2.fail("too many ElementSpecs created");
      super.insertUpdate(data);
    }
  }
}
