// Tags: JDK1.2

//Copyright (C) 2004  Michael Koch <konqueror@gmx.de>

//Mauve is free software; you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation; either version 2, or (at your option)
//any later version.

//Mauve is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with Mauve; see the file COPYING.  If not, write to
//the Free Software Foundation, 59 Temple Place - Suite 330,
//Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.awt.image.ShortLookupTable;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.image.ShortLookupTable;

public class lookupPixel implements Testlet {

  public void test(TestHarness h)
  {
    short[] data = {105, 104, 103, 102, 101, 100};
    ShortLookupTable t = new ShortLookupTable(100, data);
 
    // check 3-band source with 1-band lookup table
    int[] src = new int[] {100, 101, 102};

    try
      {
	h.checkPoint("dst is 'null'");
	int[] dst = t.lookupPixel(src, null);
	h.check(true, "no exception");
      }
    catch (Exception e)
      {
	h.fail("exception thrown");
      }
  }
}
