// Tags: GUI JDK1.2

// Copyright (C) 2005 Red Hat

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

package gnu.testlet.java.awt.Component;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.*;

/**
 * The method invalidate should be automatically called in
 * response to several property changes such as size and location.
 * This is checked here.
 *
 * @author Roman Kennke (roman@ontographics.com)
 */
public class invalidate implements Testlet
{

  /** If revalidate has been called or not. */
  boolean invalidated;

  /**
   * Non abstract subclass of Component to allow instatiation and
   * test.
   */
  public class TestComponent extends Component {
    /** Override this method to check if revalidate has been called.
    */
    public void invalidate() {
      invalidated = true;
      super.invalidate();
    }
  }

  public void test (TestHarness harness)
  {
    // prepare test component
    Component comp = new TestComponent();
    Frame frame = new Frame();
    frame.add(comp);
    frame.setVisible(true);

    // change size and check if revalidate has been called
    invalidated = false;
    comp.setSize(100, 200);
    harness.check(invalidated, true);

    // change size and check if revalidate has been called
    invalidated = false;
    comp.setSize(new Dimension(101, 201));
    harness.check(invalidated, true);

    // change size and check if revalidate has been called
    invalidated = false;
    comp.resize(102, 202);
    harness.check(invalidated, true);

    frame.dispose();
  }
}
