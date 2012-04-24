/* TestSuite.java -- JUnit test suite
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

/**
 * A collection of JUnit tests.
 */
public class TestSuite
  implements Test, Testlet
{

  /**
   * The name of the test.
   */
  private String fName;

  /**
   * The tests in this test suite.
   */
  private Vector<Test> fTests;

  /**
   * Creates a new test suite.
   */
  public TestSuite()
  {
    fTests = new Vector<Test>();
  }

  /**
   * Creates a new test suite that loads its tests from the specified class.
   *
   * @param theClass the class to load the tests from
   */
  public TestSuite(Class<?> theClass)
  {
    this();
    fName = theClass.getName();
    Class<?> clazz = theClass;
    List<String> names = new ArrayList<String>();
    while (Test.class.isAssignableFrom(clazz))
      {
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++)
          {
            addTestMethod(methods[i], names, theClass);
          }
        clazz = clazz.getSuperclass();
      }
  }

  /**
   * Creates a new TestSuite with the specified name.
   *
   * @param name the name of the test suite
   */
  public TestSuite(String name)
  {
    setName(name);
  }

  /**
   * Creates a new TestSuite with the specified name that loads the tests from
   * the specified class.
   *
   * @param theClass the class to load the tests from
   * @param name the name of the test suite
   */
  public TestSuite(Class<?> theClass, String name)
  {
    this(theClass);
    setName(name);
  }

  /**
   * Adds the specified method to the test suite if appropriate.
   *
   * @param method the method to check
   * @param names the list of names of already added methods
   * @param theClass the test class
   */
  private void addTestMethod(Method method, List<String> names, Class<?> theClass)
  {
    String name = method.getName();
    if (! names.contains(name))
      {
        if (isPublicTestMethod(method))
          {
            names.add(name);
            addTest(createTest(theClass, name));
          }
      }
  }

  /**
   * Checks if the specified method is a test method and is public.
   *
   * @param method the method to check
   *
   * @return <code>true</code> if the method is a public test method,
   *         <code>false</code> otherwise
   */
  private boolean isPublicTestMethod(Method method)
  {
    return isTestMethod(method) && Modifier.isPublic(method.getModifiers());
  }

  /**
   * Checks if the specified method is a test method.
   *
   * @param method the method to check
   *
   * @return <code>true</code> if the method is a test method,
   *         <code>false</code> otherwise
   */
  private boolean isTestMethod(Method method)
  {
    String name = method.getName();
    Class<?>[] params = method.getParameterTypes();
    Class<?> ret = method.getReturnType();
    return params.length == 0 && name.startsWith("test")
           && ret.equals(Void.TYPE);
  }

  /**
   * Creates a test for the specified test class and with the specified
   * name.
   *
   * @param theClass the test class
   * @param name the test name
   *
   * @return the test instance
   */
  public static Test createTest(Class<?> theClass, String name)
  {
    Constructor<?> constructor = null;
    Test test = null;
    try
      {
        constructor = getTestConstructor(theClass);
      }
    catch (NoSuchMethodException ex)
      {
        test = null;
      }
    if (constructor != null)
      {
        Object o = null;
        try
          {
            if (constructor.getParameterTypes().length == 0)
              {
                o = constructor.newInstance(new Object[0]);
                if (o instanceof TestCase)
                  ((TestCase) o).setName(name);
              }
            else
              {
                o = constructor.newInstance(new Object[]{ name });
              }
          }
        catch (InstantiationException ex)
          {
            test = null;
          }
        catch (InvocationTargetException ex)
          {
            test = null;
          }
        catch (IllegalAccessException ex)
          {
            test = null;
          }
        test = (Test) o;
      }
    return test;
  }

  /**
   * Returns the constructor for the specified test class.
   *
   * @param theClass the test class
   *
   * @return the constructor for the specified test class
   *
   * @throws NoSuchMethodException if no suitable constructor exists
   */
  public static Constructor<?> getTestConstructor(Class<?> theClass)
    throws NoSuchMethodException
  {
    try
      {
        return theClass.getConstructor(String.class);
      }
    catch (NoSuchMethodException ex)
      {
        // Search for a no-arg constructor below.
      }
    return theClass.getConstructor();
  }

  /**
   * Returns the number of tests in this test suite.
   *
   * @return the number of tests in this test suite
   */
  public int countTestCases()
  {
    int count = 0;
    for (Iterator<Test> i = fTests.iterator(); i.hasNext();)
      {
        Test test = i.next();
        count += test.countTestCases();
      }
    return count;
  }

  /**
   * Runs the test cases from this test suite.
   *
   * @param result the test results
   */
  public void run(TestResult result)
  {
    for (Iterator<Test> i = fTests.iterator(); i.hasNext();)
      {
        if (! result.shouldStop())
          {
            Test test = i.next();
            runTest(test, result);
          }
      }
  }

  /**
   * Runs a single test.
   *
   * @param test the test to run
   * @param result the test results
   */
  public void runTest(Test test, TestResult result)
  {
    test.run(result);
  }

  /**
   * Adds a single test to the test suite.
   *
   * @param test the test to add
   */
  public void addTest(Test test)
  {
    fTests.add(test);
  }

  /**
   * Adds tests from the specified class to the test suite.
   *
   * @param testClass the class from which to load tests to add
   */
  public void addTestSuite(Class<?> testClass)
  {
    fTests.add(new TestSuite(testClass));
  }

  /**
   * Sets the name for this test.
   *
   * @param name the name to set
   */
  public void setName(String name)
  {
    fName = name;
  }

  /**
   * Returns the name of this test.
   *
   * @return the name of this test
   */
  public String getName()
  {
    return fName;
  }

  /**
   * This implements the Mauve Testlet interface. This implementation
   * runs all tests in this testsuite that are runnable by Mauve.
   *
   * @param harness the Mauve test harness
   */
  public void test(TestHarness harness)
  {
    for (Iterator<Test> i = fTests.iterator(); i.hasNext();)
      {
        Test test = i.next();
        if (test instanceof TestCase)
          ((TestCase) test).testSingle(harness);
        else if (test instanceof Testlet)
          ((Testlet) test).test(harness);
      }
  }

}
