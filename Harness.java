// Copyright (c) 2006  Red Hat, Inc.
// Written by Anthony Balkissoon <abalkiss@redhat.com>

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

/*
 * See the README file for information on how to use this
 * file and what it is designed to do.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * The Mauve Harness.  This class parses command line input and standard
 * input for tests to run and runs them in a separate process.  It detects
 * when that separate process is hung and restarts the process.
 * @author Anthony Balkissoon abalkiss at redhat dot com
 *
 */
public class Harness
{    
  // This is the name of the program we will run to actually run the tests.
  private static final String RUNNERPROCESSNAME = "RunnerProcess";

  // How long a test may run before it is considered hung
  private long timeout = 60000; // 60 seconds, can be changed via -timeout flag

  // Whether or not we should recurse into directories when a folder is
  // specified to be tested
  private boolean recursion = true;

  // Whether we should run in noisy mode
  private boolean verbose = false;
  
  // Whether we should display one-line summaries for passing tests
  private boolean showPasses = false;

  // The total number of tests run
  private int total_tests = 0;

  // The total number of failing tests (not harness.check() calls)
  private int total_test_fails = 0;

  // All the tests that were specified on the command line rather than
  // through standard input or an input file
  private Vector commandLineTests = null;

  // All the tests that were explicitly excluded via the -exclude option
  private Vector excludeTests = new Vector();

  // A way to speak to the runner process
  private PrintWriter out = null;

  // A way to listen to the runner process
  private BufferedReader in = null;

  // The process that will run the tests for us
  private Process runnerProcess = null;

  // A watcher to determine if runnerProcess is hung
  private TimeoutWatcher watcher = null;

  // A flag indicating whether or not runnerProcess is hung
  private boolean testIsHung = false;

  // A lock used for synchronizing access to testIsHung
  private Object lock = new Object();

  // The arguments used when this Harness was invoked, we use this to create an
  // appropriate RunnerProcess
  String[] harnessArgs = null;

  // The path to the executable for the VM on which the tests will be run
  String vmCommand = null;
  

  public static void main(String[] args)
  {
    // Create a new Harness, set it up with the args, and run
    // the appropriate tests.
    Harness harness = new Harness();
    harness.setupHarness(args);
  }

  private void setupHarness(String[] args)
  {
    harnessArgs = args;
    String file = null;
    for (int i = 0; i < args.length; i++)
      {        
        if (args[i].equals("-norecursion"))
          recursion = false;
        else if (args[i].equals("-verbose"))
          verbose = true;
        else if (args[i].equals("-showpasses"))
          showPasses = true;
        else if (args[i].equals("-help") || args[i].equals("--help")
                 || args[i].equals("-h"))
          printHelpMessage();
        else if (args[i].equalsIgnoreCase("-file"))
          {
            // User wants to use an input file to specify which tests to run.
            if (++i >= args.length)
              throw new RuntimeException("No file path after '-file'.  Exit");
            file = args[i];
          }
        else if (args[i].equals("-exclude"))
          {
            // User wants to exclude some tests from the run.
            if (++i >= args.length)
              throw new RuntimeException ("No test or directory " +
                    "given after '-exclude'.  Exit");
            if (args[i].endsWith(".java"))
              args[i] = args[i].substring(0, args[i].length() - 5);
            excludeTests.add(startingFormat(args[i]));
          }
        else if (args[i].equals("-timeout"))
          {
            // User wants to change the timeout value.
            if (++i >= args.length)
              throw new RuntimeException ("No timeout value given " +
                    "after '-timeout'.  Exit");
            timeout = Long.parseLong(args[i]);
          }
        else if (args[i].charAt(0) == '-')
          {
            // One of the ignored options (ie - handled by RunnerProcess)
            // such as -verbose.
          }
        else if (args[i] != null)
          {
            // This is a command-line (not standard input) test or directory.
            if (commandLineTests == null)
              commandLineTests = new Vector();
            commandLineTests.add(startingFormat(args[i]));
          }          
      }

    // Determine the VM on which we will run the tests.
    vmCommand = System.getProperties().getProperty("java.vm.exec");
    if (vmCommand == null)
      vmCommand = "java";
    
    // Start the runner process and run all the tests.
    initProcess(vmCommand, args);
    runAllTests(file, commandLineTests);

    if (total_tests > 1)
      System.out.println("\nTEST RESULTS:\n" + total_test_fails + " of "
                         + total_tests + " tests failed.");
    else if (total_tests == 0)
      {
        // If no tests were run, try to help the user out by suggesting what
        // the problem might have been.
        if (recursion == false)
          {
            System.out.println ("No tests were run.\nDid you use -norecursion " +
                    "and specify a folder that had no tests in it?\n" +
                    "For example, 'jamvm -norecursion javax.swing' will not " +
                    "run any tests\nbecause no tests are located directly in " +
                    "the javax.swing folder.\n\nTry removing the -norecursion " +
                    "option.  Use the -help option for more\ninformation or " +
                    "read the README file.");
          }
        else if (excludeTests != null && excludeTests.size() > 0)
          {
            System.out.println ("No tests were run.\nDid you use -exclude " +
                                "and exclude all tests (or all specified tests)? \n" +
                                "Use the -help option for more information or " +
                                "read the README file.");
          }
        else
          {
            System.out.println ("No tests were run.\nDid you specify a test that " +
                                "doesn't exist or a folder that contains no tests? \n" +
                                "Use the -help option for more information or " +
                                "read the README file.");
          }          
      }
    else if (total_test_fails == 0 && !showPasses)
      System.out.println ("TEST RESULT: pass");
    finalize();
    System.exit(total_test_fails > 0 ? 1 : 0);
  }  
  
