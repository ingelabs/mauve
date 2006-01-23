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

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;

public class StyledDocument1 extends DefaultStyledDocument implements Testlet
{
  public void test(TestHarness harness) 
  {
    h2 = harness;
    StyledDocument1 doc = new StyledDocument1();
    try
      {
        doc.insertString(0, "aaaaaaaaa\nbbbbbbbbb", null);
        doc.insertString(10, "N", null);
      }
    catch (Exception ex)
      {
        // ex.printStackTrace();
        harness.debug(ex);
      }
    catch (AssertionError e)
      {
        e.printStackTrace();
        harness.debug(e);
      }
    // printElements(doc.getDefaultRootElement(), 0);
  }
  
  // A variable to keep track of the number of times text has been inserted
  static int numInserts = 0;

  static TestHarness h2;
  
  static int numLeaves = 0;
  static int numBranches = 0;

  
  // Creates a new StyledDocument1 using an ElementBuffer2 as the buffer
  public StyledDocument1()
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
      h2.check(o == 10);      
      h2.check(l == 1);
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
    numLeaves++;
    if (numLeaves== 1)
      {
        h2.checkPoint ("create first leaf element");
        h2.check (p0 == 0);
        h2.check (p1 == 10);
        try
        {
          h2.check (parent.getStartOffset() == 0);
          h2.check (parent.getEndOffset() == 20);
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
        h2.check (p0 == 19);
        h2.check (p1 == 20);
        h2.check (parent.getElementCount() == 0);
        h2.check (a.getAttributeCount() == 0);
      }
    else if (numLeaves == 3)
      {
        h2.checkPoint ("create third leaf element");
        h2.check (p0 == 10);
        h2.check (p1 == 20);
        try
        {
          h2.check (parent.getStartOffset() == 19);
          h2.check (parent.getEndOffset() == 20);
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
    else if (numLeaves == 4)
      {
        h2.checkPoint ("create fourth leaf element");
        h2.check (p0 == 0);
        h2.check (p1 == 10);
        try
        {
          h2.check (parent.getStartOffset() == 0);
          h2.check (parent.getEndOffset() == 11);
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
    else if (numLeaves == 5)
      {
        h2.checkPoint ("create fifth leaf element");
        h2.check (p0 == 10);
        h2.check (p1 == 21);
        try
        {
          h2.check (parent.getStartOffset() == 11);
          h2.check (parent.getEndOffset() == 21);
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
    numBranches ++;
    if (numBranches == 1)
      {
        h2.checkPoint("create branch element");
        h2.check(parent.getStartOffset() == 0);
        h2.check(parent.getEndOffset() == 20);
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
  
  // A class to be the buffer of the styled document that also prints out some 
  // debugging info and checks that internal structure is correct
  public class ElementBuffer2 extends ElementBuffer
  {
    public ElementBuffer2(Element root)
    {
      super(root);
    }
    
    protected void insertUpdate(ElementSpec[] data)
    {
      numInserts ++;
      if (numInserts == 1)
        {
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check 
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 10);

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
          h2.check (data[3].getLength() == 9);
        }
      else if (numInserts == 2)
        {
          h2.check (data[0].getType() == ElementSpec.EndTagType);
          h2.check (data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 0);
          
          h2.check (data[1].getType() == ElementSpec.StartTagType);
          h2.check (data[1].getDirection() == ElementSpec.JoinNextDirection);
          h2.check (data[1].getOffset() == 0);
          h2.check (data[1].getLength() == 0);

          h2.check (data[2].getType() == ElementSpec.ContentType);
          h2.check (data[2].getDirection() == ElementSpec.JoinNextDirection);
          h2.check (data[2].getOffset() == 0);
          h2.check (data[2].getLength() == 1);
        }
      else 
        h2.fail("too many ElementSpecs created");
      super.insertUpdate(data);
    }
  }  
}
