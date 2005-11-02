// Tags: JDK1.5
// Uses: DisabledEventQueue

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

package gnu.testlet.java.awt.Container;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * This checks tests if the addImpl method notfies container listeners when
 * a container is not showing and if the notification is sent over the event
 * queue or delivered directly. The test shows that the event is also sent when
 * the container is not showing and that the event is delivered directly.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class addImpl implements Testlet
{

  /**
   * A container listener for test.
   *
   * @author Roman Kennke (kennke@aicas.com)
   */
  class TestContainerListener implements ContainerListener
  {

    /**
     * Receives notification when a component was added to the container.
     *
     * @param event the container event
     */
    public void componentAdded(ContainerEvent event)
    {
      componentAddedCalled = true;
    }

    /**
     * Receives notification when a component was removed from the container.
     *
     * @param event the container event
     */
    public void componentRemoved(ContainerEvent event)
    {
      // TODO Auto-generated method stub
      
    }
    
  }

  /**
   * True when the componentAdded method in the container listener was called.
   */
  boolean componentAddedCalled;

  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    // We disable the event queue so we can check if this event is delivered
    // via the event queue or not.
    Toolkit.getDefaultToolkit().getSystemEventQueue()
    .push(new DisabledEventQueue());

    Container c = new Container();
    c.addContainerListener(new TestContainerListener());
    // Pre-condition check.
    harness.check(c.isShowing(), false);
    componentAddedCalled = false;
    c.add(new Component(){});
    harness.check(componentAddedCalled, true);
  }

}
