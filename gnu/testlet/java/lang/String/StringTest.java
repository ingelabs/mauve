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

package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class StringTest implements Testlet
{

  protected static TestHarness harness;
	public void test_Basics()
	{
		String str1 = new String();
		if ( str1.length() != 0 ) 
			harness.fail("Error : test_Basics failed - 1");
		if ( !str1.toString().equals(""))
			harness.fail("Error : test_Basics failed - 2");

		String str2 = new String("testing" );
		if ( str2.length() != 7 ) 
			harness.fail("Error : test_Basics failed - 3");
		if ( !str2.toString().equals("testing"))
			harness.fail("Error : test_Basics failed - 4");

		
		try {
		  /* CYGNUS: Disable for now to allow other tests to run.
			String str = null;
			String str3 = new String(str);
		  */		  
			harness.fail("Error : test_Basics failed - 5");
		}
		catch ( NullPointerException e ){}	

		String str4 = new String( new StringBuffer("hi there"));
		if ( str4.length () != 8 ) 
			harness.fail("Error : test_Basics failed - 6");
		if ( !str4.toString().equals("hi there"))
			harness.fail("Error : test_Basics failed - 7");

		char cdata[] = { 'h' , 'e' , 'l' , 'l' , 'o' };
		String str5 = new String( cdata );
		if ( str5.length () != 5 ) 
			harness.fail("Error : test_Basics failed - 8");
		if ( !str5.toString().equals("hello"))
			harness.fail("Error : test_Basics failed - 9");

		try {
			String str6 = new String( cdata , 0 , 10 );
			harness.fail("Error : test_Basics failed - 10");

		}catch ( IndexOutOfBoundsException e )
		{}

		try {
			byte [] barr = null;
			String str7 = new String( barr , 0 , 10 );
			harness.fail("Error : test_Basics failed - 11");

		}catch ( NullPointerException e )
		{}

		String str8 = new String( cdata , 0 , 4 );
		if ( !str8.equals("hell"))
			harness.fail("Error : test_Basics failed - 12");

		try {
			String str10 = new String( null , 10 );
			harness.fail("Error : test_Basics failed - 13");

		}catch ( NullPointerException e )
		{}

		byte bdata[] = { (byte)'d',(byte)'a',(byte)'n',(byte)'c',(byte)'i',(byte)'n',(byte)'g' };
		String str9 = new String(bdata , 10 );

		char ch = str9.charAt(1);
		int i = (ch & 0xff00 ) >> 8 ;
		byte b = (byte)(ch & 0x00ff );

		if ( i != 10 ||  b != 'a' )
			harness.fail("Error : test_Basics failed - 14");
		

		byte bnull [] = null;
		try {
			String str11 = new String( bnull , 10 , 0 , 5);
			harness.fail("Error : test_Basics failed - 15");
		}catch ( NullPointerException e ){}

		try {
			String str12 = new String( bdata , 10 , -1 , 3);
			harness.fail("Error : test_Basics failed - 16");
		}catch ( IndexOutOfBoundsException  e ){}


		String str13 = new String( bdata , 10 , 1 , 1 );
		i = (ch & 0xff00 ) >> 8 ;
		b = (byte)(ch & 0x00ff );

		if ( i != 10 ||  b != 'a' )
			harness.fail("Error : test_Basics failed - 17");

		String str14 = new String( bdata);
		if ( !str14.equals("dancing"))
			harness.fail("Error : test_Basics failed - 18");

		// EJWcr00461
		byte arr[]={(byte)'a'};
		String str15 = new String(arr,0x1234,0,1);
		if (!str15.equals("\u3461")) {
		    harness.fail("Error : test_Basics failed - 19");
		}

		// EJWcr00462
		char carr[] = {'h','e','l','l','o'};
		try {
		    String str16 = new String(carr, Integer.MAX_VALUE, 1);
		    harness.fail("Error : test_Basics failed - 20");
		} catch (IndexOutOfBoundsException e) {
		}
		byte arr2[]={(byte)'a', (byte)'b', (byte)'c', (byte)'d', (byte)'e'};
		try {
		    String str17 = new String(arr2,0x1234,Integer.MAX_VALUE,1);
		    harness.fail("Error : test_Basics failed - 21");
		} catch (IndexOutOfBoundsException e) {
		}
				 
		// this used to cause the vm to core dump (cr543)
		String s = "\u0d3e";

	}

	public void test_toString()
	{
		String str1 = "218943289";

		if ( !str1.toString().equals("218943289"))
			harness.fail("Error : test_toString failed - 1");

		if ( str1 != "218943289" )
			harness.fail("Error : test_toString failed - 2");

		if ( !str1.equals(str1.toString()))
			harness.fail("Error : test_toString failed - 3");		
	}

	public void test_equals()
	{
		String str2 = new String("Nectar");

		if ( str2.equals( null ))
			harness.fail("Error : test_equals failed - 1");		

		if ( !str2.equals("Nectar"))
			harness.fail("Error : test_equals failed - 2");		

		if ( str2.equals(""))
			harness.fail("Error : test_equals failed - 3");		

		if ( str2.equals("nectar"))
			harness.fail("Error : test_equals failed - 4");		

		if ( !"".equals(""))
			harness.fail("Error : test_equals failed - 5");		

	}

	public void test_hashCode()
	{
		String str1 = "hp";
		String str2 = "Hewlett Packard Company";

		int hash1 = 'h' * 31 + 'p';
		int acthash1 = str1.hashCode(); 

		if ( hash1 != acthash1 )
			harness.fail("Error : test_hashCode failed - 1");		
	}

	public void test_length()
	{
		if ( "".length() != 0 ) 
			harness.fail("Error : test_length failed - 1");
		
		if ( "pentium".length() != 7 ) 
			harness.fail("Error : test_length failed - 2");
	}

	public void test_charAt()
	{
		if ( "abcd".charAt(0) != 'a' || "abcd".charAt(1) != 'b' ||
			 "abcd".charAt(2) != 'c' || "abcd".charAt(3) != 'd'	)
			harness.fail("Error : test_charAt failed - 1");

		try {
			char ch = "abcd".charAt(4);
			harness.fail("Error : test_charAt failed - 2");
		}
		catch ( IndexOutOfBoundsException e ){}

		try {
			char ch = "abcd".charAt(-1);
			harness.fail("Error : test_charAt failed - 3");
		}
		catch ( IndexOutOfBoundsException e ){}
	}

	public void test_getChars()
	{
		String str = "abcdefghijklmn";

		try {
			str.getChars(0 , 3 , null , 1 );
			harness.fail("Error : test_getChars failed - 1");
		}catch ( NullPointerException e ){}

		char dst[] = new char[5];
		
		try {
			str.getChars(-1 , 3 , dst , 1 );
			harness.fail("Error : test_getChars failed - 2");
		}catch ( StringIndexOutOfBoundsException e ){}

		try {
			str.getChars(4 , 3 , dst , 1 );
			harness.fail("Error : test_getChars failed - 3");
		}catch ( StringIndexOutOfBoundsException e ){}

		try {
			str.getChars(1 , 15 , dst , 1 );
			harness.fail("Error : test_getChars failed - 4");
		}catch ( StringIndexOutOfBoundsException e ){}

		try {
			str.getChars(1 , 5 , dst , -1 );
			harness.fail("Error : test_getChars failed - 5");
		}catch ( ArrayIndexOutOfBoundsException e ){}
		catch ( IndexOutOfBoundsException e ){
			harness.fail("Error : test_getChars failed - 5a"+
                           " should throw ArrayIndexOutOfBounds exception");
                }

		try {
			str.getChars(1 , 10 , dst , 1 );
			harness.fail("Error : test_getChars failed - 6");
		}catch ( ArrayIndexOutOfBoundsException e ){}
		catch ( IndexOutOfBoundsException e ){
			harness.fail("Error : test_getChars failed - 6a"+
                           " should throw ArrayIndexOutOfBounds exception");
                }

		str.getChars(0,5,dst, 0 );
		if ( dst[0] != 'a' || dst[1] != 'b' || dst[2] != 'c' ||
			 				  dst[3] != 'd' || dst[4] != 'e' )
			harness.fail("Error : test_getChars failed - 7");

		dst[0] = dst[1] = dst[2] = dst[3] = dst[4] = ' ';
		str.getChars(0,0,dst, 0 );
		if ( dst[0] != ' ' || dst[1] != ' ' || dst[2] != ' ' ||
			 				  dst[3] != ' ' || dst[4] != ' ' )
			harness.fail("Error : test_getChars failed - 9");

		dst[0] = dst[1] = dst[2] = dst[3] = dst[4] = ' ';
		str.getChars(0,1,dst, 0 );
		if ( dst[0] != 'a' || dst[1] != ' ' || dst[2] != ' ' ||
			 				  dst[3] != ' ' || dst[4] != ' ' )
			harness.fail("Error : test_getChars failed - 10");
	}


	public void test_getBytes()
	{
		String str = "abcdefghijklmn";

		try {
			str.getBytes(0 , 3 , null , 1 );
			harness.fail("Error : test_getBytes failed - 1");
		}catch ( NullPointerException e ){}

		byte dst[] = new byte[5];
		
		try {
			str.getBytes(-1 , 3 , dst , 1 );
			harness.fail("Error : test_getBytes failed - 2");
		}catch ( StringIndexOutOfBoundsException e ){}

		try {
			str.getBytes(4 , 3 , dst , 1 );
			harness.fail("Error : test_getBytes failed - 3");
		}catch ( StringIndexOutOfBoundsException e ){}

		try {
			str.getBytes(1 , 15 , dst , 1 );
			harness.fail("Error : test_getBytes failed - 4");
		}catch ( StringIndexOutOfBoundsException e ){}

		try {
			str.getBytes(1 , 5 , dst , -1 );
			harness.fail("Error : test_getBytes failed - 5");
		}catch ( ArrayIndexOutOfBoundsException e ){}
		catch ( IndexOutOfBoundsException e ){
			harness.fail("Error : test_getBytes failed - 5a"+
                           " should throw ArrayIndexOutOfBounds exception");
                }

		try {
			str.getBytes(1 , 10 , dst , 1 );
			harness.fail("Error : test_getBytes failed - 6");
		}catch ( ArrayIndexOutOfBoundsException e ){}
		catch ( IndexOutOfBoundsException e ){
			harness.fail("Error : test_getBytes failed - 5a"+
                           " should throw ArrayIndexOutOfBounds exception");
                }

		str.getBytes(0,5,dst, 0 );
		if ( dst[0] != 'a' || dst[1] != 'b' || dst[2] != 'c' ||
			 				  dst[3] != 'd' || dst[4] != 'e' )
			harness.fail("Error : test_getBytes failed - 7");

		byte [] dst1 = new byte[40];
		dst1 = str.getBytes();
		if ( dst1[0] != 'a' || dst1[1] != 'b' || dst1[2] != 'c' ||
			 				  dst1[3] != 'd' || dst1[4] != 'e' )
			harness.fail("Error : test_getBytes failed - 8");
	}

	public void test_toCharArray()
	{
		char[] charr = "abcde".toCharArray();

		if ( charr[0] != 'a' || charr[1] != 'b' ||
			charr[2] != 'c' || charr[3] != 'd' ||
			charr[4] != 'e' )
			harness.fail("Error : test_toCharArray failed - 1");

		char [] charr1 = "".toCharArray();

		if ( charr1.length  > 0 )
			harness.fail("Error : test_toCharArray failed - 2");
	}

	public void test_equalsIgnoreCase()
	{
		if ( "hi".equalsIgnoreCase(null))
			harness.fail("Error : test_equalsIgnoreCase failed - 1");

		if ( !"hi".equalsIgnoreCase("HI"))
			harness.fail("Error : test_equalsIgnoreCase failed - 2");

		if ( "hi".equalsIgnoreCase("pq"))
			harness.fail("Error : test_equalsIgnoreCase failed - 3");

		if ( "hi".equalsIgnoreCase("HI "))
			harness.fail("Error : test_equalsIgnoreCase failed - 4");

	}

	public void test_compareTo()
	{
		try {
			int res = "abc".compareTo(null);
			harness.fail("Error : test_compareTo failed - 1");
		}
		catch ( NullPointerException e ){}

		if ( "abc".compareTo("bcdef") >= 0  )
			harness.fail("Error : test_compareTo failed - 2");

		if ( "abc".compareTo("abc") != 0 )
			harness.fail("Error : test_compareTo failed - 3");

		if ( "abc".compareTo("aabc") <= 0 )
			harness.fail("Error : test_compareTo failed - 4");

		if ( "abcd".compareTo("abc") <= 0 )
			harness.fail("Error : test_compareTo failed - 5");

		if ( "".compareTo("abc") >= 0 )
			harness.fail("Error : test_compareTo failed - 6");
	}

	public void test_regionMatches()
	{
		try {
			boolean res = "abc".regionMatches(0 , null , 0 , 2);
			harness.fail("Error : test_regionMatches failed - 1");
		}
		catch ( NullPointerException e ){}

		if ( "abcd".regionMatches(-1 , "abcd" , 0 , 2 ))
			harness.fail("Error : test_regionMatches failed - 2");
		if ( "abcd".regionMatches(0 , "abcd" , - 1 , 2 ))
			harness.fail("Error : test_regionMatches failed - 3");
		if ( "abcd".regionMatches(0 , "abcd" , 0 , 10 ))
			harness.fail("Error : test_regionMatches failed - 4");
		if ( "abcd".regionMatches(0 , "ab" , 0 , 3 ))
			harness.fail("Error : test_regionMatches failed - 5");

		if ( !"abcd".regionMatches(1 , "abc" , 1 , 2 ))
			harness.fail("Error : test_regionMatches failed - 6");

		if ( !"abcd".regionMatches(1 , "abc" , 1 , 0 ))
			harness.fail("Error : test_regionMatches failed - 7");

		if ( "abcd".regionMatches(1 , "ABC" , 1 , 2 ))
			harness.fail("Error : test_regionMatches failed - 8");
		

		try {
			boolean res = "abc".regionMatches(true , 0 , null , 0 , 2);
			harness.fail("Error : test_regionMatches failed - 11");
		}
		catch ( NullPointerException e ){}

		if ( "abcd".regionMatches(true , -1 , "abcd" , 0 , 2 ))
			harness.fail("Error : test_regionMatches failed - 12");
		if ( "abcd".regionMatches(true , 0 , "abcd" , - 1 , 2 ))
			harness.fail("Error : test_regionMatches failed - 13");
		if ( "abcd".regionMatches(true , 0 , "abcd" , 0 , 10 ))
			harness.fail("Error : test_regionMatches failed - 14");
		if ( "abcd".regionMatches(true , 0 , "ab" , 0 , 3 ))
			harness.fail("Error : test_regionMatches failed - 15");

		if ( !"abcd".regionMatches(true , 1 , "abc" , 1 , 2 ))
			harness.fail("Error : test_regionMatches failed - 16");

		if ( !"abcd".regionMatches(true , 1 , "abc" , 1 , 0 ))
			harness.fail("Error : test_regionMatches failed - 17");

		if ( !"abcd".regionMatches(true , 1 , "ABC" , 1 , 2 ))
			harness.fail("Error : test_regionMatches failed - 18");
		if ( "abcd".regionMatches(false , 1 , "ABC" , 1 , 2 ))
			harness.fail("Error : test_regionMatches failed - 19");
	}

	public void test_startsWith()
	{
		if( !"abcdef".startsWith( "abc"))
			harness.fail("Error : test_startsWith failed - 1");

		try {
			boolean b = "abcdef".startsWith( null );
			harness.fail("Error : test_startsWith failed - 2");
		} catch ( NullPointerException e ){}

		if( "abcdef".startsWith( "ABC"))
			harness.fail("Error : test_startsWith failed - 3");

		if( !"abcdef".startsWith( ""))
			harness.fail("Error : test_startsWith failed - 4");

		if( "abc".startsWith( "abcd"))
			harness.fail("Error : test_startsWith failed - 5");


		if( !"abcdef".startsWith( "abc" , 0 ))
			harness.fail("Error : test_startsWith failed - 6");

		try {
			boolean b = "abcdef".startsWith( null ,0);
			harness.fail("Error : test_startsWith failed - 7");
		} catch ( NullPointerException e ){}

		if( "abcdef".startsWith( "ABC", 2))
			harness.fail("Error : test_startsWith failed - 8");

		if( !"abcdef".startsWith( "", 0 ))
			harness.fail("Error : test_startsWith failed - 9");

		if( "abc".startsWith( "abcd" , 3))
			harness.fail("Error : test_startsWith failed - 10");

		if( "abc".startsWith( "abc" , 10))
			harness.fail("Error : test_startsWith failed - 11");
	}

	public void test_endsWith()
	{
		if( !"abcdef".endsWith( "def"))
			harness.fail("Error : test_endsWith failed - 1");

		try {
			boolean b = "abcdef".endsWith( null );
			harness.fail("Error : test_endsWith failed - 2");
		} catch ( NullPointerException e ){}

		if( "abcdef".endsWith( "DEF"))
			harness.fail("Error : test_endsWith failed - 3");

		if( !"abcdef".endsWith( ""))
			harness.fail("Error : test_endsWith failed - 4");

		if( "bcde".endsWith( "abcd"))
			harness.fail("Error : test_endsWith failed - 5");

	}

	public void test_indexOf()
	{
		if ( "a".indexOf('a') != 0 )
			harness.fail("Error : test_indexOf failed - 1");

		if ( "aabc".indexOf('c') != 3 )
			harness.fail("Error : test_indexOf failed - 2");

		if ( "a".indexOf('c') != -1 )
			harness.fail("Error : test_indexOf failed - 3");

		if ( "".indexOf('a') != -1 )
			harness.fail("Error : test_indexOf failed - 4");


		if ( "abcde".indexOf('b', 3) != -1 )
			harness.fail("Error : test_indexOf failed - 5");
		if ( "abcde".indexOf('b', 0) != 1 )
			harness.fail("Error : test_indexOf failed - 6");
		if ( "abcdee".indexOf('e', 3) != 4 )
			harness.fail("Error : test_indexOf failed - 7");
		if ( "abcdee".indexOf('e', 5) != 5 )
			harness.fail("Error : test_indexOf failed - 8");

		if ( "abcdee".indexOf('e', -5) != 4 )
			harness.fail("Error : test_indexOf failed - 9");
		if ( "abcdee".indexOf('e', 15) != -1 )
			harness.fail("Error : test_indexOf failed - 10");


		if ( "abcdee".indexOf("babu") != -1 )
			harness.fail("Error : test_indexOf failed - 11");
		try {
			int x = "abcdee".indexOf(null);
		   	harness.fail("Error : test_indexOf failed - 12");
		}catch ( NullPointerException e ){} 
	
		if ( "abcdee".indexOf("") != 0 )
			harness.fail("Error : test_indexOf failed - 13");
		if ( "abcdee".indexOf("ee") != 4 )
			harness.fail("Error : test_indexOf failed - 14");
		if ( "abcbcbc".indexOf("cbc") != 2 )
			harness.fail("Error : test_indexOf failed - 15");

		if ( "abcdee".indexOf("babu", 3) != -1 )
			harness.fail("Error : test_indexOf failed - 16");
		try {
			int x = "abcdee".indexOf(null,0);
		   	harness.fail("Error : test_indexOf failed - 17");
		}catch ( NullPointerException e ){} 
	
		if ( "abcdee".indexOf("", 0) != 0 )
			harness.fail("Error : test_indexOf failed - 18");
		if ( "abcdee".indexOf("ee", 4) != 4 )
			harness.fail("Error : test_indexOf failed - 19");
		if ( "abcbcbc".indexOf("cbc",4 ) != 4 )
			harness.fail("Error : test_indexOf failed - 20");
		// EJWcr00463
		if ( "hello \u5236 world".indexOf('\u5236') != 6 ) {
			harness.fail("Error : test_indexOf failed - 21");
		}
		if ( "hello \u0645 world".indexOf('\u0645') != 6 ) {
			harness.fail("Error : test_indexOf failed - 22");
		}
		if ( "hello \u07ff world".indexOf('\u07ff') != 6 ) {
			harness.fail("Error : test_indexOf failed - 23");
		}
	}

	public void test_lastIndexOf()
	{
		if ( "a".lastIndexOf('a') != 0 )
			harness.fail("Error : test_lastIndexOf failed - 1");

		if ( "aabc".lastIndexOf('c') != 3 )
			harness.fail("Error : test_lastIndexOf failed - 2");

		if ( "a".lastIndexOf('c') != -1 )
			harness.fail("Error : test_lastIndexOf failed - 3");

		if ( "".lastIndexOf('a') != -1 )
			harness.fail("Error : test_lastIndexOf failed - 4");


		if ( "abcde".lastIndexOf('b', 0) != -1 )
			harness.fail("Error : test_lastIndexOf failed - 5");
		if ( "abcde".lastIndexOf('b', 4) != 1 )
			harness.fail("Error : test_lastIndexOf failed - 6");
		if ( "abcdee".lastIndexOf('e', 7) != 5 )
			harness.fail("Error : test_lastIndexOf failed - 7");
		if ( "abcdee".lastIndexOf('e', 5) != 5 )
			harness.fail("Error : test_lastIndexOf failed - 8");

		if ( "abcdee".lastIndexOf('e', -5) != -1 )
			harness.fail("Error : test_lastIndexOf failed - 9");
		if ( "abcdee".lastIndexOf('e', 15) != 5 )
			harness.fail("Error : test_lastIndexOf failed - 10");


		if ( "abcdee".lastIndexOf("babu") != -1 )
			harness.fail("Error : test_lastIndexOf failed - 11");
		try {
			int x = "abcdee".lastIndexOf(null);
		   	harness.fail("Error : test_lastIndexOf failed - 12");
		}catch ( NullPointerException e ){} 
	
		if ( "abcdee".lastIndexOf("") != 6 )
			harness.fail("Error : test_lastIndexOf failed - 13");
		if ( "abcdee".lastIndexOf("ee") != 4 )
			harness.fail("Error : test_lastIndexOf failed - 14");
		if ( "abcbcbc".lastIndexOf("cbc") != 4 )
			harness.fail("Error : test_lastIndexOf failed - 15");

		if ( "abcdee".lastIndexOf("babu", 3) != -1 )
			harness.fail("Error : test_lastIndexOf failed - 16");

		try {
			int x = "abcdee".lastIndexOf(null,0);
		   	harness.fail("Error : test_lastIndexOf failed - 17");
		}catch ( NullPointerException e ){} 
	
		if ( "abcdee".lastIndexOf("", 0) != 0 )
			harness.fail("Error : test_lastIndexOf failed - 18");
		if ( "abcdee".lastIndexOf("ee", 4) != 4 )
			harness.fail("Error : test_lastIndexOf failed - 19");
		if ( "abcbcbc".lastIndexOf("cbc",3 ) != 2 )
			harness.fail("Error : test_lastIndexOf failed - 20");
	}

	public void test_substring()
	{
		if( !"unhappy".substring(2).equals("happy"))
			harness.fail("Error : test_substring failed - 1");
		if( !"Harbison".substring(3).equals("bison"))
			harness.fail("Error : test_substring failed - 2");
		if( !"emptiness".substring(9).equals(""))
			harness.fail("Error : test_substring failed - 3");

		try {
			String str = "hi there".substring(-1);
			harness.fail("Error : test_substring failed - 4");
		}catch( IndexOutOfBoundsException e ){}

		try {
			String str = "hi there".substring(10);
			harness.fail("Error : test_substring failed - 5");
		}catch( IndexOutOfBoundsException e ){}


		if( !"hamburger".substring(4,8).equals("urge"))
			harness.fail("Error : test_substring failed - 6");
		if( !"smiles".substring(1,5).equals("mile"))
			harness.fail("Error : test_substring failed - 7");
		if( !"emptiness".substring(2,2).equals(""))
			harness.fail("Error : test_substring failed - 8");

		try {
			String str = "hi there".substring(-1, 3);
			harness.fail("Error : test_substring failed - 9");
		}catch( IndexOutOfBoundsException e ){}

		try {
			String str = "hi there".substring(0, 10);
			harness.fail("Error : test_substring failed - 10");
		}catch( IndexOutOfBoundsException e ){}

		try {
			String str = "hi there".substring(7, 6);
			harness.fail("Error : test_substring failed - 11");
		}catch( IndexOutOfBoundsException e ){}


	}

	public void test_concat( )
	{
		try {
			String str = "help".concat(null);
			harness.fail("Error : test_concat failed - 1");
		}catch ( NullPointerException e){}

		if ( !"help".concat("me").equals("helpme"))
			harness.fail("Error : test_concat failed - 2");

		if( ! "to".concat("get").concat("her").equals("together"))
			harness.fail("Error : test_concat failed - 3");

		if( "hi".concat("") != "hi")
			harness.fail("Error : test_concat failed - 4");

		String str1 = "".concat("there");
		if( !str1.equals("there"))
			harness.fail("Error : test_concat failed - 5");

		// EJWcr00467
		String str2 = new String();
		try {
		    str2 = str2.concat("hello");
		    if (!str2.equals("hello")) {
			harness.fail("Error : test_concat failed - 7");
		    }
		} catch (Exception e) {
			harness.fail("Error : test_concat failed - 6");
		}
	}


	public void test_replace()
	{
		if ( !"mesquite in your cellar".replace('e' , 'o' ).equals(
			          "mosquito in your collar" ))
			harness.fail("Error : test_replace failed - 1");

		if ( !"the war of baronets".replace('r' , 'y' ).equals(
			          "the way of bayonets" ))
			harness.fail("Error : test_replace failed - 2");

		if ( !"sparring with a purple porpoise".replace('p' , 't' ).equals(
			          "starring with a turtle tortoise" ))
			harness.fail("Error : test_replace failed - 3");

		if ( !"JonL".replace('q' , 'x' ).equals("JonL" ))
			harness.fail("Error : test_replace failed - 4");

		if( !"ppppppppppppp".replace('p' , 'p' ).equals("ppppppppppppp"))
			harness.fail("Error : test_replace failed - 5");

		if( !"ppppppppppppp".replace('p' , '1' ).equals("1111111111111"))
			harness.fail("Error : test_replace failed - 6");
		if( !"hp".replace('c' , 'd' ).equals("hp"))
			harness.fail("Error : test_replace failed - 7");
		if( !"vmhere".replace('a' , 'd' ).equals("vmhere"))
			harness.fail("Error : test_replace failed - 8");


	}

	public void test_toLowerCase()
	{
		if( !"".toLowerCase().equals(""))
			harness.fail("Error : test_toLowerCase failed - 1");

		if( !"French Fries".toLowerCase().equals("french fries"))
			harness.fail("Error : test_toLowerCase failed - 2");


		if( !"SMALL-VM".toLowerCase().equals("small-vm"))
			harness.fail("Error : test_toLowerCase failed - 3");
	}

	public void test_toUpperCase()
	{
		if( !"".toUpperCase().equals(""))
			harness.fail("Error : test_toUpperCase failed - 1");

		if( !"French Fries".toUpperCase().equals("FRENCH FRIES"))
			harness.fail("Error : test_toUpperCase failed - 2");


		if( !"SMALL-VM".toUpperCase().equals("SMALL-VM"))
			harness.fail("Error : test_toUpperCase failed - 3");

		if( !"small-jvm".toUpperCase().equals("SMALL-JVM"))
			harness.fail("Error : test_toUpperCase failed - 4");
	}


	public void test_valueOf()
	{
		if ( !String.valueOf((Object)null).equals("null"))
			harness.fail("Error : test_valueOf failed - 1");

		Object obj = new Object();
		if ( !String.valueOf(obj).equals(obj.toString()))
			harness.fail("Error : test_valueOf failed - 2");


		try {
			char [] data = null;
			String str = String.valueOf( data );
		}catch ( NullPointerException e ){}

		char [] data = { 'h' , 'e' , 'l' , 'l' , 'o' };
		if( !String.valueOf( data ).equals("hello"))
			harness.fail("Error : test_valueOf failed - 3");

		if( !String.copyValueOf( data ).equals("hello"))
			harness.fail("Error : test_valueOf failed - 3a");

		try {
			String str = String.valueOf(data , -1 , 4 );
			harness.fail("Error : test_valueOf failed - 4");
		}catch ( IndexOutOfBoundsException e ){}

		try {
			String str = String.valueOf(data , 1 , 5 );
			harness.fail("Error : test_valueOf failed - 5");
		}catch ( IndexOutOfBoundsException e ){}

		try {
			String str = String.valueOf(data , 1 , -5 );
			harness.fail("Error : test_valueOf failed - 6");
		}catch ( IndexOutOfBoundsException e ){}

		try {
			String str = String.valueOf(null , 1 , 3 );
			harness.fail("Error : test_valueOf failed - 7");
		}catch ( NullPointerException e ){}

		if( !String.valueOf(data , 2 , 2 ).equals("ll"))
			harness.fail("Error : test_valueOf failed - 8");

		if( !String.copyValueOf(data , 2 , 2 ).equals("ll"))
			harness.fail("Error : test_valueOf failed - 8a");

		if( !String.valueOf(true).equals("true"))
			harness.fail("Error : test_valueOf failed - 9");

		if( !String.valueOf(false).equals("false"))
			harness.fail("Error : test_valueOf failed - 10");

		if( !String.valueOf('c').equals("c"))
			harness.fail("Error : test_valueOf failed - 11");

		if( !String.valueOf(' ').equals(" "))
			harness.fail("Error : test_valueOf failed - 12");

		if( !String.valueOf(234).equals("234"))
			harness.fail("Error : test_valueOf failed - 13");

		if( !String.valueOf(234L).equals("234"))
			harness.fail("Error : test_valueOf failed - 14");

		if( !String.valueOf(23.45f).equals("23.45"))
			harness.fail("Error : test_valueOf failed - 15");

		if( !String.valueOf(23.4).equals("23.4"))
			harness.fail("Error : test_valueOf failed - 16");
	}
        
        public void test_intern()
	{
 	 	String hp = "hp";
		String nullstr = "";
		if ( "hp".intern() != hp.intern())
			harness.fail("Error : test_intern failed - 1");
		if ( "pqr".intern() == hp.intern())
			harness.fail("Error : test_intern failed - 2");
		if ( "".intern() != nullstr.intern())
			harness.fail("Error : test_intern failed - 3");
		if ( "".intern() == hp.intern())
			harness.fail("Error : test_intern failed - 4");
		hp = "";
		if ( "".intern() != hp.intern())
			harness.fail("Error : test_intern failed - 5");
		StringBuffer buff= new StringBuffer();
		buff.append('a');
		buff.append('b');
		if ( "ab".intern() != buff.toString().intern())
			harness.fail("Error : test_intern failed - 6");
		StringBuffer buff1 = new StringBuffer();
		if ( "".intern() != buff1.toString().intern())
			harness.fail("Error : test_intern failed - 7");

	}
	public void test_trim()
	{
	    String source = "   laura";
	    String dest;

	    dest = source.trim();
	    if (!dest.equals("laura")) {
		harness.fail("Error - test_trim - 1");
		System.out.println("expected 'laura', got '" + dest + "'");
	    }

	    source = "			laura";
	    dest = source.trim();
	    if (!dest.equals("laura")) {
		harness.fail("Error - test_trim - 2");
		System.out.println("expected 'laura', got '" + dest + "'");
	    }

	    source = "              ";
	    dest = source.trim();
	    if (!dest.equals("")) {
		harness.fail("Error - test_trim - 3");
		System.out.println("expected '', got '" + dest + "'");
	    }
	    source = "laura";
	    dest = source.trim();
	    if (dest != source) {
		harness.fail("Error - test_trim - 4");
		System.out.println("Expected strings to be equal");
	    }
	    source = "l        ";
	    dest = source.trim();
	    if (!dest.equals("l")) {
		harness.fail("Error - test_trim - 5");
		System.out.println("expected 'l', got '" + dest + "'");
	    }
	    source = "           l";
	    dest = source.trim();
	    if (!dest.equals("l")) {
		harness.fail("Error - test_trim - 6");
		System.out.println("expected 'l', got '" + dest + "'");
	    }
	    source = "           l            ";
	    dest = source.trim();
	    if (!dest.equals("l")) {
		harness.fail("Error - test_trim - 7");
		System.out.println("expected 'l', got '" + dest + "'");
	    }
	    source = "           l a u r a             ";
	    dest = source.trim();
	    if (!dest.equals("l a u r a")) {
		harness.fail("Error - test_trim - 8");
		System.out.println("expected 'l a u r a', got '" + dest + "'");
	    }
	}

	public void testall()
	{
		test_Basics();
		test_toString();
		test_equals();
		test_hashCode();
		test_length();
		test_charAt();
		test_getChars();
		test_getBytes();	
		test_toCharArray();
		test_equalsIgnoreCase();
		test_compareTo();
		test_regionMatches();
		test_startsWith();
		test_endsWith();
		test_indexOf();
		test_lastIndexOf();
		test_substring();
		test_concat();
		test_replace();
		test_toLowerCase();
		test_toUpperCase();
		test_valueOf();
		test_intern();
		test_trim();
	}


  public void test (TestHarness the_harness)
  {
    harness = the_harness;
    testall ();
  }

}
