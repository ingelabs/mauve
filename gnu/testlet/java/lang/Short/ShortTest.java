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

// Tags: JDK1.0

package gnu.testlet.java.lang.Short;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class ShortTest implements Testlet
{

  protected static TestHarness harness;
	public void test_Basics()
	{
		harness.check(!( Short.MIN_VALUE != -32768 || 
			 Short.MAX_VALUE != 32767 ), 
			"Error: test_Basics failed - 1" );

		Short i1 = new Short((short)100);

		harness.check(!( i1.shortValue() != 100 ), 
			"Error: test_Basics failed - 2" );

		try {
		harness.check(!( (new Short("234")).shortValue() != 234 ), 
			"Error: test_Basics failed - 3" );
		}
		catch ( NumberFormatException e )
		{
			harness.fail("Error: test_Basics failed - 3" );
		}

		try {
		harness.check(!( (new Short("-FF")).shortValue() != -255 ), 
			"Error: test_Basics failed - 4" );
		}
		catch ( NumberFormatException e )
		{
		}

		try {
		    new Short("babu");
			harness.fail("Error: test_Basics failed - 5" );
		}
		catch ( NumberFormatException e )
		{
		}
		harness.check(!( Short.decode( "123").shortValue() != 123 ), 
			"Error: test_Basics failed - 6" );
		harness.check(!( Short.decode( "32767").shortValue() != 32767 ), 
			"Error: test_Basics failed - 7" );

	}

	public void test_toString()
	{
		harness.check(!( !( new Short((short)123)).toString().equals("123")), 
			"Error: test_toString failed - 1" );
		harness.check(!( !( new Short((short)-44)).toString().equals("-44")), 
			"Error: test_toString failed - 2" );

		harness.check(!( !Short.toString((short) 234 ).equals ("234" )), 
			"Error: test_toString failed - 3" );
		harness.check(!( !Short.toString((short) -34 ).equals ("-34" )), 
			"Error: test_toString failed - 4" );
		harness.check(!( !Short.toString((short) -34 ).equals ("-34" )), 
			"Error: test_toString failed - 5" );

	}

	public void test_equals()
	{
		Short i1 = new Short((short)23);
		Short i2 = new Short((short)-23);

		harness.check(!( !i1.equals( new Short((short)23))), 
			"Error: test_equals failed - 1" );
		harness.check(!( !i2.equals( new Short((short)-23))), 
			"Error: test_equals failed - 2" );

		
		harness.check(!( i1.equals( i2 )), 
			"Error: test_equals failed - 3" );

		harness.check(!( i1.equals(null)), 
			"Error: test_equals failed - 4" );
	}

	public void test_hashCode( )
	{
		Short b1 = new Short((short)3439);
		Short b2 = new Short((short)-3439);

		harness.check(!( b1.hashCode() != 3439 || b2.hashCode() != -3439 ), 
			"Error: test_hashCode returned wrong results" );
	}

	public void test_intValue( )
	{
		Short b1 = new Short((short)32767);
		Short b2 = new Short((short)-32767);

		harness.check(!( b1.intValue() != 32767 ),  
			"Error: test_intValue returned wrong results - 1" );

		harness.check(!( b2.intValue() != -32767 ),  
			"Error: test_intValue returned wrong results - 2" );
	}

	public void test_longValue( )
	{
		Short b1 = new Short((short)3767);
		Short b2 = new Short((short)-3767);

		harness.check(!( b1.longValue() != (long)3767 ),  
			"Error: test_longValue returned wrong results - 1" );

		harness.check(!( b2.longValue() != -3767 ),  
			"Error: test_longValue returned wrong results - 2" );
	}
	public void test_floatValue( )
	{
		Short b1 = new Short((short)3276);
		Short b2 = new Short((short)-3276);

		harness.check(!( b1.floatValue() != 3276.0f ),  
			"Error: test_floatValue returned wrong results - 1" );

		harness.check(!( b2.floatValue() != -3276.0f ),  
			"Error: test_floatValue returned wrong results - 2" );
	}
	public void test_doubleValue( )
	{
		Short b1 = new Short((short)0);
		Short b2 = new Short((short)30);

		harness.check(!( b1.doubleValue() != 0.0 ),  
			"Error: test_doubleValue returned wrong results - 1" );

		harness.check(!( b2.doubleValue() != 30.0 ),  
			"Error: test_doubleValue returned wrong results - 2" );
	}

	public void test_shortbyteValue( )
	{
		Short b1 = new Short((short)0);
		Short b2 = new Short((short)300);

		harness.check(!( b1.byteValue() != 0 ),  
			"Error: test_shortbyteValue returned wrong results - 1" );

		harness.check(!( b2.byteValue() != (byte)300 ),  
			"Error: test_shortbyteValue returned wrong results - 2" );
		harness.check(!( b1.shortValue() != 0 ),  
			"Error: test_shortbyteValue returned wrong results - 3" );

		harness.check(!( b2.shortValue() != (short)300 ),  
			"Error: test_shortbyteValue returned wrong results - 4" );
		harness.check(!( ((Number)b1).shortValue() != 0 ),  
			"Error: test_shortbyteValue returned wrong results - 5" );

		harness.check(!( ((Number)b2).byteValue() != (byte)300 ),  
			"Error: test_shortbyteValue returned wrong results - 6" );
	}

	public void test_parseShort()
	{
		harness.check(!( Short.parseShort("473") != Short.parseShort("473" , 10 )), 
			"Error: test_parseInt returned wrong results - 1" );	

		harness.check(!( Short.parseShort("0" , 10 ) != 0 ),  
			"Error: test_parseInt returned wrong results - 2" );	

		harness.check(!( Short.parseShort("473" , 10 ) != 473 ),  
			"Error: test_parseInt returned wrong results - 3" );	
		harness.check(!( Short.parseShort("-0" , 10 ) != 0 ),  
			"Error: test_parseInt returned wrong results - 4" );	
		harness.check(!( Short.parseShort("-FF" , 16 ) != -255 ),  
			"Error: test_parseInt returned wrong results - 5" );	
		harness.check(!( Short.parseShort("1100110" , 2 ) != 102 ),  
			"Error: test_parseInt returned wrong results - 6" );	
		try {
			Short.parseShort("99" , 8 );
			harness.fail("Error: test_parseInt returned wrong results - 10" );	
		}catch ( NumberFormatException e ){}
		try {
			Short.parseShort("kona" , 10 );
			harness.fail("Error: test_parseInt returned wrong results - 11" );	
		}catch ( NumberFormatException e ){}
	}

	public void test_valueOf( )
	{
		harness.check(!( Short.valueOf("21234").intValue() != Short.parseShort("21234")), 
			"Error: test_valueOf returned wrong results - 1" );	
	}

	public void testall()
	{
		test_Basics();
		test_toString();
		test_equals();
		test_hashCode();
		test_intValue();
		test_longValue();
		test_floatValue();
		test_doubleValue();
		test_shortbyteValue();
		test_parseShort();
		test_valueOf();
	}

  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }

}
