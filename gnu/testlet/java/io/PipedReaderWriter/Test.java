/*************************************************************************
/* Test.java -- Tests Piped{Reader,Writer}
/*
/* Copyright (c) 1998 Free Software Foundation, Inc.
/* Written by Aaron M. Renn (arenn@urbanophile.com)
/*
/* This program is free software; you can redistribute it and/or modify
/* it under the terms of the GNU General Public License as published 
/* by the Free Software Foundation, either version 2 of the License, or
/* (at your option) any later version.
/*
/* This program is distributed in the hope that it will be useful, but
/* WITHOUT ANY WARRANTY; without even the implied warranty of
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/* GNU General Public License for more details.
/*
/* You should have received a copy of the GNU General Public License
/* along with this program; if not, write to the Free Software Foundation
/* Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307 USA
/*************************************************************************/

// Tags: JDK1.1

package gnu.testlet.java.io.PipedReaderWriter;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.*;

public class Test implements Testlet
{

public void
test(TestHarness harness)
{
  // Set up a reasonable buffer size for this test if one is not already
  // specified.  This affects Classpath only.
  //String prop = System.getProperty("gnu.java.io.pipe_size");
  //if (prop == null)
  //  System.setProperty("gnu.java.io.pipe_size", "32");
  // Hmm, we need JDK 1.2 for the above

  try
    {
      // Set up the thread to write
      PipedTestWriter ptw = new PipedTestWriter(harness);
      String str = ptw.getStr();
      PipedWriter pw = ptw.getWriter();

      // Now set up our reader
      PipedReader pr = new PipedReader();
      pr.connect(pw); 
      new Thread(ptw).start();

      char[] buf = new char[12];
      int chars_read, total_read = 0;
      while((chars_read = pr.read(buf)) != -1)
        {
          harness.debug(new String(buf, 0, chars_read), false);
          System.gc(); // A short delay
          total_read += chars_read;
        }

      harness.check(total_read, str.length(), "total_read");
    }
  catch (IOException e) 
    {
      harness.debug("In reader: " + e);
      harness.check(false);
    }
}

} // class Test

class PipedTestWriter implements Runnable
{

String str;
StringReader sbr;
PipedWriter out;
TestHarness harness;

public
PipedTestWriter(TestHarness harness)
{
  this.harness = harness;

  str = "In college, there was a tradition going for a while that people\n" +
    "would get together and hang out at Showalter Fountain - in the center\n" +
    "of Indiana University's campus - around midnight.  It was mostly folks\n" +
    "from the computer lab and just people who liked to use the Forum\n" +
    "bbs system on the VAX.  IU pulled the plug on the Forum after I left\n" +
    "despite its huge popularity.  Now they claim they are just giving\n" +
    "students what they want by cutting deals to make the campus all\n" +
    "Microsoft.\n";

  sbr = new StringReader(str);

  out = new PipedWriter();
}

public PipedWriter
getWriter()
{
  return(out);
}

public String
getStr()
{
  return(str);
}

public void
run() 
{
  char[] buf = new char[32];

  int chars_read;

  try
    {
      int b = sbr.read();
      out.write(b);

      while ((chars_read = sbr.read(buf)) != -1)
        out.write(buf, 0, chars_read);

      out.close();
    }
  catch(IOException e)
    {
      harness.debug("In Writer: " + e);
      harness.check(false);
    }
}

} // PipedTestWriter

