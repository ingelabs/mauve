// Test for method java.lang.AssertionError.getClass().getDeclaredConstructors()

// Copyright (C) 2012 Pavel Tisnovsky <ptisnovs@redhat.com>

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
// the Free Software Foundation, Inc., 51 Franklin Street,
// Fifth Floor, Boston, MA 02110-1301 USA.

package gnu.testlet.java.lang.AssertionError.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.AssertionError;
import java.lang.reflect.Modifier;



/**
 * Test for method java.lang.AssertionError.getClass().getDeclaredConstructors()
 */
public class getDeclaredConstructors implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        String[] constructorNames = new String[] {
            "java.lang.AssertionError",
            "java.lang.AssertionError",
            "java.lang.AssertionError",
            "java.lang.AssertionError",
            "java.lang.AssertionError",
            "java.lang.AssertionError",
            "java.lang.AssertionError",
            "java.lang.AssertionError",
            "java.lang.AssertionError",
        };
        java.util.Arrays.sort(constructorNames);

        String[] constructorStrings = new String[] {
            "public java.lang.AssertionError()",
            "private java.lang.AssertionError(java.lang.String)",
            "public java.lang.AssertionError(java.lang.Object)",
            "public java.lang.AssertionError(boolean)",
            "public java.lang.AssertionError(char)",
            "public java.lang.AssertionError(int)",
            "public java.lang.AssertionError(long)",
            "public java.lang.AssertionError(float)",
            "public java.lang.AssertionError(double)",
        };
        java.util.Arrays.sort(constructorStrings);

        // create instance of a class AssertionError
        Object o = new AssertionError();

        // get a runtime class of an object "o"
        Class c = o.getClass();

        java.lang.reflect.Constructor[] constructors = c.getDeclaredConstructors();
        harness.check(constructors.length, 9);

        String constructorName;
        String constructorString;

        constructorName = constructors[0].getName();
        constructorString = constructors[0].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[1].getName();
        constructorString = constructors[1].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[2].getName();
        constructorString = constructors[2].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[3].getName();
        constructorString = constructors[3].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[4].getName();
        constructorString = constructors[4].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[5].getName();
        constructorString = constructors[5].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[6].getName();
        constructorString = constructors[6].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[7].getName();
        constructorString = constructors[7].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

        constructorName = constructors[8].getName();
        constructorString = constructors[8].toString();
        harness.check(java.util.Arrays.binarySearch(constructorNames, constructorName) >= 0);
        harness.check(java.util.Arrays.binarySearch(constructorStrings, constructorString) >= 0);

    }
}