  /**
   * This method takes a String and puts it into a consistent format
   * so we can deal with all test names in the same way.  It ensures
   * that tests start with "gnu.testlet" and that slashes ('/', which
   * are file separators) are replaced with dots (for use in class names).
   * It also strips the .java or .class extensions if they are present, 
   * and removes single trailing dots.
   * @param val
   * @return
   */
  private static String startingFormat(String val)
  {
    if (val != null)
      {
        val = val.replace(File.separatorChar, '.');
        if (! val.startsWith("gnu.testlet."))
          val = "gnu.testlet." + val;
        if (val.endsWith("."))
          val = val.substring(0, val.length() - 1);
        if (val.endsWith(".class"))
          val = val.substring(0, val.length() - 6);
      }
    return val;
  }
  
  /**
   * This method prints a help screen to the console and then exits.
   */
  static void printHelpMessage()
  {
    System.out.println(
            "This is the Mauve Harness.  Usage:\n\n" +
            " ./harness <options> <testcase | folder>\n" +
            "  If no testcase or folder is specified, all the tests will be run. \n" +
            "  It is strongly recommended that you use the -vm option or set the \n" +
            "  environment variable MAUVEVM." +
            "\n\nExample: './harness -vm jamvm -showpasses javax.swing'\n" +
            "  will use jamvm (located in your path) to run all the tests in the\n" +
            "  gnu.testlet.javax.swing folder and will display PASSES\n" +
            "  as well as FAILS.\n\nOptions:\n" +
            "  -vm [vmpath]:            specify the vm on which to run the tests\n" +
            "                           It is strongly recommended that you use this option\n" +
            "                           or set the MAUVEVM environment variable.  See the \n" +
            "                           README file for more details.\n" +
            "  -exclude [test|folder]:  specifies a test or a folder to exclude\n" +
            "                           from the run\n" +
            "  -norecursion:            if a folder is specified to be run, don't run\n" +
            "                           the tests in its subfolders\n" +
            "  -showpasses:             display passing tests as well as failing ones\n" +
            "  -exceptions:             print stack traces for uncaught exceptions\n" +
            "  -timeout [millis]:       specifies a timeout value for the tests\n" +
            "                           (default is 60000 milliseconds)\n" +
            "  -verbose:                run in noisy mode, displaying extra information\n" +
            "  -file [filename]:        specifies a file that contains the names of\n" +
            "                           tests to be run (one per line)\n" +
            "  -debug:                  displays some extra information for failing tests that\n" +
            "                           use the harness.check(Object, Object) method\n" +
            "  -xmlout [filename]:      specifies a file to use for xml output\n" +
            "  -help:                   display this help message\n");
                       
    System.exit(0);
  }
  
  protected void finalize()
  {
    //Clean up 
    try
      {
        in.close();
        out.close();
        runnerProcess.destroy();
      } 
    catch (IOException e) 
      {
        System.err.println("Could not close the interprocess pipes.");
        System.exit(-1);
      }
  }
  
  /**
   * This method sets up our runner process - the process that actually
   * runs the tests.  This needs to be done once initially and also
   * every time a test hangs.
   * @param runtime the VM to use (ie "java", "jamvm", etc).
   * @param name the name 
   * @param args
   */
  private void initProcess(String runtime, String[] args)
  {
    String command = runtime + " " + RUNNERPROCESSNAME;
    
    for (int i = 0; i < args.length; i++)      
      command += " " + args[i];
      
    try
      {
        runnerProcess = Runtime.getRuntime().exec(command);
        out = new PrintWriter(runnerProcess.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(runnerProcess.getInputStream()));
        watcher = new TimeoutWatcher(timeout);
      }
    catch (IOException e)
      {
        System.err.println("Problems invoking RunnerProcess: " + e);
        finalize();
        System.exit(1);
      }
  }

