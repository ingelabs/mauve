// Tags: JDK1.1

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

package gnu.testlet.java.util.Arrays;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.*;

public class sort implements Testlet
{
  private static boolean isSorted(int[] array) 
  {
    for (int i = 1; i < array.length; ++i) {
      if (array[i-1] > array[i])
	return false;
    }
    return true;
  }

  public void test (TestHarness harness)
  {
    int n = 100;
    int bound = 200;
    int times = 10;

    int[] A = new int[n];
    Random rand = new Random();

    int i = 0;
    for (; i < times; ++i)
      {
	for (int j = 0; j < n; ++j) 
	  A[j] = rand.nextInt(bound);

	Arrays.sort(A);

	harness.check (isSorted (A));
      }
  }
}
