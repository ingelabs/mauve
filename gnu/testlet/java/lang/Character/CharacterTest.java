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

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class CharacterTest implements Testlet
{
  protected static TestHarness harness;
	public void test_Basics()
	{
	  if (Character.forDigit(8, 2) != '\0')
	    harness.fail("Error: test_forDigit - 50");
	  if (Character.forDigit(-3, 2) != '\0')
	    harness.fail("Error: test_forDigit - 51");
	  if (Character.forDigit(2, 8) != '2')
	    harness.fail("Error: test_forDigit - 52");
	  if (Character.forDigit(12, 16) != 'c')
	    harness.fail("Error: test_forDigit - 53");

	  if (Character.isJavaLetter('\uFFFF'))
	    harness.fail("Error: test_forDigit - 54");
	  if (!Character.isJavaLetter('a'))
	    harness.fail("Error: test_forDigit - 55");

	  
	    
		if ( Character.MIN_VALUE != '\u0000' )
			harness.fail("Error: test_Basics failed - 1" );
		if ( Character.MAX_VALUE != '\uffff' )
			harness.fail("Error: test_Basics failed - 2" );
		if ( Character.MIN_RADIX != 2 )
			harness.fail("Error: test_Basics failed - 3" );
		if ( Character.MAX_RADIX != 36 )
			harness.fail("Error: test_Basics failed - 4" );

		Character ch = new Character('b');
		if ( ch.charValue() != 'b' )
			harness.fail("Error: test_Basics failed - 5" );
	}

	public void test_toString()
	{
		Character ch = new Character('a');
		String str = ch.toString();

		if ( str.length() != 1 || !str.equals("a"))
			harness.fail("Error: test_toString failed " );
	}


	public void test_equals()
	{
		Character ch1 = new Character('+');
		Character ch2 = new Character('+');
		Character ch3 = new Character('-');

		if ( !ch1.equals(ch2) || ch1.equals(ch3) || ch1.equals(null))
			harness.fail("Error: test_equals failed - 1" );
	}

	public void test_hashCode( )
	{
		Character ch1 = new Character('a');

		if ( ch1.hashCode() != (int) 'a' )
			harness.fail("Error: test_hashCode returned wrong results" );
	}


	public void test_isSpace( )
	{
		if (!Character.isSpace('\t') ||
			!Character.isSpace('\f') ||
			!Character.isSpace('\r') ||
			!Character.isSpace('\n') ||
			!Character.isSpace(' ')  ||
			Character.isSpace('+') )
			harness.fail("Error: test_isSpace returned wrong results" );

	}

	public void test_digit( )
	{
		// radix wrong
		if ( Character.digit( 'a' , Character.MIN_RADIX - 1 ) != -1 )
			harness.fail("Error: test_digit returned wrong results - 1" );
		if ( Character.digit( 'a' , Character.MAX_RADIX + 1 ) != -1 )
			harness.fail("Error: test_digit returned wrong results - 2" );
	}


	public void test_others()
	{
		//calling them just for completion
// not supported		Character.getNumericValue( 'a' );
// not supported		Character.getType( 'a' );
	  Character.isDefined( 'a' );
	  Character.isDefined( '\uffff' );

	  Character.digit('\u0665', 10);
	  Character.digit('\u06F5', 10);
	  Character.digit('\u0968', 10);
	  Character.digit('\u06E8', 10);
	  Character.digit('\u0A68', 10);
	  Character.digit('\u0AE8', 10);
	  Character.digit('\u0B68', 10);
	  Character.digit('\u0BE8', 10);
	  Character.digit('\u0C68', 10);
	  Character.digit('\u0CE8', 10);
	  Character.digit('\u0D68', 10);
	  Character.digit('\u0E52', 10);
	  Character.digit('\u0ED2', 10);
	  Character.digit('\uFF12', 10);
	  Character.digit('\uFFFF', 10);

// not supported		Character.isISOControl( 'a' );
// not supported		Character.isIdentifierIgnorable( 'a' );
// not supported		Character.isJavaIdentifierPart( 'a' );
// not supported		Character.isJavaIdentifierStart( 'a' );
// not supported		Character.isJavaLetter( 'a' );
	  Character.isJavaLetterOrDigit( 'a' );
	  if (Character.isJavaLetterOrDigit('\uFFFF'))
	      harness.fail("Error: isJavaLetterOrDigit - 60");
	  if (Character.isLetterOrDigit('\uFFFF'))
	      harness.fail("Error: isLetterOrDigit - 61");


// not supported		Character.isLetter( 'a' );
	      Character.isLetterOrDigit( 'a' );
		Character.isLowerCase( 'A' );
		Character.isLowerCase( 'a' );
		Character.isSpace( 'a' );
		
// not supported		Character.isSpaceChar( 'a' );
// not supported		Character.isTitleCase( 'a' );
// not supported		Character.isUnicodeIdentifierPart( 'a' );
// not supported		Character.isUnicodeIdentifierStart( 'a' );
		Character.isUpperCase( 'a' );
		Character.isUpperCase( 'A' );
// not supported		Character.isWhitespace( 'a' );
// not supported		Character.toTitleCase( 'a' );
	}

	public void testall()
	{
		test_Basics();
		test_toString();
		test_equals();
		test_hashCode();
		test_isSpace();
		test_digit();
		test_others();
	}

  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }

}
