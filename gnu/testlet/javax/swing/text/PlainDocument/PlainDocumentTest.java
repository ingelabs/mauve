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

package gnu.testlet.javax.swing.text.PlainDocument;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import javax.swing.text.*;

public class PlainDocumentTest
  implements Testlet
{
  private void checkElement(TestHarness harness, Element elem,
			    AttributeSet attributes, int children,
			    Element parent, int p0, int p1,
			    boolean leaf)
  {
    //harness.check(elem.getAttributes() == null, "unexpected value for Element.getAttributes()");
    harness.check(elem.getElementCount() == children, "unexpected number of children: " + elem.getElementCount());
    harness.check(elem.getParentElement() == parent, "Wrong parent");
    harness.check(elem.getStartOffset() == p0, "start offset: " + elem.getStartOffset() + " (" + p0 + " expected");
    harness.check(elem.getEndOffset() == p1, "end offset: " + elem.getEndOffset() + " (" + p1 + " expected");
    harness.check(elem.isLeaf() == leaf, "element is leaf or branch element");
  }

  public void test(TestHarness harness)
  {
    PlainDocument doc = new PlainDocument();

    try
      {
	doc.insertString(0, "This is a test", null);
	harness.check(doc.getText(0, doc.getLength()), "This is a test", "insertString");

	doc.insertString(14, "case", null);
	harness.check(doc.getText(0, doc.getLength()), "This is a testcase", "insertString");

	doc.insertString(10, "little ", null);
	harness.check(doc.getText(0, doc.getLength()), "This is a little testcase", "insertString");
      }
    catch (Exception e)
      {
	harness.fail("Unexpected expection");
      }

    // Check build Element tree.
    Element root = doc.getDefaultRootElement();
    checkElement(harness, root, null, 1, null, 0, doc.getLength() + 1, false);

    Element elem = root.getElement(0);
    checkElement(harness, elem, null, 0, root, 0, doc.getLength() + 1, true);
  }
}