// Tags: JDK1.2

// Copyright (C) 2003 Sascha Brawer <brawer@dandelis.ch>

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
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.awt.geom.QuadCurve2D.Float;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.awt.geom.QuadCurve2D;


/**
 * Checks whether the QuadCurve2D.Float constructors works correctly.
 *
 * @author Sascha Brawer (brawer@dandelis.ch)
 */
public class Float
  implements Testlet
{
  private TestHarness h;

  public void test(TestHarness harness)
  {
    this.h = harness;
    testZeroArgs();
    testSixArgs();
  }


  /**
   * Checks whether the zero-argument constructor works as specified.
   */
  public void testZeroArgs()
  {
    QuadCurve2D curve;

    h.checkPoint("noArgs");
    curve = new QuadCurve2D.Float();
    h.check(curve.getX1(), 0);     // 1
    h.check(curve.getY1(), 0);     // 2
    h.check(curve.getCtrlX(), 0);  // 3
    h.check(curve.getCtrlY(), 0);  // 4
    h.check(curve.getX2(), 0);     // 5
    h.check(curve.getY2(), 0);     // 6
  }


  /**
   * Checks whether the six-argument constructor works as specified.
   */
  public void testSixArgs()
  {
    QuadCurve2D.Float curve;

    h.checkPoint("sixArgs");
    curve = new QuadCurve2D.Float(1e1f, 2e2f, 3e3f, 4e4f, 5e5f, 6e6f);
    h.check(curve.getX1(), 1e1f);    // 1
    h.check(curve.getY1(), 2e2f);    // 2
    h.check(curve.getCtrlX(), 3e3f); // 3
    h.check(curve.getCtrlY(), 4e4f); // 4
    h.check(curve.getX2(), 5e5f);    // 5
    h.check(curve.getY2(), 6e6f);    // 6

    h.check(curve.x1, 1e1f);         // 7
    h.check(curve.y1, 2e2f);         // 8
    h.check(curve.ctrlx, 3e3f);      // 9
    h.check(curve.ctrly, 4e4f);      // 10
    h.check(curve.x2, 5e5f);         // 11
    h.check(curve.y2, 6e6f);         // 12
  }
}
