/*************************************************************************
/* WriteRead2.java -- Tests Data{Input,Output}Stream's
/*
/* Copyright (c) 1998, 1999 Free Software Foundation, Inc.
/* Written by Daryl Lee <dol@sources.redhat.com>
/* Shameless ripoff of WriteRead.java by Aaron M. Renn (arenn@urbanophile.com)
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

// This test contains the JDK 1.1 tests not included in WriteRead.java

package gnu.testlet.java.io.DataOutputStream;

import java.io.*;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import gnu.testlet.java.io.DataInputStream.ReadStream2;

public class WriteRead2 implements Testlet
{

public void
test(TestHarness harness)
{
  // First write it.
  try
    {
      FileOutputStream fos = new FileOutputStream("dataoutput2.out");
      DataOutputStream dos = new DataOutputStream(fos);
	  byte[] b = {97, 98, 99, 100, 101, 102};  // "abcdef"

	  dos.writeChars("Random String One");
	  dos.writeBytes("Random String Two");
	  dos.write('X');
	  dos.write(b, 0, b.length);
	  dos.writeByte(12);
	  dos.writeShort(1234);
      dos.flush();
	  harness.check(true, "flush()");
	  harness.check(dos.size(), 61, "size()");
	  dos.close();
      harness.check(true, "DataOutputStream write (2, conditionally");
    }
  catch (Exception e)
    {
      harness.debug(e);
      harness.check(false, "DataOutputStream write(2)");
      return;
    }

  // Now read it
  try
    {
      FileInputStream is = new FileInputStream("dataoutput2.out");
      DataInputStream dis = new DataInputStream(is); 

      harness.debug("Reading data written during write phase.");
      ReadStream2.runReadTest(dis, harness);

      dis.close();
    }
  catch (Exception e)
    {
      harness.debug(e);
      harness.check(false, "Read data written during write phase");
    }
}

} // class WriteRead

