// Tags: JDK1.2

// Copyright (C) 2004 Michael Koch <konqueror@gmx.de>

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

package gnu.testlet.javax.swing.text.GapContent;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import javax.swing.text.GapContent;

public class GapContentTest
  implements Testlet
{
  private void testContent(TestHarness harness, GapContent content)
  {
    //harness.check(content.length() == 0, "length == 0: " + content.length());

    try
      {
	content.insertString(0, "This is a testcase");
	harness.check(true, "insertString");
      }
    catch (Exception e)
      {
	harness.fail("unexpected excpetion");
      }

    //harness.check(content.length() == 18, "length == 18:" + content.length());

    try
      {
	content.insertString(10, "little ");
	harness.check(true, "insertString");
      }
    catch (Exception e)
      {
	harness.fail("unexpected excpetion");
      }

    //harness.check(content.length() == 25, "length == 25:" + content.length());
  }
  
  public void test(TestHarness harness)
  {
    testContent(harness, new GapContent());
    testContent(harness, new GapContent(100));
  }
}