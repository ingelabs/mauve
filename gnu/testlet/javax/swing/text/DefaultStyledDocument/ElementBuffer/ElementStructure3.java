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
import javax.swing.text.Segment;

public class ElementStructure3 extends DefaultStyledDocument implements Testlet
{
	/**
   * Starts the test run.
   * 
   * @param harness
   *          the test harness to use
   */
public void test(TestHarness harness)
  {
    h2 = harness;
    try
    {
      ElementStructure3 doc = new ElementStructure3();
      doc.insertString(0, "Questions are <font size=\"+1\" color=\"blue\">a "
                       + "burden</font> to others,\n"
                       + "answers <font size=\"+2\" color=\"red\">a "
                       + "prison</font> for oneself.", null);
      // printElements(doc.getDefaultRootElement(), 0);
    }
    catch (Exception t)
    {
      // t.printStackTrace();
      harness.debug(t);
    }    
    catch (AssertionError e)
    {
      // e.printStackTrace();
      harness.debug(e);
    }
  }
  
  static TestHarness h2;
  static int numInserts = 0;
  static int numLeaves = 0;
  static int numBranches = 0;

  // We override the constructor so we can explicitly set the type of the
  // buffer to be our ElementBuffer2, allowing us to test some internals.
  public ElementStructure3()
  {
    super();    
    buffer = new ElementBuffer2(createDefaultRoot());
  }
  
  // Overriding this method allows us to check that the right number
  // of newLines was encountered and that the event has the proper
  // offset and length.
  protected void insertUpdate(DefaultDocumentEvent ev, AttributeSet attr)
  {    
    int newLines = 0;
    h2.check (ev.getLength() == 134);
    h2.check (ev.getOffset() == 0);
    Segment txt = new Segment();
    try
      {
        getText(ev.getOffset(), ev.getLength() + 1, txt);
      }
    catch (BadLocationException ble)
      {
      }

    int i = txt.offset;
    for (; i < txt.offset + txt.count - 1; i ++)
      {
        if (txt.array[i] == '\n')
          newLines ++;
      }
    h2.check (newLines == 1);
    h2.check (txt.array[i] == '\n');    
    super.insertUpdate(ev, attr);
  }

  // Overriding this method allows us to check that the proper LeafElements
  // are being created.
  protected Element createLeafElement(Element parent, 
                                      AttributeSet a, int p0, int p1)
  {
    // In this test 3 LeafElements should be created.
    numLeaves++;
    if (numLeaves== 1)
      {
        h2.checkPoint ("create first leaf element");
        h2.check (p0 == 0);
        h2.check (p1 == 70);
        try
        {
          h2.check (parent.getStartOffset() == 0);
          h2.check (parent.getEndOffset() == 135);
        }
        catch (Exception e)
        {
          // I put 2 fails here so that the total number of tests will remain
          // the same whether we pass or fail these tests.
          h2.fail ("parent Element should have children, but has none.");
          h2.fail ("parent Element should have children, but has none.");
        }
        h2.check (a.getAttributeCount() == 0);
      }
    else if (numLeaves == 2)
      {
        h2.checkPoint ("create second leaf element");
        h2.check (p0 == 134);
        h2.check (p1 == 135);
        h2.check (parent.getElementCount() == 0);
        h2.check (a.getAttributeCount() == 0);
      }
    else if (numLeaves == 3)
      {
        h2.checkPoint ("create third leaf element");
        h2.check (p0 == 70);
        h2.check (p1 == 135);
        try
        {
          h2.check (parent.getStartOffset() == 134);
          h2.check (parent.getEndOffset() == 135);
        }
        catch (Exception e)
        {
          // I put 2 fails here so that the total number of tests will remain
          // the same whether we pass or fail these tests.
          h2.fail ("branch element should have children, but has none");
          h2.fail ("branch element should have children, but has none");
        }
        h2.check (a.getAttributeCount() == 0);
      }
    else
      h2.fail ("too many leaf elements created");
    return super.createLeafElement(parent, a, p0, p1);
  }
  
  // Overriding this method allows us to check that the proper BranchElements
  // are being created.
  protected Element createBranchElement(Element parent,
                                        AttributeSet a)
  {
    // In this test we create 1 new BranchElement.    
    numBranches ++;
    if (numBranches == 1)
      {
        h2.checkPoint("create branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 135);
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
                                + start.getAttributes().getAttributeCount() 
                                + ": "
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
          h2.check (data.length == 4);
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check 
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 70);
          
          h2.check (data[1].getType() == ElementSpec.EndTagType);
          h2.check (data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check (data[1].getOffset() == 0);
          h2.check (data[1].getLength() == 0);

          h2.check (data[2].getType() == ElementSpec.StartTagType);
          h2.check 
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check (data[2].getOffset() == 0);
          h2.check (data[2].getLength() == 0);

          h2.check (data[3].getType() == ElementSpec.ContentType);
          h2.check (data[3].getDirection() == ElementSpec.JoinNextDirection);
          h2.check (data[3].getOffset() == 0);
          h2.check (data[3].getLength() == 64);
        }
      else 
        h2.fail("too many ElementSpecs created");
      super.insertUpdate(data);
    }
  }
}
