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

public class RunTests extends MatchingTask {
    private boolean verbose=false, debug=false, haltOnFailure=false, failOnError=true;
    private Path sourceDir=null;
    private Vector filesets=new Vector();

    public void execute() throws BuildException {
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

                    harness.runTest(filename);
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


    private static class MyTestHarness extends SimpleTestHarness {
        private boolean haltOnFailure;
        // extend it b/cause someone thought it should not have public constructors.
        public MyTestHarness(boolean verbose, boolean debug, boolean haltOnFailure) {
            super(verbose, debug, false);
            this.haltOnFailure = haltOnFailure;
        }

        protected void runTest (String name) throws BuildException {
            super.runtest(name);
            if(haltOnFailure && getFailures() > 0)
                throw new BuildException("Failures");
        }
    }
}
