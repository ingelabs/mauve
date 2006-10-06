/* TestResult.java -- Collects test results
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Collects the results of a test run.
 */
public class TestResult
{

  /**
   * The errors from the test run.
   */
  protected List fErrors;

  /**
   * The failures from the test run.
   */
  protected List fFailures;

  /**
   * The test listeners.
   */
  protected List fListeners;

  /**
   * The number of tests that have been run.
   */
  protected int fRunTests;

  /**
   * Indicates if the test run should stop.
   */
  private boolean fStop;

  /**
   * Creates a new TestResult object.
   */
  public TestResult()
  {
    fErrors = new ArrayList();
    fFailures = new ArrayList();
    fListeners = new ArrayList();
    fRunTests = 0;
    fStop = false;
  }

  /**
   * Runs the specified TestCase.
   *
   * @param test the test case to run
   */
  protected void run(final TestCase test)
  {
    startTest(test);
    Protectable protectable = new Protectable()
    {
      public void protect()
        throws Throwable
      {
        test.runBare();
      }
    };
    runProtected(test, protectable);
    endTest(test);
  }

  /**
   * Runs a test in a protected environment.
   *
   * @param test the test to run
   * @param p the protectable
   */
  public void runProtected(final TestCase test, Protectable p)
  {
    try
      {
        p.protect();
      }
    catch (AssertionFailedError e)
      {
        addFailure(test, e);
      }
    catch (ThreadDeath e)
      {
        throw e;
      }
    catch (Throwable e)
      {
        addError(test, e);
      }
  }

  /**
   * Starts the specified test. This counts the tests and informs
   * interested listeners.
   *
   * @param test the test to start
   */
  public void startTest(Test test)
  {
    final int count = test.countTestCases();
    synchronized (this)
      {
        fRunTests += count;
      }
    for (Iterator i = cloneListeners().iterator(); i.hasNext();)
      {
        TestListener l = (TestListener) i.next();
        l.startTest(test);
      }
  }

  /**
   * Ends the specified test. This informs interested listeners.
   *
   * @param test the test to end
   */
  public void endTest(Test test)
  {
    for (Iterator i = cloneListeners().iterator(); i.hasNext();)
      {
        TestListener l = (TestListener) i.next();
        l.endTest(test);
      }
  }

  /**
   * Adds a failure to the test result.
   *
   * @param test the failed test
   * @param failure the test failure
   */
  public synchronized void addFailure(Test test, AssertionFailedError failure)
  {
    fFailures.add(new TestFailure(test, failure));
    for (Iterator i = cloneListeners().iterator(); i.hasNext();)
      {
        TestListener l = (TestListener) i.next();
        l.addFailure(test, failure);
      }
  }

  /**
   * Adds an error to the test result.
   *
   * @param test the err'ed test
   * @param failure the test error
   */
  public synchronized void addError(Test test, Throwable failure)
  {
    fErrors.add(new TestFailure(test, failure));
    for (Iterator i = cloneListeners().iterator(); i.hasNext();)
      {
        TestListener l = (TestListener) i.next();
        l.addError(test, failure);
      }
  }

  /**
   * Adds a test listener.
   *
   * @param l the listener to add
   */
  public synchronized void addListener(TestListener l)
  {
    fListeners.add(l);
  }

  /**
   * Removes a test listener.
   *
   * @param l the listener to be removed
   */
  public synchronized void removeListener(TestListener l)
  {
    fListeners.remove(l);
  }

  /**
   * Returns the number of errors.
   *
   * @return the number of errors
   */
  public synchronized int errorCount()
  {
    return fErrors.size();
  }

  /**
   * Returns the errors in this test result.
   *
   * @return the errors in this test result
   */
  public synchronized Enumeration errors()
  {
    return Collections.enumeration(fErrors);
  }

  /**
   * Returns the number of failures.
   *
   * @return the number of failures
   */
  public synchronized int failureCount()
  {
    return fFailures.size();
  }

  /**
   * Returns the failures in this test result.
   *
   * @return the failures in this test result
   */
  public synchronized Enumeration failures()
  {
    return Collections.enumeration(fFailures);
  }

  /**
   * Returns the number of tests that have been run.
   *
   * @return the number of tests that have been run
   */
  public synchronized int runCount()
  {
    return fRunTests;
  }

  /**
   * Returns <code>true</code> when the test should stop, <code>false</code>
   * otherwise.
   *
   * @return <code>true</code> when the test should stop, <code>false</code>
   *         otherwise
   */
  public synchronized boolean shouldStop()
  {
    return fStop;
  }

  /**
   * Stops the test.
   */
  public synchronized void stop()
  {
    fStop = true;
  }

  /**
   * Returns <code>true</code> when the test had no errors and no failures,
   * <code>false</code> otherwise.
   *
   * @return <code>true</code> when the test had no errors and no failures,
   *         <code>false</code> otherwise
   */
  public synchronized boolean wasSuccessful()
  {
    return failureCount() == 0 && errorCount() == 0;
  }

  /**
   * Returns a cloned listener list.
   *
   * @return a cloned listener list
   */
  private synchronized List cloneListeners()
  {
    List copy = new ArrayList();
    copy.addAll(fListeners);
    return copy;
  }
}
