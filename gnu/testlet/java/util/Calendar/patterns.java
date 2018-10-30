package gnu.testlet.java.util.Calendar;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.text.ParseException;


public class patterns implements Testlet
{
  public void test (TestHarness harness)
  {
    testPatterns(harness);
  }

  private Calendar newCalendar()
  {
    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
    c.clear();
    return c;
  }

  private void testPatterns(TestHarness harness)
  {
    Calendar c;

    harness.checkPoint("Inconsistent data");
    /*
      If there is any conflict in calendar field values, Calendar gives
      priorities to calendar fields that have been set more recently. The
      following are the default combinations of the calendar fields. The most
      recent combination, as determined by the most recently set single field,
      will be used.

      For the date fields:

      YEAR + MONTH + DAY_OF_MONTH
      YEAR + MONTH + WEEK_OF_MONTH + DAY_OF_WEEK
      YEAR + MONTH + DAY_OF_WEEK_IN_MONTH + DAY_OF_WEEK
      YEAR + DAY_OF_YEAR
      YEAR + DAY_OF_WEEK + WEEK_OF_YEAR


      For the time of day fields:

      HOUR_OF_DAY
      AM_PM + HOUR
    */

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.JANUARY);
    c.set(Calendar.DAY_OF_MONTH, 1);
    c.set(Calendar.DAY_OF_YEAR, 10);
    c.set(Calendar.WEEK_OF_MONTH, 1);
    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
    c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    c.set(Calendar.WEEK_OF_YEAR, 3);
    // YEAR + DAY_OF_WEEK + WEEK_OF_YEAR should be used, as WEEK_OF_YEAR is set
    // in the last place
    harness.check(c.getTime(), new Date(1516060800000L)); // 2018-01-16T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.FEBRUARY);
    c.set(Calendar.WEEK_OF_YEAR, 3);
    c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
    c.set(Calendar.WEEK_OF_MONTH, 2);
    // YEAR + MONTH + WEEK_OF_MONTH + DAY_OF_WEEK should be used, as
    // WEEK_OF_MONTH is set in the last place
    harness.check(c.getTime(), new Date(1517875200000L)); // 2018-02-06T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.JANUARY);
    c.set(Calendar.WEEK_OF_YEAR, 3);
    c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
    c.set(Calendar.WEEK_OF_MONTH, 1);
    c.set(Calendar.DAY_OF_MONTH, 3);
    // YEAR + MONTH + DAY_OF_MONTH should be used, as DAY_OF_MONTH is set in the
    // last place
    harness.check(c.getTime(), new Date(1514937600000L)); // 2018-01-03T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.JANUARY);
    c.set(Calendar.WEEK_OF_MONTH, 1);
    c.set(Calendar.WEEK_OF_YEAR, 3);
    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
    c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    // YEAR + MONTH + DAY_OF_WEEK_IN_MONTH + DAY_OF_WEEK should be used.
    // DAY_OF_WEEK matches more than one pattern, so the field set before that
    // should be used to determine the pattern to use
    harness.check(c.getTime(), new Date(1515456000000L)); // 2018-01-09T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.WEEK_OF_MONTH, 1);
    c.set(Calendar.WEEK_OF_YEAR, 3);
    c.set(Calendar.DAY_OF_MONTH, 5);
    c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
    c.set(Calendar.MONTH, Calendar.JANUARY);
    // MONTH, as opposed to DAY_OF_WEEK, is ignored in inconsistency resolution,
    // so YEAR + MONTH + DAY_OF_WEEK_IN_MONTH + DAY_OF_WEEK
    harness.check(c.getTime(), new Date(1515456000000L)); // 2018-01-09T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.DAY_OF_YEAR, 50);
    c.set(Calendar.WEEK_OF_YEAR, 3);
    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
    // YEAR + DAY_OF_YEAR, because complete patterns have priority
    harness.check(c.getTime(), new Date(1518998400000L)); // 2018-01-09T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.FEBRUARY);
    c.set(Calendar.DAY_OF_MONTH, 5);
    c.set(Calendar.HOUR, 7);
    c.set(Calendar.AM_PM, 1);
    c.set(Calendar.HOUR_OF_DAY, 3);
    // HOUR_OF_DAY over HOUR + AM_PM
    harness.check(c.getTime(), new Date(1517799600000L)); // 2018-02-05T03:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.FEBRUARY);
    c.set(Calendar.DAY_OF_MONTH, 5);
    c.set(Calendar.HOUR, 7);
    c.set(Calendar.HOUR_OF_DAY, 3);
    c.set(Calendar.AM_PM, 1);
    // HOUR + AM_PM over HOUR_OF_DAY
    harness.check(c.getTime(), new Date(1517857200000L)); // 2018-02-05T19:00Z


    harness.checkPoint("Incomplete pattern");
    /*
      If there are any calendar fields whose values haven't been set in the
      selected field combination, Calendar uses their default values. The
      default value of each field may vary by concrete calendar systems. For
      example, in GregorianCalendar, the default of a field is the same as that
      of the start of the Epoch: i.e., YEAR = 1970, MONTH = JANUARY,
      DAY_OF_MONTH = 1, etc.
    */

    c = newCalendar();
    c.set(Calendar.MONTH, Calendar.OCTOBER);
    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 3);
    harness.check(c.getTime(), new Date(25056000000L)); // 1970-10-18T01:00Z

    c = newCalendar();
    c.set(Calendar.WEEK_OF_YEAR, 2);
    harness.check(c.getTime(), new Date(259200000L)); // 1970-01-04T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.DATE, 35);
    harness.check(c.getTime(), new Date(1517702400000L)); // 2018-02-04T01:00Z

    c = newCalendar();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.NOVEMBER);
    c.set(Calendar.WEEK_OF_MONTH, 3);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    harness.check(c.getTime(), new Date(1541980800000L)); // 2018-11-12T01:00Z

    c = newCalendar();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.NOVEMBER);
    c.set(Calendar.WEEK_OF_MONTH, 3);
    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    harness.check(c.getTime(), new Date(1542499200000L)); // 2018-11-18T01:00Z

    c = newCalendar();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.NOVEMBER);
    c.set(Calendar.WEEK_OF_MONTH, 3);
    c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    harness.check(c.getTime(), new Date(1542067200000L)); // 2018-11-13T01:00Z

    c = newCalendar();
    c.set(Calendar.YEAR, 2030);
    c.set(Calendar.DAY_OF_YEAR, 3650);
    harness.check(c.getTime(), new Date(2208729600000L)); // 2039-12-29T01:00Z

    c = newCalendar();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.WEEK_OF_YEAR, Calendar.NOVEMBER);
    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    harness.check(c.getTime(), new Date(1520726400000L)); // 2039-12-29T01:00Z

    c = newCalendar();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.WEEK_OF_YEAR, Calendar.NOVEMBER);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    harness.check(c.getTime(), new Date(1520208000000L)); // 2018-03-05T01:00Z

    c = newCalendar();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.WEEK_OF_YEAR, Calendar.NOVEMBER);
    c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
    harness.check(c.getTime(), new Date(1520467200000L)); // 2018-03-05T01:00Z

    c = newCalendar();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.setMinimalDaysInFirstWeek(3);
    c.set(Calendar.YEAR, 2018);
    c.set(Calendar.MONTH, Calendar.SEPTEMBER);
    c.set(Calendar.WEEK_OF_MONTH, 1);
    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    harness.check(c.getTime(), new Date(1536451200000L)); // 2018-09-09T02:00Z

  }

}
