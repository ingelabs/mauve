// Tags: JDK1.2

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

package gnu.testlet.javax.swing.plaf.metal.MetalLookAndFeel;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * Checks if MetalLookAndFeel.setCurrentTheme works correctly.
 *
 * @author Roman Kennke
 */
public class setCurrentTheme implements Testlet
{
  public void test(TestHarness h)
  {
    MetalLookAndFeel laf = new MetalLookAndFeel();
    Color c1 = laf.getDefaults().getColor("Button.background");
    h.check(c1, new Color(204, 204, 204));
    MetalLookAndFeel.setCurrentTheme(new TestTheme());
    c1 = laf.getDefaults().getColor("Button.background");
    h.check(c1, Color.red);

    // reset the theme so that other tests won't be affected
    MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
  }

  class TestTheme extends DefaultMetalTheme 
  {
    protected ColorUIResource getSecondary3()
    {
      return new ColorUIResource(Color.red);
    }
  }
  
}
