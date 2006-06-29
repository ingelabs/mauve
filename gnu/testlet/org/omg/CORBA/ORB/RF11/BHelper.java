// Tags: not-a-test
// Copyright (c) 2000, 2001 NEC Corporation.

// Adapted for Mauve by Audrius Meskauskas <audriusa@bluewin.ch>

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
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

/*
This code originally came from the OMG's CORBA Open Source Testing project,
which lived at cost.omg.org. That site no longer exists.

All the contributing companies agreed to release their tests under the
terms of the GNU Lesser General Public License, available in the file
COPYING.LIB.

The code has been modified integrating into Mauve test environment and
removing tests that are not yet supported by Suns jre 1.4. Hence the license
is now GPL.

We downloaded the code from http://sourceforge.net/projects/corba-cost/,
administrated by Duncan Grisby.
*/


// B
package gnu.testlet.org.omg.CORBA.ORB.RF11;

public abstract class BHelper
{
  private static String _id = "IDL:gnu/testlet/org/omg/CORBA/ORB/RF11/B:1.0";

  public static void insert(org.omg.CORBA.Any a, B that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream();
    a.type(type());
    write(out, that);
    a.read_value(out.create_input_stream(), type());
  }

  public static B extract(org.omg.CORBA.Any a)
  {
    return read(a.create_input_stream());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;

  public static synchronized org.omg.CORBA.TypeCode type()
  {
    if (__typeCode == null)
      {
        __typeCode =
          org.omg.CORBA.ORB.init().create_enum_tc(BHelper.id(), "B",
                                                  new String[] { "b1", "b2", "b3" }
                                                 );
      }
    return __typeCode;
  }

  public static String id()
  {
    return _id;
  }

  public static B read(org.omg.CORBA.portable.InputStream istream)
  {
    return B.from_int(istream.read_long());
  }

  public static void write(org.omg.CORBA.portable.OutputStream ostream, B value)
  {
    ostream.write_long(value.value());
  }
}
