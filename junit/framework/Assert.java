/* Assert.java -- Assertions to be used by tests
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

/**
 * Test assertions to be used by test cases.
 */
public class Assert
{

  /**
   * The mauve test harness. The assertions are delegated to the harness
   * if this is not null. Otherwise the normal JUnit behaviour is implemented,
   * which is to throw an AssertionFailedError.
   */
  static TestHarness harness;

  /**
   * Creates a new Assert object.
   */
  protected Assert()
  {
    // Nothing to do here.
  }

  /**
   * Checks if <code>value</code> is <code>true</code>.
   *
   * @param message the error message in the case this assertion fails
   * @param value the value to check.
   */
  public static void assertTrue(String message, boolean value)
  {
    if (harness != null)
      harness.check(value);
    else if (! value)
      fail(message);
  }

  /**
   * Checks if <code>value</code> is <code>true</code>.
   *
   * @param value the value to check.
   */
  public static void assertTrue(boolean value)
  {
    assertTrue(null, value);
  }

  /**
   * Checks if <code>value</code> is <code>true</code>.
   *
   * @param message the error message in the case this assertion fails
   * @param value the value to check.
   */
  public static void assertFalse(String message, boolean value)
  {
    assertTrue(message, ! value);
  }

  /**
   * Checks if <code>value</code> is <code>false</code>.
   *
   * @param value the value to check.
   */
  public static void assertFalse(boolean value)
  {
    assertFalse(null, value);
  }

  /**
   * Unconditionally fails with the specified message.
   *
   * @param message
   */
  public static void fail(String message)
  {
    if (harness != null)
      harness.check(false);
    else
      throw new AssertionFailedError(message);
  }

