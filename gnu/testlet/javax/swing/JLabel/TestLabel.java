// Tags: not-a-test

// Copyright (C) 2005 Roman Kennke <kennke@aicas.com>

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
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.javax.swing.JLabel;

import javax.swing.JLabel;

/**
 * A subclass of {@link javax.swing.JLabel} that enables to check if
 * certain methods (like repaint() or revalidate()) are called correctly.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
class TestLabel extends JLabel
{
  /**
   * A flag indicating if repaint() has been called.
   */
  boolean repaintCalled;

  /**
   * A flag indicating if revalidate() has been called.
   */
  boolean revalidateCalled;

  /**
   * Performs the superclass repaint and sets the repaintCalled flag to true.
   */
  public void repaint()
  {
    repaintCalled = true;
    super.repaint();
  }

  /**
   * Performs the superclass revalidate and sets the revalidateCalled flag
   * to true.
   */
  public void revalidate()
  {
    revalidateCalled = true;
    super.revalidate();
  }
}
