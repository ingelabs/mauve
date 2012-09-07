// Test for method java.lang.ClassNotFoundException.getClass().getDeclaredMethods()

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
 * Test for method java.lang.ClassNotFoundException.getClass().getDeclaredMethods()
 */
public class getDeclaredMethods implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        String[] methodNames = new String[] {
            "getCause",
            "getException",
        };
        java.util.Arrays.sort(methodNames);

        String[] methodStrings = new String[] {
            "public java.lang.Throwable java.lang.ClassNotFoundException.getCause()",
            "public java.lang.Throwable java.lang.ClassNotFoundException.getException()",
        };
        java.util.Arrays.sort(methodStrings);

        // create instance of a class ClassNotFoundException
        Object o = new ClassNotFoundException("ClassNotFoundException");

        // get a runtime class of an object "o"
        Class c = o.getClass();

        java.lang.reflect.Method[] methods = c.getDeclaredMethods();
        harness.check(methods.length, 2);

        String methodName;
        String methodString;

        methodName = methods[0].getName();
        methodString = methods[0].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[1].getName();
        methodString = methods[1].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

    }
}

