// Test for method java.lang.Package.getClass().getConstructors()

// Copyright (C) 2012, 2013, 2014, 2015, 2016 Pavel Tisnovsky <ptisnovs@redhat.com>

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

// Tags: JDK1.5

package gnu.testlet.java.lang.Package.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.Package;
import java.util.Map;
import java.util.HashMap;



/**
 * Test for method java.lang.Package.getClass().getConstructors()
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
        // map of constructors which should exists
        Map<String, String> testedConstructors = null;

        // map of constructors for (Open)JDK6
        Map<String, String> testedConstructors_jdk6 = new HashMap<String, String>();

        // map of constructors for (Open)JDK7
        Map<String, String> testedConstructors_jdk7 = new HashMap<String, String>();

        // map for constructors declared in (Open)JDK6
        // --- empty ---

        // map for constructors declared in (Open)JDK7
        // --- empty ---

        // create instance of a class Package
        final Object o = Package.getPackage("java.lang");

        // get a runtime class of an object "o"
        final Class c = o.getClass();

        // get the right map containing constructor signatures
        testedConstructors = getJavaVersion() < 7 ? testedConstructors_jdk6 : testedConstructors_jdk7;

        // get all constructors for this class
        java.lang.reflect.Constructor[] constructors = c.getConstructors();

        // expected number of constructors
        final int expectedNumberOfConstructors = testedConstructors.size();

        // basic check for a number of constructors
        harness.check(constructors.length, expectedNumberOfConstructors);

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

