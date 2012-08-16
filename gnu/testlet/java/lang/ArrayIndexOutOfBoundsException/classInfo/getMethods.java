// Test for method java.lang.ArrayIndexOutOfBoundsException.getClass().getMethods()

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

package gnu.testlet.java.lang.ArrayIndexOutOfBoundsException.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.reflect.Modifier;



/**
 * Test for method java.lang.ArrayIndexOutOfBoundsException.getClass().getMethods()
 */
public class getMethods implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        String[] methodNames = new String[] {
            "printStackTrace",
            "printStackTrace",
            "printStackTrace",
            "fillInStackTrace",
            "getCause",
            "initCause",
            "toString",
            "getMessage",
            "getLocalizedMessage",
            "getStackTrace",
            "setStackTrace",
            "wait",
            "wait",
            "wait",
            "hashCode",
            "getClass",
            "equals",
            "notify",
            "notifyAll",
        };
        java.util.Arrays.sort(methodNames);

        String[] methodStrings = new String[] {
            "public void java.lang.Throwable.printStackTrace()",
            "public void java.lang.Throwable.printStackTrace(java.io.PrintStream)",
            "public void java.lang.Throwable.printStackTrace(java.io.PrintWriter)",
            "public synchronized native java.lang.Throwable java.lang.Throwable.fillInStackTrace()",
            "public java.lang.Throwable java.lang.Throwable.getCause()",
            "public synchronized java.lang.Throwable java.lang.Throwable.initCause(java.lang.Throwable)",
            "public java.lang.String java.lang.Throwable.toString()",
            "public java.lang.String java.lang.Throwable.getMessage()",
            "public java.lang.String java.lang.Throwable.getLocalizedMessage()",
            "public java.lang.StackTraceElement[] java.lang.Throwable.getStackTrace()",
            "public void java.lang.Throwable.setStackTrace(java.lang.StackTraceElement[])",
            "public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException",
            "public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException",
            "public final void java.lang.Object.wait() throws java.lang.InterruptedException",
            "public native int java.lang.Object.hashCode()",
            "public final native java.lang.Class java.lang.Object.getClass()",
            "public boolean java.lang.Object.equals(java.lang.Object)",
            "public final native void java.lang.Object.notify()",
            "public final native void java.lang.Object.notifyAll()",
        };
        java.util.Arrays.sort(methodStrings);

        // create instance of a class ArrayIndexOutOfBoundsException
        Object o = new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException");

        // get a runtime class of an object "o"
        Class c = o.getClass();

        java.lang.reflect.Method[] methods = c.getMethods();
        harness.check(methods.length, 19);

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

        methodName = methods[2].getName();
        methodString = methods[2].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[3].getName();
        methodString = methods[3].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[4].getName();
        methodString = methods[4].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[5].getName();
        methodString = methods[5].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[6].getName();
        methodString = methods[6].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[7].getName();
        methodString = methods[7].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[8].getName();
        methodString = methods[8].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[9].getName();
        methodString = methods[9].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[10].getName();
        methodString = methods[10].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[11].getName();
        methodString = methods[11].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[12].getName();
        methodString = methods[12].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[13].getName();
        methodString = methods[13].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[14].getName();
        methodString = methods[14].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[15].getName();
        methodString = methods[15].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[16].getName();
        methodString = methods[16].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[17].getName();
        methodString = methods[17].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

        methodName = methods[18].getName();
        methodString = methods[18].toString();
        harness.check(java.util.Arrays.binarySearch(methodNames, methodName) >= 0);
        harness.check(java.util.Arrays.binarySearch(methodStrings, methodString) >= 0);

    }
}

