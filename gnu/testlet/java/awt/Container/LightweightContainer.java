/* LightweightContainer.java -- 
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

// Tags: JDK 1.4

package gnu.testlet.java.awt.Container;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.*;

public class LightweightContainer implements Testlet
{
  
  public void test (TestHarness harness)
  {
    testLoc(harness);
    testWindow(harness);
  }
  
  // Tests a Window containing a Frame and a Lightweight Container
  public void testWindow(TestHarness harness)
  {
    Robot r = harness.createRobot();
        
    Frame f = new Frame();
    testContainer tc = new testContainer();
    f.setSize(500, 500);
    tc.setBounds(0, 0, 500, 500);
    f.add(tc);
    f.show();

    r.waitForIdle();
    r.delay(300);
    
    // bounds of red rectangle (1 pixel wide border)
    Rectangle bounds = tc.getBounds();
    Point loc = f.getLocationOnScreen();
    Insets i = f.getInsets();
    bounds.x = loc.x + i.left;
    bounds.y = loc.y + i.top;
    // bounds of blue rectangle inside red rectangle
    Rectangle bounds2 = new Rectangle(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height - 2);
    harness.checkRectangleOuterColors(r, bounds2, Color.red, true);
    harness.checkRectangleCornerColors(r, bounds2, Color.red, false);
    
    r.delay(3000);
  }
  
  // Tests the location of a Lightweight Container containing
  // a heavyweight component, which is also in a heavyweight
  public void testLoc(TestHarness harness)
  {
    Robot r = harness.createRobot();
    Color bgHW_c = Color.red;
    Color fgHW_c = Color.blue;
    
    testPanel bgHW = new testPanel(bgHW_c);
    Container fgLW = new Container();
    testPanel fgHW = new testPanel(fgHW_c);
    
    Frame f = new Frame();
    
    int bgHW_x = 0;
    int bgHW_y = 0;
    int bgHW_w = 500;
    int bgHW_h = 500;
    
    int fgLW_x = 50;
    int fgLW_y = 60;
    int fgLW_w = 200;
    int fgLW_h = 200;
    
    int fgHW_x = 70;
    int fgHW_y = 40;
    int fgHW_w = 100;
    int fgHW_h = 100;

    f.setSize(500, 500);
    bgHW.setBounds(bgHW_x, bgHW_y, bgHW_w, bgHW_h);
    fgLW.setBounds(fgLW_x, fgLW_y, fgLW_w, fgLW_h);
    fgHW.setBounds(fgHW_x, fgHW_y, fgHW_w, fgHW_h);
    f.add(bgHW);
    bgHW.add(fgLW);
    fgLW.add(fgHW);
    f.show();
    
    r.waitForIdle();
    r.delay(300);    
    Insets i = f.getInsets();
    
    // check the two pixels adjacent to each corner of the fgHW
    Point p = f.getLocationOnScreen();
    fgHW_x = p.x + i.left + fgHW_x + fgLW_x;
    fgHW_y = p.y + i.top + fgHW_y + fgLW_y;
    harness.checkRectangleOuterColors(r, new Rectangle(fgHW_x, fgHW_y, fgHW_w, fgHW_h), bgHW_c, true);
    
    // check the fgHW's corner pixels.
    harness.checkRectangleCornerColors(r, new Rectangle(fgHW_x, fgHW_y, fgHW_w, fgHW_h), bgHW_c, false);
    
    // check the two pixels adjacent to each corner of the fgLW
    p = f.getLocationOnScreen();
    fgLW_x = p.x + i.left + fgLW_x;
    fgLW_y = p.y + i.top + fgLW_y;
    harness.checkRectangleOuterColors(r, new Rectangle(fgLW_x, fgLW_y, fgLW_w, fgLW_h), bgHW_c, true);

    r.delay(3000);
  }
  
  class testPanel extends Panel
  {
    Color c;

    public testPanel(Color c)
    {
      super(null);
      this.c = c;
    }

    public void paint(Graphics g)
    {
      Rectangle bounds = new Rectangle(0,
                                       0,
                                       this.getSize().width,
                                       this.getSize().height);
      g.setColor(c);
      g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
  }
  
  class testContainer extends Container
  {

    public testContainer()
    {
      super();
    }

    public void paint(Graphics g)
    {
      Rectangle bounds = new Rectangle(0,
                                       0,
                                       this.getSize().width,
                                       this.getSize().height);
      g.setColor(Color.red);
      g.drawRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
      g.setColor(Color.blue);
      g.fillRect(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height - 2);
    }
  }
}
