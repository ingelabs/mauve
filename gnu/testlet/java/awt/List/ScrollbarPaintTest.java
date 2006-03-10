/* ScrollbarPaintTest.java -- 
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

// Tags: GUI, JDK 1.0

package gnu.testlet.java.awt.List;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;

public class ScrollbarPaintTest
    extends Panel
    implements Testlet
{

  /**
   * Runs the test using the specified harness. 
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)
  {
    setBackground(Color.red);
    Frame f = new Frame();
    List c = new List(2);
    c.add("1");
    c.add("2");
    c.add("3");
    add(c);
    f.add(this);
    f.pack();
    f.show();
    Rectangle bounds = c.getBounds();
    Insets i = f.getInsets();
    Point loc = f.getLocationOnScreen();
    loc.x += i.left + bounds.x;
    loc.y += i.top + bounds.y;
    bounds.x += i.left;
    bounds.y += i.top;
    
    Robot r = harness.createRobot();
    Color scroll = r.getPixelColor(loc.x + bounds.width - i.left, loc.y + bounds.height/2);
    
    // Check if scrollbar was painted.
    harness.check(!(scroll.equals(Color.white)));
    harness.check(!(scroll.equals(Color.red)));
    
    // There is a delay to avoid any race conditions    
    // and so user can see frame
    r.waitForIdle();
    r.delay(1000);
  }

  public void paint(Graphics g)
  {
    Image offScr = createImage(getSize().width, getSize().height);
    Graphics offG = offScr.getGraphics();
    offG.setClip(0, 0, getSize().width, getSize().height);

    super.paint(offG);
    g.drawImage(offScr, 0, 0, null);

    offG.dispose();
  }
}


