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
		harness.check(!( Byte.MIN_VALUE != -128 ), 
			"Error: test_Basics failed - 1" );
		harness.check(!( Byte.MAX_VALUE != 127 ), 
			"Error: test_Basics failed - 2" );

		Byte ch = new Byte((byte)'b');
		harness.check(!( ch.byteValue() != (byte)'b' ), 
			"Error: test_Basics failed - 3" );
		Byte ch1 = new Byte("122");
		harness.check(!( ch1.byteValue() != 122 ), 
			"Error: test_Basics failed - 4" );
		harness.check(!( (Byte.valueOf( "120")).byteValue() != 120 ), 
			"Error: test_Basics failed - 5" );
		harness.check(!( (Byte.valueOf( "120")).byteValue() != 120 ), 
			"Error: test_Basics failed - 6" );

	}

	public void test_toString()
	{
		Byte ch = new Byte((byte)'a');
		String str = ch.toString();
		harness.check(!( str.length() != 2 || !str.equals("97")), 
			"Error: test_toString failed " );
	}


	public void test_equals()
	{
		Byte ch1 = new Byte((byte)'+');
		Byte ch2 = new Byte((byte)'+');
		Byte ch3 = new Byte((byte)'-');

		harness.check(!( !ch1.equals(ch2) || ch1.equals(ch3) || ch1.equals(null)), 
			"Error: test_equals failed - 1" );
	}

	public void test_hashCode( )
	{
		Byte ch1 = new Byte((byte)'a');

		harness.check(!( ch1.hashCode() != (int) 'a' ), 
			"Error: test_hashCode returned wrong results" );
	}

	public void test_decode()
	{
		try {
			Byte.decode("1234");
			harness.fail("Error : test_decode failed - 1" );
		}
		catch ( NumberFormatException e ){}

		harness.check(!( Byte.decode("34").intValue() != 34 ), 
			"Error : test_decode failed - 2" );

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
		harness.check(!( b.intValue () != 100 ), 
			"Error : test_values failed - 1" );
		harness.check(!( b1.intValue () != -123 ), 
			"Error : test_values failed - 2" );

		harness.check(!( b.longValue () != 100 ), 
			"Error : test_values failed - 3" );
		harness.check(!( b1.longValue () != -123 ), 
			"Error : test_values failed - 4" );
		harness.check(!( b.floatValue () != 100.0f ), 
			"Error : test_values failed - 5" );
		harness.check(!( b1.floatValue () != -123.0f ), 
			"Error : test_values failed - 6" );
		harness.check(!( b.doubleValue () != 100.0 ), 
			"Error : test_values failed - 7" );
		harness.check(!( b1.doubleValue () != -123.0 ), 
			"Error : test_values failed - 8" );
		harness.check(!( b.shortValue () != 100 ), 
			"Error : test_values failed - 9" );
		harness.check(!( b1.shortValue () != -123 ), 
			"Error : test_values failed - 10" );
		harness.check(!( b.byteValue () != 100 ), 
			"Error : test_values failed - 11" );
		harness.check(!( b1.byteValue () != -123 ), 
			"Error : test_values failed - 12" );
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
