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

// Tags: JDK1.1

package gnu.testlet.java.util.Calendar;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Locale;

public class set implements Testlet
{
  public void test (TestHarness harness)
  {
    test_DST(harness);
    test_DAY_OF_MONTH(harness);
    testUnsetFields(harness);
  }
  
  public void test_DST (TestHarness harness)
  {
    // Create a custom TimeZone with a daylight-time period.
    SimpleTimeZone stz = new SimpleTimeZone(60 * 60 * 1000, "MyZone",
		    Calendar.MARCH, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000,
		    Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

    // Register the timezone as the default:
    TimeZone.setDefault(stz);
    
    Calendar cal = Calendar.getInstance(stz);
    Calendar cal2 = Calendar.getInstance(stz);

    cal.set(2004, Calendar.NOVEMBER, 4, 17, 30);

    harness.checkPoint ("Basic set/get");
    
    // Test field basics.
    harness.check (cal.get(Calendar.MINUTE), 30);
    harness.check (cal.get(Calendar.HOUR), 5);
    harness.check (cal.get(Calendar.MONTH), Calendar.NOVEMBER);
    harness.check (cal.get(Calendar.DAY_OF_WEEK), Calendar.THURSDAY);
    harness.check (cal.get(Calendar.AM_PM), Calendar.PM);
    harness.check (cal.get(Calendar.ZONE_OFFSET), 60 * 60 * 1000);
    harness.check (cal.get(Calendar.DST_OFFSET), 0);
    harness.check (cal.get(Calendar.WEEK_OF_MONTH), 1);

    // Now switch months.
    cal.set(Calendar.MONTH, Calendar.APRIL);
    harness.check (cal.get(Calendar.MONTH), Calendar.APRIL);
    harness.check (cal.get(Calendar.HOUR_OF_DAY), 17);

    harness.checkPoint ("moving calendar across DST boundary");
    
    // Check that hour is still correct after moving into a DST period.
    harness.check (cal.getTime().getHours(), 17);

    cal2.setTimeInMillis(cal.getTimeInMillis());
    harness.check (cal2.get(Calendar.HOUR_OF_DAY), 17);
      
    // Restore default timezone
    TimeZone.setDefault(null);
  }
  
  public void test_DAY_OF_MONTH(TestHarness harness)
  {
    harness.checkPoint("setting DAY_OF_MONTH etc shouldn't effect other fields");
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEEEEEE, yyyy-MM-dd [DDD] HH:mm:ss.SSSS", Locale.US);
    c.set(2004, 9, 1, 12, 0, 0);
    c.set(Calendar.MILLISECOND, 0);
    
    String time = df.format(c.getTime());
    harness.check(time, "Friday, 2004-10-01 [275] 12:00:00.0000");
    harness.check (c.get(Calendar.HOUR), 12);
    
    c.set(Calendar.DAY_OF_MONTH, 31);
    time = df.format(c.getTime());
    harness.check(time, "Sunday, 2004-10-31 [305] 12:00:00.0000");

    c.set(Calendar.MONTH, Calendar.JANUARY);
    time = df.format(c.getTime());
    harness.check(time, "Saturday, 2004-01-31 [031] 12:00:00.0000");
  }

  private void testUnsetFields(TestHarness harness)
  {
    Calendar c = Calendar.getInstance();
    c.setTimeZone(TimeZone.getTimeZone("GMT"));
    c.clear();
    c.set(Calendar.YEAR, 1978);
    c.set(Calendar.MONTH, Calendar.AUGUST);
    harness.check(c.getTime(), new Date(270777600000L), // 1978-08-01T00:00Z
		  "A calendar with only YEAR and MONTH set");
  }
}
