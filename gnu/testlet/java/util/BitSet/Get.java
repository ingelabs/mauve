// Tags: JDK1.4

/* Copyright (C) 2005 David Daney

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
   Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.util.BitSet;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.BitSet;

public class Get implements Testlet
{
    public void test ( TestHarness harness )
    {
        BitSet o = new BitSet(70);
        o.set(1);
        o.set(2);
        o.set(63);
        o.set(64);
        
        BitSet s1 = o.get(0, 9);
        harness.check(s1.cardinality(), 2);
        harness.check(s1.get(0), false);
        harness.check(s1.get(1), true);
        harness.check(s1.get(2), true);
        harness.check(s1.get(3), false);

        BitSet s2 = o.get(60, 69);
        harness.check(s2.cardinality(), 2);
        harness.check(s2.get(2), false);
        harness.check(s2.get(3), true);
        harness.check(s2.get(4), true);
        harness.check(s2.get(5), false);
    }
}
