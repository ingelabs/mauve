// Tags: GUI JDK1.0

// Copyright (C) 2004 Red Hat

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

package gnu.testlet.java.awt.Frame;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.*;
import java.awt.event.*;

/**
 * Check that a frame's size is set correctly when it is initially
 * shown.
 */
public class size1 implements Testlet
{
  // a random background color that is unlikely to be used by the
  // window decorations.
  static Color nonWMColor = new Color (243, 133, 89);

  class testFrame extends Frame
  {
    Color c;

    public testFrame (Color c)
    {
      super ();

      this.c = c;
    }

    public void paint (Graphics g)
    {
      Rectangle bounds = new Rectangle (0, 0,
					this.getSize ().width,
					this.getSize ().height);
      g.setColor (c);
      g.fillRect (bounds.x, bounds.y, bounds.width, bounds.height);
    }
  }

  public void checkColor (TestHarness harness, Color c, boolean match)
  {
    if (match)
      harness.check (c.getRed () == nonWMColor.getRed ()
		     && c.getGreen () == nonWMColor.getGreen ()
		     && c.getBlue () == nonWMColor.getBlue ());
    else
      harness.check (!(c.getRed () == nonWMColor.getRed ()
		       && c.getGreen () == nonWMColor.getGreen ()
		       && c.getBlue () == nonWMColor.getBlue ()));
  }

  public void test (TestHarness harness)
  {
    Robot r = harness.createRobot ();
    Color c;
    testFrame bg = new testFrame (nonWMColor);
    testFrame fg = new testFrame (new Color (0, 0, 0));

    int bg_x = 75;
    int bg_y = 75;
    int bg_width = 250;
    int bg_height = 250;

    int fg_x = 150;
    int fg_y = 150;
    int fg_width = 100;
    int fg_height = 100;

    bg.setLocation (bg_x, bg_y);
    bg.setSize (bg_width, bg_height);

    fg.setLocation (fg_x, fg_y);
    fg.setSize (fg_width, fg_height);

    bg.show ();
    fg.show ();

    r.waitForIdle ();
    r.delay (100);

    // check the two pixels adjacent to each corner of the foreground
    // frame.

    // top-left-left
    c = r.getPixelColor (fg_x - 1, fg_y);
    checkColor (harness, c, true);

    // top-left-top
    c = r.getPixelColor (fg_x, fg_y - 1);
    checkColor (harness, c, true);

    // top-right-right
    c = r.getPixelColor ((fg_x + fg_width - 1) + 1, fg_y);
    checkColor (harness, c, true);

    // top-right-top
    c = r.getPixelColor ((fg_x + fg_width - 1), fg_y - 1);
    checkColor (harness, c, true);

    // bottom-left-left
    c = r.getPixelColor (fg_x - 1, (fg_y + fg_height - 1));
    checkColor (harness, c, true);

    // bottom-left-bottom
    c = r.getPixelColor (fg_x, (fg_y + fg_height - 1) + 1);
    checkColor (harness, c, true);

    // bottom-right-right
    c = r.getPixelColor ((fg_x + fg_width - 1) + 1,
			 (fg_y + fg_height - 1));
    checkColor (harness, c, true);

    // bottom-right-bottom
    c = r.getPixelColor ((fg_x + fg_width - 1),
			 (fg_y + fg_height - 1) + 1);
    checkColor (harness, c, true);

    // check the frame's corner pixels.

    // top-left
    c = r.getPixelColor (fg_x, fg_y);
    checkColor (harness, c, false);

    // top-right
    c = r.getPixelColor (fg_x + fg_width - 1, fg_y);
    checkColor (harness, c, false);

    // bottom-left
    c = r.getPixelColor (fg_x, fg_y + fg_height - 1);
    checkColor (harness, c, false);

    // bottom-right
    c = r.getPixelColor (fg_x + fg_width -1,
			 fg_y + fg_height - 1);
    checkColor (harness, c, false);

    r.delay (3000);
  }
}
