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

    private static boolean isSorted(float[] array) 
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

	test_quicksort(harness);
    }

    public void test_quicksort(TestHarness harness)
    {
	float[] float_array = { 7.3f, 20000.7f, 343f, 24f, 0.000004f, 1e09f, 44, 44, 44, 44, 44, 44, 44, 44 };
	java.util.Arrays.sort(float_array);
	harness.check(isSorted(float_array));

	float[] float_array2 = { 7.3f, 20000.7f, 21.2f, 343f, 24f, 0.000004f, 1e09f };
	java.util.Arrays.sort(float_array2);
	harness.check(isSorted(float_array2));

	int[] iarray1 = { 1, 2, 3, 4, 5, 6, 7, 8};
	java.util.Arrays.sort(iarray1);
	harness.check(isSorted(iarray1));

	int[] iarray2 = { 8, 7, 6, 5, 4, 3, 2, 1};
	java.util.Arrays.sort(iarray2);
	harness.check(isSorted(iarray2));

	int[] iarray3 = { 8, 7, 6, 5, 11, 3, 2, 1};
	java.util.Arrays.sort(iarray3);
	harness.check(isSorted(iarray3));
    }
}
