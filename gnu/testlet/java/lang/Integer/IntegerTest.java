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

package gnu.testlet.java.lang.Integer;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class IntegerTest implements Testlet
{

  protected static TestHarness harness;
	public void test_Basics()
	{
		if ( Integer.MIN_VALUE != 0x80000000 || 
			 Integer.MAX_VALUE != 0x7fffffff )
			harness.fail("Error: test_Basics failed - 1" );

		Integer i1 = new Integer(100);

		if ( i1.intValue() != 100 )
			harness.fail("Error: test_Basics failed - 2" );

		try {
		if ( (new Integer("234")).intValue() != 234 )
			harness.fail("Error: test_Basics failed - 3" );
		}
		catch ( NumberFormatException e )
		{
			harness.fail("Error: test_Basics failed - 3" );
		}

		try {
		if ( (new Integer("-FF")).intValue() != -255 )
			harness.fail("Error: test_Basics failed - 4" );
		}
		catch ( NumberFormatException e )
		{
		}

		try {
		    new Integer("babu");
			harness.fail("Error: test_Basics failed - 5" );
		}
		catch ( NumberFormatException e )
		{
		}
		if ( Integer.decode( "123").intValue() != 123 )
			harness.fail("Error: test_Basics failed - 6" );
		if ( Integer.decode( "32767").intValue() != 32767 )
			harness.fail("Error: test_Basics failed - 7" );

	}

	public void test_toString()
	{
		if ( !( new Integer(123)).toString().equals("123"))
			harness.fail("Error: test_toString failed - 1" );
		if ( !( new Integer(-44)).toString().equals("-44"))
			harness.fail("Error: test_toString failed - 2" );

		if ( !Integer.toString( 234 ).equals ("234" ))
			harness.fail("Error: test_toString failed - 3" );
		if ( !Integer.toString( -34 ).equals ("-34" ))
			harness.fail("Error: test_toString failed - 4" );
		if ( !Integer.toString( -34 ).equals ("-34" ))
			harness.fail("Error: test_toString failed - 5" );

		if ( !Integer.toString(99 , 1 ).equals("99"))
			harness.fail("Error: test_toString failed - 6" );
		if ( !Integer.toString(99 , 37 ).equals("99"))
			harness.fail("Error: test_toString failed - 7" );

		if ( !Integer.toString(15 , 2 ).equals("1111"))
			harness.fail("Error: test_toString failed - 8" );
		if ( !Integer.toString(37 , 36 ).equals("11"))
			harness.fail("Error: test_toString failed - 9" );
		if ( !Integer.toString(31 , 16 ).equals("1f"))
			harness.fail("Error: test_toString failed - 10" );


		if ( !Integer.toString(-99 , 1 ).equals("-99"))
			harness.fail("Error: test_toString failed - 11" );
		if ( !Integer.toString(-99 , 37 ).equals("-99"))
			harness.fail("Error: test_toString failed - 12" );

		if ( !Integer.toString(-15 , 2 ).equals("-1111"))
			harness.fail("Error: test_toString failed - 13" );
		if ( !Integer.toString(-37 , 36 ).equals("-11"))
			harness.fail("Error: test_toString failed - 14" );
		if ( !Integer.toString(-31 , 16 ).equals("-1f"))
			harness.fail("Error: test_toString failed - 15" );
	}

	public void test_equals()
	{
		Integer i1 = new Integer(23);
		Integer i2 = new Integer(-23);

		if ( !i1.equals( new Integer(23)))
			harness.fail("Error: test_equals failed - 1" );
		if ( !i2.equals( new Integer(-23)))
			harness.fail("Error: test_equals failed - 2" );

		
		if ( i1.equals( i2 ))
			harness.fail("Error: test_equals failed - 3" );

		if ( i1.equals(null))
			harness.fail("Error: test_equals failed - 4" );
	}

	public void test_hashCode( )
	{
		Integer b1 = new Integer(3439);
		Integer b2 = new Integer(-3439);

		if ( b1.hashCode() != 3439 || b2.hashCode() != -3439 )
			harness.fail("Error: test_hashCode returned wrong results" );
	}

	public void test_intValue( )
	{
		Integer b1 = new Integer(32767);
		Integer b2 = new Integer(-32767);

		if ( b1.intValue() != 32767 ) 
			harness.fail("Error: test_intValue returned wrong results - 1" );

		if ( b2.intValue() != -32767 ) 
			harness.fail("Error: test_intValue returned wrong results - 2" );
	}

	public void test_longValue( )
	{
		Integer b1 = new Integer(3767);
		Integer b2 = new Integer(-3767);

		if ( b1.longValue() != (long)3767 ) 
			harness.fail("Error: test_longValue returned wrong results - 1" );

		if ( b2.longValue() != -3767 ) 
			harness.fail("Error: test_longValue returned wrong results - 2" );
	}
	public void test_floatValue( )
	{
		Integer b1 = new Integer(3276);
		Integer b2 = new Integer(-3276);

		if ( b1.floatValue() != 3276.0f ) 
			harness.fail("Error: test_floatValue returned wrong results - 1" );

		if ( b2.floatValue() != -3276.0f ) 
			harness.fail("Error: test_floatValue returned wrong results - 2" );
	}
	public void test_doubleValue( )
	{
		Integer b1 = new Integer(0);
		Integer b2 = new Integer(30);

		if ( b1.doubleValue() != 0.0 ) 
			harness.fail("Error: test_doubleValue returned wrong results - 1" );

		if ( b2.doubleValue() != 30.0 ) 
			harness.fail("Error: test_doubleValue returned wrong results - 2" );
	}

	public void test_shortbyteValue( )
	{
		Integer b1 = new Integer(0);
		Integer b2 = new Integer(300);

		if ( b1.byteValue() != 0 ) 
			harness.fail("Error: test_shortbyteValue returned wrong results - 1" );

		if ( b2.byteValue() != (byte)300 ) 
			harness.fail("Error: test_shortbyteValue returned wrong results - 2" );
		if ( b1.shortValue() != 0 ) 
			harness.fail("Error: test_shortbyteValue returned wrong results - 3" );

		if ( b2.shortValue() != (short)300 ) 
			harness.fail("Error: test_shortbyteValue returned wrong results - 4" );
		if ( ((Number)b1).shortValue() != 0 ) 
			harness.fail("Error: test_shortbyteValue returned wrong results - 5" );

		if ( ((Number)b2).byteValue() != (byte)300 ) 
			harness.fail("Error: test_shortbyteValue returned wrong results - 6" );
	}

	public void test_toHexString()
	{
		String str, str1;

		str = Integer.toHexString(8375);
		str1 = Integer.toHexString( -5361 ); 

		if ( !str.equalsIgnoreCase("20B7"))
			harness.fail("Error: test_toHexString returned wrong results - 1" );

		if ( !str1.equalsIgnoreCase("FFFFEB0F"))
			harness.fail("Error: test_toHexString returned wrong results - 2" );	
	}

	public void test_toOctalString()
	{
		String str, str1;
		str = Integer.toOctalString(5847);
		str1= Integer.toOctalString(-9863 );

		if ( !str.equals("13327"))
			harness.fail("Error: test_toOctalString returned wrong results - 1" );

		if ( !str1.equals("37777754571"))
			harness.fail("Error: test_toOctalString returned wrong results - 2" );	
	}

	public void test_toBinaryString()
	{
		if ( !Integer.toBinaryString(358).equals("101100110"))
			harness.fail("Error: test_toBinaryString returned wrong results - 1" );

		if ( !Integer.toBinaryString( -5478 ).equals("11111111111111111110101010011010"))
			harness.fail("Error: test_toBinaryString returned wrong results - 2" );	
	}

	public void test_parseInt()
	{
		if ( Integer.parseInt("473") != Integer.parseInt("473" , 10 ))
			harness.fail("Error: test_parseInt returned wrong results - 1" );	

		if ( Integer.parseInt("0" , 10 ) != 0 ) 
			harness.fail("Error: test_parseInt returned wrong results - 2" );	

		if ( Integer.parseInt("473" , 10 ) != 473 ) 
			harness.fail("Error: test_parseInt returned wrong results - 3" );	
		if ( Integer.parseInt("-0" , 10 ) != 0 ) 
			harness.fail("Error: test_parseInt returned wrong results - 4" );	
		if ( Integer.parseInt("-FF" , 16 ) != -255 ) 
			harness.fail("Error: test_parseInt returned wrong results - 5" );	
		if ( Integer.parseInt("1100110" , 2 ) != 102 ) 
			harness.fail("Error: test_parseInt returned wrong results - 6" );	
		if ( Integer.parseInt("2147483647" , 10 )  !=  2147483647 ) 
			harness.fail("Error: test_parseInt returned wrong results - 7" );	
		if ( Integer.parseInt("-2147483647" , 10 ) != -2147483647 ) 
			harness.fail("Error: test_parseInt returned wrong results - 8" );	
		try {
			Integer.parseInt("2147483648" , 10 );
			harness.fail("Error: test_parseInt returned wrong results - 9" );	
		}catch ( NumberFormatException e ){}
		try {
			Integer.parseInt("99" , 8 );
			harness.fail("Error: test_parseInt returned wrong results - 10" );	
		}catch ( NumberFormatException e ){}
		try {
			Integer.parseInt("kona" , 10 );
			harness.fail("Error: test_parseInt returned wrong results - 11" );	
		}catch ( NumberFormatException e ){}
        if ( Integer.parseInt("Kona" , 27 ) != 411787 )
			harness.fail("Error: test_parseInt returned wrong results - 12" );	
	}

	public void test_valueOf( )
	{
		if ( Integer.valueOf("21234").intValue() != Integer.parseInt("21234"))
			harness.fail("Error: test_valueOf returned wrong results - 1" );	
		if ( Integer.valueOf("Kona", 27).intValue() != Integer.parseInt("Kona", 27))
			harness.fail("Error: test_valueOf returned wrong results - 2" );	
	}

	public void test_getInteger( )
	{
		java.util.Properties  prop = System.getProperties();
		prop.put("integerkey1" , "2345" );
		prop.put("integerkey2" , "-984" );
		prop.put("integerkey3" , "-0" );

		prop.put("integerkey4" , "#1f" );
		prop.put("integerkey5" , "0x1f" );
		prop.put("integerkey6" , "017" );

		prop.put("integerkey7" , "babu" );



		System.setProperties(prop);

		if ( Integer.getInteger("integerkey1").intValue() != 2345 ||
			 Integer.getInteger("integerkey2").intValue() != -984 ||
			 Integer.getInteger("integerkey3").intValue() != 0 )
			harness.fail("Error: test_getInteger returned wrong results - 1" );

		if ( Integer.getInteger("integerkey4", new Integer(0)).intValue() != 31 ||
			 Integer.getInteger("integerkey5",new Integer(0)).intValue() != 31 ||
			 Integer.getInteger("integerkey6",new Integer(0)).intValue() != 15 )
			harness.fail("Error: test_getInteger returned wrong results - 2" );

		if ( Integer.getInteger("integerkey7", new Integer(0)).intValue() != 0 )
			harness.fail("Error: test_getInteger returned wrong results - 3" );
		if ( Integer.getInteger("integerkey7", 0).intValue() != 0 )
			harness.fail("Error: test_getInteger returned wrong results - 4" );

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
		test_toHexString();
		test_toOctalString();
		test_toBinaryString();
		test_parseInt();
		test_valueOf();
		test_getInteger();
	}

  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }

}
