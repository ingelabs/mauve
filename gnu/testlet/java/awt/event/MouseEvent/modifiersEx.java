// Tags: JDK1.4

// Copyright (C) 2005 Roman Kennke <roman@kennke.org>

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

package gnu.testlet.java.awt.event.MouseEvent;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.awt.Frame;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Checks if a click does trigger the correct getModifiersEx() flags.
 *
 * @author Roman Kennke
 */
public class modifiersEx implements Testlet
{
  int mask;
  
  public void test(TestHarness h)
  {
    Frame frame = new Frame();
    frame.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent ev) 
        {
          mask = ev.getModifiersEx();
        }
        public void mouseReleased(MouseEvent ev) 
        {
          mask = ev.getModifiersEx();
        }
      });
    frame.setSize(100, 100);
    frame.show();
    Point loc = frame.getLocationOnScreen();
    
    Robot robot = h.createRobot();
    robot.mouseMove(loc.x + 50, loc.y + 50);
    
    robot.mousePress(InputEvent.BUTTON1_MASK);
    h.check(mask & InputEvent.BUTTON1_DOWN_MASK, InputEvent.BUTTON1_DOWN_MASK,
            "mousePressed: " + mask);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    h.check(mask & InputEvent.BUTTON1_DOWN_MASK, 0, "mouseReleased: " + mask);
  }

}
