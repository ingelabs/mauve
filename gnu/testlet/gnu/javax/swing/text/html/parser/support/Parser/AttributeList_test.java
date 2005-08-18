// Tags: JDK1.2 

// Copyright (C) 2005 Audrius Meskauskas <audriusa@bluewin.ch>

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


package gnu.testlet.gnu.javax.swing.text.html.parser.support.Parser;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.text.html.parser.AttributeList;
import gnu.testlet.gnu.javax.swing.text.html.parser.*;

/**
 * @author Audrius Meskauskas (AudriusA@Bioinformatics.org)
 */
public class AttributeList_test
  extends TestCase
  implements Testlet
{
  private AttributeList attributeList = null;

  public void test(TestHarness harness)
  {
    h = harness;
    testSame();
  }

  public void testSame()
  {
    for (int i = 0; i < 100; i++)
      {
        String t = AttributeList.type2name(i);
        if (t != null)
          assertEquals(i, AttributeList.name2type(t));
      }
  }

  protected void setUp()
                throws Exception
  {
    super.setUp();
    attributeList = new AttributeList("ku");
    assertEquals(attributeList.toString(), "ku");
  }
}
