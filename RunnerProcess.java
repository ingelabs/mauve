// Copyright (c) 2006  Red Hat, Inc.
// Written by Anthony Balkissoon <abalkiss@redhat.com>
// Adapted from gnu.testlet.SimpleTestHarness written by Tom Tromey.
// Copyright (c) 2005  Mark J. Wielaard  <mark@klomp.org>

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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

// This file is used by Harness.java to run the tests in a separate process
// so that the process can be killed by the Harness if it is hung.

import gnu.testlet.ResourceNotFoundException;
import gnu.testlet.TestHarness;
import gnu.testlet.TestReport;
import gnu.testlet.TestResult;
import gnu.testlet.TestSecurityManager;
import gnu.testlet.Testlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;

public class RunnerProcess
    extends TestHarness
{
  // A description of files that are not tests
  public static final String NOT_A_TEST_DESCRIPTION = "not-a-test";
  
  // Total number of harness.check calls since the last checkpoint
  private int count = 0;

  // Total number of harness.check fails plus harness.fail calls
  private int failures = 0;

  // The expected fails
  private static Vector expected_xfails = new Vector();

  // The number of expected failures that did fail
  private int xfailures = 0;

  // The number of expected failures that passed (unexpectedly)
  private int xpasses = 0;

  // The total number of harness.check calls plus harness.fail calls
  private int total = 0;

  // True if we should run in verbose (noisy) mode
  private boolean verbose = false;

  // True if failing calls to harness.check(Object, Object) should print the
  // toString methods of each Object
  private boolean debug = false;

  // True if stack traces should be printed for uncaught exceptions
  private boolean exceptions = false;

  // A description of the test
  private String description;

  // The name of the last checkpoint
  private String last_check;

  // The TestReport if a report is necessary
  private TestReport report = null;

  // The result of the current test
  private TestResult currentResult = null;

  
  protected RunnerProcess(boolean verbose, boolean debug,
                              boolean exceptions, TestReport report)
  {
    this.verbose = verbose;
    this.debug = debug;
    this.exceptions = exceptions;
    this.report = report;

    try
      {
        BufferedReader xfile = new BufferedReader(new FileReader("xfails"));
        String str;
        while ((str = xfile.readLine()) != null)
          {
            expected_xfails.addElement(str);
          }
      }
    catch (FileNotFoundException ex)
      {
        // Nothing.
      }
    catch (IOException ex)
      {
        // Nothing.
      }
  }

  public static void main(String[] args)
  {    
    boolean verbose = false;
    boolean debug = false;
    boolean exceptions = false;
    String xmlfile = null;
    TestReport report = null;
    
    // The test that Harness wants us to run.
    String testname;
    
    // This reader is used to get testnames from Harness
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    // Parse the arguments so we can create an appropriate RunnerProcess
    // to run the tests.
    for (int i = 0; i < args.length; i++)
      {        
        if (args[i].equals("-verbose"))
          // User wants to run in verbose mode.
          verbose = true;
        else if (args[i].equals("-debug"))
          // User wants extra debug info.
          debug = true;
        else if (args[i].equals("-exceptions"))
          // User wants stack traces for uncaught exceptions.
          exceptions = true;
        else if (args[i].equals("-xmlout"))
          {
            // User wants a report.
            if (++i >= args.length)
              throw new RuntimeException("No file path after '-xmlout'.");
            xmlfile = args[i];
          }
      }
    // If the user wants an xml report, create a new TestReport.
    if (xmlfile != null)
      {
        report = new TestReport(System.getProperties());
      }

    while (true)
      {
        // Ask Harness for a test to run, run it, report back to Harness, and
        // then repeat the cycle.
        try
        {
          testname = in.readLine();
          RunnerProcess harness = 
            new RunnerProcess(verbose, debug, exceptions, report);
          runAndReport(harness, testname, report, xmlfile);
        }
        catch (IOException ioe)
        {          
          System.out.println("Problems communicating between " +
                "Harness and RunnerProcess");
        }
      }
  }
  
  /**
   * This method runs a single test.  If an exception is caught some
   * information is printed out so the test can be debugged.
   * @param name the name of the test to run
   */
  protected void runtest(String name)
  {
    // Try to ensure we start off with a reasonably clean slate.
    System.gc();
    System.runFinalization();

    currentResult = new TestResult(name);

    checkPoint(null);

    Testlet t = null;
    try
      {
        Class k = Class.forName(name);

        Object o = k.newInstance();
        if (! (o instanceof Testlet))
          {
            description = NOT_A_TEST_DESCRIPTION;
            return;
          }

        t = (Testlet) o;
      }
    catch (Throwable ex)
      {
        // Maybe the file was marked not-a-test, check that before we report
        // it as an error
        try
        {
          File f = new File(name.replace('.', File.separatorChar) + ".java");
          BufferedReader r = new BufferedReader(new FileReader(f));
          String firstLine = r.readLine();
          // Since some people mistakenly put not-a-test not as the first line
          // we have to check through the file.
          while (firstLine != null)
            {
              if (firstLine.contains("not-a-test"))
                  {
                    description = NOT_A_TEST_DESCRIPTION;
                    return;
                  }
              firstLine = r.readLine();
            }
        }
        catch(FileNotFoundException fnfe)
        {          
        }
        catch (IOException ioe)
        {          
        }
        
        String d = "FAIL: " + stripPrefix(name)
                   + "uncaught exception when loading";
        currentResult.addException(ex, "failed loading class " + name);
        if (verbose || exceptions)
          d += ": " + ex.toString();

        if (exceptions)
          ex.printStackTrace(System.out);
        debug(ex);
        if (ex instanceof InstantiationException
            || ex instanceof IllegalAccessException)
          debug("Hint: is the code we just loaded a public non-abstract "
                + "class with a public nullary constructor???");
        ++failures;
        ++total;
      }

    if (t != null)
      {
        description = name;
        try
          {
            if (verbose)
              System.out.println("TEST: " + stripPrefix(name));
            t.test(this);
            removeSecurityManager();
          }
        catch (Throwable ex)
          {
            if (failures == 0 && !verbose)
              System.out.println ("FAIL: " + stripPrefix(name) + ":");
            removeSecurityManager();
            String s = (last_check == null ? "" : " at " + last_check + " ["
                                                  + (count + 1) + "]");
            String d = exceptionDetails(ex, name, exceptions);
            currentResult.addException(ex, "uncaught exception" + s);
            System.out.println(d);
            if (exceptions)
              ex.printStackTrace(System.out);
            debug(ex);
            ++failures;
            ++total;
          }
      }
    if (report != null)
      report.addTestResult(currentResult);
  }

  
  /**
   * This method runs a single test in a new Harness and increments the
   * total tests run and total failures, if the test fails.  Prints
   * PASS and adds to the report, if the appropriate options are enabled.
   * @param harness the TestHarness to use for this test
   * @param testName the name of the test
   * @param report the TestReport to generate
   * @param xmlfile the name of the file for xml output
   */
  static void runAndReport(RunnerProcess harness, String testName,
                      TestReport report, String xmlfile)
  {
    // If this call to runtest hangs, Harness will terminate this process.
    harness.runtest(testName);
    // If the test wasn't a real test, return and tell Harness so.
    if (harness.description.equals(NOT_A_TEST_DESCRIPTION))
      {
        System.out.println("RunnerProcess:not-a-test");
        return;
      }
    
    int temp = harness.done();
    
    // Print the report if necessary.
    if (report != null)
      {
        File f = new File(xmlfile);
        try
          {
            report.writeXml(f);
          }
        catch (IOException e)
          {
            throw new Error("Failed to write data to xml file: "
                            + e.getMessage());
          }
      }
    
    // Report back to Harness that we've finished properly, whether the test
    // passed or failed.  Harness will wait for a message starting with 
    // "RunnerProcess" and if it doesn't receive it after a certain amount of 
    // time (specified in the timeout variable) it will consider the test hung
    // and will terminate and restart this Process.
    if (temp == 0)
      System.out.println ("RunnerProcess:pass");
    else
      System.out.println("RunnerProcess:fail");
  }
  
  private final String getDescription(StackTraceElement[] st)
  {
    // Find the line number of the check() call that failed.
    int line = -1;
    for (int i = 0; i < st.length; i++)
      {
        if (st[i].getClassName().equals(description))
          {
            line = st[i].getLineNumber();
            break;
          }
      }
    
    return ("  line " + line + ": " + ((last_check == null) ? "" : last_check) + " ["
            + (count + 1) + "]");
  }

  protected int getFailures()
  {
    return failures;
  }

  /**
   * Removes the "gnu.testlet." from the start of a String.
   * @param val the String
   * @return the String with "gnu.testlet." removed
   */
  private static String stripPrefix(String val)
  {
    if (val.startsWith("gnu.testlet."))
      val = val.substring(12);
    return val;
  }
  
  /**
   * A convenience method that sets a checkpoint with the specified name
   * then prints a message about the forced fail.
   *
   * @param name  the checkpoint name.
   */
  public void fail(String name)
  {
    checkPoint(name);
    check2(false);
    System.out.println ("forced fail");
  }
  
  /**
   * Checks the two objects for equality and prints a message if they are not
   * equal.
   *
   * @param result  the actual result.
   * @param expected  the expected result.
   */
  public void check(Object result, Object expected)
  {
    boolean ok = (result == null ? expected == null : result.equals(expected));
    check2(ok);
    // This debug message may be misleading, depending on whether
    // string conversion produces same results for unequal objects.
    if (! ok)
      {
        String gotString = result == null ? "null"
                                         : result.getClass().getName();
        String expString = expected == null ? "null"
                                           : expected.getClass().getName();
        
        // If the strings are equal but the objects aren't, we have to tell
        // the user so, otherwise we can just print the strings.
        if (gotString.equals(expString))
          {
            // Since the toString() methods can print long and ugly information
            // we only use them if the user really wants to see it, ie
            // if they used the -debug option.
            if (debug)
              {
                gotString = result.toString();
                expString = expected.toString();
                System.out.println("\n           got " + gotString
                                   + "\n\n           but expected " + expString
                                   + "\n\n");
                return;
              }
            else
              {
                System.out.println("objects were not equal.  " +
                        "Use -debug for more information.");
                return;
              }
          }
        System.out.println("got " + gotString + " but expected " + expString);
      }
  }

  /**
   * Checks two booleans for equality and prints out a message if they are not
   * equal.
   * 
   * @param result the actual result.
   * @param expected the expected result.
   */
  public void check(boolean result, boolean expected)
  {
    boolean ok = (result == expected);
    check2(ok);
    if (! ok)
      System.out.println("got " + result + " but expected " + expected);
  }

  /**
   * Checks two ints for equality and prints out a message if they are not
   * equal.
   * 
   * @param result the actual result.
   * @param expected the expected result.
   */
  public void check(int result, int expected)
  {
    boolean ok = (result == expected);
    check2(ok);
    if (! ok)
      System.out.println("got " + result + " but expected " + expected);
  }

  /**
   * Checks two longs for equality and prints out a message if they are not
   * equal.
   * 
   * @param result the actual result.
   * @param expected the expected result.
   */
  public void check(long result, long expected)
  {
    boolean ok = (result == expected);
    check2(ok);
    if (! ok)
      System.out.println("got " + result + " but expected " + expected);
  }

  /**
   * Checks two doubles for equality and prints out a message if they are not
   * equal.
   * 
   * @param result the actual result.
   * @param expected the expected result.
   */
  public void check(double result, double expected)
  {
    // This triple check overcomes the fact that == does not
    // compare NaNs, and cannot tell between 0.0 and -0.0;
    // and all without relying on java.lang.Double (which may
    // itself be buggy - else why would we be testing it? ;)
    // For 0, we switch to infinities, and for NaN, we rely
    // on the identity in JLS 15.21.1 that NaN != NaN is true.
    boolean ok = (result == expected ? (result != 0)
                                       || (1 / result == 1 / expected)
                                    : (result != result)
                                      && (expected != expected));
    check2(ok);
    if (! ok)
      System.out.println("got " + result + " but expected " + expected);
  }
  
  /**
   * Checks if <code>result</code> is true.  If not, prints out 
   * a message.
   * @param result the boolean to check
   */
  public void check(boolean result)
  {
    check2(result);
    if (!result)
      System.out.println ("boolean passed to check was false");
  }
  
  /**
   * This method prints out failures and checks the XFAILS file.
   * @param result true if the test passed, false if it failed
   */
  private void check2(boolean result)
  {
    // If the test failed we have to print out some explanation.
    StackTraceElement[] st = new Throwable().getStackTrace();
    String desc = getDescription(st);


    if (! result)
      {
        
        currentResult.addFail((last_check == null ? "" : last_check)
                              + " (number " + (count + 1) + ")");
        if (! expected_xfails.contains(desc))
          {
            // If the failure wasn't expected, we need to print it to the
            // screen.
            if (failures == 0 && !verbose)
              System.out.println ("FAIL: " + stripPrefix(description) + ":");
            if (verbose)
              System.out.print("  FAIL:");
            System.out.print(desc + " -- ");
            ++failures;
          }
        else if (verbose)
          {
            // If it was expected but verbose is true, we also print it.
            System.out.println("X" + desc  + " -- ");
            ++xfailures;
          }
      }
    else
      {
        // The test passed.  Only print info if verbose is true
        currentResult.addPass();
        if (verbose)
          {
            if (expected_xfails.contains(desc))
              {
                System.out.println("XPASS: " + desc);
                ++xpasses;
              }
            else
              System.out.println("  pass:" + desc);
          }
      }
    ++count;
    ++total;
  }

  public Reader getResourceReader(String name) throws ResourceNotFoundException
  {
    return new BufferedReader(new InputStreamReader(getResourceStream(name)));
  }

  public InputStream getResourceStream(String name)
      throws ResourceNotFoundException
  {
    // The following code assumes File.separator is a single character.
    if (File.separator.length() > 1)
      throw new Error("File.separator length is greater than 1");
    String realName = name.replace('#', File.separator.charAt(0));
    try
      {
        return new FileInputStream(getSourceDirectory() + File.separator
                                   + realName);
      }
    catch (FileNotFoundException ex)
      {
        throw new ResourceNotFoundException(ex.getLocalizedMessage() + ": "
                                            + getSourceDirectory()
                                            + File.separator + realName);
      }
  }

  public File getResourceFile(String name) throws ResourceNotFoundException
  {
    // The following code assumes File.separator is a single character.
    if (File.separator.length() > 1)
      throw new Error("File.separator length is greater than 1");
    String realName = name.replace('#', File.separator.charAt(0));
    File f = new File(getSourceDirectory() + File.separator + realName);
    if (! f.exists())
      {
        throw new ResourceNotFoundException("cannot find mauve resource file"
                                            + ": " + getSourceDirectory()
                                            + File.separator + realName);
      }
    return f;
  }

  public void checkPoint(String name)
  {
    last_check = name;
    count = 0;
  }

  public void verbose(String message)
  {
    if (verbose)
      System.out.println(message);
  }

  public void debug(String message)
  {
    debug(message, true);
  }

  public void debug(String message, boolean newline)
  {
    if (debug)
      {
        if (newline)
          System.out.println(message);
        else
          System.out.print(message);
      }
  }

  public void debug(Throwable ex)
  {
    if (debug)
      ex.printStackTrace(System.out);
  }

  public void debug(Object[] o, String desc)
  {
    debug("Dumping Object Array: " + desc);
    if (o == null)
      {
        debug("null");
        return;
      }

    for (int i = 0; i < o.length; i++)
      {
        if (o[i] instanceof Object[])
          debug((Object[]) o[i], desc + " element " + i);
        else
          debug("  Element " + i + ": " + o[i]);
      }
  }

  private void removeSecurityManager()
  {
    SecurityManager m = System.getSecurityManager();
    if (m instanceof TestSecurityManager)
      {
        TestSecurityManager tsm = (TestSecurityManager) m;
        tsm.setRunChecks(false);
        System.setSecurityManager(null);
      }
  }

  /**
   * This method returns some information about uncaught exceptions.
   * Nothing is printed if the test was run with the -exceptions flag since in
   * that case a full stack trace will be printed.
   * @param ex the exception
   * @param name the name of the test
   * @param exceptions true if a full stack trace will be printed
   * @return a String containing some information about the uncaught exception
   */
  private String exceptionDetails(Throwable ex, String name,
                                         boolean exceptions)
  {
    // If we can't get a stack trace, we return no details.
    StackTraceElement[] st = ex.getStackTrace();
    if (st == null || st.length == 0)
      return "  uncaught exception:";

    // lineOrigin will store the line number in the test method that caused
    // the exception.
    int lineOrigin = -1;
    
    // This for loop looks for the line within the test method that caused the
    // exception.
    for (int i = 0; i < st.length; i++)
      {
        if (st[i].getClassName().equals(name)
            && st[i].getMethodName().equals("test"))
          {
            lineOrigin = st[i].getLineNumber();
            break;
          }
      }
    
    // sb holds all the information we wish to return.
    StringBuilder sb = 
      new StringBuilder("  line " + lineOrigin + ": " + 
                        (last_check == null ? "" : last_check) +
                        " [" + (count + 1) + "] -- uncaught exception:");
    
    // If a full stack trace will be printed, this method returns no details.
    if (exceptions)
      return sb.toString();
    
    // Otherwise, add some details onto the buffer before returning.
    sb.append("\n  " + ex.getClass().getName() + " in ");
    sb.append(stripPrefix(st[0].getClassName()) + "." + st[0].getMethodName()
              + " (line " + st[0].getLineNumber() + ")");
    sb.append("\n  Run tests with -exceptions to print exception " +
              "stack traces.");
    return sb.toString();
  }
  
  /**
   * This method is called from Harness to tidy up.  It prints out appropriate
   * information and returns 0 if the test passed or 1 if it failed.
   * @return 0 if the test passed, 1 if it failed
   */
  protected int done()
  {
    if (failures > 0 && verbose)
      {
        System.out.print("TEST FAILED: ");
        System.out.println(failures + " of " + total + " checks failed "
                           + stripPrefix(description));
      }
    else if (verbose)
      System.out.println("TEST PASSED (" + total + " checks) "
                         + stripPrefix(description));
    if (xpasses > 0)
      System.out.println(xpasses + " of " + total
                         + " tests unexpectedly passed");
    if (xfailures > 0)
      System.out.println(xfailures + " of " + total
                         + " tests expectedly failed");
    return failures > 0 ? 1 : 0;
  }
}