  /**
   * This method runs all the tests, both from the command line and from
   * standard input.  This is so the legacy method of running tests by 
   * echoing the classname and piping it to the Harness works, but so does
   * a more natural "jamvm Harness <TESTNAME>".
   * @param file the file input of testnames to run
   * @param commandLineTests the Vector of tests that were specified on the
   * command line
   */
  private void runAllTests(String file, Vector commandLineTests)
  {   
    // Run the commandLine tests.  These were assembled into 
    // <code>commandLineTests</code> in the setupHarness method.
    if (commandLineTests != null)
      {
        for (int i = 0; i < commandLineTests.size(); i++)
          {
            String cname = null;
            cname = (String) commandLineTests.elementAt(i);
            if (cname == null)
              break;
            processTest(cname);
          }
      }
    
    
    // Now run the standard input tests.  First we determine if the input is
    // coming from a file (if the -file option was used) or from stdin.
    BufferedReader r = null;
    if (file != null)
      // The -file option was used, so set up our BufferedReader to use the
      // input file.
      try
        {
          r = new BufferedReader(new FileReader(file));
        }
      catch (FileNotFoundException x)
        {
          throw new RuntimeException("Cannot find \"" + file + "\".  Exit");
        }
    else
      {
        // The -file option was not used, so use stdin instead.
        r = new BufferedReader(new InputStreamReader(System.in));
        try
          {
            if (! r.ready())
              {
                // If no tests were specified to be run, we will run all the 
                // tests (except those explicitly excluded).
                if (commandLineTests == null || commandLineTests.size() == 0)
                  processTest("gnu.testlet.all");
                return;
              }
          }
        catch (IOException ioe)
          {
          }
      }

    // Now process all the tests specified in the file or from stdin.
    while (true)
      {
        String cname = null;
        try
          {
            cname = r.readLine();
            if (cname == null)
              break;
          }
        catch (IOException x)
          {
            // Nothing.
          }
        processTest(startingFormat(cname));
      }
  }
  
  /**
   * This method runs a single test in a new Harness and increments the
   * total tests run and total failures, if the test fails.  Prints
   * PASS and adds to the report, if the appropriate options are enabled.
   * @param testName the name of the test
   */
  private void runTest(String testName)
  {
    String outputFromTest;
    int temp = -1;

    // Start the timeout watcher
    if (watcher.isAlive())
      watcher.reset();
    else
      watcher.start();
    
    // Tell the RunnerProcess to run test with name testName
    out.println(testName);
    
    while (true)
      {
        // This while loop polls for output from the test process and 
        // passes it to System.out unless it is the signal that the 
        // test finished properly.  Also checks to see if the watcher
        // thread has declared the test hung and if so ends the process.
        if (testIsHung)
          {
            synchronized (lock)
            {
              testIsHung = false;
            }
            finalize();
            initProcess(vmCommand, harnessArgs);
            break;
          }
        try
        {
          if (in.ready())
            {
              outputFromTest = in.readLine();              
              if (outputFromTest.startsWith("RunnerProcess:"))
                {
                  // This means the test finished properly, now have to see if
                  // it passed or failed.
                  if (outputFromTest.endsWith("pass"))
                    temp = 0;
                  else if (outputFromTest.endsWith("fail"))
                    temp = 1;
                  else if (outputFromTest.endsWith("not-a-test"))
                    {
                      // Temporarily decrease the total number of tests,
                      // because it will be incremented later even 
                      // though the test was not a real test.
                      total_tests--;
                      temp = 0;
                    }
                  break;
                }                
              else
                // This means it was just output from the test, like a 
                // System.out.println within the test itself, we should
                // pass these on to stdout.
                System.out.println(outputFromTest);
            }
        }
        catch (IOException e)
        {
        }
      }
    if (temp == -1)
      {
        // This means the watcher thread had to stop the process 
        // from running.  So this is a fail.
        if (verbose)
          System.out.println("  FAIL: timed out. \nTEST FAILED: timeout "
                             + stripPrefix(testName));
        else
        System.out.println("FAIL: " + stripPrefix(testName)
                           + "\n  Test timed out.  Use -timeout [millis] " +
                                "option to change the timeout value.");
        
        total_test_fails++;
      }
    else
      total_test_fails += temp;
    total_tests ++;
    
    // If the test passed and the user wants to know about passes, tell them.
    if (showPasses && temp == 0 && !verbose)
      System.out.println ("PASS: "+stripPrefix(testName));
  }
  
