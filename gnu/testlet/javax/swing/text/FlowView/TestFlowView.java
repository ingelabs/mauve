/* TestFlowView.java -- A concrete FlowView for testing
   Copyright (C) 2006 Roman Kennke (kennke@aicas.com)
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

// Tags: not-a-test

package gnu.testlet.javax.swing.text.FlowView;

import javax.swing.text.Element;
import javax.swing.text.FlowView;
import javax.swing.text.View;

/**
 * A concrete subclass of FlowView that can be used to test the FlowView.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class TestFlowView extends FlowView
{

  /**
   * Constructs a new TestFlowView by calling super.
   *
   * @param element the element
   * @param axis the axis
   */
  public TestFlowView(Element element, int axis)
  {
    super(element, axis);
  }

  /**
   * Creates a row.
   *
   * @return a view for one row
   */
  protected View createRow()
  {
    return null;
  }

}
