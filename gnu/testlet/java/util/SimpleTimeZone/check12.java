// Test SimpleTimeZone.check12().

// Written by Jerry Quinn <jlquinn@optonline.net>

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

// Test features added by JDK 1.2

package gnu.testlet.java.util.SimpleTimeZone;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.*;

public class check12 implements Testlet
{
  public void test (TestHarness harness)
  {
    int rawOff = -18000000;	// 5 hours
    int dstOff = 3600000;	// 1 hour

    // Create a timezone for UTC-5 with daylight savings starting on
    // the second Monday, April 10 at 12 noon, ending the second
    // Sunday, September 10, 12 noon in daylight savings, 1 hour
    // shift.

    // All three should represent the same period
    SimpleTimeZone tz =
      new SimpleTimeZone(rawOff, "Z1",
			 Calendar.APRIL, 10, 0, 43200000,
			 Calendar.SEPTEMBER, 10, 0, 43200000,
			 dstOff);

    int off;

    // test 1/2 hour before dst
    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 10, 0, 41400000);
    harness.check(off, rawOff);
    
    // test 1/2 hour into dst
    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 10, 0, 45000000);
    harness.check(off, rawOff + dstOff);
    
    // test that nth dayofweek works with day of month rules
    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 2, Calendar.MONDAY, 41400000);
    harness.check(off, rawOff);
    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 2, Calendar.MONDAY, 45000000);
    harness.check(off, rawOff + dstOff);
    
    // test that -nth dayofweek works with day of month rules
    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, -3, Calendar.MONDAY, 41400000);
    harness.check(off, rawOff);
    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, -3, Calendar.MONDAY, 45000000);
    harness.check(off, rawOff + dstOff);

    // Sunday on or before April 4, 2000 is April 2
    // Test arguments get overidden and perform correctly
    tz.setStartRule(Calendar.APRIL, 4, -Calendar.SUNDAY, 43200000, false);

    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 2, 0, 41400000);
    harness.check(off, rawOff);

    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 2, 0, 45000000);
    harness.check(off, rawOff + dstOff);
    
    // Sunday on or after April 4, 2000 is April 9
    // Test arguments get overidden and perform correctly
    tz.setStartRule(Calendar.APRIL, 4, -Calendar.SUNDAY, 43200000, true);

    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 9, 0, 41400000);
    harness.check(off, rawOff);

    off = tz.getOffset(GregorianCalendar.AD, 2000, Calendar.APRIL, 9, 0, 45000000);
    harness.check(off, rawOff + dstOff);
  }
}
