// Tags: JDK1.2

// Copyright (C) 2004 David Gilbert <david.gilbert@object-refinery.com>

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
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.util.Collections;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class binarySearch implements Testlet
{

  public void test(TestHarness harness) 
  {
    // a simple test
    List list1 = new ArrayList();
    list1.add("B"); list1.add("C"); list1.add("D"); 
    int i = Collections.binarySearch(list1, "C");
    harness.check(i == 1);
    i = Collections.binarySearch(list1, "E");
    harness.check(i == -4);
    i = Collections.binarySearch(list1, "A");
    harness.check(i == -1);
    // test for a known bug (10447)
    testBug10447(harness);
  }
  
  /**
   * A test for bug report 10447.
   * 
   * @param harness  the test harness.
   */
  private void testBug10447(TestHarness harness) 
  {
    List list = new LinkedList();
    list.add("A"); list.add("B"); list.add("C"); list.add("D"); 
    list.add("E"); list.add("F"); list.add("G"); list.add("H"); 
    list.add("I"); list.add("J"); list.add("K"); list.add("L"); 
    list.add("M"); list.add("N"); list.add("O"); list.add("P"); 
    
    // this works
    int i = Collections.binarySearch(list, "E");
    harness.check(i == 4);
    
    // this doesn't (bug seems to need at least 17 items to trigger)    
    list.add("Q"); 
    i = Collections.binarySearch(list, "E");
    harness.check(i, 4);
      
    // but all is fine for ArrayList
    List list2 = new ArrayList();
    list2.add("A"); list2.add("B"); list2.add("C"); list2.add("D"); 
    list2.add("E"); list2.add("F"); list2.add("G"); list2.add("H"); 
    list2.add("I"); list2.add("J"); list2.add("K"); list2.add("L"); 
    list2.add("M"); list2.add("N"); list2.add("O"); list2.add("P"); 
    
    // this works
    i = Collections.binarySearch(list2, "E");
    harness.check(i == 4);
    
    // and this does too   
    list2.add("Q"); 
    i = Collections.binarySearch(list2, "E");
    harness.check(i == 4);
  }
  
}
