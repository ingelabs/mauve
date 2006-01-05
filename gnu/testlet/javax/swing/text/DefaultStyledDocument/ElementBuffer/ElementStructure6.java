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

public class ElementStructure6 implements Testlet
{
  static TestHarness h2;
  static DefaultStyledDocument doc = new StyledDocument2();
  static AbstractDocument.DefaultDocumentEvent docEvent = null;
  
  public void test(TestHarness harness)
  {
    try
      {
        h2 = harness;
        doc.insertString(0, "First line of text. \n", null);
      }
    catch (BadLocationException ex)
      {
        h2.debug(ex);
      }        
  }
  

  static class StyledDocument2 extends DefaultStyledDocument
  {
    public class ElementBuffer2 extends ElementBuffer
    {
      public ElementBuffer2(Element root)
      {
        super(root);
      }
      
      // ElementBuffer.insertUpdate
      protected void insertUpdate(ElementSpec[] data)
      {
        h2.check(docEvent.getChange(getDefaultRootElement()) == null);
        super.insertUpdate(data);
        h2.check(docEvent.getChange(getDefaultRootElement()) == null);
      }

      public void insert(int offset,
                         int length,
                         DefaultStyledDocument.ElementSpec[] data,
                         AbstractDocument.DefaultDocumentEvent de)
      {
        h2.check(docEvent.getChange(getDefaultRootElement()) == null);
        super.insert(offset, length, data, de);
        h2.check(docEvent.getChange(getDefaultRootElement()) != null);
      }
    }
    
    // Creates a new StyledDocument2 using an ElementBuffer2 as the buffer
    public StyledDocument2()
    {
      super();
      buffer = new ElementBuffer2(createDefaultRoot());
    }
    
    // DefaultStyledDocument.insertUpdate
    protected void insertUpdate(AbstractDocument.DefaultDocumentEvent chng,
                                AttributeSet attr)
    {      
      docEvent = chng;
      h2.check(docEvent.getChange(getDefaultRootElement()) == null);
      super.insertUpdate (chng, attr);
      h2.check(docEvent.getChange(getDefaultRootElement()) != null);
    }    
  }    
}
