// raf.java - Test RandomAccessFile

// Copyright (c) 2002 Free Software Foundation, Inc.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published 
// by the Free Software Foundation, either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software Foundation
// Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307 USA

// Tags: JDK1.1

package gnu.testlet.java.io.RandomAccessFile;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.*;

public class raf implements Testlet
{
  public void test (TestHarness harness)
  {
    boolean ok = true;

    try
      {
	RandomAccessFile raf = new RandomAccessFile("raftmpfile", "rw");
	raf.writeUTF("foobar");
	raf.seek(0);

	int skipped;
	while ((skipped = raf.skipBytes(1)) == 1)
	  harness.debug("skipped 1 byte");

	if (skipped != 0)
	  {
	    ok = false;
	    harness.debug("What's going on?  skipped = " + skipped +
			  ", not 0");
	  }
	if (raf.getFilePointer() != raf.length())
	  {
	    ok = false;
	    harness.debug("What's going on?  filePointer != length!");
	    harness.debug("  filePointer = " + raf.getFilePointer());
	    harness.debug("  length = " + raf.length());
	  }
      }
    catch (IOException _)
      {
	ok = false;
      }

    harness.check(ok);
  }
}
