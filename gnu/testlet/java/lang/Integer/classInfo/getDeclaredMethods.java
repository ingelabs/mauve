// Test for method java.lang.Integer.getClass().getDeclaredMethods()

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

package gnu.testlet.java.lang.Integer.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.Integer;
import java.util.Map;
import java.util.HashMap;



/**
 * Test for method java.lang.Integer.getClass().getDeclaredMethods()
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
        // map of declared methods which should exists
        Map<String, String> testedDeclaredMethods = null;

        // map of declared methods for (Open)JDK6
        Map<String, String> testedDeclaredMethods_jdk6 = new HashMap<String, String>();

        // map of declared methods for (Open)JDK7
        Map<String, String> testedDeclaredMethods_jdk7 = new HashMap<String, String>();

        // map for methods declared in (Open)JDK6
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.numberOfLeadingZeros(int)", "numberOfLeadingZeros");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.numberOfTrailingZeros(int)", "numberOfTrailingZeros");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.bitCount(int)", "bitCount");
        testedDeclaredMethods_jdk6.put("public boolean java.lang.Integer.equals(java.lang.Object)", "equals");
        testedDeclaredMethods_jdk6.put("public java.lang.String java.lang.Integer.toString()", "toString");
        testedDeclaredMethods_jdk6.put("public static java.lang.String java.lang.Integer.toString(int,int)", "toString");
        testedDeclaredMethods_jdk6.put("public static java.lang.String java.lang.Integer.toString(int)", "toString");
        testedDeclaredMethods_jdk6.put("public int java.lang.Integer.hashCode()", "hashCode");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.reverseBytes(int)", "reverseBytes");
        testedDeclaredMethods_jdk6.put("public int java.lang.Integer.compareTo(java.lang.Object)", "compareTo");
        testedDeclaredMethods_jdk6.put("public int java.lang.Integer.compareTo(java.lang.Integer)", "compareTo");
        testedDeclaredMethods_jdk6.put("public byte java.lang.Integer.byteValue()", "byteValue");
        testedDeclaredMethods_jdk6.put("public short java.lang.Integer.shortValue()", "shortValue");
        testedDeclaredMethods_jdk6.put("public int java.lang.Integer.intValue()", "intValue");
        testedDeclaredMethods_jdk6.put("public long java.lang.Integer.longValue()", "longValue");
        testedDeclaredMethods_jdk6.put("public float java.lang.Integer.floatValue()", "floatValue");
        testedDeclaredMethods_jdk6.put("public double java.lang.Integer.doubleValue()", "doubleValue");
        testedDeclaredMethods_jdk6.put("public static java.lang.Integer java.lang.Integer.valueOf(int)", "valueOf");
        testedDeclaredMethods_jdk6.put("public static java.lang.Integer java.lang.Integer.valueOf(java.lang.String) throws java.lang.NumberFormatException", "valueOf");
        testedDeclaredMethods_jdk6.put("public static java.lang.Integer java.lang.Integer.valueOf(java.lang.String,int) throws java.lang.NumberFormatException", "valueOf");
        testedDeclaredMethods_jdk6.put("public static java.lang.String java.lang.Integer.toHexString(int)", "toHexString");
        testedDeclaredMethods_jdk6.put("static void java.lang.Integer.getChars(int,int,char[])", "getChars");
        testedDeclaredMethods_jdk6.put("public static java.lang.Integer java.lang.Integer.decode(java.lang.String) throws java.lang.NumberFormatException", "decode");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.reverse(int)", "reverse");
        testedDeclaredMethods_jdk6.put("static int java.lang.Integer.stringSize(int)", "stringSize");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.parseInt(java.lang.String,int) throws java.lang.NumberFormatException", "parseInt");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.parseInt(java.lang.String) throws java.lang.NumberFormatException", "parseInt");
        testedDeclaredMethods_jdk6.put("public static java.lang.String java.lang.Integer.toOctalString(int)", "toOctalString");
        testedDeclaredMethods_jdk6.put("public static java.lang.String java.lang.Integer.toBinaryString(int)", "toBinaryString");
        testedDeclaredMethods_jdk6.put("private static java.lang.String java.lang.Integer.toUnsignedString(int,int)", "toUnsignedString");
        testedDeclaredMethods_jdk6.put("public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String)", "getInteger");
        testedDeclaredMethods_jdk6.put("public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String,int)", "getInteger");
        testedDeclaredMethods_jdk6.put("public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String,java.lang.Integer)", "getInteger");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.highestOneBit(int)", "highestOneBit");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.lowestOneBit(int)", "lowestOneBit");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.rotateLeft(int,int)", "rotateLeft");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.rotateRight(int,int)", "rotateRight");
        testedDeclaredMethods_jdk6.put("public static int java.lang.Integer.signum(int)", "signum");

        // map for methods declared in (Open)JDK7
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.numberOfLeadingZeros(int)", "numberOfLeadingZeros");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.numberOfTrailingZeros(int)", "numberOfTrailingZeros");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.bitCount(int)", "bitCount");
        testedDeclaredMethods_jdk7.put("public boolean java.lang.Integer.equals(java.lang.Object)", "equals");
        testedDeclaredMethods_jdk7.put("public java.lang.String java.lang.Integer.toString()", "toString");
        testedDeclaredMethods_jdk7.put("public static java.lang.String java.lang.Integer.toString(int,int)", "toString");
        testedDeclaredMethods_jdk7.put("public static java.lang.String java.lang.Integer.toString(int)", "toString");
        testedDeclaredMethods_jdk7.put("public int java.lang.Integer.hashCode()", "hashCode");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.reverseBytes(int)", "reverseBytes");
        testedDeclaredMethods_jdk7.put("public int java.lang.Integer.compareTo(java.lang.Object)", "compareTo");
        testedDeclaredMethods_jdk7.put("public int java.lang.Integer.compareTo(java.lang.Integer)", "compareTo");
        testedDeclaredMethods_jdk7.put("public byte java.lang.Integer.byteValue()", "byteValue");
        testedDeclaredMethods_jdk7.put("public short java.lang.Integer.shortValue()", "shortValue");
        testedDeclaredMethods_jdk7.put("public int java.lang.Integer.intValue()", "intValue");
        testedDeclaredMethods_jdk7.put("public long java.lang.Integer.longValue()", "longValue");
        testedDeclaredMethods_jdk7.put("public float java.lang.Integer.floatValue()", "floatValue");
        testedDeclaredMethods_jdk7.put("public double java.lang.Integer.doubleValue()", "doubleValue");
        testedDeclaredMethods_jdk7.put("public static java.lang.Integer java.lang.Integer.valueOf(int)", "valueOf");
        testedDeclaredMethods_jdk7.put("public static java.lang.Integer java.lang.Integer.valueOf(java.lang.String) throws java.lang.NumberFormatException", "valueOf");
        testedDeclaredMethods_jdk7.put("public static java.lang.Integer java.lang.Integer.valueOf(java.lang.String,int) throws java.lang.NumberFormatException", "valueOf");
        testedDeclaredMethods_jdk7.put("public static java.lang.String java.lang.Integer.toHexString(int)", "toHexString");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.compare(int,int)", "compare");
        testedDeclaredMethods_jdk7.put("public static java.lang.Integer java.lang.Integer.decode(java.lang.String) throws java.lang.NumberFormatException", "decode");
        testedDeclaredMethods_jdk7.put("static void java.lang.Integer.getChars(int,int,char[])", "getChars");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.reverse(int)", "reverse");
        testedDeclaredMethods_jdk7.put("static int java.lang.Integer.stringSize(int)", "stringSize");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.parseInt(java.lang.String) throws java.lang.NumberFormatException", "parseInt");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.parseInt(java.lang.String,int) throws java.lang.NumberFormatException", "parseInt");
        testedDeclaredMethods_jdk7.put("public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String)", "getInteger");
        testedDeclaredMethods_jdk7.put("public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String,int)", "getInteger");
        testedDeclaredMethods_jdk7.put("public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String,java.lang.Integer)", "getInteger");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.highestOneBit(int)", "highestOneBit");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.lowestOneBit(int)", "lowestOneBit");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.rotateLeft(int,int)", "rotateLeft");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.rotateRight(int,int)", "rotateRight");
        testedDeclaredMethods_jdk7.put("public static int java.lang.Integer.signum(int)", "signum");
        testedDeclaredMethods_jdk7.put("public static java.lang.String java.lang.Integer.toBinaryString(int)", "toBinaryString");
        testedDeclaredMethods_jdk7.put("public static java.lang.String java.lang.Integer.toOctalString(int)", "toOctalString");
        testedDeclaredMethods_jdk7.put("private static java.lang.String java.lang.Integer.toUnsignedString(int,int)", "toUnsignedString");

        // create instance of a class Integer
        final Object o = new Integer(42);

        // get a runtime class of an object "o"
        final Class c = o.getClass();

        // get the right map containing method signatures
        testedDeclaredMethods = getJavaVersion() < 7 ? testedDeclaredMethods_jdk6 : testedDeclaredMethods_jdk7;

        // get all declared methods for this class
        java.lang.reflect.Method[] declaredMethods = c.getDeclaredMethods();

        // expected number of declared methods
        final int expectedNumberOfDeclaredMethods = testedDeclaredMethods.size();

        // basic check for a number of declared methods
        harness.check(declaredMethods.length, expectedNumberOfDeclaredMethods);

        // check if all declared methods exist
        for (java.lang.reflect.Method declaredMethod : declaredMethods) {
            // method name should consists of package name + class name
            String methodName = declaredMethod.getName();
            // modifiers + package + method name + parameter types
            String methodString = declaredMethod.toString().replaceAll(" native ", " ");
            harness.check(testedDeclaredMethods.containsKey(methodString));
            harness.check(testedDeclaredMethods.get(methodString), methodName);
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

