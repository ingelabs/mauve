// Tags: JDK1.2

// Copyright (C) 2004 Arnaud Vandyck <avdyk@gnu.org>

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
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

package gnu.testlet.javax.swing.text.StringContent;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.StringContent;
import javax.swing.text.Segment;
import javax.swing.undo.UndoableEdit;

public class insertUndo implements Testlet
{
  public void test(TestHarness h)
  {
    h.checkPoint("StringContent -- InsertUndo");

    StringContent sc = new StringContent();
    try
      {
        h.checkPoint("StringContent -- insertString()");
        UndoableEdit ue = sc.insertString(0, "path");
        h.check("path\n", sc.getString(0, sc.length()),
                "StringContent.insertString(): insert 'path' at 0");
        /* it depends on locale! how can I test this?
         * do I have to test all the locales?
         */
        /*
        String presentationName = ue.getPresentationName();
        h.check("blah", presentationName, "PresentationName should be blah and is: " + presentationName);
        String redoPresentationName = ue.getRedoPresentationName();
        h.check("blah", redoPresentationName, "RedoPresentationName should be blah and is: " + redoPresentationName);
        String undoPresentationName = ue.getUndoPresentationName();
        h.check("blah", undoPresentationName, "UndoPresentationName should be blah and is: " + undoPresentationName);
        */

        h.check(false, ue.canRedo(), "canRedo? () (" + ue.canRedo() + ")");
        h.check(true, ue.canUndo(), "canUndo? () (" + ue.canUndo() + ")");
        ue.undo();
        h.check("\n", sc.getString(0, sc.length()),
                "Undo");
        h.check(true, ue.canRedo(), "canRedo? () (" + ue.canRedo() + ")");
        h.check(false, ue.canUndo(), "canUndo? () (" + ue.canUndo() + ")");
        ue.redo();
        h.check("path\n", sc.getString(0, sc.length()),
                "Redo");
      }
    catch (BadLocationException ble)
      {
        h.fail("BadLocation! " + ble.getMessage());
      }
  }

}