  /**
   * Unconditionally fails without message.
   */
  public static void fail()
  {
    fail(null);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expexted</code> in the
   * sense of <code>Object.equals()</code>.
   *
   * @param message the error message in case of failure
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertEquals(String message, Object expected,
                                  Object value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      {
        if ((expected != null || value != null)
            && (expected == null || ! expected.equals(value)))
          failNotEquals(message, expected, value);
      }
  }

  /**
   * Checks if <code>value</code> is equal to <code>expexted</code> in the
   * sense of <code>Object.equals()</code>.
   *
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertEquals(Object expected, Object value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expexted</code> in the
   * sense of <code>Object.equals()</code>.
   *
   * @param message the error message in case of failure
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertEquals(String message, String expected,
                                  String value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      {
        if ((expected != null || value != null)
            && (expected == null || ! expected.equals(value)))
          throw new ComparisonFailure(message, expected, value);
      }
  }

  /**
   * Checks if <code>value</code> is equal to <code>expexted</code> in the
   * sense of <code>Object.equals()</code>.
   *
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertEquals(String expected, String value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>, taking
   * a rounding delta <code>delta</code> into account.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value to check
   * @param delta the rounding delta
   */
  public static void assertEquals(String message, double expected,
                                  double value, double delta)
  {
    if (harness != null)
      harness.check(expected, value, delta);
    else
      {
        if (Double.isInfinite(expected))
          {
            if (value != expected)
              failNotEquals(message, new Double(expected), new Double(value));
          }
        else if (! (Math.abs(expected - value) <= delta))
          failNotEquals(message, new Double(expected), new Double(value));
      }
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>, taking
   * a rounding delta <code>delta</code> into account.
   *
   * @param expected the expected value
   * @param value the actual value to check
   * @param delta the rounding delta
   */
  public static void assertEquals(double expected, double value, double delta)
  {
    assertEquals(null, expected, value, delta);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>, taking
   * a rounding delta <code>delta</code> into account.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value to check
   * @param delta the rounding delta
   */
  public static void assertEquals(String message, float expected, float value,
                                  float delta)
  {
    if (harness != null)
      harness.check(expected, value, delta);
    else
      {
        if (Float.isInfinite(expected))
          {
            if (value != expected)
              failNotEquals(message, new Float(expected), new Float(value));
          }
        else if (! (Math.abs(expected - value) <= delta))
          failNotEquals(message, new Float(expected), new Float(value));
      }
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>, taking
   * a rounding delta <code>delta</code> into account.
   *
   * @param expected the expected value
   * @param value the actual value to check
   * @param delta the rounding delta
   */
  public static void assertEquals(float expected, float value, float delta)
  {
    assertEquals(null, expected, value, delta);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(String message, long expected, long value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      failNotEquals(message, new Long(expected), new Long(value));
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(long expected, long value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(String message, boolean expected,
                                  boolean value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      failNotEquals(message, new Boolean(expected), new Boolean(value));
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(boolean expected, boolean value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(String message, byte expected, byte value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      failNotEquals(message, new Byte(expected), new Byte(value));
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(byte expected, byte value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(String message, char expected, char value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      failNotEquals(message, new Character(expected), new Character(value));
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(char expected, char value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(String message, short expected, short value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      failNotEquals(message, new Short(expected), new Short(value));
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(short expected, short value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param message the error message in the case of failure
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(String message, int expected, int value)
  {
    if (harness != null)
      harness.check(expected, value);
    else
      failNotEquals(message, new Integer(expected), new Integer(value));
  }

  /**
   * Checks if <code>value</code> is equal to <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value
   */
  public static void assertEquals(int expected, int value)
  {
    assertEquals(null, expected, value);
  }

  /**
   * Checks that the <code>value</code> is not null.
   *
   * @param message the error message in the case of failure
   * @param value the value to check
   */
  public static void assertNotNull(String message, Object value)
  {
    assertTrue(message, value != null);
  }

  /**
   * Checks that the <code>value</code> is not null.
   *
   * @param value the value to check
   */
  public static void assertNotNull(Object value)
  {
    assertNotNull(null, value);
  }

  /**
   * Checks that the <code>value</code> is null.
   *
   * @param message the error message in the case of failure
   * @param value the value to check
   */
  public static void assertNull(String message, Object value)
  {
    assertTrue(message, value == null);
  }

  /**
   * Checks that the <code>value</code> is null.
   *
   * @param value the value to check
   */
  public static void assertNull(Object value)
  {
    assertNull(null, value);
  }

  /**
   * Checks that the <code>value</code> is the same object instance as
   * <code>expected</code>.
   *
   * @param message the error message in case of failure
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertSame(String message, Object expected, Object value)
  {
    if (harness != null)
      harness.check(expected == value);
    else if (value != expected)
      {
        StringBuffer str = new StringBuffer();
        if (message != null)
          {
            str.append(message);
            str.append(' ');
            str.append("expected to be same");
          }
        fail(format(str, expected, value));
      }
  }

  /**
   * Checks that the <code>value</code> is the same object instance as
   * <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertSame(Object expected, Object value)
  {
    assertSame(null, expected, value);
  }

  /**
   * Checks that the <code>value</code> is not the same object instance as
   * <code>expected</code>.
   *
   * @param message the error message in case of failure
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertNotSame(String message, Object expected,
                                   Object value)
  {
    if (harness != null)
      harness.check(expected != value);
    else if (value == expected)
      {
        StringBuffer str = new StringBuffer();
        if (message != null)
          {
            str.append(message);
            str.append(' ');
            str.append("expected to be not the same");
          }
        fail(format(str, expected, value));
      }
  }

  /**
   * Checks that the <code>value</code> is not the same object instance as
   * <code>expected</code>.
   *
   * @param expected the expected value
   * @param value the actual value to check
   */
  public static void assertNotSame(Object expected, Object value)
  {
    assertNotSame(null, expected, value);
  }

  /**
   * Called when a test failed because two objects are not equal.
   *
   * @param message the error message
   * @param expected the expected value
   * @param value the actual value
   */
  private static void failNotEquals(String message, Object expected,
                                    Object value)
  {
    StringBuffer str = new StringBuffer();
    if (message != null)
      {
        str.append(message);
        str.append(' ');
      }
    fail(format(str, expected, value));
  }

  /**
   * Formats the error message.
   *
   * @param str the string buffer to append to, with the start of the error
   *        message
   * @param expected the expected value
   * @param value the actual value
   *
   * @return the formatted message
   */
  private static String format(StringBuffer str, Object expected,
                               Object value)
  {
    str.append(' ');
    str.append(" expected value: ");
    str.append(expected);
    str.append(" actual value: " + value);
    return str.toString();
  }
}
