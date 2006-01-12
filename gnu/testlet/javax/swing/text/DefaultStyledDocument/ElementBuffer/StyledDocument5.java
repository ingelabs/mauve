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

public class StyledDocument5 extends DefaultStyledDocument implements Testlet
{
  // A variable to keep track of the number of times text has been inserted
  static int numInserts = 0;
  
  static TestHarness h2;

  // Creates a new StyledDocument5 using an ElementBuffer2 as the buffer
  public StyledDocument5()
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
          h2.check (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 10);
          
          h2.check (data[1].getType() == ElementSpec.EndTagType);
          h2.check (data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check (data[1].getOffset() == 0);
          h2.check (data[1].getLength() == 0);

          h2.check (data[2].getType() == ElementSpec.StartTagType);
          h2.check (data[2].getDirection() == ElementSpec.JoinFractureDirection);
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
      else if (numInserts == 3)
        {
          h2.check (data[0].getType() == ElementSpec.ContentType);
          h2.check (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check (data[0].getOffset() == 0);
          h2.check (data[0].getLength() == 1);
          
          h2.check (data[1].getType() == ElementSpec.EndTagType);
          h2.check (data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check (data[1].getOffset() == 0);
          h2.check (data[1].getLength() == 0);

          h2.check (data[2].getType() == ElementSpec.StartTagType);
          h2.check (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check (data[2].getOffset() == 0);
          h2.check (data[2].getLength() == 0);

          h2.check (data[3].getType() == ElementSpec.ContentType);
          h2.check (data[3].getDirection() == ElementSpec.JoinNextDirection);
          h2.check (data[3].getOffset() == 0);
          h2.check (data[3].getLength() == 9);
        }

      super.insertUpdate(data);
    }
  }
     
  public void test(TestHarness harness) 
  {
    h2 = harness;
    StyledDocument doc = new StyledDocument5();
    try
      {
        doc.insertString(0, "aaaaaaaaa\nbbbbbbbbb", null);
        doc.insertString(10, "N", null);        
        doc.insertString(5, "\nhellooooo", null);
      }
    catch (BadLocationException ex)
      {
        harness.debug(ex);
      }        
  }
}
