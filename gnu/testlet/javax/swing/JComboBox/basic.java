// Tags: JDK1.2

// Copyright (C) 2004 Audrius Meskauskas <audriusa@bluewin.ch>

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

package gnu.testlet.javax.swing.JComboBox;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

/**
 * The the basic JComboBox features (including listeners).
 * This test must also work when the component is not displayed.
 * @author Audrius Meskauskas (AudriusA@Bluewin.ch)
 */
public class basic
  implements Testlet, ActionListener, ItemListener
{
  static String COMMAND = "mauve";
  static String UNASSIGNED = "<unassigned>";
  String command = UNASSIGNED;
  StringBuffer stateChangeEvents = new StringBuffer();

  public void test(TestHarness harness)
  {
    JComboBox box = createBox(harness);
    testSelectionByIndex(harness, box);
    testSelectionByName(harness, box);
    testItemRemoving(harness, box);

    testAddingItems(harness, box);
  }

  private void testAddingItems(TestHarness harness, JComboBox box) {
    harness.checkPoint("Adding items");
    box.addItem("x");
    box.addItem("y");
    harness.check(box.getItemCount(), 2, "adding items");
    harness.check(box.getItemAt(0), "x", "adding items");
    harness.check(box.getItemAt(1), "y", "adding items");
  }

  private void testItemRemoving(TestHarness harness, JComboBox box)
  {
    harness.checkPoint("Removing items");
    box.removeItem("b");
    harness.check(box.getItemCount(), 2, "removing by name");
    harness.check((String) box.getItemAt(0) + (String) box.getItemAt(1), "ac",
                  "removing by name"
                 );

    box.removeItemAt(0);
    harness.check(box.getItemCount(), 1, "removing by index");
    harness.check(box.getItemAt(0), "c", "removing by index");

    box.removeAllItems();
    harness.check(box.getItemCount(), 0, "Removing all items.");
  }

  private JComboBox createBox(TestHarness harness)
  {
    harness.checkPoint("Creating");

    String array[] = new String[] { "a", "b", "c" };

    JComboBox box = new JComboBox(array);

    box.addActionListener(this);
    box.addItemListener(this);

    box.setActionCommand(COMMAND);

    harness.check(box.getActionCommand(), COMMAND, "action command");
    harness.check(box.getItemCount(), 3, "item count");

    testGetItemAt(harness, array, box);

    return box;
  }

  private void testGetItemAt(TestHarness harness, String array[], JComboBox box)
  {
    harness.checkPoint("getItemAt");
    for (int i = 0; i < array.length; i++)
      {
        harness.check(box.getItemAt(i).toString(), array [ i ],
                      "getItemAt(" + i + ") " + array [ i ] + "!=" +
                      box.getItemAt(i)
                     );
      }
  }

  private void testSelectionByName(TestHarness harness, JComboBox box)
  {
    harness.checkPoint("s.b.n");
    command = UNASSIGNED;
    stateChangeEvents.setLength(0);

    box.setSelectedItem("a");

    harness.check(command, COMMAND);
    harness.check(box.getSelectedIndex(), 0, "selected index in s.b.n");
    harness.check(box.getSelectedItem(), "a", "selected item in s.b.n");

    // c was previously selected by testSelectionByIndex.
    harness.check(stateChangeEvents.toString(), " -c +a",
                  "item events in s.b.i " + stateChangeEvents
                 );
  }

  private void testSelectionByIndex(TestHarness harness, JComboBox box)
  {
    harness.checkPoint("s.b.i");
    command = UNASSIGNED;
    stateChangeEvents.setLength(0);

    box.setSelectedIndex(1);

    harness.check(command, COMMAND, "action listener in s.b.i");
    harness.check(box.getSelectedIndex(), 1, "selected index in s.b.i.");
    harness.check(box.getSelectedItem(), "b", "selected item in s.b.i.");

    for (int i = 0; i < 3; i++)
      {
        box.setSelectedIndex(i);
      }

    String events = stateChangeEvents.toString();
    harness.check(events.startsWith(" -a"),
                  "Missing event on deselecting the default item. " +
                  "Bug in 1.3, fixed in 1.4."
                 );

    harness.check(events, " -a +b -b +a -a +b -b +c",
                  "item events in s.b.i " + stateChangeEvents
                 );

    command = UNASSIGNED;
  }

  public void actionPerformed(ActionEvent e)
  {
    command = e.getActionCommand();
  }

  public void itemStateChanged(ItemEvent ie)
  {
    if (ie.getStateChange() == ie.SELECTED)
      stateChangeEvents.append(" +" + ie.getItem());
    else
      stateChangeEvents.append(" -" + ie.getItem());
  }
}
