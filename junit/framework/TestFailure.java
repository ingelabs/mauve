/* TestFailure.java -- Wraps a test failure
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

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Wraps a failed test together with the failure.
 */
public class TestFailure
{

  /**
   * The test that failed.
   */
  protected Test fFailedTest;

  /**
   * The exception that has been thrown.
   */
  protected Throwable fThrownException;

  /**
   * Creates a new TestFailure.
   *
   * @param failedTest the failed test
   * @param thrownException the thrown exception
   */
  public TestFailure(Test failedTest, Throwable thrownException)
  {
    fFailedTest = failedTest;
    fThrownException = thrownException;
  }

  /**
   * Returns the message of the thrown exception.
   *
   * @return the message of the thrown exception
   */
  public String exceptionMessage()
  {
    return fThrownException.getMessage();
  }

  /**
   * Returns the failed test.
   *
   * @return the failed test
   */
  public Test failedTest()
  {
    return fFailedTest;
  }

  /**
   * Returns <code>true</code>, if this is a failure (that is, the exception is
   * an instance of {@link AssertionFailedError}, <code>false</code>
   * otherwise (in case of error for instance).
   *
   * @return <code>true</code>, if this is a failure, <code>false</code>
   *         otherwise
   */
  public boolean isFailure()
  {
    return fThrownException instanceof AssertionFailedError;
  }

  /**
   * Returns a string representation of this TestFailure object.
   *
   * @return a string representation of this TestFailure object
   */
  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append(fFailedTest);
    str.append(": ");
    str.append(fThrownException.getMessage());
    return str.toString();
  }

  /**
   * Returns the stacktrace of the exception as string.
   *
   * @return the stacktrace of the exception as string
   */
  public String trace()
  {
    StringWriter w = new StringWriter();
    PrintWriter p = new PrintWriter(w);
    fThrownException.printStackTrace(p);
    return w.getBuffer().toString();
  }
}
