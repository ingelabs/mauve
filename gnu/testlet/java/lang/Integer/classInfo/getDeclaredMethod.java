// Test for method java.lang.Integer.getClass().getDeclaredMethod()

// Copyright (C) 2012, 2013, 2014, 2015 Pavel Tisnovsky <ptisnovs@redhat.com>

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
// Tags: CompileOptions: -source 1.5

package gnu.testlet.java.lang.Integer.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.Integer;
import java.util.Map;
import java.util.HashMap;



/**
 * Test for method java.lang.Integer.getClass().getDeclaredMethod()
 */
public class getDeclaredMethod implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        // following declared methods should exist
        Map<String, Class[]> methodsThatShouldExist_jdk6 = new HashMap<String, Class[]>();
        methodsThatShouldExist_jdk6.put("numberOfLeadingZeros", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("numberOfTrailingZeros", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("bitCount", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("equals", new Class[] {java.lang.Object.class});
        methodsThatShouldExist_jdk6.put("toString", new Class[] {});
        methodsThatShouldExist_jdk6.put("toString", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk6.put("toString", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("hashCode", new Class[] {});
        methodsThatShouldExist_jdk6.put("reverseBytes", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("compareTo", new Class[] {java.lang.Object.class});
        methodsThatShouldExist_jdk6.put("compareTo", new Class[] {java.lang.Integer.class});
        methodsThatShouldExist_jdk6.put("byteValue", new Class[] {});
        methodsThatShouldExist_jdk6.put("shortValue", new Class[] {});
        methodsThatShouldExist_jdk6.put("intValue", new Class[] {});
        methodsThatShouldExist_jdk6.put("longValue", new Class[] {});
        methodsThatShouldExist_jdk6.put("floatValue", new Class[] {});
        methodsThatShouldExist_jdk6.put("doubleValue", new Class[] {});
        methodsThatShouldExist_jdk6.put("valueOf", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("valueOf", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk6.put("valueOf", new Class[] {java.lang.String.class, int.class});
        methodsThatShouldExist_jdk6.put("toHexString", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("decode", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk6.put("reverse", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("stringSize", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("parseInt", new Class[] {java.lang.String.class, int.class});
        methodsThatShouldExist_jdk6.put("parseInt", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk6.put("toOctalString", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("toBinaryString", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("toUnsignedString", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk6.put("getInteger", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk6.put("getInteger", new Class[] {java.lang.String.class, int.class});
        methodsThatShouldExist_jdk6.put("getInteger", new Class[] {java.lang.String.class, java.lang.Integer.class});
        methodsThatShouldExist_jdk6.put("highestOneBit", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("lowestOneBit", new Class[] {int.class});
        methodsThatShouldExist_jdk6.put("rotateLeft", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk6.put("rotateRight", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk6.put("signum", new Class[] {int.class});

        Map<String, Class[]> methodsThatShouldExist_jdk7 = new HashMap<String, Class[]>();
        methodsThatShouldExist_jdk7.put("numberOfLeadingZeros", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("numberOfTrailingZeros", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("bitCount", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("equals", new Class[] {java.lang.Object.class});
        methodsThatShouldExist_jdk7.put("toString", new Class[] {});
        methodsThatShouldExist_jdk7.put("toString", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk7.put("toString", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("hashCode", new Class[] {});
        methodsThatShouldExist_jdk7.put("reverseBytes", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("compareTo", new Class[] {java.lang.Object.class});
        methodsThatShouldExist_jdk7.put("compareTo", new Class[] {java.lang.Integer.class});
        methodsThatShouldExist_jdk7.put("byteValue", new Class[] {});
        methodsThatShouldExist_jdk7.put("shortValue", new Class[] {});
        methodsThatShouldExist_jdk7.put("intValue", new Class[] {});
        methodsThatShouldExist_jdk7.put("longValue", new Class[] {});
        methodsThatShouldExist_jdk7.put("floatValue", new Class[] {});
        methodsThatShouldExist_jdk7.put("doubleValue", new Class[] {});
        methodsThatShouldExist_jdk7.put("valueOf", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("valueOf", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk7.put("valueOf", new Class[] {java.lang.String.class, int.class});
        methodsThatShouldExist_jdk7.put("toHexString", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("compare", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk7.put("decode", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk7.put("reverse", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("stringSize", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("parseInt", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk7.put("parseInt", new Class[] {java.lang.String.class, int.class});
        methodsThatShouldExist_jdk7.put("getInteger", new Class[] {java.lang.String.class});
        methodsThatShouldExist_jdk7.put("getInteger", new Class[] {java.lang.String.class, int.class});
        methodsThatShouldExist_jdk7.put("getInteger", new Class[] {java.lang.String.class, java.lang.Integer.class});
        methodsThatShouldExist_jdk7.put("highestOneBit", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("lowestOneBit", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("rotateLeft", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk7.put("rotateRight", new Class[] {int.class, int.class});
        methodsThatShouldExist_jdk7.put("signum", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("toBinaryString", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("toOctalString", new Class[] {int.class});
        methodsThatShouldExist_jdk7.put("toUnsignedString", new Class[] {int.class, int.class});

        // get the right map containing method signatures
        Map<String, Class[]> methodsThatShouldExist = getJavaVersion() < 7 ? methodsThatShouldExist_jdk6 : methodsThatShouldExist_jdk7;

        // create instance of a class Integer
        final Object o = new Integer(42);

        // get a runtime class of an object "o"
        final Class c = o.getClass();

        // check if all required methods really exist
        for (Map.Entry<String, Class[]> methodThatShouldExists : methodsThatShouldExist.entrySet()) {
            try {
                java.lang.reflect.Method method = c.getDeclaredMethod(methodThatShouldExists.getKey(), methodThatShouldExists.getValue());
                harness.check(method != null);
                String methodName = method.getName();
                harness.check(methodName != null);
                harness.check(methodName, methodThatShouldExists.getKey());
            }
            catch (Exception e) {
                harness.check(false);
            }
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

