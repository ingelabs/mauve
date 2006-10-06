/* TestListener.java -- Listens for test progress
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
 * Listens for progress on a test.
 */
public interface TestListener
{
  /**
   * Notifies that a test error has occured.
   *
   * @param test the test
   * @param t the error that was thrown
   */
  void addError(Test test, Throwable t);

  /**
   * Notifies that a test failure has occured.
   *
   * @param test the test
   * @param t the failure
   */
  void addFailure(Test test, AssertionFailedError t);

  /**
   * Notifies that a new test has started.
   * 
   * @param test the test
   */
  void startTest(Test test);

  /**
   * Notifies that a new test has been finished.
   * 
   * @param test the test
   */
  void endTest(Test test);
}
