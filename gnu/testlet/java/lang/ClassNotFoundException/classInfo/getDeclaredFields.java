// Test for method java.lang.ClassNotFoundException.getClass().getDeclaredFields()

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
 * Test for method java.lang.ClassNotFoundException.getClass().getDeclaredFields()
 */
public class getDeclaredFields implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        String[] fieldNames = new String[] {
            "serialVersionUID",
            "ex",
        };
        java.util.Arrays.sort(fieldNames);

        String[] fieldStrings = new String[] {
            "private static final long java.lang.ClassNotFoundException.serialVersionUID",
            "private java.lang.Throwable java.lang.ClassNotFoundException.ex",
        };
        java.util.Arrays.sort(fieldStrings);

        // create instance of a class ClassNotFoundException
        Object o = new ClassNotFoundException("ClassNotFoundException");

        // get a runtime class of an object "o"
        Class c = o.getClass();

        java.lang.reflect.Field[] fields = c.getDeclaredFields();
        harness.check(fields.length, 2);

        String fieldName;
        String fieldString;

        fieldName = fields[0].getName();
        fieldString = fields[0].toString();
        harness.check(java.util.Arrays.binarySearch(fieldNames, fieldName) >= 0);
        harness.check(java.util.Arrays.binarySearch(fieldStrings, fieldString) >= 0);

        fieldName = fields[1].getName();
        fieldString = fields[1].toString();
        harness.check(java.util.Arrays.binarySearch(fieldNames, fieldName) >= 0);
        harness.check(java.util.Arrays.binarySearch(fieldStrings, fieldString) >= 0);

    }
}

