// Test of PhantomReference

// Copyright (C) 2001 Red Hat, Inc.

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

// Tags: JDK1.2

// In this test we make some assumptions about how the GC operates
// that are probably not quite sound.  In particular we assume
// System.gc() will collect everything.

package gnu.testlet.java.lang.ref.PhantomReference;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.lang.ref.*;

public class phantom implements Testlet
{
  public static int final_count = 0;

  public phantom ()
  {
  }

  public void finalize ()
  {
    ++final_count;
  }

  public PhantomReference genRef (ReferenceQueue q, Object o)
  {
    return new PhantomReference (o, q);
  }

  public PhantomReference try1 (ReferenceQueue q, TestHarness harness)
  {
    phantom twt = new phantom ();
    PhantomReference wr = genRef (q, twt);

    // Give the runtime some hints that it should really garbage collect.
    System.gc ();
    System.runFinalization();
    System.gc ();
    System.runFinalization();

    Reference r = q.poll ();
    harness.check (r, null, "live reference");
    harness.check (final_count, 0);

    // Must keep the phantom object live.
    System.out.println(twt);

    return wr;
  }

  public void test (TestHarness harness)
  {
    ReferenceQueue q = new ReferenceQueue ();

    PhantomReference wr = try1 (q, harness);
    System.gc ();
    System.runFinalization();
    System.gc ();
    System.runFinalization();

    Reference r = null;
    try
      {
	r = q.remove (5 * 1000); // 5 seconds.
      }
    catch (InterruptedException _)
      {
	harness.debug (_);
      }

    harness.check (r, wr, "unreachable");
    harness.check (final_count, 1, "object finalized");

    // Make sure we're not GCed while running the test.
    System.out.println(this);
  }
}