  /**
   * This method is used to potentially run a single test.  If runAnyway is
   * false we've reached here as a result of processing a directory and we
   * should only run tests if they end in ".java" to avoid running tests
   * multiple times.
   *  
   * @param cname the name of the test to run
   * @param runAnyway true if we should run the test even if it doesn't end
   * with ".java"
   * @return -1 if the test was explicitly excluded via the -exclude option,
   * 0 if cname represents a single test, 1 if cname does not represent a 
   * single test
   */  
  private int processSingleTest(String cname, boolean runAnyway)
  {
    if (cname.endsWith(".java"))
      {
        runAnyway = true;
        // FIXME: we need to invoke the compiler here in case the .class
        // is not present or is out of date.
        
        cname = cname.substring(0, cname.length() - 5);
        String temp = cname.replace('.', File.separatorChar) + ".class";
        File f = new File(temp);
        if (!f.exists())
          return -1;
      }
    // Avoid running tests multiple times by quitting if this method was 
    // called from processDirectory and the file wasn't a .java file.
    if (!runAnyway)
      return -1;

    // If the test should be excluded return -1, this is a signal
    // to processTest that it should quit.
    if (excludeTests.contains(cname))
      return -1;

    // Check if cname represents a single test, and if so run it.
    try
      {
        Class.forName(cname);
      }
    catch (Throwable t)
      {
        // This means it wasn't a single test.
        return 1;
      }
    // If we've reached here, we've found a legitimate test, so run it and then
    // return 0 to say that everything was fine.
    runTest(cname);
    return 0;
  }
  
  /**
   * This method processes all the tests in a directory.  To avoid running
   * tests twice, we pass <code>false</code> as the 2nd argument to 
   * processSingleTest.  This method calls itself if it finds a directory.
   * @param cname the name of the directory
   */
  private void processDirectory(String cname)
  {
    cname = cname.replace('.', File.separatorChar);
    File dir = new File(cname);
    if (! dir.exists())
      return;
    
    String[] filenames = dir.list();
    if (filenames == null)
      return;
    
    // Look through all the files and folders in dir, call this method
    // on any folders and call processSingleTest on any files.  Since we pass
    // false as our 2nd argument to processSingleTest, we will not run tests
    // twice (ie, one for the ".java" file and one for the ".class file).
    for (int k = 0; k < filenames.length; k++)
      {
        String temp = dir.getPath() + File.separatorChar + filenames[k];
        File f = new File(temp);
        // If it's a directory, call this method on it.
        if (f.isDirectory() && recursion
            && ! excludeTests.contains(startingFormat(temp)))
          processDirectory(temp);
        else
          processSingleTest(temp.replace(File.separatorChar, '.'), false);
      }
  }
  /**
   * This method handles the input, whether it is a single test or a folder
   * and calls runTest on the appropriate .class files.  Will also compile
   * tests that haven't been compiled or that have been changed since last
   * being compiled.
   * @param cname the input file name - may be a directory
   */
  private void processTest(String cname)
  {
    if (cname.equals("CVS") || cname.endsWith(File.separatorChar + "CVS")
        || cname.indexOf("$") != - 1 || excludeTests.contains(cname))
      return;

    if (cname.equals("gnu.testlet.all"))
      cname = "gnu.testlet";

    // If processSingleTest returns -1 then this test was explicitly 
    // excluded with the -exclude option, and if it returns 0 then 
    // the test was successfully run and we should stop here.  Only
    // if it returns 1 should we try to process cname as a directory.
    if (processSingleTest(cname, true) == 1)
      processDirectory(cname);    
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
   * This class is used for our timer to cancel tests that have hung.
   * @author Anthony Balkissoon abalkiss at redhat dot com
   *
   */
  class TimeoutWatcher implements Runnable
  {
    private long millisToWait;
    private Thread watcherThread;
    boolean loop = true;
    
    /**
     * Creates a new TimeoutWatcher that will wait for <code>millis</code>
     * milliseconds once started.
     * @param millis the number of milliseconds to wait before declaring the 
     * test as hung
     */
    public TimeoutWatcher(long millis)
    {
      millisToWait = millis;
      watcherThread = new Thread(this);
    }
    
    /**
     * Start the watcher thread, ie start the countdown.     
     */
    public void start()
    {
      watcherThread.start();      
    }
    
    /**
     * Return true if the watcher thread is currently counting down.
     * @return true if the watcher thread is alive
     */
    public boolean isAlive()
    {
      return watcherThread.isAlive();
    }
    
    /**
     * Reset the counter and wait another <code>millisToWait</code>
     * milliseconds before declaring the test as hung.     
     */
    public synchronized void reset()
    {
      loop = true;
      notify();
    }
    
    public synchronized void run()
    {
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
      while (loop)
        {
          // We set loop to false here, it will get reset to true if 
          // reset() is called from the main Harness thread.
          loop = false;
          try
          {
            wait(millisToWait);
          }
          catch (InterruptedException ie)
          {}
        }
      // The test is hung, set testIsHung to true so the process will be 
      // destroyed and restarted.
      synchronized (lock)
        {
          testIsHung = true;
        }
    }
  }
}
