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

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

/**
 * Some checks for the constructors in the {@link JTable} class.
 */
public class constructors implements Testlet {

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    constructor1(harness);
    constructor2(harness);
    constructor3(harness);
    constructor4(harness);
    constructor5(harness);
    constructor6(harness);
    constructor7(harness);
  }
  
  public void constructor1(TestHarness harness) 
  {
    harness.checkPoint("JTable()");
    JTable table = new JTable();
    harness.check(table.getAutoCreateColumnsFromModel(), true);
  }

  public void constructor2(TestHarness harness) 
  {
    harness.checkPoint("JTable(int, int)");
    JTable table = new JTable(1, 2);
    harness.check(table.getAutoCreateColumnsFromModel(), true);
    
    table = new JTable(0, 2);
    table = new JTable(1, 0);
    
    // negative rows
    boolean pass = false;
    try
    {
      /*JTable t =*/ new JTable(-1, 2);
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // negative columns
    pass = false;
    try
    {
      /*JTable t =*/ new JTable(1, -2);
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);
  }

  public void constructor3(TestHarness harness) 
  {
    harness.checkPoint("JTable(Object[][], Object[])");

    // try null data
    boolean pass = false;
    try
    {
      /*JTable t1 =*/ new JTable(null, new String[] {"AA", "BB"});
    }
    catch (NullPointerException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // try null column identifiers
    pass = false;
    try
    {
      /*JTable t2 =*/ new JTable(new String[][] {{"AA", "BB"}}, null);      
    }
    catch (NullPointerException e)
    {
      pass = true;
    }
    harness.check(pass);
  }

  public void constructor4(TestHarness harness) 
  {
    harness.checkPoint("JTable(TableModel)");
    JTable t = new JTable(new DefaultTableModel());
    harness.check(t.getAutoCreateColumnsFromModel(), true);
    
    // a null model is acceptable, it gets replaced with a default model
    t = new JTable(null);
    harness.check(t.getModel() != null);
  }

  public void constructor5(TestHarness harness) 
  {
    harness.checkPoint("JTable(TableModel, TableColumnModel)");
    JTable t = new JTable(new DefaultTableModel(), null);
    harness.check(t.getAutoCreateColumnsFromModel(), true);
    
    t = new JTable(new DefaultTableModel(), new DefaultTableColumnModel());
    harness.check(t.getAutoCreateColumnsFromModel(), false);
  }

  public void constructor6(TestHarness harness) 
  {
    harness.checkPoint("JTable(TableModel, TableColumnModel, ListSelectionModel)");
    JTable t = new JTable(new DefaultTableModel(), null, null);
    harness.check(t.getAutoCreateColumnsFromModel(), true);
    
    t = new JTable(new DefaultTableModel(), new DefaultTableColumnModel(), null);
    harness.check(t.getAutoCreateColumnsFromModel(), false);
  }

  public void constructor7(TestHarness harness) 
  {
    harness.checkPoint("JTable(Vector, Vector)");
  }

}

