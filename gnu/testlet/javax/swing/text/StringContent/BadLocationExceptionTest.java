// Tags: JDK1.2

// Copyright (C) 2005 Arnaud Vandyck <avdyk@gnu.org>

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

public class BadLocationExceptionTest implements Testlet
{
  public void test(TestHarness h)
  {
    h.checkPoint("BadLocationTest");
    java.util.Locale.setDefault(java.util.Locale.US);
    StringContent sc = new StringContent();
    try
      {
        sc.insertString(-1, "path");
        h.fail("badlocation");
      }
    catch (BadLocationException ble)
      {
        h.check("Invalid location", ble.getMessage(), "BadLocation message should be 'Invalid location' and is: " + ble.getMessage());
        h.check(1, ble.offsetRequested(), "OffsetRequested() should be 1 and is: " + ble.offsetRequested());
      }
    try
      {
        sc.insertString(1, "path");
        h.fail("badlocation");
      }
    catch (BadLocationException ble)
      {
        h.check("Invalid location", ble.getMessage(), "BadLocation message should be 'Invalid location' and is: " + ble.getMessage());
        h.check(1, ble.offsetRequested(), "OffsetRequested() should be 1 and is: " + ble.offsetRequested());
      }
    try
      {
        sc.insertString(4, "path");
        h.fail("badlocation");
      }
    catch (BadLocationException ble)
      {
        h.check("Invalid location", ble.getMessage(), "BadLocation message should be 'Invalid location' and is: " + ble.getMessage());
        h.check(1, ble.offsetRequested(), "OffsetRequested() should be 1 and is: " + ble.offsetRequested());
      }
    try
      {
        sc.insertString(0, "path");
        sc.getString(1, sc.length());
        h.fail("badlocation");
      }
    catch (BadLocationException ble)
      {
        h.check("Invalid range", ble.getMessage(), "BadLocation message should be 'Invalid range' and is: " + ble.getMessage());
        h.check(5, ble.offsetRequested(), "OffsetRequested() should be 5 and is: " + ble.offsetRequested());
      }
    try
      {
        sc.insertString(0, "path");
        sc.getString(0, sc.length()+1);
        h.fail("badlocation");
      }
    catch (BadLocationException ble)
      {
        h.check("Invalid range", ble.getMessage(), "BadLocation message should be 'Invalid range' and is: " + ble.getMessage());
        h.check(9, ble.offsetRequested(), "OffsetRequested() should be 9 and is: " + ble.offsetRequested());
      }
  }

}

