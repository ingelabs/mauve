// Test for method java.lang.ClassNotFoundException.getClass().getConstructors()

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

package gnu.testlet.java.lang.ClassNotFoundException.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.ClassNotFoundException;
import java.lang.reflect.Modifier;



/**
 * Test for method java.lang.ClassNotFoundException.getClass().getConstructors()
 */
public class getConstructors implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        String[] constructorNames = new String[] {
            "java.lang.ClassNotFoundException",
            "java.lang.ClassNotFoundException",
            "java.lang.ClassNotFoundException",
        };
        java.util.Arrays.sort(constructorNames);

        String[] constructorStrings = new String[] {
            "public java.lang.ClassNotFoundException()",
            "public java.lang.ClassNotFoundException(java.lang.String)",
            "public java.lang.ClassNotFoundException(java.lang.String,java.lang.Throwable)",
        };
        java.util.Arrays.sort(constructorStrings);

        // create instance of a class ClassNotFoundException
        Object o = new ClassNotFoundException("ClassNotFoundException");

        // get a runtime class of an object "o"
        Class c = o.getClass();

        java.lang.reflect.Constructor[] constructors = c.getConstructors();
        harness.check(constructors.length, 3);

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

    }
}

