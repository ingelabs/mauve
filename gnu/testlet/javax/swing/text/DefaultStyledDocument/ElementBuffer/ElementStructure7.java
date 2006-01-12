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

import javax.swing.JTextPane;
import javax.swing.text.*;
import javax.swing.event.*;

import java.util.Vector;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class ElementStructure7 implements Testlet
{

  static TestHarness h2;

  /**
   * Starts the test run.
   * 
   * @param harness
   *          the test harness to use
   */
  public void test(TestHarness harness)
  {
    try
      {
        JTextPane tp = new JTextPane();
        StyledDocument doc = new StyledDocument2();
        GapContent gc = new GapContent();
        tp.setStyledDocument(doc);

        h2 = harness;
        doc.insertString(0, "aaaaaaaaa\nbbbbbbbbb", null);
        doc.insertString(5, "\nN", null);
      }
    catch (BadLocationException ex)
      {
        h2.debug(ex);
      }
  }

  static class StyledDocument2 extends DefaultStyledDocument
  {
    DefaultDocumentEvent2 docEvent = null;

    public StyledDocument2()
    {
      super();
      buffer = new ElementBuffer2(createDefaultRoot());
    }

    public class ElementBuffer2 extends ElementBuffer
    {

      public ElementBuffer2(Element root)
      {
        super(root);
      }

      public void insert(int offset, int length, ElementSpec[] data,
                         DefaultDocumentEvent ev)
      {
        docEvent = new DefaultDocumentEvent2(ev.getOffset(), ev.getLength(),
                                             ev.getType());
        super.insert(offset, length, data, docEvent);
        Vector edits = docEvent.getEdits();
        
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
}
