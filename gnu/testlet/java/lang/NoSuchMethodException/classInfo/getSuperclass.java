// Test for method java.lang.NoSuchMethodException.getClass().getSuperclass()

// Copyright (C) 2012, 2013, 2014 Pavel Tisnovsky <ptisnovs@redhat.com>

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

package gnu.testlet.java.lang.NoSuchMethodException.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.NoSuchMethodException;



/**
 * Test for method java.lang.NoSuchMethodException.getClass().getSuperclass()
 */
public class getSuperclass implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        // create instance of a class Double
        Object o = new NoSuchMethodException("NoSuchMethodException");

        // get a runtime class of an object "o"
        Class c = o.getClass();

        Class superClass = c.getSuperclass();

        final int javaVersion = getJavaVersion();
        if (javaVersion == 6) {
            harness.check(superClass.getName(), "java.lang.Exception");
        }
        if (javaVersion == 7) {
            harness.check(superClass.getName(), "java.lang.ReflectiveOperationException");
        }
    }

    /**
     * Returns version of Java. The input could have the following form: "1.7.0_06"
     * and we are interested only in "7" in this case.
     * 
     * @return Java version
     */
    protected int getJavaVersion() {
        String javaVersionStr = System.getProperty("java.version");
        String[] parts = javaVersionStr.split("\\.");
        return Integer.parseInt(parts[1]);
    }
}

