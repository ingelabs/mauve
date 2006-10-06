/* Test.java -- The basic interface for a JUnit test
   Copyright (C) 2006 Roman Kennke (kennke@aicas.com)
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

// Tags: not-a-test

package junit.framework;

/**
 * The basic interface for a JUnit test.
 */
public interface Test
{

  /**
   * Returns the number of test cases that will be run by this test.
   *
   * @return the number of test cases that will be run by this test
   */
  int countTestCases();

  /**
   * Runs the test and store the results in <code>result</code>
   *
   * @param result the test result object for storing the results into
   */
  void run(TestResult result);
}
