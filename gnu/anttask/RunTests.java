/*
Copyright (c) 2004 Thomas Zander <zander@kde.org>

This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to
the Free Software Foundation, 59 Temple Place - Suite 330,
Boston, MA 02111-1307, USA. */

package gnu.anttask;

import gnu.testlet.*;
import java.io.*;
import java.util.*;

import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.*;
import org.apache.tools.ant.types.*;

/**
 * Ant task to run Mauve test-suite.
 * <p>Before asking questions; always refer to the <a href="http://jakarta.apache.org/ant/manual/index.html">ant manual</a> first.</p>
    <p>Test classes are passed as child fileset tags, typically you simply pass the build
        dir for the project</p>
    <h3>Parameters</h3>
    <table border="1" cellpadding="2" cellspacing="0">
      <tr>
        <td valign="top"><b>Attribute</b></td>
        <td valign="top"><b>Description</b></td>
        <td align="center" valign="top"><b>Required</b></td>
      </tr>
      <tr>
        <td valign="top">debug</td>
        <td valign="top">Prints additional debug info for the tests</td>
        <td align="center" valign="top">No</td>
      </tr>
      <tr>
        <td valign="top">verbose</td>
        <td valign="top">Lets the test suite be more verbose; also showing passed-tests for example</td>
        <td align="center" valign="top">No</td>
      </tr>
      <tr>
        <td valign="top">haltonfailure</td>
        <td valign="top">make the testrun halt as soon as a failure is failed (see also failonerror)</td>
        <td align="center" valign="top">No</td>
      </tr>
      <tr>
        <td valign="top">srcdir</td>
        <td valign="top">provide the sources directory allowing the parsing of a "Tags:" comment in the header of the file.  If the testJDK or testJDBC are also supplied then tests that have tags specifying that their API comatibility are incompatible with the testJDK/testJDBC version will be skipped.</td>
        <td align="center" valign="top">No</td>
      </tr>
      <tr>
        <td valign="top">testJDK</td>
        <td valign="top">The version of the JDK you are running/simulating. Only tests that are compatible with this version will be run.</td>
        <td align="center" valign="top">No</td>
      </tr>
      <tr>
        <td valign="top">testJDBC</td>
        <td valign="top">The version of the JDBC API you are running/simulating. Only tests that are compatible with this version will be run.</td>
        <td align="center" valign="top">No</td>
      </tr>
      <tr>
        <td valign="top">failonerror</td>
        <td valign="top">If a test fails, should that be flagged as an error? Setting this and haltonfailure to true will stop the run as soon as a failure is found.</td>
        <td align="center" valign="top">No</td>
      </tr>
    </table>
 */
public class RunTests extends MatchingTask {
    private boolean verbose=false, debug=false, haltOnFailure=false, failOnError=true;
    private File sourceDir=null;
    private Vector filesets=new Vector();
    private double javaVersion=0d, JDBCVersion=0d;

    public void execute() throws BuildException {
        if(sourceDir == null)
            System.err.println("Warning; without 'srcdir' element no Tag checking will be done");
        MyTestHarness harness = new MyTestHarness (verbose, debug, haltOnFailure);

        Iterator iter = filesets.iterator();
        while(iter.hasNext()) {
            FileSet fs = (FileSet) iter.next();
            try {
                DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                Iterator classNames = Arrays.asList(ds.getIncludedFiles()).iterator();
                while(classNames.hasNext()) {
                    String filename = (String) classNames.next();
                    if(! filename.endsWith(".class"))
                        continue; // only class files
                    if(filename.lastIndexOf("$") > filename.lastIndexOf(File.separator))
                        continue; // no inner classeS
                    filename=filename.substring(0, filename.length()-6);
                    filename=filename.replace(File.separatorChar, '.');
                    if(shouldRunTest(filename)) {
                        if(!verbose)
                            System.out.println("Run: "+ filename);
                        harness.runTest(filename);
                    }
                }
            } catch (BuildException be) {
                // directory doesn't exist or is not readable
                if (failOnError)
                    throw be;
                else
                    log(be.getMessage(),Project.MSG_WARN);
            }
        }
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setHaltOnFailure(boolean on) {
        haltOnFailure = on;
    }

    public void setFailOnError(boolean on) {
        failOnError = on;
    }

    public void addFileset(FileSet set) {
        filesets.add(set);
    }

    public void setSrcdir(File sourceDir) {
        this.sourceDir = sourceDir;
    }

    public void setTestJDK(String jdk) {
        jdk=jdk.toLowerCase().trim();
        if(jdk.startsWith("jdk")) {
            try {
                javaVersion = Double.parseDouble(jdk.substring(3));
                return;
            } catch(NumberFormatException e) {
            }
        }
        System.err.println("Failed to parse the testJDK argument; format is: 'JDK1.4'");
    }

    public void setTestJDBC(String jdbc) {
        jdbc=jdbc.toLowerCase().trim();
        if(jdbc.startsWith("jdbc")) {
            try {
                javaVersion = Double.parseDouble(jdbc.substring(4));
                return;
            } catch(NumberFormatException e) {
            }
        }
        System.err.println("Failed to parse the testJDBC argument; format is: 'JDBC2.0'");
    }

    private boolean shouldRunTest(String filename) {
        if(sourceDir == null)
            return true;
        File sourceFile = new File(sourceDir, filename.replace('.', File.separatorChar)+".java");
        if(! sourceFile.exists())
            return false;
        try {
            Reader reader = new FileReader(sourceFile);
            StringBuffer buf = new StringBuffer();
            int maxLines=30;
            while(maxLines > 0) {
                int character = reader.read();
                if(character == -1)
                    break;
                if(character == '\n') {
                    int index = buf.indexOf("Tags:") + 5; // 5 == length of string
                    if(index > 5 && buf.length() > index) {
                        String tags = buf.substring(index).trim().toLowerCase();
                        if("not-a-test".equals(tags))
                            return false;
                        try {
                            return new Tags(tags).isValid(javaVersion, JDBCVersion);
                        } catch(NumberFormatException e) {
                            System.err.println("Unreadable tags in class: "+ filename);
                            return false;
                        }
                    }
                    buf = new StringBuffer();
                    maxLines--;
                }
                else
                    buf.append((char) character);
            }
        } catch(IOException e) { }
        return false;
    }


    private static class MyTestHarness extends SimpleTestHarness {
        private boolean haltOnFailure;
        // extend it b/cause someone thought it should not have public constructors.
        public MyTestHarness(boolean verbose, boolean debug, boolean haltOnFailure) {
            super(verbose, debug, false, null);
            this.haltOnFailure = haltOnFailure;
        }

        protected void runTest (String name) throws BuildException {
            super.runtest(name);
            if(haltOnFailure && getFailures() > 0)
                throw new BuildException("Failures");
        }
    }
}
