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

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class StyledDocument3 extends DefaultStyledDocument implements Testlet
{
  // A variable to keep track of the number of times text has been inserted
  static int numInserts = 0;

  static TestHarness h2;

  // Creates a new StyledDocument3 using an ElementBuffer2 as the buffer
  public StyledDocument3()
  {
    super();
    buffer = new ElementBuffer2(createDefaultRoot());
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
          h2.check (data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 5);
        }
      else if (numInserts == 2)
        {
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check (data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 5);
        }
      else if (numInserts == 3)
        {
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 1);
        }
      else if (numInserts == 4)
        {
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check (data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 1);
        }

      super.insertUpdate(data);
    }
  }
    
  public void test(TestHarness harness) 
  {
    h2 = harness;
    StyledDocument doc = new StyledDocument3();
    SimpleAttributeSet atts = new SimpleAttributeSet();
    try
      {
        atts.addAttribute(StyleConstants.StrikeThrough, Boolean.TRUE);
        doc.insertString(0, "bbbbb", atts);
        doc.insertString(5, "aaaaa", null);
        doc.insertString(5, "N", atts);
        atts.addAttribute(StyleConstants.Bold, Boolean.TRUE);
        doc.insertString(6, "M", atts);
      }
    catch (BadLocationException ex)
      {
        ex.printStackTrace();
      }        

  }
}
