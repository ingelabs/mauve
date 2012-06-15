// Test if constructor is working properly for a class java.lang.NoSuchFieldError

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

package gnu.testlet.java.lang.NoSuchFieldError;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.NoSuchFieldError;



/**
 * Test if constructor is working properly for a class java.lang.NoSuchFieldError
 */
public class constructor implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        NoSuchFieldError error1 = new NoSuchFieldError();
        harness.check(error1 != null);
        harness.check(error1.toString(), "java.lang.NoSuchFieldError");

        NoSuchFieldError error2 = new NoSuchFieldError("nothing happens");
        harness.check(error2 != null);
        harness.check(error2.toString(), "java.lang.NoSuchFieldError: nothing happens");
    }
}

