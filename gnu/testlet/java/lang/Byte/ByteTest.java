/* Copyright (C) 1999 Hewlett-Packard Company

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
   Boston, MA 02111-1307, USA.
*/

// Tags: JLS1.0

package gnu.testlet.java.lang.Byte;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class ByteTest implements Testlet
{
  protected static TestHarness harness;
	public void test_Basics()
	{
		if ( Byte.MIN_VALUE != -128 )
			harness.fail("Error: test_Basics failed - 1" );
		if ( Byte.MAX_VALUE != 127 )
			harness.fail("Error: test_Basics failed - 2" );

		Byte ch = new Byte((byte)'b');
		if ( ch.byteValue() != (byte)'b' )
			harness.fail("Error: test_Basics failed - 3" );
		Byte ch1 = new Byte("122");
		if ( ch1.byteValue() != 122 )
			harness.fail("Error: test_Basics failed - 4" );
		if ( (Byte.valueOf( "120")).byteValue() != 120 )
			harness.fail("Error: test_Basics failed - 5" );
		if ( (Byte.valueOf( "120")).byteValue() != 120 )
			harness.fail("Error: test_Basics failed - 6" );

	}

	public void test_toString()
	{
		Byte ch = new Byte((byte)'a');
		String str = ch.toString();
		if ( str.length() != 2 || !str.equals("97"))
			harness.fail("Error: test_toString failed " );
	}


	public void test_equals()
	{
		Byte ch1 = new Byte((byte)'+');
		Byte ch2 = new Byte((byte)'+');
		Byte ch3 = new Byte((byte)'-');

		if ( !ch1.equals(ch2) || ch1.equals(ch3) || ch1.equals(null))
			harness.fail("Error: test_equals failed - 1" );
	}

	public void test_hashCode( )
	{
		Byte ch1 = new Byte((byte)'a');

		if ( ch1.hashCode() != (int) 'a' )
			harness.fail("Error: test_hashCode returned wrong results" );
	}

	public void test_decode()
	{
		try {
			Byte.decode("1234");
			harness.fail("Error : test_decode failed - 1" );
		}
		catch ( NumberFormatException e ){}

		if ( Byte.decode("34").intValue() != 34 )
			harness.fail("Error : test_decode failed - 2" );

		try {
			Byte.decode("123.34");
			harness.fail("Error : test_decode failed - 3" );
		}
		catch ( NumberFormatException e ){}
		try {
			Byte.decode("ff");
			harness.fail("Error : test_decode failed - 4" );
		}
		catch ( NumberFormatException e ){}
	 		
	}
		
	public void test_values()
	{
		Byte b = new Byte( (byte)100 );
		Byte b1 = new Byte((byte) -123 );
		if ( b.intValue () != 100 )
			harness.fail("Error : test_values failed - 1" );
		if ( b1.intValue () != -123 )
			harness.fail("Error : test_values failed - 2" );

		if ( b.longValue () != 100 )
			harness.fail("Error : test_values failed - 3" );
		if ( b1.longValue () != -123 )
			harness.fail("Error : test_values failed - 4" );
		if ( b.floatValue () != 100.0f )
			harness.fail("Error : test_values failed - 5" );
		if ( b1.floatValue () != -123.0f )
			harness.fail("Error : test_values failed - 6" );
		if ( b.doubleValue () != 100.0 )
			harness.fail("Error : test_values failed - 7" );
		if ( b1.doubleValue () != -123.0 )
			harness.fail("Error : test_values failed - 8" );
		if ( b.shortValue () != 100 )
			harness.fail("Error : test_values failed - 9" );
		if ( b1.shortValue () != -123 )
			harness.fail("Error : test_values failed - 10" );
		if ( b.byteValue () != 100 )
			harness.fail("Error : test_values failed - 11" );
		if ( b1.byteValue () != -123 )
			harness.fail("Error : test_values failed - 12" );
	}

	public void testall()
	{
		test_Basics();
		test_equals();
		test_toString();
		test_hashCode();
		test_decode();
		test_values();
	}

  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }

}
