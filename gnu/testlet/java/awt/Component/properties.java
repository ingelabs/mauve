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
import java.awt.dnd.*;
import java.util.*;
import java.beans.*;


/**
 * Check if bound properties are firing PropertyChangeEvents and
 * simple properties not.
 *
 * @author Roman Kennke (roman@ontographics.com)
 */
public class properties implements Testlet
{

  /**
   * stores the name of a fired property or <code>null</code> of none has
   * been fired.
   */
  private String propertyName;

  /**
   * Non abstract subclass of Component to allow instatiation.
   */
  public class TestComponent extends Component {
  }

  public void test (TestHarness harness)
  {
    // prepare test component
    Component comp = new TestComponent();
    comp.addPropertyChangeListener(new PropertyChangeListener() {
            // sets <code>propertyName</code> when called
            public void propertyChange(PropertyChangeEvent ev) {
                propertyName = ev.getPropertyName();
            }
        });


    // check 'background' property (must be fired)
    propertyName = null;
    comp.setBackground(Color.YELLOW);
    harness.check(propertyName, "background");

    // check 'bounds' property (must not be fired)
    propertyName = null;
    comp.setBounds(new Rectangle(143, 564, 1200, 2233));
    harness.check(propertyName, null);

    // check 'componentOrientation' property (should be fired)
    propertyName = null;
    comp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    // do second call to assure that the property actually changes
    comp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    harness.check(propertyName, "componentOrientation");

    // check 'cursor' property (must not be fired)
    propertyName = null;
    comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
    harness.check(propertyName, null);
    harness.check(propertyName, null);

    // check 'dropTarget' property (must not be fired)
    propertyName = null;
    comp.setDropTarget(new DropTarget());
    harness.check(propertyName, null);

    // check 'enabled' property (must not be fired)
    propertyName = null;
    comp.setEnabled(true);
    comp.setEnabled(false);
    harness.check(propertyName, null);

    // check 'font' property (must be fired)
    propertyName = null;
    comp.setFont(Font.getFont("Monospaced"));
    comp.setFont(Font.getFont("SansSerif"));
    harness.check(propertyName, "font");

    // check 'foreground' property (must be fired)
    propertyName = null;
    comp.setForeground(Color.CYAN);
    harness.check(propertyName, "foreground");

    // check 'locale' property (must be fired)
    propertyName = null;
    comp.setLocale(Locale.CHINESE);
    comp.setLocale(Locale.GERMAN);
    harness.check(propertyName, "locale");

    // check 'location' property (must not be fired)
    propertyName = null;
    comp.setLocation(new Point(123, 456));
    harness.check(propertyName, null);

    // check 'name' property (must not be fired)
    propertyName = null;
    comp.setName("Obelix");
    harness.check(propertyName, null);

    // check 'size' property (must not be fired)
    propertyName = null;
    comp.setSize(new Dimension(987, 654));
    harness.check(propertyName, null);

    // check 'visible' property (must not be fired)
    propertyName = null;
    comp.setVisible(true);
    comp.setVisible(false);
    harness.check(propertyName, null);


  }
}
