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
import javax.swing.text.Segment;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.JTextPane;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class ElementStructure3 extends DefaultStyledDocument implements Testlet
{

  static TestHarness h2;

  // Creates a new ElementStructure3 using an ElementBuffer2 as the buffer
  public ElementStructure3()
  {
    super();
    buffer = new ElementBuffer2(createDefaultRoot());
  }

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
  
 public class ElementBuffer2 extends ElementBuffer
  {
    public ElementBuffer2(Element root)
    {
      super(root);
    }
    
    protected void insertUpdate(ElementSpec[] data)
    {
      // Check that ElementSpecs were created with the proper direction, offset
      // and length, and that the right number of Specs were created.
      h2.check (data.length == 4);
      for (int i = 0; i < data.length; i ++)
        {
          short s = data[i].getDirection();
          if (s == ElementSpec.JoinPreviousDirection)
            {
              h2.check (data[i].getOffset() == 0);
              h2.check (data[i].getLength() == 70);
            }
          else if (s == ElementSpec.JoinNextDirection)
            {
              h2.check (data[i].getOffset() == 0);
              h2.check (data[i].getLength() == 64);
            }
          else if (s == ElementSpec.JoinFractureDirection)
            {
              h2.check (data[i].getOffset() == 0);
              h2.check (data[i].getLength() == 0);
            }
          else if (s == ElementSpec.OriginateDirection)
            {
              h2.check (data[i].getOffset() == 0);
              h2.check (data[i].getLength() == 0);
            }
        }
      super.insertUpdate(data);
    }
  }
  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    try
      {
        h2 = harness;
        JTextPane tp = new JTextPane();
        tp.setDocument(new ElementStructure3());
        tp.setText("Questions are <font size=\"+1\" color=\"blue\">a " + 
                   "burden</font> to others,\n" +
                   "answers <font size=\"+2\" color=\"red\">a " + 
                   "prison</font> for oneself.");
      }
    catch (Throwable t)
      {
        harness.debug(t);
      }
  }
}
