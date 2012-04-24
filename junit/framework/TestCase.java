/* TestCase.java -- A JUnit test case
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

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * A JUnit test case.
 */
public abstract class TestCase
  extends Assert
  implements Test, Testlet
  
{

  /**
   * The name of the test case.
   */
  private String fName;

  /**
   * Creates a new TestCase.
   */
  public TestCase()
  {
    fName = null;
  }

  /**
   * Creates a test case with a name.
   *
   * @param name the name of the test case
   */
  public TestCase(String name)
  {
    fName = name;
  }

  /**
   * Returns the number of test cases executed by this test.
   *
   * @return the number of test cases executed by this test
   */
  public int countTestCases()
  {
    return 1;
  }

  /**
   * Creates a default TestResult object.
   *
   * @return a default TestResult object
   */
  protected TestResult createResult()
  {
    return new TestResult();
  }

  /**
   * Creates a TestResult and runs a test by calling
   * {@link #run(TestResult)}.
   *
   * @return the test results after running the test
   */
  public TestResult run()
  {
    TestResult result = createResult();
    run(result);
    return result;
  }

  /**
   * Runs the test and collects the result in the specified TestResult
   * object.
   *
   * @param result the TestResult object to collect the results in
   */
  public void run(TestResult result)
  {
    result.run(this);
  }

  /**
   * Runs the bare test sequence. This calls {@link #setUp()}, executes
   * the test by calling {@link #runTest}, and finally finishes with
   * {@link #tearDown()}.
   *
   * @throws Throwable if a failure or error occured
   */
  public void runBare()
    throws Throwable
  {
    Throwable t = null;
    setUp();
    try
      {
        runTest();
      }
    catch (Throwable ex)
      {
        t = ex;
      }
    finally
      {
        try
          {
            tearDown();
          }
        catch (Throwable ex2)
          {
            if (t == null)
              t = ex2;
          }
      }
    if (t != null)
      throw t;
  }

  /**
   * Actually runs the test. The default implementation tries to find
   * a method with the name specified in the constructor of this class.
   * If such a method is found, it is invoked.
   *
   * @throws Throwable for test errors or failures
   */
  protected void runTest()
    throws Throwable
  {
    Method method = null;
    try
      {
        method = getClass().getMethod(fName, (Class[]) null);
      }
    catch (NoSuchMethodException ex)
      {
        fail("Method " + fName + " not found");
      }
    if (! Modifier.isPublic(method.getModifiers()))
      fail("Method " + fName + " must be public");
    try
      {
        method.invoke(this, new Object[0]);
      }
    catch (InvocationTargetException ex)
      {
        ex.fillInStackTrace();
        throw ex.getTargetException();
      }
    catch (IllegalAccessException ex)
      {
        ex.fillInStackTrace();
        throw ex;
      }
  }

  /**
   * Prepares the test. This is called before {@link #runTest()}.
   *
   * @throws Exception if anything goes wrong
   */
  protected void setUp()
    throws Exception
  {
    // This is a hook with nothing to do itself.
  }

  /**
   * Finishes the test. This is called after {@link #runTest()}.
   *
   * @throws Exception if anything goes wrong
   */
  protected void tearDown()
    throws Exception
  {
    // This is a hook with nothing to do itself.
  }

  /**
   * Returns the name of the test case.
   *
   * @return the name of the test case
   */
  public String getName()
  {
    return fName;
  }

  /**
   * Sets the name of the test case.
   *
   * @param name the name to set
   */
  public void setName(String name)
  {
    fName = name;
  }

  /**
   * Returns a string representation of this test case.
   *
   * @return a string representation of this TestCase
   */
  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append(getName());
    str.append('(');
    str.append(getClass().getName());
    str.append(')');
    return str.toString();
  }

  /**
   * Implementation of Mauve's Testlet interface. This makes JUnit TestCases
   * automatically executable by the Mauve test harness.
   */
  public void test(TestHarness harness)
  {
    // Fetch the actual JUnit testsuite.
    Test test = getTest();
    // This is normally an instance of TestSuite.
    if (test instanceof TestSuite)
      {
        TestSuite suite = (TestSuite) test;
        suite.test(harness);
      }
  }

  /**
   * Performs a single test.
   *
   * @param harness the test harness to use
   */
  void testSingle(TestHarness harness)
  {
    TestCase.harness = harness;
    try
      {
        runBare();
      }
    catch (Throwable ex)
      {
        harness.fail(ex.getMessage());
        harness.debug(ex);
      }
    TestCase.harness = null;
  }

  /**
   * Fetches the test suite to be run. This tries to call a static
   * suite() method on this class, and, if that fails, create a new TestSuite
   * with this class as parameter, which will collect all test*() methods.
   *
   * @return the testsuite for this class or null, if no test suite could be
   *         created
   */
  private Test getTest()
  {
    Class<?> clazz = getClass();
    Method suiteMethod = null;
    Test test = null;
    try
      {
        suiteMethod = clazz.getMethod("suite");
      }
    catch (Exception ex)
      {
        test = new TestSuite(clazz);
      }
    if (test == null && suiteMethod != null) // suite() method found.
      {
        try
          {
            test = (Test) suiteMethod.invoke(null, (Object[]) new Class[0]);
          }
        catch (InvocationTargetException ex)
          {
            test = null;
          }
        catch (IllegalAccessException ex)
          {
            test = null;
          }
      }
    return test;
  }
}
