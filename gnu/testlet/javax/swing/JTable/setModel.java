// Tags: JDK1.2

// Copyright (C) 2005 David Gilbert <david.gilbert@object-refinery.com>

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

package gnu.testlet.javax.swing.JTable;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * Some checks for the setModel() method in the {@link JTable} class.
 */
public class setModel implements Testlet {

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    test1(harness);
    test2(harness);
  }

  public void test1(TestHarness harness)      
  {
    harness.checkPoint("setModel(TableModel) - test1");
    DefaultTableModel m1 = new DefaultTableModel();
    JTable t = new JTable(m1);
    DefaultTableModel m2 = new DefaultTableModel();
    t.setModel(m2);
    TableModelListener[] listeners1 = m1.getTableModelListeners();
    TableModelListener[] listeners2 = m2.getTableModelListeners();
    harness.check(!Arrays.asList(listeners1).contains(t));
    harness.check(Arrays.asList(listeners2).contains(t));
    
    // try a null argument
    boolean pass = false;
    try
    {
      t.setModel(null);   
    }
    catch (IllegalArgumentException e)
    {
      pass = true;   
    }
    harness.check(pass);
  }

  /**
   * This test checks that the autoCreateColumnsFromModel flag is correctly
   * observed.
   * 
   * @param harness  the test harness.
   */
  public void test2(TestHarness harness)      
  {
    harness.checkPoint("setModel(TableModel) - test2");
    DefaultTableModel m1 = new DefaultTableModel(2, 3);
    JTable t = new JTable(m1);
    TableColumnModel tcm = t.getColumnModel();
    tcm.getColumn(1).setModelIndex(0);
    tcm.getColumn(0).setModelIndex(1);
    harness.check(t.getColumnCount(), 3);
    harness.check(t.getColumnName(0), "B");
    harness.check(t.getColumnName(1), "A");
    harness.check(t.getColumnName(2), "C");
    
    DefaultTableModel m2 = new DefaultTableModel(new String[] {"AA", "BB"}, 1);
    t.setModel(m2);
    harness.check(t.getColumnCount(), 2);
    harness.check(t.getColumnName(0), "AA");
    harness.check(t.getColumnName(1), "BB");
   
    tcm = t.getColumnModel();
    tcm.getColumn(1).setModelIndex(0);
    tcm.getColumn(0).setModelIndex(1);    
    t.setAutoCreateColumnsFromModel(false);
    DefaultTableModel m3 = new DefaultTableModel(
            new String[] {"CC", "DD", "EE"}, 1);
    t.setModel(m3);
    harness.check(t.getColumnCount(), 2);
    harness.check(t.getColumnName(0), "DD");
    harness.check(t.getColumnName(1), "CC");
  }
}
